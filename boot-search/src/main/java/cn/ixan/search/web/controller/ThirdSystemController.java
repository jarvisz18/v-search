package cn.ixan.search.web.controller;

import cn.ixan.search.domain.BaseIndexDTO;
import cn.ixan.search.service.ThirdSystemService;
import cn.ixan.search.web.controller.dto.IndexParamDTO;
import cn.ixan.search.web.controller.dto.ThirdQueryDTO;
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

    @ApiOperation(value = "通用索引条件删除")
    @PostMapping("/system/deleteByParam")
    public Map<String, Object> deleteByParam(@RequestBody IndexParamDTO indexParamDTO) {
        return thirdSystemService.deleteByParam(indexParamDTO);
    }

    @ApiOperation(value = "通用索引删除")
    @PostMapping("/system/delete")
    public Map<String, Object> delete(@RequestBody IndexParamDTO indexParamDTO) {
        return thirdSystemService.delete(indexParamDTO);
    }

    @ApiOperation(value = "通用索引更新")
    @PostMapping("/system/update")
    public Map<String, Object> update(@RequestBody IndexParamDTO indexParamDTO) {
        return thirdSystemService.update(indexParamDTO);
    }

    @ApiOperation(value = "通用索引新增")
    @PostMapping("/system/insert")
    public Map<String, Object> insert(@RequestBody IndexParamDTO indexParamDTO) {
        return thirdSystemService.insert(indexParamDTO);
    }

    @ApiOperation(value = "通用索引查询")
    @PostMapping("/system/search")
    public Map<String, Object> search(@RequestBody @Valid ThirdQueryDTO queryDTO) {
        return thirdSystemService.search(queryDTO);
    }

    @ApiOperation(value = "查询索引重复数据")
    @PostMapping("/system/repeat")
    public Map<String, Object> repeat(@RequestBody @Valid BaseIndexDTO baseIndexDTO) {
        return thirdSystemService.repeat(baseIndexDTO);
    }
}
