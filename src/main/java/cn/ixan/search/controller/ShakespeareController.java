package cn.ixan.search.controller;

import cn.ixan.search.domain.ResultBean;
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
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stack_zhang@outlook.com
 * <p>对shakespeare数据操作</p>
 */
@Api("对shakespeare数据操作")
@RestController
@Slf4j
public class ShakespeareController {

    @Resource
    private ShakespeareService shakespeareService;

    @ApiOperation(value = "获取索引settings信息",notes = "获取索引settings信息")
    @GetMapping("/settings/{indexName}/{indexType}")
    public ResultBean<String> settings(@PathVariable("indexName") String indexName,
                                    @PathVariable("indexType") String indexType){
        return shakespeareService.settings(indexName,indexType);
    }

    @ApiOperation(value = "获取索引mapping信息",notes = "获取索引mapping信息")
    @GetMapping("/mapping/{indexName}/{indexType}")
    public ResultBean<String> mapping(@PathVariable("indexName") String indexName,
                                    @PathVariable("indexType") String indexType){
        return shakespeareService.mapping(indexName,indexType);
    }

    @ApiOperation(value = "重新加载数据",notes = "重新加载数据")
    @PostMapping("/shake/reload")
    public Map<String, Object> reload(){
        return shakespeareService.reload();
    }

    @ApiOperation(value = "清空数据",notes = "清空数据")
    @PostMapping("/shake/clean")
    public boolean clean(){
        return shakespeareService.clean();
    }

    @ApiOperation(value = "初始化数据",notes = "初始化数据")
    @PostMapping("/shake/init")
    public boolean init(){
        return shakespeareService.init();
    }

    @ApiOperation(value = "备份数据",notes = "备份数据")
    @PostMapping("/shake/backup")
    public Map<String, Object> backup(){
        Map<String,Object> result = new HashMap<>();
        Instant now = Instant.now();
        boolean backup = shakespeareService.backupToDatabase();
        Instant end = Instant.now();
        long millis = Duration.between(now, end).toMillis();
        result.put("result",backup);
        result.put("millis",millis+"ms");
        return result;
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
