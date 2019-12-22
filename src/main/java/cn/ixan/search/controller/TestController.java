package cn.ixan.search.controller;

import cn.ixan.search.utils.DateUtil;
import cn.ixan.search.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stack_zhang@outlook.com
 */
@RestController
@Slf4j
public class TestController {

	@GetMapping("/test")
	public String testCheckTime(){
		String uuid = UUIDUtils.uuid();
		log.info("开始时间[{}]", DateUtil.currentTime());
		log.info("结束时间[{}]",DateUtil.currentTime());
		log.info("获取到数据:[{}]",uuid);
		return uuid;

	}
}
