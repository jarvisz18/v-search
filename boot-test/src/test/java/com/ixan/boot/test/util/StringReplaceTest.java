package com.ixan.boot.test.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/11 下午7:49
 * @description 字符串替换测试
 */
public class StringReplaceTest {

	//去除空白字符
	@Test
	public void testTrimMethodThree() {
		String str = "  字符串\t测试\n" +
				"  空白字符";
		Assert.assertEquals("字符串测试空白字符", str.replaceAll("\\s", ""));
	}

	//去所有空格
	@Test
	public void testTrimMethodTwo() {
		String str = "  字符 串";
		Assert.assertEquals("字符串", str.replace(" ", ""));
		Assert.assertEquals("字符串", str.replaceAll(" ", ""));
		Assert.assertEquals("字符串", str.replaceAll(" +", ""));
	}

	//去前后空格
	@Test
	public void testTrimMethod() {
		String str = "  字符串 ";
		Assert.assertEquals("字符串", str.trim());
	}
}
