package com.root.home.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.root.home.jwt.JwtFilter;
import com.root.home.jwt.TokenProvider;
import com.root.home.service.AdminService;
import com.root.home.vo.AdminLoginVo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Controller
@Log4j2
@Slf4j
public class AdminController {	
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("admin/login")
	public String adminLogin(@ModelAttribute AdminLoginVo adminLoginVo, BindingResult result, Model model,
			 HttpServletRequest request) throws Exception {
		
		List<AdminLoginVo> list = adminService.selectMemberList();
		log.info("Mampper Data -------------- : "+ list);
		
		return "/admin/testLogin.html";
	}
	
	
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String admin() throws Exception {
		return "/admin/testAdminHome.html";
	}
}