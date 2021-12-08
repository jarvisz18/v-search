package com.ixan.boot.service;

import com.ixan.boot.domain.event.DriverChangeEvent;
import com.ixan.boot.utils.ConnectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/9/16 下午9:13
 * @description 监听器
 */
@Service
public class DriverListener implements ApplicationListener<DriverChangeEvent> {
	private static final Logger logger = LoggerFactory.getLogger(DriverListener.class);

	@Override
	public void onApplicationEvent(DriverChangeEvent driverChangeEvent) {
		String id = driverChangeEvent.getId();
		Integer op = driverChangeEvent.getOp();
		logger.info("receive event change id:[{}],op:[{}]", id, op);
		switch (op) {
			case 0:
			case 1:
				ConnectPool.POOL_MAP.put(id, id);
				break;
			case 2:
				ConnectPool.POOL_MAP.remove(id);
				break;
			default:
				throw new RuntimeException("暂不支持此操作");
		}
		ConnectPool.POOL_MAP.forEach((k, v) -> logger.info("k:[{}],v:[{}]", k, v));
		logger.info("--------------------------------------------------");
	}
}
