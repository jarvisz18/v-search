package com.ixan.boot.test.juc.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/11 下午1:40
 * @description TestPark
 */
public class TestPark {
	public static void main(String[] args) {
		TestPark testPark = new TestPark();
		testPark.testPark();
	}

	public void testPark() {
		LockSupport.park(this);
	}
}
