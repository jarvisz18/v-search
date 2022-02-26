package com.ixan.boot.test.juc.sync;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/4 下午7:45
 * @description 线程中断测试
 */
public class WaitNotifyinterupt {
	private static final Object object = new Object();

	public static void main(String[] args) throws InterruptedException {
		//创建线程
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("---begin---");
					//阻塞当前线程
					synchronized (object) {
						object.wait();
					}
					System.out.println("---end---");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		threadA.start();
		Thread.sleep(1000L);

		System.out.println("---begin interrupt threadA--- ");
		threadA.interrupt();
		System.out.println("---end interrupt threadA---");
	}
}
