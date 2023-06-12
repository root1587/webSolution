package com.root.biz.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.root.biz.service.jwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;

public class jwtServiceImpl implements jwtService{
	 @Value("${jwt.secret}")
	    private String secret;
	 
	@Override
	public Map<String, Object> verify(String token) throws Exception {
		Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token)
                .getBody();
		
		return new HashMap<>(claims);
	}

}
