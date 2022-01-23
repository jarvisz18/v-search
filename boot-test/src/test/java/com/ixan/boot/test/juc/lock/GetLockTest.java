package com.ixan.boot.test.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/22 下午12:53
 * @description 抢锁测试
 */
public class GetLockTest {
	private static final CompareAndSetLock lock = new CompareAndSetLock();

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				try {
					getLock2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, "thread-" + i).start();
		}
	}

	/**
	 * synchronized 抢锁,抢不到我就等着
	 *
	 * @throws InterruptedException 异常
	 */
	private static void getLock() throws InterruptedException {
		synchronized (GetLockTest.class) {
			TimeUnit.SECONDS.sleep(600L);
		}
	}

	/**
	 * CompareAndSetLock 抢锁,抢不到我就抛异常告诉你
	 *
	 * @throws InterruptedException 异常
	 */
	private static void getLock2() throws InterruptedException {
		try {
			lock.tryLock();
			TimeUnit.SECONDS.sleep(600L);
		} finally {
			lock.unlock();
		}
	}
}
