package com.root.biz.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.root.biz.jwt.JwtAuthenticationEntryPoint;
import com.root.biz.service.AdminService;
import com.root.biz.vo.AdminLoginVo;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminService adminService;
	
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("in2======================================");
    	AdminLoginVo authRepository = new AdminLoginVo();
    	
    	try {
			List<AdminLoginVo> list = adminService.selectMemberList();			
			System.out.println(list.get(0));		
			authRepository = list.get(0);			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	System.out.println(authRepository);
    	String encodedPassword = new BCryptPasswordEncoder().encode("1234");
    	authRepository.setPw(encodedPassword);
    	
    	System.out.println(authRepository);	
    	//500 error 추가필요
        UserDetails userDetails = (UserDetails)new User(	
        		authRepository.getId(), authRepository.getPw(), AuthorityUtils.createAuthorityList("ROLE_ADMIN")
        );
        System.out.println("in3======================================");
        System.out.println(userDetails);

        return userDetails;
    }


}

