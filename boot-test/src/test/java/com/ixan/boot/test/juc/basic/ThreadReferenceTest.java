package com.ixan.boot.test.juc.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/11/30 下午9:57
 * @description 换地址测试
 */
public class ThreadReferenceTest {

	private static class TaskThread implements Runnable {
		private List<Integer> list;

		TaskThread(List<Integer> list) {
			this.list = new ArrayList<>(list);
		}

		@Override
		public void run() {
			for (Integer integer : list) {
				System.out.println("当前处理线程:" + Thread.currentThread().getName() + "==" + integer);
			}
		}
	}


	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>());
		try {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 10000; i++) {
				list.add(i);
			}

			threadPoolExecutor.submit(new TaskThread(list));
			list.clear();
			System.out.println("main over");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPoolExecutor.shutdown();
		}

	}
}
