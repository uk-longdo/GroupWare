package com.gw.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.User_dto;

@Repository
public class User_dao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public int insert_user(Map<String, Object> map) {
		
		
		return sqlSession.insert("com.gw.insert_user", map);
	}
	
	public List<User_dto> user_list(Map<String, Object> map){
		
		
		return sqlSession.selectList("com.gw.user_list", map);
	}
	
	public int logindo(Map<String, Object> map) {
		
		return sqlSession.selectOne("com.gw.logindo", map);
	}
	public int login_rank(Map<String, Object> map) {
		
		return sqlSession.selectOne("com.gw.login_rank",map);
	}
	public String loginname(Map<String, Object> map) {
		
		return sqlSession.selectOne("com.gw.loginname", map); 
	}
}


