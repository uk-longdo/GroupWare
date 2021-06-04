package com.gw.app.dto;

public class Board_dto {
	private int board_idx;
	private int user_idx;
	private String board_title;
	private String board_content;
	private int board_count;
	private String board_date;
	private int comm_cnt;
	
	
	public Board_dto() {}
	
	
	public Board_dto(int board_idx, int user_idx, String board_title, String board_content, int board_count,
			String board_date, int comm_cnt) {
		super();
		this.board_idx = board_idx;
		this.user_idx = user_idx;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_count = board_count;
		this.board_date = board_date;
		this.comm_cnt = comm_cnt;
		
	}
	
	
	
	public int getComm_cnt() {
		return comm_cnt;
	}


	public void setComm_cnt(int comm_cnt) {
		this.comm_cnt = comm_cnt;
	}


	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_count() {
		return board_count;
	}
	public void setBoard_count(int board_count) {
		this.board_count = board_count;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	
	
	
	
	
	
	
	
}
