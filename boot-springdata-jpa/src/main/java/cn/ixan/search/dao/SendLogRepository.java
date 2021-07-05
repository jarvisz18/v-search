package cn.ixan.search.dao;

import cn.ixan.search.domain.SendLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2021/7/5 10:11
 * @description
 * @version 1.0
 */
@Repository
public interface SendLogRepository extends JpaRepository<SendLog, Integer> {
	/**
	 * 派生的通过解析方法名称的查询
	 * @param templateName
	 * @return
	 */
	List<SendLog> findSendLogByTemplateName(String templateName);

	/**
	 * HQL
	 * @param templateName
	 * @return
	 */
	@Query(value = "from SendLog s where s.templateName=:templateName")
	List<SendLog> findByTempLateName(String templateName);

	/**s
	 * 原生sql
	 * @param templateName
	 * @return
	 */
	@Query(value = "select s.* from send_log s where s.templateName = :templateName", nativeQuery = true)
	List<SendLog> findByTempLateNameNative(String templateName);
}
