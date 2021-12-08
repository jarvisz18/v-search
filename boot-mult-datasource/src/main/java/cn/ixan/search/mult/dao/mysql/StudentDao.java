package cn.ixan.search.mult.dao.mysql;

import cn.ixan.search.mult.domain.mysql.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/24 16:50
 * @description 学生
 */
@Repository
@Slf4j
public class StudentDao {

	@PersistenceContext(unitName = "jpa-mysql")
	private EntityManager entityManager;

	/**
	 * 使用自定义SQL查询数据
	 */
	public List<Student> findAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from student");
		SQLQuery sqlQuery = entityManager.createNativeQuery(sb.toString()).unwrap(SQLQuery.class);
		org.hibernate.Query query = sqlQuery.setResultTransformer(Transformers.aliasToBean(Student.class));
		return query.list();
	}

	@Transactional(transactionManager = "mysqlTransactionManager", rollbackFor = Exception.class)
	public void save(Student student) {
		entityManager.persist(student);
	}
}
