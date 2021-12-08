package cn.ixan.search.dao;

import cn.ixan.search.domain.SendLog;
import cn.ixan.search.domain.SendLogDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/7/5 17:55
 * @description 日志
 * @version 1.0
 */
@Repository
public class SendLogJpaRepository extends BaseJpaRepository<SendLog, Integer> {

	public SendLogJpaRepository(EntityManager em) {
		super(SendLog.class, em);
	}

	public Page<SendLog> findAll(SendLogDTO logDto, Pageable pageable) {
		Specification specification = (Specification) (root, query, criteriaBuilder) -> {
			Predicate predicate = criteriaBuilder.conjunction();
			if (!StringUtils.isEmpty(logDto.getTemplateName())) {
				predicate.getExpressions().add(criteriaBuilder.like(root.get("templateName"), logDto.getTemplateName()));
			}
			query.where(predicate);
			return predicate;
		};
		return findAll(specification, pageable);
	}
}
