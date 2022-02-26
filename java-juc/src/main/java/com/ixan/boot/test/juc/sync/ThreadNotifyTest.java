package com.ixan.boot.test.juc.sync;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/4 下午8:04
 * @description 线程唤醒测试
 */
public class ThreadNotifyTest {
	//创建资源
	private static final Object resourceA = new Object();

	public static void main(String[] args) throws InterruptedException {
		//创建线程
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				//获取resourceA共享资源的监视器锁
				synchronized (resourceA) {
					System.out.println("threadA get resourceA lock");

					try {
						System.out.println("threadA begin wait");
						resourceA.wait();
						System.out.println("threadA end wait");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		//创建线程
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				//获取resourceA共享资源的监视器锁
				synchronized (resourceA) {
					System.out.println("threadB get resourceA lock");

					try {
						System.out.println("threadB begin wait");
						resourceA.wait();
						System.out.println("threadB end wait");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		//创建线程
		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (resourceA) {
					System.out.println("threadC begin notify");
					//唤醒挂起的线程
					//resourceA.notify();
					resourceA.notifyAll();
				}
			}
		});

		//启动线程
		threadA.start();
		threadB.start();

		Thread.sleep(1000L);
		threadC.start();

		//等待线程结束
		threadA.join();
		threadB.join();
		threadC.join();

		System.out.println("main over");

	}
}
