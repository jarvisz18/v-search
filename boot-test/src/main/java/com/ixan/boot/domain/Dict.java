package com.ixan.boot.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 15:31
 * @description 字典
 * @version 1.0
 */
@Data
public class Dict implements Serializable {
	private Date createTime;
	private Date updateTime;
	private Long version;
}
