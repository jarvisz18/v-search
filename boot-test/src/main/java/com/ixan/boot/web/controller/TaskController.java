package com.ixan.boot.web.controller;

import com.ixan.boot.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/12/7 11:56
 * @description 任务处理
 * @version 1.0
 */
@RestController
@Slf4j
public class TaskController {
	@Autowired
	private TaskService taskService;

	@PostMapping("/order")
	public String addOrder() throws Exception {
		//这里会执行你开启的任务，都是异步的,调用这个接口会立马返回 OK  然后业务是在后台运行的
		for (int i = 0; i < 10000; i++) {
			log.info("开启第[{}]个任务", i);
			taskService.doTask(i);
		}
		return "OK";
	}
}
