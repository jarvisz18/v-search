package cn.ixan.search.dao;

import cn.ixan.search.domain.SendLog;
import cn.ixan.search.domain.SendLogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/7/5 10:11
 * @description
 */
@Repository
public interface SendLogRepository extends JpaRepository<SendLog, Integer> {
	/**
	 * 派生的通过解析方法名称的查询
	 *
	 * @param templateName
	 * @return
	 */
	List<SendLog> findSendLogByTemplateName(String templateName);

	@Modifying
	@Query("update SendLog set templateName=:templateName where id=:id and type=:type")
	int updateSendLogByIdAndType(@Param("id") Long id, @Param("type") String type, @Param("templateName") String templateName);

	@Query("from SendLog s where s.templateName like :template%")
	List<SendLog> findByCondition(@Param("template") String template);

	@Query("from SendLog s where s.type in (:typeList)")
	List<SendLog> findSendLogByTypeList(@Param("typeList") List<String> typeList);

	/**
	 * HQL
	 *
	 * @param templateName
	 * @return
	 */
	@Query(value = "from SendLog s where s.templateName=:templateName")
	List<SendLog> findByTempLateName(String templateName);

	/**
	 * 原生sql
	 *
	 * @param templateName
	 * @return
	 */
	@Query(value = "select s.* from send_log s where s.templateName = :templateName", nativeQuery = true)
	List<SendLog> findByTempLateNameNative(String templateName);

	/**
	 * jpa 动态条件且分页，使用IF进行三元判断
	 * countProjection 指定根据哪个字段计算总数
	 *
	 * @param type     类型
	 * @param pageable 分页参数
	 * @return Page<SendLog>
	 */
	@Query(value = "select s.* from send_log as s where 1=1" +
			"  and if(:type is not null and :type != '', s.type = :type,1=1)", nativeQuery = true, countProjection = "s.id")
	Page<SendLog> findSendLogsByType(@Param("type") String type, Pageable pageable);

	@Query(value = "select s.* from send_log as s where 1=1" +
			"  and if(:#{#sendLogDTO.type} is not null and :#{#sendLogDTO.type} != '', s.type = :#{#sendLogDTO.type},1=1)",
			nativeQuery = true, countProjection = "s.id")
	Page<SendLog> findSendLogsByType2(SendLogDTO sendLogDTO, Pageable pageable);

	@Query(value = "select s.* from send_log as s where 1=1" +
			"  and (:#{#sendLogDTO.type} is null or s.type = :#{#sendLogDTO.type})", nativeQuery = true, countProjection = "s.id")
	Page<SendLog> findSendLogsByType3(SendLogDTO sendLogDTO, Pageable pageable);

	@Query(value = "select s.* from send_log as s where 1=1" +
			"  and (:type is null or s.type = :type)", nativeQuery = true, countProjection = "s.id")
	Page<SendLog> findSendLogsByType4(@Param("type") String type, Pageable pageable);

	@Query(value = "select s from SendLog as s where 1=1" +
			"  and (:type is null or s.type = :type)", countProjection = "s.id")
	Page<SendLog> findSendLogsByType5(@Param("type") String type, Pageable pageable);


}
