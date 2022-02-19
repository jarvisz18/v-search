package com.ixan.boot.test.juc.basic;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/19 上午9:25
 * @description synchronized example
 * <p>javap -v com.ixan.boot.test.juc.basic.SynchronizedExample6</p>
 */
public class SynchronizedExample6 {
	public synchronized void method() {
		System.out.println("Hello World!");
	}
}
