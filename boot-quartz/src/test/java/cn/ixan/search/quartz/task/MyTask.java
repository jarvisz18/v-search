package cn.ixan.search.quartz.task;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/1 下午2:27
 * @description MyTask
 */
public class MyTask {
	public static void main(String[] args) throws Exception {

		DemoTask demoTask = new DemoTask();

		// 定义了；执行的内容
		MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MethodInvokingJobDetailFactoryBean();
		methodInvokingJobDetailFactoryBean.setTargetObject(demoTask);
		methodInvokingJobDetailFactoryBean.setTargetMethod("execute");
		methodInvokingJobDetailFactoryBean.setConcurrent(true);
		methodInvokingJobDetailFactoryBean.setName("demoTask");
		methodInvokingJobDetailFactoryBean.afterPropertiesSet();

		// 定义了；执行的计划
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(methodInvokingJobDetailFactoryBean.getObject());
		cronTriggerFactoryBean.setCronExpression("0/5 * * * * ?");
		cronTriggerFactoryBean.setName("demoTask");
		cronTriggerFactoryBean.afterPropertiesSet();

		// 实现了；执行的功能
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
		schedulerFactoryBean.setAutoStartup(true);
		schedulerFactoryBean.afterPropertiesSet();

		schedulerFactoryBean.start();

		// 暂停住
		System.in.read();

	}
}
