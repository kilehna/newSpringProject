package com.inna.spring.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inna.spring.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public boolean isExist(String inputId) {
		String id = session.selectOne("users.isExist", inputId);
		if(id==null) {//아이디가 존재하지 않는다면
			return false;
		}else {//아이디가 존재한다면
			return true;
		}
	}

	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
	}

	@Override
	public boolean isValid(UsersDto dto) {
		String id = session.selectOne("users.isValid", dto);
		if(id==null) {//로그인 중이 아니라면
			return false;
		}else {//로그인 중이라면
			return true;
		}
	}

	@Override
	public UsersDto getData(String id) {
		UsersDto dto = session.selectOne("users.getData", id);
		return dto;
	}

}
