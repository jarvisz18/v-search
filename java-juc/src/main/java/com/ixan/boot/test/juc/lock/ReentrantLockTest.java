package com.ixan.boot.test.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/18 下午8:41
 * @description ReentrantLock test
 */
public class ReentrantLockTest {
	private static ReentrantLock reentrantLock = new ReentrantLock();

	public static void main(String[] args) {
		Thread threadA = new Thread(() -> {
			try {
				reentrantLock.lock();
				System.out.println(Thread.currentThread().getName() + ":执行biz");
				try {
					TimeUnit.SECONDS.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} finally {
				reentrantLock.unlock();
			}
		}, "thread-A");

		Thread threadB = new Thread(() -> {
			try {
				reentrantLock.lock();
				System.out.println(Thread.currentThread().getName() + ":执行biz");
			} finally {
				reentrantLock.unlock();
			}
		}, "thread-B");

		Thread threadC = new Thread(() -> {
			try {
				reentrantLock.lock();
				System.out.println(Thread.currentThread().getName() + ":执行biz");
			} finally {
				reentrantLock.unlock();
			}
		}, "thread-C");

		threadA.start();
		threadB.start();
		threadC.start();
	}
}
