package com.ixan.boot.test;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/10/8 下午9:30
 * @description 用户信息
 */
@Data
public class UserDTO implements Serializable {
	private int id;

	@JSONField(name = "userName")
	private String name;

	/**
	 * 创建时间.
	 * com.alibaba.fastjson.annotation.JSONField
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date createTime;

	/**
	 * 备注
	 * serialize:是否需要序列化属性.
	 */
	@JSONField(serialize = false)
	private String remarks;
}
