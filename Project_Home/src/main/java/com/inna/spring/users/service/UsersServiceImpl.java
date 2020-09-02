package com.inna.spring.users.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.inna.spring.users.dao.UsersDao;
import com.inna.spring.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao dao;
	
	@Override
	public Map<String, Object> isExistId(String inputId) {
		//dao를 이용해서 아이디 존재 여부 알아내기
		boolean isExist = dao.isExist(inputId);
		//아이디 존재 여부를 Map에 담아주기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isExist", isExist);
		return map;
	}

	@Override
	public void addUser(UsersDto dto) {
		dao.insert(dto);
	}

	@Override
	public void loginProcess(UsersDto dto, ModelAndView mView, HttpSession session) {
		//dao 객체 이용해서 id, pwd가 유효한 정보인지 여부 얻어내기
		boolean isValid=dao.isValid(dto);
		if(isValid) {
			//유효한 정보라면 로그인 처리
			session.setAttribute("id", dto.getId());
			mView.addObject("isSuccess", true);
		}else {
			mView.addObject("isSuccess", false);
		}
		
	}

}
