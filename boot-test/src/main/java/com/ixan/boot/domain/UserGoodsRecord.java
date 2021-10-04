package com.ixan.boot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/10/4 下午4:41
 * @description 用户下单记录（秒杀记录）
 */
@Data
@Table(name = "tb_user_goods_record")
public class UserGoodsRecord {
	@Id
	@Column(name = "id")
	private String id;
	/**
	 * 用户标识
	 */
	@Column(name = "user_id")
	private String userId;
	/**
	 * 商品标识
	 */
	@Column(name = "goods_id")
	private String goodsId;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 订单号
	 */
	@Column(name = "order_id")
	private String orderId;
}
