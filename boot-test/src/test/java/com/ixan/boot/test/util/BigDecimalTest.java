package com.ixan.boot.test.util;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/11 下午8:11
 * @description BigDecimal
 */
public class BigDecimalTest {

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
