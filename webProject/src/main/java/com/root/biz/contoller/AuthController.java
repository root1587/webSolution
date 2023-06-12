package com.root.biz.contoller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.root.biz.jwt.JwtFilter;
import com.root.biz.jwt.TokenProvider;
import com.root.biz.vo.AdminLoginVo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthController(
            TokenProvider tokenProvider,
            AuthenticationManagerBuilder authenticationManagerBuilder
    ){
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/admin/login")
    public void getLoginToken(AdminLoginVo loginRequestDTO, RedirectAttributes redirectAttributes, HttpServletResponse response ) {
    	
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getId(), loginRequestDTO.getPw());
        System.out.println(authenticationToken);        

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        
        /* header 사용 --> 쿠키사용으로 변경 */
        //httpHeaders.setLocation(URI.create("/admin"));
        //httpHeaders.set(JwtFilter.AUTHORIZATION_HEADER,"Bearer " + jwt);
        //redirectAttributes.addFlashAttribute(JwtFilter.AUTHORIZATION_HEADER,"Bearer " + jwt);
		
        log.info("#######     headers     ######:" + httpHeaders);
        //return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
        
        Cookie cookie = new Cookie(
                JwtFilter.COOKIE_NAME,
                jwt
            );
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        cookie.setHttpOnly(true);
        /* SSL 사용시 설정해주면 됨 */
        //cookie.setSecure(true);

        response.addCookie(cookie);
     
        //return jwt;
    }
}