package com.ixan.boot.web.controller;

import com.ixan.boot.utils.DateTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/12/7 10:11
 * @description 监控后台线程情况
 * @version 1.0
 */
@RestController
@Slf4j
public class WatchDogController {
	@Resource(name = "taskExecutor")
	private Executor taskExecutor;


	@GetMapping("/threadTask/monitor")
	public Map<String, Object> threadTaskMonitor() {
		Map<String, Object> map = new HashMap<>();
		Object[] myThread = {taskExecutor};
		for (Object thread : myThread) {
			ThreadPoolTaskExecutor threadTask = (ThreadPoolTaskExecutor) thread;
			ThreadPoolExecutor threadPoolExecutor = threadTask.getThreadPoolExecutor();
			log.info("提交任务数:[{}],完成任务数:[{}],当前有:[{}]个线程正在处理任务,还剩:[{}]个任务", threadPoolExecutor.getTaskCount(), threadPoolExecutor.getCompletedTaskCount(),
					threadPoolExecutor.getActiveCount(), threadPoolExecutor.getQueue().size());

			map.put("提交任务数-->", threadPoolExecutor.getTaskCount());
			map.put("完成任务数-->", threadPoolExecutor.getCompletedTaskCount());
			map.put("当前有多少线程正在处理任务-->", threadPoolExecutor.getActiveCount());
			map.put("还剩多少个任务未执行-->", threadPoolExecutor.getQueue().size());
			map.put("当前可用队列长度-->", threadPoolExecutor.getQueue().remainingCapacity());
			map.put("当前时间-->", DateTool.getCurrentDateTime());
		}
		return map;
	}
}
