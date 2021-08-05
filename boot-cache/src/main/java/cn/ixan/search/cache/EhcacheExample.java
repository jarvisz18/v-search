package cn.ixan.search.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/5 下午8:24
 * @description Ehcache缓存例子
 */
public class EhcacheExample {
	public static void main(String[] args) {
		//创建缓存管理器
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		//初始化Ehcache
		cacheManager.init();
		//创建缓存(存储器)
		Cache<String, String> myCache = cacheManager.createCache("myCache",
				CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
						//设置缓存的最大容量
						ResourcePoolsBuilder.heap(10)));
		//设置缓存
		myCache.put("key", "Hello World");
		//读取缓存
		String key = myCache.get("key");
		System.out.println(key);
		//关闭缓存
		cacheManager.close();
	}
}
