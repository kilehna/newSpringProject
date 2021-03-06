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
import org.springframework.web.multipart.MultipartFile;
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
	
	//개인정보 조회 요청처리
	@RequestMapping("/users/private/info")
	public ModelAndView info(ModelAndView mView,
			HttpServletRequest request) {
		service.getInfo(request.getSession(), mView);
		mView.setViewName("users/private/info");
		return mView;
	}
	
	//회원 정보 수정폼 요청 처리
	@RequestMapping("/users/private/updateform")
	public ModelAndView updateForm(HttpServletRequest request,
			ModelAndView mView) {
		service.getInfo(request.getSession(), mView);
		mView.setViewName("users/private/updateform");
		return mView;
	}
	
	//개인 정보 수정 반영 요청 처리
	@RequestMapping("/users/private/update")
	public ModelAndView update(HttpServletRequest request, 
			UsersDto dto, ModelAndView mView) {
		//service 객체를 이용해서 개인정보를 수정한다.
		service.updateUser(request.getSession(), dto);
		//개인 정보 보기 페이지로 리다일렉트 이동한다.
		mView.setViewName("redirect:/users/private/info.do");
		return mView;
	}
	
	//ajax 프로필 사진 업로드 요청처리
	@RequestMapping("/users/private/profile_upload")
	@ResponseBody
	public Map<String, Object> profile_upload
		(HttpServletRequest request, @RequestParam MultipartFile image){
		Map<String, Object> map = service.saveProfileImage(request, image);
		return map;
	}
	
	//개인 정보 삭제 요청 처리
	@RequestMapping("/users/private/delete")
	public ModelAndView delete(HttpServletRequest request, 
			ModelAndView mView) {
		//service 객체를 이용해서 사용자 정보 삭제
		service.deleteUser(request.getSession());
		//view 페이지로 forward 이동해서 응답
		mView.setViewName("users/private/delete");
		return mView;
	}

}
