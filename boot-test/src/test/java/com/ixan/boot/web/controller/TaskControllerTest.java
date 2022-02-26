package com.ixan.boot.web.controller;

import com.ixan.boot.BaseUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class TaskControllerTest extends BaseUnitTest {

	/**
	 * 任务处理
	 * {@link TaskController#addOrder()}
	 */
	@Test
	public void addOrder() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/order"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}