package com.zy.blog.filter;

import java.io.IOException;


import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletInputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;

public class HttpServletRequestWrapper implements HttpServletRequest
{
	private HttpServletRequest request;
	private String method;

	public HttpServletRequestWrapper(HttpServletRequest request, String method)
	{
		this.request = request;
		this.method = method;
	}

	/*
	 * 实现HttpServletRequest接口
	 */
	public boolean authenticate(HttpServletResponse response) throws java.io.IOException, ServletException { return request.authenticate(response); }
	public java.lang.String getAuthType() { return request.getAuthType(); }
	public java.lang.String getContextPath() { return request.getContextPath(); }
	public Cookie[] getCookies() { return request.getCookies(); }
	public long getDateHeader(java.lang.String name) { return request.getDateHeader(name); }
	public java.lang.String getHeader(java.lang.String name) { return request.getHeader(name); }
	public java.util.Enumeration<java.lang.String> getHeaderNames() { return request.getHeaderNames(); }
	public java.util.Enumeration<java.lang.String> getHeaders(java.lang.String name) { return request.getHeaders(name); }
	public int getIntHeader(java.lang.String name) { return request.getIntHeader(name); }
	public java.lang.String getMethod() { return method; }
	public Part getPart(java.lang.String name) throws java.io.IOException, ServletException { return request.getPart(name); }
	public java.util.Collection<Part> getParts() throws java.io.IOException, ServletException { return request.getParts(); }
	public java.lang.String getPathInfo() { return request.getPathInfo(); }
	public java.lang.String getPathTranslated() { return request.getPathTranslated(); }
	public java.lang.String getQueryString() { return request.getQueryString(); }
	public java.lang.String getRemoteUser() { return request.getRemoteUser(); }
	public java.lang.String getRequestedSessionId() { return request.getRequestedSessionId(); }
	public java.lang.String getRequestURI() { return request.getRequestURI(); }
	public java.lang.StringBuffer getRequestURL() { return request.getRequestURL(); }
	public java.lang.String getServletPath() { return request.getServletPath(); }
	public HttpSession getSession() { return request.getSession(); }
	public HttpSession getSession(boolean create) { return request.getSession(create); }
	public java.security.Principal getUserPrincipal() { return request.getUserPrincipal(); }
	public boolean isRequestedSessionIdFromCookie() { return request.isRequestedSessionIdFromCookie(); }
	public boolean isRequestedSessionIdFromUrl() { return request.isRequestedSessionIdFromURL(); }
	public boolean isRequestedSessionIdFromURL() { return request.isRequestedSessionIdFromURL(); }
	public boolean isRequestedSessionIdValid() { return request.isRequestedSessionIdValid(); }
	public boolean isUserInRole(java.lang.String role) { return request.isUserInRole(role); }
	public void login(java.lang.String username, java.lang.String password) throws ServletException { request.login(username, password); }
	public void logout() throws ServletException { request.logout(); }
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException { return request.upgrade(handlerClass); }
	public String changeSessionId() { return request.changeSessionId(); }
	/*
	 * 实现ServletRequest接口
	 */
	public AsyncContext getAsyncContext() { return request.getAsyncContext(); }
	public java.lang.Object getAttribute(java.lang.String name) { return request.getAttribute(name); }
	public java.util.Enumeration<java.lang.String> getAttributeNames() { return request.getAttributeNames(); }
	public java.lang.String getCharacterEncoding() { return request.getCharacterEncoding(); }
	public int getContentLength() { return request.getContentLength(); }
	public long getContentLengthLong() { return request.getContentLengthLong(); }
	public java.lang.String getContentType() { return request.getContentType(); }
	public DispatcherType getDispatcherType() { return request.getDispatcherType(); }
	public ServletInputStream getInputStream() throws java.io.IOException { return request.getInputStream(); }
	public java.lang.String getLocalAddr() { return request.getLocalAddr(); }
	public java.util.Locale getLocale() { return request.getLocale(); }
	public java.util.Enumeration<java.util.Locale> getLocales() { return request.getLocales(); }
	public java.lang.String getLocalName() { return request.getLocalName(); }
	public int getLocalPort() { return request.getLocalPort(); }
	public java.lang.String getParameter(java.lang.String name) { return request.getParameter(name); }
	public java.util.Map<java.lang.String,java.lang.String[]> getParameterMap() { return request.getParameterMap(); }
	public java.util.Enumeration<java.lang.String> getParameterNames() { return request.getParameterNames(); }
	public java.lang.String[] getParameterValues(java.lang.String name) { return request.getParameterValues(name); }
	public java.lang.String getProtocol() { return request.getProtocol(); }
	public java.io.BufferedReader getReader() throws java.io.IOException { return request.getReader(); }
	public java.lang.String getRealPath(java.lang.String path) { return request.getServletContext().getRealPath(path); }
	public java.lang.String getRemoteAddr() { return request.getRemoteAddr(); }
	public java.lang.String getRemoteHost() { return request.getRemoteHost(); }
	public int getRemotePort() { return request.getRemotePort(); }
	public RequestDispatcher getRequestDispatcher(java.lang.String path) { return request.getRequestDispatcher(path); }
	public java.lang.String getScheme() { return request.getScheme(); }
	public java.lang.String getServerName() { return request.getServerName(); }
	public int getServerPort() { return request.getServerPort(); }
	public ServletContext getServletContext() { return request.getServletContext(); }
	public boolean isAsyncStarted() { return request.isAsyncStarted(); }
	public boolean isAsyncSupported() { return request.isAsyncSupported(); }
	public boolean isSecure() { return request.isSecure(); }
	public void removeAttribute(java.lang.String name) { request.removeAttribute(name); }
	public void setAttribute(java.lang.String name, java.lang.Object o) { request.setAttribute(name, o); }
	public void setCharacterEncoding(java.lang.String env) throws java.io.UnsupportedEncodingException { request.setCharacterEncoding(env); }
	public AsyncContext startAsync() throws java.lang.IllegalStateException { return request.startAsync(); }
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws java.lang.IllegalStateException { return request.startAsync(servletRequest, servletResponse); }
}
