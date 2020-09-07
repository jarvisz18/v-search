package cn.ixan.search.service;

import cn.ixan.search.domain.BaseIndexDTO;
import cn.ixan.search.web.controller.dto.ThirdQueryDTO;

import java.util.Map;

public interface ThirdSystemService {
	/**
	 * 通用索引查询
	 *
	 * @param queryDTO 查询实体
	 * @return Map<String, Object>
	 */
	Map<String, Object> search(ThirdQueryDTO queryDTO);

	Map<String, Object> repeat(BaseIndexDTO baseIndexDTO);

}
