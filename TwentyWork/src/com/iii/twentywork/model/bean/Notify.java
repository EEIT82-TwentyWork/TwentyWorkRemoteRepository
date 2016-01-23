package com.iii.twentywork.model.bean;

import java.util.Date;

public class Notify implements java.io.Serializable
{
	@Override
	public String toString() {
		return "Notify [notifyID=" + notifyID + ", team=" + team + ", users=" + users + ", sendUser=" + sendUser
				+ ", file=" + file + ", shareTime=" + shareTime + ", comment=" + comment + ", readState=" + readState
				+ "]";
	}

	private String notifyID;
	private TeamBean team;
    private UsersBean users;
    private UsersBean sendUser;
    private ShareFileBean file;
    private Date shareTime;
    private String comment;
    private String readState;

    public Notify()
    {
    }


	public String getNotifyID() {
		return notifyID;
	}


	public void setNotifyID(String notifyID) {
		this.notifyID = notifyID;
	}


	public TeamBean getTeam() {
		return team;
	}

	public void setTeam(TeamBean team) {
		this.team = team;
	}

	public UsersBean getUsers() {
		return users;
	}

	public void setUsers(UsersBean users) {
		this.users = users;
	}

	public ShareFileBean getFile() {
		return file;
	}

	public void setFile(ShareFileBean file) {
		this.file = file;
	}

	public Date getShareTime() {
		return shareTime;
	}

	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReadState() {
		return readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

	public UsersBean getSendUser() {
		return sendUser;
	}

	public void setSendUser(UsersBean sendUser) {
		this.sendUser = sendUser;
	}


}
