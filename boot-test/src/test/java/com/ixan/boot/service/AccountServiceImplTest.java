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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class AccountServiceImplTest extends BaseUnitTest {
	@Autowired
	private AccountService accountService;

	@Test
	@Transactional
	@Rollback
	public void update() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUsername("张三");
		accountDTO.setSite("www.baidu.com");
		Integer integer = accountService.update(accountDTO);
		Assert.assertTrue(integer > 0);
	}

	@Test
	public void findById() {
		Account account = accountService.findById("1");
		Assert.assertNotNull(account);
	}

	@Test
	public void findAccountBySite() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setSite("baidu");
		accountDTO.setPageSize(10);
		Assert.assertNotNull(accountService.findAccountBySite(accountDTO));
	}

	@Test
	@Transactional
	@Rollback
	public void add() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setUsername("张三");
		accountDTO.setPassword("123456");
		accountDTO.setSite("www.baidu.com");
		accountDTO.setSite_name("百度");
		Assert.assertTrue(accountService.add(accountDTO) > 0);
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