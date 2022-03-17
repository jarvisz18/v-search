package com.ixan.boot.test.base.util.serial;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/3/17 10:53
 * @description 测试用户的序列化和反序列化
 * @version 1.0
 */
public class User1 implements Serializable {
	private String name;
	private transient int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("name", name)
				.append("age", age)
				.toString();
	}
}
