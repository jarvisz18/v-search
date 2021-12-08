package com.ixan.boot.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/6/27 0:15
 * @description
 */
@Data
public class AccountDTO implements Serializable {
	private Integer pageNum = 1;
	private Integer pageSize = 10;
	private String username;
	private String password;
	private String site;
	private String site_name;
}
