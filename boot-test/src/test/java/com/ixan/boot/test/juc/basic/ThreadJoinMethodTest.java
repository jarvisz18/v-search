package com.ixan.boot.test.juc.basic;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/4 下午8:46
 * @description 测试线程等待的join方法
 */
public class ThreadJoinMethodTest {
	/**
	 * 经典题目:
	 * 现有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，
	 * T3在T2执行完后执行
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread threadOne = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("child ThreadOne over");
			}
		});

		Thread threadTwo = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("child ThreadTwo over");
			}
		});

		Thread threadThree = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("child ThreadThree over");
			}
		});

		//启动子线程
		threadOne.start();
		threadTwo.start();
		threadThree.start();

		System.out.println("wait all child thread over");

		//等待子线程执行完毕，返回
		threadOne.join();
		threadTwo.join();
		threadThree.join();

		System.out.println("all child thread over");

	}
}
