package com.ixan.boot.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/4/15 23:21
 * @description 客户收货地址业务对象
 * @version 1.0
 */
@ApiModel(value = "Customer receiving address business object")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBO implements Serializable {
	@ApiModelProperty(value = "Recipient's name")
	@NotBlank(message = "Recipient's name cannot be empty")
	private String userName;
	@ApiModelProperty(value = "Recipient's detailed address")
	@NotBlank(message = "Recipient's detailed address cannot be empty")
	private String address;
	@ApiModelProperty(value = "Recipient's telPhone")
	@NotBlank(message = "Recipient's telPhone cannot be empty")
	private String telPhone;
	@ApiModelProperty(value = "The postcode of the addressee's region")
	@NotBlank(message = "The postcode of the addressee's region cannot be empty")
	private String postCode;
}
