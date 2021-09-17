package com.ixan.boot.domain.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/9/16 下午8:58
 * @description 数据源变更事件
 */
public class DriverChangeEvent extends ApplicationEvent {
	/**
	 * 变化的ID
	 */
	private String id;
	/**
	 * 0:新增 1:修改 2:删除
	 */
	private Integer op;

	public DriverChangeEvent(Object source) {
		super(source);
	}

	public DriverChangeEvent(Object source, String id, Integer op) {
		super(source);
		this.id = id;
		this.op = op;
	}

	public String getId() {
		return id;
	}

	public Integer getOp() {
		return op;
	}
}
