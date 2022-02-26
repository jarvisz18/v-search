package com.ixan.boot.test.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/6 下午2:48
 * @description 使用Lock的Condition实现线程通信
 */
public class NotifyLockTask {
	private int num = 0;
	private final ReentrantLock reentrantLock = new ReentrantLock();
	private final Condition condition = reentrantLock.newCondition();

	private void incr() throws InterruptedException {
		reentrantLock.lock();
		try {
			while (num != 0) {
				condition.await();
			}
			num++;
			System.out.println(Thread.currentThread().getName() + " num:" + num);
			condition.signalAll();
		} finally {
			reentrantLock.unlock();
		}
	}

	private void decr() throws InterruptedException {
		reentrantLock.lock();
		try {
			while (num != 1) {
				condition.await();
			}
			num--;
			System.out.println(Thread.currentThread().getName() + " num:" + num);
			condition.signalAll();
		} finally {
			reentrantLock.unlock();
		}
	}

	public static void main(String[] args) {
		NotifyLockTask notifyLockTask = new NotifyLockTask();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					notifyLockTask.incr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-1").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					notifyLockTask.decr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-2").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					notifyLockTask.incr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-3").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					notifyLockTask.decr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-4").start();
	}
}
