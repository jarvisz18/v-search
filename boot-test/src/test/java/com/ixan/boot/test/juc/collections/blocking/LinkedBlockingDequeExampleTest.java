package com.ixan.boot.test.juc.collections.blocking;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 16:13
 * @description 双端队列
 * @version 1.0
 */
public class LinkedBlockingDequeExampleTest {

	@Test
	public void testLinkedBlockingDequeRemove() throws InterruptedException {
		LinkedBlockingDeque<String> blockingDeque = LinkedBlockingDequeExample.create();
		blockingDeque.addFirst("Java");
		blockingDeque.addFirst("Python");
		Assert.assertEquals("Python", blockingDeque.take());
		Assert.assertEquals("Java", blockingDeque.take());

		Assert.assertEquals(0, blockingDeque.size());
	}

	@Test
	public void testLinkedBlockingDequeAdd() throws InterruptedException {
		LinkedBlockingDeque<String> blockingDeque = LinkedBlockingDequeExample.create();
		blockingDeque.addFirst("Java");
		blockingDeque.addFirst("Python");
		Assert.assertEquals("Python", blockingDeque.takeFirst());
		Assert.assertEquals("Java", blockingDeque.takeFirst());
	}
}
