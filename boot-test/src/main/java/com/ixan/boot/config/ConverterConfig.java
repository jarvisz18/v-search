package com.ixan.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/7 下午9:05
 * @description 日期序列化
 */
//@Configuration
public class ConverterConfig {
	@Bean
	public DateJacksonConverter dateJacksonConverter() {
		return new DateJacksonConverter();
	}

	@Bean
	public Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean(
			@Autowired
					DateJacksonConverter dateJacksonConverter) {
		Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();

		jackson2ObjectMapperFactoryBean.setDeserializers(dateJacksonConverter);
		return jackson2ObjectMapperFactoryBean;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
			@Autowired
					ObjectMapper objectMapper) {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
				new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
		return mappingJackson2HttpMessageConverter;
	}
}
