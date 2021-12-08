package com.ixan.boot.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/7 下午8:24
 * @description 节假日查询
 */
@Data
public class HolidayDTO implements Serializable {
	private Integer pageNum = 1;
	private Integer pageSize = 10;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime holiday;
}
