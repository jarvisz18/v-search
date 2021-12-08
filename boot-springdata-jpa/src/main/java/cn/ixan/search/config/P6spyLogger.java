package cn.ixan.search.config;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.FormattedLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/7/5 14:49
 * @description P6spyLogger
 * @version 1.0
 */
@Slf4j
public class P6spyLogger extends FormattedLogger {
	@Override
	public void logException(Exception e) {
		log.info("", e);
	}

	@Override
	public void logText(String text) {
		log.info(text);
	}

	@Override
	public void logSQL(int connectionId, String now, long elapsed, Category category, String prepared, String sql, String url) {
		final String msg = strategy.formatMessage(connectionId, now, elapsed,
				category.toString(), prepared, sql, url);

		if (StringUtils.isEmpty(msg)) {
			return;
		}
		if (Category.ERROR.equals(category)) {
			log.error(msg);
		} else if (Category.WARN.equals(category)) {
			log.warn(msg);
		} else if (Category.DEBUG.equals(category)) {
			log.debug(msg);
		} else {
			log.info(msg);
		}
	}

	@Override
	public boolean isCategoryEnabled(Category category) {
		if (Category.ERROR.equals(category)) {
			return log.isErrorEnabled();
		} else if (Category.WARN.equals(category)) {
			return log.isWarnEnabled();
		} else if (Category.DEBUG.equals(category)) {
			return log.isDebugEnabled();
		} else {
			return log.isInfoEnabled();
		}
	}
}
