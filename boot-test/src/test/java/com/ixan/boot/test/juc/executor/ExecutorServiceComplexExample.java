package com.ixan.boot.test.juc.executor;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/8 17:48
 * @description 关于ExecutorCompletionService的一个复杂案例
 * @version 1.0
 */
public class ExecutorServiceComplexExample {
	public static void main(String[] args) {
		testGetUnDoneTask1();
	}

	/**
	 * 等待12s取出未执行成功的任务，包含被打断的任务，推荐!
	 */
	public static void testGetUnDoneTask1() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
		List<MyCallableTask> collect = IntStream.range(0, 5).boxed().map(MyCallableTask::new)
				.collect(Collectors.toList());

		collect.forEach(completionService::submit);
		//等待12s 取出未执行成功的任务
		try {
			TimeUnit.SECONDS.sleep(12L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executorService.shutdownNow();
		List<MyCallableTask> list = collect.stream().filter(callable -> !callable.isSuccess()).collect(Collectors.toList());
		System.out.println("size:" + list.size());
	}

	/**
	 * 等待12s取出未执行成功的任务，不包含被打断的任务，不推荐!
	 */
	public static void testGetUnDoneTask() {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		ExecutorCompletionService<Object> completionService = new ExecutorCompletionService<>(executorService);
		IntStream.range(0, 5).boxed().forEach(i -> completionService.submit(Executors.callable(new MyRunnableTask(i))));
		//等待12s 取出未执行的任务
		try {
			TimeUnit.SECONDS.sleep(12L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Runnable> list = executorService.shutdownNow();
		System.out.println("size:" + list.size());
	}

	public static class MyCallableTask implements Callable<Integer> {
		private final int value;
		private boolean success = false;

		public MyCallableTask(int value) {
			this.value = value;
		}

		@Override
		public Integer call() throws Exception {
			try {
				System.out.println(Thread.currentThread().getName() + " will do task[" + value + "]");
				TimeUnit.SECONDS.sleep(value * 2 + 10);
				System.out.println(Thread.currentThread().getName() + " do task[" + value + "] finish.");
				success = true;
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " task[" + value + "] Interrupted");
			}
			return value;
		}

		public boolean isSuccess() {
			return success;
		}
	}

	public static class MyRunnableTask implements Runnable {
		private final int value;

		public MyRunnableTask(int value) {
			this.value = value;
		}

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " will do task[" + value + "]");
				TimeUnit.SECONDS.sleep(value * 2 + 10);
				System.out.println(Thread.currentThread().getName() + " do task[" + value + "] finish.");
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " task[" + value + "] Interrupted");
			}
		}
	}
}
