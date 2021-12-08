package com.ixan.boot.test;

import com.ixan.boot.utils.GUIDsGenerater;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/8/15 21:55
 * @description test guid
 */
public class GUIDGeneraterTest {
	@Test
	public void guid() {
		Assert.assertEquals(36, GUIDsGenerater.guid().length());
	}
}
