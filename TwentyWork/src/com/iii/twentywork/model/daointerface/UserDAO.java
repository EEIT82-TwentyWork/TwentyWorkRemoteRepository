package com.iii.twentywork.model.daointerface;

import com.iii.twentywork.model.bean.users.UsersBean;
import com.iii.twentywork.model.bean.team.TeamBean;
public interface UserDAO {

	public abstract UsersBean select(int userID);

	public abstract UsersBean usersRegister(UsersBean usersBean);
	
	public abstract TeamBean teamRegister(TeamBean teamBean);


}