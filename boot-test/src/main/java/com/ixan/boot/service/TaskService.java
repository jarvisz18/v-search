package com.ixan.boot.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/12/7 12:00
 * @description 任务处理
 * @version 1.0
 */
public interface TaskService {
	@Async("taskExecutor")
	void doTask(int i) throws Exception;
}
