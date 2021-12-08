package com.ixan.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/12/7 12:00
 * @description 任务处理
 * @version 1.0
 */
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

	@Async("taskExecutor")
	@Override
	public void doTask(int i) throws Exception {
		log.info("开始做任务,当前线程:[{}]", Thread.currentThread().getName());
		long start = System.currentTimeMillis();
		//这里写业务代码
		Thread.sleep(TimeUnit.SECONDS.toSeconds(1000 * 5));
		long end = System.currentTimeMillis();
		log.info("完成任务耗时：[{}]秒", ((end - start) / 1000));

	}
}
