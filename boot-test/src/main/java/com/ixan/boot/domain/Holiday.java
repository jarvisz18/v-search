package com.ixan.boot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/7 下午7:14
 * @description 节假日
 */
@Data
@Table(name = "holiday")
public class Holiday {
	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	private String id;
	/**
	 * 是否删除
	 */
	@Column(name = "is_delete")
	private Integer isDelete;
	/**
	 * 节假日或周六日
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "holiday")
	private Date holiday;
	/**
	 * 备注
	 */
	@Column(name = "mark")
	private String mark;
	/**
	 * 当前年
	 */
	@Column(name = "current_year")
	private Integer currentYear;
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
