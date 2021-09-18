package com.ixan.boot.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/9/18 下午10:32
 * @description future task test
 */
public class FutureTaskTest {
	private static final Random random = new Random(10);

	public static void main(String[] args) {
		List<Future> result = new ArrayList<>();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		CountDownLatch countDownLatch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			Future future = pool.submit(() -> {
				int num = new FutureTaskTest().getNum();
				TimeUnit.MILLISECONDS.sleep(num);
				System.out.println(Thread.currentThread().getName() + " get num :" + num);
				countDownLatch.countDown();
				return num;
			});
			result.add(future);
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		result.forEach
				(e -> {
					try {
						System.out.println("future result:" + e.get());
					} catch (InterruptedException | ExecutionException ex) {
						ex.printStackTrace();
					}
				});

	}

	private int getNum() {
		return random.nextInt(5000);
	}
}
