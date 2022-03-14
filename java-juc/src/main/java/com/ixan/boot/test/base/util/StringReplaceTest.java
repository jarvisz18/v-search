package com.ixan.boot.test.base.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;

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

	@Test
	public void testSumSubString() {
		String a = "aabbccaaadaaeaafaaa";
		String b = "aa";
		Assert.assertEquals(5, sumSubString(a, b));
	}

	/**
	 * b 为$ \时会发生死循环
	 * 解决办法:使用{@link java.util.regex.Matcher#quoteReplacement(String)}​
	 */
	@Test(timeout = 5)
	public void testSumSubString2() {
		String a = "$bbcc$ad$e$f$a";
		String b = "$";
		Assert.assertEquals(5, sumSubString(a, b));
	}

	@Test
	public void testSumSubString3() {
		String a = "$bbcc$ad$e$f$a";
		String b = "$";
		Assert.assertEquals(5, sumSubString2(a, b));
	}

	/**
	 * b 为空格时会发生死循环
	 */
	@Test(timeout = 5)
	public void testSumSubString4() {
		String a = " bbcc ad e f a";
		String b = " ";
		Assert.assertEquals(5, sumSubString(a, b));
	}

	/**
	 * <p>1，以下代码有什么目的？ 有什么严重的bug？</p>
	 * 计算替换字符串的次数
	 * @param a 源字符串
	 * @param b 要匹配的字符串
	 * @return count
	 */
	public static int sumSubString(String a, String b) {
		int sub = 0;
		while (a.contains(b)) {
			sub++;
			a = a.replaceFirst(b, " ");
		}
		return sub;
	}

	/**
	 * 计算替换字符串的次数
	 * @param a 源字符串
	 * @param b 要匹配的字符串
	 * @return count
	 */
	public static int sumSubString2(String a, String b) {
		int sub = 0;
		while (a.contains(b)) {
			sub++;
			a = a.replaceFirst(Matcher.quoteReplacement(b), " ");
		}
		return sub;
	}

}
