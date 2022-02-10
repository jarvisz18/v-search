package com.ixan.boot.test.juc.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/6 下午7:05
 * @description completable test
 */
public class CompletableFutureTest01 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//异步调用，无返回值
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " CompletableFuture");
		});
		future.get();

		//异步调用，有返回值(出现异常可以获取异常信息)
		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " CompletableFuture2");
			//int a = 1 / 0;
			return 1024;
		});
		future2.whenComplete((t, u) -> {
			System.out.println("返回值:" + t);
			System.out.println("异常:" + u);
		});
	}

}
