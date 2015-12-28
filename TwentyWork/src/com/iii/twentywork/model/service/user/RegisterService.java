package com.iii.twentywork.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.team.TeamBean;
import com.iii.twentywork.model.bean.users.UsersBean;
import com.iii.twentywork.model.daointerface.UserDAO;
@Component(value="RegisterService")
public class RegisterService {
	@Autowired
	private UserDAO userDAO;
	
		public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
		public UsersBean usersRegister(UsersBean usersBean){
			UsersBean result=null;
			if(usersBean!=null){
				result=userDAO.usersRegister(usersBean);
			}
			return result;
		}
		public TeamBean teamRegister(TeamBean teamBean){
			TeamBean teamResult=null;
			if(teamBean!=null){
				teamResult=userDAO.teamRegister(teamBean);
			}
			return teamResult;
		}
	
}
