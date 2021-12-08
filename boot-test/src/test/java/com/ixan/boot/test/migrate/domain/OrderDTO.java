package com.ixan.boot.test.migrate.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/20 下午6:29
 * @description order
 */
@Data
public class OrderDTO {
	/**
	 * 订单编号
	 */
	private Integer orderId;
	/**
	 * 订单名称
	 */
	private String orderName;
	/**
	 * 创建时间
	 */
	private Date orderTime;
}
