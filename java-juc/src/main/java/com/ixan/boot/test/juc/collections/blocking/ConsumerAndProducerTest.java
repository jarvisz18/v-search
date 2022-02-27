package com.ixan.boot.test.juc.collections.blocking;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/27 下午10:22
 * @description 生产者和消费者测试
 */
public class ConsumerAndProducerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerAndProducerTest.class);
	/**
	 * 毒丸对象
	 */
	private static final Message NULL_MESSAGE = new Message(null);

	public static void main(String[] args) {
		final CountDownLatch countDownLatch = new CountDownLatch(10);
		//生产者线程池
		ThreadPoolExecutor producerPoolExecutor = new ThreadPoolExecutor(10, 20, 2000L, TimeUnit.MICROSECONDS,
				new LinkedBlockingDeque<>(),
				new ThreadFactoryBuilder().setNameFormat("fetchTask-%d").build());
		//消费者线程池
		ThreadPoolExecutor consumerPoolExecutor = new ThreadPoolExecutor(10, 20, 2000L, TimeUnit.MICROSECONDS,
				new LinkedBlockingDeque<>(),
				new ThreadFactoryBuilder().setNameFormat("insertTask-%d").build());
		Map<Integer, LinkedBlockingDeque<Message>> dequeLinkedHashMap = new LinkedHashMap<>(10);

		try {
			for (int i = 0; i < 10; i++) {
				LinkedBlockingDeque<Message> linkedBlockingDeque = new LinkedBlockingDeque<>();
				producerPoolExecutor.execute(new ProducerTask(linkedBlockingDeque));
				dequeLinkedHashMap.put(i, linkedBlockingDeque);
			}

			for (int i = 0; i < 10; i++) {
				consumerPoolExecutor.execute(new ConsumerTask(dequeLinkedHashMap.get(i), countDownLatch));
			}
			//放入毒丸对象
			Set<Map.Entry<Integer, LinkedBlockingDeque<Message>>> entries = dequeLinkedHashMap.entrySet();
			for (Map.Entry<Integer, LinkedBlockingDeque<Message>> entry : entries) {
				entry.getValue().put(NULL_MESSAGE);
			}

			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			producerPoolExecutor.shutdown();
			consumerPoolExecutor.shutdown();
		}

	}

	private static class ConsumerTask implements Runnable {
		private final LinkedBlockingDeque<Message> linkedBlockingDeque;
		private final CountDownLatch countDownLatch;

		private ConsumerTask(LinkedBlockingDeque<Message> linkedBlockingDeque, CountDownLatch countDownLatch) {
			this.linkedBlockingDeque = linkedBlockingDeque;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Message message = linkedBlockingDeque.take();
					if (message == NULL_MESSAGE) {
						LOGGER.info("currentThread:[{}],is done", Thread.currentThread().getName());
						break;
					}
					LOGGER.info("currentThread:[{}],take message:[{}]", Thread.currentThread().getName(), message);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
			}
		}
	}

	private static class ProducerTask implements Runnable {
		private final LinkedBlockingDeque<Message> linkedBlockingDeque;

		private ProducerTask(LinkedBlockingDeque<Message> linkedBlockingDeque) {
			this.linkedBlockingDeque = linkedBlockingDeque;
		}

		@Override
		public void run() {
			for (int i = 0; i < 100; i++) {
				try {
					Message message = new Message(String.valueOf(System.currentTimeMillis()));
					linkedBlockingDeque.put(message);
					LOGGER.info("currentThread:[{}], put message:[{}]", Thread.currentThread().getName(), message);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Message {
		private final String message;

		private Message(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return "Message{" +
					"message='" + message + '\'' +
					'}';
		}
	}
}
