package com.ixan.boot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.ixan.boot.config.Result;
import com.ixan.boot.config.ResultGenerate;
import com.ixan.boot.domain.Holiday;
import com.ixan.boot.domain.dto.HolidayDTO;
import com.ixan.boot.service.HolidayService;
import com.ixan.boot.utils.DateTool;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static java.time.format.DateTimeFormatter.ISO_DATE;

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

	@ApiOperation(value = "修改节假日", notes = "修改节假日")
	@PostMapping("/updateHoliday")
	public Result<Integer> updateHoliday(@RequestBody Holiday param) {
		Holiday holiday = new Holiday();
		if (StringUtils.isBlank(param.getId())) {
			throw new RuntimeException("参数错误,id" + param.getId());
		}
		holiday.setId(param.getId());
		holiday.setMark(param.getMark());
		holiday.setIsDelete(param.getIsDelete());
		return ResultGenerate.success(holidayService.updateHoliday(holiday));
	}

	@ApiOperation(value = "删除节假日", notes = "删除节假日")
	@DeleteMapping("/{id}")
	public Result<Integer> deleteHoliday(@PathVariable("id") String id) {
		if (StringUtils.isBlank(id)) {
			return ResultGenerate.fail();
		}
		return ResultGenerate.success(holidayService.deleteHoliday(id));
	}

	@ApiOperation(value = "批量新增节假日", notes = "批量新增节假日")
	@PostMapping("/addHolidays")
	public Result<Integer> addHolidays(@RequestBody Map<String, Object> paramMap) {
		Object object = paramMap.get("holiday");
		Map<String, Object> jsonObject = JSONObject.parseObject(JSON.toJSONString(object), new TypeReference<Map<String, Object>>() {
		});
		Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
		int count = 0;
		for (Map.Entry<String, Object> entry : entries) {
			Holiday holiday = new Holiday();
			Map value = (Map) entry.getValue();
			Boolean isHoliday = (Boolean) value.get("holiday");
			if (!isHoliday) {
				continue;
			}

			String date = (String) value.get("date");
			String name = (String) value.get("name");
			LocalDate localdate = LocalDate.parse(date, ISO_DATE);
			holiday.setHoliday(DateTool.fromLocalDate(localdate));
			holiday.setMark(name);

			count += holidayService.addHoliday(holiday);
		}
		return ResultGenerate.success(count);
	}

	@ApiOperation(value = "新增节假日", notes = "新增节假日")
	@PostMapping("/addHoliday")
	public Result<Integer> addHoliday(@RequestBody Map<String, Object> paramMap) {
		Holiday holiday = new Holiday();
		Boolean isHoliday = (Boolean) paramMap.get("holiday");
		if (!isHoliday) {
			return ResultGenerate.fail();
		}

		String date = (String) paramMap.get("date");
		String name = (String) paramMap.get("name");
		LocalDate localdate = LocalDate.parse(date, ISO_DATE);
		holiday.setHoliday(DateTool.fromLocalDate(localdate));
		holiday.setMark(name);
		return ResultGenerate.success(holidayService.addHoliday(holiday));
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
