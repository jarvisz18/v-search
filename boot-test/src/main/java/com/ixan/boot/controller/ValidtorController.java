package com.ixan.boot.controller;

import com.ixan.boot.config.Result;
import com.ixan.boot.config.ResultGenerate;
import com.ixan.boot.controller.dto.ContractDTO;
import com.ixan.boot.domain.base.ApplicationContextUtil;
import com.ixan.boot.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/29 9:09
 * @description
 */
@RestController
@Slf4j
public class ValidtorController {

    @PostMapping("/valid")
    public Result<String> valid(@Validated @RequestBody ContractDTO contractDTO){
        AccountMapper bean = ApplicationContextUtil.getBean(AccountMapper.class);
        log.info("对象:[{}]",bean);
        log.info("对象:[{}]", ToStringBuilder.reflectionToString(bean));
        log.info("对象 cond_id :[{}]", contractDTO.getCon_id());
        log.info("测试 Jrebel cond_id :[{}]", contractDTO.getCon_id());
        String con_id = contractDTO.getCon_id();
        if(StringUtils.isBlank(con_id)){
            return ResultGenerate.fail(con_id);
        }
        return ResultGenerate.success(con_id);
    }

}
