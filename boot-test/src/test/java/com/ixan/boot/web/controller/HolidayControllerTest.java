package com.ixan.boot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ixan.boot.BaseUnitTest;
import com.ixan.boot.config.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class HolidayControllerTest extends BaseUnitTest {
	/**
	 * 指定日期范围有几个节假日
	 * {@link HolidayController#numHolidays(Map)}
	 */
	@Test
	public void numHolidays() throws Exception {
		Map<String, String> paramMap = new HashMap<>();
		String startTime = "2021-11-01 00:00:00";
		paramMap.put("startTime", startTime);
		String endTime = "2021-12-31 23:59:59";
		paramMap.put("endTime", endTime);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/holiday/numHolidays")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(JSON.toJSONString(paramMap)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		Result<Integer> integerResult = JSON.parseObject(result, new TypeReference<Result<Integer>>() {
		});
		System.out.printf("指定日期范围[%s]-[%s]有[%s]个节假日\n", startTime, endTime, integerResult.getData());
	}

	@Test
	public void updateHoliday() {
	}

	@Test
	public void deleteHoliday() {
	}

	@Test
	public void addHolidays() {
	}

	@Test
	public void addHoliday() {
	}

	@Test
	public void isHoliday() {
	}

	@Test
	public void findAll() {
	}

	@Test
	public void init() {
	}
}