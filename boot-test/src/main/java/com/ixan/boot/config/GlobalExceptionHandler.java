package com.ixan.boot.config;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author stackzhang@126.com
 * @date Created in 2022/1/11 12:45
 * @description 统一异常处理
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		StringBuilder stringBuilder = new StringBuilder("校验失败:");
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			stringBuilder.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append(", ");
		}
		String msg = stringBuilder.toString();
		return ResultGenerate.fail(msg);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Result handleConstraintViolationException(ConstraintViolationException ex) {
		return ResultGenerate.fail(ex.getMessage());
	}
}
