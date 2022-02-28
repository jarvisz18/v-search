package com.ixan.boot.test.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/28 12:18
 * @description UserHandler
 * @version 1.0
 */
public class UserHandler implements InvocationHandler {
	private UserDao userDao;

	public UserHandler(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 *
	 * @param proxy 代理对象
	 * @param method 代理对象调用的方法
	 * @param args 调用方法中的三叔
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		saveUserStart();
		Object invoke = method.invoke(userDao, args);
		saveUserDone();
		return invoke;
	}

	public void saveUserStart() {
		System.out.println("----insert start----");
	}

	public void saveUserDone() {
		System.out.println("----insert done----");
	}
}
