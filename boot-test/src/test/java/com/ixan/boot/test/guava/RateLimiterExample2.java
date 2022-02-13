package com.ixan.boot.test.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/13 14:49
 * @description 令牌桶算法
 * @version 1.0
 */
public class RateLimiterExample2 {
	public static void main(String[] args) throws InterruptedException {
		//每秒钟生成5个令牌
		RateLimiter rateLimiter = RateLimiter.create(5);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.range(0, 10).boxed().forEach(i -> {
			executorService.execute(() -> {
				//获取令牌
				for (int temp = 0; temp < 100; temp++) {
					System.out.println(rateLimiter.acquire(1));
				}

			});
		});
		executorService.shutdown();
		executorService.awaitTermination(30, TimeUnit.MINUTES);
	}
}
