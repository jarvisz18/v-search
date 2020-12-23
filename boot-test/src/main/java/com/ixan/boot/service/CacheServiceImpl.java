package com.ixan.boot.service;

import com.ixan.boot.domain.CacheEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 16:08
 * @description 缓存服务
 * @version 1.0
 */
@Service
public class CacheServiceImpl implements CacheService {
	private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

	/**
	 * <p> value 缓存名，必填，它指定了你的缓存存放在哪块命名空间
	 *
	 * @param userName
	 * @return
	 */
	@Cacheable(value = "accountCache", cacheManager = "concurrentMapCacheManager")
	@Override
	public CacheEntity getAccountByName(String userName) {
		// 方法内部实现不考虑缓存逻辑，直接实现业务
		logger.info("real query account." + userName);
		return getFromDB(userName);
	}

	private CacheEntity getFromDB(String acctName) {
		logger.info(("real querying db..." + acctName));
		return new CacheEntity(acctName);
	}

	@CacheEvict(value = "accountCache", allEntries = true, beforeInvocation = true)
	@Override
	public void reload() {
		logger.info("reload accountCache ... ");
	}
}
