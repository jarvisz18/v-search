package com.ixan.boot.test.juc.executor;

import java.util.concurrent.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/8 11:45
 * @description Future 案例学习
 * @version 1.0
 */
public class FutureExample1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		testCallableGetInterrupt();
	}

	public static void testCallableGetInterrupt() throws ExecutionException, InterruptedException, TimeoutException {
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
		Thread callerThread = Thread.currentThread();
		new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(20L);
				callerThread.interrupt();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		Integer result = future.get(1L, TimeUnit.SECONDS);
		System.out.println("result:" + result);
	}

	public static void testCallableGetTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
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
		Integer result = future.get(1L, TimeUnit.SECONDS);
		System.out.println("result:" + result);
	}

	public static void testCallableGet() throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Integer> future = executorService.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(3L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 10;
		});
		Integer result = future.get();
		System.out.println("result:" + result);
	}

	/**
	 * {@link Future#get()}
	 * @throws InterruptedException
	 */
	public static void testRunnableCustomGet() throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Future<String> submit = executorService.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(1L);
				System.out.println(Thread.currentThread().getName() + " is execute.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "DONE");
		System.out.println("i will be execute quickly");
		String result = submit.get();
		System.out.println("result:" + result);

		executorService.shutdown();
		executorService.awaitTermination(30, TimeUnit.MINUTES);
	}

	/**
	 * {@link Future#get()}
	 * @throws InterruptedException
	 */
	public static void testRunnableGet() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Future<?> submit = executorService.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(1L);
				System.out.println(Thread.currentThread().getName() + " is execute.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println("i will be execute quickly");
		Object NULL = submit.get();
		System.out.println("result:" + NULL);

		executorService.shutdown();
		executorService.awaitTermination(30, TimeUnit.MINUTES);
	}
}
