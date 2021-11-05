package com.ixan.boot.test.thread.util;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/5 下午10:37
 * @description Exchanger test
 */
public class ExchangerTest {

	@Test
	public void latch() throws IOException {
		int count = 5;
		Exchanger<String> exchanger = new Exchanger<>();
		ExecutorService executorService = Executors.newFixedThreadPool(count);
		for (int i = 0; i < count; i++) {
			executorService.execute(new TaskWorker(i, exchanger));
		}
		System.in.read();
	}

	private static class TaskWorker implements Runnable {
		private final Integer start;
		private final Exchanger<String> exchanger;

		TaskWorker(int i, Exchanger<String> exchanger) {
			this.start = i;
			this.exchanger = exchanger;
		}


		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " 准备执行");
				TimeUnit.SECONDS.sleep(start);
				System.out.println(Thread.currentThread().getName() + " 等待交换");
				String value = exchanger.exchange(Thread.currentThread().getName(), 30L, TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName() + " 交换得到的数据:" + value);
			} catch (InterruptedException | TimeoutException e) {
				e.printStackTrace();
			}
		}
	}
}
