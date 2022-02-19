package com.ixan.boot.service.ratelimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 冰河技术
 * @version 1.0
 * @date Created in 2022/2/19 下午11:02
 * @description 模拟支付
 */
@Service
public class PayServiceImpl implements PayService {
	private final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

	@Override
	public int pay(BigDecimal price) {
		logger.info("支付成功===>>" + price);
		return 1;
	}
}
