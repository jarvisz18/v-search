package com.ixan.boot.service;

import com.github.pagehelper.PageInfo;
import com.ixan.boot.domain.Holiday;
import com.ixan.boot.domain.dto.HolidayDTO;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/7 下午7:40
 * @description 节假日
 */
public interface HolidayService {
	void init();

	PageInfo<Holiday> findAll(HolidayDTO holidayDTO);

	boolean isHoliday(HolidayDTO holidayDTO);

	Integer numHolidayOfBetweenTime(String startTime, String endTime);
}
