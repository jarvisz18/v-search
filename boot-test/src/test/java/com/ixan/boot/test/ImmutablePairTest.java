package com.ixan.boot.test;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/7/15 22:58
 * @description an example of ImmutablePair Test
 */
public class ImmutablePairTest {
	public static void main(String[] args) {
		ImmutablePair<Integer, String> pair = ImmutablePair.of(10086, "中国移动");
		System.out.println(pair.getLeft() + "," + pair.getRight());

		ImmutableTriple<Integer, String, String> immutableTriple = ImmutableTriple.of(1086, "中国移动", "China Mobile");
		System.out.println(immutableTriple.getMiddle() + ":" + immutableTriple.getRight());
	}
}
