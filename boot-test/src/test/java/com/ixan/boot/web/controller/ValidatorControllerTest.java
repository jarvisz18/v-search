package com.ixan.boot.web.controller;

import com.alibaba.fastjson.JSON;
import com.ixan.boot.BaseUnitTest;
import com.ixan.boot.domain.dto.ContractDTO;
import com.ixan.boot.domain.dto.EmplDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ValidatorControllerTest extends BaseUnitTest {
	/**
	 * {@link ValidatorController#findEmplByAccount(String)}
	 */
	@Test
	public void findEmplByAccount() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/findEmplByAccount")
				.contentType(MediaType.APPLICATION_JSON)
				.param("account", "1011"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.account").value("1011"));
	}

	/**
	 * {@link ValidatorController#findEmpl(Long)}
	 */
	@Test
	public void findEmpl() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{userId}", 1011)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.userId").value(1011L));
	}

	/**
	 * {@link ValidatorController#saveEmpl(EmplDTO)}
	 */
	@Test
	public void saveEmpl() throws Exception {
		EmplDTO emplDTO = new EmplDTO();
		emplDTO.setUserName("张三");
		emplDTO.setAccount("zhangsan");
		emplDTO.setUserId(1001L);
		emplDTO.setPassword("123456");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/save")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(JSON.toJSONString(emplDTO)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	/**
	 * {@link ValidatorController#valid(ContractDTO)}
	 */
	@Test
	public void valid() throws Exception {
		ContractDTO contractDTO = new ContractDTO();
		contractDTO.setCon_id("10086");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/user/valid")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(contractDTO)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}