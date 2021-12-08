package cn.ixan.example;

import cn.ixan.example.spi.Fruit;

import java.util.ServiceLoader;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/8 下午9:02
 * @description 水果spi 测试
 */
public class FruitSPITest {
	public static void main(String[] args) {
		ServiceLoader<Fruit> loader = ServiceLoader.load(Fruit.class);
		for (Fruit fruit : loader) {
			System.out.println(fruit.getName());
		}
	}
}
