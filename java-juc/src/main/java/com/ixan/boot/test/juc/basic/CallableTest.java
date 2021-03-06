package com.ixan.boot.test.juc.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/4 下午3:32
 * @description 有返回值的线程创建方式
 */
public class CallableTest {
	public static class CallerTask implements Callable<String> {
		@Override
		public String call() throws Exception {
			return "caller task";
		}
	}

	public static class CallerExceptionTask implements Callable<String> {
		@Override
		public String call() throws Exception {
			throw new RuntimeException("caller task throw exception");
		}
	}

	public static void main(String[] args) {
		//创建异步任务
		FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
		FutureTask<String> futureTask2 = new FutureTask<>(new CallerExceptionTask());
		//启动线程
		new Thread(futureTask).start();
		new Thread(futureTask2).start();

		String s = null;
		try {
			s = futureTask.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("异步任务返回值:" + s);
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		String s2 = null;
		try {
			s2 = futureTask2.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("异步任务返回值:" + s2);
	}
}
