package cn.ixan.search.service;

import cn.ixan.search.dao.SendLogRepository;
import cn.ixan.search.domain.SendLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/7/5 10:21
 * @description 日志
 * @version 1.0
 */
@Service
public class SendLogServiceImpl implements SendLogService {
	@Autowired
	private SendLogRepository sendLogRepository;

	@Override
	public List<SendLog> findSendLogByTemplateName(String templateName) {
		return sendLogRepository.findByTempLateName(templateName);
	}

	@Override
	public void addLog(SendLog sendLog) {
		sendLogRepository.save(sendLog);
	}
}
