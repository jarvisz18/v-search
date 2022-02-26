package com.ixan.boot.test.juc.executor;

import java.util.concurrent.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/14 15:33
 * @description CountDownLatch 测试
 * @version 1.0
 */
public class CountDownLatchTest {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		testOneByOne();
		System.out.printf("testOneByOne spend time:[%s]ms \n", (System.currentTimeMillis() - startTime));
		System.out.println("==============================================================");

		long startTime4Executor = System.currentTimeMillis();
		testByExecutor();
		System.out.printf("testByExecutor spend time:[%s]ms \n", (System.currentTimeMillis() - startTime4Executor));
		System.out.println("==============================================================");

		long startTime4CompletableFuture = System.currentTimeMillis();
		testCompletableFuture();
		System.out.printf("testCompletableFuture spend time:[%s]ms \n", (System.currentTimeMillis() - startTime4CompletableFuture));
	}

	public static void testCompletableFuture() {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		CompletableFuture<Boolean> test1Future = CompletableFuture.supplyAsync(() -> {
			test1();
			return Boolean.TRUE;
		}, executorService);

		CompletableFuture<Boolean> test2Future = CompletableFuture.supplyAsync(() -> {
			test2();
			return Boolean.TRUE;
		}, executorService);

		CompletableFuture<Boolean> test3Future = CompletableFuture.supplyAsync(() -> {
			test3();
			return Boolean.TRUE;
		}, executorService);

		CompletableFuture<Boolean> test4Future = CompletableFuture.supplyAsync(() -> {
			test4();
			return Boolean.TRUE;
		}, executorService);

		CompletableFuture.allOf(test1Future, test2Future, test3Future, test4Future).join();
		//关闭线程池
		executorService.shutdown();

		try {
			test1Future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		try {
			test2Future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		try {
			test3Future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		try {
			test4Future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void testByExecutor() {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		CountDownLatch countDownLatch = new CountDownLatch(4);
		executorService.execute(() -> {
			try {
				test1();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		});

		executorService.execute(() -> {
			try {
				test2();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		});

		executorService.execute(() -> {
			try {
				test3();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		});

		executorService.execute(() -> {
			try {
				test4();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				countDownLatch.countDown();
			}
		});

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
	}

	public static void testOneByOne() {
		test1();
		test2();
		test3();
		test4();
	}

	public static void test4() {
		sleep(1020L);
	}

	public static void test3() {
		sleep(500L);
	}

	public static void test2() {
		sleep(205L);
	}

	public static void test1() {
		sleep(125L);
	}

	public static void sleep(Long seconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
