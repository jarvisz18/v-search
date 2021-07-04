package com.ixan.boot.test.thread;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/4 下午3:22
 * @description 继承Thread类的线程创建方式
 */
public class ThreadTest {
	public static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("i am a child Thread");
		}
	}

	public static void main(String[] args) {
		//创建线程
		MyThread myThread = new MyThread();
		//启动线程
		myThread.start();
	}
}
