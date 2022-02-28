package com.ixan.boot.test.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/28 12:26
 * @description 动态代理测试
 * @version 1.0
 */
public class DynamicProxyTest {
	public static void main(String[] args) {
		dynamicProxy();
	}

	public static void dynamicProxy() {
		UserDao userDao = new UserDaoImpl();
		InvocationHandler invocationHandler = new UserHandler(userDao);

		ClassLoader classLoader = userDao.getClass().getClassLoader();
		Class<?>[] interfaces = userDao.getClass().getInterfaces();
		//从这里追踪源码
		UserDao proxy = (UserDao) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
		proxy.saveUser();
	}
}
