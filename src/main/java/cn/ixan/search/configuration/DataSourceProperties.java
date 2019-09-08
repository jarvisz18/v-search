package cn.ixan.search.configuration;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author stack_zhang@outlook.com
 * @date 19-9-7
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
@Data
@Slf4j
public class DataSourceProperties {

	private String url;
	private String driverClassName;
	private String username;
	private String password;
	private Integer initialSize;
	private Integer minIdle;
	private Integer maxActive;
	private Integer maxWait;
	private Integer timeBetweenEvictionRunsMillis;
	private Integer minEvictableIdleTimeMillis;
	private String validationQuery;
	private Boolean testWhileIdle;
	private Boolean testOnBorrow;
	private Boolean testOnReturn;
	private Boolean poolPreparedStatements;
	private Integer maxPoolPreparedStatementPerConnectionSize;
	private String filters;
	private String connectProperties;
	private String publicKey;

	// TODO: 19-9-8 如何配置druid自动解密
	@Primary
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(db_decrypt_test());
		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		//${spring.datasource.druid.publicKey}占位,publicKey要真实存在
		//datasource.setConnectionProperties(connectProperties);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			log.error("druid configuration initialization filter", e);
		}
		return datasource;
	}

	private String db_decrypt_test() {
		try {
			return ConfigTools.decrypt(publicKey, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解密失败...");
		}
	}

	//配置管理后台的Servlet
	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean druidServletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		//白名单
		servletRegistrationBean.addInitParameter("allow", "");
		//黑名单
		//servletRegistrationBean.addInitParameter("deny","192.168.0.9");
		//超级管理员 用户名 密码
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		//是否能够重置数据  禁用HTML的 reset all 功能
		servletRegistrationBean.addInitParameter("resetEnable","false");
		return servletRegistrationBean;
	}

	//配置问监控的filter
	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
