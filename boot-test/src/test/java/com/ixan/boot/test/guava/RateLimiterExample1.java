package com.ixan.boot.test.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 22:27
 * @description 令牌桶算法实现
 * @version 1.0
 */
public class RateLimiterExample1 {
	private static final RateLimiter rateLimiter = RateLimiter.create(5);

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			if (rateLimiter.tryAcquire(190, TimeUnit.MILLISECONDS)) {
				System.out.println(i);
			}
		}
	}
}
