package com.ixan.boot.test.migrate.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/20 下午2:02
 * @description JdbcUtils
 */
public final class JdbcUtils {
	private JdbcUtils() {
		throw new UnsupportedOperationException();
	}

	public static <T> List<T> findAll(Connection connection, Class<T> clazz, String sql, Object... args) {
		List<T> list = new ArrayList<>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			//设置参数
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			//执行SQL
			resultSet = statement.executeQuery();
			//获取结果集字段个数
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				T t = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					//字段值
					Object object = resultSet.getObject(i + 1);
					//字段别名
					String columnLabel = metaData.getColumnLabel(i + 1);
					Field declaredField = clazz.getDeclaredField(columnLabel);
					declaredField.setAccessible(true);
					declaredField.set(t, object);
				}
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(null, statement, resultSet);
		}
		return list;
	}

	public static <T> List<T> findAll(Class<T> clazz, String sql, Object... args) {
		List<T> list = new ArrayList<>();
		//获取连接
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.prepareStatement(sql);
			//设置参数
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			//执行SQL
			resultSet = statement.executeQuery();
			//获取结果集字段个数
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				T t = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					//字段值
					Object object = resultSet.getObject(i + 1);
					//字段别名
					String columnLabel = metaData.getColumnLabel(i + 1);
					Field declaredField = clazz.getDeclaredField(columnLabel);
					declaredField.setAccessible(true);
					declaredField.set(t, object);
				}
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(connection, statement, resultSet);
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
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			//执行SQL
			resultSet = statement.executeQuery();
			//获取结果集字段个数
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			if (resultSet.next()) {
				T t = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					//字段值
					Object object = resultSet.getObject(i + 1);
					//字段别名
					String columnLabel = metaData.getColumnLabel(i + 1);
					Field declaredField = clazz.getDeclaredField(columnLabel);
					declaredField.setAccessible(true);
					declaredField.set(t, object);
				}
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(null, statement, resultSet);
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
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			//执行SQL
			resultSet = statement.executeQuery();
			//获取结果集字段个数
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			if (resultSet.next()) {
				T t = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					//字段值
					Object object = resultSet.getObject(i + 1);
					//字段别名
					String columnLabel = metaData.getColumnLabel(i + 1);
					Field declaredField = clazz.getDeclaredField(columnLabel);
					declaredField.setAccessible(true);
					declaredField.set(t, object);
				}
				return t;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(connection, statement, resultSet);
		}
		return null;
	}

	public static void update(Connection connection, String sql, Object... args) {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			//执行SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(null, statement);
		}
	}

	public static void update(String sql, Object... args) {
		//获取连接
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			//执行SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(connection, statement);
		}
	}

	@SuppressWarnings("all")
	public static <E> E getValue(Connection connection, String sql, Object... args) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			//执行SQL
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return (E) resultSet.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(null, statement, resultSet);
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
			connection = JdbcUtils.getConnection();
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}
			//执行SQL
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return (E) resultSet.getObject(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(connection, statement, resultSet);
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
		try {
			if (null != connection) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (null != statement) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//关闭资源
	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (null != connection) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (null != statement) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (null != resultSet) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
