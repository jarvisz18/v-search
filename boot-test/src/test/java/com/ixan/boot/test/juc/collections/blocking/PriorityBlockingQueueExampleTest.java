package com.ixan.boot.test.juc.collections.blocking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/9 19:11
 * @description 优先级的阻塞队列
 * @version 1.0
 */
public class PriorityBlockingQueueExampleTest {
	private PriorityBlockingQueueExample blockingQueueExample;

	@Before
	public void before() {
		blockingQueueExample = new PriorityBlockingQueueExample();
	}

	@After
	public void after() {
		blockingQueueExample = null;
	}

	/**
	 * {@link PriorityBlockingQueue#add(Object)}
	 * {@link PriorityBlockingQueue#offer(Object)}
	 * {@link PriorityBlockingQueue#put(Object)}
	 * all of same
	 * 添加元素超过初始化容量时扩容{@link Integer#MAX_VALUE}
	 *
	 * {@link PriorityBlockingQueue#element()}
	 * {@link PriorityBlockingQueue#peek()}
	 * 取出第一个元素,队列不弹出元素
	 *
	 * {@link PriorityBlockingQueue#take()}
	 * 方法阻塞、且弹出元素
	 */
	@Test
	public void testPriorityBlockingQueue() throws InterruptedException {
		PriorityBlockingQueue<String> queue = blockingQueueExample.create(3);
		Assert.assertTrue(queue.add("Hello1"));
		Assert.assertTrue(queue.add("Hello2"));
		Assert.assertTrue(queue.offer("Hello3"));
		queue.put("Hello4");
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());

		//测试取出元素
		Assert.assertSame("Hello1", queue.element());
		Assert.assertSame("Hello1", queue.peek());
		Assert.assertEquals(4, queue.size());
		Assert.assertSame("Hello1", queue.take());
		Assert.assertEquals(3, queue.size());
	}
}
