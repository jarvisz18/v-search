package com.ixan.boot.test.juc.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/30 下午4:09
 * @description 交换的场景
 */
public class ExchangerExample3 {
	public static void main(String[] args) {
		Exchanger<Integer> exchanger = new Exchanger<>();
		new Thread(() -> {
			try {
				AtomicReference<Integer> data = new AtomicReference<>(1);
				while (true) {
					System.out.println(Thread.currentThread().getName() + "交换前的数据:" + data.get());
					TimeUnit.SECONDS.sleep(3L);
					data.set(exchanger.exchange(data.get()));
					System.out.println(Thread.currentThread().getName() + "交换后的数据:" + data.get());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread-1").start();

		new Thread(() -> {
			try {
				AtomicReference<Integer> data = new AtomicReference<>(2);
				while (true) {
					System.out.println(Thread.currentThread().getName() + "交换前的数据:" + data.get());
					TimeUnit.SECONDS.sleep(3L);
					data.set(exchanger.exchange(data.get()));
					System.out.println(Thread.currentThread().getName() + "交换后的数据:" + data.get());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread-2").start();
	}

}
