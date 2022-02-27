package com.ixan.boot.test.juc.basic;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/27 下午12:06
 * @description 优雅的中止线程
 */
public class ThreadStopTest {
	private static volatile boolean flag = true;

	/**
	 * 中止线程的三种方式:
	 * 1.调用{@link Thread#stop()}方法，可能出现线程安全问题，不推荐使用
	 * 2.调用{@link Thread#interrupt()}方法，推荐使用
	 * 3.通过设置标志位打断线程 volatile
	 */

	public static void main(String[] args) throws InterruptedException {
		testStopThread();
		System.out.println("=================");
		testStopThreadInterrupt();
		System.out.println("=================");
		new FlagThread().start();
		TimeUnit.SECONDS.sleep(3L);
		flag = false;
		System.out.println("程序执行结束");
	}

	private static void testStopThreadInterrupt() throws InterruptedException {
		StopThread stopThread = new StopThread();
		stopThread.start();
		TimeUnit.SECONDS.sleep(1L);
		//中止线程
		stopThread.interrupt();
		while (stopThread.isAlive()) {
			System.out.println("stopThread isAlive");
		}
		stopThread.print();
	}

	/**
	 * 可能导致线程安全问题，不推荐使用
	 *
	 * @throws InterruptedException
	 */
	private static void testStopThread() throws InterruptedException {
		StopThread stopThread = new StopThread();
		stopThread.start();
		TimeUnit.SECONDS.sleep(1L);
		//中止线程
		stopThread.stop();
		while (stopThread.isAlive()) {
			System.out.println("stopThread isAlive");
		}
		stopThread.print();
	}

	public static class FlagThread extends Thread {
		@Override
		public void run() {
			while (flag) {
				System.out.println("正在执行线程任务.");
				try {
					TimeUnit.SECONDS.sleep(1L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class StopThread extends Thread {
		private int i = 0;
		private int j = 0;

		@Override
		public void run() {
			synchronized (this) {
				++i;
				try {
					TimeUnit.SECONDS.sleep(10L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				++j;
			}
		}

		void print() {
			System.out.println("i=" + i + ",j=" + j);
		}
	}

}
