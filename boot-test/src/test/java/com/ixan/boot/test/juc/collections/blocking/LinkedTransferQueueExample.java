package com.ixan.boot.test.juc.collections.blocking;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 16:18
 * @description LinkedTransferQueue 案例
 * @version 1.0
 */
public class LinkedTransferQueueExample {
	public static <T> LinkedTransferQueue<T> create() {
		return new LinkedTransferQueue<>();
	}
}
