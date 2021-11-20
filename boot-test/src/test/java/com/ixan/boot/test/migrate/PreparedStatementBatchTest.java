package com.ixan.boot.test.migrate;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/20 下午8:53
 * @description 批处理
 */
public class PreparedStatementBatchTest {

	/**
	 * 方式三：关闭自动提交，最后提交事务
	 * 批处理，往一张表中插入100w条数据
	 */
	@Test
	public void testPreparedStatementBatch3() {
		Connection connection = null;
		PreparedStatement statement = null;
		long start = System.currentTimeMillis();
		try {
			connection = JdbcUtils.getConnection();
			connection.setAutoCommit(false);
			String sql = "insert into goods (goods_name) values (?) ";

			statement = connection.prepareStatement(sql);
			for (int i = 0; i < 1000_000; i++) {
				statement.setObject(1, "商品_" + i);
				statement.addBatch();
				//每1000条执行一次
				if (i % 1000 == 0) {
					statement.executeBatch();
					statement.clearBatch();
				}
			}
			statement.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			//关闭资源
			JdbcUtils.close(connection, statement);
		}
		long end = System.currentTimeMillis();
		System.out.println("插入执行完毕,耗时:[" + (end - start) + "]ms");

	}

	/**
	 * 方式二：使用addBatch、executeBatch、clearBatch
	 * 批处理，往一张表中插入100w条数据<br/>
	 * mysql开启批处理需要设置连接参数: &allowMultiQueries=true&&rewriteBatchedStatements=true <br/>
	 */
	@Test
	public void testPreparedStatementBatch2() {
		Connection connection = null;
		PreparedStatement statement = null;
		long start = System.currentTimeMillis();
		try {
			connection = JdbcUtils.getConnection();
			String sql = "insert into goods (goods_name) values (?) ";

			statement = connection.prepareStatement(sql);
			for (int i = 0; i < 10_000; i++) {
				statement.setObject(1, "商品_" + i);
				statement.addBatch();
				//每1000条执行一次
				if (i % 1000 == 0) {
					statement.executeBatch();
					statement.clearBatch();
				}
			}
			statement.executeBatch();
			statement.clearBatch();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			//关闭资源
			JdbcUtils.close(connection, statement);
		}
		long end = System.currentTimeMillis();
		System.out.println("插入执行完毕,耗时:[" + (end - start) + "]ms");

	}

	/**
	 * 方式一：使用PreparedStatement
	 * 批处理，往一张表中插入2w条数据
	 * 6658ms
	 */
	@Test
	public void testPreparedStatementBatch1() {
		Connection connection = null;
		PreparedStatement statement = null;
		long start = System.currentTimeMillis();
		try {
			connection = JdbcUtils.getConnection();
			String sql = "insert into goods (goods_name) values (?) ";

			statement = connection.prepareStatement(sql);
			for (int i = 0; i < 20_000; i++) {
				statement.setObject(1, "商品_" + i);
				statement.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			JdbcUtils.close(connection, statement);
		}
		long end = System.currentTimeMillis();
		System.out.println("插入执行完毕,耗时:[" + (end - start) + "]ms");

	}
}
