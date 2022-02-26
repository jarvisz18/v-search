package com.ixan.boot.test.guava;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/10/4 下午3:00
 * @description 测试事件驱动
 */
public class EventBusTest {
	public static void main(String[] args) {
		UserService userService = new UserService();
		userService.regist();
	}
}
