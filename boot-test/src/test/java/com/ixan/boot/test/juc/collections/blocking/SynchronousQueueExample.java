package com.ixan.boot.test.juc.collections.blocking;

import java.util.concurrent.SynchronousQueue;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 13:32
 * @description SynchronousQueue 案例
 * @version 1.0
 */
public class SynchronousQueueExample {
	public static <T> SynchronousQueue<T> create() {
		return new SynchronousQueue<>();
	}
}
