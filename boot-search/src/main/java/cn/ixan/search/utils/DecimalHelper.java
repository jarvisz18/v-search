package cn.ixan.search.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/11 下午8:07
 * @description Decimal工具类
 */
public final class DecimalHelper {
	private DecimalHelper() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 保留两位小数
	 * 传double的时候，精度会损失。所以toString
	 */
	public static float truncate(float v1, int number) {
		BigDecimal bigDecimal = new BigDecimal(Float.toString(v1)).setScale(number, RoundingMode.DOWN);
		return bigDecimal.floatValue();
	}

	/**
	 * 保留两位小数
	 * 传double的时候，精度会损失。所以toString
	 */
	public static float truncate(double v1, int number) {
		BigDecimal bigDecimal = new BigDecimal(Double.toString(v1)).setScale(number, RoundingMode.DOWN);
		return bigDecimal.floatValue();
	}


	/**
	 * 加法计算（result = x + y）
	 *
	 * @param x 被加数（可为null）
	 * @param y 加数 （可为null）
	 * @return 和 （可为null）
	 * @author dengcs
	 */
	public static BigDecimal add(BigDecimal x, BigDecimal y) {
		if (x == null) {
			return y;
		}
		if (y == null) {
			return x;
		}
		return x.add(y);
	}

	/**
	 * 两数相加,避免3.6+0.4问题;
	 */
	public static float add(float x, float y) {
		BigDecimal b1 = new BigDecimal(Float.toString(x));
		BigDecimal b2 = new BigDecimal(Float.toString(y));
		return b1.add(b2).floatValue();
	}

	/**
	 * 两数相加,避免3.6+0.4问题;
	 */
	public static float add(double x, double y) {
		BigDecimal b1 = new BigDecimal(Double.toString(x));
		BigDecimal b2 = new BigDecimal(Double.toString(y));
		return b1.add(b2).floatValue();
	}

	/**
	 * 加法计算（result = a + b + c + d）
	 *
	 * @param a 被加数（可为null）
	 * @param b 加数（可为null）
	 * @param c 加数（可为null）
	 * @param d 加数（可为null）
	 * @return BigDecimal （可为null）
	 * @author dengcs
	 */
	public static BigDecimal add(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d) {
		BigDecimal ab = add(a, b);
		BigDecimal cd = add(c, d);
		return add(ab, cd);
	}

	/**
	 * 累加计算(result=x + result)
	 *
	 * @param x      被加数（可为null）
	 * @param result 和 （可为null,若被加数不为为null，result默认值为0）
	 * @return result 和 （可为null）
	 * @author dengcs
	 */
	public static BigDecimal accumulate(BigDecimal x, BigDecimal result) {
		if (x == null) {
			return result;
		}
		if (result == null) {
			result = new BigDecimal("0");
		}
		return result.add(x);
	}

	/**
	 * 减法计算(result = x - y)
	 *
	 * @param x 被减数（可为null）
	 * @param y 减数（可为null）
	 * @return BigDecimal 差 （可为null）
	 * @author dengcs
	 */
	public static BigDecimal subtract(BigDecimal x, BigDecimal y) {
		if (x == null || y == null) {
			return null;
		}
		return x.subtract(y);
	}

	/**
	 * 两个float 相减 避免 2.0 - 1.1问题
	 */
	public static float subtract(float x, float y) {
		BigDecimal b1 = new BigDecimal(Float.toString(x));
		BigDecimal b2 = new BigDecimal(Float.toString(y));
		return b1.subtract(b2).floatValue();
	}

	/**
	 * 两个double 相减 避免 2.0 - 1.1问题
	 */
	public static float subtract(double x, double y) {
		BigDecimal b1 = new BigDecimal(Double.toString(x));
		BigDecimal b2 = new BigDecimal(Double.toString(y));
		return b1.subtract(b2).floatValue();
	}


	/**
	 * 乘法计算(result = x × y)
	 *
	 * @param x 乘数(可为null)
	 * @param y 乘数(可为null)
	 * @return BigDecimal 积
	 * @author dengcs
	 */
	public static BigDecimal multiply(BigDecimal x, BigDecimal y) {
		if (x == null || y == null) {
			return null;
		}
		return x.multiply(y);
	}

	public static BigDecimal multiply(float x, float y) {
		BigDecimal b1 = new BigDecimal(Float.toString(x));
		BigDecimal b2 = new BigDecimal(Float.toString(y));
		return b1.multiply(b2);
	}

