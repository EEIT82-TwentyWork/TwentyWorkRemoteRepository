package com.iii.twentywork.model.daointerface;

import com.iii.twentywork.model.bean.team.TeamBean;
import com.iii.twentywork.model.bean.users.UsersBean;
public interface UserDAO {

	public abstract UsersBean select(int userID);

	public abstract UsersBean usersRegister(UsersBean usersBean);
	
	public abstract TeamBean teamRegister(TeamBean teamBean);
	
	// public abstract boolean update(String userName, byte[] password,
	// java.util.Date birth, byte[] userImage, String cellPhone,
	// String phone, String email);

}