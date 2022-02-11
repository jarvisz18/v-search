package com.ixan.boot.test.juc.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/11 11:24
 * @description 并发累加器
 * @link https://mp.weixin.qq.com/s/7-vdq0-VCJsKVFUQxtTEuw
 * @version 1.0
 */
public class LongAccumulatorExample1 {

	@Test
	public void testLongAccumulatorAdd() throws InterruptedException {
		LongAccumulator balance = new LongAccumulator(Long::sum, 10_000L);
		Runnable w = () -> balance.accumulate(1_000L);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		IntStream.range(0, 50).boxed().forEach(i -> executorService.submit(w));

		//关闭线程池
		executorService.shutdown();
		if (executorService.awaitTermination(1L, TimeUnit.SECONDS)) {
			System.out.println("Balance:" + balance.get());
		}
		Assert.assertEquals(60_000L, balance.get());
	}
}
