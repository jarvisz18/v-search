package cn.ixan.search.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/7/5 14:47
 * @description P6spyLogFormat
 * @version 1.0
 */
public class P6spyLogFormat implements MessageFormattingStrategy {
	@Override
	public String formatMessage(final int connectionId, final String now, final long elapsed, final String category, final String prepared, final String sql, final String url) {
		return !StringUtils.isEmpty(sql) ? "Execute SQL：" + sql.replaceAll("[\\s]+", " ") : null;
	}
}
