package com.ixan.boot.utils;

import com.google.common.collect.Sets;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/12/7 10:15
 * @description 日期工具类
 */
public class DateTool {
	/**
	 * 获取给定日期的差值月的第几个工作日(周一至周五)
	 *
	 * @param localDate 指定日期
	 * @param dValue    当前日期的差值月
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
		LocalDate lastDayOfMonth = getLastDayOfMonth();
		System.out.println("lastDayOfMonth :" + lastDayOfMonth);
		LocalDate firstDayOfMonth = getFirstDayOfMonth();
		System.out.println("firstDayOfMonth :" + firstDayOfMonth);
	}


	public static String getCurrentDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(localDateTime);
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

	//LocalDateTime to Date
	public static Date fromLocalDateTime(LocalDateTime localDateTime) {
		if (localDateTime == null) return null;
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	//Date to LocalDateTime
	public static LocalDateTime fromDate(Date date) {
		if (date == null) return null;
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}
