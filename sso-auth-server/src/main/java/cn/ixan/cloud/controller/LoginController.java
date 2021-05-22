package cn.ixan.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/5/8 下午10:23
 * @description LoginController
 */
@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
