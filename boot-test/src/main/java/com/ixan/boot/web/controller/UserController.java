package com.ixan.boot.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/11/14 10:12 下午
 * @description 测试表单提交
 */
@RestController
public class UserController {

	@PostMapping(value = "/addUser1", consumes = "application/json")
	public String addUser1() {
		return "success";
	}

	@PostMapping(value = "/addUser2", consumes = "application/x-www-form-urlencoded")
	public String addUser2() {
		return "success";
	}
}
