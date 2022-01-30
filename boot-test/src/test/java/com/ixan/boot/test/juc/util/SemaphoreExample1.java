package com.ixan.boot.test.juc.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/30 下午4:26
 * @description Semapaore Test
 * 基于Semaphore的锁
 */
public class SemaphoreExample1 {

	public static void main(String[] args) {
		final SemaphoreLock lock = new SemaphoreLock();
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + " is running!");
				try {
					lock.lock();
					System.out.println(Thread.currentThread().getName() + " get lock #Semaphore");
					TimeUnit.SECONDS.sleep(5L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
				System.out.println(Thread.currentThread().getName() + " release lock #Semaphore");
			}, "thread-" + i).start();
		}
	}

	private static class SemaphoreLock {
		private final Semaphore semaphore = new Semaphore(1);

		public void lock() throws InterruptedException {
			semaphore.acquire();
		}

		public void unlock() {
			semaphore.release();
		}
	}

}
