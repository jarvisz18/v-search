package cn.ixan.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/9/19 13:12
 * @description AdminServerApplication
 * @version 1.0
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class AdminServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}
}
