package com.gw.app.dto;

public class Notice_dto {
	private int notice_idx;
	private int user_idx;
	private String notice_title;
	private String notice_content;
	private int notice_count;
	private String notice_date;
	private String user_name;
	
	
	
	public Notice_dto() {}
	
	
	public Notice_dto(int notice_idx, int user_idx, String notice_title, String notice_content, int notice_count,
			String notice_date, String user_name) {
		super();
		this.notice_idx = notice_idx;
		this.user_idx = user_idx;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_count = notice_count;
		this.notice_date = notice_date;
		this.user_name = user_name;
	}
	
	
	public int getNotice_idx() {
		return notice_idx;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public int getNotice_count() {
		return notice_count;
	}
	public void setNotice_count(int notice_count) {
		this.notice_count = notice_count;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	
	
	
	
}
