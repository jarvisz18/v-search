package com.ixan.boot.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/1 9:59 下午
 * @description pserion dto
 */
@Data
public class PersonDTO implements Serializable {
	private Integer id;
	private String username;
	private Date birthday;
}
