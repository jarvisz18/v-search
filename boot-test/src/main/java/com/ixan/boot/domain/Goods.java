package com.ixan.boot.domain;

import com.ixan.boot.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/12 下午7:03
 * @description 商品
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "goods")
public class Goods extends BaseEntity {
	@Id
	private String id;

	@Column(name = "goods_name")
	private String goodsName;
}
