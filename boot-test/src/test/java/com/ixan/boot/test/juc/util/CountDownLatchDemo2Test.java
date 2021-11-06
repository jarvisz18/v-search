package com.ixan.boot.test.juc.util;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/5 下午10:00
 * @description CountDownLatch demo2 test
 */
public class CountDownLatchDemo2Test {
	/**
	 * 使用场景:划分任务由多个线程执行，使用CountDownLatch等待线程执行完毕<br/>
	 */
	@Test
	public void latch() throws InterruptedException {
		int count = 5;
		CountDownLatch countDownLatch = new CountDownLatch(count);
		for (int i = 0; i < 5; i++) {
			new TaskWorker(i * 20, countDownLatch).start();
		}
		countDownLatch.await();
		System.out.println("全部任务执行完毕");
	}

	private static class TaskWorker extends Thread {
		private final Integer start;
		private final CountDownLatch countDownLatch;

		TaskWorker(int i, CountDownLatch countDownLatch) {
			this.start = i;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			System.out.println(start + " 已经执行");
			countDownLatch.countDown();
		}
	}
}
