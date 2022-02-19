package com.ixan.boot.web.aspect;

import java.lang.annotation.*;

/**
 * @author 冰河技术
 * @version 1.0
 * @date Created in 2022/2/19 下午11:16
 * @description 自定义限流注解
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRateLimiter {
	/**
	 * 向令牌桶放入令牌的速率
	 */
	double rate();

	/**
	 * 从令牌桶获取令牌的超时时间
	 */
	long timeout() default 0L;
}
