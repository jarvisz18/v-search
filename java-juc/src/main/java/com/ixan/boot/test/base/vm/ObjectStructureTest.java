package com.ixan.boot.test.base.vm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/3/14 下午10:24
 * @description 对象结构测试
 */
public class ObjectStructureTest {
	public static void main(String[] args) {
		Object obj = new Object();
		System.out.println(obj + " 十六进制哈希:" + Integer.toHexString(obj.hashCode()));
		System.out.println(ClassLayout.parseInstance(obj).toPrintable());
	}
}
