package cn.ixan.search.utils;

import java.util.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/6/19 下午5:07
 * @description ExampleHelper
 */
public final class ExampleHelper {
	/**
	 * 常量值列表
	 */
	public static final List<Integer> CONST_VALUE_LIST = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
	/**
	 * 常量值集合
	 */
	public static final Set<Integer> CONST_VALUE_SET = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 2, 3)));
	/**
	 * 常量值映射
	 */
	public static final Map<Integer, String> CONST_VALUE_MAP;

	static {
		Map<Integer, String> valueMap = new HashMap<>(3);
		valueMap.put(1, "value1");
		valueMap.put(2, "value2");
		valueMap.put(3, "value3");
		CONST_VALUE_MAP = Collections.unmodifiableMap(valueMap);
	}

	/**
	 * 常量值数组
	 */
	private static final int[] CONST_VALUES = new int[]{1, 2, 3};

	/**
	 * 获取常量值数组方法
	 */
	public static int[] getConstValues() {
		return CONST_VALUES.clone();
	}
}
