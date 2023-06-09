package com.root.biz.service;

import java.util.List;

import com.root.biz.vo.AdminLoginVo;

public interface AdminService {
	List<AdminLoginVo> selectMemberList() throws Exception;
}
