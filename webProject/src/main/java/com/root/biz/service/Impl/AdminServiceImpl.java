package com.root.biz.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.biz.mapper.AdminMapper;
import com.root.biz.service.AdminService;
import com.root.biz.vo.AdminLoginVo;


@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public List<AdminLoginVo> selectMemberList() throws Exception {
		return adminMapper.selectMemberList();
	}
}
