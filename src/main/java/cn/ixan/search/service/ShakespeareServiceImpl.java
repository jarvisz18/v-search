package cn.ixan.search.service;

import cn.ixan.search.domain.ResultBean;
import cn.ixan.search.domain.Shakespeare;
import cn.ixan.search.domain.ShakespeareIndex;
import cn.ixan.search.domain.constant.Constant;
import cn.ixan.search.mapper.ShakespeareMapper;
import cn.ixan.search.utils.DateUtil;
import com.google.gson.Gson;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.settings.GetSettings;
import io.searchbox.params.Parameters;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.util.Lists;
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

@Service
public class ShakespeareServiceImpl implements ShakespeareService{
    private static final Logger log = LoggerFactory.getLogger(ShakespeareServiceImpl.class);
    @Resource
    private Gson gson;
    @Resource
    private JestClient jestClient;
    @Resource
    private ShakespeareMapper shakespeareMapper;
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public boolean existIndex(String indexName, String indexType) {
        GetMapping mapping = new GetMapping.Builder()
                .addIndex(indexName)
                .addType(indexType)
                .build();
        try {
            JestResult execute = jestClient.execute(mapping);
            return execute.isSucceeded();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultBean<String> settings(String indexName, String indexType) {
        ResultBean<String> resultBean = new ResultBean<>();
        GetSettings mapping = new GetSettings.Builder()
                .addIndex(indexName)
                .build();
        try {
            JestResult execute = jestClient.execute(mapping);
            boolean succeeded = execute.isSucceeded();
            resultBean.setCode(execute.getResponseCode());
            resultBean.setMsg(execute.getErrorMessage());
            if(succeeded){
                resultBean.setData(gson.toJson(execute.getJsonObject()));
            } else {
                resultBean.setData(gson.toJson(execute));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    @Override
    public ResultBean<String> mapping(String indexName, String indexType) {
        ResultBean<String> resultBean = new ResultBean<>();
        GetMapping mapping = new GetMapping.Builder()
                .addIndex(indexName)
                .addType(indexType)
                .build();
        try {
            JestResult execute = jestClient.execute(mapping);
            boolean succeeded = execute.isSucceeded();
            resultBean.setCode(execute.getResponseCode());
            resultBean.setMsg(execute.getErrorMessage());
            if(succeeded){
                resultBean.setData(gson.toJson(execute.getJsonObject()));
            } else {
                resultBean.setData(gson.toJson(execute));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultBean;
    }

    @Override
    public Map<String, Object> reload() {
        Map<String,Object> map = new HashMap<>();
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);
        //获取数据库数据
        List<Shakespeare> shakespeareList = shakespeareMapper.findAll();
        List<Shakespeare> addList = new ArrayList<>();
        //获取ES库数据
        shakespeareList.forEach(e -> compare(count, addList, e));
        log.info("产生重复数据记录数:[{}]",count.get());
        bulkDataToES(addList);
        long end = System.currentTimeMillis();
        map.put("success","200");
        map.put("total",count.get());
        map.put("mills",end-start);
        return map;
    }

    private void compare(AtomicInteger count, List<Shakespeare> addList, Shakespeare shakespeare) {
        long start = System.currentTimeMillis();
        String line_id = shakespeare.getLine_id();
        List<Shakespeare> shake = listShakespeare(line_id);
        if(CollectionUtils.isEmpty(shake)){
            addList.add(shakespeare);
        } else if(shake.size()>1){
            delete(line_id);
            count.incrementAndGet();
            addList.add(shakespeare);
            log.info("产生重复数据的line_id:[{}]",line_id);
        }
        long end = System.currentTimeMillis();
        log.info("本次比较,耗时[{}]ms",end-start);
    }

    private boolean delete(String line_id) {
        String query = "{\"query\":{\"bool\":{\"must\":[{\"match\":{\"line_id\":"+line_id+"}}]}}}";
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
        String query = "{\"query\":{\"bool\":{\"must\":[{\"match\":{\"line_id\":"+line_id+"}}]}}}";
        Search search = new Search.Builder(query)
                .addIndex(Constant.SHAKES_PEARE_INDEX)
                .addType(Constant.INDEX_TYPE)
                .build();
        try {
            SearchResult execute = jestClient.execute(search);
            if(execute.isSucceeded()){
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
        String query = "{\"query\":{\"match_all\":{}},\"size\":"+ Constant.SCROLL_SIZE+"}";
        Search search = new Search.Builder(query)
                .addIndex(Constant.SHAKES_PEARE_INDEX)
                .addType(Constant.INDEX_TYPE)
                .setParameter(Parameters.SCROLL, Constant.SCROLL_TIME)
                .build();
        try {
            SearchResult execute = jestClient.execute(search);
            if(execute.isSucceeded()){
                Long total = execute.getTotal();
                log.info("索引[{}]存在[{}]条数据",Constant.SHAKES_PEARE_INDEX,total);
                List<SearchResult.Hit<Shakespeare, Void>> hits = execute.getHits(Shakespeare.class);
                list.addAll(hits.stream().map(e -> e.source).collect(Collectors.toList()));
                //获取scroll_id
                String scroll_id = execute.getJsonObject().get(Constant.SCROLL_ID).getAsString();
                while (true){
                    SearchScroll build = new SearchScroll.Builder(scroll_id, Constant.SCROLL_TIME).build();
                    JestResult result = jestClient.execute(build);
                    List<Shakespeare> source = result.getSourceAsObjectList(Shakespeare.class);
                    list.addAll(source);
                    if(CollectionUtils.isEmpty(source)){
                        break;
                    }
                }
                log.info("ES backupToDatabase to Database success, data size is [{}]",list.size());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean clean() {
        int count = 0;
        List<ShakespeareIndex> list = Lists.newArrayList();
        String query = "{\"query\":{\"match_all\":{}},\"size\":"+Constant.SCROLL_SIZE+"}";
        Search search = new Search.Builder(query)
                .addIndex(Constant.SHAKES_PEARE_INDEX)
                .addType(Constant.INDEX_TYPE)
                .setParameter(Parameters.SCROLL, Constant.SCROLL_TIME)
                .build();
        try {
            SearchResult execute = jestClient.execute(search);
            if(execute.isSucceeded()){
                Long total = execute.getTotal();
                log.info("索引[{}]存在[{}]条数据",Constant.SHAKES_PEARE_INDEX,total);
                List<SearchResult.Hit<Shakespeare, Void>> hits = execute.getHits(Shakespeare.class);
                list = hits.stream().map(this::convert).collect(Collectors.toList());
                count += clean(list);
                log.info("[{}]批量删除数据成功,插入数据[{}]条", DateUtil.currentTime(),list.size());
                list.clear();
                log.info("clean ES data success, data size is [{}]",count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private ShakespeareIndex convert(SearchResult.Hit<Shakespeare, Void> e) {
        ShakespeareIndex shakespeareIndex = new ShakespeareIndex();
        shakespeareIndex.setIndex(e.index);
        shakespeareIndex.setType(e.type);
        shakespeareIndex.setId(e.id);
        shakespeareIndex.setSource(e.source);
        shakespeareIndex.setScore(e.score);
        return shakespeareIndex;
    }

    private int clean(List<ShakespeareIndex> source) {
        List<Delete> collect = source.stream().map(shakespeareIndex -> new Delete.Builder(shakespeareIndex.getId())
                .index(Constant.SHAKES_PEARE_INDEX)
                .type(Constant.INDEX_TYPE).build())
                .collect(Collectors.toList());
        Bulk bulk = new Bulk.Builder()
                .defaultIndex(Constant.SHAKES_PEARE_INDEX)
                .defaultType(Constant.INDEX_TYPE)
                .addAction(collect)
                .setParameter(Parameters.REFRESH, true)
                .build();
        try {
            BulkResult execute = jestClient.execute(bulk);
            if (execute.isSucceeded()){
                log.info("处理结果[{}]",execute.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean init() {
        AtomicInteger count = new AtomicInteger(1);
        int total = shakespeareMapper.total();
        int from = 0;
        while (from < total){
            List<Shakespeare> list = shakespeareMapper.simpleQuery(from,Constant.SCROLL_SIZE);
            from += list.size();
            bulkDataToES(list);
            log.info("第[{}]次导入ES[{}]条数据",count.incrementAndGet(),list.size());
        }
        return true;
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
        String query = "{\"query\":{\"match_all\":{}},\"size\":"+Constant.SCROLL_SIZE+"}";
        Search search = new Search.Builder(query)
                .addIndex(Constant.SHAKES_PEARE_INDEX)
                .addType(Constant.INDEX_TYPE)
                .setParameter(Parameters.SCROLL, Constant.SCROLL_TIME)
                .build();
        try {
            SearchResult execute = jestClient.execute(search);
            if(execute.isSucceeded()){
                Long total = execute.getTotal();
                log.info("索引[{}]存在[{}]条数据",Constant.SHAKES_PEARE_INDEX,total);
                List<SearchResult.Hit<Shakespeare, Void>> hits = execute.getHits(Shakespeare.class);
                list = hits.stream().map(e -> e.source).collect(Collectors.toList());
                count += inserBatch(list);
                list.clear();
                //获取scroll_id
                String scroll_id = execute.getJsonObject().get(Constant.SCROLL_ID).getAsString();
                log.info("当前scroll_id为:[{}]",scroll_id);
                log.info("当前scroll_id长度为:[{}]",scroll_id.length());
                while (true){
                    SearchScroll build = new SearchScroll.Builder(scroll_id, Constant.SCROLL_TIME).build();
                    JestResult result = jestClient.execute(build);
                    List<Shakespeare> source = result.getSourceAsObjectList(Shakespeare.class);
                    if(CollectionUtils.isEmpty(source)){
                        break;
                    }
                    //插入操作
                    count += inserBatch(source);
                }
                log.info("ES backupToDatabase to Database success, data size is [{}]",count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Shakespeare> query(Integer from, Integer size) {
        List<Shakespeare> list = Lists.newArrayList();
        String query = "{\"query\":{\"match_all\":{}},\"from\":"+from+",\"size\":"+size+"}";
        Search search = new Search.Builder(query)
                .addIndex(Constant.SHAKES_PEARE_INDEX)
                .addType(Constant.INDEX_TYPE)
                .build();

        try {
            SearchResult execute = jestClient.execute(search);
            if(execute.isSucceeded()){
                List<SearchResult.Hit<Shakespeare, Void>> hits = execute.getHits(Shakespeare.class);
                list = hits.stream().map(e -> e.source).collect(Collectors.toList());
                return list;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int save(Integer from, Integer size) {
        List<Shakespeare> query = query(from, size);
        query.forEach(e -> shakespeareMapper.save(e));
        if(log.isDebugEnabled()){
            log.debug("批量插入数据成功,插入数据[{}]条",query.size());
        }
        return query.size();
    }

    private int save(List<Shakespeare> list) {
        list.forEach(e -> shakespeareMapper.save(e));
        if(log.isDebugEnabled()){
            log.debug("[{}]批量插入数据成功,插入数据[{}]条", DateUtil.currentTime(),list.size());
        }
        return list.size();
    }

    private int inserBatch(List<Shakespeare> list) {
        ShakespeareMapper mapper = sqlSessionTemplate.getMapper(ShakespeareMapper.class);
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            for(Shakespeare shakespeare:list){
                mapper.save(shakespeare);
            }
            if(log.isDebugEnabled()){
                log.debug("[{}]批量插入数据成功,插入数据[{}]条", DateUtil.currentTime(),list.size());
            }
            session.commit();
        } finally {
            session.close();
        }
        return list.size();
    }

    private int addBatch(List<Shakespeare> list) {
        shakespeareMapper.addBatch(list);
        if(log.isDebugEnabled()){
            log.debug("[{}]批量插入数据成功,插入数据[{}]条", DateUtil.currentTime(),list.size());
        }
        return list.size();
    }
}
