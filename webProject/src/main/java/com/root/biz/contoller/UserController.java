package com.root.biz.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class UserController {
	
	@RequestMapping("/")
	public String mainIndex() {
		return "/user/index.html";
	}
	
	@RequestMapping("/layout")
	public String layout() {
		
		return "user/board.html";
	}
}