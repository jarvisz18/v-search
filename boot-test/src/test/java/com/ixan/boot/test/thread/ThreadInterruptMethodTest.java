package com.ixan.boot.test.thread;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/4 下午8:55
 * @description 测试interrupt方法
 */
public class ThreadInterruptMethodTest {
	public static void main(String[] args) {
		Thread threadOne = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("threadOne begin run!");
				for (; ; ) {

				}
			}
		});

		//获取主线程
		final Thread mainThread = Thread.currentThread();

		//线程Two
		Thread threadTwo = new Thread(new Runnable() {
			@Override
			public void run() {
				//休眠1s
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//中断主线程
				mainThread.interrupt();
			}
		});

		//启动子线程
		threadOne.start();
		threadTwo.start();

		//等待线程1执行结束
		try {
			threadOne.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
