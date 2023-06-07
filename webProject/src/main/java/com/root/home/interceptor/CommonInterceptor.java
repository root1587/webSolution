package com.root.home.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommonInterceptor implements HandlerInterceptor {

	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        // Controller 실행 전에 수행되는 메소드.
	        log.info("===== preHandler START =====");
	        log.info("Request URL : {}", request.getRequestURI());
	        return true;
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	        // Controller 실행 후 View가 랜더링 되기 전에 실행
	        log.info("===== preHandler END =====");
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	        // Controller 실행 후 View가 랜더링 된 후에 실행
	        log.info("===== after Completion =====");
	    }
}