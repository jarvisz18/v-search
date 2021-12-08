package cn.ixan.example.spi.impl;

import cn.ixan.example.spi.Fruit;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/12/8 下午8:51
 * @description 苹果
 */
public class Apple implements Fruit {
	/**
	 * @return java.lang.String
	 * @author stackzhang@126.com
	 * @date 2021/12/8 下午11:15
	 */
	@Override
	public String getName() {
		return "My Name is Apple";
	}
}
