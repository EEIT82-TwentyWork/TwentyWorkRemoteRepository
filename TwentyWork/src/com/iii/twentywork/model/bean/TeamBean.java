package com.iii.twentywork.model.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "TEAM")
public class TeamBean implements java.io.Serializable {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "teamid", unique = true)
	private String teamId;
	private String teamName;

	private byte[] teamImage;

	private String teamAbout;


	public TeamBean() {
	}

	public TeamBean(String teamId, String teamName, String teamAbout) {
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamAbout = teamAbout;
	}

	


	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getTeamAbout() {
		return teamAbout;
	}

	public void setTeamAbout(String teamAbout) {
		this.teamAbout = teamAbout;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public byte[] getTeamImage() {
		return this.teamImage;
	}

	public void setTeamImage(byte[] teamImage) {
		this.teamImage = teamImage;
	}

	public String getteamAbout() {
		return this.teamAbout;
	}

	public void setteamAbout(String teamAbout) {
		this.teamAbout = teamAbout;
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + "]";
	}

}
