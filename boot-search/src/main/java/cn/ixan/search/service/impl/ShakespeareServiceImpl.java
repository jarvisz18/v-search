package cn.ixan.search.service.impl;

import cn.ixan.search.domain.Shakespeare;
import cn.ixan.search.domain.ShakespeareIndex;
import cn.ixan.search.domain.constant.Constant;
import cn.ixan.search.mapper.ShakespeareMapper;
import cn.ixan.search.service.ShakespeareService;
import cn.ixan.search.utils.DateHelper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.params.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShakespeareServiceImpl implements ShakespeareService {
    private static final Logger log = LoggerFactory.getLogger(ShakespeareServiceImpl.class);
    @Resource
    private Gson gson;
    @Resource
    private JestClient jestClient;
    @Resource
    private ShakespeareMapper shakespeareMapper;

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
                log.info("[{}]批量删除数据成功,插入数据[{}]条", DateHelper.currentTime(), list.size());
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
    public List<Shakespeare> query(Integer from, Integer size) {
        List<Shakespeare> list = Lists.newArrayList();
        String query = "{\"query\":{\"match_all\":{}},\"from\":" + from + ",\"size\":" + size + "}";
        JsonElement jsonElement = gson.fromJson(query, JsonElement.class);
        log.info("索引:[{}],查询脚本:\n{}", Constant.SHAKES_PEARE_INDEX, gson.toJson(jsonElement));
        Search search = new Search.Builder(query)
                .addIndex(Constant.SHAKES_PEARE_INDEX)
                .addType(Constant.INDEX_TYPE)
                .build();

        try {
            SearchResult execute = jestClient.execute(search);
            if (execute.isSucceeded()) {
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
            log.debug("[{}]批量插入数据成功,插入数据[{}]条", DateHelper.currentTime(), list.size());
        }
        return list.size();
    }


    private int addBatch(List<Shakespeare> list) {
        shakespeareMapper.addBatch(list);
        if(log.isDebugEnabled()){
            log.debug("[{}]批量插入数据成功,插入数据[{}]条", DateHelper.currentTime(), list.size());
        }
        return list.size();
    }
}
