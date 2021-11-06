package com.ixan.boot.test.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/6 下午6:43
 * @description forkjoin demo
 */
public class ForkJoinTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//创建Task
		ForkCalJoinTask forkJoinTask = new ForkCalJoinTask(0, 100);
		//创建分支合并池对象
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		ForkJoinTask<Integer> submit = forkJoinPool.submit(forkJoinTask);
		//获取结果
		Integer integer = submit.get();
		System.out.println("1+2+...+100 = " + integer);
		forkJoinPool.shutdown();
	}
}
