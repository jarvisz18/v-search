package com.ixan.boot.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import com.google.common.collect.Sets;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.time.temporal.TemporalAdjusters;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/12/7 10:15
 * @description 日期工具类
 * @version 1.0
 */
public class DateTool {
	/**
	 * 获取给定日期的差值月的第几个工作日(周一至周五)
	 * @param localDate 指定日期
	 * @param dValue 当前日期的差值月
	 * @param dayOfWork 第几个工作日
	 * @return LocalDate yyyy-MM-dd
	 */
	public static LocalDate getFirstInMonthDay(LocalDate localDate, int dValue, int dayOfWork) {
		//工作日
		Set<DayOfWeek> dayOfWorkSet = Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);

		LocalDate tempDate = localDate.plusMonths(dValue);
		//count 代表每月第几天,i 代表第几个工作日
		for (int i = 0, count = 1; i < dayOfWork; ) {
			tempDate = tempDate.withDayOfMonth(count);
			DayOfWeek dayOfWeek = tempDate.getDayOfWeek();
			if (dayOfWorkSet.contains(dayOfWeek)) {
				i++;
			}
			count++;
		}
		return tempDate;
	}

	public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		LocalDate firstInMonthDay = getFirstInMonthDay(now, -1, 3);
		System.out.println(firstInMonthDay);
	}


	public static String getCurrentDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(localDateTime);
	}

	public static void main(String[] args) {
		LocalDate lastDayOfMonth = getLastDayOfMonth();
		System.out.println("lastDayOfMonth :" + lastDayOfMonth);
		LocalDate firstDayOfMonth = getFirstDayOfMonth();
		System.out.println("firstDayOfMonth :" + firstDayOfMonth);
	}

	//获取本月的第一个星期一
	public static LocalDate getFirstDayOfMonth() {
		LocalDate now = LocalDate.now();
		return now.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
	}

	//获取本月的最后一个星期五
	public static LocalDate getLastDayOfMonth() {
		LocalDate now = LocalDate.now();
		return now.with(TemporalAdjusters.dayOfWeekInMonth(-1, DayOfWeek.FRIDAY));
	}
}
