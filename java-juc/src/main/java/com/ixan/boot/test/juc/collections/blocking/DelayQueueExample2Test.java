package com.ixan.boot.test.juc.collections.blocking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/11 10:36
 * @description DelayQueueExample2Test
 * @version 1.0
 */
public class DelayQueueExample2Test {
	private static final Logger LOGGER = LoggerFactory.getLogger(DelayQueueExample2Test.class);

	public static void main(String[] args) throws InterruptedException {
		final DelayQueue<DelayedEvent> delayQueue = DelayQueueExample.create();
		final long timeFirst = System.currentTimeMillis() + 10_000L;
		final long timeSecond = System.currentTimeMillis() + 12_000L;
		final long timeThird = System.currentTimeMillis() + 8_000L;
		delayQueue.offer(new DelayedEvent(timeFirst, "1"));
		delayQueue.offer(new DelayedEvent(timeSecond, "2"));
		delayQueue.offer(new DelayedEvent(timeThird, "3"));
		LOGGER.info("Done");
		LOGGER.info(delayQueue.take().msg);
		LOGGER.info(delayQueue.take().msg);
		LOGGER.info(delayQueue.take().msg);
	}

	private static class DelayedEvent implements Delayed {
		private final long startTime;
		private final String msg;

		public DelayedEvent(long startTime, String msg) {
			this.startTime = startTime;
			this.msg = msg;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			long diff = startTime - System.currentTimeMillis();
			return unit.convert(diff, TimeUnit.MILLISECONDS);
		}

		@Override
		public int compareTo(Delayed o) {
			return (int) (this.startTime - ((DelayedEvent) o).startTime);
		}
	}
}
