package com.ixan.boot.test.thread;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/4 下午8:46
 * @description 测试线程等待的join方法
 */
public class ThreadJoinMethodTest {
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

		//启动子线程
		threadOne.start();
		threadTwo.start();

		System.out.println("wait all child thread over");

		//等待子线程执行完毕，返回
		threadOne.join();
		threadTwo.join();

		System.out.println("all child thread over");

	}
}
