package com.ixan.boot.web.controller.ratelimiter;

import com.ixan.boot.service.ratelimiter.MessageService;
import com.ixan.boot.service.ratelimiter.PayService;
import com.ixan.boot.web.aspect.MyRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author 冰河技术
 * @version 1.0
 * @date Created in 2022/2/19 下午11:19
 * @description 基于注解的RateLimiter的接口限流
 */
@RequestMapping("/api/v2")
@RestController
public class SimplePayController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private PayService payService;

	@MyRateLimiter(rate = 1.0, timeout = 500L)
	@RequestMapping("/send/message")
	public String sendMessage() {
		String result = "";
		boolean flag = messageService.sendMessage("恭喜您成长值+1");
		if (flag) {
			result = "消息发送成功";
			return result;
		}
		result = "消息发送失败,再试一次吧...";
		return result;
	}

	@MyRateLimiter(rate = 1.0, timeout = 500L)
	@RequestMapping("/pay")
	public String pay() {
		String result = "";
		int ret = payService.pay(BigDecimal.valueOf(100.0));
		if (ret > 0) {
			result = "支付成功";
			return result;
		}
		result = "支付失败,再试一次吧...";
		return result;
	}
}
