package com.ixan.boot.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

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

	public static void main(String[] args) {
		LocalDate lastDayOfMonth = getLastDayOfMonth();
		System.out.println("lastDayOfMonth :" + lastDayOfMonth);
	}

	//获取本月的最后一个星期五
	public static LocalDate getLastDayOfMonth() {
		LocalDate now = LocalDate.now();
		return now.with(TemporalAdjusters.dayOfWeekInMonth(-1, DayOfWeek.FRIDAY));
	}
}
