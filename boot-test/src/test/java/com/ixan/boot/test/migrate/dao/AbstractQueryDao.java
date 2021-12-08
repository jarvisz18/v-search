package com.ixan.boot.test.migrate.dao;

import com.ixan.boot.test.migrate.domain.User;
import com.ixan.boot.test.migrate.utils.JdbcQueryHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/21 下午9:30
 * @description 基础的数据查询-抽象
 */
public abstract class AbstractQueryDao implements BaseQueryDao {

	abstract User findById(String sql, Object arg);

	@Override
	public <T> T findById(Class<T> clazz, String sql, Object arg) {
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			BeanHandler<T> handler = new BeanHandler<>(clazz);
			return queryRunner.query(connection, sql, handler, arg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, null);
		}
		return null;
	}

	abstract List<User> findAll(String sql);

	@Override
	public <T> List<T> findAll(Class<T> clazz, String sql) {
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			BeanListHandler<T> handler = new BeanListHandler<>(clazz);
			return queryRunner.query(connection, sql, handler);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, null);
		}
		return null;
	}

	public int deleteById(String sql, Object arg) {
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			return queryRunner.update(connection, sql, arg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, null);
		}
		return 0;
	}

	@Override
	public int update(String sql, Object... args) {
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			return queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, null);
		}
		return 0;
	}
}
