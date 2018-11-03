package cn.ixan.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

/**
 * @author stack_zhang@outlook.com
 */
@EnableCaching
@MapperScan(basePackages = "cn.ixan.search.mapper")
@SpringBootApplication
public class SearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("models")));
		return cacheManager;
	}
}
