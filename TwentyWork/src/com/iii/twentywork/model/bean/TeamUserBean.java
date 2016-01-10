package com.iii.twentywork.model.bean;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
//@Entity
//@Table(name = "TEAMUSER")
public class TeamUserBean implements Serializable {
	
	private String userID;
	private String teamID;
	private String post;
	private String department;
	private String extension;
	private Date active;
	private int rights;

	public TeamUserBean() {
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
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

	public Date getActive() {
		return active;
	}

	public void setActive(Date active) {
		this.active = active;
	}

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof TeamUserBean)) {
			return false;
		}

		TeamUserBean teamUserBean = (TeamUserBean) obj;
		return new EqualsBuilder()
				.append(this.userID, teamUserBean.getTeamID())
				.append(this.teamID, teamUserBean.getUserID()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.userID).append(this.teamID)
				.toHashCode();
	}

}
