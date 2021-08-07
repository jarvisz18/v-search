package com.ixan.boot.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/7 下午9:06
 * @description 日期转换
 */
public class DateJacksonConverter extends JsonDeserializer<Date> {
	private static String[] pattern =
			new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S",
					"yyyy.MM.dd", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss.S",
					"yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss.S"};

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

		Date targetDate = null;
		String originDate = p.getText();
		if (StringUtils.isNotEmpty(originDate)) {
			try {
				long longDate = Long.valueOf(originDate.trim());
				targetDate = new Date(longDate);
			} catch (NumberFormatException e) {
				try {
					targetDate = DateUtils.parseDate(originDate, DateJacksonConverter.pattern);
				} catch (ParseException pe) {
					throw new IOException(String.format(
							"'%s' can not convert to type 'java.util.Date',just support timestamp(type of long) and following date format(%s)",
							originDate,
							StringUtils.join(pattern, ",")));
				}
			}
		}

		return targetDate;
	}

	@Override
	public Class<?> handledType() {
		return Date.class;
	}

}
