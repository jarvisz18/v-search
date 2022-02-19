package com.ixan.boot.test.juc.basic;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/18 下午9:59
 * @description 静态方法的类同步
 */
public class SynchronizedExample3 {
	public static void main(String[] args) {
		new Thread(SynchronizedExample3::method1).start();
		new Thread(SynchronizedExample3::method2).start();
	}

	private static synchronized void method1() {
		System.out.println("method1 start");
		try {
			System.out.println("method1 execute");
			TimeUnit.SECONDS.sleep(3L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method1 end");
	}

	private static synchronized void method2() {
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
