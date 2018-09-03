package cn.ixan.search.domain;

import lombok.Data;

/**
 * 自定义返回实体
 * @param <T>
 */
@Data
public class ResultBean<T> {
	private Integer code;
	private String msg;
	private T data;
}
