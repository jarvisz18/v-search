package com.ixan.boot.domain.base;

import java.lang.annotation.*;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/10/6 20:38
 * @description 日志切面类
 * @version 1.0
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface OperLog {
	String operModul() default ""; // 操作模块

	String operType() default "";  // 操作类型

	String operDesc() default "";  // 操作说明
}
