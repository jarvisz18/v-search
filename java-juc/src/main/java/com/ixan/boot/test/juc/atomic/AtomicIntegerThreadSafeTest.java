package com.ixan.boot.test.juc.atomic;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/19 下午8:23
 * @description 线程安全的计数
 */
public class AtomicIntegerThreadSafeTest {
	private static final AtomicInteger counter = new AtomicInteger();

	public static void main(String[] args) throws InterruptedException {
		HashSet<Integer> set = new HashSet<>();
		CountThread countThreadA = new CountThread(set);
		CountThread countThreadB = new CountThread(set);
		CountThread countThreadC = new CountThread(set);

		countThreadA.start();
		countThreadB.start();
		countThreadC.start();

		countThreadA.join();
		countThreadB.join();
		countThreadC.join();
		System.out.println(set.size());
	}

	private static class CountThread extends Thread {
		private final Set<Integer> set;

		private CountThread(Set<Integer> set) {
			this.set = set;
		}

		@Override
		public void run() {
			int limit = 0;
			while (limit < 500) {
				int value = counter.incrementAndGet();
				System.out.println(Thread.currentThread().getName() + ",value:" + value);
				set.add(value);
				limit++;
			}
		}
	}
}
