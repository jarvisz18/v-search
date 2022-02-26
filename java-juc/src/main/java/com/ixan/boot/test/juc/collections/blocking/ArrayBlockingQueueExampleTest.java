package com.ixan.boot.test.juc.collections.blocking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/9 17:18
 * @description ArrayBlockingQueue 案例学习
 * @version 1.0
 */
public class ArrayBlockingQueueExampleTest {
	private ArrayBlockingQueueExample blockingQueueExample;

	@Before
	public void before() {
		blockingQueueExample = new ArrayBlockingQueueExample();
	}

	@After
	public void after() {
		blockingQueueExample = null;
	}

	/**
	 * 抽取阻塞队列的元素到集合中
	 * @throws InterruptedException
	 */
	@Test
	public void testAbstractQueueDrainTo() throws InterruptedException {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		queue.put("Hello1");
		queue.put("Hello2");
		queue.put("Hello3");
		queue.put("Hello4");
		queue.put("Hello5");

		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());
		List<String> list = new ArrayList<>();
		queue.drainTo(list);
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());
		System.out.println(list.size());
	}

	@Test
	public void testAbstractQueueRemove() throws InterruptedException {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		queue.put("Hello1");
		queue.put("Hello2");
		queue.put("Hello3");
		queue.put("Hello4");
		queue.put("Hello5");

		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());
	}

	@Test
	public void testAbstractQueuePeek() throws InterruptedException {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		queue.put("Hello1");
		queue.put("Hello2");
		queue.put("Hello3");
		queue.put("Hello4");
		queue.put("Hello5");

		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());
	}

	@Test
	public void testAbstractQueuePoll() throws InterruptedException {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		queue.put("Hello1");
		queue.put("Hello2");
		queue.put("Hello3");
		queue.put("Hello4");
		queue.put("Hello5");

		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());

		queue.clear();
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.size());
		System.out.println(queue.remainingCapacity());
	}

	/**
	 * {@link ArrayBlockingQueue#put(java.lang.Object)} 队列满时阻塞
	 * @throws InterruptedException
	 */
	@Test
	public void testAbstractQueuePutFull() throws InterruptedException {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		//定时1s取出元素
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.schedule(() -> {
			try {
				queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 1L, TimeUnit.SECONDS);
		scheduledExecutorService.shutdown();
		queue.put("Hello1");
		queue.put("Hello2");
		queue.put("Hello3");
		queue.put("Hello4");
		queue.put("Hello5");
		queue.put("Hello6");
	}

	@Test
	public void testAbstractQueuePutNotFull() throws InterruptedException {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		queue.put("Hello1");
		queue.put("Hello2");
		queue.put("Hello3");
		queue.put("Hello4");
		queue.put("Hello5");
	}

	@Test
	public void testAbstractQueueOfferFull() {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		Assert.assertTrue(queue.offer("Hello1"));
		Assert.assertTrue(queue.offer("Hello2"));
		Assert.assertTrue(queue.offer("Hello3"));
		Assert.assertTrue(queue.offer("Hello4"));
		Assert.assertTrue(queue.offer("Hello5"));
		Assert.assertFalse(queue.offer("Hello6"));
	}

	@Test
	public void testAbstractQueueOfferNotFull() {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		Assert.assertTrue(queue.offer("Hello1"));
		Assert.assertTrue(queue.offer("Hello2"));
		Assert.assertTrue(queue.offer("Hello3"));
		Assert.assertTrue(queue.offer("Hello4"));
		Assert.assertTrue(queue.offer("Hello5"));
	}

	@Test(expected = IllegalStateException.class)
	public void testAbstractQueueAddFull() {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		Assert.assertTrue(queue.add("Hello1"));
		Assert.assertTrue(queue.add("Hello2"));
		Assert.assertTrue(queue.add("Hello3"));
		Assert.assertTrue(queue.add("Hello4"));
		Assert.assertTrue(queue.add("Hello5"));
		Assert.assertTrue(queue.add("Hello6"));
	}

	@Test
	public void testAbstractQueueAddNotFull() {
		ArrayBlockingQueue<String> queue = blockingQueueExample.create(5);
		Assert.assertTrue(queue.add("Hello1"));
		Assert.assertTrue(queue.add("Hello2"));
		Assert.assertTrue(queue.add("Hello3"));
		Assert.assertTrue(queue.add("Hello4"));
		Assert.assertTrue(queue.add("Hello5"));
	}
}
