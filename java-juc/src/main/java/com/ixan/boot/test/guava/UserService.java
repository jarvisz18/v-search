package com.ixan.boot.test.guava;

import com.google.common.eventbus.EventBus;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/10/4 下午2:57
 * @description 用户服务
 */
public class UserService {
	private static EventBus eventBus = new EventBus();

	public void regist() {
		Long userId = 1L;
		eventBus.register(new EmailMsgHandler());
		eventBus.register(new SmsMsgHandler());
		eventBus.post(userId);
	}
}
