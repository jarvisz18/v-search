package com.ixan.boot.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/4/3 16:47
 * @description 死锁
 * @version 1.0
 */
public class DeathLock {
	private static Lock lock1 = new ReentrantLock();
	private static Lock lock2 = new ReentrantLock();

	public static void deathLock() {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					lock1.lock();
					TimeUnit.SECONDS.sleep(1);
					lock2.lock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					lock2.lock();
					TimeUnit.SECONDS.sleep(1);
					lock1.lock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		t1.setName("mythread1");
		t2.setName("mythread2");
		t1.start();
		t2.start();
	}

	public static void main(String[] args) {
		deathLock();
	}
}
