package cn.ixan.docker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/2/13 15:22
 * @description docker application
 */
@SpringBootApplication
@RestController
@Slf4j
public class DockerApplication {

    @GetMapping("/test")
    public String test(){
        String s = "1. Hello world ! " + new SimpleDateFormat(" [yyyy-mm-dd  HH:mm:ss]").format(new Date());
        log.info("docker "+ s);
        return s;
    }

    public static void main(String[] args) {
        SpringApplication.run(DockerApplication.class,args);
    }
}
