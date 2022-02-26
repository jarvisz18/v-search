package com.ixan.boot.test.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/1/24 16:24
 * @description 基于AtomicBoolean的显式锁
 * @version
 */
public class AtomicBooleanTryLock {
	private final AtomicBoolean ab = new AtomicBoolean();

	private final ThreadLocal<Boolean> threadLocal = ThreadLocal.withInitial(() -> false);

	public boolean tryLock() {
		boolean result = ab.compareAndSet(false, true);
		if (result) {
			threadLocal.set(true);
		}
		return result;
	}

	public boolean release() {
		if (threadLocal.get()) {
			threadLocal.set(false);
			return ab.compareAndSet(true, false);
		} else {
			return false;
		}
	}
}
