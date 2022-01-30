package com.ixan.boot.test.juc.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/30 上午11:27
 * @description CyclicBarrier例子
 */
public class CyclicBarrierExample {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
			System.out.println("all of finish ..");
		});

		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("T1 thread start");
					TimeUnit.MILLISECONDS.sleep(2000);
					cyclicBarrier.await();
					System.out.println("T1 thread finish");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("T2 thread start");
					TimeUnit.MILLISECONDS.sleep(100);
					cyclicBarrier.await();
					System.out.println("T2 thread finish");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}
}
