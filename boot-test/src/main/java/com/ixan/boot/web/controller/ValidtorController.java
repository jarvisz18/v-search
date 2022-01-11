package com.ixan.boot.web.controller;

import com.ixan.boot.config.Result;
import com.ixan.boot.config.ResultGenerate;
import com.ixan.boot.domain.base.ApplicationContextUtil;
import com.ixan.boot.domain.dto.ContractDTO;
import com.ixan.boot.domain.dto.EmplDTO;
import com.ixan.boot.domain.valid.Save;
import com.ixan.boot.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/6/29 9:09
 * @description 参数校验
 */
@RestController
@Slf4j
@Validated
public class ValidtorController {

	@GetMapping("/api/user/getByAccount")
	public Result<EmplDTO> findEmpl(@Length(min = 3, max = 6) @NotNull String account) {
		EmplDTO emplDTO = new EmplDTO();
		emplDTO.setAccount(account);
		emplDTO.setUserName("校验通过");
		return ResultGenerate.success(emplDTO);
	}

	@GetMapping("/api/user/{userId}")
	public Result<EmplDTO> findEmpl(@PathVariable("userId") @Min(1000L) Long userId) {
		EmplDTO emplDTO = new EmplDTO();
		emplDTO.setUserId(userId);
		emplDTO.setUserName("校验通过");
		return ResultGenerate.success(emplDTO);
	}

	@PostMapping("/save")
	public Result<EmplDTO> saveEmpl(@Validated({Save.class}) @RequestBody EmplDTO emplDTO) {
		return ResultGenerate.success(emplDTO);
	}

	@PostMapping("/valid")
	public Result<String> valid(@Validated @RequestBody ContractDTO contractDTO) {
		AccountMapper bean = ApplicationContextUtil.getBean(AccountMapper.class);
		log.info("对象:[{}]", bean);
		log.info("对象:[{}]", ToStringBuilder.reflectionToString(bean));
		log.info("对象 cond_id :[{}]", contractDTO.getCon_id());
		log.info("测试 Jrebel cond_id :[{}]", contractDTO.getCon_id());
		String con_id = contractDTO.getCon_id();
		if (StringUtils.isBlank(con_id)) {
			return ResultGenerate.fail(con_id);
		}
		return ResultGenerate.success(con_id);
	}

}
