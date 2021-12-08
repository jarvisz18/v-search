package com.ixan.boot.test.migrate.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author stackzhang@126.com
 * @version 2.0
 * @date Created in 2021/11/20 下午2:02
 * @description 使用Apache—DbUtils进一步封装我们的查询工具类
 */
public final class JdbcQueryHelper {
	private JdbcQueryHelper() {
		throw new UnsupportedOperationException();
	}

	public static <T> List<T> findAll(Connection connection, Class<T> clazz, String sql, Object... args) {
		List<T> list = new ArrayList<>();
		try {
			QueryRunner queryRunner = new QueryRunner();
			BeanListHandler<T> handler = new BeanListHandler<>(clazz);
			return queryRunner.query(connection, sql, handler, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static <T> List<T> findAll(Class<T> clazz, String sql, Object... args) {
		List<T> list = new ArrayList<>();
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			BeanListHandler<T> handler = new BeanListHandler<>(clazz);
			return queryRunner.query(connection, sql, handler, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, null);
		}
		return list;
	}

	/**
	 * 通用的JDBC查询
	 * metaData.getColumnLabel() 获取查询结果集字段别名<br/>
	 * metaData.getColumnName() 获取查询结果集字段名<br/>
	 * 未设置字段别名则 getColumnLabel 等价于getColumnName <br/>
	 *
	 * @param connection 数据库连接
	 * @param clazz      结果映射的对象
	 * @param sql        查询的SQL语句
	 * @param args       参数
	 * @param <T>        范型
	 * @return T
	 */
	public static <T> T findOne(Connection connection, Class<T> clazz, String sql, Object... args) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			QueryRunner queryRunner = new QueryRunner();
			BeanHandler<T> handler = new BeanHandler<>(clazz);
			return queryRunner.query(connection, sql, handler, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(null, statement, resultSet);
		}
		return null;
	}

	/**
	 * 通用的JDBC查询
	 * metaData.getColumnLabel() 获取查询结果集字段别名<br/>
	 * metaData.getColumnName() 获取查询结果集字段名<br/>
	 * 未设置字段别名则 getColumnLabel 等价于getColumnName <br/>
	 *
	 * @param clazz 结果映射的对象
	 * @param sql   查询的SQL语句
	 * @param args  参数
	 * @param <T>   范型
	 * @return T
	 */
	public static <T> T findOne(Class<T> clazz, String sql, Object... args) {
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			BeanHandler<T> handler = new BeanHandler<>(clazz);
			return queryRunner.query(connection, sql, handler, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, null);
		}
		return null;
	}

	public static int update(Connection connection, String sql, Object... args) {
		PreparedStatement statement = null;
		try {
			QueryRunner queryRunner = new QueryRunner();
			return queryRunner.update(connection, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(null, statement);
		}
		return 0;
	}

	public static int update(String sql, Object... args) {
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getDruidConnection();
			QueryRunner queryRunner = new QueryRunner();
			return queryRunner.update(connection, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcQueryHelper.close(connection, null);
		}
		return 0;
	}

	@SuppressWarnings("all")
	public static <E> E getValue(Connection connection, String sql, Object... args) {
		try {
			QueryRunner queryRunner = new QueryRunner();
			ScalarHandler<E> handler = new ScalarHandler<>();
			return queryRunner.query(connection, sql, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("all")
	public static <E> E getValue(String sql, Object... args) {
		//获取连接
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcQueryHelper.getConnection();
			QueryRunner queryRunner = new QueryRunner();
			ScalarHandler<E> handler = new ScalarHandler<>();
			return queryRunner.query(connection, sql, handler);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, statement, resultSet);
		}
		return null;
	}

	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection() {
		Connection connection = null;
		InputStream resourceAsStream = null;
		try {
			resourceAsStream = new ClassPathResource("jdbc.properties").getInputStream();
			Properties properties = new Properties();
			properties.load(resourceAsStream);

			//连接基本参数
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			String driverClass = properties.getProperty("driverClass");

			//mysql的驱动实现类，一旦加载即自动注册
			Class.forName(driverClass);

			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Druid 数据库连接池
	 */
	private static DataSource dataSource;

	static {
		try {
			Properties properties = new Properties();
			ClassPathResource resource = new ClassPathResource("druid.properties");
			properties.load(resource.getInputStream());
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getDruidConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	//关闭资源
	public static void close(Connection connection, Statement statement) {
		DbUtils.closeQuietly(connection);
		DbUtils.closeQuietly(statement);
	}

	//关闭资源
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		DbUtils.closeQuietly(connection);
		DbUtils.closeQuietly(statement);
		DbUtils.closeQuietly(resultSet);
	}

}
