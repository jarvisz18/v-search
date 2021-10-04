package com.ixan.boot.service;

import com.ixan.boot.domain.ChinaRegions;
import com.ixan.boot.mapper.ChinaRegionsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/7 14:30
 * @description ChinaRegionsServiceImpl
 * @version 1.0
 */
@Service
public class ChinaRegionsServiceImpl implements ChinaRegionsService {
	@Resource
	private ChinaRegionsMapper chinaRegionsMapper;

	@Override
	public void saveBatch(List<ChinaRegions> regionsInfoList) {
		chinaRegionsMapper.insertList(regionsInfoList);
	}
}
