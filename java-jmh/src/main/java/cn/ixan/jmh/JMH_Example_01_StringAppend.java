package cn.ixan.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/2/11 13:03
 * @description JMH 简单例子
 * @link https://www.wdbyte.com/2020/08/develop/tool-jmh/#jmh-%E4%BD%BF%E7%94%A8
 * @version 1.0
 */
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class JMH_Example_01_StringAppend {
	String string = "";
	StringBuilder builder = new StringBuilder();

	@Benchmark
	public String stringAdd() {
		for (int i = 0; i < 1000; i++) {
			string = string + i;
		}
		return string;
	}

	@Benchmark
	public String stringBuilderAppend() {
		for (int i = 0; i < 1000; i++) {
			builder.append(i);
		}
		return builder.toString();
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(JMH_Example_01_StringAppend.class.getSimpleName())
				.build();
		new Runner(opt).run();
	}
}
