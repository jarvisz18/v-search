package com.ixan.boot.web.controller.thread;

import com.ixan.boot.BaseUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserGoodsControllerTest extends BaseUnitTest {

	/**
	 * 多线程 测试秒杀iPhone13
	 * {@link UserGoodsController#shop()}
	 */
	@Test
	public void shop() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/order/shop"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		String contentAsString = mvcResult.getResponse().getContentAsString();
		System.out.println(contentAsString);
	}
}