package com.ixan.boot.test.thread;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/4 下午6:48
 * @description 线程等待通知
 */
public class ThreadWaitTest {
	//创建资源
	private static volatile Object resourceA = new Object();
	private static volatile Object resourceB = new Object();

	public static void main(String[] args) throws InterruptedException {
		//创建线程
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				//获取resourceA共享资源的监视器锁
				try {
					synchronized (resourceA) {
						System.out.println("threadA get resourceA lock");
						//获取resourceB共享资源的监视器锁
						synchronized (resourceB) {
							System.out.println("threadA get resourceB lock");

							//线程A阻塞，并释放获取到的resourceA的锁
							System.out.println("threadA release resourceA lock");
							resourceA.wait();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		//创建线程
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				//获取resourceA共享资源的监视器锁
				try {
					//休眠1s
					Thread.sleep(1000L);
					synchronized (resourceA) {
						System.out.println("threadB get resourceA lock");

						System.out.println("threadB try get resourceB lock...");
						//获取resourceB的共享资源的监视器锁
						synchronized (resourceB) {
							System.out.println("threadB get resourceB lock");

							//线程B并释放获取到resourceA的锁
							System.out.println("threadB release resourceB lock");
							resourceB.wait();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		//启动线程
		threadA.start();
		threadB.start();

		//等待两个线程结束
		threadA.join();
		threadB.join();
		System.out.println("main over");
	}
}
