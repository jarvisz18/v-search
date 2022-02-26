package com.ixan.boot.test.juc.basic;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/18 下午9:56
 * @description synchronized example
 */
public class SynchronizedExample2 {
	public static void main(String[] args) {
		final SynchronizedExample2 test = new SynchronizedExample2();
		new Thread(test::method1).start();
		new Thread(test::method2).start();
	}

	private synchronized void method1() {
		System.out.println("method1 start");
		try {
			System.out.println("method1 execute");
			TimeUnit.SECONDS.sleep(3L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method1 end");
	}

	private synchronized void method2() {
		System.out.println("method2 start");
		try {
			System.out.println("method2 execute");
			TimeUnit.SECONDS.sleep(1L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method2 end");
	}
}
