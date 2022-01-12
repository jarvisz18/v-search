package com.ixan.boot;

import com.ixan.boot.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/6/25 15:05
 * @description
 */
@EnableFeignClients
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableDiscoveryClient
@MapperScan("com.ixan.boot.mapper")
@SpringBootApplication
public class BootApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(BootApplication.class, args);
		SpringContextUtils.setApplicationContext(applicationContext);
	}
}
