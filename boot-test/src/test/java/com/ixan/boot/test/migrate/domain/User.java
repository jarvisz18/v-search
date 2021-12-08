package com.ixan.boot.test.migrate.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/20 下午7:31
 * @description 用户
 */
@Data
public class User {
	private Integer id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
}
