package cn.ixan.search.service.impl;

import cn.ixan.search.domain.User;
import cn.ixan.search.domain.UserExample;
import cn.ixan.search.mapper.UserMapper;
import cn.ixan.search.service.UserService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JestClient jestClient;

	/**
	 * 同步用户信息到ES库
	 * @return
	 */
	@Override
	public boolean syncUserInfo2ES() {
		boolean result =false;
		List<User> userList = userMapper.selectByExample(new UserExample());
		Set<Index> collect = userList.stream()
				.map(user -> new Index.Builder(user).build())
				.collect(Collectors.toSet());
		Bulk bulk = new Bulk.Builder()
				.defaultIndex("user")
				.defaultType("info")
				.addAction(collect).build();
		try {
			BulkResult execute = jestClient.execute(bulk);
			result = execute.isSucceeded();
		} catch (IOException e) {
			log.error("执行批量操作发生异常",e);
		}
		return result;
	}
}
