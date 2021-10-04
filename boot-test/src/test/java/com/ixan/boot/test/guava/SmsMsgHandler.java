package com.ixan.boot.test.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/10/4 下午2:56
 * @description 短信
 */
public class SmsMsgHandler {
	@Subscribe
	public void handle(Long businessId) {
		System.out.println("send sms msg:" + businessId);
	}
}
