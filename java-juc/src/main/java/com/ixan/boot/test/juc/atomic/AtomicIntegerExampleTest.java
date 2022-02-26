package com.ixan.boot.test.juc.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/23 下午9:11
 * @description 原子类的测试
 */
public class AtomicIntegerExampleTest {

	@Test
	public void testAtomicIntegerGet() {
		final AtomicInteger value = new AtomicInteger(10);
		Assert.assertFalse(value.compareAndSet(1, 2));
		Assert.assertEquals(value.get(), 10);
		Assert.assertTrue(value.compareAndSet(10, 3));
		Assert.assertEquals(value.get(), 3);
	}
}