	public static BigDecimal multiply(double x, double y) {
		BigDecimal b1 = new BigDecimal(Double.toString(x));
		BigDecimal b2 = new BigDecimal(Double.toString(y));
		return b1.multiply(b2);
	}

	/**
	 * 除法计算(result = x ÷ y)
	 *
	 * @param x 被除数（可为null）
	 * @param y 除数（可为null）
	 * @return 商 （可为null,四舍五入，默认保留20位小数）
	 * @author dengcs
	 */
	public static BigDecimal divide(BigDecimal x, BigDecimal y) {
		if (x == null || y == null || y.compareTo(BigDecimal.ZERO) == 0) {
			return null;
		}
		// 结果为0.000..时，不用科学计数法展示
		return stripTrailingZeros(x.divide(y, 20, BigDecimal.ROUND_HALF_UP));
	}

	public static BigDecimal divide(double x, double y) {
		BigDecimal b1 = new BigDecimal(Double.toString(x));
		BigDecimal b2 = new BigDecimal(Double.toString(y));
		return b1.divide(b2);
	}

	/**
	 * 转为字符串(防止返回可续计数法表达式)
	 *
	 * @param x 要转字符串的小数
	 * @return String
	 * @author dengcs
	 */
	public static String toPlainString(BigDecimal x) {
		if (x == null) {
			return null;
		}
		return x.toPlainString();
	}

	/**
	 * 保留小数位数
	 *
	 * @param x     目标小数
	 * @param scale 要保留小数位数
	 * @return BigDecimal 结果四舍五入
	 * @author dengcs
	 */
	public static BigDecimal scale(BigDecimal x, int scale) {
		if (x == null) {
			return null;
		}
		return x.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 整型转为BigDecimal
	 *
	 * @param x(可为null)
	 * @return BigDecimal (可为null)
	 * @author dengcs
	 */
	public static BigDecimal toBigDecimal(Integer x) {
		if (x == null) {
			return null;
		}
		return new BigDecimal(x.toString());
	}

	/**
	 * 长整型转为BigDecimal
	 *
	 * @param x(可为null)
	 * @return BigDecimal (可为null)
	 * @author dengcs
	 */
	public static BigDecimal toBigDecimal(Long x) {
		if (x == null) {
			return null;
		}
		return new BigDecimal(x.toString());
	}

	/**
	 * 双精度型转为BigDecimal
	 *
	 * @param x(可为null)
	 * @return BigDecimal (可为null)
	 * @author dengcs
	 */
	public static BigDecimal toBigDecimal(Double x) {
		if (x == null) {
			return null;
		}
		return new BigDecimal(x.toString());
	}

	/**
	 * 单精度型转为BigDecimal
	 *
	 * @param x(可为null)
	 * @return BigDecimal (可为null)
	 * @author dengcs
	 */
	public static BigDecimal toBigDecimal(Float x) {
		if (x == null) {
			return null;
		}
		return new BigDecimal(x.toString());
	}

	/**
	 * 字符串型转为BigDecimal
	 *
	 * @param x(可为null)
	 * @return BigDecimal (可为null)
	 * @author dengcs
	 */
	public static BigDecimal toBigDecimal(String x) {
		if (x == null) {
			return null;
		}
		return new BigDecimal(x);
	}

	/**
	 * 对象类型转为BigDecimal
	 *
	 * @param x(可为null)
	 * @return BigDecimal (可为null)
	 * @author dengcs
	 */
	public static BigDecimal toBigDecimal(Object x) {
		if (x == null) {
			return null;
		}
		BigDecimal result = null;
		try {
			result = new BigDecimal(x.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 倍数计算，用于单位换算
	 *
	 * @param x        目标数(可为null)
	 * @param multiple 倍数 (可为null)
	 * @return BigDecimal (可为null)
	 * @author dengcs
	 */
	public static BigDecimal multiple(BigDecimal x, Integer multiple) {
		if (x == null || multiple == null) {
			return null;
		}
		return multiply(x, toBigDecimal(multiple));
	}

	/**
	 * 去除小数点后的0（如: 输入1.000返回1）
	 *
	 * @param x 目标数(可为null)
	 * @return
	 */
	public static BigDecimal stripTrailingZeros(BigDecimal x) {
		if (x == null) {
			return null;
		}
		return x.stripTrailingZeros();
	}
}
