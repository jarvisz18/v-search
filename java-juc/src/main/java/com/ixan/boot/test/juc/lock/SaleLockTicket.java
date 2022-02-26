package com.ixan.boot.test.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/6 下午1:48
 * @description 使用可重入锁卖票
 */
public class SaleLockTicket {
	private final ReentrantLock lock = new ReentrantLock();
	/**
	 * 票的数量
	 */
	private int ticketNum = 30;

	private void sale() {
		//加锁
		lock.lock();
		try {
			if (ticketNum > 0) {
				System.out.println("name:" + Thread.currentThread().getName() + "卖出:" + (ticketNum--) + "剩余:" + ticketNum);
			}
		} finally {
			//解锁
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		SaleLockTicket ticket = new SaleLockTicket();
		new Thread(() -> {
			for (int i = 0; i < 40; i++) {
				ticket.sale();
			}
		}, "thread-1").start();

		new Thread(() -> {
			for (int i = 0; i < 40; i++) {
				ticket.sale();
			}
		}, "thread-2").start();

		new Thread(() -> {
			for (int i = 0; i < 40; i++) {
				ticket.sale();
			}
		}, "thread-3").start();
	}
}
