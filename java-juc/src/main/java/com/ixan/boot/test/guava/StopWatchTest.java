package com.ixan.boot.test.guava;

import com.google.common.base.Stopwatch;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/10/20 下午10:02
 * @description stopwatch test
 */
public class StopWatchTest {
	public static void main(String[] args) {
		Random random = new Random(100);
		//创建并启动计时器
		Stopwatch stopwatch = Stopwatch.createStarted();
		callSome(random.nextInt(1000));
		stopwatch.stop();
		System.out.printf("方法执行耗时:%s ms\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
		stopwatch.reset();

		stopwatch.start();
		callSome(random.nextInt(1000));
		stopwatch.stop();
		System.out.printf("方法执行耗时:%s ms\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
		stopwatch.reset();
	}

	private static void callSome(int nextInt) {
		try {
			TimeUnit.MILLISECONDS.sleep(nextInt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
