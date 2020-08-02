package com.ixan.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/8/2 10:45
 * @description 验证一些想法的控制器
 */
@RestController
@Slf4j
public class TestController {

	@GetMapping("/url")
	public void getFullRequestUri(HttpServletRequest request) {
		String requestUrl = request.getScheme() //当前链接使用的协议
				+ "://" + request.getServerName()//服务器地址
				+ ":" + request.getServerPort() //端口号
				+ request.getContextPath() //应用名称，如果应用名称为
				+ request.getServletPath() //请求的相对url
				+ "?" + request.getQueryString(); //请求参数
		log.info("完整的请求URL:[{}]", requestUrl);
	}
}
