package com.iii.twentywork.model.daointerface;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
public interface UserDAO {

	public abstract UsersBean select(int userID);
	public abstract UsersBean SelectEmail(String email);
	public abstract TeamBean SelectTeamName(String teamName);
	public abstract UsersBean usersRegister(UsersBean usersBean);
	public abstract TeamBean teamRegister(TeamBean teamBean);
	

}
