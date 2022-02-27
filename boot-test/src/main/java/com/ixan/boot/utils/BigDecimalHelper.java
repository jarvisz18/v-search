package com.ixan.boot.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/27 下午1:27
 * @description BigDecimal工具类
 */
public class BigDecimalHelper {
	/**
	 * 保留两位小数
	 * 传double的时候 精度会损失。。所以tostring
	 */
	public static float truncate(float v1, int number) {
		BigDecimal bg = new BigDecimal(Float.toString(v1)).setScale(number, RoundingMode.DOWN);
		return bg.floatValue();
	}

	/**
	 * 保留两位小数
	 * 传double的时候 精度会损失。。所以tostring
	 */
	public static double truncate(double v1, int number) {
		BigDecimal bg = new BigDecimal(Double.toString(v1)).setScale(number, RoundingMode.DOWN);
		return bg.doubleValue();
	}


	/**
	 * 两个 double 相减    避免   2.0 - 1.1 问题
	 */
	public static float subtract(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.subtract(b2).floatValue();
	}


	/**
	 * 两个 double 相减    避免   2.0 - 1.1 问题
	 */
	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 两个 float 相加  避免  3.6 + 0.4 问题
	 */
	public static float add(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.add(b2).floatValue();
	}

	/**
	 * 两个 double 相加  避免  3.6 + 0.4 问题
	 */
	public static BigDecimal add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2);
	}

	/**
	 * 乘法
	 */
	public static BigDecimal multiply(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.multiply(b2);
	}

	public static BigDecimal multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2);
	}

	public static BigDecimal multiplyBig(BigDecimal b1, double v2) {
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2);
	}

	/**
	 * 除法
	 */
	public static float divide(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.divide(b2).setScale(2).floatValue();
	}

	public static BigDecimal divide(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2);
	}
}
