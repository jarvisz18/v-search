package com.ixan.boot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ixan.boot.domain.Holiday;
import com.ixan.boot.domain.dto.HolidayDTO;
import com.ixan.boot.mapper.HolidayMapper;
import com.ixan.boot.utils.DateTool;
import com.ixan.boot.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author stackzhang@126.com
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
		LocalDateTime holiday = holidayDTO.getHoliday();
		if (Objects.isNull(holiday)) {
			return false;
		}

		LocalDateTime startTime = DateTool.getDayStart(holiday);
		LocalDateTime endTime = DateTool.getDayEnd(holiday);
		String ofStartTime = DateTool.formatLocalDateTime(startTime);
		String ofEndTime = DateTool.formatLocalDateTime(endTime);
		int count = holidayMapper.findHoliday(ofStartTime, ofEndTime);
		return count > 0;
	}

	@Override
	public Integer numHolidayOfBetweenTime(String startTime, String endTime) {
		return holidayMapper.findHoliday(startTime, endTime);
	}

	@Override
	public int addHoliday(Holiday holiday) {
		holiday.setId(UuidUtil.get32UUID());
		holiday.setIsDelete(0);
		holiday.setCreateTime(new Date());
		holiday.setUpdateTime(new Date());
		holiday.setCurrentYear(LocalDate.now().getYear());
		return holidayMapper.insert(holiday);
	}

	@Override
	public int deleteHoliday(String id) {
		return holidayMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateHoliday(Holiday holiday) {
		return holidayMapper.updateByPrimaryKeySelective(holiday);
	}
}