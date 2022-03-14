package cn.ixan.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/3/5 20:47
 * @description ArrayList案例
 * @version 1.0
 */
public class ArrayListExampleTest {

	@Test(expected = IndexOutOfBoundsException.class)
	public void test_add() {
		List<String> list = new ArrayList<>(10);
		list.add(2, "zhangsan");
		System.out.println(list.get(0));
	}
}
