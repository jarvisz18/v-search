package com.ixan.boot.service;

import com.github.pagehelper.PageInfo;
import com.ixan.boot.BaseUnitTest;
import com.ixan.boot.domain.Account;
import com.ixan.boot.domain.dto.AccountDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Method;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class AccountServiceImplTest extends BaseUnitTest {
	@Autowired
	private AccountService accountService;

	@Test
	public void update() {
	}

	@Test
	public void findById() {
	}

	@Test
	public void findAccountBySite() {
	}

	@Test
	public void add() {
	}

	/**
	 * 测试公共方法
	 */
	@Test
	public void findAll() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setPageNum(1);
		accountDTO.setPageSize(10);
		PageInfo<Account> all = accountService.findAll(accountDTO);
		Assert.assertNotNull(all.getList());
	}

	/**
	 * 测试私有方法
	 */
	@Test
	public void exist() throws Exception {
		Class<?> clazz = Class.forName("com.ixan.boot.service.AccountServiceImpl");
		Method method = clazz.getDeclaredMethod("exist", String.class);
		method.setAccessible(true);
		Object invoke = method.invoke(accountService, "2");
		System.out.println(invoke);
	}
}