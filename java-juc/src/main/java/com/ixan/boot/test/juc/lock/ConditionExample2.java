package com.ixan.boot.test.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/6 上午11:41
 * @description 基于synchronized的生产者消费者一对一
 */
public class ConditionExample2 {
	private static final Object lock = new Object();

	private static int data = 0;
	/**
	 * 数据是否被使用
	 */
	private static volatile boolean noUse = true;

	public static void main(String[] args) {
		//生产者线程
		new Thread(() -> {
			for (; ; ) {
				producer();
			}
		}).start();

		//消费者线程
		new Thread(() -> {
			for (; ; ) {
				consumer();
			}
		}).start();

	}

	private static void consumer() {
		synchronized (lock) {
			while (!noUse) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//消费数据，唤醒生产者
			System.out.println("C=> " + data);
			sleep(1);
			noUse = false;
			lock.notifyAll();

		}
	}

	private static void producer() {
		synchronized (lock) {
			while (noUse) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//生产数据，唤醒消费者
			data++;
			System.out.println("P=> " + data);
			sleep(1);
			noUse = true;
			lock.notifyAll();
		}
	}

	private static void sleep(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
