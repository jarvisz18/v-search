package com.ixan.boot.test.juc.collections.blocking;

import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 15:44
 * @description 延迟队列 测试
 * @version 1.0
 */
public class DelayQueueExampleTest {

	@Test
	public void testDelayQueueTake() throws InterruptedException {
		DelayQueue<MyDelayTask> delayQueue = DelayQueueExample.create();
		delayQueue.add(MyDelayTask.of("data1", 800L));
		delayQueue.add(MyDelayTask.of("data2", 1000L));
		delayQueue.add(MyDelayTask.of("data3", 2000L));
		delayQueue.add(MyDelayTask.of("data4", 1200L));
		long start = System.currentTimeMillis();
		MyDelayTask take = delayQueue.take();
		System.out.println((System.currentTimeMillis() - start) + ":" + take.data);
	}

	@Test
	public void testDelayQueueAdd() throws InterruptedException {
		DelayQueue<MyDelayTask> delayQueue = DelayQueueExample.create();
		delayQueue.add(MyDelayTask.of("data1", 800L));
		delayQueue.add(MyDelayTask.of("data2", 1000L));
		delayQueue.add(MyDelayTask.of("data3", 2000L));
		delayQueue.add(MyDelayTask.of("data4", 1200L));
		long start = System.currentTimeMillis();
		while (!delayQueue.isEmpty()) {
			MyDelayTask take = delayQueue.take();
			System.out.println((System.currentTimeMillis() - start) + ":" + take.data);
		}

		System.out.println(System.currentTimeMillis() - start);
	}

	public static class MyDelayTask implements Delayed {
		/**
		 * 数据
		 */
		private final String data;
		/**
		 * 到期时间
		 */
		private final long deadline;

		public MyDelayTask(String data, long deadline) {
			this.data = data;
			//到期时间=当前时间+延迟时间 (单位:ms)
			this.deadline = System.currentTimeMillis() + deadline;
		}

		public static MyDelayTask of(String data, long deadline) {
			return new MyDelayTask(data, deadline);
		}

		/**
		 * 判断延迟任务是否到期
		 */
		@Override
		public long getDelay(TimeUnit unit) {
			long diff = this.deadline - System.currentTimeMillis();
			return unit.convert(diff, TimeUnit.MILLISECONDS);
		}

		@Override
		public int compareTo(Delayed o) {
			MyDelayTask delayTask = (MyDelayTask) o;
			//根据过期时间升序出队,先过期的先出队
			return Long.compare(this.deadline, delayTask.deadline);
		}
	}
}
