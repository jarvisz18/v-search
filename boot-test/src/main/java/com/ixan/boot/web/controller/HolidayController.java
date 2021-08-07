package com.ixan.boot.web.controller;

import com.github.pagehelper.PageInfo;
import com.ixan.boot.config.Result;
import com.ixan.boot.config.ResultGenerate;
import com.ixan.boot.domain.Holiday;
import com.ixan.boot.domain.dto.HolidayDTO;
import com.ixan.boot.service.HolidayService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/7 下午7:36
 * @description 节假日
 */
@RestController
@Slf4j
@RequestMapping("/holiday")
public class HolidayController {
	@Autowired
	private HolidayService holidayService;

	@ApiOperation(value = "指定日期范围有几个节假日", notes = "指定日期范围有几个节假日")
	@PostMapping("/numHolidays")
	public Result<Integer> numHolidays(@RequestBody Map<String, String> paramMap) {
		String startTime = paramMap.get("startTime");
		String endTime = paramMap.get("endTime");
		return ResultGenerate.success(holidayService.numHolidayOfBetweenTime(startTime, endTime));
	}

	@ApiOperation(value = "指定天是否节假日", notes = "指定天是否节假日")
	@PostMapping("/isHoliday")
	public Result<Boolean> isHoliday(@RequestBody HolidayDTO holidayDTO) {
		return ResultGenerate.success(holidayService.isHoliday(holidayDTO));
	}

	@ApiOperation(value = "查询所有节假日数据", notes = "查询所有节假日数据")
	@PostMapping("/findAll")
	public Result<PageInfo<Holiday>> findAll(@RequestBody HolidayDTO holidayDTO) {
		return ResultGenerate.success(holidayService.findAll(holidayDTO));
	}

	@ApiOperation(value = "初始化节假日数据", notes = "初始化节假日数据")
	@PostMapping("/init")
	public String init() {
		holidayService.init();
		return "ok";
	}
}
