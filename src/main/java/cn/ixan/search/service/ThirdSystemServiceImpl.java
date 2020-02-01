package cn.ixan.search.service;

import cn.ixan.search.domain.BaseIndexDTO;
import cn.ixan.search.domain.Bucket;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ThirdSystemServiceImpl implements ThirdSystemService {
    @Autowired
    private JestClient jestClient;
    @Autowired
    private Gson gson;


    @Override
    public Map<String, Object> repeat(BaseIndexDTO baseIndexDTO) {
        Map<String,Object> resultMap = new HashMap<>();
        String indexName = baseIndexDTO.getIndexName();
        String indexType = baseIndexDTO.getIndexType();
        String field = baseIndexDTO.getField();
        Integer size = baseIndexDTO.getSize();
        String query = "{\"size\":0,\"aggs\":{\"nums\":{\"terms\":{\"field\":\""+field+"\",\"size\":"+size+"}}}}";
        Search search = new Search.Builder(query).addIndex(indexName).addType(indexType).build();
        try {
            SearchResult execute = jestClient.execute(search);
            if(execute.isSucceeded()){
                JsonObject jsonObject = execute.getJsonObject();
                JsonObject aggregations = jsonObject.get("aggregations").getAsJsonObject();
                JsonObject nums = aggregations.get("nums").getAsJsonObject();
                String buckets = nums.get("buckets").toString();
                List<Bucket> data = gson.fromJson(buckets, new TypeToken<List<Bucket>>(){}.getType());
                resultMap.put("data",data);
            }
            resultMap.put("total",execute.getTotal());
            resultMap.put("msg",execute.getErrorMessage());
            resultMap.put("code",execute.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
