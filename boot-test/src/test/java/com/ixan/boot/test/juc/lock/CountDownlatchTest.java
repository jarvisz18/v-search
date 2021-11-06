package com.ixan.boot.test.juc.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/11 下午6:41
 * @description CountDownlatch线程计数器测试
 */
public class CountDownlatchTest {
	//创建一个线程计数器实例
	private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

	@Test
	public void joinCountDownLatchTwo() throws InterruptedException {
		//创建一个线程池
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		//将线程A添加至线程池
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
				System.out.println("child threadOne over!");
			}
		});
		//将线程B添加至线程池
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
				System.out.println("child threadTwo over!");
			}
		});

		System.out.println("wait all child thread over!");

		//等待子线程执行完毕，返回
		countDownLatch.await();
		System.out.println("all child thread over!");
		executorService.shutdown();
	}

	@Test
	public void joinCountDownLatchOne() throws InterruptedException {
		Thread threadOne = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
				System.out.println("child threadOne over!");
			}
		});

		Thread threadTwo = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
				System.out.println("child threadTwo over!");
			}
		});
		//启动子线程
		threadOne.start();
		threadTwo.start();

		System.out.println("wait all child thread over!");

		countDownLatch.await();
		System.out.println("all child thread over!");
	}
}
