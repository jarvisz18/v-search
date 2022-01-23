package com.ixan.boot.test.juc.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/22 下午12:59
 * @description 自定义锁
 */
public class CompareAndSetLock {
	private final AtomicInteger value = new AtomicInteger(0);
	private Thread lockThread;

	void tryLock() throws GetLockException {
		if (value.compareAndSet(0, 1)) {
			System.out.println(Thread.currentThread().getName() + " get the lock");
			lockThread = Thread.currentThread();
		} else {
			throw new GetLockException("get lock fail");
		}
	}

	void unlock() {
		if (value.get() == 0) {
			return;
		}
		if (lockThread == Thread.currentThread()) {
			boolean flag = value.compareAndSet(1, 0);
			if (flag) {
				System.out.println(Thread.currentThread().getName() + " unlock success");
			}
		}

	}
}
