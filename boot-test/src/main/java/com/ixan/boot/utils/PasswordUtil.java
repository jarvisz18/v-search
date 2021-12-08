package com.ixan.boot.utils;

import java.util.Random;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/10/22 17:25
 * @description PasswordUtil
 */
public class PasswordUtil {
	private final static String[] WORD = {
			"a", "b", "c", "d", "e", "f", "g",
			"h", "j", "k", "m", "n",
			"p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z",
			"A", "B", "C", "D", "E", "F", "G",
			"H", "J", "K", "M", "N",
			"P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z",
			"!", "@", "#", "$", "-", "_",
	};

	private final static String[] NUM = {
			"2", "3", "4", "5", "6", "7", "8", "9"
	};

	private static String randomPassword() {
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random(System.currentTimeMillis());
		boolean flag = false;
		//int length = random.nextInt(3) + 8;
		int length = 12;
		for (int i = 0; i < length; i++) {
			if (flag) {
				stringBuilder.append(NUM[random.nextInt(NUM.length)]);
			} else {
				stringBuilder.append(WORD[random.nextInt(WORD.length)]);
			}
			flag = !flag;
		}
		return stringBuilder.toString();
	}


	public static void main(String[] args) throws Exception {
		System.out.println(randomPassword());
		Thread.sleep(100);
		System.out.println(randomPassword());
		Thread.sleep(100);
		System.out.println(randomPassword());
	}
}
