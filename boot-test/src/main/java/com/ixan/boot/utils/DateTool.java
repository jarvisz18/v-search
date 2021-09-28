package com.ixan.boot.utils;

import com.google.common.collect.Sets;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Set;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/12/7 10:15
 * @description JAVA8 日期工具类
 */
public final class DateTool {
	private DateTool() {
		throw new UnsupportedOperationException();
	}
	//获取当前时间的LocalDateTime对象
	//LocalDateTime.now();

	//根据年月日构建LocalDateTime
	//LocalDateTime.of();

	//比较日期先后
	//LocalDateTime.now().isBefore(),
	//LocalDateTime.now().isAfter(),


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

	//LocalDate to Date
	public static Date fromLocalDate(LocalDate localDate) {
		if (localDate == null) return null;
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
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

	// format LocalDateTime
	public static String formatLocalDateTime(LocalDateTime date) {
		if (date == null) return null;
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date);
	}

	//获取指定日期的毫秒
	public static Long getMilliByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	//获取指定日期的秒
	public static Long getSecondsByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
	}

	//获取指定时间的指定格式
	public static String formatTime(LocalDateTime time, String pattern) {
		return time.format(DateTimeFormatter.ofPattern(pattern));
	}

	//获取当前时间的指定格式
	public static String formatNow(String pattern) {
		return formatTime(LocalDateTime.now(), pattern);
	}

	//日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
	public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
		return time.plus(number, field);
	}

	//日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
	public static LocalDateTime minus(LocalDateTime time, long number, TemporalUnit field) {
		return time.minus(number, field);
	}

	/**
	 * 获取两个日期的差  field参数为ChronoUnit.*
	 */
	public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
		Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
		if (field == ChronoUnit.YEARS) return period.getYears();
		if (field == ChronoUnit.MONTHS) return period.getYears() * 12 + period.getMonths();
		return field.between(startTime, endTime);
	}

	//获取一天的开始时间，2017,7,22 00:00
	public static LocalDateTime getDayStart(LocalDateTime time) {
		return time.withHour(0)
				.withMinute(0)
				.withSecond(0)
				.withNano(0);
	}

	//获取一天的结束时间，2017,7,22 23:59:59.999999999
	public static LocalDateTime getDayEnd(LocalDateTime time) {
		return time.withHour(23)
				.withMinute(59)
				.withSecond(59)
				.withNano(999999999);
	}

	//获取今天的开始时间
	public static String getTodayStart() {
		LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(localDateTime);
	}

	//获取今天的结束时间
	public static String getTodayEnd() {
		LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(localDateTime);
	}

}
