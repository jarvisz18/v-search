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
	@Cacheable(value = "accountCache")
// 使用了一个缓存名叫 accountCache
	CacheEntity getAccountByName(String userName);

	@CacheEvict(value = "accountCache", beforeInvocation = true)
	void reload();
}
