package com.ixan.boot.web.controller;

import com.ixan.boot.BaseUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TestControllerTest extends BaseUnitTest {
	/**
	 * 获取用户信息
	 * {@link TestController#get()}
	 */
	@Test
	public void get() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/user"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(26));
	}

	/**
	 * 获取完整的请求URL
	 * {@link TestController#getFullRequestURL(HttpServletRequest)}
	 */
	@Test
	public void getFullRequestURL() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/url"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * 获取实体
	 * {@link TestController#getEntity()}
	 */
	@Test
	public void getEntity() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getEntity"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	/**
	 * 除零异常
	 * {@link TestController#getError(Integer, Integer)}
	 */
	@Test
	public void getError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getError/1/0"))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
}