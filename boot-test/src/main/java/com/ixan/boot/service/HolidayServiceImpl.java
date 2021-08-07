package com.ixan.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ixan.boot.domain.Holiday;
import com.ixan.boot.domain.dto.HolidayDTO;
import com.ixan.boot.mapper.HolidayMapper;
import com.ixan.boot.utils.DateTool;
import com.ixan.boot.utils.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/7 下午7:40
 * @description 节假日
 */
@Service
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	private HolidayMapper holidayMapper;


	@Override
	public void init() {
		LocalDateTime startTime = LocalDateTime.of(2021, 1, 1, 0, 0);
		LocalDateTime endTime = LocalDateTime.of(2021, 12, 31, 0, 0);
		while (startTime.isBefore(endTime)) {
			DayOfWeek dayOfWeek = startTime.getDayOfWeek();
			if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
				Holiday holiday = new Holiday();
				holiday.setId(UuidUtil.get32UUID());
				holiday.setHoliday(DateTool.fromLocalDateTime(startTime));
				holiday.setIsDelete(0);
				holiday.setMark("节假日");
				holiday.setCurrentYear(startTime.getYear());
				holiday.setCreateTime(new Date());
				holiday.setUpdateTime(new Date());
				holidayMapper.insert(holiday);
			}
			startTime = startTime.plusDays(1L);
		}
	}

	@Override
	public PageInfo<Holiday> findAll(HolidayDTO holidayDTO) {
		PageHelper.startPage(holidayDTO.getPageNum(), holidayDTO.getPageSize());
		List<Holiday> accountList = holidayMapper.selectAll();
		return new PageInfo<>(accountList);
	}

	@Override
	public boolean isHoliday(HolidayDTO holidayDTO) {
		String holiday = holidayDTO.getHoliday();
		if (StringUtils.isBlank(holiday)) return false;
		//Pattern compile = Pattern.compile("yyyy-MM-dd");
		//if (!compile.matcher(holiday).find()) return false;
		int count = holidayMapper.findHoliday(holiday + " 00:00:00", holiday + " 23:59:59");
		return count > 0;
	}

	@Override
	public Integer numHolidayOfBetweenTime(String startTime, String endTime) {
		return holidayMapper.findHoliday(startTime, endTime);
	}
}