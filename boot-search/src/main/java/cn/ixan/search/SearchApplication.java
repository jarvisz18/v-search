package cn.ixan.search;

import com.google.gson.Gson;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Collections;

/**
 * @author stack_zhang@outlook.com
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableAspectJAutoProxy
@EnableCaching
@MapperScan(basePackages = "cn.ixan.search.mapper")
@SpringBootApplication
public class SearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@Bean
	public Gson gson(){
		return new Gson();
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("models")));
		return cacheManager;
	}
}
