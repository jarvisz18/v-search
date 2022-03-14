package com.ixan.boot.test.base.gc;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/3/12 下午4:34
 * @description Jvm参数设置
 */
public class JvmParameter {
	public static void main(String[] args) {
		byte[] bytes = new byte[10 * 1024 * 1024];
		System.out.println("分配1M空间给数组");
		//执行 full gc
		System.gc();
		System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
		System.out.println("free mem=" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
		System.out.println("total mem=" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
	}
}
