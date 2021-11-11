package com.ixan.boot.test.juc.util;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/11 下午11:05
 * @description 基于LRU算法的本地缓存
 * 原文链接:https://www.jianshu.com/p/4194483127fe <br/>
 */
public class LRULocalCacheMap {
	/**
	 * 默认有效时长,单位:秒
	 */
	private static final int DEFUALT_TIMEOUT = 3600;

	private static final long SECOND_TIME = 1000L;

	private static final Map<String, Object> map;

	private static final Timer timer;


	/**
	 * 初始化
	 */
	static {
		timer = new Timer();
		map = new LRUMap<>();
	}

	/**
	 * 私有构造函数,工具类不允许实例化
	 */
	private LRULocalCacheMap() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 基于LRU策略的map
	 *
	 * @param <K>
	 * @param <V>
	 */
	private static class LRUMap<K, V> extends LinkedHashMap<K, V> {

		/**
		 * LinkedHashMap线程不安全
		 * 使用ReadWriteLock 读写锁
		 */
		private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

		private final Lock rLock = readWriteLock.readLock();

		private final Lock wLock = readWriteLock.writeLock();

		/**
		 * 默认缓存容量
		 */
		private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

		/**
		 * 默认最大缓存容量
		 */
		private static final int DEFAULT_MAX_CAPACITY = 1 << 30;

		/**
		 * 加载因子
		 */
		private static final float DEFAULT_LOAD_FACTOR = 0.75f;

		public LRUMap() {
			super(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
		}

		public LRUMap(int initialCapacity) {
			super(initialCapacity, DEFAULT_LOAD_FACTOR);
		}


		public V get(String k) {
			rLock.lock();
			try {
				return super.get(k);
			} finally {
				rLock.unlock();
			}
		}

		public V put(K k, V v) {
			wLock.lock();
			try {
				return super.put(k, v);
			} finally {
				wLock.unlock();
			}
		}

		public void putAll(Map<? extends K, ? extends V> m) {
			wLock.lock();
			try {
				super.putAll(m);
			} finally {
				wLock.unlock();
			}
		}

		public V remove(Object k) {
			wLock.lock();
			try {
				return super.remove(k);
			} finally {
				wLock.unlock();
			}
		}


		public boolean containKey(K k) {
			rLock.lock();
			try {
				return super.containsKey(k);
			} finally {
				rLock.unlock();
			}
		}

		public int size() {
			rLock.lock();
			try {
				return super.size();
			} finally {
				rLock.unlock();
			}
		}


		public void clear() {
			wLock.lock();
			try {
				super.clear();
			} finally {
				wLock.unlock();
			}
		}


		/**
		 * 重写LinkedHashMap中removeEldestEntry方法;
		 * 新增元素的时候,会判断当前map大小是否超过DEFAULT_MAX_CAPACITY,超过则移除map中最老的节点;
		 */
		protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
			return size() > DEFAULT_MAX_CAPACITY;
		}

	}

	/**
	 * 清除缓存任务类
	 */
	private static class CleanWorkerTask extends TimerTask {

		private String key;

		public CleanWorkerTask(String key) {
			this.key = key;
		}

		public void run() {
			LRULocalCacheMap.remove(key);
		}
	}

	public static void add(String key, Object value) {
		map.put(key, value);
		timer.schedule(new LRULocalCacheMap.CleanWorkerTask(key), DEFUALT_TIMEOUT);

	}

	public static void add(String key, Object value, int timeout) {
		map.put(key, value);
		timer.schedule(new LRULocalCacheMap.CleanWorkerTask(key), timeout * SECOND_TIME);
	}

	public static void put(String key, Object value, Date expireTime) {
		map.put(key, value);
		timer.schedule(new LRULocalCacheMap.CleanWorkerTask(key), expireTime);
	}


	public static void putAll(Map<String, Object> m) {
		map.putAll(m);
		for (String key : m.keySet()) {
			timer.schedule(new LRULocalCacheMap.CleanWorkerTask(key), DEFUALT_TIMEOUT);
		}
	}

	public static void putAll(Map<String, Object> m, int timeout) {
		map.putAll(m);
		for (String key : m.keySet()) {
			timer.schedule(new LRULocalCacheMap.CleanWorkerTask(key), timeout * SECOND_TIME);
		}
	}

	public static void putAll(Map<String, Object> m, Date expireTime) {
		map.putAll(m);
		for (String key : m.keySet()) {
			timer.schedule(new LRULocalCacheMap.CleanWorkerTask(key), expireTime);
		}
	}

	public static Object get(String key) {
		return map.get(key);
	}

	public static boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public static void remove(String key) {
		map.remove(key);
	}

	public static int size() {
		return map.size();
	}

	public static void clear() {
		if (size() > 0) {
			map.clear();
		}
		timer.cancel();
	}
}
