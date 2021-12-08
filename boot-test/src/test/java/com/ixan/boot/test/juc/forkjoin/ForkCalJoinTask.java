package com.ixan.boot.test.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/6 下午6:44
 * @description 拆分的任务
 */
public class ForkCalJoinTask extends RecursiveTask<Integer> {
	private static final Integer MAX_VALUE = 10;
	private int begin;
	private int end;
	private Integer result = 0;

	ForkCalJoinTask(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	//拆分和合并过程
	@Override
	protected Integer compute() {
		//判断两个相加的数值是否大于10
		if ((end - begin) <= MAX_VALUE) {
			for (int i = begin; i <= end; i++) {
				result = result + i;
			}
		} else { //进一步拆分
			//获取中间值
			int middle = (begin + end) / 2;
			//拆分左边
			ForkCalJoinTask leftTask = new ForkCalJoinTask(begin, middle);
			//拆分右边
			ForkCalJoinTask rightTask = new ForkCalJoinTask(middle + 1, end);
			//调用方法拆分
			leftTask.fork();
			rightTask.fork();
			//合并结果
			result = leftTask.join() + rightTask.join();
		}
		return result;
	}

}
