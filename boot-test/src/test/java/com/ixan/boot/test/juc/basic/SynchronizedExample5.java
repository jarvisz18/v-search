package com.ixan.boot.test.juc.basic;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/19 上午9:16
 * @description synchronized example
 * <p>javap -v com.ixan.boot.test.juc.basic.SynchronizedExample5</p>
 */
public class SynchronizedExample5 {
	public void method() {
		synchronized (this) {
			System.out.println("method start");
		}
	}
}
