package com.ixan.boot.mapper;

import com.ixan.boot.config.BaseMapper;
import com.ixan.boot.domain.Holiday;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/7 下午7:34
 * @description 节假日
 */
@Repository
public interface HolidayMapper extends BaseMapper<Holiday> {
	int findHoliday(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
