package com.ixan.boot;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/2/26 上午10:49
 * @description 基础单元测试, 子类继承它
 */
@Component
public class BaseUnitTest {
	protected MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
}
