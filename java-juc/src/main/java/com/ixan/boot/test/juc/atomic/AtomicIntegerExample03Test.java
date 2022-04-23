package com.ixan.boot.test.juc.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/21 21:41
 * @description 原子类生成序列号
 * @version 1.0
 */
public class AtomicIntegerExample03Test {
	static AtomicInteger count = new AtomicInteger(0);

	public static void main(String[] args) {
		int sum = 10000;
		Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
		CountDownLatch countDownLatch = new CountDownLatch(sum);

		for (int i = 0; i < sum; i++) {
			new Thread(() -> {
				try {
					int num = nextId();
					System.out.println(Thread.currentThread().getName() + ":" + num);
					set.add(num);
				} finally {
					countDownLatch.countDown();
				}
			}).start();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(set.size());
	}

	public static int nextId() {
		synchronized (AtomicIntegerExample03Test.class) {
			return count.incrementAndGet();
		}
	}


}
