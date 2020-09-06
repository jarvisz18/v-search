package cn.ixan.search.service.impl;

import cn.ixan.search.domain.Shakespeare;
import cn.ixan.search.domain.constant.Constant;
import cn.ixan.search.mapper.ShakespeareMapper;
import cn.ixan.search.service.ShakeDataService;
import cn.ixan.search.utils.DateUtil;
import com.google.common.collect.Lists;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.params.Parameters;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/6 20:11
 * @description 莎士比亚DB操作实现
 */
@Service
public class ShakeDataServiceImpl implements ShakeDataService {
	private static final Logger log = LoggerFactory.getLogger(ShakeDataServiceImpl.class);
	@Resource
	private JestClient jestClient;
	@Resource
	private ShakespeareMapper shakespeareMapper;
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public boolean init() {
		AtomicInteger count = new AtomicInteger(1);
		int total = shakespeareMapper.total();
		int from = 0;
		while (from < total) {
			List<Shakespeare> list = shakespeareMapper.simpleQuery(from, Constant.SCROLL_SIZE);
			from += list.size();
			bulkDataToES(list);
			log.info("第[{}]次导入ES[{}]条数据", count.incrementAndGet(), list.size());
		}
		return true;
	}

	@Override
	public Map<String, Object> reload() {
		Map<String, Object> map = new HashMap<>();
		long start = System.currentTimeMillis();
		AtomicInteger count = new AtomicInteger(0);
		//获取数据库数据
		List<Shakespeare> shakespeareList = shakespeareMapper.findAll();
		List<Shakespeare> addList = new ArrayList<>();
		//获取ES库数据
		shakespeareList.forEach(e -> compare(count, addList, e));
		log.info("产生重复数据记录数:[{}]", count.get());
		bulkDataToES(addList);
		long end = System.currentTimeMillis();
		map.put("success", "200");
		map.put("total", count.get());
		map.put("mills", end - start);
		return map;
	}

	private void compare(AtomicInteger count, List<Shakespeare> addList, Shakespeare shakespeare) {
		long start = System.currentTimeMillis();
		String line_id = shakespeare.getLine_id();
		List<Shakespeare> shake = listShakespeare(line_id);
		if (CollectionUtils.isEmpty(shake)) {
			addList.add(shakespeare);
		} else if (shake.size() > 1) {
			delete(line_id);
			count.incrementAndGet();
			addList.add(shakespeare);
			log.info("产生重复数据的line_id:[{}]", line_id);
		}
		long end = System.currentTimeMillis();
		log.info("本次比较,耗时[{}]ms", end - start);
	}

