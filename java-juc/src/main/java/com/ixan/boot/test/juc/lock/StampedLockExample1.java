package com.ixan.boot.test.juc.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/6 下午3:53
 * @description 读多写少的场景
 */
public class StampedLockExample1 {
	private static final StampedLock lock = new StampedLock();
	//装数据的集合
	private static final List<Long> DATA = new ArrayList<>();

	public static void main(String[] args) {
		final ExecutorService executorService = Executors.newFixedThreadPool(10);
		Runnable readTask = () -> {
			for (; ; ) {
				read();
			}
		};

		Runnable writeTask = () -> {
			for (; ; ) {
				write();
			}
		};
		//9个线程读 1个线程写
		executorService.submit(readTask);
		executorService.submit(readTask);
		executorService.submit(readTask);
		executorService.submit(readTask);
		executorService.submit(readTask);
		executorService.submit(readTask);
		executorService.submit(readTask);
		executorService.submit(readTask);
		executorService.submit(readTask);

		executorService.submit(writeTask);

	}

	private static void write() {
		long stamped = -1;
		try {
			stamped = lock.writeLock();
			DATA.add(System.currentTimeMillis());

			TimeUnit.MILLISECONDS.sleep(50L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlockWrite(stamped);
		}
	}

	private static void read() {
		long stamped = -1L;
		try {
			stamped = lock.readLock();
			String value = DATA.stream().map(String::valueOf).collect(Collectors.joining("-", "R", ""));
			Optional.of(value)
					.ifPresent(v -> System.out.println(Thread.currentThread().getName() + " value :" + v));
			TimeUnit.SECONDS.sleep(1L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlockRead(stamped);
		}
	}
}
