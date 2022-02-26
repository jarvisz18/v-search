package com.ixan.boot.test.juc.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/30 下午3:46
 * @description Exchanger test
 */
public class ExchangerExample1 {
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();
		new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + " start");
				String result = exchanger.exchange("thread-1 data", 5, TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName() + " get result [" + result + "]");
				System.out.println(Thread.currentThread().getName() + " finish.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				System.out.println("time out");
				e.printStackTrace();
			}
		}, "thread-1").start();

		new Thread(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + " start");
				TimeUnit.SECONDS.sleep(10);
				String result = exchanger.exchange("thread-2 data");
				System.out.println(Thread.currentThread().getName() + " get result [" + result + "]");
				System.out.println(Thread.currentThread().getName() + " finish.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "thread-2").start();
	}
}
