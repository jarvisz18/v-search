package com.ixan.boot.test.migrate;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.ixan.boot.test.migrate.domain.User;
import com.ixan.boot.test.migrate.utils.JdbcUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/21 下午6:38
 * @description 测试druid数据源连接池
 */
public class DruidTest {

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

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Test
	public void testDruidConnectionPoolQuery() {
		Connection connection = getConnection();
		String sql = "select id,name,create_time createTime,update_time updateTime from user where id = ?";
		User one = JdbcUtils.findOne(connection, User.class, sql, 1);
		System.out.println(one);

		//释放资源
		JdbcUtils.close(connection, null);
	}
}
