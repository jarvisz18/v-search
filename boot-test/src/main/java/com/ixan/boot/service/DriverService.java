package com.ixan.boot.service;

import com.ixan.boot.domain.event.DriverChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/9/16 下午9:06
 * @description 驱动管理类
 */
@Service
public class DriverService implements ApplicationEventPublisherAware {
	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void change(String id, Integer op) {
		//执行业务逻辑
		logger.info("driver id:[{}] is change,op:[{}]", id, op);
		//发布事件
		applicationEventPublisher.publishEvent(new DriverChangeEvent(this, id, op));
	}
}
