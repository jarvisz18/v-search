package com.ixan.boot.test.migrate;

import com.ixan.boot.test.migrate.utils.JdbcQueryHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/21 下午11:04
 * @description 数据库基本信息查询
 */
public class DbBasicInfoQueryTest {

	/**
	 * 1.2 Mysql 获取指定数据库下指定表的字段信息
	 */
	@Test
	public void testGetAllTableField() {
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getDruidConnection();
			String sql = "select ordinal_position ,column_name ,column_comment " +
					"from information_schema.columns where table_schema = ? and table_name = ? " +
					"order by ordinal_position asc";
			QueryRunner queryRunner = new QueryRunner();
			ArrayListHandler handler = new ArrayListHandler();
			List<Object[]> test = queryRunner.query(connection, sql, handler, "test", "user");
			for (Object[] objects : test) {
				System.out.println(objects[0] + "  " + objects[1] + "  " + objects[2]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcQueryHelper.close(connection, null);
		}
	}

	/**
	 * 1.1 Mysql 根据数据库获取该数据库下所有的表名
	 */
	@Test
	public void testGetAllTableName() {
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getDruidConnection();
			String sql = "select table_name from information_schema.tables where table_schema=?";
			QueryRunner queryRunner = new QueryRunner();
			ArrayListHandler handler = new ArrayListHandler();
			List<Object[]> test = queryRunner.query(connection, sql, handler, "test");
			for (Object[] objects : test) {
				System.out.println(objects[0]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcQueryHelper.close(connection, null);
		}
	}
}
