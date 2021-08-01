package cn.ixan.search.quartz;

import cn.ixan.search.common.quartz.HelloJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/1 下午12:54
 * @description 定时任务测试
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QuartzJobTest {

	@Test
	public void quartzTest() throws SchedulerException {
		// 1. 创建工厂类 SchedulerFactory
		SchedulerFactory factory = new StdSchedulerFactory();
		// 2. 通过 getScheduler() 方法获得 Scheduler 实例
		Scheduler scheduler = factory.getScheduler();

		// 3. 使用上文定义的 HelloJob
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				//job 的name和group
				.withIdentity("jobName", "jobGroup")
				.build();

		//3秒后启动任务
		Date startTime = new Date(System.currentTimeMillis() + 3000);

		// 4. 启动 Scheduler
		scheduler.start();

		// 5. 创建Trigger
		//使用SimpleScheduleBuilder或者CronScheduleBuilder
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("jobTriggerName", "jobTriggerGroup")
				.startNow()
				.startAt(startTime)
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")) //两秒执行一次
				.build();

		// 6. 注册任务和定时器
		scheduler.scheduleJob(jobDetail, trigger);

	}

}
