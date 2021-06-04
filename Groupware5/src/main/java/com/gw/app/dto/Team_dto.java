package com.gw.app.dto;

public class Team_dto {
	
	private int team_idx;
	private String team_name;
	
	public Team_dto() {}
	
//	CREATE TABLE gw_team
//	(
//	    `team_idx`   INT            NOT NULL    AUTO_INCREMENT COMMENT '부서고유번호', 
//	    `team_name`  VARCHAR(50)    NOT NULL    COMMENT '부서', 
//	    CONSTRAINT  PRIMARY KEY (team_idx)
//	)default character set utf8 collate UTF8_GENERAL_CI;

	
	public Team_dto(int team_idx, String team_name) {
		this.team_idx = team_idx;
		this.team_name = team_name;
	}

	public int getTeam_idx() {
		return team_idx;
	}

	public void setTeam_idx(int team_idx) {
		this.team_idx = team_idx;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	
	

}
