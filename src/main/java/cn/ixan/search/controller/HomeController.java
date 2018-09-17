package cn.ixan.search.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class HomeController {
	/**
	 * 跳转主页
	 * @return
	 */
	@RequestMapping(value = "/main.html",method = RequestMethod.GET)
	public String toIndex(){
		return "index";
	}
}
