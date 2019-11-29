package cn.ixan.search.controller;

import cn.ixan.search.domain.Shakespeare;
import cn.ixan.search.service.ShakespeareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stack_zhang@outlook.com
 * <p>对shakespeare数据操作</p>
 */
@RestController
@Slf4j
public class ShakespeareController {

    @Autowired
    private ShakespeareService shakespeareService;

    @PostMapping("/shake/clean")
    public boolean clean(){
        return shakespeareService.clean();
    }

    @PostMapping("/shake/init")
    public boolean init(){
        return shakespeareService.init();
    }

    @PostMapping("/shake/backup")
    public Map<String, Object> backup(){
        Map<String,Object> result = new HashMap<>();
        final Instant now = Instant.now();
        final boolean backup = shakespeareService.backup();
        final Instant end = Instant.now();
        final long millis = Duration.between(now, end).toMillis();
        result.put("result",backup);
        result.put("millis",millis+"ms");
        return result;
    }

    @GetMapping("/shake/query/{from}/{size}")
    public List<Shakespeare> query(@PathVariable Integer from,@PathVariable Integer size){
        return shakespeareService.query(from,size);
    }

    @PostMapping("/shake/save/{from}/{size}")
    public int save(@PathVariable Integer from, @PathVariable Integer size){
        return shakespeareService.save(from,size);
    }

}
