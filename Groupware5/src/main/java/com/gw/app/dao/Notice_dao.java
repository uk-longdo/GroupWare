package com.gw.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.Notice_dto;


@Repository
public class Notice_dao {
	
@Autowired 
private SqlSession sqlSession;
	
public List<Notice_dto> notice_list(Map<String, Object> map){
		
		
		return sqlSession.selectList("com.gw.notice_list", map);
	}


public int notice_insert(Map<String, Object> map) {
	
	return sqlSession.insert("com.gw.notice_insert", map);
}

public Notice_dto notice_read(int notice_idx){
	
	
	return sqlSession.selectOne("com.gw.notice_read", notice_idx);
}
public int notice_hit(int notice_idx) {
	
	return sqlSession.update("com.gw.notice_hit", notice_idx);
}

	//업데이트를 위한 구문
	public int Notice_update(Map<String, Object> map) {
		return sqlSession.update("com.gw.notice_update", map);
	}
	
	//삭제를 위한 메소드
	public int Notice_delete(int notice_idx) {
		return sqlSession.delete("com.gw.notice_delete", notice_idx);
	}
	
	//페이징 처리를 위한 메소드
	public int Notice_paging() {
		return sqlSession.selectOne("com.gw.notice_paging");
	}


}
