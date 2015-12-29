package com.iii.twentywork.model.bean.team;

public class TeamBean {
	private int teamID;
	private String teamName;
	private byte[] teamImage;
	private String teamAbout;
	
	public byte[] getTeamImage() {
		return teamImage;
	}
	public void setTeamImage(byte[] teamImage) {
		this.teamImage = teamImage;
	}
	public String getTeamAbout() {
		return teamAbout;
	}
	public void setTeamAbout(String teamAbout) {
		this.teamAbout = teamAbout;
	}
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	
}
