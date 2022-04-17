package com.ixan.boot.test.base.vm;

import com.ixan.boot.test.base.vm.domain.ObjectItem;
import com.ixan.boot.test.base.vm.domain.ObjectOne;
import com.ixan.boot.test.base.vm.domain.ObjectThree;
import com.ixan.boot.test.base.vm.domain.ObjectTwo;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/16 22:57
 * @description ObjectMemoryTest
 * @version 1.0
 */
public class ObjectMemoryTest {
	/**
	 * -XX:+UseCompressedOops  开启指针压缩
	 * -XX:-UseCompressedOops  关闭指针压缩
	 *
	 * java.lang.Object object internals:
	 * OFF  SZ   TYPE DESCRIPTION               VALUE
	 *   0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
	 *   8   4        (object header: class)    0xf80001e5
	 *  12   4        (object alignment gap)
	 * Instance size: 16 bytes
	 * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
	 */
	public static void main(String[] args) {
		Object object = new Object();
		System.out.println(ClassLayout.parseInstance(object).toPrintable());

		// a simple object one
		ObjectOne objectOne = new ObjectOne();
		System.out.println(ClassLayout.parseInstance(objectOne).toPrintable());

		ObjectTwo objectTwo = new ObjectTwo("Java");
		System.out.println(ClassLayout.parseInstance(objectTwo).toPrintable());

		ObjectThree objectThree = new ObjectThree();
		System.out.println(ClassLayout.parseInstance(objectThree).toPrintable());

		ObjectItem objectItem = new ObjectItem();
		System.out.println(ClassLayout.parseInstance(objectItem).toPrintable());

		int[] arr = new int[]{1, 2, 3, 4, 5};
		System.out.println(ClassLayout.parseInstance(arr).toPrintable());

		String name = "Java";
		System.out.println(ClassLayout.parseInstance(name).toPrintable());

		Integer intValue = 100;
		System.out.println(ClassLayout.parseInstance(intValue).toPrintable());
	}
}
