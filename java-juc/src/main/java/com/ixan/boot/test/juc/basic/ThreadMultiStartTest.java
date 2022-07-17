package com.ixan.boot.test.juc.basic;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/7/17 下午10:26
 * @description thread multi call start method
 */
public class ThreadMultiStartTest {
	/**
	 * Q:What's wrong with the following program
	 *
	 * A:It will throw an IllegalThreadStateException
	 * Exception in thread "main" run
	 * java.lang.IllegalThreadStateException
	 * 	at java.lang.Thread.start(Thread.java:708)
	 * 	at com.ixan.boot.test.juc.basic.ThreadMultiStartTest.main(ThreadMutilStartTest.java)
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println("run");
		});
		thread.start();
		thread.start();
	}
}
