package com.ixan.boot.test.juc.collections.blocking;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 10:41
 * @description LinkedBlockingQueue 案例
 * @version 1.0
 */
public class LinkedBlockingQueueExample {
	/**
	 * 无界队列,默认大小为{@link Integer#MAX_VALUE}
	 */
	public <T> LinkedBlockingQueue<T> create() {
		return new LinkedBlockingQueue<T>();
	}

	/**
	 * 指定大小
	 */
	public <T> LinkedBlockingQueue<T> create(int capacity) {
		return new LinkedBlockingQueue<T>(capacity);
	}
}
