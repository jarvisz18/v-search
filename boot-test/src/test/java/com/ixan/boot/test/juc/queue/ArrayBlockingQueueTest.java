package com.ixan.boot.test.juc.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/20 下午9:17
 * @description 测试
 */
public class ArrayBlockingQueueTest {
	private static final int MAX_QUEUE_SIZE = 1000;
	private static final int MAX_SIZE = 100;

	public static void main(String[] args) {
		ArrayBlockingQueue<Message> queue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
		for (int i = 0; i < 999; i++) {
			queue.add(new Message(i, false));
		}
		queue.add(new Message(999, true));
		List<Message> messages = new ArrayList<>(MAX_SIZE);
		try {
			while (true) {
				Message m = queue.take();
				if (m.end) {
					List<Message> list = new ArrayList<>(messages);
					doTask(list);
					messages.clear();
					break;
				}
				messages.add(m);
				if (messages.size() == MAX_SIZE) {
					List<Message> list = new ArrayList<>(messages);
					doTask(list);
					messages.clear();
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void doTask(List<Message> messages) {
		messages.forEach(System.out::println);
		System.out.println("----------------------------");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("任务队列:%s,执行任务,已处理数据%s \n", Thread.currentThread().getName(), messages.size());
	}

	private static class Message {
		private final int data;
		private final boolean end;

		public Message(int data, boolean end) {
			this.data = data;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Message{" +
					"data=" + data +
					", end=" + end +
					'}';
		}
	}
}
