package com.ixan.boot.test.juc.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/30 下午3:21
 * @description CyclicBarrier与CountDownLatch对比
 * CountDownLatch 等待计数器变为0
 * CyclicBarrier 各个线程等待到达某个点
 */
public class CyclicBarrierExample2 {
	private static class MyCountDownLatch extends CountDownLatch {
		private final Runnable runnable;

		MyCountDownLatch(int count, Runnable runnable) {
			super(count);
			this.runnable = runnable;
		}

		@Override
		public void countDown() {
			if (super.getCount() == 0) {
				this.runnable.run();
			}
		}
	}

	public static void main(String[] args) {
		MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, new Runnable() {
			@Override
			public void run() {
				System.out.println("all of task finish");
			}
		});

		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + " start work!");
					TimeUnit.SECONDS.sleep(10L);
					myCountDownLatch.countDown();
					System.out.println(Thread.currentThread().getName() + " finish work!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + " start work!");
					TimeUnit.SECONDS.sleep(5L);
					myCountDownLatch.countDown();
					System.out.println(Thread.currentThread().getName() + " finish work!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
