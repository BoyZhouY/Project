package com.zy.blog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class OverrideHttpMethodFilter extends OncePerRequestFilter {
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
		String receivedMethod = request.getMethod();
		String methodFromHeader = request.getHeader("X-HTTP-Method-Override");
		
		if (receivedMethod != null && !receivedMethod.equals(methodFromHeader)) {
			if(receivedMethod.equalsIgnoreCase("OPTIONS")) {
				filterChain.doFilter(request, response);
			}else {
				filterChain.doFilter(new HttpServletRequestWrapper(request, methodFromHeader), response);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
}