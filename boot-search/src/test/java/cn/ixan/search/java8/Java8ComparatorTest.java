package cn.ixan.search.java8;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/12/10 13:54
 * @description java8 compare test
 */
public class Java8ComparatorTest {
	@Test
	public void test_integer_desc(){
		List<Integer> numberList = Lists.newArrayList(1,3,4,7,5,8,2,9,6);
		// 倒序排序
		numberList = numberList.stream().sorted(Comparator.comparingInt(Integer::intValue).reversed())
				.collect(Collectors.toList());
		System.out.println(numberList);
	}

	@Test
	public void test_integer_desc_and_null_first(){
		List<Integer> numberList = Lists.newArrayList(1,3,4,7,5,8,2,9,6,null);
		// 倒序排序
		numberList = numberList.stream().sorted(Comparator.nullsFirst(Comparator.comparingInt(Integer::intValue)).reversed())
				.collect(Collectors.toList());
		System.out.println(numberList);
	}

	@Test
	public void test_integer_asc(){
		List<Integer> numberList = Lists.newArrayList(1,3,4,7,5,8,2,9,6);
		// 正序排序
		List<Integer> sortNumberList = numberList.stream().sorted(Comparator.comparingInt(Integer::intValue))
				.collect(Collectors.toList());
		System.out.println(sortNumberList);

		// 正序排序2
		sortNumberList = numberList.stream().sorted()
				.collect(Collectors.toList());
		System.out.println(sortNumberList);
	}
}
