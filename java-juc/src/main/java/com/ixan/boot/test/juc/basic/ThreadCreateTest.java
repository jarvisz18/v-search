package com.ixan.boot.test.juc.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/4 下午3:22
 * @description 继承Thread类的线程创建方式
 */
public class ThreadCreateTest {
	//实现Callable接口并重写call方法
	public static class CallerTask implements Callable<String> {
		public String call() throws Exception {
			return "hello";
		}
	}

	//实现Runnable接口并重写run方法
	public static class RunableTask implements Runnable {
		public void run() {
			System.out.println("i am a child thread");
		}
	}

	//继承Thread类并重写run方法
	public static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("i am a child thread");
			System.out.println("i am a child thread");
			System.out.println("i am a child thread");
		}
	}

	public static void main(String[] args) {
		testThreadTask();
		System.out.println("---------------");
		testRunnableTeak();

		//创建异步任务
		FutureTask<String> stringFutureTask = new FutureTask<>(new CallerTask());
		new Thread(stringFutureTask).start();
		String result = null;
		try {
			result = stringFutureTask.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(result);

	}

	private static void testRunnableTeak() {
		RunableTask task = new RunableTask();
		new Thread(task).start();
		new Thread(task).start();
	}

	private static void testThreadTask() {
		//创建线程
		MyThread myThread = new MyThread();
		//启动线程
		myThread.start();
	}
}
