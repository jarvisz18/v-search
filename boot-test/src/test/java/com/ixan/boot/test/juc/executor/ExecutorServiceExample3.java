package com.ixan.boot.test.juc.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/8 10:11
 * @description ExecutorServiceExample 案例
 * @version 1.0
 */
public class ExecutorServiceExample3 {
	public static void main(String[] args) throws InterruptedException {
		testMyThreadPoolExecutor();
	}

	public static void testMyThreadPoolExecutor() {
		ThreadPoolExecutor poolExecutor = new MyThreadPoolExecutor(2, 2, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
				new ThreadFactoryBuilder().setNameFormat("myTask-%d").build(), new ThreadPoolExecutor.AbortPolicy());
		poolExecutor.setKeepAliveTime(10L, TimeUnit.SECONDS);
		poolExecutor.allowCoreThreadTimeOut(true);
		IntStream.range(0, 2).boxed().forEach(i -> {
			poolExecutor.execute(new MyTask() {
				@Override
				protected void doTask() {
					try {
						TimeUnit.SECONDS.sleep(1L);
						System.out.println(Thread.currentThread().getName() + " do [" + i + "]");
						if (i % 2 == 0) {
							int result = 1 / 0;
							System.out.println(result);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		});
	}

	public static void testRemove() throws InterruptedException {
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		executorService.setKeepAliveTime(10L, TimeUnit.SECONDS);
		executorService.allowCoreThreadTimeOut(true);
		IntStream.range(0, 2).boxed().forEach(i -> {
			executorService.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(5L);
					System.out.println(Thread.currentThread().getName() + " task [" + i + "] finished .");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		});
		TimeUnit.MILLISECONDS.sleep(20L);
		Runnable task = () -> {
			System.out.println("I will never be execute!");
		};
		executorService.execute(task);
		TimeUnit.MILLISECONDS.sleep(20L);
		executorService.remove(task);
	}

	/**
	 * 测试核心线程超时退出
	 * 仅适用线程不重复适用的场景
	 */
	public static void testAllowCoreThreadTimeOut() {
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		executorService.setKeepAliveTime(3L, TimeUnit.SECONDS);
		executorService.allowCoreThreadTimeOut(true);
		IntStream.range(0, 5).boxed().forEach(i -> {
			executorService.execute(() -> {
				try {
					TimeUnit.SECONDS.sleep(5L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		});
	}

	public static void test() throws InterruptedException {
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
		executorService.execute(() -> {
			try {
				TimeUnit.SECONDS.sleep(5L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		TimeUnit.MILLISECONDS.sleep(20L);
		System.out.println(executorService.getActiveCount());
	}

	private static abstract class MyTask implements Runnable {

		@Override
		public void run() {
			doTask();
		}

		protected abstract void doTask();
	}

	/**
	 * 自定义线程池继承自{@link ThreadPoolExecutor}
	 * 重写两个方法{@link ThreadPoolExecutor#beforeExecute(Thread, Runnable)}
	 * {@link ThreadPoolExecutor#afterExecute(Runnable, Throwable)}
	 */
	private static class MyThreadPoolExecutor extends ThreadPoolExecutor {

		public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
		}

		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			System.out.println(t.getName() + ",i will be execute before task Execute");
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			if (null == t) {
				System.out.println("i will be execute after task Execute, is ok");
			} else {
				System.out.println("i will be execute after task Execute , task has error");
				throw new RuntimeException(t);
			}
		}
	}
}
