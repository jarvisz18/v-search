package com.ixan.boot.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/5/7 下午3:29
 * @description 解决方案
 */
public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.reverseStringI("i am a student"));
	}

	/**
	 * 给定字符类型的数组chas，请在单词间做逆序调整。只要做到单词的顺序逆序即可，对空格的位置没有要求。<br/>
	 * 输入<br/>
	 * "i am a student"<br/>
	 * 输出<br/>
	 * "i ma a tneduts"<br/>
	 *
	 * @param str 原始字符串
	 * @return String
	 */
	public String reverseStringI(String str) {
		String[] s = str.split(" ");
		List<String> list = new ArrayList<>();
		for (String string : s) {
			StringBuilder builder = new StringBuilder();
			char[] chars = string.toCharArray();
			for (int i = chars.length - 1; i >= 0; i--) {
				char ch = chars[i];
				builder.append(ch);
			}
			list.add(builder.toString());
		}
		return String.join(" ", list);
	}
}
