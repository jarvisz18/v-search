package com.ixan.boot.web.controller;

import com.ixan.boot.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/3 17:50
 * @description test date 序列化 sp2.x
 */
@RestController
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@GetMapping("/url")
	public void getFullRequestUri(HttpServletRequest request) {
		String requestUrl = request.getScheme() //当前链接使用的协议
				+ "://" + request.getServerName()//服务器地址
				+ ":" + request.getServerPort() //端口号
				+ request.getContextPath() //应用名称，如果应用名称为
				+ request.getServletPath() //请求的相对url
				+ "?" + request.getQueryString(); //请求参数
		logger.info("完整的请求URL:[{}]", requestUrl);
	}

	@GetMapping("/getEntity")
	public Object getEntity() {
		Account account = new Account();
		account.setVersion(1);
		account.setId(1);
		account.setUsername("zhangxianlong");
		account.setSite("www.baidu.com");
		account.setCreate_time(new Date());
		account.setUpdate_time(new Date());
		return account;
	}

	@GetMapping("/error/{a}/{b}")
	public int getError(@PathVariable Integer a,
						@PathVariable Integer b) {
		logger.info("获取到请求参数 a :[{}]", a);
		logger.info("获取到请求参数 b :[{}]", b);
		return a / b;
	}

}
