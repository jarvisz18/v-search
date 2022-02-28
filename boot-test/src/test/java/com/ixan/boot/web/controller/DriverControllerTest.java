package com.ixan.boot.web.controller;

import com.alibaba.fastjson.JSON;
import com.ixan.boot.BaseUnitTest;
import com.ixan.boot.config.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class DriverControllerTest extends BaseUnitTest {
	/**
	 * 驱动添加事件
	 * {@link DriverController#addEvent(String, Integer)}
	 */
	@Test
	public void addEvent() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/event/add")
				.param("id", "1")
				.param("op", "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Result result = JSON.parseObject(content, Result.class);
		Assert.assertEquals("200", result.getCode());
	}
}