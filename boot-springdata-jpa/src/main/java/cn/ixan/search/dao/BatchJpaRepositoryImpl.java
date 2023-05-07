package cn.ixan.search.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/7 21:36
 * @description BatchJpaRepository impl
 */
@Repository
public abstract class BatchJpaRepositoryImpl<T, ID> implements IBatchJpaRepository<T, ID> {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 批量插入
	 *
	 * @param list 实体类集合
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void batchInsert(List<T> list, Integer batchSize) {
		if (!ObjectUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); ) {
				entityManager.persist(list.get(i++));
				if (i % batchSize == 0) {
					entityManager.flush();
					entityManager.clear();
				}
			}
			entityManager.flush();
			entityManager.clear();
		}
	}

	/**
	 * 批量更新
	 *
	 * @param list 实体类集合
	 */
	@Override
	public void batchUpdate(List<T> list, Integer batchSize) {
		if (!ObjectUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); ) {
				entityManager.merge(list.get(i++));
				if (i % batchSize == 0) {
					entityManager.flush();
					entityManager.clear();
				}
			}
			entityManager.flush();
			entityManager.clear();
		}
	}
}
