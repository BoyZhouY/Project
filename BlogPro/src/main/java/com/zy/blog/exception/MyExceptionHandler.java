/*
 * 模块编号
 * 功能描述  
 * 文件名       ExceptionHandler.java
 * 作者           周泰斗
 * 编写日期           2020年4月30日下午5:07:01
 */
package com.zy.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.blog.common.Response;
import com.zy.blog.common.ResponseEnum;

/**
 * 功能描述 用于同一处理异常信息
 *
 * @version 1.0.0
 * @author 周泰斗
 */
@ControllerAdvice
public class MyExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Response handle(Exception e) {
		logger.error(e.getMessage(), e);
		// 自定义异常
		if (e instanceof MyException) {
			return new Response(((MyException) e).getCode(), e.getMessage(), null, System.currentTimeMillis());
		}
		return new Response(ResponseEnum.ERROR.getCode(), "服务器出现错误，已记录", null, System.currentTimeMillis());
	}
}
