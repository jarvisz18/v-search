package cn.ixan.search.controller;

import cn.ixan.search.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/syncUserInfo2ES")
	public boolean syncUserInfo2ES(){
		return userService.syncUserInfo2ES();
	}
}
