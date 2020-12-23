package com.ixan.boot.utils;

import java.util.Date;
import java.util.Random;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/22 17:25
 * @description PasswordUtil
 * @version 1.0
 */
public class PasswordUtil {
	public final static String[] word = {
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

	public final static String[] num = {
			"2", "3", "4", "5", "6", "7", "8", "9"
	};

	public static String randomPassword() {
		StringBuilder stringBuilder = new StringBuilder();
		Random random = new Random(new Date().getTime());
		boolean flag = false;
		//int length = random.nextInt(3) + 8;
		int length = 12;
		for (int i = 0; i < length; i++) {
			if (flag) {
				stringBuilder.append(num[random.nextInt(num.length)]);
			} else {
				stringBuilder.append(word[random.nextInt(word.length)]);
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
