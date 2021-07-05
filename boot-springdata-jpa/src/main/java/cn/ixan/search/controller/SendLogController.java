package cn.ixan.search.controller;

import cn.ixan.search.domain.SendLog;
import cn.ixan.search.domain.SendLogDTO;
import cn.ixan.search.service.SendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

	@PostMapping("/updateSendLogByIdAndType")
	public String updateSendLogByIdAndType() {
		sendLogService.updateSendLogByIdAndType();
		return "ok";
	}

	@PostMapping("/findByCondition")
	public List<SendLog> findByCondition(@RequestBody Map<String, String> paramMap) {
		String template = paramMap.get("template");
		return sendLogService.findByCondition(template);
	}

	@PostMapping("/findByTypeIn")
	public List<SendLog> findByTypeIn() {
		List<String> typeList = Arrays.asList("1", "4", "5");
		return sendLogService.findByTypeIn(typeList);
	}

	@PostMapping("/findAll")
	public Page<SendLog> findAll() {
		SendLogDTO logDto = new SendLogDTO();
		logDto.setTemplateName("测试%");
		Pageable pageable = PageRequest.of(0, 10);
		return sendLogService.findAll(logDto, pageable);
	}

	@PostMapping("/findSendLog")
	public List<SendLog> findSendLogByTemplateName(@RequestBody Map<String, String> paramMap) {
		String templateName = paramMap.get("templateName");
		return sendLogService.findSendLogByTemplateName(templateName);
	}

	@PostMapping("/addLog")
	public void addLog(@RequestBody Map<String, String> paramMap) {
		String type = paramMap.get("type");
		SendLog sendLog = new SendLog();
		sendLog.setTemplateName("测试:" + System.currentTimeMillis());
		sendLog.setType(type);
		sendLogService.addLog(sendLog);
	}
}
