package com.ixan.boot.test.juc.atomic;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/1/24 16:14
 * @description 原子类AtomicBoolean 测试
 * @version 1.0
 */
public class AtomicBooleanTest {

	@Test
	public void testCompareAndSetSuccess() {
		AtomicBoolean ab = new AtomicBoolean();
		Assert.assertTrue(ab.compareAndSet(false, true));
		Assert.assertTrue(ab.get());
	}

	@Test
	public void testCompareAndSet() {
		AtomicBoolean ab = new AtomicBoolean();
		Assert.assertFalse(ab.compareAndSet(true, false));
	}

	@Test
	public void testDefault() {
		AtomicBoolean ab = new AtomicBoolean();
		Assert.assertFalse(ab.get());
	}
}
