package com.ixan.boot.domain.vo;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 15:52
 * @description 缓存测试实体类
 * @version 1.0
 */
public class CacheEntity {
	private int id;
	private String name;

	public CacheEntity() {
	}

	public CacheEntity(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
