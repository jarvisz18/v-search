package com.ixan.boot.test.migrate;

import com.ixan.boot.test.migrate.dao.UserQueryDaoImpl;
import com.ixan.boot.test.migrate.domain.User;
import org.junit.Test;

import java.util.List;

public class UserQueryDaoImplTest {

	@Test
	public void findById() {
		UserQueryDaoImpl userQueryDao = new UserQueryDaoImpl();
		String sql = "select id,name,create_time createTime,update_time updateTime from user where id = ?";
		User user = userQueryDao.findById(sql, 1);
		System.out.println(user);
	}

	@Test
	public void findAll() {
		UserQueryDaoImpl userQueryDao = new UserQueryDaoImpl();
		String sql = "select id,name,create_time createTime,update_time updateTime from user";
		List<User> userList = userQueryDao.findAll(sql);
		userList.forEach(System.out::println);
	}

	@Test
	public void deleteById() {
		UserQueryDaoImpl userQueryDao = new UserQueryDaoImpl();
		String sql = "delete from user where id = ?";
		int rowCount = userQueryDao.deleteById(sql, 4);
		System.out.println("effect rows:" + rowCount);
	}

	@Test
	public void update() {
		UserQueryDaoImpl userQueryDao = new UserQueryDaoImpl();
		String sql = "update user set name = ? where id = ?";
		int rowCount = userQueryDao.update(sql, "古力娜扎", 5);
		System.out.println("effect rows:" + rowCount);
	}
}