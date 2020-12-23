package com.ixan.boot.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/1 10:04 下午
 * @description test fastjson
 */
public class FastJSONTest {

	@Test
	public void testString() {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setId(1);
		personDTO.setUsername("zhangxianlong");
		personDTO.setBirthday(new Date());
		System.out.println(JSON.toJSONString(personDTO));


	}
}
