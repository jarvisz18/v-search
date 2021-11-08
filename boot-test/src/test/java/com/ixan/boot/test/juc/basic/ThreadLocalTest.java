package com.ixan.boot.test.juc.basic;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/11/8 下午8:56
 * @description 线程本地变量测试
 */
public class ThreadLocalTest {
	private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	private static class ThreadLocalTask implements Runnable {
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			threadLocal.set(name);
			System.out.println(name + " before threadLocal.remove():value is " + threadLocal.get());
			print(name);
			//调用remove时
			System.out.println(name + " after threadLocal.remove():value is " + threadLocal.get());
		}

	}

	private static void print(String name) {
		//打印本地变量
		System.out.println("name:" + name + "-->value:" + threadLocal.get());
		threadLocal.remove();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread threadOne = new Thread(new ThreadLocalTask(), "ThreadLocalTask-1");

		Thread theadTwo = new Thread(new ThreadLocalTask(), "ThreadLocalTask-2");
		threadOne.start();
		theadTwo.start();

		threadOne.join();
		theadTwo.join();
		System.out.println("main thread over");
	}
}
