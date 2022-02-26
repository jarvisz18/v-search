package com.ixan.boot.test.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/6 下午3:17
 * @description 线程定制化通知
 */
public class NotifyCustomTask {
	private int flag = 1;
	private ReentrantLock reentrantLock = new ReentrantLock();
	private Condition condition1 = reentrantLock.newCondition();
	private Condition condition2 = reentrantLock.newCondition();
	private Condition condition3 = reentrantLock.newCondition();

	private void print5(int loop) throws InterruptedException {
		reentrantLock.lock();
		try {
			while (flag != 1) {
				condition1.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + i + ",打印第:" + loop + "轮");
			}
			flag = 2;
			condition2.signal();
		} finally {
			reentrantLock.unlock();
		}
	}

	private void print10(int loop) throws InterruptedException {
		reentrantLock.lock();
		try {
			while (flag != 2) {
				condition2.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + i + ",打印第:" + loop + "轮");
			}
			flag = 3;
			condition3.signal();
		} finally {
			reentrantLock.unlock();
		}
	}

	private void print15(int loop) throws InterruptedException {
		reentrantLock.lock();
		try {
			while (flag != 3) {
				condition3.await();
			}
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName() + ":" + i + ",打印第:" + loop + "轮");
			}
			flag = 1;
			condition1.signal();
		} finally {
			reentrantLock.unlock();
		}
	}

	public static void main(String[] args) {
		NotifyCustomTask customTask = new NotifyCustomTask();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					customTask.print5(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-1").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					customTask.print10(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-2").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					customTask.print15(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-3").start();
	}
}
