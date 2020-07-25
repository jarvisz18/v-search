package cn.ixan.search.web.controller;

import cn.ixan.search.domain.BaseIndexDTO;
import cn.ixan.search.service.ThirdSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@Api(tags = "通用索引数据操作")
@RestController
@Slf4j
public class ThirdSystemController {

    @Resource
    private ThirdSystemService thirdSystemService;

    @ApiOperation(value = "查询索引重复数据")
    @PostMapping("/system/repeat")
    public Map<String, Object> repeat(@RequestBody @Valid BaseIndexDTO baseIndexDTO) {
        return thirdSystemService.repeat(baseIndexDTO);
    }
}
