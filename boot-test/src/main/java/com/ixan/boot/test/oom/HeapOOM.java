package com.ixan.boot.test.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/4/5 12:55
 * @description 堆内存溢出
 * @version 1.0
 */
public class HeapOOM {
	public static void main(String[] args) throws InterruptedException {
		List<HeapOOM> list = new ArrayList<>();
		while (true) {
			list.add(new HeapOOM());
			TimeUnit.SECONDS.sleep(1L);
		}
	}
}
