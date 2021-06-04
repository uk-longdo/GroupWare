package com.gw.app.dto;

public class Data_dto {
	
//	`data_idx`      INT              NOT NULL    AUTO_INCREMENT COMMENT '자료번호', 
//    `user_idx`      INT              NOT NULL    COMMENT '사원번호', 
//    `data_title`    VARCHAR(200)     NOT NULL    COMMENT '제목', 
//    `data_up`       TEXT             NULL        COMMENT '업로드자료', 
//    `data_content`  VARCHAR(1000)    NOT NULL    COMMENT '내용', 
//    `data_date`     DATETIME         NOT NULL    COMMENT '작성일', 
//    `data_uppath`   TEXT             NULL        COMMENT '업로드자료경로'

	private int data_idx;
	private int user_idx;
	private String data_title;
	private String data_up;
	private String data_content;
	private String data_date;
	private String data_uppath;
	private String user_name;
	
	public Data_dto() {}
	
	public Data_dto(int data_idx, int user_idx, String data_title, String data_up, String data_content,
			String data_date, String data_uppath, String user_name) {
		this.data_idx = data_idx;
		this.user_idx = user_idx;
		this.data_title = data_title;
		this.data_up = data_up;
		this.data_content = data_content;
		this.data_date = data_date;
		this.data_uppath = data_uppath;
		this.user_name = user_name;
		
	}
	
	public int getData_idx() {
		return data_idx;
	}
	
	public void setData_idx(int data_idx) {
		this.data_idx = data_idx;
	}
	
	public int getUser_idx() {
		return user_idx;
	}
	
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getData_title() {
		return data_title;
	}

	public void setData_title(String data_title) {
		this.data_title = data_title;
	}

	public String getData_up() {
		return data_up;
	}

	public void setData_up(String data_up) {
		this.data_up = data_up;
	}

	public String getData_content() {
		return data_content;
	}

	public void setData_content(String data_content) {
		this.data_content = data_content;
	}

	public String getData_date() {
		return data_date;
	}

	public void setData_date(String data_date) {
		this.data_date = data_date;
	}

	public String getData_uppath() {
		return data_uppath;
	}

	public void setData_uppath(String data_uppath) {
		this.data_uppath = data_uppath;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}
