package cn.ixan.search.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/3/1 22:26
 * @description test controller
 */
@RestController
@Slf4j
@Scope("prototype")
public class TestController {
    private int count = 0;

    @PostMapping("/test")
    public Integer test() {
        log.info("当前时间：" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return count++;
    }
}
