package com.ixan.boot.test.juc.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/7 下午6:33
 * @description 线程异步测试
 */
public class MyFutureTaskTest {
	private static final Random random = new Random(10);

	public static void main(String[] args) {
		//创建线程池
		ExecutorService executorService = new ThreadPoolExecutor(10, 20,
				30L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(10),
				new ThreadFactoryBuilder().setNameFormat("count-task-%d").build(),
				new ThreadPoolExecutor.CallerRunsPolicy());

		long fansCount = 0, msgCount = 0, collectCount = 0,
				followCount = 0, redBagCount = 0, couponCount = 0;
		long start = System.currentTimeMillis();
//		fansCount = fansCount();
//		msgCount = msgCount();
//		collectCount = collectCount();
//		followCount = followCount();
//		redBagCount = redBagCount();
//		couponCount = couponCount();

		try {
			Future<Long> fansCountFT = executorService.submit(MyFutureTaskTest::fansCount);
			Future<Long> msgCountFT = executorService.submit(MyFutureTaskTest::msgCount);
			Future<Long> collectCountFT = executorService.submit(MyFutureTaskTest::collectCount);
			Future<Long> followCountFT = executorService.submit(MyFutureTaskTest::followCount);
			Future<Long> redBagCountFT = executorService.submit(MyFutureTaskTest::redBagCount);
			Future<Long> couponCountFT = executorService.submit(MyFutureTaskTest::couponCount);

			//获取结果
			fansCount = fansCountFT.get();
			msgCount = msgCountFT.get();
			collectCount = collectCountFT.get();
			followCount = followCountFT.get();
			redBagCount = redBagCountFT.get();
			couponCount = couponCountFT.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("聚合查询异常");
			e.printStackTrace();
		}

		System.out.printf("fansCount:%d \n", fansCount);
		System.out.printf("msgCount:%d \n", msgCount);
		System.out.printf("collectCount:%d \n", collectCount);
		System.out.printf("followCount:%d \n", followCount);
		System.out.printf("redBagCount:%d \n", redBagCount);
		System.out.printf("couponCount:%d \n", couponCount);
		System.out.println("执行完毕,耗时:" + (System.currentTimeMillis() - start) + "ms");
		//关闭资源
		executorService.shutdown();
	}

	private static long couponCount() {
		int millis = random.nextInt(1000);
		sleep(millis);
		return millis;
	}

	private static long redBagCount() {
		int millis = random.nextInt(1000);
		sleep(millis);
		return millis;
	}

	private static long followCount() {
		int millis = random.nextInt(1000);
		sleep(millis);
		return millis;
	}

	/**
	 * 调用Thread.sleep()后，线程状态从RUNNABLE转换到哪个?
	 * RUNNABLE -> TIMED_WAITING
	 */
	private static void sleep(int millis) {
		try {
			TimeUnit.MILLISECONDS.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static long collectCount() {
		int millis = random.nextInt(1000);
		sleep(millis);
		return millis;
	}

	private static long msgCount() {
		int millis = random.nextInt(1000);
		sleep(millis);
		return millis;
	}

	private static long fansCount() {
		int millis = random.nextInt(1000);
		sleep(millis);
		return millis;
	}
}
