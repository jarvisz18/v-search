package com.ixan.boot.test.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/7 下午5:10
 * @description ExecutorServiceExample3
 */
public class ExecutorServiceExample3 {
	public static void main(String[] args) {
		test();
	}

	public static void test() {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

		executorService.execute(() -> {
			System.out.println(Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(1L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
	}
}
