package com.inna.spring.users.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inna.spring.users.dto.UsersDto;
import com.inna.spring.users.service.UsersService;

@Controller
public class UsersController {
	@Autowired
	private UsersService service;
	
	//회원가입페이지 요청 처리
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	//아이디 존재 여부 처리 요청처리
	@RequestMapping("/users/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam String inputId) {
		return service.isExistId(inputId);
	}
	
	//회원가입 요청처리
	@RequestMapping("/users/signup")
	public ModelAndView signup(UsersDto dto, ModelAndView mView) {
		service.addUser(dto);
		mView.setViewName("users/signup");
		return mView;
	}
	
}
