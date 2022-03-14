package com.ixan.boot.test.base.gc;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/3/12 下午6:06
 * @description w
 */
public class WeakReferce {
	public static void main(String[] args) {
		String test_strong_reference = "test strong reference";
		String test = test_strong_reference;
		test_strong_reference = null;
		System.gc();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("test_strong_reference - > " + test_strong_reference);
		System.out.println("test - > " + test);
	}
}
