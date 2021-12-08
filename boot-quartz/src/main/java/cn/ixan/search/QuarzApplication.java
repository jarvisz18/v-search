package cn.ixan.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/1 下午12:34
 * @description
 */
@SpringBootApplication
@MapperScan(value = "cn.ixan.search.mapper")
public class QuarzApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuarzApplication.class, args);
	}
}
