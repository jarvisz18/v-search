package com.ixan.boot.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/9/16 下午9:27
 * @description 连接池
 */
public class ConnectPool {
	public static final Map<String, String> POOL_MAP = new ConcurrentHashMap<>();

	static {
		POOL_MAP.put("db2", "db2");
	}
}
