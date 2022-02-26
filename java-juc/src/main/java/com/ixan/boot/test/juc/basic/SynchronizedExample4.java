package com.ixan.boot.test.juc.basic;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/18 下午10:07
 * @description 代码块同步
 */
public class SynchronizedExample4 {
	public static void main(String[] args) {
		final SynchronizedExample4 test = new SynchronizedExample4();
		new Thread(test::method1).start();
		new Thread(test::method2).start();
	}

	private synchronized void method1() {
		System.out.println("method1 start");
		try {
			synchronized (this) {
				System.out.println("method1 execute");
				TimeUnit.SECONDS.sleep(3L);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method1 end");
	}

	private synchronized void method2() {
		System.out.println("method2 start");
		try {
			synchronized (this) {
				System.out.println("method2 execute");
				TimeUnit.SECONDS.sleep(1L);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method2 end");
	}
}
