package com.iii.twentywork.model.bean;

import java.util.Arrays;

public class TeamBean {
	private int teamID;
	private String teamName;
	private byte[] teamImage;
	
	@Override
	public String toString() {
		return "TeamBean [teamID=" + teamID + ", teamName=" + teamName
				+ ", teamImage=" + Arrays.toString(teamImage) + "]";
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
	public byte[] getTeamImage() {
		return teamImage;
	}
	public void setTeamImage(byte[] teamImage) {
		this.teamImage = teamImage;
	}
	
}
