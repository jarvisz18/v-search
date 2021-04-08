package com.ixan.boot.test.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/4/5 13:18
 * @description 方法区(运行时常量池)和元空间溢出
 * @version 1.0
 */
public class MetaspaceOOM {
	private static String str = "test";

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		while (true) {
			String str2 = str + str;
			str = str2;
			list.add(str.intern());
		}
	}
}
