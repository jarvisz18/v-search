package cn.ixan.search.cache;

import com.google.common.cache.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/5 下午8:36
 * @description guava缓存测试
 */
public class GuavaLoadingCacheExample {
	public static void main(String[] args) throws ExecutionException {
		//创建方式一: LoadingCache
		LoadingCache<String, String> cache = CacheBuilder.newBuilder()
				// 并发级别设置为5，是指可以同时写缓存的线程数
				.concurrencyLevel(5)
				// 设置8秒过期
				.expireAfterAccess(8, TimeUnit.SECONDS)
				//设置缓存容器的初始容量为10
				.initialCapacity(10)
				//设置最大容量100，超过则执行LRU算法移除元素
				.maximumSize(100)
				//设置要统计缓存的命中率
				.recordStats()
				//设置缓存移除的通知
				.removalListener(new RemovalListener<Object, Object>() {
					@Override
					public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
						System.out.println(removalNotification.getKey() + "was removed ,cause is " + removalNotification.getCause());
					}
				}).build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						return "cache-value:" + key;
					}
				});
		//设置缓存
		cache.put("k1", "Hello k1");
		//查询缓存
		String k1 = cache.get("k1", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "nil";
			}
		});
		System.out.println(k1);

		//查询缓存
		String noKey = cache.get("noKey", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "nil";
			}
		});
		System.out.println(noKey);

	}
}
