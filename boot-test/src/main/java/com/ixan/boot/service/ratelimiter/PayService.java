package com.ixan.boot.service.ratelimiter;

import java.math.BigDecimal;

/**
 * @author 冰河技术
 * @version 1.0
 * @date Created in 2022/2/19 下午10:56
 * @description 模拟支付
 */
public interface PayService {
	/**
	 * 支付
	 *
	 * @param price 价格
	 * @return int
	 */
	int pay(BigDecimal price);
}
