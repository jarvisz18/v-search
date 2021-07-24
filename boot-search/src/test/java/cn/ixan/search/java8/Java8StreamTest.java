package cn.ixan.search.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/24 下午11:01
 * @description stream流测试
 */
public class Java8StreamTest {

	@Test
	public void test1() {
		List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
		Map<String, Long> collect = items.stream().collect(
				Collectors.groupingBy(Function.identity(), Collectors.counting())
		);
		System.out.println(collect);
	}
}
