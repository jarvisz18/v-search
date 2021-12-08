package com.ixan.boot.service;

import com.ixan.boot.domain.event.DriverChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/9/16 下午9:17
 * @description 驱动变化业务类
 */
@Service
public class DriverBizService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverBizService.class);

	@EventListener
	public void addDriver(DriverChangeEvent event) {
		LOGGER.info("add driver event:[{}]", event.getId());
	}
}
