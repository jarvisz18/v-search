package com.ixan.boot.test.juc.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/13 11:13
 * @description Map性能大比拼
 * @version 1.0
 */
public class CollectionPerformanceTest1 {

	public static void main(String[] args) {
		testCollectionAdd(new HashMap<>());
		testCollectionAdd(Collections.synchronizedMap(new HashMap<>()));
		testCollectionAdd(new Hashtable<>());
		testCollectionAdd(new ConcurrentHashMap<>());
	}

	public static void testCollectionAdd(Map<String, Integer> map) {
		long startTime = System.currentTimeMillis();
		ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
		final int MAX_COUNT = 5000000;
		for (int i = 0; i < MAX_COUNT; i++) {
			if (null == map.get(UUID.randomUUID().toString())) {
				map.put(UUID.randomUUID().toString(), threadLocalRandom.nextInt(MAX_COUNT * 300));
			} else {
				map.put(UUID.randomUUID().toString(), threadLocalRandom.nextInt(MAX_COUNT * 300));
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.printf("MAP:[%s]插入[%s]条数据,耗时[%s]ms..\n", map.getClass().getSimpleName(), map.size(), (endTime - startTime));
		System.out.println("===========================================================================================");
	}
}
