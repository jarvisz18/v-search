package cn.ixan.example;

import org.junit.Test;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/3/6 15:01
 * @description 创建String字符串的几种方式
 * @version 1.0
 */
public class StringExampleTest2 {

	@Test
	public void test_string_init() {
		String str_01 = "abc";
		System.out.println("默认方式：" + str_01);
		String str_02 = new String(new char[]{'a', 'b', 'c'});
		System.out.println("char 方式：" + str_02);
		String str_03 = new String(new int[]{0x61, 0x62, 0x63}, 0, 3);
		System.out.println("int 方式：" + str_03);
		String str_04 = new String(new byte[]{0x61, 0x62, 0x63});
		System.out.println("byte 方式：" + str_04);
	}
}
