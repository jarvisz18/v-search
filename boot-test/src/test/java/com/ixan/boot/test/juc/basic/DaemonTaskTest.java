package com.ixan.boot.test.juc.basic;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/7 下午12:47
 * @description 守护线程与普通线程的区别
 */
public class DaemonTaskTest {
	/**
	 * 用户线程：创建的普通线程
	 * 守护线程：用来服务与用户线程，不需要上层逻辑介入
	 */
	private static class DaemonTask implements Runnable {
		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < 100; i++) {
					System.out.println("daemon thread:" + i);
					try {
						TimeUnit.SECONDS.sleep(1L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new DaemonTask());
		//设置为守护线程
		thread.setDaemon(true);
		thread.start();
		//thread.setDaemon(true)必须在thread.start()之前设置，否则会抛出异常
		System.out.println("isDaemon? = " + thread.isDaemon());

		Scanner scanner = new Scanner(System.in);
		scanner.next();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("JVM Exit!");
		}));
	}

}
