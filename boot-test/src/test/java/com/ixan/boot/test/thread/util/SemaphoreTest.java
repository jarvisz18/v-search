package com.ixan.boot.test.thread.util;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/5 下午10:26
 * @description semaphore
 */
public class SemaphoreTest {

	/**
	 * 使用场景:限制资源的访问<br/>
	 */
	@Test
	public void latch() throws IOException {
		int count = 5;
		Semaphore semaphore = new Semaphore(1);
		ExecutorService executorService = Executors.newFixedThreadPool(count);
		for (int i = 0; i < count; i++) {
			executorService.execute(new TaskWorker(i, semaphore));
		}
		System.in.read();
	}

	private static class TaskWorker implements Runnable {
		private final Integer start;
		private final Semaphore semaphore;

		TaskWorker(int i, Semaphore semaphore) {
			this.start = i;
			this.semaphore = semaphore;
		}


		@Override
		public void run() {
			try {
				System.out.println(start + " 准备执行");
				TimeUnit.SECONDS.sleep(1);
				semaphore.acquire();
				System.out.println(start + " 已经执行");
				semaphore.release();
				System.out.println(start + " 已经释放");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
