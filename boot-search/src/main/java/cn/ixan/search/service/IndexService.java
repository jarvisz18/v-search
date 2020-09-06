package cn.ixan.search.service;

import cn.ixan.search.domain.ResultBean;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/6 19:27
 * @description index service
 */
public interface IndexService {
	/**
	 * 获取索引settings
	 *
	 * @param indexName 索引名称
	 * @param indexType _doc
	 */
	ResultBean<String> settings(String indexName, String indexType);

	/**
	 * 获取索引mappings结构
	 *
	 * @param indexName 索引名称
	 * @param indexType _doc
	 */
	ResultBean<String> mapping(String indexName, String indexType);
}
