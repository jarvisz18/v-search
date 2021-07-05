package cn.ixan.search.dao;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/7/5 17:53
 * @description 抽象公用Repository
 * @version 1.0
 */
public abstract class BaseJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {
	public EntityManager em;

	BaseJpaRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}
}
