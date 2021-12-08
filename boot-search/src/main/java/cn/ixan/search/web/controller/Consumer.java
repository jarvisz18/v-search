package cn.ixan.search.web.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/9/4 13:44
 * @description consumer message
 */
@Component
public class Consumer {

	//@KafkaListener(topics = "test")
	public void consumer(ConsumerRecord consumerRecord) {
		Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
		if (kafkaMassage.isPresent()) {
			Object o = kafkaMassage.get();
			System.out.println(o);
		}

	}
}
