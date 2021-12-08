package cn.ixan.search.web;

import cn.ixan.search.web.dto.UserDTO;
import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/10 15:02
 * @description kafka product
 * @version 1.0
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
