package cn.ixan.search.hutool;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/11 下午11:02
 * @description 线程池构建
 */
public class ExecutorHelper {

	public ExecutorService buildWriteTaskPool() {
		ThreadPoolExecutor executor = ExecutorBuilder.create()
				//核心线程数
				.setCorePoolSize(2)
				//最大线程数
				.setMaxPoolSize(5 << 1)
				//阻塞队列
				.setWorkQueue(new LinkedBlockingQueue<>(500))
				//线程存活时间
				.setKeepAliveTime(30, TimeUnit.SECONDS)
				//线程池拒绝策略
				.setHandler(new ThreadPoolExecutor.AbortPolicy())
				//线程工厂
				.setThreadFactory(ThreadFactoryBuilder.create().setNamePrefix("write-task").build())
				.build();
		return executor;
	}


}
