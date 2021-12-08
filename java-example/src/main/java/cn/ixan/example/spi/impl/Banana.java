package cn.ixan.example.spi.impl;

import cn.ixan.example.spi.Fruit;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/12/8 下午8:51
 * @description 香蕉
 */
public class Banana implements Fruit {
	@Override
	public String getName() {
		return "My Name is Banana";
	}
}
