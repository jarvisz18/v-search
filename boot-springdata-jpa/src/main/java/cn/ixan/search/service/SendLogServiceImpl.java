package cn.ixan.search.service;

import cn.ixan.search.dao.SendLogJpaRepository;
import cn.ixan.search.dao.SendLogRepository;
import cn.ixan.search.domain.SendLog;
import cn.ixan.search.domain.SendLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private SendLogJpaRepository sendLogJpaRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateSendLogByIdAndType() {
		sendLogRepository.updateSendLogByIdAndType(1L, "1", "发送日志模版");
	}

	@Override
	public List<SendLog> findByCondition(String template) {
		return sendLogRepository.findByCondition(template);
	}

	@Override
	public List<SendLog> findByTypeIn(List<String> typeList) {
		return sendLogRepository.findSendLogByTypeList(typeList);
	}

	@Override
	public Page<SendLog> findAll(SendLogDTO logDto, Pageable pageable) {
		return sendLogJpaRepository.findAll(logDto, pageable);
	}

	@Override
	public List<SendLog> findAll() {
		return sendLogRepository.findAll();
	}

	@Override
	public List<SendLog> findSendLogByTemplateName(String templateName) {
		return sendLogRepository.findByTempLateName(templateName);
	}

	@Override
	public void addLog(SendLog sendLog) {
		sendLogRepository.save(sendLog);
	}
}
