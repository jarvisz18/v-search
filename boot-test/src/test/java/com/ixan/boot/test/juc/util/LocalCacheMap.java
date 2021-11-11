package com.ixan.boot.test.juc.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/11 下午9:49
 * @description 使用ConcurrentHashMap制作的本地缓存测试
 */
public class LocalCacheMap {
	private static final ConcurrentHashMap<String, SoftReference<CacheObject>> cache = new ConcurrentHashMap<>();

	//定时器，定时清除缓存
	private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1,
			new ThreadFactoryBuilder().setNameFormat("localcache-map-%d").build());

	static {
		executor.scheduleAtFixedRate(LocalCacheMap::removeExpiryReference, 5, 5, TimeUnit.SECONDS);
	}

	private static void removeExpiryReference() {
		cache.entrySet().removeIf(
				entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
						.map(CacheObject::isExpiry).orElse(false));
		System.out.println(Thread.currentThread().getName() + "执行清除过期key");
	}

	public static void add(String key, Object value, long periodInMillis) {
		if (null == key) {
			return;
		}
		if (value == null) {
			cache.remove(key);
		} else {
			long expiryTime = System.currentTimeMillis() + periodInMillis;
			cache.put(key, new SoftReference<>(new CacheObject(value, expiryTime)));
		}
	}

	public static void remove(String key) {
		cache.remove(key);
	}

	public static List<Object> findAll() {
		return cache.values().stream().map(SoftReference::get)
				.filter(Objects::nonNull)
				.filter(cacheObject -> !cacheObject.isExpiry()).map(CacheObject::getValue)
				.collect(Collectors.toList());
	}

	public static Object get(String key) {
		return Optional.ofNullable(cache.get(key)).map(SoftReference::get)
				.filter(cacheObject -> !cacheObject.isExpiry())
				.map(CacheObject::getValue).orElse(null);
	}

	public static void clear() {
		cache.clear();
	}

	public static long size() {
		return cache.entrySet().stream().filter(entry -> Optional.ofNullable(entry.getValue())
				.map(SoftReference::get)
				.map(cacheObject -> !cacheObject.isExpiry())
				.orElse(false)).count();
	}


	private static class CacheObject {
		private Object value;
		private long expiryTime;

		CacheObject(Object value, long expiryTime) {
			this.value = value;
			this.expiryTime = expiryTime;
		}

		Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		boolean isExpiry() {
			return System.currentTimeMillis() > expiryTime;
		}
	}
}
