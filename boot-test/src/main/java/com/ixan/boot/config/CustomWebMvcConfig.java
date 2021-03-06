package com.ixan.boot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/6/30 15:06
 * @description web mvc configuration
 */
@Configuration
@Slf4j
public class CustomWebMvcConfig implements WebMvcConfigurer {

	/**
	 * 配置消息转换器
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//需要定义一个convert转换消息的对象;
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//添加fastJson的配置信息;
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(
				//  List类型字段为null时输出[]而非null
				SerializerFeature.WriteNullListAsEmpty,
				//  显示空字段
				SerializerFeature.WriteMapNullValue,
				//  字符串类型字段为null时间输出""而非null
				SerializerFeature.WriteNullStringAsEmpty,
				//  Boolean类型字段为null时输出false而非null
				SerializerFeature.WriteNullBooleanAsFalse,
				//  美化json输出，否则会作为整行输出
				SerializerFeature.PrettyFormat,
				//  数值字段如果为null,输出为0,而非null
				SerializerFeature.WriteNullNumberAsZero,
				//  Boolean字段如果为null,输出为false,而非null
				SerializerFeature.WriteNullBooleanAsFalse,
				//  时间格式yyyy-MM-dd HH:mm:ss
				SerializerFeature.WriteDateUseDateFormat,
				//  禁用循环引用检测
				SerializerFeature.DisableCircularReferenceDetect);
		//全局时间配置
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setCharset(StandardCharsets.UTF_8);
		//处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		//在convert中添加配置信息.
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(0, fastConverter);
	}
}
