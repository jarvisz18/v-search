package cn.ixan.search.service;

import cn.ixan.search.domain.Shakespeare;
import cn.ixan.search.domain.ShakespeareIndex;
import cn.ixan.search.domain.constant.Constant;
import cn.ixan.search.mapper.ShakespeareMapper;
import cn.ixan.search.utils.DateUtil;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.params.Parameters;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ShakespeareServiceImpl implements ShakespeareService{
    private static final Logger log = LoggerFactory.getLogger(ShakespeareServiceImpl.class);
    @Autowired
    private JestClient jestClient;
    @Autowired
    private ShakespeareMapper shakespeareMapper;

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
            builDataToES(list);
            log.info("第[{}]次导入ES[{}]条数据",count.incrementAndGet(),list.size());
        }
        return true;
    }

    private void builDataToES(List<Shakespeare> list) {
        List<Index> collect = list.stream().map(shakespeare -> new Index.Builder(shakespeare).build())
                .collect(Collectors.toList());
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
    public boolean backup() {
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
                count += save(list);
                list.clear();
                //获取scroll_id
                String scroll_id = execute.getJsonObject().get(Constant.SCROLL_ID).getAsString();
                log.info("当前scroll_id为:[{}]",scroll_id);
                log.info("当前scroll_id长度为:[{}]",scroll_id.length());
                while (true){
                    SearchScroll build = new SearchScroll.Builder(scroll_id, Constant.SCROLL_TIME).build();
                    String restMethodName = build.getRestMethodName();
                    log.info("请求方式:[{}]",restMethodName);
                    JestResult result = jestClient.execute(build);
                    List<Shakespeare> source = result.getSourceAsObjectList(Shakespeare.class);
                    if(CollectionUtils.isEmpty(source)){
                        break;
                    }
                    //插入操作
                    count += save(source);
                }
                log.info("ES backup to Database success, data size is [{}]",count);
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
                .addIndex("shakespeare")
                .addType("doc").build();

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
}
