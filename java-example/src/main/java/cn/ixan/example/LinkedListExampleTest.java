package cn.ixan.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/3/5 21:11
 * @description LinkedList案例
 * @version 1.0
 */
public class LinkedListExampleTest {

	@Test
	public void test_init() {
		//初始化方式一
		LinkedList<String> list01 = new LinkedList<>();
		list01.add("a");
		list01.add("b");
		list01.add("c");
		System.out.println(list01);

		//初始化方式； Arrays.asList
		LinkedList<String> list02 = new LinkedList<>(Arrays.asList("a", "b", "c")
		);
		System.out.println(list02);

		//初始化方式；内部类
		LinkedList<String> list03 = new LinkedList<String>() {
			{
				add("a");
				add("b");
				add("c");
			}
		};
		System.out.println(list03);
		//初始化方式； Collections.nCopies
		LinkedList<Integer> list04 = new LinkedList<>(Collections.nCopies(10, 0)
		);
		System.out.println(list04);
	}
}
