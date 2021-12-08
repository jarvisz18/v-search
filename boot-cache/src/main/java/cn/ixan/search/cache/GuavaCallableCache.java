package cn.ixan.search.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/5 下午8:53
 * @description guava callable cache
 */
public class GuavaCallableCache {
	public static void main(String[] args) throws ExecutionException {
		Cache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(2)
				.build();
		cache.put("k1", "Hello k1");
		String k1 = cache.get("k1", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "nil";
			}
		});
		//输出缓存值
		System.out.println(k1);

		String nokey = cache.get("nokey", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "nil";
			}
		});
		//输出缓存值
		System.out.println(nokey);
	}
}
