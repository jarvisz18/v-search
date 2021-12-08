package cn.ixan.search.web.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/3/1 21:38
 * @description user login advice
 */
//@Aspect
//@Component
public class UserLogAdvice {
    private static final Logger log = LoggerFactory.getLogger(UserLogAdvice.class);

    //统一切点,对cn.ixan.search.controller及其子包中所有的类的所有方法切面
    @Pointcut("execution(public * cn.ixan.search.web.controller..*.*(..))")
    public void pointcut() {
    }

    @Pointcut("execution (* cn.ixan.search.web.controller..*.*(..)) && @annotation(userLog)")
    public void controllerAspect(UserLog userLog) {
    }

    /**
     * 拦截的方法执行之前就执行
     *
     * @param joinPoint 拦截的方法传入的参数
     * @param userLog   自定义注解
     */
    @Before(value = "controllerAspect(userLog)", argNames = "joinPoint,userLog")
    public void addBeforeLogger(JoinPoint joinPoint, UserLog userLog) {
        log.info("调用了前置通知");
        LocalDateTime now = LocalDateTime.now();
        log.info("登陆时间:" + now.toString() + ",接口访问开始");
    }

    //@After: 后置通知
    @After("pointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        log.info("调用了后置通知");
    }

    //@AfterRunning: 返回通知 rsult为返回内容
    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        log.info("调用了返回通知");
    }

    //@AfterThrowing: 异常通知
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e) {
        log.info("调用了异常通知");
    }

    //@Around：环绕通知
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around执行方法之前");
        Object object = pjp.proceed();
        log.info("around执行方法之后--返回值：" + object);
        return object;
    }
}
