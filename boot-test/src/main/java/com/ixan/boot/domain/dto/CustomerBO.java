package com.ixan.boot.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/15 23:20
 * @description 客户业务对象
 * @version 1.0
 */
@ApiModel(value = "Customer business object")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBO implements Serializable {

	@Valid
	@NotEmpty(message = "addressBOList cannot be empty")
	@ApiModelProperty(value = "One person has more than one receiving address")
	private List<AddressBO> addressBOList;
}
