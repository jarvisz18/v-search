package cn.ixan.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/9/19 13:16
 * @description AdminClientApplication
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AdminClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminClientApplication.class, args);
	}
}
