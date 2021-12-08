package cn.ixan.example;

import com.google.common.collect.Maps;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/19 22:17
 * @description GsonTest
 * @version 1.0
 */
public class GsonTest {
	public static void main(String[] args) {
		Gson gson = new Gson();
		HashMap<Object, Object> map = Maps.newHashMap();
		map.put("color", "red");
		map.put("weight", "10kg");
		//对象转JSON
		String s = gson.toJson(map);
		System.out.println(s);
		//JSON转MAP
		Map fromJson = gson.fromJson(s, Map.class);
		System.out.println(fromJson);
	}
}
