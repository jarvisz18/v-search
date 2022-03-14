package com.ixan.boot.web.controller;

import com.alibaba.fastjson.JSON;
import com.ixan.boot.BaseUnitTest;
import com.ixan.boot.config.Result;
import com.ixan.boot.domain.dto.AccountDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class AccountControllerTest extends BaseUnitTest {
	/**
	 * 根据主键查询账户
	 * {@link AccountController#update(AccountDTO)}
	 */
	@Test
	@Transactional
	@Rollback
	public void update() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUsername("张三");
		accountDTO.setSite("www.baidu.com");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/update")
				.content(JSON.toJSONString(accountDTO))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Result result = JSON.parseObject(content, Result.class);
		Assert.assertEquals("200", result.getCode());
	}

	/**
	 * 根据主键查询账户
	 * {@link AccountController#findById(String)}
	 */
	@Test
	public void findById() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/account/findById/{id}", "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Result result = JSON.parseObject(content, Result.class);
		Assert.assertEquals("200", result.getCode());
	}

	/**
	 * 根据站点模糊查询账户数据
	 * {@link AccountController#findAccountBySite(AccountDTO)}
	 */
	@Test
	public void findAccountBySite() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setSite("baidu");
		accountDTO.setPageSize(10);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/findAccountBySite")
				.content(JSON.toJSONString(accountDTO))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Result result = JSON.parseObject(content, Result.class);
		Assert.assertEquals("200", result.getCode());
	}

	/**
	 * 新增账户数据
	 * {@link AccountController#add(AccountDTO)}
	 */
	@Test
	@Transactional
	@Rollback
	public void add() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUsername("张三");
		accountDTO.setPassword("123456");
		accountDTO.setSite("www.baidu.com");
		accountDTO.setSite_name("百度");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/add")
				.content(JSON.toJSONString(accountDTO))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Result result = JSON.parseObject(content, Result.class);
		Assert.assertEquals("200", result.getCode());
	}

	/**
	 * 查询所有账户数据
	 * {@link AccountController#findAll(AccountDTO)}
	 */
	@Test
	public void findAll() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setPageNum(1);
		accountDTO.setPageSize(10);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/account/findAll")
				.content(JSON.toJSONString(accountDTO))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Result result = JSON.parseObject(content, Result.class);
		Assert.assertEquals("200", result.getCode());
	}
}