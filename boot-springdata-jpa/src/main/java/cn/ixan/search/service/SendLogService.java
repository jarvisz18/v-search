package cn.ixan.search.service;

import cn.ixan.search.domain.SendLog;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/7/5 10:21
 * @description 日志
 * @version 1.0
 */
public interface SendLogService {
	List<SendLog> findSendLogByTemplateName(String templateName);

	void addLog(SendLog sendLog);

}
