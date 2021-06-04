package com.gw.app.dto;

public class Position_dto {
	
	private int position_idx;
	private String position_name;
	
	public Position_dto() {}
	
//	CREATE TABLE gw_position
//	(
//	    `position_idx`   INT            NOT NULL    AUTO_INCREMENT COMMENT '직급 고유번호', 
//	    `position_name`  VARCHAR(50)    NOT NULL    COMMENT '직급', 
//	    CONSTRAINT  PRIMARY KEY (position_idx)
//	)default character set utf8 collate UTF8_GENERAL_CI;

	
	public Position_dto(int position_idx, String position_name) {
		this.position_idx = position_idx;
		this.position_name = position_name;
	}

	public int getPosition_idx() {
		return position_idx;
	}

	public void setPosition_idx(int position_idx) {
		this.position_idx = position_idx;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	
	
	

}
