package com.ixan.boot.test.juc.collections.blocking;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
	public void test() throws InterruptedException {
		DelayQueue<MyDelayTask> delayQueue = DelayQueueExample.create();
		delayQueue.add(MyDelayTask.of("data1", 800L));
		delayQueue.add(MyDelayTask.of("data2", 1000L));
		delayQueue.add(MyDelayTask.of("data3", 2000L));
		delayQueue.add(MyDelayTask.of("data4", 1200L));
		Instant now = Instant.now();
		delayQueue.take();
		delayQueue.take();
		delayQueue.take();
		delayQueue.take();
		Instant end = Instant.now();
		System.out.println(ChronoUnit.MILLIS.between(now, end));
	}

	public static class MyDelayTask implements Delayed {
		private final String data;
		private final long deadline;

		public MyDelayTask(String data, long deadline) {
			this.data = data;
			this.deadline = System.currentTimeMillis() + deadline;
		}

		public static MyDelayTask of(String data, long deadline) {
			return new MyDelayTask(data, deadline);
		}

		@Override
		public long getDelay(TimeUnit unit) {
			long delayTime = System.currentTimeMillis() - deadline;
			return TimeUnit.MILLISECONDS.convert(delayTime, unit);
		}

		@Override
		public int compareTo(Delayed o) {
			MyDelayTask delayTask = (MyDelayTask) o;
			if (this.deadline > delayTask.deadline) {
				return -1;
			} else if (this.deadline < delayTask.deadline) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
