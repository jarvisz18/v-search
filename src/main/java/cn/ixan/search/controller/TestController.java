package cn.ixan.search.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class TestController {
	@RequestMapping("/test")
	public void testCheckTime(@ModelAttribute String start,
	                          @ModelAttribute String end){
		log.info("开始时间[{}]",start);
		log.info("结束时间[{}]",end);

	}
}
