package com.ixan.boot.test.juc.basic;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/3/15 下午9:41
 * @description volatile关键字测试
 */
public class VolatileTest {


	public static void main(String[] args) {
		final VolatileTask vt = new VolatileTask();
		Thread thread1 = new Thread(vt);
		Thread thread2 = new Thread(() -> {
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			vt.sign = true;
			System.out.println("vt.sign = true 通知 while (!sign) 结束!");
		});

		thread1.start();
		thread2.start();

	}

	public static class VolatileTask implements Runnable {
		volatile boolean sign = false;

		@Override
		public void run() {
			while (!sign) {

			}
			System.out.println("你坏");
		}
	}

	public static class NotVolatileTask implements Runnable {
		boolean sign = false;

		@Override
		public void run() {
			while (!sign) {

			}
			System.out.println("你坏");
		}
	}
}
