package com.ixan.boot.test.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/6 上午11:05
 * @description  基于Condition的生产者消费者一对一
 */
public class ConditionExample1 {
	private static final ReentrantLock lock = new ReentrantLock(true);
	private static final Condition condition = lock.newCondition();

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
		try {
			lock.lock();
			while (!noUse) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//消费数据，唤醒生产者
			System.out.println("C=> " + data);
			sleep(1);
			noUse = false;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}

	private static void producer() {
		try {
			lock.lock();
			while (noUse) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//生产数据，唤醒消费者
			data++;
			System.out.println("P=> " + data);
			sleep(1);
			noUse = true;
			condition.signal();
		} finally {
			lock.unlock();
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
