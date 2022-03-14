package cn.ixan.example;

import org.junit.Test;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/3/6 14:50
 * @description 关于String的案例测试
 * @version 1.0
 */
public class StringExampleTest {

	@Test
	public void test_string_append() {
		long startTime = System.currentTimeMillis();
		String str = "";
		for (int i = 0; i < 100_000; i++) {
			str += i;
		}
		System.out.println("String 耗时ms:" + (System.currentTimeMillis() - startTime));
	}

	@Test
	public void test_stringBuilder_append() {
		long startTime = System.currentTimeMillis();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 100_000; i++) {
			str.append(i);
		}
		System.out.println("StringBuilder 耗时ms:" + (System.currentTimeMillis() - startTime));
	}

	@Test
	public void test_stringBuffer_append() {
		long startTime = System.currentTimeMillis();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 100_000; i++) {
			str.append(i);
		}
		System.out.println("StringBuffer 耗时ms:" + (System.currentTimeMillis() - startTime));
	}
}
