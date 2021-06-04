package com.gw.app.dto;

public class Work_dto {
	private int work_idx;
	private int user_idx;
	private String work_on;
	private String work_off;
	private String user_name;
	

	
	public Work_dto() {}
	
	
	
	
	public Work_dto(int work_idx, int user_idx, String work_on, String work_off, String user_name) {
		super();
		this.work_idx = work_idx;
		this.user_idx = user_idx;
		this.work_on = work_on;
		this.work_off = work_off;
		this.user_name = user_name;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public int getWork_idx() {
		return work_idx;
	}
	public void setWork_idx(int work_idx) {
		this.work_idx = work_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getWork_on() {
		return work_on;
	}
	public void setWork_on(String work_on) {
		this.work_on = work_on;
	}
	public String getWork_off() {
		return work_off;
	}
	public void setWork_off(String work_off) {
		this.work_off = work_off;
	}
	
	
	
	
	
}
