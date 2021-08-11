package com.ixan.boot.test.fastjson;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/11 下午8:17
 * @description user
 */
@AllArgsConstructor
@Data
public class User {
	private String id;
	private String name;
	private Integer value;
	private String remark;
}
