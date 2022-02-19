package com.ixan.boot.web.aspect;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author 冰河技术
 * @version 1.0
 * @date Created in 2022/2/19 下午11:20
 * @description 一般限流切面类
 */
@Aspect
@Component
public class MyRateLimiterAspect {
	private RateLimiter rateLimiter = RateLimiter.create(2);

	@Pointcut("execution(public * com.ixan.boot.web.controller.ratelimiter.*.*(..)) && @annotation(MyRateLimiter)")
	public void pointcut() {
	}

	@Around("pointcut()")
	public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		//使用反射获取方法上是否存在@MyRateLimiter注解
		MyRateLimiter myRateLimiter = signature.getMethod().getDeclaredAnnotation(MyRateLimiter.class);
		if (null == myRateLimiter) {
			//程序正常执行，执行目标方法
			return proceedingJoinPoint.proceed();
		}
		//获取配置的速率和等待令牌的时间
		double rate = myRateLimiter.rate();
		long timeout = myRateLimiter.timeout();

		//设置限流速率
		rateLimiter.setRate(rate);

		//判断客户端获取令牌是否超时
		boolean tryAcquire = rateLimiter.tryAcquire(timeout, TimeUnit.MICROSECONDS);
		if (!tryAcquire) {
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			fallback(response);
			return null;
		}
		//获取到令牌,直接执行
		return proceedingJoinPoint.proceed();
	}

	private void fallback(HttpServletResponse response) {
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		try (PrintWriter writer = response.getWriter()) {
			writer.println("出错了,重试一次试试");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
