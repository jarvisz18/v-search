package com.ixan.boot.test.migrate;

import com.ixan.boot.test.migrate.utils.JdbcUtils;
import org.apache.commons.dbutils.DbUtils;
import org.junit.Test;

import java.sql.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/24 下午10:38
 * @description hikari DataSource Test
 * 测试hikari 数据源连接池
 */
public class HikariDataSourceTest {


	@Test
	public void test1() throws SQLException {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Connection connection = JdbcUtils.getHikariConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select now() from dual");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			DbUtils.closeQuietly(connection);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗时:[" + (endTime - startTime) + "]ms");
	}

	@Test
	public void test2() throws SQLException {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select now() from dual");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			connection.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("耗时:[" + (endTime - startTime) + "]ms");
	}
}
