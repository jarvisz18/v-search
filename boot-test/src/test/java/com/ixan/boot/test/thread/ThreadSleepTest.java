package com.ixan.boot.test.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/7/4 下午9:02
 * @description 线程休眠测试
 */
public class ThreadSleepTest {
	//创建一个独占锁
	private static final Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		//创建线程A
		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				//获取独占锁
				lock.lock();
				try {
					System.out.println("child thread is in sleep");
					Thread.sleep(10000L);
					System.out.println("child thread is in awaked");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					//释放锁
					lock.unlock();
				}
			}
		});

		//创建线程B
		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				try {
					System.out.println("child threadB is in sleep");
					Thread.sleep(10000L);
					System.out.println("child threadB is in awaked");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});

		//启动线程
		threadA.start();
		threadB.start();
	}
}

