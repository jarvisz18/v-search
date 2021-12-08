package cn.ixan.search;

import cn.ixan.search.web.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

import java.util.Date;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/10 14:56
 * @description kafka application
 * @version 1.0
 */
@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class KafkaApplication {
	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Autowired
	private Product product;

	//@PostConstruct
	public void init() {
		for (int i = 0; i < 10; i++) {
			product.send("v-search" + new Date());
		}
	}
}
