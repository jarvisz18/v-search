package cn.ixan.search.web.controller;

import cn.ixan.search.web.dto.Message;
import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/9/10 15:32
 * @description product controller
 * @version 1.0
 */
@RestController
public class ProductController {
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;

	@PostMapping("/send")
	public void send(@RequestBody Message message) {
		kafkaTemplate.send(message.getTopic(), new Gson().toJson(message.getContent()));
	}
}
