package com.ixan.boot.test.juc.util;

import java.util.concurrent.Exchanger;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/30 下午4:04
 * @description 测试交换数据的地址值
 * 同一数据，交换前后的地址是一样的，不是它的拷贝
 */
public class ExchangerExample2 {
	public static void main(String[] args) {
		Exchanger<Object> exchanger = new Exchanger<>();
		new Thread(() -> {
			try {
				Object object = new Object();
				System.out.println(Thread.currentThread().getName() + "交换前的数据:" + object);
				Object exchange = exchanger.exchange(object);
				System.out.println(Thread.currentThread().getName() + "交换后的数据:" + exchange);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread-1").start();

		new Thread(() -> {
			try {
				Object object = new Object();
				System.out.println(Thread.currentThread().getName() + "交换前的数据:" + object);
				Object exchange = exchanger.exchange(object);
				System.out.println(Thread.currentThread().getName() + "交换后的数据:" + exchange);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread-2").start();
	}
}
