package cn.ixan.search.web.controller;

import cn.ixan.search.web.controller.dto.UserDTO;
import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/9/4 13:42
 * @description product message
 */
@Component
public class Product {
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String name) {
		UserDTO u = new UserDTO();
		u.setUsername(name);
		u.setAge(11);
		kafkaTemplate.send("test", new Gson().toJson(u));
	}
}
