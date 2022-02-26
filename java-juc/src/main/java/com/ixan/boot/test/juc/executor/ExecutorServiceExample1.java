package com.ixan.boot.test.juc.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/7 下午1:57
 * @description 线程池学习案例
 */
public class ExecutorServiceExample1 {
	private static final Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = new ThreadPoolExecutor(1, 2,
				30L, TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(100), new ThreadFactoryBuilder().setNameFormat("task-%d").build(),
				new ThreadPoolExecutor.AbortPolicy()
		);

		//提交任务
		IntStream.rangeClosed(0, 100).boxed().forEach(i -> {
			executorService.execute(() -> {
				sleep(random.nextInt(5));
				System.out.println(Thread.currentThread().getName() + " [" + i + "]");
			});
		});

		//关闭线程池
		try {
			List<Runnable> runnableList = executorService.shutdownNow();
			System.out.println(runnableList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		//最多等待30分钟
		executorService.awaitTermination(30L, TimeUnit.MINUTES);
		System.out.println("all task finish.");
	}

	private static void sleep(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
