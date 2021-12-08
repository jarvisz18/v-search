package com.ixan.boot.test.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/10/4 下午2:53
 * @description 邮件
 */
public class EmailMsgHandler {

	@Subscribe
	public void handle(Long businessId) {
		System.out.println("send email msg:" + businessId);
	}
}
