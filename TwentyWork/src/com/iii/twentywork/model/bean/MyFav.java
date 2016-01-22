package com.iii.twentywork.model.bean;

import java.util.Date;

public class MyFav implements java.io.Serializable
{

    @Override
	public String toString() {
		return "MyFav [ boardTitle=" + board.getBoardTitle() + ", teamName=" + team.getTeamName() + ", userName=" + users.getUserName() + "]";
	}

	private MyFavId id;
    private Board board;
    private TeamBean team;
    private UsersBean users;
    private Date activeTime;
    private String favTitle;

    public MyFav()
    {
    }

    public MyFav(MyFavId id, Board board, UsersBean users)
    {
        this.id = id;
        this.board = board;
        this.users = users;
    }

    public MyFav(MyFavId id, Board board, TeamBean team, UsersBean users)
    {
        this.id = id;
        this.board = board;
        this.team = team;
        this.users = users;
    }

    public MyFavId getId()
    {
        return this.id;
    }

    public void setId(MyFavId id)
    {
        this.id = id;
    }

    public Board getBoard()
    {
        return this.board;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public TeamBean getTeam()
    {
        return this.team;
    }

    public void setTeam(TeamBean team)
    {
        this.team = team;
    }

    public UsersBean getUsers()
    {
        return this.users;
    }

    public void setUsers(UsersBean users)
    {
        this.users = users;
    }

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public String getFavTitle() {
		return favTitle;
	}

	public void setFavTitle(String favTitle) {
		this.favTitle = favTitle;
	}

}
