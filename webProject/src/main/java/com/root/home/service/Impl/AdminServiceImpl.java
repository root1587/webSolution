package com.root.home.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.home.mapper.AdminMapper;
import com.root.home.service.AdminService;
import com.root.home.vo.AdminLoginVo;


@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public List<AdminLoginVo> selectMemberList() throws Exception {
		return adminMapper.selectMemberList();
	}
}
