package com.ixan.boot.test.juc.sync;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/8 下午8:30
 * @description 挂起唤醒测试
 */
public class WaitNotifyTest {
	private static final Object resource = new Object();

	private static class WaitTask implements Runnable {
		@Override
		public void run() {
			synchronized (resource) {
				try {
					System.out.println("thread:" + Thread.currentThread().getName() + " begin wait");
					resource.wait();
					System.out.println("thread:" + Thread.currentThread().getName() + " end wait");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class NotifyTask implements Runnable {
		@Override
		public void run() {
			synchronized (resource) {
				resource.notify();
				System.out.println("thread:" + Thread.currentThread().getName() + " notify");
			}
		}
	}

	private static class NotifyAllTask implements Runnable {
		@Override
		public void run() {
			synchronized (resource) {
				resource.notifyAll();
				System.out.println("thread:" + Thread.currentThread().getName() + " notifyAll");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread threadA = new Thread(new WaitTask(), "threadA");
		Thread threadB = new Thread(new WaitTask(), "threadB");
		Thread threadC = new Thread(new NotifyAllTask(), "threadC");

		threadA.start();
		threadB.start();
		Thread.sleep(1000L);
		threadC.start();

		threadA.join();
		threadB.join();
		threadC.join();
		System.out.println("main thread is over");

	}
}
