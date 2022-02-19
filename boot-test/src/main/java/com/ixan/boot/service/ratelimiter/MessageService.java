package com.ixan.boot.service.ratelimiter;

/**
 * @author 冰河技术
 * @version 1.0
 * @date Created in 2022/2/19 下午10:59
 * @description 模拟消息发送服务
 */
public interface MessageService {
	boolean sendMessage(String message);
}
