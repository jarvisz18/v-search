package com.ixan.boot.test.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/21 21:41
 * @description 原子类生成序列号
 * @version 1.0
 */
public class AtomicIntegerExample01Test {
	public static void main(String[] args) {
		AtomicInteger count = new AtomicInteger(1);

		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				int i1 = count.get();
				System.out.println(Thread.currentThread().getName() + ":" + i1);
				/*try {
					Thread.sleep(20L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
				count.incrementAndGet();
			}).start();
		}
	}


}
