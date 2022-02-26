package com.ixan.boot.test.juc.lock;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/6 上午11:41
 * @description 基于Condition的生产者消费者多对多
 */
public class ConditionExample3 {
	private static final ReentrantLock lock = new ReentrantLock(true);
	private static final Condition PRODUCER_CONDITION = lock.newCondition();
	private static final Condition CONSUMER_CONDITION = lock.newCondition();
	//装数据的容器
	private static final LinkedList<Long> DATA_POOL = new LinkedList<>();
	// 最大容量
	private static final int MAX_SIZE = 100;

	public static void main(String[] args) {
		//生产者线程
		producerBegin(10);

		//消费者线程
		consumerBegin(13);

	}

	private static void consumerBegin(int threadCount) {
		for (int i = 0; i < threadCount; i++) {
			new Thread(() -> {
				for (; ; ) {
					consumer();
				}
			}, "consumer-" + i).start();
		}
	}

	private static void producerBegin(int threadCount) {
		for (int i = 0; i < threadCount; i++) {
			new Thread(() -> {
				for (; ; ) {
					producer();
				}
			}, "producer-" + i).start();
		}
	}

	private static void consumer() {
		try {
			lock.lock();
			while (DATA_POOL.isEmpty()) {
				try {
					CONSUMER_CONDITION.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Long value = DATA_POOL.removeFirst();
			System.out.println(Thread.currentThread().getName() + " consumer :" + value);
			sleep(3);
			//唤醒生产者
			CONSUMER_CONDITION.signalAll();
		} finally {
			lock.unlock();
		}
	}

	private static void producer() {
		try {
			lock.lock();
			while (DATA_POOL.size() >= MAX_SIZE) {
				try {
					PRODUCER_CONDITION.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			long value = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + " producer :" + value);
			DATA_POOL.addLast(value);
			sleep(2);
			//唤醒消费者
			CONSUMER_CONDITION.signalAll();
		} finally {
			lock.unlock();
		}
	}

	private static void sleep(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
