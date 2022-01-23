package com.ixan.boot.test.juc.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/7/20 16:25
 * @description
 * @version 1.0
 */
public class QueueTakeTest {
	public static void main(String[] args) {
		ArrayBlockingQueue<Message> queue = new ArrayBlockingQueue<>(1000);
		for (int i = 0; i < 999; i++) {
			queue.add(new Message(i, false));
		}
		queue.add(new Message(1000, true));

		boolean isEnd = false;
		try {
			List<Message> list = new ArrayList<>(100);
			while (!isEnd) {
				Message take = queue.take();
				list.add(take);
				doTalk(take);
				isEnd = take.isEnd();

				if (isEnd) {
					break;
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void doTalk(Message take) {
		System.out.println(take);
	}

	public static class Message {
		private int num;
		private boolean isEnd;

		Message(int num, boolean isEnd) {
			this.num = num;
			this.isEnd = isEnd;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		boolean isEnd() {
			return isEnd;
		}

		public void setEnd(boolean end) {
			isEnd = end;
		}

		@Override
		public String toString() {
			return "Message{" +
					"num=" + num +
					", isEnd=" + isEnd +
					'}';
		}
	}
}
