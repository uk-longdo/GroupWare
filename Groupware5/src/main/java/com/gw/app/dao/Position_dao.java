package com.gw.app.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.Position_dto;



@Repository
public class Position_dao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//position_list를 가지고 오기 위한 dao
	public List<Position_dto> position_list(){
		return sqlSession.selectList("com.gw.position_list");
	}

}
