package com.iii.twentywork.model.bean;

import java.util.Date;

public class Notify implements java.io.Serializable
{

//	--14--
//	create table MyFav									||create table Notify
//	(
//		teamID varchar(32) references Team  (teamID), ||teamID varchar(32) references Team  (teamID),
//	    userID varchar(32) references Users (userID), ||userID varchar(32) references Users (userID),
//	    boardID varchar(32) references Board(boardID),||fileId int references ShareFile(fileId),
//		activeTime datetime not null,					||shareTime datetime not null,
//		favTitle varchar(50) not null,					||comment varchar(max) ,
//														||readState  varchar(5),
//	    constraint MyFavID primary key(userID,boardID),	||constraint Notify primary key(userID,fileId),
//	)
//	GO
	
	
	

	private NotifyId id;
    @Override
	public String toString() {
		return "Notify [id=" + id + ", team=" + team + ", users=" + users + ", file=" + file + ", shareTime="
				+ shareTime + ", comment=" + comment + ", readState=" + readState + "]";
	}

	private TeamBean team;
    private UsersBean users;
    private ShareFileBean file;
    private Date shareTime;
    private String comment;
    private String readState;

    public Notify()
    {
    }

	public NotifyId getId() {
		return id;
	}

	public void setId(NotifyId id) {
		this.id = id;
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


}
