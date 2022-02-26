package com.ixan.boot.test.juc.collections.blocking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 10:43
 * @description LinkedBlockingQueueExample 单元测试
 * @version 1.0
 */
public class LinkedBlockingQueueExampleTest {
	private LinkedBlockingQueueExample blockingQueueExample;

	@Before
	public void before() {
		blockingQueueExample = new LinkedBlockingQueueExample();
	}

	@After
	public void after() {
		blockingQueueExample = null;
	}

	/**
	 * Test {@link LinkedBlockingQueue#element()}
	 * Test {@link LinkedBlockingQueue#peek()}
	 * Test {@link LinkedBlockingQueue#poll()}
	 * Test {@link LinkedBlockingQueue#remove()}
	 * Test {@link LinkedBlockingQueue#take()}
	 */
	@Test
	public void testLinkedBlockingQueueRemove() throws InterruptedException {
		LinkedBlockingQueue<String> blockingQueue = blockingQueueExample.create(3);
		Assert.assertTrue(blockingQueue.add("data1"));
		Assert.assertTrue(blockingQueue.add("data2"));
		Assert.assertTrue(blockingQueue.add("data3"));

		Assert.assertSame("data1", blockingQueue.element());
		Assert.assertSame("data1", blockingQueue.element());

		Assert.assertSame("data1", blockingQueue.peek());
		Assert.assertSame("data1", blockingQueue.peek());

		Assert.assertSame("data1", blockingQueue.poll());
		Assert.assertSame("data2", blockingQueue.remove());
		//调用take方法会阻塞
		Assert.assertSame("data3", blockingQueue.take());
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.schedule(() -> blockingQueue.add("data4"), 1L, TimeUnit.SECONDS);
		scheduledExecutorService.shutdown();
		Assert.assertSame("data4", blockingQueue.take());
	}

	/**
	 * Test {@link LinkedBlockingQueue#add(Object)}
	 * Test {@link LinkedBlockingQueue#add(Object)}
	 * Test {@link LinkedBlockingQueue#put(Object)}
	 */
	@Test
	public void testLinkedBlockingQueueAdd() throws InterruptedException {
		LinkedBlockingQueue<String> blockingQueue = blockingQueueExample.create(3);
		Assert.assertTrue(blockingQueue.add("data1"));
		Assert.assertTrue(blockingQueue.add("data2"));
		Assert.assertTrue(blockingQueue.add("data3"));
		blockingQueue.clear();

		Assert.assertTrue(blockingQueue.offer("data1"));
		Assert.assertTrue(blockingQueue.offer("data2"));
		Assert.assertTrue(blockingQueue.offer("data3"));
		Assert.assertFalse(blockingQueue.offer("data4"));
		blockingQueue.clear();

		blockingQueue.put("data1");
		blockingQueue.put("data2");
		blockingQueue.put("data3");
	}
}
