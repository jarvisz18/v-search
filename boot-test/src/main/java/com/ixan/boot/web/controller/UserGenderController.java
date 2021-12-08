package com.ixan.boot.web.controller;

import com.ixan.boot.domain.UserGender;
import com.ixan.boot.mapper.UserGenderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/11/11 11:09
 * @description UserGender Controller
 * @version 1.0
 */
@RestController
@Slf4j
public class UserGenderController {
	@Resource
	private UserGenderMapper userGenderMapper;


	@GetMapping("/initUserGender")
	public String initUserGender() {
		long start = System.currentTimeMillis();
		for (int i = 10000; i <= 1000030; i++) {
			String serNum = String.valueOf(i);
			UserGender userGender = new UserGender();
			userGender.setUserId(serNum);
			userGender.setUserName("student" + "-" + serNum);
			userGender.setUserGender("0");
			userGenderMapper.insert(userGender);
		}
		long end = System.currentTimeMillis();
		log.info("运行结束,耗时:[{}]ms", (end - start));
		return "ok";
	}

}
