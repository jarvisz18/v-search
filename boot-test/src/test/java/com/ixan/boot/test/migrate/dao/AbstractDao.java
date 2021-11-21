package com.ixan.boot.test.migrate.dao;

import com.ixan.boot.test.migrate.utils.JdbcQueryHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/21 下午9:30
 * @description 基础的数据查询-抽象
 */
public abstract class AbstractDao<T> implements BaseDao<T> {
	private Class<T> clazz;

	//todo:反射中如何获取父类的泛型类型
	{
		// 获取当前BaseQueryDao的子类继承父类的泛型类型
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
		Type[] typeArguments = parameterizedType.getActualTypeArguments();
		clazz = (Class<T>) typeArguments[0];
	}

	@Override
	public T findById(String sql, Object arg) {
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

	@Override
	public List<T> findAll(String sql) {
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
