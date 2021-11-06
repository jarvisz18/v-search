package com.ixan.boot.test.juc.sync;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/6 下午1:35
 * @description 卖票
 */
public class SaleTicket {
	private int num = 30;

	private synchronized void sale() {
		if (num > 0) {
			System.out.println("name:" + Thread.currentThread().getName() + "卖出:" + (num--) + "还剩:" + num);
		}
	}

	public static void main(String[] args) {
		SaleTicket ticket = new SaleTicket();
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
