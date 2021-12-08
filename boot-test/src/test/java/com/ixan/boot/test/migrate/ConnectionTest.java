package com.ixan.boot.test.migrate;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/20 下午12:17
 * @description jdbc连接测试
 */
public class ConnectionTest {

	/**
	 * 方式四:连接基本参数通过配置文件获取
	 */
	@Test
	public void testConnection4() throws Exception {
		InputStream resourceAsStream = new ClassPathResource("jdbc.properties").getInputStream();
		Properties properties = new Properties();
		properties.load(resourceAsStream);

		//连接基本参数
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String driverClass = properties.getProperty("driverClass");

		//mysql的驱动实现类，一旦加载即自动注册
		Class.forName(driverClass);

		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
	}

	/**
	 * 方式三:使用DriverManager替换 Driver
	 */
	@Test
	public void testConnection3() throws Exception {
		//获取驱动
		Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
		Driver driver = (Driver) aClass.newInstance();
		//注册驱动
		DriverManager.registerDriver(driver);
		//连接基本参数
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "root";

		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
	}

	@Test
	public void testConnection2() throws Exception {
		//获取驱动连接 反射获取
		Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
		Driver driver = (Driver) aClass.newInstance();
		//url
		String url = "jdbc:mysql://localhost:3306/test";
		//将用户名和密码封装在Properties中
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "root");

		Connection connect = driver.connect(url, properties);
		System.out.println(connect);
	}

	@Test
	public void testConnection1() throws SQLException {
		//获取驱动连接
		Driver driver = new com.mysql.cj.jdbc.Driver();
		//url
		String url = "jdbc:mysql://localhost:3306/test";
		//将用户名和密码封装在Properties中
		Properties properties = new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "root");

		Connection connect = driver.connect(url, properties);
		System.out.println(connect);
	}
}
