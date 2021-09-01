package com.ixan.boot.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/31 下午9:34
 * @description jdbc utils
 */
public class JdbcUtils {

	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		close(resultSet);
		close(statement);
		close(connection);
	}

	public static void close(Connection connection) {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Statement statement) {
		if (null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet resultSet) {
		if (null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
