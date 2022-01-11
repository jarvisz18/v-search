package com.ixan.boot.domain.dto;

import com.ixan.boot.domain.valid.Save;
import com.ixan.boot.domain.valid.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/1/11 12:18
 * @description 用户信息参数校验
 * @version 1.0
 */
@Data
public class EmplDTO {
	private Long userId;

	@NotNull(groups = {Save.class, Update.class})
	@Length(min = 2, max = 10)
	private String userName;

	@NotNull(groups = {Save.class, Update.class})
	@Length(min = 6, max = 20)
	private String account;

	@NotNull(groups = {Save.class, Update.class})
	@Length(min = 6, max = 20)
	private String password;
}
