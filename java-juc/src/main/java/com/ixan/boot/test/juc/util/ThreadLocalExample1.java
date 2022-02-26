package com.ixan.boot.test.juc.util;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/16 下午7:40
 * @description 线程本地变量测试
 */
public class ThreadLocalExample1 {
	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static void main(String[] args) {
		new Thread(() -> {
			threadLocal.set("ThreadA:" + Thread.currentThread().getName());
			System.out.println("线程A本地变量中的值为:" + threadLocal.get());
		}).start();

		new Thread(() -> {
			threadLocal.set("ThreadB:" + Thread.currentThread().getName());
			System.out.println("线程B本地变量中的值为:" + threadLocal.get());
		}).start();
	}
}
