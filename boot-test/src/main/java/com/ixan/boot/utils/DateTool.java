package com.ixan.boot.utils;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/12/7 10:15
 * @description JAVA8 日期工具类
 */
public final class DateTool {

	private static final DateTimeFormatter CST_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter UTC_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

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
		return CST_DATE_TIME_FORMATTER.format(localDateTime);
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
		if (localDate == null) {
			return null;
		}
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * timestamp 转 java.util.Date
	 */
	@Nullable
	public static Date toDate(Timestamp timestamp){
		if(Objects.isNull(timestamp)){
			return null;
		}
		return new Date(timestamp.getTime());
	}

	/**
	 * Timestamp 转换LocalDateTime
	 */
	@Nullable
	public static LocalDateTime toLocalDateTime(Timestamp timestamp){
		if(Objects.isNull(timestamp)){
			return null;
		}
		return timestamp.toLocalDateTime();
	}

	/**
	 * LocalDateTime 转换 Timestamp
	 */
	@Nullable
	public static Timestamp toTimestamp(LocalDateTime localDateTime){
		if(Objects.isNull(localDateTime)){
			return null;
		}
		return Timestamp.valueOf(localDateTime);
	}

	/**
	 * Java8 LocalDateTime to Date
	 */
	@Nullable
	public static Date fromLocalDateTime(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Java8 Date to LocalDateTime
	 */
	@Nullable
	public static LocalDateTime fromDate(Date date) {
		if (date == null) {
			return null;
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	// format LocalDateTime
	public static String formatLocalDateTime(LocalDateTime date) {
		if (date == null) {
			return null;
		}
		return CST_DATE_TIME_FORMATTER.format(date);
	}

	//获取指定日期的毫秒
	public static Long getMilliByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	//获取指定日期的秒
	public static Long getSecondsByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
	}

	/**
	 * 格式化UTC 时间,必须按照标准格式: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	 */
	public static LocalDateTime formatUtcTime(String utcTimeString) {
		if (StringUtils.isBlank(utcTimeString)) {
			return null;
		}
		return LocalDateTime.parse(utcTimeString, UTC_DATE_TIME_FORMATTER);
	}

	/**
	 * 强烈推荐,这种格式化UTC时间
	 * 支持: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'以及毫秒可省略的yyyy-MM-dd'T'HH:mm:ss'Z'
	 */
	public static LocalDateTime formatUtcTime2(String utcTimeString) {
		if (StringUtils.isBlank(utcTimeString)) {
			return null;
		}
		Instant instant = Instant.parse(utcTimeString);
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

	public static ZonedDateTime formatUtcTime3(String utcTimeString) {
		if (StringUtils.isBlank(utcTimeString)) {
			return null;
		}
		Instant instant = Instant.parse(utcTimeString);
		return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
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
		if (field == ChronoUnit.YEARS) {
			return period.getYears();
		}
		if (field == ChronoUnit.MONTHS) {
			return period.getYears() * 12L + period.getMonths();
		}
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
		return CST_DATE_TIME_FORMATTER.format(localDateTime);
	}

	//获取今天的结束时间
	public static String getTodayEnd() {
		LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		return CST_DATE_TIME_FORMATTER.format(localDateTime);
	}

}
