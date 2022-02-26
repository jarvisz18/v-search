package com.ixan.boot.web.controller;

import com.ixan.boot.BaseUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 测试用户表单提交
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserControllerTest extends BaseUnitTest {
	/**
	 * 添加用户
	 * {@link UserController#addUser1()}
	 */
	@Test
	public void addUser1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/addUser1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	/**
	 * 添加用户
	 * {@link UserController#addUser2()}
	 */
	@Test
	public void addUser2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/addUser2").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}