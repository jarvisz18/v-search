package cn.ixan.search.web.controller;

import io.searchbox.client.JestClient;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/3/4 9:50
 * @description MultiGet controller
 */
@Api(tags = "MultiGetController")
@RestController
public class MultiGetController {
    @Resource
    private JestClient jestClient;

    @GetMapping("/multiGet")
    public void multiGet() {
    }
}
