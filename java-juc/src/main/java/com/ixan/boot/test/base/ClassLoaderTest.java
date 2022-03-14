package com.ixan.boot.test.base;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/29 下午8:33
 * @description 类加载器测试
 */
public class ClassLoaderTest {
	public static void main(String[] args) {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		//应用类加载器
		System.out.println(contextClassLoader);
		//扩展类加载器
		System.out.println(contextClassLoader.getParent());
		//应用类加载器，也叫系统应用类加载器
		System.out.println(contextClassLoader.getParent().getParent());

	}
}
