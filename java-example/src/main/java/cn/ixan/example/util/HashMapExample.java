package cn.ixan.example.util;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/3/1 下午10:37
 * @description HashMapExample
 */
public class HashMapExample {

	@Test
	public void test_hashmap_size() {
		HashMap<Object, Object> map = new HashMap<>(17);
		System.out.println(map.size());
	}

	@Test
	public void test_hashmap_size2() {
		int n = 17 - 1;
		n |= n >>> 1;
		System.out.println(Integer.toBinaryString(n));
	}
}
