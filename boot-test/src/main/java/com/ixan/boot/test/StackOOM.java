package com.ixan.boot.test;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/4/5 13:24
 * @description 栈内存溢出
 * @version 1.0
 */
public class StackOOM {
	private int length = 1;

	public void stackTest() {
		System.out.println("stack lenght=" + length);
		length++;
		stackTest();
	}

	private void dontStop() {
		while (true) {
		}
	}

	public void stackLeakByThread() {
		while (true) {
			new Thread(this::dontStop).start();
		}
	}


	public static void main(String[] args) {
		StackOOM test = new StackOOM();
		test.stackLeakByThread();

	}
}
