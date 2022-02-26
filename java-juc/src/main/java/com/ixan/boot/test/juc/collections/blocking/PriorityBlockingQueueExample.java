package com.ixan.boot.test.juc.collections.blocking;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 16:24
 * @description 优先级队列
 * @version 1.0
 */
public class PriorityBlockingQueueExample {
	public <T> PriorityBlockingQueue<T> create() {
		return new PriorityBlockingQueue<>();
	}

	public <T> PriorityBlockingQueue<T> create(int capacity) {
		return new PriorityBlockingQueue<>(capacity);
	}
}
