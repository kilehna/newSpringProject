package com.inna.spring.users.dao;

import com.inna.spring.users.dto.UsersDto;

public interface UsersDao {
	//입력한 아이디가 존재하는지 여부 판별하는 메소드
	public boolean isExist(String inputId);
	public void insert(UsersDto dto);
	//로그인 여부를 판단하기 위한 메소드
	public boolean isValid(UsersDto dto);
	//회원정보 조회용 메소드
	public UsersDto getData(String id);
}
