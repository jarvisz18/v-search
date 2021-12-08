package cn.ixan.search.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/1 下午2:26
 * @description DemoTask
 */
public class DemoTask {
	private Logger logger = LoggerFactory.getLogger(DemoTask.class);

	public void execute() throws Exception {
		logger.info("定时处理用户信息任务：0/5 * * * * ?");
	}
}
