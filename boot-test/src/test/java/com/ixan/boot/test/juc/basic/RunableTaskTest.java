package com.ixan.boot.test.juc.basic;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/4 下午3:27
 * @description 实现Runnable接口的线程创建方式
 */
public class RunableTaskTest {
	public static class RunnableTask implements Runnable {
		@Override
		public void run() {
			System.out.println("i am a child thread");
		}
	}

	public static void main(String[] args) {
		RunnableTask task = new RunnableTask();
		new Thread(task).start();
		new Thread(task).start();
	}
}
