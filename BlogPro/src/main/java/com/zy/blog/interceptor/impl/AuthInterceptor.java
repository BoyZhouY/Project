/**
 * 功能描述 
 * 文件名 AuthInterceptor.java
 * 作者 周泰斗
 * 编写日期 2020年5月19日下午10:21:52
 **/
package com.zy.blog.interceptor.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import com.zy.blog.annotation.DisableAuth;
import com.zy.blog.interceptor.BaseInterceptor;
import com.zy.blog.util.RedisUtil;
import com.zy.blog.util.ResponseUtil;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
public class AuthInterceptor extends BaseInterceptor {
	/**
	 * 用户服务
	 */
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		// 放行逻辑
		HandlerMethod method = (HandlerMethod) handler;
		DisableAuth auth = method.getMethod().getAnnotation(DisableAuth.class);
		if (isDisableAuth(auth)) {
			return super.preHandle(request, response, handler);
		}
		// 获取token
		String userUid = request.getParameter("userUid");
		String token = request.getParameter("tokenId");
		if (StringUtils.isEmpty(userUid) || StringUtils.isEmpty(token)) {
			setResponse(request, response, ResponseUtil.failure("Error: token is null"));
			return false;
		}

		// 3.查询token是否正确
		String _token = redisUtil.get(userUid);
		if (_token ==  null || !_token.equals(token)) {
			setResponse(request, response, ResponseUtil.failure("Error: token is invalid"));
			return false;
		}
		return true;
	}

	private static boolean isDisableAuth(DisableAuth auth) {
		return auth != null;
	}
}
