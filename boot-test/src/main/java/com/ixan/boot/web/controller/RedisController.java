package com.ixan.boot.web.controller;

import com.ixan.boot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/11/14 10:11 上午
 * @description redis 控制器
 */
@RestController
public class RedisController {
	@Autowired
	private RedisService redisService;

	@GetMapping("/testTransaction")
	public String test() {
		redisService.testTransaction();
		return "ok";
	}
}
