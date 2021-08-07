package com.ixan.boot.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/7 下午8:24
 * @description 节假日查询
 */
@Data
public class HolidayDTO implements Serializable {
	private Integer pageNum = 1;
	private Integer pageSize = 10;

	private String holiday;
}
