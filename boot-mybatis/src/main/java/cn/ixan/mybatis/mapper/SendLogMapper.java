package cn.ixan.mybatis.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author stackzhang@126.com
 * @date Created in 2023/6/24 22:10
 * @description send log mapper
 */
public interface SendLogMapper {
	Integer getLogCount();

	@MapKey("id")
	Map<String,Object> findById(@Param("id") Integer id);

	@MapKey("id")
	Map<String,Object> findByTypeLike(@Param("type") String type);
}
