package com.ixan.boot.test.juc.queue;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/14 下午4:09
 * @description wait all thread complete
 */
public class WaitAllThreadComplete {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		ExecutorService executorService = new ThreadPoolExecutor(5, 10, 60, TimeUnit.MINUTES,
				new ArrayBlockingQueue<>(10),
				new ThreadFactoryBuilder().setNameFormat("test-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
		List<Integer> list = new ArrayList<>();
		LinkedBlockingQueue<List<Integer>> deque = new LinkedBlockingQueue<>(10);
		for (int i = 0; i < 1000080; i++) {
			list.add(i);
			if (list.size() == 100) {
				deque.put(new ArrayList<>(list));
				list.clear();
			}
			while (!deque.isEmpty()) {
				List<Integer> take = deque.take();
				executorService.submit(() -> {
					System.out.println("正在执行任务task,集合大小" + take.size());
				});
			}
		}
		System.out.println("最后的集合大小:" + list.size());
		executorService.submit(() -> {
			System.out.println("正在执行任务task,集合大小" + list.size());
		});

		executorService.shutdown();
		while (true) {
			if (executorService.isTerminated()) {
				long end = System.currentTimeMillis();
				System.out.println("执行任务完毕,耗时:" + (end - start) + "ms");
				break;
			}
		}
	}
}
