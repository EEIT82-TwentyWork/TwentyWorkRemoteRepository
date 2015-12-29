package com.iii.twentywork.model.bean;

public class TeamUserBean {
	private String post;
	private String department;
	private String extension;
	private java.util.Date activeDate;
	private int rights;	
	private String teamUserID;
	
	@Override
	public String toString() {
		return "TeamUserBean [post=" + post + ", department=" + department
				+ ", extension=" + extension + ", activeDate=" + activeDate
				+ ", rights=" + rights + ", teamUserID=" + teamUserID + "]";
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public java.util.Date getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(java.util.Date activeDate) {
		this.activeDate = activeDate;
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
	public String getTeamUserID() {
		return teamUserID;
	}
	public void setTeamUserID(String teamUserID) {
		this.teamUserID = teamUserID;
	}
	

}