package com.ixan.boot.service;

import com.ixan.boot.domain.vo.CacheEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/10/6 16:21
 * @description CacheService
 * @version 1.0
 */
public interface CacheService {
	/**
	 * 使用了一个缓存名叫 accountCache
	 * @return cache entity
	 */
	@Cacheable(value = "accountCache")
	CacheEntity create(Integer cacheId);

	@CacheEvict(value = "accountCache", beforeInvocation = true)
	void clear();

	Object findAll(String name);

	CacheEntity update(Integer cacheId);

	void delete(Integer cacheId);

	CacheEntity find(Integer cacheId);
}
