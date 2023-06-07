package com.root.home.service;

import java.util.List;

import com.root.home.vo.AdminLoginVo;

public interface AdminService {
	List<AdminLoginVo> selectMemberList() throws Exception;
}
