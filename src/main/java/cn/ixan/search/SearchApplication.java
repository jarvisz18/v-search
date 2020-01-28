package cn.ixan.search;

import com.google.gson.Gson;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

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
	public Gson gson(){
		return new Gson();
	}

	//@Bean
	public Jedis jedis(){
		return new Jedis("192.168.70.128",6379);
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("models")));
		return cacheManager;
	}
}
