package cn.ixan.search.service.impl;

import cn.ixan.search.domain.BaseIndexDTO;
import cn.ixan.search.domain.Bucket;
import cn.ixan.search.service.ThirdSystemService;
import cn.ixan.search.web.controller.dto.IndexParamDTO;
import cn.ixan.search.web.controller.dto.ThirdQueryDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ThirdSystemServiceImpl implements ThirdSystemService {
	@Autowired
	private JestClient jestClient;
	@Autowired
	private Gson gson;


	@Override
	public Map<String, Object> deleteByParam(IndexParamDTO indexParamDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		String index = indexParamDTO.getIndex();
		String type = indexParamDTO.getType();
		Object object = indexParamDTO.getObject();
		DeleteByQuery build = new DeleteByQuery.Builder(gson.toJson(object)).
				addIndex(index).addType(type).build();
		try {
			JestResult execute = jestClient.execute(build);
			int responseCode = execute.getResponseCode();
			if (execute.isSucceeded()) {
				resultMap.put("data", execute.getJsonString());
				resultMap.put("code", responseCode);
				resultMap.put("msg", "ok");
			} else {
				resultMap.put("code", responseCode);
				resultMap.put("msg", execute.getErrorMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> delete(IndexParamDTO indexParamDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		String index = indexParamDTO.getIndex();
		String type = indexParamDTO.getType();
		String id = indexParamDTO.getId();
		Delete build = new Delete.Builder(id).
				index(index).type(type).build();
		try {
			DocumentResult execute = jestClient.execute(build);
			int responseCode = execute.getResponseCode();
			if (execute.isSucceeded()) {
				resultMap.put("data", execute.getJsonString());
				resultMap.put("code", responseCode);
				resultMap.put("msg", "ok");
			} else {
				resultMap.put("code", responseCode);
				resultMap.put("msg", execute.getErrorMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> update(IndexParamDTO indexParamDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		Object object = indexParamDTO.getObject();
		String index = indexParamDTO.getIndex();
		String type = indexParamDTO.getType();
		String id = indexParamDTO.getId();
		Index build = new Index.Builder(object)
				.index(index)
				.type(type).id(id).build();
		try {
			DocumentResult execute = jestClient.execute(build);
			int responseCode = execute.getResponseCode();
			if (execute.isSucceeded()) {
				resultMap.put("data", execute.getJsonString());
				resultMap.put("code", responseCode);
				resultMap.put("msg", "ok");
			} else {
				resultMap.put("code", responseCode);
				resultMap.put("msg", execute.getErrorMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> insert(IndexParamDTO indexParamDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		Object object = indexParamDTO.getObject();
		String index = indexParamDTO.getIndex();
		String type = indexParamDTO.getType();
		Index build = new Index.Builder(object)
				.index(index)
				.type(type).build();
		try {
			DocumentResult execute = jestClient.execute(build);
			int responseCode = execute.getResponseCode();
			if (execute.isSucceeded()) {
				resultMap.put("data", execute.getJsonString());
				resultMap.put("code", responseCode);
				resultMap.put("msg", "ok");
			} else {
				resultMap.put("code", responseCode);
				resultMap.put("msg", execute.getErrorMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> search(ThirdQueryDTO queryDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		String indexName = queryDTO.getIndexName();
		String indexType = queryDTO.getIndexType();
		String query = gson.toJson(queryDTO.getQuery());
		if (log.isInfoEnabled()) {
			log.info("索引:[{}],查询脚本:\n {}", indexName, query);
		}
		Search search = new Search.Builder(query).addIndex(indexName).addType(indexType).build();
		try {
			SearchResult execute = jestClient.execute(search);
			if (execute.isSucceeded()) {
				List<SearchResult.Hit<Object, Void>> hits = execute.getHits(Object.class);
				List<Object> collect = hits.stream().map(e -> e.source).collect(Collectors.toList());
				resultMap.put("data", collect);
			}
			resultMap.put("msg", StringUtils.isNotBlank(execute.getErrorMessage()) ? execute.getErrorMessage() : "ok");
			resultMap.put("code", execute.getResponseCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> repeat(BaseIndexDTO baseIndexDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		String indexName = baseIndexDTO.getIndexName();
		String indexType = baseIndexDTO.getIndexType();
		String field = baseIndexDTO.getField();
		Integer size = baseIndexDTO.getSize();
		String query = "{\"size\":0,\"aggs\":{\"nums\":{\"terms\":{\"field\":\"" + field + "\",\"size\":" + size + "}}}}";
		Search search = new Search.Builder(query).addIndex(indexName).addType(indexType).build();
		try {
			SearchResult execute = jestClient.execute(search);
			if (execute.isSucceeded()) {
				JsonObject jsonObject = execute.getJsonObject();
				JsonObject aggregations = jsonObject.get("aggregations").getAsJsonObject();
				JsonObject nums = aggregations.get("nums").getAsJsonObject();
				String buckets = nums.get("buckets").toString();
				List<Bucket> data = gson.fromJson(buckets, new TypeToken<List<Bucket>>() {
				}.getType());
				resultMap.put("data", data);
			}
			resultMap.put("total", execute.getTotal());
			resultMap.put("msg", execute.getErrorMessage());
			resultMap.put("code", execute.getResponseCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
