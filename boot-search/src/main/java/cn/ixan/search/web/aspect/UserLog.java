package cn.ixan.search.web.aspect;

import java.lang.annotation.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/3/1 21:36
 * @description user login aspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLog {
    String menuName();
    String menuDesc();
}
