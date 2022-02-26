package com.ixan.boot.test.juc.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/11 下午10:19
 * @description 本地缓存测试
 */
public class LocalCacheMapTest {
	private static final Random random = new Random();

	public static void main(String[] args) {
		//生产者线程，一直生产元素
		Thread thread1 = new Thread(() -> {
			String name = Thread.currentThread().getName();
			while (true) {
				String value = String.valueOf(random.nextInt(100));
				Object obj = LRULocalCacheMap.get(value);
				if (null != obj) {
					System.out.println(name + " get value:" + obj + " from LocalCacheMap");
				} else {
					System.out.println(name + " get value:" + value + " from other ");
					LRULocalCacheMap.add(value, value, 30);
				}
				try {
					TimeUnit.SECONDS.sleep(1L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-producer");


		//消费者线程
		Thread thread2 = new Thread(() -> {
			String name = Thread.currentThread().getName();
			while (true) {
				long size = LRULocalCacheMap.size();
				System.out.println(name + " LocalCacheMap size:" + size);
				try {
					TimeUnit.SECONDS.sleep(1L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-monitor");
		thread2.setDaemon(true);

		thread1.start();
		thread2.start();
	}
}
