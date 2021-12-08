package com.ixan.boot.service;

import com.github.pagehelper.PageInfo;
import com.ixan.boot.domain.Holiday;
import com.ixan.boot.domain.dto.HolidayDTO;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/7 下午7:40
 * @description 节假日
 */
public interface HolidayService {
	void init();

	PageInfo<Holiday> findAll(HolidayDTO holidayDTO);

	boolean isHoliday(HolidayDTO holidayDTO);

	Integer numHolidayOfBetweenTime(String startTime, String endTime);

	int addHoliday(Holiday holiday);

	int deleteHoliday(String id);

	int updateHoliday(Holiday holiday);
}
