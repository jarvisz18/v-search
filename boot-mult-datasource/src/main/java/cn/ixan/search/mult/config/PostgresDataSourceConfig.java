package cn.ixan.search.mult.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/8/24 15:37
 * @description 另一个数据源配置
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "postgresEntityManagerFactory",
		transactionManagerRef = "postgresTransactionManager",
		// 数据层所在的包位置
		basePackages = "cn.ixan.search.mult.dao.postgres")
public class PostgresDataSourceConfig {
	@Resource
	private Environment environment;
	@Resource
	@Qualifier("postgresDataSource")
	private DataSource dataSource;

	@Bean(name = "postgresEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		Map<String, Object> properties = new HashMap<>(4);
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		return builder.dataSource(dataSource)
				// 实体所在的包位置
				.properties(properties)
				.packages("cn.ixan.search.mult.domain.postgres")
				.persistenceUnit("jpa-postgres")
				.build();
	}

	@Bean
	public PlatformTransactionManager postgresTransactionManager(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
