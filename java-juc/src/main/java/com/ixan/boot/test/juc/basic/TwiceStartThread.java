package com.ixan.boot.test.juc.basic;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/3/16 下午9:34
 * @description 两次strat线程测试
 */
public class TwiceStartThread {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println("i am running!");
		});

		//多次调用start方法，会抛出java.lang.IllegalThreadStateException
		thread.start();
		thread.start();
	}
}
