package cn.ixan.search.mult.dao.postgres;

import cn.ixan.search.mult.domain.postgres.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @date Created in 2021/8/24 18:38
 * @description 教师
 * @version 1.0
 */
@Repository
@Slf4j
public class TeacherDao {
	@PersistenceContext(unitName = "jpa-postgres")
	private EntityManager entityManager;

	/**
	 *  使用自定义SQL查询数据
	 */
	public List<Teacher> findAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from teacher");
		SQLQuery sqlQuery = entityManager.createNativeQuery(sb.toString()).unwrap(SQLQuery.class);
		org.hibernate.Query query = sqlQuery.setResultTransformer(Transformers.aliasToBean(Teacher.class));
		return query.list();
	}
}
