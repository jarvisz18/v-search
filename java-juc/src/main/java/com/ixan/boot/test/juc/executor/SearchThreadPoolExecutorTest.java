package com.ixan.boot.test.juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/19 21:30
 * @description Test a custom singleton thread pool
 * @version 1.0
 */
public class SearchThreadPoolExecutorTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchThreadPoolExecutorTest.class);
	/**
	 * total number of tasks
	 */
	private static final int MAX_TASK_SIZE = 167;
	/**
	 * The number of tasks executed per batch
	 */
	private static final int BATCH_SIZE = 10;

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor mySearchThreadPoolExecutor = SearchThreadPoolExecutor.getMySearchThreadPoolExecutor();
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		//init data
		List<String> list = Collections.synchronizedList(new ArrayList<>());
		for (int i = 0; i < MAX_TASK_SIZE; i++) {
			list.add(String.valueOf(i));
		}
		List<String> targetList = Collections.synchronizedList(new ArrayList<>());
		LinkedList<String> copyOfList = new LinkedList<>(list);
		List<String> tempList = new ArrayList<>(BATCH_SIZE);
		int temp = 0;
		//Monitor queue information
		scheduledExecutorService.schedule(() -> {
			LOGGER.info("task queue size:" + mySearchThreadPoolExecutor.getQueue().remainingCapacity());
		}, 100L, TimeUnit.MILLISECONDS);
		while (copyOfList.size() != 0) {
			String first = copyOfList.removeFirst();
			tempList.add(first);
			temp++;

			if (temp % BATCH_SIZE == 0) {
				LOGGER.info("Accumulated [{}] tasks", temp);
				CountDownLatch countDownLatch = new CountDownLatch(tempList.size());
				for (int i = 0; i < tempList.size(); i++) {
					mySearchThreadPoolExecutor.submit(new SearchTask(i, tempList.get(i), targetList, countDownLatch));
				}
				countDownLatch.await();
				tempList.clear();
			}
		}

		//execute the remainder
		if (tempList.size() > 0) {
			LOGGER.info("Execute remaining [{}] tasks", tempList.size());
			CountDownLatch countDownLatch = new CountDownLatch(tempList.size());
			for (int i = 0; i < tempList.size(); i++) {
				mySearchThreadPoolExecutor.submit(new SearchTask(i, tempList.get(i), targetList, countDownLatch));
			}
			countDownLatch.await();
		}

		mySearchThreadPoolExecutor.shutdown();
		scheduledExecutorService.shutdown();

		LOGGER.info("Results of :{}", targetList.size());

	}

	public static class SearchTask implements Runnable {
		private static final Logger LOGGER = LoggerFactory.getLogger(SearchTask.class);
		private final int taskNum;
		private final String value;
		private final List<String> targetList;
		private final CountDownLatch countDownLatch;

		public SearchTask(int taskNum, String value, List<String> targetList, CountDownLatch countDownLatch) {
			this.taskNum = taskNum;
			this.value = value;
			this.targetList = targetList;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			try {
				LOGGER.info("The current thread [{}] executes task [{}], task value [{}]", Thread.currentThread().getName(), taskNum, value);
				//Time-consuming simulation tasks
				try {
					TimeUnit.MILLISECONDS.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LOGGER.info("The current thread [{}] has finished executing the [{}] task!", Thread.currentThread().getName(), taskNum);
				targetList.add(value);
			} finally {
				countDownLatch.countDown();
			}
		}
	}
}
