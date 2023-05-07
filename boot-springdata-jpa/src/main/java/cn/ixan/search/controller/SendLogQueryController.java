package cn.ixan.search.controller;

import cn.ixan.search.domain.SendLog;
import cn.ixan.search.domain.SendLogDTO;
import cn.ixan.search.service.SendLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/7/5 10:20
 * @description 日志
 * @version 1.0
 */
@RestController
public class SendLogQueryController {
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

	@GetMapping("/findSendLogsByType")
	public Page<SendLog> findSendLogsByType(@RequestParam(required = false) String type,
											@RequestParam(defaultValue = "0") Integer pageNo,
											@RequestParam(defaultValue = "100") Integer pageSize) {
		SendLogDTO logDto = new SendLogDTO();
		logDto.setPageNo(pageNo);
		logDto.setPageSize(pageSize);
		logDto.setType(type);
		logDto.setTemplateName("测试%");
		return sendLogService.findSendLogsByType(logDto);
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
		sendLog.setEntryDatetime(LocalDateTime.now());
		sendLogService.addLog(sendLog);
	}
}
