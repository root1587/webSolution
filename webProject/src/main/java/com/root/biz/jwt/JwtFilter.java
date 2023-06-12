package com.root.biz.jwt;

//JWT를 위한 커스텀 필터

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;

public class JwtFilter extends GenericFilterBean {

 private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
 public static final String COOKIE_NAME = "login_cookie";
 public static final String AUTHORIZATION_HEADER = "Authorization";

 private final TokenProvider tokenProvider;

 public JwtFilter(TokenProvider tokenProvider){
     this.tokenProvider = tokenProvider;
 }

 // 실제 필터링 로직 작성
 // doFilter : 토큰의 인증 정보를 SecurityContext에 저장
 @Override
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     System.out.println("doFilter");

     // resolveToken을 통해 토큰을 받아옴
     HttpServletRequest httpServletRequest = (HttpServletRequest) request;
     String jwt = resolveToken(httpServletRequest);
     String requestURI = httpServletRequest.getRequestURI();
     
     logger.info(jwt);
     
     // 토큰 유효성 검증 후 정상이면 SecurityContext에 저장
     if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
         Authentication authentication = tokenProvider.getAuthentication(jwt);
         SecurityContextHolder.getContext().setAuthentication(authentication);
         logger.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}",authentication.getName(),requestURI);
     }

     else logger.info("유효한 JWT 토큰이 없습니다, uri: {}",requestURI);
     
     // 생성한 필터 실행
     logger.info("######Request: " + httpServletRequest);
     logger.info("######Response: " + response);
     chain.doFilter(httpServletRequest,response);
 }

 // Request Header에서 토큰 정보를 꺼내오기
 private String resolveToken(HttpServletRequest request){
     //String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
	 String bearerToken = Arrays.stream(request.getCookies())
             .filter(cookie -> cookie.getName().equals(COOKIE_NAME))
             .findFirst().map(Cookie::getValue)
             .orElse("dummy");
	 
     if(StringUtils.hasText(bearerToken)){ //&& bearerToken.startsWith("Bearer ")

         System.out.println("token : " + bearerToken);

         return bearerToken;//.substring(7);
     }
     return null;
 }
}