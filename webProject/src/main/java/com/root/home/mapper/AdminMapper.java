package com.root.home.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.root.home.vo.AdminLoginVo;

@Repository
@Mapper
public interface AdminMapper {
	List<AdminLoginVo> selectMemberList() throws Exception;

	AdminLoginVo getUserInfo(String username);
}
