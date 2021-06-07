package com.gw.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.Email_dto;

@Repository
public class Email_dao {
	
	
	@Autowired
	private SqlSession sqlSession;

	public int insert_email(Map<String, Object> map) {
		
		
		return sqlSession.insert("com.gw.insert_email", map);
	}
	
	
	public List<Email_dto> list_email(Map<String, Object> map){
		
		
		return sqlSession.selectList("com.gw.list_email", map);
	}
	
	public List<Email_dto> sent_email(Map<String, Object> map){
		
		
		return sqlSession.selectList("com.gw.sent_email", map);
	}
	
	public Email_dto email_read(int email_idx) {
		
		return sqlSession.selectOne("com.gw.email_read",email_idx);
	}
	
	public int emaildelete(Map<String, Object> map) {
		
		return sqlSession.delete("com.gw.emaildelete",map);
	}
	
	public int email_read_delete (Map<String, Object> map) {
		
		return sqlSession.delete("com.gw.email_read_delete",map);
	}
	
	//페이징 카운트
	
	public int Email_list_page(int user_idx) {
		
		return sqlSession.selectOne("com.gw.Email_list_page",user_idx);
	}
	//보낸메일함 페이징 카운트
	public int Email_list_a(int email_re) {
		
		return sqlSession.selectOne("com.gw.Email_list_a", email_re);
	}
	
	//읽은시간 업데이트
	public int Email_readtime(int email_idx) {
		
		return sqlSession.update("com.gw.Email_readtime",email_idx);
	}
	
}
