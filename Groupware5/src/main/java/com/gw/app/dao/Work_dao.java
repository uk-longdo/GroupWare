package com.gw.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.Work_dto;

@Repository
public class Work_dao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public int insert_work(Map<String, Object> map) {
		
		
		return sqlSession.insert("com.gw.insert_work", map);
	}
	
	public int work_update(Map<String, Object> map) {
		
		return sqlSession.update("com.gw.work_update", map);
	}
	
	public List<Work_dto> work_list(Map<String, Object> map) {
		
		return sqlSession.selectList("com.gw.work_list", map);
	}
	
	public String workon(Map<String, Object> map) {
		
		return sqlSession.selectOne("com.gw.workon", map); 
		
	}
	
	//���� ��� ���� ����� Ȯ�� dao
	public List<Work_dto> user_work_list(Map<String, Object> map){
		return sqlSession.selectList("com.gw.user_work_list", map);
	}
	
	//���� ��¥ ��� Ȯ�� dao
	public int work_inck(Map<String, Object> map) {
		return sqlSession.selectOne("com.gw.work_inck", map);
	}
		
	//��� or ���� ��¥ ��� Ȯ�� dao
	public int work_offck(Map<String, Object> map) {
		return sqlSession.selectOne("com.gw.work_offck", map);
	}
		
	//���� ��� ����¡ ó���� ���� ����
	public int user_work_paging(int user_idx) {
		return sqlSession.selectOne("com.gw.user_work_paging", user_idx);
	}
	
}
