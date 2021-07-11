package cn.ixan.search.hutool;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class ExecutorHelperTest {
	private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

	@Test
	public void buildWriteTaskPool() throws InterruptedException {
		ExecutorHelper helper = new ExecutorHelper();
		ExecutorService taskPool = helper.buildWriteTaskPool();

		taskPool.submit(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("线程:" + Thread.currentThread().getName() + "执行task!");
					Thread.sleep(1000L);
					System.out.println("线程:" + Thread.currentThread().getName() + "执行完毕");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
			}
		});

		taskPool.submit(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("线程:" + Thread.currentThread().getName() + "执行task!");
					Thread.sleep(1000L);
					System.out.println("线程:" + Thread.currentThread().getName() + "执行完毕");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();
				}
			}
		});
		System.out.println("等待子线程执行完毕");
		countDownLatch.await();
		System.out.println("子线程执行完毕");

		//关闭线程池
		taskPool.shutdown();

	}
}