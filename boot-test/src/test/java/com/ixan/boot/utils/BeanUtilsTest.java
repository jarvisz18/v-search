package com.ixan.boot.utils;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/3/16 下午7:08
 * @description 测试BeanUtils是深拷贝还是浅拷贝
 */
public class BeanUtilsTest {

	/**
	 * 张翠山和张无忌应该有各自的生活，应该互不影响
	 */
	@Test
	public void test4() {
		Father father = new Father();
		father.setFace("handsome");
		father.setHeight("180");
		Life cuishanLife = new Life();
		cuishanLife.setStatus("alive");
		father.setLife(cuishanLife);

		Son wuji = new Son();
		BeanUtils.copyProperties(father, wuji);

		//张翠山自刎
		father.getLife().setStatus("dead");

		//张无忌活着 导致 张翠山也活了？
		Life life = new Life();
		life.setStatus("alive");
		wuji.setLife(life);

		System.out.println(JSON.toJSONString(father));
		System.out.println(JSON.toJSONString(wuji));
	}

	//通过下面三个例子 可以证明BeanUtils是浅拷贝
	@Test
	public void test3() {
		Father father = new Father();
		father.setFace("handsome");
		father.setHeight("180");
		Life cuishanLife = new Life();
		cuishanLife.setStatus("alive");
		father.setLife(cuishanLife);

		Son wuji = new Son();
		BeanUtils.copyProperties(father, wuji);

		//张翠山自刎
		father.getLife().setStatus("dead");

		//张无忌活着 导致 张翠山也活了？
		wuji.getLife().setStatus("alive");

		System.out.println(JSON.toJSONString(father));
		System.out.println(JSON.toJSONString(wuji));
	}

	@Test
	public void test2() {
		Father father = new Father();
		father.setFace("handsome");
		father.setHeight("180");
		Life cuishanLife = new Life();
		cuishanLife.setStatus("alive");
		father.setLife(cuishanLife);

		Son wuji = new Son();
		BeanUtils.copyProperties(father, wuji);

		//张翠山自刎
		father.getLife().setStatus("dead");

		System.out.println(JSON.toJSONString(father));
		System.out.println(JSON.toJSONString(wuji));
	}

	@Test
	public void test1() {
		Father father = new Father();
		father.setFace("handsome");
		father.setHeight("180");
		Life cuishanLife = new Life();
		cuishanLife.setStatus("alive");
		father.setLife(cuishanLife);

		Son wuji = new Son();
		BeanUtils.copyProperties(father, wuji);

		System.out.println(JSON.toJSONString(father));
		System.out.println(JSON.toJSONString(wuji));
	}


	@Data
	public static class Son extends Father {
		private Life life;
	}

	@Data
	public static class Father {
		private String face;
		private String height;
		private Life life;
	}

	@Data
	public static class Life {
		private String status;
	}
}
