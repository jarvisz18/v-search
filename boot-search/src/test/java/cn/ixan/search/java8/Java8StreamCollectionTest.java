package cn.ixan.search.java8;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/8/21 下午2:08
 * @description java8 stream流编程案例
 */
public class Java8StreamCollectionTest {

	@Test
	public void testStreamCollection_1() {
		// String数组转List<Integer>
		String[] aa = {"1", "2"};
		List<Integer> collect = Stream.of(aa).map(Integer::parseInt).collect(Collectors.toList());
		Assert.assertEquals(collect.get(0).intValue(), 1);
	}

	@Test
	public void testListPartition() {
		//[1,2,3,4,5,6,7] -> ["123","456","7"]
		List<Integer> integer = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		List<List<Integer>> partition = Lists.partition(integer, 3);

		List<String> collect = partition.stream()
				.map(e -> e.stream().map(String::valueOf).collect(Collectors.toList()))
				.map(e -> String.join("", e))
				.collect(Collectors.toList());
		Assert.assertEquals(collect.get(0), "123");

	}


}
