package com.ixan.boot.web.controller.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import com.ixan.boot.service.ratelimiter.MessageService;
import com.ixan.boot.service.ratelimiter.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author 冰河技术
 * @version 1.0
 * @date Created in 2022/2/19 下午11:04
 * @description 测试接口限流
 * {@link RateLimiter}
 */
@RequestMapping("/api/v1")
@RestController
public class PayController {
	private final Logger logger = LoggerFactory.getLogger(PayController.class);
	/**
	 * RateLimiter的create()方法中传入一个参数，表示以固定的速率1r/s,即以每秒1个令牌的速率向桶中放入令牌
	 */
	private RateLimiter rateLimiter = RateLimiter.create(1);

	@Autowired
	private MessageService messageService;
	@Autowired
	private PayService payService;

	@RequestMapping("/send/message")
	public String sendMessage() {
		String result = "";
		boolean tryAcquire = rateLimiter.tryAcquire(500, TimeUnit.MICROSECONDS);
		if (!tryAcquire) {
			result = "请求过多，降级处理";
			logger.info(result);
			return result;
		}
		boolean flag = messageService.sendMessage("恭喜您成长值+1");
		if (flag) {
			result = "消息发送成功";
			return result;
		}
		result = "消息发送失败,再试一次吧...";
		return result;
	}

	@RequestMapping("/pay")
	public String pay() {
		String result = "";
		boolean tryAcquire = rateLimiter.tryAcquire(500, TimeUnit.MICROSECONDS);
		if (!tryAcquire) {
			result = "请求过多，降级处理";
			logger.info(result);
			return result;
		}
		int ret = payService.pay(BigDecimal.valueOf(100.0));
		if (ret > 0) {
			result = "支付成功";
			return result;
		}
		result = "支付失败,再试一次吧...";
		return result;
	}
}
