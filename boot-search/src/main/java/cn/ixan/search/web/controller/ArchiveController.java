package cn.ixan.search.web.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/5/13 18:51
 * @description
 */
@Api(tags = "大批量数据操作")
@RestController
@Slf4j
public class ArchiveController {

    @PostMapping("/archive/batchArchiveInsert")
    public Map<String, Object> batchArchiveInsert(@RequestBody List<String> list) {
        Map<String, Object> map = new HashMap<>();
        log.info("接收成功");
        return map;
    }
}
