package cn.ixan.search.dao;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/7 21:34
 * @description IBatchJpaRepository
 */
public interface IBatchJpaRepository <T, ID>{
	void batchInsert(List<T> list, Integer batchSize);

	void batchUpdate(List<T> list, Integer batchSize);
}
