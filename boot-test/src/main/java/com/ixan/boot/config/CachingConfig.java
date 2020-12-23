package com.ixan.boot.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 16:58
 * @description CachingConfig
 * @version 1.0
 */
@Configuration
@EnableCaching
public class CachingConfig {

	@Bean(name = "concurrentMapCacheManager")
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
