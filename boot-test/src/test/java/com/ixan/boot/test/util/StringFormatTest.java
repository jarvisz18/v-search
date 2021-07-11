package com.ixan.boot.test.util;

import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/7/31 16:36
 * @description 字符串格式化测试
 */
public class StringFormatTest {
	public static void main(String[] args) {
		String format = String.format("现在时间:%s ,要处理的问题:%s", new Date(), "格式化字符串");
		System.out.println(format );
	}
}
