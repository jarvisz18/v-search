package com.ixan.boot.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/12 下午7:13
 * @description SpringContext工具类
 */
public class SpringContextUtils {
	private static ConfigurableApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = (ConfigurableApplicationContext) context;
	}

	/**
	 * 获取ApplicationContext对象
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 停止应用程序
	 */
	public static void close() {
		if (applicationContext != null) {
			applicationContext.close();
		}
	}

	/**
	 * 根据bean的名称获取bean
	 */
	public static Object getBeanByName(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 根据bean的class来查找对象
	 */
	public static <T> T getBeanByClass(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}

	/**
	 * 根据bean的class来查找所有的对象（包括子类）
	 */
	public static <T> Map<String, T> getBeansByClass(Class<T> c) {
		return applicationContext.getBeansOfType(c);
	}
}
