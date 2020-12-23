package com.ixan.boot.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/11/11 11:04
 * @description user gender
 * @version 1.0
 */
@Data
@Entity
@Table(name = "user_gender")
public class UserGender {
	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_gender")
	private String userGender;
}
