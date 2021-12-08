package com.ixan.boot.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/11 下午8:16
 * @description json与对象互转测试
 */
public class FastJSONConvertTest {

	@Test
	public void testStringToMap() {
		User user = new User("1", "mark", 20, "remark");
		Map<String, String> stringStringMap = JSON.parseObject(JSON.toJSONString(user), new TypeReference<Map<String, String>>() {
		});
		stringStringMap.forEach((k, v) -> System.out.println(k + ":" + v));
	}

	@Test
	public void testStringToList() {
		User user = new User("1", "mark", 20, "");
		User user1 = new User("2", "lily", 30, "");
		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(user1);
		String userListString = JSON.toJSONString(users);
		//方法1
		List<User> userList = JSONObject.parseArray(userListString, User.class);
		userList.forEach(System.out::println);
		System.out.println("++++++++++++++++++++++++++++++++++++++");
		//方法2
		List<User> userList1 = JSON.parseObject(userListString, new TypeReference<List<User>>() {
		});
		userList1.forEach(System.out::println);
	}

	//JSON转对象
	@Test
	public void testStringToObj() {
		User user = new User("1", "mark", 20, "");
		String str2 = JSON.toJSONString(user);
		User userObject = JSON.parseObject(str2, User.class);
		System.out.println(userObject);
	}

	//JSON转对象
	@Test
	public void testStringToObject() {
		User user = new User("1", "mark", 20, "");
		String str2 = JSON.toJSONString(user);
		User userObject = JSON.parseObject(str2, new TypeReference<User>() {
		});
		System.out.println(userObject);
	}

	//对象转string
	@Test
	public void testListToString() {
		User user = new User("1", "mark", 20, "");
		User user1 = new User("2", "lily", 30, "");
		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(user1);
		String jsonObject2 = JSON.toJSONString(users);
		System.out.println(jsonObject2);
	}

	@Test
	public void testObjectToString() {
		User user = new User("1", "mark", 20, "");
		//转换对象
		JSONObject jsonObject1 = JSONObject.parseObject(JSONObject.toJSONString(user));
		System.out.println(jsonObject1);
	}
}
