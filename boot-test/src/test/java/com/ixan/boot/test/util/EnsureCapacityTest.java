package com.ixan.boot.test.util;

import java.util.ArrayList;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/8 下午12:29
 * @description EnsureCapacityTest
 */
public class EnsureCapacityTest {
	public static void main(String[] args) {
		ArrayList<Object> list = new ArrayList<Object>();
		final int N = 10000000;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			list.add(i);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("使用ensureCapacity方法前：" + (endTime - startTime));
	}
}
