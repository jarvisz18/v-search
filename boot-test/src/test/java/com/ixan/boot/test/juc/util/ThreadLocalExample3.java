package com.ixan.boot.test.juc.util;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/16 下午8:00
 * @description 子线程访问父线程中的本地变量
 */
public class ThreadLocalExample3 {
	private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

	public static void main(String[] args) {
		//在父线程中设置值
		threadLocal.set(ThreadLocalExample3.class.getSimpleName());

		//在子线程中获取值
		new Thread(() -> {
			System.out.println("子线程:" + Thread.currentThread().getName() + "获取值:" + threadLocal.get());
		}).start();

		//在主线程中获取值
		System.out.println("主线程获取值:" + threadLocal.get());
	}
}
