package cn.ixan.search.mult.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/8/24 15:27
 * @description 数据源配置
 * @version 1.0
 */
// Spring Boot 1.0+ ，DataSourceBuilder所在包位置为：org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
// Spring Boot 2.0+ ，DataSourceBuilder所在包位置为：org.springframework.boot.jdbc.DataSourceBuilder
@Configuration
public class DataSourceConfig {
	@Primary
	@Bean(name = "mysqlDataSource")
	@ConfigurationProperties("spring.datasource.hikari.test1")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "postgresDataSource")
	@ConfigurationProperties("spring.datasource.hikari.test2")
	public DataSource postgresDataSource() {
		return DataSourceBuilder.create().build();
	}
}
