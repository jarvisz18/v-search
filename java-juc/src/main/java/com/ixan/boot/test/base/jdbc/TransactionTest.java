package com.ixan.boot.test.base.jdbc;

import com.ixan.boot.test.base.jdbc.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/11/21 下午12:29
 * @description
 */
public class TransactionTest {

	/**
	 * AA给BB转账100，自动提交事务的情况
	 */
	@Test
	public void testTransactionTx() {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			//关闭自动提交
			connection.setAutoCommit(false);

			String sql = "update user_table set balance = balance - 100 where user = ?";
			JdbcUtils.update(connection, sql, "AA");

			//模拟异常
			System.out.println(10 / 0);

			String sql1 = "update user_table set balance = balance + 100 where user = ?";
			JdbcUtils.update(connection, sql1, "BB");
			connection.commit();
			System.out.println("转账成功");
		} catch (Exception e) {
			try {
				if (null != connection) {
					connection.rollback();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			System.out.println("转账失败");
			e.printStackTrace();
		} finally {
			//将自动提交恢复
			//主要针对使用数据库连接池的情况
			try {
				if (connection != null) {
					connection.setAutoCommit(true);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//关闭资源
			JdbcUtils.close(connection, null);
		}
	}

	/**
	 * AA给BB转账100，自动提交事务的情况
	 */
	@Test(expected = ArithmeticException.class)
	public void testTransaction() {
		String sql = "update user_table set balance = balance - 100 where user = ?";
		JdbcUtils.update(sql, "AA");

		//模拟异常
		System.out.println(10 / 0);

		String sql1 = "update user_table set balance = balance + 100 where user = ?";
		JdbcUtils.update(sql1, "BB");
		System.out.println("转账成功");
	}
}
