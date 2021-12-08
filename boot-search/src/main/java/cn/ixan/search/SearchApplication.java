package cn.ixan.search;

import cn.ixan.search.web.controller.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.annotation.PostConstruct;
import java.util.Collections;

/**
 * @author stackzhang@126.com
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

	@Autowired
	private Product product;

	@PostConstruct
	public void init() {
		for (int i = 0; i < 10; i++) {
			product.send("v-search" + i);
		}
	}

	@Bean
	public Gson gson() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("models")));
		return cacheManager;
	}
}
