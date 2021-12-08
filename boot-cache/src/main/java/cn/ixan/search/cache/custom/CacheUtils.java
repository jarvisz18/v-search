package cn.ixan.search.cache.custom;

import org.apache.commons.lang3.StringUtils;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/5 下午9:23
 * @description cache工具类
 */
public class CacheUtils {
	/**
	 * 田间缓存
	 *
	 * @param key    键
	 * @param value  值
	 * @param expire 过期时间
	 */
	public void put(String key, Object value, long expire) {
		if (StringUtils.isBlank(key)) {
			return;
		}

		//缓存存在,更新缓存
		if (CacheGlobal.concurrentHashMap.containsKey(key)) {
			MyCache cache = CacheGlobal.concurrentHashMap.get(key);
			cache.setHitCount(cache.getHitCount() + 1);
			cache.setWriteTime(System.currentTimeMillis());
			cache.setLastTime(System.currentTimeMillis());
			cache.setExpireTime(expire);
			cache.setValue(value);
			return;
		}
		//创建缓存
		MyCache cache = new MyCache();
		cache.setKey(key);
		cache.setValue(value);
		cache.setWriteTime(System.currentTimeMillis());
		cache.setLastTime(System.currentTimeMillis());
		cache.setHitCount(1);
		cache.setExpireTime(expire);
		CacheGlobal.concurrentHashMap.put(key, cache);
	}

	public Object get(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		if (CacheGlobal.concurrentHashMap.isEmpty()) {
			return null;
		}

		//不存在
		if (!CacheGlobal.concurrentHashMap.containsKey(key)) {
			return null;
		}

		MyCache cache = CacheGlobal.concurrentHashMap.get(key);
		if (cache == null) {
			return cache;
		}

		//惰性删除,判断缓存是否过期
		long timeoutTime = System.currentTimeMillis() - cache.getWriteTime();
		if (cache.getExpireTime() <= timeoutTime) {
			CacheGlobal.concurrentHashMap.remove(key);
			return null;
		}
		cache.setHitCount(cache.getHitCount() + 1);
		cache.setLastTime(System.currentTimeMillis());
		System.out.println("获取缓存成功! 命中数:" + cache.getHitCount());
		return cache.getValue();
	}


}
