package com.ixan.boot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/11 下午8:37
 * @description fastJSON工具类
 */
public final class JSONHelper {
	private JSONHelper() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 将JSON转换成Map对象
	 */
	public static <T> Map<String, T> jsonToMap(String json, Class<T> tClass) {
		@SuppressWarnings("unchecked")
		Map<String, T> map = JSON.parseObject(json, new TypeReference<Map<String, T>>() {
		});
		return map;
	}

	/**
	 * 将JSON转换成List对象
	 */
	public static <T> List<T> jsonToList(String json, Class<T> tClass) {
		@SuppressWarnings("unchecked")
		List<T> list = JSON.parseObject(json, new TypeReference<List<T>>() {
		});
		return list;
	}

	/**
	 * JSON 转换成POJO
	 */
	public static <T> T getObject(String json, Class<T> tClass) {
		return JSONObject.parseObject(json, tClass);
	}

	/**
	 * POJO  转换成JSON
	 */
	public static <T> String objectToString(T bean) {
		return JSON.toJSONString(bean);
	}
}
