package com.gw.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gw.app.dto.Data_dto;



@Repository
public class Data_dao {

	@Autowired
	private SqlSession sqlSession;
	
	//자료실 게시판 목록 가져오기
	public List<Data_dto> dataroom_list(Map<String, Object> map){
		return sqlSession.selectList("com.gw.dataroom_list", map);
	}
	
	//자료실 게시글 가져오기
	public Data_dto dataroom_read(int data_idx) {
		return sqlSession.selectOne("com.gw.dataroom_read", data_idx);
	}
	
	//자료실 게시글 작성
	//파일 업로드까지 완료 21.05.20 
	public int dataroom_insert(Map<String, Object> map) {
		return sqlSession.insert("com.gw.dataroom_insert", map);
		
	}
	
	//나중에 글번호에 맞는 데이터베이스 가져올 때 사용할 수 있음
	//데이트 삭제를 위해 아이디엑스 값으로 파일명 파일 경로를 뽑아오는 것
	public Map<String, Object> dataroom_download(int data_idx) {
		return sqlSession.selectOne("com.gw.dataroom_download", data_idx);
	}
	
	//데이터 삭제와 게시글 삭제를 동시에 진행
	public int dataroom_delete(int data_idx) {
		return sqlSession.delete("com.gw.dataroom_delete", data_idx);
			
	}
	
	//데이터 업데이트를 위해서 ver1 자료실 파일까지 변경할 경우
		public int dataroom_update1(Map<String, Object> map) {
			return sqlSession.update("com.gw.dataroom_update1", map);
			
		}
		
		//데이터 업데이트를 위해서 ver2 자료실 파일은 변경 없을 경우  
		public int dataroom_update2(Map<String, Object> map) {
			return sqlSession.update("com.gw.dataroom_update2", map);
		}
		
		//페이징 처리를 위한 dao
		public int dataroom_paging() {
			return sqlSession.selectOne("com.gw.dataroom_paging");
		}
	
	
}
