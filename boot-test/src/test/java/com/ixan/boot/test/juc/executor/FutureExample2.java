package com.ixan.boot.test.juc.executor;

import java.util.concurrent.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/8 14:13
 * @description Future 案例测试
 * @version 1.0
 */
public class FutureExample2 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		testIsDoneError();
	}

	public static void testIsDoneError() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Integer> future = executorService.submit(() -> {
			throw new RuntimeException();
		});
		Integer result = null;
		try {
			result = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(result + " is done:" + future.isDone());
	}

	public static void testIsDoneNormal() throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Integer> future = executorService.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(3L);
				System.out.println("I am finished.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 10;
		});
		Integer result = future.get();
		System.out.println(result + " is done:" + future.isDone());
	}
}
