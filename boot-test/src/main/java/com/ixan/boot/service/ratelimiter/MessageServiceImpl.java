package com.ixan.boot.service.ratelimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author 冰河技术
 * @version 1.0
 * @date Created in 2022/2/19 下午11:00
 * @description 发送消息服务实现
 */
@Service
public class MessageServiceImpl implements MessageService {
	private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Override
	public boolean sendMessage(String message) {
		logger.info("发送消息成功===>>" + message);
		return true;
	}
}
