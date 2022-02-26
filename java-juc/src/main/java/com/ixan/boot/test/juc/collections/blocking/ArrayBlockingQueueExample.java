package com.ixan.boot.test.juc.collections.blocking;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 16:28
 * @description ArrayBlockingQueueExample
 * @version 1.0
 */
public class ArrayBlockingQueueExample {
	public <T> ArrayBlockingQueue<T> create(int capacity) {
		return new ArrayBlockingQueue<>(capacity);
	}

}
