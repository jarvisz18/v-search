package com.ixan.boot.domain.base;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/12 下午7:03
 * @description 基本实体的父实现
 */
public class BaseEntity implements Serializable {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
