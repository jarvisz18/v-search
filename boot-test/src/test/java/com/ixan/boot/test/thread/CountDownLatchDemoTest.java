package com.ixan.boot.test.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/10/24 下午4:31
 * @description thread demo test
 */
public class CountDownLatchDemoTest {
	private static final Logger logger = LoggerFactory.getLogger(CountDownLatchDemoTest.class);

	@Test
	public void testCountDownLatch() {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 2000L, TimeUnit.SECONDS,
				new LinkedBlockingDeque<>(50),
				new ThreadFactoryBuilder().setNameFormat("task-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
		CountDownLatch countDownLatch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			threadPoolExecutor.submit(() -> {
				try {
					doTask(finalI);
				} catch (Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadPoolExecutor.shutdown();
		logger.info("all task finish");
	}

	private void doTask(int finalI) {
		if (finalI % 3 == 0) {
			throw new RuntimeException();
		}
		logger.info("current thread is :" + Thread.currentThread().getName() + ":" + finalI);
	}
}
