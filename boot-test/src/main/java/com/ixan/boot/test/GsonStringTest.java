package com.ixan.boot.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/4 9:31
 * @description string json test
 */
public class GsonStringTest {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put("data1", 100);
		map.put("data2", "hello  world");
		map.put("list", Arrays.asList("String 1", "String 2", "String 3"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(map);
		System.out.println(new Gson().toJson(map));
		System.out.println("----------------------------------------------");
		System.out.println("\r\n" + json);
	}
}
