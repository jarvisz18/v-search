package cn.ixan.search.controller;

import cn.ixan.search.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author stack_zhang@outlook.com
 */
@Controller
@Slf4j
public class TestController {
	@GetMapping("/test")
	public String testCheckTime(@ModelAttribute String start,
								@ModelAttribute String end){
		String uuid = UUIDUtils.uuid();
		log.info("开始时间[{}]",start);
		log.info("结束时间[{}]",end);
		log.info("获取到数据:[{}]",uuid);
		return uuid;

	}
}
