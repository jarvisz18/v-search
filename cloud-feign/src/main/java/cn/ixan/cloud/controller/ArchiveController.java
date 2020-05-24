package cn.ixan.cloud.controller;

import cn.ixan.cloud.feign.SearchFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/5/13 19:07
 * @description
 */
@RestController
public class ArchiveController {
    @Autowired
    private SearchFeignClient searchFeignClient;

    @GetMapping("/batchArchiveInsert")
    public String batchArchiveInsert() {
        List<String> param = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            param.add(UUID.randomUUID().toString());
        }
        searchFeignClient.batchArchiveInsert(param);
        return "ok";
    }
}
