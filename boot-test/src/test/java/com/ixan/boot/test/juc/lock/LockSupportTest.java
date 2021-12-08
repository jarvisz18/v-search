package com.ixan.boot.test.juc.lock;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/11 下午1:06
 * @description LockSupportTest
 */
public class LockSupportTest {

	@Test
	public void testLockSupportParkMethod() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("child thread begin park!");
				while (!Thread.currentThread().isInterrupted()) {
					LockSupport.park();
				}
				System.out.println("child thread end park!");
			}
		});
		//启动子线程
		thread.start();

		//主线程休眠1s
		Thread.sleep(1000L);
		System.out.println("main thread begin unpark!");

		//LockSupport.unpark(thread);
	}

	@Test
	public void testUnParkMethod() {
		System.out.println("begin park!");
		//使当前线程获得许可证
		LockSupport.unpark(Thread.currentThread());

		//再次调用park方法
		LockSupport.park();
		System.out.println("end park!");
	}

	@Test
	public void testParkMethod() {
		System.out.println("begin park!");
		LockSupport.park();
		System.out.println("end park!");
	}
}
