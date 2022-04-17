package cn.ixan.jmh;

import cn.ixan.jmh.domain.Car;
import cn.ixan.jmh.domain.Dog;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/17 11:56
 * @description JMH测试构造器快还是set方法build对象快
 * @version 1.0
 */
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class JMH_Example_05_FastConstructOrOtherTest {

	@Benchmark
	public void construct() {
		for (int i = 0; i < 10000; i++) {
			Car car = new Car("奥迪", new BigDecimal(100), new ArrayList<>(Collections.singletonList(new Dog("aaa", true, true))));
		}
	}

	@Benchmark
	public void set() {
		for (int i = 0; i < 10000; i++) {
			Car car = new Car();
			car.setSign("奥迪");
			car.setPrice(new BigDecimal(100));
			Dog dog = new Dog();
			dog.setName("aaa");
			dog.setAlive(true);
			dog.setLoyal(true);
			car.setMyDog(new ArrayList<>(Collections.singletonList(dog)));
		}
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(JMH_Example_05_FastConstructOrOtherTest.class.getSimpleName())
				.build();
		new Runner(opt).run();
	}
}
