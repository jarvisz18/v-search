package com.ixan.boot.test.base.jdbc.dao;

import com.ixan.boot.test.base.jdbc.domain.User;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/21 下午10:15
 * @description 人员信息查询
 */
public class UserQueryDaoImpl extends AbstractQueryDao implements BaseQueryDao {

	@Override
	public User findById(String sql, Object arg) {
		return super.findById(User.class, sql, arg);
	}

	@Override
	public List<User> findAll(String sql) {
		return super.findAll(User.class, sql);
	}

	@Override
	public int deleteById(String sql, Object arg) {
		return super.deleteById(sql, arg);
	}

	@Override
	public int update(String sql, Object... args) {
		return super.update(sql, args);
	}
}
