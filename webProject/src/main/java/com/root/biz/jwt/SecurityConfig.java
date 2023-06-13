package com.root.biz.jwt;

import com.root.biz.jwt.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Security 활성화
@EnableMethodSecurity // @PreAuthorize 어노테이션 메소드 단위로 추가하기 위해 적용 (default : true)
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // TokenProvider,JwtAuthenticationEntryPoint,JwtAccessDeniedHandler 의존성 주입
    public SecurityConfig(
            TokenProvider tokenProvider,	
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ){
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .requestMatchers("/")
                .requestMatchers("/css/**")
                .requestMatchers("/js/**")
                .requestMatchers("/assets/**")
                .requestMatchers("/scss/**")
                .requestMatchers("/vendor/**")
                .requestMatchers("favicon.ico")
                .requestMatchers("/resources/**")
                .requestMatchers("/error/**")
                .requestMatchers("/401")
        
        		//임시
                .requestMatchers("/admin/login");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 토큰을 사용하기 때문에 csrf 설정 disable
        		// 헤더 -> 쿠키로 변경하였으므로 어떻게 보안 처리할지 고민.. 
                .csrf().disable()
      
                // 예외 처리 시 직접 만들었던 클래스 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 세션 사용하지 않기 때문에 세션 설정 STATELESS
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 토큰이 없는 상태에서 요청이 들어오는 API들은 permitAll
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                
                // 토큰이 없는 상태에서 요청이 들어오는 API들은 permitAll
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/admin/login").permitAll()//.hasRole("ADMIN")
                .anyRequest().authenticated()
                
                // JwtFilter를 addFilterBefore로 등록했던 jwtSecurityConfig 클래스 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}