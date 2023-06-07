package com.root.home.service.Impl;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.root.home.vo.AdminLoginVo;


@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("in2======================================");
    	AdminLoginVo authRepository = new AdminLoginVo();
    	authRepository.setId("test2");
    	authRepository.setName("Tester");
    	String encodedPassword = new BCryptPasswordEncoder().encode("1234");
    	authRepository.setPw(encodedPassword);
    	authRepository.setAuthKey("testKey");
    	
    	System.out.println(authRepository);
        UserDetails userDetails = (UserDetails)new User(
        		authRepository.getAuthKey(), authRepository.getPw(), AuthorityUtils.createAuthorityList("ADMIN")
        );
        System.out.println("in3======================================");
        System.out.println(userDetails);

        return userDetails;
    }


}

