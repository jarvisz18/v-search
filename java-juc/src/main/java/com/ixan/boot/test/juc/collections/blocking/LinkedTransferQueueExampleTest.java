package com.ixan.boot.test.juc.collections.blocking;

import org.junit.Test;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 16:19
 * @description LinkedTransferQueueExample 测试
 * @version 1.0
 */
public class LinkedTransferQueueExampleTest {

	@Test
	public void test() throws InterruptedException {
		LinkedTransferQueue<String> queue = LinkedTransferQueueExample.create();
		queue.put("data1");
		queue.take();
	}

	@Test(expected = NullPointerException.class)
	public void testLinkedTransferQueuePutNull() {
		LinkedTransferQueue<String> queue = LinkedTransferQueueExample.create();
		queue.put(null);
	}
}
