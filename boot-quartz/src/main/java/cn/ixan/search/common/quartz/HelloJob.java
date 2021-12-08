package cn.ixan.search.common.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/1 下午12:47
 * @description 例子:定义一个执行输出当前时间的任务
 * <p>
 * Quartz的基本组成部分：
 * 调度器：Scheduler
 * 任务：JobDetail
 * 触发器：Trigger，包括SimpleTrigger和CronTrigger</p>
 */
public class HelloJob implements Job {
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("Hello执行任务,当前时间: " + DATE_TIME_FORMATTER.format(LocalDateTime.now()));
	}
}
