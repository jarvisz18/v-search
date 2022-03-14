package com.ixan.boot.web.controller;

import com.ixan.boot.BaseUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class GoodsControllerTest extends BaseUnitTest {
	/**
	 * 游标查询商品
	 * {@link GoodsController#listGoods()}
	 */
	@Test
	public void listGoods() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/goods/list")
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}