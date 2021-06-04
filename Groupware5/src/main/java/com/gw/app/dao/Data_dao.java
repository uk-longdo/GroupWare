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
	
	//�ڷ�� �Խ��� ��� ��������
	public List<Data_dto> dataroom_list(Map<String, Object> map){
		return sqlSession.selectList("com.gw.dataroom_list", map);
	}
	
	//�ڷ�� �Խñ� ��������
	public Data_dto dataroom_read(int data_idx) {
		return sqlSession.selectOne("com.gw.dataroom_read", data_idx);
	}
	
	//�ڷ�� �Խñ� �ۼ�
	//���� ���ε���� �Ϸ� 21.05.20 
	public int dataroom_insert(Map<String, Object> map) {
		return sqlSession.insert("com.gw.dataroom_insert", map);
		
	}
	
	//���߿� �۹�ȣ�� �´� �����ͺ��̽� ������ �� ����� �� ����
	//����Ʈ ������ ���� ���̵𿢽� ������ ���ϸ� ���� ��θ� �̾ƿ��� ��
	public Map<String, Object> dataroom_download(int data_idx) {
		return sqlSession.selectOne("com.gw.dataroom_download", data_idx);
	}
	
	//������ ������ �Խñ� ������ ���ÿ� ����
	public int dataroom_delete(int data_idx) {
		return sqlSession.delete("com.gw.dataroom_delete", data_idx);
			
	}
	
	//������ ������Ʈ�� ���ؼ� ver1 �ڷ�� ���ϱ��� ������ ���
		public int dataroom_update1(Map<String, Object> map) {
			return sqlSession.update("com.gw.dataroom_update1", map);
			
		}
		
		//������ ������Ʈ�� ���ؼ� ver2 �ڷ�� ������ ���� ���� ���  
		public int dataroom_update2(Map<String, Object> map) {
			return sqlSession.update("com.gw.dataroom_update2", map);
		}
		
		//����¡ ó���� ���� dao
		public int dataroom_paging() {
			return sqlSession.selectOne("com.gw.dataroom_paging");
		}
	
	
}
