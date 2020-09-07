package cn.ixan.search.service;

import cn.ixan.search.domain.BaseIndexDTO;
import cn.ixan.search.web.controller.dto.IndexParamDTO;
import cn.ixan.search.web.controller.dto.ThirdQueryDTO;

import java.util.Map;

public interface ThirdSystemService {
	/**
	 * <p>通用索引删除根据条件
	 *
	 * @param indexParamDTO 实体
	 * @return Map<String, Object>
	 */
	Map<String, Object> deleteByParam(IndexParamDTO indexParamDTO);

	/**
	 * <p>通用索引删除
	 *
	 * @param indexParamDTO 实体
	 * @return Map<String, Object>
	 */
	Map<String, Object> delete(IndexParamDTO indexParamDTO);

	/**
	 * <p>通用索引更新
	 *
	 * @param indexParamDTO 实体
	 * @return Map<String, Object>
	 */
	Map<String, Object> update(IndexParamDTO indexParamDTO);

	/**
	 * <p>通用索引新增
	 *
	 * @param indexParamDTO 实体
	 * @return Map<String, Object>
	 */
	Map<String, Object> insert(IndexParamDTO indexParamDTO);

	/**
	 * 通用索引查询
	 *
	 * @param queryDTO 查询实体
	 * @return Map<String, Object>
	 */
	Map<String, Object> search(ThirdQueryDTO queryDTO);

	Map<String, Object> repeat(BaseIndexDTO baseIndexDTO);

}
