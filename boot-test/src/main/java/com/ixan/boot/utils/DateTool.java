package com.ixan.boot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/12/7 10:15
 * @description 日期工具类
 * @version 1.0
 */
public class DateTool {
	public static String getCurrentDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(localDateTime);
	}
}
