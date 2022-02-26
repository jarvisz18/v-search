package com.ixan.boot.test.base.jdbc.dao;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/21 下午9:24
 * @description 基础的数据查询(Data Access Object)
 */
public interface BaseQueryDao {
	/**
	 * 根据主键查询
	 *
	 * @param arg
	 * @return
	 */
	<T> T findById(Class<T> clazz, String sql, Object arg);

	/**
	 * 查询全部
	 */
	<T> List<T> findAll(Class<T> clazz, String sql);

	/**
	 * 根据主键删除
	 */
	int deleteById(String sql, Object arg);

	/**
	 * 更新
	 *
	 * @return
	 */
	int update(String sql, Object... args);


}
