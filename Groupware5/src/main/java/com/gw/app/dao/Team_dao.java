package com.gw.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.Team_dto;

@Repository
public class Team_dao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//team_list를 가지고 오기 위한 dao
	public List<Team_dto> team_list(){
		return sqlSession.selectList("com.gw.team_list");
		
	}
	
	

}