package com.ixan.boot.web.controller;

import com.ixan.boot.BaseUnitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserGenderControllerTest extends BaseUnitTest {
	/**
	 * 批量插入用户
	 * ID生成使用雪花算法<li/>
	 * 使用mybatis foreach 批量插入<li/>
	 * {@link UserGenderController#initUserGender()}
	 */
	@Test
	@Transactional
	@Rollback
	public void initUserGender() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/initUserGender")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}