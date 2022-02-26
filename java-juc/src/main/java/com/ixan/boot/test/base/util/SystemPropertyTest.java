package com.ixan.boot.test.base.util;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/9/28 下午7:17
 * @description 系统属性测试
 */
public class SystemPropertyTest {

	//获取系统CPU个数及内存信息
	@Test
	public void printRuntimeProperties() {
		int NCPU = Runtime.getRuntime().availableProcessors();
		System.out.println("NCPU:" + NCPU);

		System.out.println("虚拟机内存总量:" + Runtime.getRuntime().totalMemory());
		System.out.println("虚拟机空闲内存量:" + Runtime.getRuntime().freeMemory());
		System.out.println("虚拟机使用最大内存量:" + Runtime.getRuntime().maxMemory());
	}

	//获取系统常用属性
	@Test
	public void printSomeProperties() {
		System.out.println("操作系统的名称:" + System.getProperty("os.name"));
		System.out.println("操作系统的架构:" + System.getProperty("os.arch"));
		System.out.println("操作系统的版本：" + System.getProperty("os.version"));

		//文件分隔符
		System.out.println("file separator:" + System.getProperty("file.separator"));

		//用户的主目录
		System.out.println("用户的主目录:" + System.getProperty("user.home"));
		//用户的工作目录
		System.out.println("用户的当前工作目录:" + System.getProperty("user.dir"));
		//临时目录
		System.out.println("临时目录:" + System.getProperty("java.io.tmpdir"));
	}

	//获取系统所有属性
	@Test
	public void printSystemProperties() {
		Properties properties = System.getProperties();
		Set<Map.Entry<Object, Object>> entries = properties.entrySet();
		for (Map.Entry<Object, Object> entry : entries) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}
