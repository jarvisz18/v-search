package com.ixan.boot.test.juc.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/7 下午2:22
 * @description 使用工厂方法创建线程池案例
 */
public class ExecutorServiceExample2 {
	public static void main(String[] args) throws InterruptedException {
		testNewWorkStealingPool();
	}

	/**
	 * 与cpu核心数有关
	 * {@link Executors#newWorkStealingPool()}
	 */
	private static void testNewWorkStealingPool() throws InterruptedException {
		ExecutorService executorService = Executors.newWorkStealingPool();

		IntStream.rangeClosed(0, 100).boxed().forEach(i -> {
			executorService.execute(() -> {
				System.out.println(Thread.currentThread().getName() + " task [" + i + "]");
			});
		});
		executorService.shutdown();
		executorService.awaitTermination(30L, TimeUnit.MINUTES);
	}

	/**
	 * {@link Executors#newSingleThreadExecutor()}
	 *
	 * @throws InterruptedException
	 */
	private static void testNewSingleThreadPool() throws InterruptedException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		IntStream.rangeClosed(0, 5).boxed().forEach(i -> {
			executorService.execute(() -> {
				System.out.println(Thread.currentThread().getName() + " task [" + i + "]");
			});
		});

		executorService.shutdown();
		executorService.awaitTermination(30L, TimeUnit.MINUTES);
	}

	/**
	 * 使用 Executors.newFixedThreadPool()
	 * <p>
	 * {@link Executors#newFixedThreadPool(int)}
	 */
	private static void testNewFixedThreadPool() throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.rangeClosed(0, 100).boxed().forEach(i -> {
			executorService.execute(() -> {
				System.out.println(Thread.currentThread().getName() + " task [" + i + "]");
			});
		});

		executorService.shutdown();
		System.out.println("ThreadPoolExecutor getActiveCount: " + ((ThreadPoolExecutor) executorService).getActiveCount());
		executorService.awaitTermination(30L, TimeUnit.MINUTES);
	}

	/**
	 * 使用 Executors.newCachedThreadPool()
	 * <p>
	 * {@link Executors#newCachedThreadPool()}
	 */
	private static void testNewCachedThreadPool() throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		IntStream.rangeClosed(0, 100).boxed().forEach(i -> {
			executorService.execute(() -> {
				System.out.println(Thread.currentThread().getName() + " task [" + i + "]");
			});
		});

		executorService.shutdown();
		System.out.println("ThreadPoolExecutor getActiveCount: " + ((ThreadPoolExecutor) executorService).getActiveCount());
		executorService.awaitTermination(30L, TimeUnit.MINUTES);
	}
}
