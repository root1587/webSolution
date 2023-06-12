package com.root.biz.service;

import java.util.Map;

public interface jwtService {
	/**
     * 토큰 검증후 저장된 값 복원
     */
	Map<String, Object> verify(String token) throws Exception;
}
