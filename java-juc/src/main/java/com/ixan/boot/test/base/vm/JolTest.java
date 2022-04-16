package com.ixan.boot.test.base.vm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/16 12:36
 * @description jol test
 * @version 1.0
 */
public class JolTest {
	public static void main(String[] args) {
		Object object = new Object();
		System.err.println(ClassLayout.parseInstance(object).toPrintable());

		synchronized (object){
			System.out.println(ClassLayout.parseInstance(object).toPrintable());
		}
	}
}
