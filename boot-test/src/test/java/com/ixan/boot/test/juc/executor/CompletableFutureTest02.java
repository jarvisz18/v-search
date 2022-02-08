package com.ixan.boot.test.juc.executor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/8 15:42
 * @description 异步回调的方式加快接口访问速度
 * @version 1.0
 */
public class CompletableFutureTest02 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Instant instantStart = Instant.now();
		doTaskWithCompletableFuture();
		Instant instantEnd = Instant.now();
		System.out.println("total seconds:" + (ChronoUnit.SECONDS.between(instantStart, instantEnd)));
	}

	private static void doTaskWithCompletableFuture() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(4);
		CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
			int result = task01();
			countDownLatch.countDown();
			return result;
		});
		CompletableFuture<Integer> future02 = CompletableFuture.supplyAsync(() -> {
			int result = task02();
			countDownLatch.countDown();
			return result;
		});
		CompletableFuture<Integer> future03 = CompletableFuture.supplyAsync(() -> {
			int result = task03();
			countDownLatch.countDown();
			return result;
		});
		CompletableFuture<Integer> future04 = CompletableFuture.supplyAsync(() -> {
			int result = task04();
			countDownLatch.countDown();
			return result;
		});

		countDownLatch.await();

		future01.whenComplete((t, u) -> System.out.println(t));
		future02.whenComplete((t, u) -> System.out.println(t));
		future03.whenComplete((t, u) -> System.out.println(t));
		future04.whenComplete((t, u) -> System.out.println(t));
	}

	/**
	 * 使用CountDownLatch，等待所有线程执行完毕，获取结果
	 * {@link CountDownLatch}
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private static void doTaskWithCountDownLatch() throws ExecutionException, InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(4);
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		Future<Integer> future01 = executorService.submit(() -> {
			int result = task01();
			countDownLatch.countDown();
			return result;
		});
		Future<Integer> future02 = executorService.submit(() -> {
			int result = task02();
			countDownLatch.countDown();
			return result;
		});
		Future<Integer> future03 = executorService.submit(() -> {
			int result = task03();
			countDownLatch.countDown();
			return result;
		});
		Future<Integer> future04 = executorService.submit(() -> {
			int result = task04();
			countDownLatch.countDown();
			return result;
		});

		countDownLatch.await();

		System.out.println(future01.get());
		System.out.println(future02.get());
		System.out.println(future03.get());
		System.out.println(future04.get());

		executorService.shutdown();
	}

	/**
	 * 异步获取结果,谁先执行完毕谁先拿到结果,不适用于执行完立即结束的任务
	 * {@link ExecutorCompletionService#submit(Callable)}
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private static void doTaskWithExecutorCompletionService() throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
		completionService.submit(CompletableFutureTest02::task01);
		completionService.submit(CompletableFutureTest02::task02);
		completionService.submit(CompletableFutureTest02::task03);
		completionService.submit(CompletableFutureTest02::task04);

		Future<Integer> result = null;
		while ((result = completionService.poll(10, TimeUnit.SECONDS)) != null) {
			System.out.println(result.get());
		}
		executorService.shutdown();
	}

	/**
	 * 阻塞获取结果,同步执行
	 * {@link Future#get()}
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private static void doTaskWithFuture() throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		Integer task01 = executorService.submit(CompletableFutureTest02::task01).get();
		Integer task02 = executorService.submit(CompletableFutureTest02::task02).get();
		Integer task03 = executorService.submit(CompletableFutureTest02::task03).get();
		Integer task04 = executorService.submit(CompletableFutureTest02::task04).get();
		System.out.println(task01);
		System.out.println(task02);
		System.out.println(task03);
		System.out.println(task04);

		executorService.shutdown();
	}

	/**
	 * 正常业务执行,同步
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private static void doTaskNormal() {
		Integer task01 = task01();
		Integer task02 = task02();
		Integer task03 = task03();
		Integer task04 = task04();
		System.out.println(task01);
		System.out.println(task02);
		System.out.println(task03);
		System.out.println(task04);
	}

	private static Integer task04() {
		sleep(1L);
		return 1;
	}

	private static Integer task03() {
		sleep(3L);
		return 3;
	}

	private static Integer task02() {
		sleep(5L);
		return 5;
	}

	private static Integer task01() {
		sleep(10L);
		return 10;
	}

	private static void sleep(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
