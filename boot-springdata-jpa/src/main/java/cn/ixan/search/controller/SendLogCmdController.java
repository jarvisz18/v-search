package cn.ixan.search.controller;

import cn.ixan.search.domain.SendLog;
import cn.ixan.search.service.SendLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/7 21:07
 * @description send log cmd controller
 */
@Slf4j
@RestController
public class SendLogCmdController {
	@Resource
	private SendLogService sendLogService;

	/**
	 * 1w: one by one time spend:6936 ms
	 */
	@PostMapping("/addBatch")
	public void addBatch() {
		Random random = new Random();
		List<SendLog> sendLogList = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			SendLog sendLog = new SendLog();
			sendLog.setTemplateName("测试:" + System.currentTimeMillis());
			sendLog.setType("type_" + random.nextInt(100));
			sendLog.setEntryId(1);
			sendLog.setEntryDatetime(LocalDateTime.now());
			sendLogList.add(sendLog);
		}
		long start = System.currentTimeMillis();
		sendLogService.batchAddLog(sendLogList);
		log.info("time spend:{} ms", (System.currentTimeMillis() - start));
	}
}
