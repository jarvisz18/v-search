package com.ixan.boot.service;

import com.ixan.boot.domain.vo.CacheEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Random;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/10/6 16:08
 * @description 缓存服务
 */
@Service
public class CacheServiceImpl implements CacheService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);

	@Resource(name = "concurrentMapCacheManager")
	private CacheManager concurrentMapCacheManager;

	/**
	 * <p> value 缓存名，必填，它指定了你的缓存存放在哪块命名空间
	 */
	@Cacheable(value = "accountCache", cacheManager = "concurrentMapCacheManager")
	@Override
	public CacheEntity create(Integer cacheId) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		LOGGER.info("real query account:{}", cacheId);
		return getFromDB(cacheId);
	}

	private CacheEntity getFromDB(Integer cacheId) {
		LOGGER.info(("real querying db..." + cacheId));
		return new CacheEntity(cacheId, "somebody" + new Random().nextInt(100));
	}

	@CacheEvict(value = "accountCache", allEntries = true, beforeInvocation = true)
	@Override
	public void clear() {
		LOGGER.info("clear accountCache ... ");
	}

	@Override
	public Object findAll(String name) {
		String cacheName = StringUtils.defaultString(name, "accountCache");
		Cache accountCache = concurrentMapCacheManager.getCache(cacheName);
		if (Objects.isNull(accountCache)) {
			LOGGER.info("cache name:{} not found", name);
			return new Object();
		}
		Object nativeCache = accountCache.getNativeCache();
		LOGGER.info("get name:{},cache value:{}", name, nativeCache);
		return nativeCache;
	}

	@CachePut(value = "accountCache", key = "#cacheId")
	@Override
	public CacheEntity update(Integer cacheId) {
		LOGGER.info(("real update db..." + cacheId));
		return new CacheEntity(cacheId, "somebody" + new Random().nextInt(100));
	}

	@CacheEvict(value = "accountCache", key = "#cacheId")
	@Override
	public void delete(Integer cacheId) {
		LOGGER.info(("real delete db..." + cacheId));
	}

	@Override
	public CacheEntity find(Integer cacheId) {
		Cache accountCache = concurrentMapCacheManager.getCache("accountCache");
		if (Objects.isNull(accountCache)) {
			return CacheEntity.empty();
		}
		return accountCache.get(cacheId, CacheEntity.class);
	}
}
