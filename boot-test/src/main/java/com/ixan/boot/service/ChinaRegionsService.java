package com.ixan.boot.service;

import com.ixan.boot.domain.ChinaRegions;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/7 14:29
 * @description ChinaRegionsService
 * @version 1.0
 */
public interface ChinaRegionsService {
	void saveBatch(List<ChinaRegions> regionsInfoList);
}
