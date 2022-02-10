package com.ixan.boot.test.juc.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/8 10:55
 * @description ExecutorService 部分方法学习
 * @version 1.0
 */
public class ExecutorServiceExample4 {
	public static void main(String[] args) throws InterruptedException {
		testInvokeAll();
	}

	public static void testInvokeAll() throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		List<Callable<Integer>> collect = IntStream.range(0, 10).boxed().map(i -> (Callable<Integer>) () -> i * 2).collect(Collectors.toList());
		List<Future<Integer>> futures = executorService.invokeAll(collect);

		futures.stream().map(i -> {
			try {
				return i.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList()).forEach(System.out::println);

		executorService.shutdown();
		executorService.awaitTermination(1L, TimeUnit.MINUTES);
	}
}
