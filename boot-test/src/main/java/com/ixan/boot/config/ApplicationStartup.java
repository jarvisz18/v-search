package com.ixan.boot.config;

import com.ixan.boot.utils.ConnectPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/11/11 13:33
 * @description 应用准备完成监听器
 * @version 1.0
 */
@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		log.info("this is zero-knowledge application!");
		ConnectPool.POOL_MAP.forEach((k, v) -> log.info("k:[{}],v:[{}]", k, v));
		log.info("--------------------------------------------------");
	}
}
