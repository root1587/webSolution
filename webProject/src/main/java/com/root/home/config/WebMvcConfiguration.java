package com.root.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.root.home.interceptor.CommonInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	@Autowired
	CommonInterceptor commonInterceptor;
	
	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		/*필터 설정*/
		registry.addInterceptor(commonInterceptor).addPathPatterns("/admin/**");
		System.out.println(registry);
		System.out.println(registry.toString());
	}
}
