package com.ixan.boot.test.base;

import org.junit.Test;

import java.util.BitSet;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/11 11:37
 * @description 二进制数组操作
 * @link https://mp.weixin.qq.com/s/7-vdq0-VCJsKVFUQxtTEuw
 * @version 1.0
 */
public class BitSetTest {

	@Test
	public void testBitSetXor() {
		BitSet bitSet1 = new BitSet();
		bitSet1.set(0);
		bitSet1.set(2);
		bitSet1.set(4);
		System.out.println("bitSet1:" + bitSet1);

		BitSet bitSet2 = new BitSet();
		bitSet2.set(1);
		bitSet2.set(2);
		bitSet2.set(3);
		System.out.println("bitSet2:" + bitSet2);

		bitSet2.xor(bitSet1);
		System.out.println("xor: " + bitSet2);
	}
}
