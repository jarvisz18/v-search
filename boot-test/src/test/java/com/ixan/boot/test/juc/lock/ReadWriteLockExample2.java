package com.ixan.boot.test.juc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/6 上午9:58
 * @description ReadWriteLockExample1
 * <p> 读写锁案例</p>
 */
public class ReadWriteLockExample2 {
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
	private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
	private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
	private static volatile List<Long> data = new ArrayList<>();

	/**
	 * 读写锁案例:
	 * W R NO
	 * W W NO
	 * R W NO
	 * R R YES
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(ReadWriteLockExample2::write);
		thread1.start();

		TimeUnit.SECONDS.sleep(1L);
		Thread thread2 = new Thread(ReadWriteLockExample2::read);
		thread2.start();

	}

	private static void write() {
		try {
			writeLock.lock();
			data.add(System.currentTimeMillis());
			try {
				TimeUnit.SECONDS.sleep(5L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			writeLock.unlock();
		}
	}

	private static void read() {
		try {
			readLock.lock();
			data.forEach(System.out::println);
			try {
				TimeUnit.SECONDS.sleep(5L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "===============================");
		} finally {
			readLock.unlock();
		}
	}
}
