package com.ixan.boot.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/29 9:29
 * @description
 */
@Data
public class ContractDTO implements Serializable {
	@NotBlank(message = "主键不能为空")
	private String con_id;
}
