package cn.ixan.search.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期工具类,切勿重复造轮子(也可使用Java8日期API)
 * @see DateUtils
 * @author stack_zhang@outlook.com
 */
public class DateUtil extends DateUtils{

	public static Date string2Date(String dateString){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition position = new ParsePosition(0);
		return format.parse(dateString, position);
	}

	public static long differentTimeByMills(Date dateBegin, Date dateEnd){
		return (dateEnd.getTime()-dateBegin.getTime())/(1000*3600*24);
	}

}
