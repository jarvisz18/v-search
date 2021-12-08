package cn.ixan.search.utils;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/6/19 下午5:02
 * @description ExampleEnum
 */
public enum ExampleEnum {
	/**
	 * 枚举相关
	 */
	ONE(1, "one(1)"),

	TWO(2, "two(2)"),

	THREE(3, "two(3)");

	/**
	 * 字段相关
	 */
	private final int value;
	private final String desc;

	/**
	 * 构造方法
	 */
	ExampleEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	/**
	 * 获取取值
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 获取描述
	 */
	public String getDesc() {
		return desc;
	}
}
