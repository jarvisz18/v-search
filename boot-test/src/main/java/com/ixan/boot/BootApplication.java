package com.ixan.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/25 15:05
 * @description
 */

@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableDiscoveryClient
@MapperScan("com.ixan.boot.mapper")
@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
