package com.ixan.boot.utils;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2024/6/16 11:02
 * @description date tool test
 */
public class DateToolTest {


	@Test
	public void test_formatUtcTime3(){
		String utcTimeString1 = "2024-06-16T12:28:30.156Z";
		String utcTimeString2 = "2024-06-16T12:28:30.15Z";
		String utcTimeString3 = "2024-06-16T12:28:30.1Z";
		String utcTimeString4 = "2024-06-16T12:28:30Z";

		System.out.println(DateTool.formatUtcTime3(utcTimeString1));
		System.out.println(DateTool.formatUtcTime3(utcTimeString2));
		System.out.println(DateTool.formatUtcTime3(utcTimeString3));
		System.out.println(DateTool.formatUtcTime3(utcTimeString4));
	}

	@Test
	public void test_formatUtcTime2(){
		String utcTimeString1 = "2024-06-16T12:28:30.156Z";
		String utcTimeString2 = "2024-06-16T12:28:30.15Z";
		String utcTimeString3 = "2024-06-16T12:28:30.1Z";
		String utcTimeString4 = "2024-06-16T12:28:30Z";

		System.out.println(DateTool.formatUtcTime2(utcTimeString1));
		System.out.println(DateTool.formatUtcTime2(utcTimeString2));
		System.out.println(DateTool.formatUtcTime2(utcTimeString3));
		System.out.println(DateTool.formatUtcTime2(utcTimeString4));
	}

	@Test
	public void test_formatUtcTime(){
		LocalDateTime localDateTime = DateTool.formatUtcTime("2024-06-16T12:28:30.156Z");
		System.out.println(localDateTime);
	}

	@Test
	public void test(){
		String todayStart = DateTool.getTodayStart();
		String todayEnd = DateTool.getTodayEnd();
		System.out.println(todayStart);
		System.out.println(todayEnd);
	}

	@Test
	public void test_toLocalDateTime(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		LocalDateTime localDateTime = DateTool.toLocalDateTime(timestamp);
		System.out.println(localDateTime);
		// true
		assert localDateTime!= null;
		assert DateTool.toLocalDateTime(null) == null;
	}

	@Test
	public void test_toTimestamp(){
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = DateTool.toTimestamp(now);
		System.out.println(timestamp);
		// true
		assert timestamp!= null;
		assert DateTool.toTimestamp(null) == null;
	}

	@Test
	public void test_toDate(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
		System.out.println(DateTool.toDate(timestamp));
	}

	@Test
	public void test_toDate2(){
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime);
		System.out.println(DateTool.fromLocalDateTime(dateTime));
	}

	@Test
	public void test_toLocalDateTime2(){
		Date date = new Date();
		System.out.println(date);
		System.out.println(DateTool.fromDate(date));
	}
}
