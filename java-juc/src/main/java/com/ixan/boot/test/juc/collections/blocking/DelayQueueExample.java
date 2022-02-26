package com.ixan.boot.test.juc.collections.blocking;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 15:42
 * @description 延迟队列 案例
 * @version 1.0
 */
public class DelayQueueExample {
	public static <T extends Delayed> DelayQueue<T> create() {
		return new DelayQueue<>();
	}
}
