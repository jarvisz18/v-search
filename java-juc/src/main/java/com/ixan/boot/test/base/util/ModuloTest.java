package com.ixan.boot.test.base.util;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/27 下午4:15
 * @description 取模和与运算性能比较
 */
public class ModuloTest {
	public static void main(String[] args) {
		long currentMills = System.currentTimeMillis();
		int a = 0;
		int times = 10000 * 10000;
		for (int i = 0; i < times; i++) {
			a = 9999 % 1024;
		}
		long currentMills2 = System.currentTimeMillis();

		int b = 0;
		for (int i = 0; i < times; i++) {
			b = 9999 & (1024 - 1);
		}
		long currentMills3 = System.currentTimeMillis();
		System.out.println("a:" + a + ",b:" + b);
		System.out.println("% time spend ms:" + (currentMills2 - currentMills));
		System.out.println("& time spend ms:" + (currentMills3 - currentMills2));
	}
}
