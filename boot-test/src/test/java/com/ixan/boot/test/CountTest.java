package com.ixan.boot.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/6/17 9:09
 * @description 计数测试
 * @version 1.0
 */
public class CountTest {
	public static void main(String[] args) {
		AtomicInteger count = new AtomicInteger(0);
		count(true, count);
		count(true, count);
		count(true, count);
		System.out.println(count.get());
	}

	public static void count(boolean condition, AtomicInteger count) {
		if (condition) {
			count.incrementAndGet();
		}
	}
}
