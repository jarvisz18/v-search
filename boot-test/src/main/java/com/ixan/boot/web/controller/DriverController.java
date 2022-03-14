package com.ixan.boot.web.controller;

import com.ixan.boot.config.Result;
import com.ixan.boot.config.ResultGenerate;
import com.ixan.boot.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/9/16 下午9:21
 * @description 驱动控制器
 */
@RestController
public class DriverController {

	@Autowired
	private DriverService driverService;

	@GetMapping("/event/add")
	public Result<String> addEvent(@RequestParam String id, @RequestParam Integer op) {
		driverService.change(id, op);
		return ResultGenerate.success("ok");
	}
}
