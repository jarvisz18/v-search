package cn.ixan.search.cache.custom;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/5 下午9:14
 * @description 全局缓存对象
 */
public class CacheGlobal {
	/**
	 * 全局缓存对象
	 */
	public static ConcurrentHashMap<String, MyCache> concurrentHashMap = new ConcurrentHashMap<>();
}
