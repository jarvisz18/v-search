package com.ixan.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/30 15:06
 * @description web mvc configuration
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                Result<String> result = new Result<>();
                if(ex instanceof NoHandlerFoundException){
                    result.setCode("500");
                    result.setMsg(ex.getMessage());
                } else {
                    if(handler instanceof HandlerMethod){
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        String message = String.format("接口[%s]出现异常，方法[%s.%s],异常摘要[%s]",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                ex.getMessage());
                        log.error(message,ex);
                    }
                    result.setCode("500");
                    result.setMsg(ex.getMessage());
                }
                return null;
            }
        });
    }
}
