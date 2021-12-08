package com.ixan.boot.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/1 10:01 下午
 * @description persion vo
 */
@Data
public class PersonVO implements Serializable {
	private Integer id;
	private String username;
	private Long birthday;
}
