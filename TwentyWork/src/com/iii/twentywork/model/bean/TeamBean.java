package com.iii.twentywork.model.bean;

import java.util.Arrays;
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
@Component("teamBean")
public class TeamBean implements java.io.Serializable {
	public TeamBean() {
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "teamid", unique = true)
	private String teamId;
	private String teamName;
	private byte[] teamImage;
	private String teamAbout;
	private Set<UsersBean> userses;
//	private Set<TeamSchedule> teamSchedules;

	/*
	 * @OneToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "TeamUser", joinColumns = {
	 * 
	 * @JoinColumn(name = "teamId", referencedColumnName = "teamId") },
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "userID", referencedColumnName = "userID") }) private
	 * List<UsersBean> UserBeanList;
	 * 
	 * public List<UsersBean> getUserBeanList() { return UserBeanList; }
	 * 
	 * public void setUserBeanList(List<UsersBean> userBeanList) { UserBeanList
	 * = userBeanList; }
	 */
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

	public Set<UsersBean> getUserses() {
		return userses;
	}

	public void setUserses(Set<UsersBean> userses) {
		this.userses = userses;
	}
	
//	public Set<TeamSchedule> getTeamSchedules() {
//		return teamSchedules;
//	}
//
//	public void setTeamSchedules(Set<TeamSchedule> teamSchedules) {
//		this.teamSchedules = teamSchedules;
//	}

	@Override
	public String toString() {
		return "TeamBean [teamId=" + teamId + ", teamName=" + teamName + ", teamImage=" + Arrays.toString(teamImage)
				+ ", teamAbout=" + teamAbout + "]";
	}


}
