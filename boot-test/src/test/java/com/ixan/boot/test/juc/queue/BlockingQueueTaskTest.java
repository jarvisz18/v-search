package com.ixan.boot.test.juc.queue;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/2 下午3:32
 * @description BlockingQueue task Test
 */
public class BlockingQueueTaskTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BlockingQueueTaskTest.class);
	private static final int MAX_QUEUE_SIZE = 10;

	public static void main(String[] args) {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);
		final int NCPUS = Runtime.getRuntime().availableProcessors();
		LOGGER.info("Runtime.getRuntime().availableProcessors() :[{}]", NCPUS);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(NCPUS, NCPUS, 2000L, TimeUnit.MICROSECONDS,
				new ArrayBlockingQueue<>(100),
				new ThreadFactoryBuilder().setNameFormat("doTask-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());

		List<Future<Integer>> futureList = new ArrayList<>();
		try {
			Future<Integer> submit = threadPoolExecutor.submit(new ConsumerTask(queue));
			futureList.add(submit);
			//当前线程是生产者
			for (int i = 0; i <= 980; i++) {
				queue.put("number:" + i);
			}
			//计算结果
			int actualCount = 0;
			for (Future<Integer> future : futureList) {
				actualCount += future.get();
			}
			LOGGER.info("实际结果:[{}]", actualCount);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			//关闭线程池
			threadPoolExecutor.shutdown();
		}
	}

	private static class ConsumerTask implements Callable<Integer> {
		private final BlockingQueue<String> blockingQueue;

		ConsumerTask(BlockingQueue<String> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public Integer call() throws Exception {
			int i = 0;
			while (!blockingQueue.isEmpty()) {
				String take = blockingQueue.take();
				LOGGER.info("get element :{}", take);
				i++;
			}
			return i;
		}
	}
}
