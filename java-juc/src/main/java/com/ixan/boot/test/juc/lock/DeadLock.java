package com.ixan.boot.test.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/6 下午5:01
 * @description 死锁
 */
public class DeadLock {
	private static final Object objectA = new Object();
	private static final Object objectB = new Object();

	public static void main(String[] args) {
		new Thread(() -> {
			synchronized (objectA) {
				System.out.println("线程:" + Thread.currentThread().getName() + "成功获取锁A,试图获取锁B");
				try {
					TimeUnit.SECONDS.sleep(1L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (objectB) {
					System.out.println("线程:" + Thread.currentThread().getName() + "成功获取锁B");
				}
			}
		}, "thread-1").start();

		new Thread(() -> {
			synchronized (objectB) {
				System.out.println("线程:" + Thread.currentThread().getName() + "成功获取锁B,试图获取锁A");
				try {
					TimeUnit.SECONDS.sleep(1L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (objectA) {
					System.out.println("线程:" + Thread.currentThread().getName() + "成功获取锁A");
				}
			}
		}, "thread-2").start();
	}
}
