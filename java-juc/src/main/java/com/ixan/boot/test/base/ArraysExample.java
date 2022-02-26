package com.ixan.boot.test.base;

import java.util.Arrays;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/11 11:32
 * @description Arrays工具类
 * @link https://mp.weixin.qq.com/s/7-vdq0-VCJsKVFUQxtTEuw
 * @version 1.0
 */
public class ArraysExample {
	public static void main(String[] args) {
		int[] t = new int[]{1, 2, 3, 5};
		int x = Arrays.binarySearch(t, 3);
		System.out.println(x);

		assert ~x == 2;
	}
}
