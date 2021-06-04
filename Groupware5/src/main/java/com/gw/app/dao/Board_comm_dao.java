package com.gw.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.Board_comm_dto;

@Repository
public class Board_comm_dao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int comm_insert(Map<String, Object> map) {
		
		return sqlSession.insert("com.gw.comm_insert",map);
	}
	
	public List<Board_comm_dto> comm_list(int board_idx){
		
		return sqlSession.selectList("com.gw.comm_list", board_idx);
	}
	
	//´ñ±Û °¹¼ö¸¦ À§ÇÑ Ä«¿îÆ® ¼¿·ºÆ®¹® 
	public int comm_cnt(int board_idx) {
		return sqlSession.selectOne("com.gw.comm_cnt", board_idx);
	}
}
