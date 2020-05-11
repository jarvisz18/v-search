package com.cloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/4/4 22:57
 * @description Eureka
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka {
    public static void main(String[] args) {
        SpringApplication.run(Eureka.class);
    }
}
