package com.ixan.boot.test.juc.util;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/16 下午7:45
 * @description 线程本地变量测试2
 */
public class ThreadLocalExample2 {
	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static void main(String[] args) {
		new Thread(() -> {
			threadLocal.set("ThreadA:" + Thread.currentThread().getName());
			System.out.println("线程A本地变量中的值为:" + threadLocal.get());
			threadLocal.remove();
			System.out.println("线程A本地变量移除后值为:" + threadLocal.get());
		}).start();

		new Thread(() -> {
			threadLocal.set("threadB:" + Thread.currentThread().getName());
			System.out.println("线程B本地变量中的值为:" + threadLocal.get());

			System.out.println("线程B本地变量未移除,值为" + threadLocal.get());
		}).start();
	}
}
