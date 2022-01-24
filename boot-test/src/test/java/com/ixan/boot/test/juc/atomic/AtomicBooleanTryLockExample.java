package com.ixan.boot.test.juc.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/1/24 16:43
 * @description AtomicBooleanTryLock Example
 * @version 1.0
 */
public class AtomicBooleanTryLockExample {
	private final static Object VAL_OBJ = new Object();
	private static final Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		AtomicBooleanTryLock lock = new AtomicBooleanTryLock();
		final List<Object> validation = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					try {
						//尝试获取锁
						if (lock.tryLock()) {
							System.out.println(currentThread() + ": get the lock");

							if (validation.size() > 1) {
								throw new IllegalStateException("validation failed.");
							}
							validation.add(VAL_OBJ);
							TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
						} else {
							TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						if (lock.release()) {
							System.out.println(currentThread() + ": release the lock");
							validation.remove(VAL_OBJ);
						}
					}
				}
			}).start();
		}
	}
}
