package com.gw.app.dto;

public class Email_dto {

	private int email_idx;
	private int user_idx;
	private int email_re;
	private String email_title;
	private String email_content;
	private String email_readtime;
	private String email_imgname;
	private String email_imgpath;
	private String email_day;
	private String user_name;
	private String re_name;
	
	public Email_dto() {}
	
	
	
	
	public Email_dto(int email_idx, int user_idx, int email_re, String email_title, String email_content,
			String email_readtime, String email_imgname, String email_imgpath, String email_day, String user_name, String re_name) {
		super();
		this.email_idx = email_idx;
		this.user_idx = user_idx;
		this.email_re = email_re;
		this.email_title = email_title;
		this.email_content = email_content;
		this.email_readtime = email_readtime;
		this.email_imgname = email_imgname;
		this.email_imgpath = email_imgpath;
		this.email_day = email_day;
		this.user_name = user_name;
		this.user_name = re_name;
		
	}
	
	
	
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	
	public String getRe_name() {
		return re_name;
	}

	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}




	public int getEmail_idx() {
		return email_idx;
	}
	public void setEmail_idx(int email_idx) {
		this.email_idx = email_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public int getEmail_re() {
		return email_re;
	}
	public void setEmail_re(int email_re) {
		this.email_re = email_re;
	}
	public String getEmail_title() {
		return email_title;
	}
	public void setEmail_title(String email_title) {
		this.email_title = email_title;
	}
	public String getEmail_content() {
		return email_content;
	}
	public void setEmail_content(String email_content) {
		this.email_content = email_content;
	}
	public String getEmail_readtime() {
		return email_readtime;
	}
	public void setEmail_readtime(String email_readtime) {
		this.email_readtime = email_readtime;
	}
	public String getEmail_imgname() {
		return email_imgname;
	}
	public void setEmail_imgname(String email_imgname) {
		this.email_imgname = email_imgname;
	}
	public String getEmail_imgpath() {
		return email_imgpath;
	}
	public void setEmail_imgpath(String email_imgpath) {
		this.email_imgpath = email_imgpath;
	}
	public String getEmail_day() {
		return email_day;
	}
	public void setEmail_day(String email_day) {
		this.email_day = email_day;
	}
	
	
}
