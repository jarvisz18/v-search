package cn.ixan.jmh;

import cn.ixan.jmh.utils.TimeUtil_Sentinel;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/11 13:18
 * @description JMH测试获取时间戳
 * @link https://mp.weixin.qq.com/s/hs11xUNF7Vh-zO6zeaXXmw
 * @version 1.0
 */
@State(Scope.Benchmark)
public class JMH_Example_04_TimeStampTest {
	private static final int MAX = 10_000_000;

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(JMH_Example_04_TimeStampTest.class.getSimpleName())
				.forks(1)
				.warmupIterations(1)
				.measurementIterations(1)
				.warmupTime(TimeValue.seconds(5))
				.measurementTime(TimeValue.seconds(5))
				.mode(Mode.AverageTime)
				.syncIterations(false)
				.build();
		new Runner(opt).run();
	}

	@Benchmark
	@Threads(1)
	public void test1Thread() {
		for (int i = 0; i < MAX; i++) {
			TimeUtil_Sentinel.currentTimeMillis();
		}
	}

	@Benchmark
	@Threads(2)
	public void test2Thread() {
		for (int i = 0; i < MAX; i++) {
			TimeUtil_Sentinel.currentTimeMillis();
		}
	}

	@Benchmark
	@Threads(4)
	public void test4Thread() {
		for (int i = 0; i < MAX; i++) {
			TimeUtil_Sentinel.currentTimeMillis();
		}
	}

	@Benchmark
	@Threads(8)
	public void test8Thread() {
		for (int i = 0; i < MAX; i++) {
			TimeUtil_Sentinel.currentTimeMillis();
		}
	}

	@Benchmark
	@Threads(16)
	public void test16Thread() {
		for (int i = 0; i < MAX; i++) {
			TimeUtil_Sentinel.currentTimeMillis();
		}
	}

	@Benchmark
	@Threads(32)
	public void test32Thread() {
		for (int i = 0; i < MAX; i++) {
			TimeUtil_Sentinel.currentTimeMillis();
		}
	}

	@Benchmark
	@Threads(64)
	public void test64Thread() {
		for (int i = 0; i < MAX; i++) {
			TimeUtil_Sentinel.currentTimeMillis();
		}
	}

	@Benchmark
	@Threads(128)
	public void test128Thread() {
		for (int i = 0; i < MAX; i++) {
			TimeUtil_Sentinel.currentTimeMillis();
		}
	}

	private long currentTimeMills() {
		return System.currentTimeMillis();
	}

}
