package cn.ixan.search.web.controller;

import cn.ixan.search.domain.Shakespeare;
import cn.ixan.search.service.ShakespeareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author stackzhang@126.com
 * <p>对shakespeare数据操作</p>
 */
@Api(tags = "对shakespeare数据操作")
@RestController
@Slf4j
public class ShakespeareController {

    @Resource
    private ShakespeareService shakespeareService;


    @ApiOperation(value = "清空数据",notes = "清空数据")
    @PostMapping("/shake/clean")
    public boolean clean(){
        return shakespeareService.clean();
    }

    @ApiOperation(value = "分页查询数据",notes = "分页查询数据")
    @GetMapping("/shake/query/{from}/{size}")
    public List<Shakespeare> query(@PathVariable Integer from,@PathVariable Integer size){
        return shakespeareService.query(from,size);
    }

    @ApiOperation(value = "分页查询数据并存储至ES",notes = "分页查询数据并存储至ES")
    @PostMapping("/shake/save/{from}/{size}")
    public int save(@PathVariable Integer from, @PathVariable Integer size){
        return shakespeareService.save(from,size);
    }

}
