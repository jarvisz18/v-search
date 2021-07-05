package cn.ixan.search.controller;

import cn.ixan.search.domain.SendLog;
import cn.ixan.search.service.SendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/7/5 10:20
 * @description 日志
 * @version 1.0
 */
@RestController
public class SendLogController {
	@Autowired
	private SendLogService sendLogService;

	@PostMapping("/findSendLog")
	public List<SendLog> findSendLogByTemplateName(@RequestBody Map<String, String> paramMap) {
		String templateName = paramMap.get("templateName");
		return sendLogService.findSendLogByTemplateName(templateName);
	}

	@PostMapping("/addLog")
	public void addLog() {
		SendLog sendLog = new SendLog();
		sendLog.setTemplateName("测试:" + System.currentTimeMillis());
		sendLogService.addLog(sendLog);
	}
}
