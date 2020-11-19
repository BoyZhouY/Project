/**
 * 功能描述 
 * 文件名 WebConfig.java
 * 作者 周泰斗
 * 编写日期 2020年6月2日下午11:27:33
 **/
package com.zy.blog.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zy.blog.filter.OverrideHttpMethodFilter;
import com.zy.blog.interceptor.impl.AuthInterceptor;

/**
 * @author 周泰斗
 * @version 1.0.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Bean
	public AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Bean  
    public FilterRegistrationBean<OverrideHttpMethodFilter> filterRegistrationBean(){
        FilterRegistrationBean<OverrideHttpMethodFilter> registration = new FilterRegistrationBean<OverrideHttpMethodFilter>();  
        registration.setFilter(new OverrideHttpMethodFilter());  
        registration.setEnabled(true);  
        registration.addUrlPatterns("/*");  
		registration.addInitParameter("paramName", "paramValue");  
        registration.setName("overrideHttpMethodFilter");  
        registration.setOrder(0); 
        return registration;  
    }  
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	//可以全局Cors配置
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("*")
//				.allowedMethods("*")
//				.allowedHeaders("*");
//	}
}
