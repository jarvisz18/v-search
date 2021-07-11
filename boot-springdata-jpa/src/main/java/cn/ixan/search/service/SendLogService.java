package cn.ixan.search.service;

import cn.ixan.search.domain.SendLog;
import cn.ixan.search.domain.SendLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/7/5 10:21
 * @description 日志
 * @version 1.0
 */
public interface SendLogService {
	void updateSendLogByIdAndType();

	List<SendLog> findByCondition(String template);

	List<SendLog> findByTypeIn(List<String> typeList);

	Page<SendLog> findAll(SendLogDTO logDto, Pageable pageable);

	List<SendLog> findAll();

	List<SendLog> findSendLogByTemplateName(String templateName);

	void addLog(SendLog sendLog);

}
