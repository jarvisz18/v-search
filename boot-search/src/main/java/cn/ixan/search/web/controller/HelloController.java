package cn.ixan.search.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String test1() {
        log.info("hello----------");
        return "hello world!" + new Random().nextInt(100);
    }
}