	private boolean delete(String line_id) {
		String query = "{\"query\":{\"bool\":{\"must\":[{\"match\":{\"line_id\":" + line_id + "}}]}}}";
		DeleteByQuery builder = new DeleteByQuery.Builder(query).build();
		try {
			JestResult execute = jestClient.execute(builder);
			return execute.isSucceeded();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private List<Shakespeare> listShakespeare(String line_id) {
		List<Shakespeare> list = Lists.newArrayList();
		String query = "{\"query\":{\"bool\":{\"must\":[{\"match\":{\"line_id\":" + line_id + "}}]}}}";
		Search search = new Search.Builder(query)
				.addIndex(Constant.SHAKES_PEARE_INDEX)
				.addType(Constant.INDEX_TYPE)
				.build();
		try {
			SearchResult execute = jestClient.execute(search);
			if (execute.isSucceeded()) {
				List<SearchResult.Hit<Shakespeare, Void>> hits = execute.getHits(Shakespeare.class);
				list = hits.stream().map(e -> e.source).collect(Collectors.toList());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<Shakespeare> listShakespeare() {
		List<Shakespeare> list = Lists.newArrayList();
		String query = "{\"query\":{\"match_all\":{}},\"size\":" + Constant.SCROLL_SIZE + "}";
		Search search = new Search.Builder(query)
				.addIndex(Constant.SHAKES_PEARE_INDEX)
				.addType(Constant.INDEX_TYPE)
				.setParameter(Parameters.SCROLL, Constant.SCROLL_TIME)
				.build();
		try {
			SearchResult execute = jestClient.execute(search);
			if (execute.isSucceeded()) {
				Long total = execute.getTotal();
				log.info("索引[{}]存在[{}]条数据", Constant.SHAKES_PEARE_INDEX, total);
				List<SearchResult.Hit<Shakespeare, Void>> hits = execute.getHits(Shakespeare.class);
				list.addAll(hits.stream().map(e -> e.source).collect(Collectors.toList()));
				//获取scroll_id
				String scroll_id = execute.getJsonObject().get(Constant.SCROLL_ID).getAsString();
				while (true) {
					SearchScroll build = new SearchScroll.Builder(scroll_id, Constant.SCROLL_TIME).build();
					JestResult result = jestClient.execute(build);
					List<Shakespeare> source = result.getSourceAsObjectList(Shakespeare.class);
					list.addAll(source);
					if (CollectionUtils.isEmpty(source)) {
						break;
					}
				}
				log.info("ES backupToDatabase to Database success, data size is [{}]", list.size());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void bulkDataToES(List<Shakespeare> list) {
		List<Index> collect = new ArrayList<>();
		for (Shakespeare shakespeare : list) {
			Index index = new Index.Builder(shakespeare).build();
			collect.add(index);
		}
		Bulk build = new Bulk.Builder()
				.defaultIndex(Constant.SHAKES_PEARE_INDEX)
				.defaultType(Constant.INDEX_TYPE)
				.addAction(collect)
				.build();
		try {
			jestClient.execute(build);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean backupToDatabase() {
		int count = 0;
		List<Shakespeare> list = Lists.newArrayList();
		String query = "{\"query\":{\"match_all\":{}},\"size\":" + Constant.SCROLL_SIZE + "}";
		Search search = new Search.Builder(query)
				.addIndex(Constant.SHAKES_PEARE_INDEX)
				.addType(Constant.INDEX_TYPE)
				.setParameter(Parameters.SCROLL, Constant.SCROLL_TIME)
				.build();
		try {
			SearchResult execute = jestClient.execute(search);
			if (execute.isSucceeded()) {
				Long total = execute.getTotal();
				log.info("索引[{}]存在[{}]条数据", Constant.SHAKES_PEARE_INDEX, total);
				List<SearchResult.Hit<Shakespeare, Void>> hits = execute.getHits(Shakespeare.class);
				list = hits.stream().map(e -> e.source).collect(Collectors.toList());
				count += inserBatch(list);
				list.clear();
				//获取scroll_id
				String scroll_id = execute.getJsonObject().get(Constant.SCROLL_ID).getAsString();
				log.info("当前scroll_id为:[{}]", scroll_id);
				log.info("当前scroll_id长度为:[{}]", scroll_id.length());
				while (true) {
					SearchScroll build = new SearchScroll.Builder(scroll_id, Constant.SCROLL_TIME).build();
					JestResult result = jestClient.execute(build);
					List<Shakespeare> source = result.getSourceAsObjectList(Shakespeare.class);
					if (CollectionUtils.isEmpty(source)) {
						break;
					}
					//插入操作
					count += inserBatch(source);
				}
				log.info("ES backupToDatabase to Database success, data size is [{}]", count);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private int inserBatch(List<Shakespeare> list) {
		ShakespeareMapper mapper = sqlSessionTemplate.getMapper(ShakespeareMapper.class);
		SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			for (Shakespeare shakespeare : list) {
				mapper.save(shakespeare);
			}
			if (log.isDebugEnabled()) {
				log.debug("[{}]批量插入数据成功,插入数据[{}]条", DateUtil.currentTime(), list.size());
			}
			session.commit();
		} finally {
			session.close();
		}
		return list.size();
	}
}
