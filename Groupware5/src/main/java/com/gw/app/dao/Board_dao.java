package com.gw.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.Board_dto;

@Repository
public class Board_dao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<Board_dto> board_list(Map<String, Object> map){
		
		return sqlSession.selectList("com.gw.board_list", map);
	}
	
	public Board_dto board_read(int board_idx){
		
		
		return sqlSession.selectOne("com.gw.board_read", board_idx);
	}
	public int board_hit(int board_idx) {
		
		return sqlSession.update("com.gw.board_hit", board_idx);
	}
	
	public int board_insert(Map<String, Object> map) {
		
		return sqlSession.insert("com.gw.board_insert", map);
	}
	
	public int board_delete(int board_idx) {
		
		return sqlSession.delete("com.gw.board_delete", board_idx); 
	}
	
	
	public int board_page() {
		
		return sqlSession.selectOne("com.gw.board_page");
	}
	
}
