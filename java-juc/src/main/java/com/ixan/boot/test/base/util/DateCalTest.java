package com.ixan.boot.test.base.util;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/5 下午8:44
 * @description 日期时间计算
 */
public class DateCalTest {

	/**
	 * ChronoUnit 类可用于在单个时间单位内测量一段时间，例如天数或秒<br/>
	 */
	@Test
	public void testChronoUnit() {
		LocalDate startDate = LocalDate.of(2018, 3, 6);
		System.out.println("start date:" + startDate);
		LocalDate endDate = LocalDate.now();
		System.out.println("end date:" + endDate);
		long between = ChronoUnit.DAYS.between(startDate, endDate);
		System.out.println("起止日期相差的天数:" + between);
	}

	/**
	 * Duration 提供了基于时间的值（如秒，纳秒）测量时间量的方法<br/>
	 */
	@Test
	public void testDuration() {
		Instant inst1 = Instant.now();
		System.out.println(inst1);
		Instant inst2 = inst1.plus(Duration.ofSeconds(20));
		System.out.println(inst2);
		System.out.println("different in mill seconds:" + Duration.between(inst1, inst2).toMillis());
		System.out.println("different in seconds:" + Duration.between(inst1, inst2).getSeconds());
	}

	/**
	 * Period 用来计算LocalDate之间相差的年、月、日
	 */
	@Test
	public void testPeriod() {
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(1995, 5, 16);
		System.out.println("birthday:" + birthday);
		Period period = Period.between(birthday, today);
		System.out.printf("age: %s year %s month %s day", period.getYears(),
				period.getMonths(), period.getDays());
	}
}
