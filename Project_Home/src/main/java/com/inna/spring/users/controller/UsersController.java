package com.inna.spring.users.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	//로그인 폼 요청처리
	@RequestMapping("/users/loginform")
	public String loginform(HttpServletRequest request) {
		String url=request.getParameter("url");
		if(url==null) {
			String cPath=request.getContextPath();
			url=cPath+"/home.do";
		}
		request.setAttribute("url", url);
		return "users/loginform";
	}
	
	//로그인 요청처리
	@RequestMapping("/users/login")
	public ModelAndView login(UsersDto dto, ModelAndView mView,
			HttpServletRequest request, HttpSession session) {
		//로그인 후 목적지 정보 
		String url=request.getParameter("url");
		//목적지 정보 인코딩
		String encodeUrl=URLEncoder.encode(url);
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodeUrl);
		
		//service 객체 이용해서 로그인 처리를 한다.
		service.loginProcess(dto, mView, session);
		mView.setViewName("users/login");
		return mView;
	}	
	
	//로그아웃 요청처리
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		//세션 비우기
		session.invalidate();
		//홈으로 리다일렉트 이동
		return "redirect:/home.do";
	}
	
	
}
