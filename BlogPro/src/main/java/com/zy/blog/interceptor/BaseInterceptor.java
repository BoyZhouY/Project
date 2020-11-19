/**
 * 功能描述 
 * 文件名 BaseInterceptor.java
 * 作者 周泰斗
 * 编写日期 2020年5月21日下午9:48:43
 **/
package com.zy.blog.interceptor;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.zy.blog.common.Response;
import com.zy.blog.util.ResponseUtil;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void setResponse(HttpServletRequest request,
			HttpServletResponse response, Response res) {

		response.setContentType("application/json;charset=UTF-8");
		try (Writer writer = response.getWriter()) {

			logger(request, res);
			JSON.writeJSONString(writer, res);
			writer.flush();
		} catch (IOException e) {
			logger.error("response 设置操作异常：" + e);
		}
	}

	public void setResponse(HttpServletRequest request,
			HttpServletResponse response, String messageKey) {
		setResponse(request, response, ResponseUtil.failure(null));

	}

	/**
	 * 记录日志
	 */
	private void logger(HttpServletRequest request,Response res) {
		StringBuffer msg = new StringBuffer();
		msg.append("异常拦截日志:");
		msg.append("[uri:").append(request.getRequestURI()).append("]");
		Enumeration<String> enumer = request.getParameterNames();
		while (enumer.hasMoreElements()) {
			String name = enumer.nextElement();
			String[] values = request.getParameterValues(name);
			msg.append("[").append(name).append("=");
			if (values != null) {
				int i = 0;
				for (String value : values) {
					i++;
					msg.append(value);
					if (i < values.length) {
						msg.append("｜");
					}
				}
			}
			msg.append("]");
		}
		msg.append(JSON.toJSONString(res));

		logger.warn(msg.toString());
	}
}
