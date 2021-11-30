package com.ixan.boot.test.base;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/29 下午8:33
 * @description 类加载器测试
 */
public class ClassLoaderTest {
	public static void main(String[] args) {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(contextClassLoader);
		System.out.println(contextClassLoader.getParent());
		System.out.println(contextClassLoader.getParent().getParent());
	}
}
