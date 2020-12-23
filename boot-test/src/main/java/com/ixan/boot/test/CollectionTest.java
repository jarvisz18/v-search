package com.ixan.boot.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/11/14 9:30 下午
 * @description
 */
public class CollectionTest {
	@Test
	public void testStopWatch() throws InterruptedException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("校验耗时");
		Thread.sleep(TimeUnit.SECONDS.toMillis(1L));
		stopWatch.stop();

		stopWatch.start("组装参数耗时");
		Thread.sleep(TimeUnit.SECONDS.toMillis(1L));
		stopWatch.stop();

		stopWatch.start("请求耗时");
		Thread.sleep(TimeUnit.SECONDS.toMillis(1L));
		stopWatch.stop();

		System.out.println(stopWatch.prettyPrint());
		System.out.println(stopWatch.getTotalTimeMillis());
	}

	@Test
	public void testRegex() {
		String pattern = ".*baidu.*";
		String content = "www.baidu.com,pan.baidu.com";
		Assert.assertTrue(Pattern.matches(pattern, content));
	}

	@Test
	public void testRetainAll() {
		Set<String> set1 = new HashSet<>(Arrays.asList("a", "b", "c"));
		Set<String> set2 = new HashSet<>(Arrays.asList("c", "d", "e"));
		set1.retainAll(set2);
		System.out.println(set1);
	}

	@Test
	public void testBigDecimal() {
		double num = 0.1D;
		BigDecimal bigDecimal = BigDecimal.valueOf(num);
		BigDecimal bigDecimal1 = new BigDecimal(num);
		System.out.println(bigDecimal.equals(bigDecimal1));
		//避免丢失精度，推荐使用下面这个
		System.out.println(bigDecimal);

		float num1 = 0.1F;
		BigDecimal bigDecimal_2 = BigDecimal.valueOf(num1);
		BigDecimal bigDecimal_3 = new BigDecimal(num1);
		System.out.println(bigDecimal_2.equals(bigDecimal_3));
		BigDecimal bigDecimal_4 = new BigDecimal(Float.toString(num1));
		//避免丢失精度，推荐使用下面这个
		System.out.println(bigDecimal_4);
	}
}
