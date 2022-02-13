package com.ixan.boot.test.juc.basic;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/13 15:49
 * @description 线程状态测试
 * @version 1.0
 */
public class ThreadStateTest {
	public static void main(String[] args) {
		new Thread(new WaitingTimeTask(), "WaitingTimeTask").start();
		new Thread(new WaitingStateTask(), "WaitingStateTask").start();

		new Thread(new BlockedTask(), "BlockedTask-01").start();
		new Thread(new BlockedTask(), "BlockedTask-02").start();
	}


	private static class WaitingTimeTask implements Runnable {
		@Override
		public void run() {
			while (true) {
				sleep(200);
			}
		}
	}

	private static class WaitingStateTask implements Runnable {

		@Override
		public void run() {
			while (true) {
				synchronized (WaitingStateTask.class) {
					try {
						WaitingStateTask.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static class BlockedTask implements Runnable {

		@Override
		public void run() {
			synchronized (BlockedTask.class) {
				while (true) {
					sleep(100);
				}
			}
		}
	}

	private static void sleep(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
