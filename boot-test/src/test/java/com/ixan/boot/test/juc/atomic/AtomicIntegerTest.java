package com.ixan.boot.test.juc.atomic;

import java.util.HashSet;
import java.util.Set;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/1/19 下午8:07
 * @description AtomicIntegerTest
 * 多线程计数，加synchronized
 */
public class AtomicIntegerTest {
	private static int value;

	private static final Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		Set<Integer> set = new HashSet<>();
		Thread threadA = new CountThread(set);
		Thread threadB = new CountThread(set);
		Thread threadC = new CountThread(set);
		threadA.start();
		threadB.start();
		threadC.start();

		threadA.join();
		threadB.join();
		threadC.join();

		System.out.println(set.size());
	}

	private static class CountThread extends Thread {
		private final Set<Integer> set;

		CountThread(Set<Integer> set) {
			this.set = set;
		}

		@Override
		public void run() {
			int limit = 0;
			while (limit < 500) {
				synchronized (lock) {
					value = value + 1;
					System.out.println(Thread.currentThread().getName() + ":value:" + value);
					limit++;
					set.add(value);
				}
			}
		}
	}

}
