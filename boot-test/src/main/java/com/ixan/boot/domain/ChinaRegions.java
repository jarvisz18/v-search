package com.ixan.boot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/7 14:08
 * @description 中国行政区域信息
 * @version 1.0
 */
@Data
@Table(name = "china_regions")
public class ChinaRegions {
	@Id
	private Integer id;
	/**
	 * 行政区域编码
	 */
	@Column(name = "code")
	private String code;

	/**
	 * 行政区域名称
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 行政区域类型，1:省份，2：城市，3：区或者县城
	 */
	@Column(name = "type")
	private Integer type;

	/**
	 * 上一级行政区域编码
	 */
	@Column(name = "parent_code")
	private String parentCode;

	/**
	 * 是否删除 1：已删除；0：未删除
	 */
	@Column(name = "is_delete")
	private Integer isDelete = 0;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
}
