package cn.ixan.search.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/6 11:21
 * @description kafka template config
 */
@Configuration
public class KafkaTemplateConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServersConfig;
	@Value("${spring.kafka.producer.retries}")
	private Integer retriesConfig;
	@Value("${spring.kafka.producer.acks}")
	private String acksConfig;
	@Value("${spring.kafka.producer.batch-size}")
	private Integer batchSizeConfig;
	@Value("${spring.kafka.producer.buffer-memory}")
	private Integer bufferMemoryConfig;

	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConfig);
		props.put(ProducerConfig.RETRIES_CONFIG, retriesConfig);
		props.put(ProducerConfig.ACKS_CONFIG, acksConfig);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSizeConfig);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemoryConfig);
		props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 5048576);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return props;
	}

	/**
	 * 获取工厂
	 */
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	/**
	 * 注册实例
	 */
	@Bean(name = "kafkaTemplate")
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
}
