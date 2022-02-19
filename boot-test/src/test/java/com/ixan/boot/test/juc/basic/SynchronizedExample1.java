package com.ixan.boot.test.juc.basic;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/18 下午9:48
 * @description 关于Synchronized的例子
 */
public class SynchronizedExample1 {

	public static void main(String[] args) {
		final SynchronizedExample1 test = new SynchronizedExample1();
		new Thread(test::method1).start();
		new Thread(test::method2).start();
	}

	private void method1() {
		System.out.println("method1 start");
		try {
			System.out.println("method1 execute");
			TimeUnit.SECONDS.sleep(3L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("method1 end");
	}

	private void method2() {
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
