package com.gw.app.dto;

public class User_dto {
	private int user_idx;
	private String user_pw;
	private String user_name;
	private int team_idx;
	private int position_idx;
	private String user_day;
	private String user_tel;
	private String user_email;
	private String user_add;
	private String user_imgname;
	private String user_imgpath;
	private String team_name;
	private String position_name;
	private int user_rank;
	
public User_dto() {}
	

	
	public User_dto(int user_idx, String user_pw, String user_name, int team_idx, int position_idx, String user_day,
			String user_tel, String user_email, String user_add, String user_imgname, String user_imgpath,
			String team_name, String position_name, int user_rank) {
		super();
		this.user_idx = user_idx;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.team_idx = team_idx;
		this.position_idx = position_idx;
		this.user_day = user_day;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.user_add = user_add;
		this.user_imgname = user_imgname;
		this.user_imgpath = user_imgpath;
		this.team_name = team_name;
		this.position_name = position_name;
		this.user_rank = user_rank;
	}

	
	
	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}

	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getTeam_idx() {
		return team_idx;
	}
	public void setTeam_idx(int team_idx) {
		this.team_idx = team_idx;
	}
	public int getPosition_idx() {
		return position_idx;
	}
	public void setPosition_idx(int position_idx) {
		this.position_idx = position_idx;
	}
	public String getUser_day() {
		return user_day;
	}
	public void setUser_day(String user_day) {
		this.user_day = user_day;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_add() {
		return user_add;
	}
	public void setUser_add(String user_add) {
		this.user_add = user_add;
	}
	public String getUser_imgname() {
		return user_imgname;
	}
	public void setUser_imgname(String user_imgname) {
		this.user_imgname = user_imgname;
	}
	public String getUser_imgpath() {
		return user_imgpath;
	}
	public void setUser_imgpath(String user_imgpath) {
		this.user_imgpath = user_imgpath;
	}
	public int getUser_rank() {
		return user_rank;
	}
	public void setUser_rank(int user_rank) {
		this.user_rank = user_rank;
	}
}
