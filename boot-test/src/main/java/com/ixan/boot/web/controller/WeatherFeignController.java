package com.ixan.boot.web.controller;

import com.ixan.boot.service.feign.WeatherFeignService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/8/10 21:01
 * @description 测试天气服务
 */
@RestController
public class WeatherFeignController {
	@Autowired
	private WeatherFeignService weatherFeignService;

	@ApiOperation(value = "查询天气")
	@GetMapping(value = "/search")
	public ResponseEntity<byte[]> searchGithubRepoByStr() {
		String city = "beijing";
		// 您申请的appkey https://wx.jdcloud.com/market/api/10610
		String appkey = "您申请的appkey";
		return weatherFeignService.searchForecast(city, appkey);
	}
}
