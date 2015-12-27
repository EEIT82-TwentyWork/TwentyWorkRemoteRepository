package com.iii.twentywork.model.bean;

public class BoardBean {
	private int boardID;
	private String boardTitle;
	private String boardText;
	private java.util.Date boardTime;
	
	@Override
	public String toString() {
		return "BoardBean [boardID=" + boardID + ", boardTitle=" + boardTitle
				+ ", boardText=" + boardText + ", boardTime=" + boardTime + "]";
	}
	
	public int getBoardID() {
		return boardID;
	}
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardText() {
		return boardText;
	}
	public void setBoardText(String boardText) {
		this.boardText = boardText;
	}
	public java.util.Date getBoardTime() {
		return boardTime;
	}
	public void setBoardTime(java.util.Date boardTime) {
		this.boardTime = boardTime;
	}
	
}
