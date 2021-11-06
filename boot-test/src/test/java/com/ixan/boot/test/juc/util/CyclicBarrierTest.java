package com.ixan.boot.test.juc.util;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/5 下午10:14
 * @description cyclicBarrier test
 */
public class CyclicBarrierTest {

	@Test
	public void latch() {
		int count = 5;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> System.out.println("全部执行完毕"));
		ExecutorService executorService = Executors.newFixedThreadPool(count);
		while (true) {
			for (int x = 0; x < count; x++) {
				executorService.execute(new TaskWorker(x, cyclicBarrier));
			}
		}
	}

	private static class TaskWorker implements Runnable {
		private final Integer start;
		private final CyclicBarrier cyclicBarrier;

		TaskWorker(int x, CyclicBarrier cyclicBarrier) {
			this.start = x;
			this.cyclicBarrier = cyclicBarrier;
		}

		@Override
		public void run() {
			System.out.println(start + " 已经执行");
			try {
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
