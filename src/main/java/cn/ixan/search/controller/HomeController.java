package cn.ixan.search.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author stack_zhang@outlook.com
 */
@Controller
@Slf4j
public class HomeController {
	/**
	 * 跳转主页
	 * @return
	 */
	@GetMapping(value = "/main.html")
	public String toIndex(){
		return "index";
	}
}
