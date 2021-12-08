package cn.ixan.search.service.impl;

import cn.ixan.search.domain.ResultBean;
import cn.ixan.search.service.IndexService;
import com.google.gson.Gson;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.settings.GetSettings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/9/6 19:29
 * @description impl index service
 */
@Service
public class IndexServiceImpl implements IndexService {
	@Resource
	private Gson gson;
	@Resource
	private JestClient jestClient;

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
			if (succeeded) {
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
				.build();
		try {
			JestResult execute = jestClient.execute(mapping);
			boolean succeeded = execute.isSucceeded();
			resultBean.setCode(execute.getResponseCode());
			resultBean.setMsg(execute.getErrorMessage());
			if (succeeded) {
				resultBean.setData(gson.toJson(execute.getJsonObject()));
			} else {
				resultBean.setData(gson.toJson(execute));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultBean;
	}
}
