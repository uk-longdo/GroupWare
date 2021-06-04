package com.gw.app.dto;

public class Board_comm_dto {

	private int comm_idx;
	private int board_idx;
	private int user_idx;
	private String comm_cotent;
	private String comm_date;
	
	
	
	public Board_comm_dto() {}
	
	
	
	public Board_comm_dto(int comm_idx, int board_idx, int user_idx, String comm_cotent, String comm_date) {
		super();
		this.comm_idx = comm_idx;
		this.board_idx = board_idx;
		this.user_idx = user_idx;
		this.comm_cotent = comm_cotent;
		this.comm_date = comm_date;
	}
	public int getComm_idx() {
		return comm_idx;
	}
	public void setComm_idx(int comm_idx) {
		this.comm_idx = comm_idx;
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
	public String getComm_cotent() {
		return comm_cotent;
	}
	public void setComm_cotent(String comm_cotent) {
		this.comm_cotent = comm_cotent;
	}
	public String getComm_date() {
		return comm_date;
	}
	public void setComm_date(String comm_date) {
		this.comm_date = comm_date;
	}
	
	
	
	
}
