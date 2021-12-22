package cn.ixan.search.hutool.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/22 下午7:31
 * @description Excel属性关联
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelRef {
	String header() default "";

	boolean required() default false;
}
