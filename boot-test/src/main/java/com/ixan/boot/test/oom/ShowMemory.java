package com.ixan.boot.test.oom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/15 21:35
 * @description show memory
 * @version 1.0
 */
public class ShowMemory {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShowMemory.class);

	public static void main(String[] args) throws InterruptedException {
		//get vm memory byte size
		long freeMemory = Runtime.getRuntime().freeMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long maxMemory = Runtime.getRuntime().maxMemory();
		LOGGER.info("freeMemory:{}[K Byte],totalMemory:{}[K Byte],maxMemory:{}[K Byte]", freeMemory / 1024, totalMemory / 1024, maxMemory / 1024);
		//
		int maxSize = 1000;
		Map<String, Object> map = new HashMap<>(maxSize);
		for (int i = 0; i < maxSize; i++) {
			map.put(String.valueOf(i), System.currentTimeMillis());
		}
		freeMemory = Runtime.getRuntime().freeMemory();
		totalMemory = Runtime.getRuntime().totalMemory();
		maxMemory = Runtime.getRuntime().maxMemory();
		LOGGER.info("freeMemory:{}[K Byte],totalMemory:{}[K Byte],maxMemory:{}[K Byte]", freeMemory / 1024, totalMemory / 1024, maxMemory / 1024);
		TimeUnit.HOURS.sleep(1L);
	}
}
