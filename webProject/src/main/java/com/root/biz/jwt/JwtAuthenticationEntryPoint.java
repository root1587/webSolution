package com.root.biz.jwt;

//유효한 자격 증명을 제공하지 않고 접근하려 할 때 401 Unauthorized 에러 리턴

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
 @Override
 public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
     response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
     log.info("######    401 ERROR IN    #####");
 }
}