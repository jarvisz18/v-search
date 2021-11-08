package com.ixan.boot.test.juc.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/8 下午7:49
 * @description 使用synchronized的生产者消费者模式
 */
public class SynQueueTaskTest {
	private static final int MAX_SIZE = 20;
	private static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(MAX_SIZE);

	private static final Random random = new Random();

	//生产者线程
	private static class ProducerTask implements Runnable {
		private static int count = 0;

		@Override
		public void run() {
			while (count < 1000) {
				synchronized (queue) {
					while (queue.size() == MAX_SIZE) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//空闲则添加元素并唤醒消费者
					String e = String.valueOf(count++);
					queue.add(e);
					System.out.println("生产者线程:" + Thread.currentThread().getName() + ",生产元素:" + e);
					queue.notifyAll();
				}
			}
		}
	}

	//消费者线程
	private static class ConsumerTask implements Runnable {
		@Override
		public void run() {
			while (true) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					//消费元素并唤醒生产者
					try {
						String take = queue.take();
						System.out.println("消费者线程:" + Thread.currentThread().getName() + ",获取到元素:" + take);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					queue.notifyAll();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread producer = new Thread(new ProducerTask(), "producer-1");
		Thread consumer = new Thread(new ConsumerTask(), "consumer-1");
		producer.start();
		consumer.start();
	}
}
