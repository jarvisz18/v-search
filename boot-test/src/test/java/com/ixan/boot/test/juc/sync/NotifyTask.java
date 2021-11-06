package com.ixan.boot.test.juc.sync;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/6 下午2:24
 * @description 线程间通信
 */
public class NotifyTask {
	private int num = 0;

	private synchronized void incr() throws InterruptedException {
		//if替换为while 防止虚假唤醒
		while (num != 0) {
			this.wait();
		}
		num++;
		System.out.println(Thread.currentThread().getName() + " num: " + num);
		this.notifyAll();
	}

	private synchronized void decr() throws InterruptedException {
		//if替换为while 防止虚假唤醒
		while (num != 1) {
			this.wait();
		}
		num--;
		System.out.println(Thread.currentThread().getName() + " num: " + num);
		this.notifyAll();
	}

	public static void main(String[] args) {
		NotifyTask notifyTask = new NotifyTask();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					notifyTask.incr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-1").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					notifyTask.decr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-2").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					notifyTask.incr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-3").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					notifyTask.decr();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "thread-4").start();


	}
}
