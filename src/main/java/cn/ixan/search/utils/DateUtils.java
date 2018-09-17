package cn.ixan.search.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 日期工具类
 */
public final class DateUtils {

	/**
	 * 返回某段时间的所有日期
	 * @param dateBegin 开始时间
	 * @param dateEnd 结束时间
	 * @return 一段日期字符串
	 */
	public static List<String> findDates(Date dateBegin, Date dateEnd) {
		List<String> lDate = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		lDate.add(format.format(dateBegin));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dateBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dateEnd);
		// 测试此日期是否在指定日期之后
		while (dateEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(format.format(calBegin.getTime()));
		}
		return lDate;
	}

	public static Date string2Date(String dateString){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition position = new ParsePosition(0);
		return format.parse(dateString, position);
	}

	public static int differentTimeByMills(Date dateBegin, Date dateEnd){
		int times = (int)((dateEnd.getTime()-dateBegin.getTime())/(1000*3600*24));
		return times;
	}
	public static void main(String[] args) {
		getList();
	}

	public static void getList(){
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("abb");
		list.add("sss");
		list.add("ccc");
		list.add("bba");
		List<String> a = list.stream().filter(s -> s.contains("a")).collect(Collectors.toList());
		a.forEach(System.out::println);
	}

}
