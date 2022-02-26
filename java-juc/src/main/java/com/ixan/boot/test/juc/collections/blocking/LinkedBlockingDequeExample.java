package com.ixan.boot.test.juc.collections.blocking;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/10 16:10
 * @description 双端队列
 * @version 1.0
 */
public class LinkedBlockingDequeExample {
	public static <T> LinkedBlockingDeque<T> create() {
		return new LinkedBlockingDeque<>();
	}
}
