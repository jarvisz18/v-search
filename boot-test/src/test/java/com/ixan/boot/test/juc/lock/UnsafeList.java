package com.ixan.boot.test.juc.lock;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/6 下午3:44
 * @description 不安全的线程集合与安全的线程集合
 */
public class UnsafeList {
	private List<String> list;

	private UnsafeList() {
	}

	/**
	 * ArrayList集合线程不安全：
	 * 解决方案：
	 * 1.使用Vector
	 * 2.使用Collections
	 * 3.使用CopyOnWriteArrayList
	 * <p>
	 * HashSet线程不安全
	 * 解决方案：
	 * 使用 CopyOnWriteArraySet
	 * <p>
	 * HashMap线程不安全
	 * 解决方案:
	 * 使用 ConcurrentHashMap
	 */
	public static void main(String[] args) {
		UnsafeList unsafeList = new UnsafeList();
		//unsafeList.list = new ArrayList<>();
		//unsafeList.list = new Vector<>();
		unsafeList.list = new CopyOnWriteArrayList<>();
		unsafeList.printList();
	}


	private void printList() {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int k = 0; k < 10; k++) {
					list.add(UUID.randomUUID().toString().substring(8));
				}
				System.out.println("name:" + Thread.currentThread() + "list:" + list);
			}, "thread-" + i).start();
		}
	}
}
