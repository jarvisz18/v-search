package com.ixan.boot.test.juc.basic;

import org.junit.Test;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/13 16:06
 * @description 线程顺序打印测试
 * @version 1.0
 */
public class ThreadSortPrintTest {

	@Test
	public void testSortPrint() throws InterruptedException {
		Thread thread1 = new Thread(getRunnable(), "T1");
		Thread thread2 = new Thread(getRunnable(), "T2");
		Thread thread3 = new Thread(getRunnable(), "T3");

		thread1.start();
		thread1.join();

		thread2.start();
		thread2.join();

		thread3.start();
		thread3.join();
	}

	private Runnable getRunnable() {
		return () -> System.out.println(Thread.currentThread().getName());
	}

	@Test
	public void testUnSortPrint() {
		Thread thread1 = new Thread(getRunnable());
		Thread thread2 = new Thread(getRunnable());
		Thread thread3 = new Thread(getRunnable());
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
