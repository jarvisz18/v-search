package com.ixan.boot.domain.vo;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/10/6 15:52
 * @description 缓存测试实体类
 * @version 1.0
 */
public class CacheEntity {
	private final int id;
	private final String name;

	public CacheEntity(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static CacheEntity empty(){
		return new CacheEntity(-1,"not found");
	}
}
