package com.iii.twentywork.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.daointerface.UserDAO;
@Component(value = "registerService")
public class RegisterService {
	@Autowired
	private UserDAO userDAO;
	
		public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
		System.out.println("RegisterServicesetUserDAO");
	}
		public UsersBean usersRegister(UsersBean usersBean){
			System.out.println("進入usersRegister");
			UsersBean result=null;
			if(usersBean!=null){
				result=userDAO.usersRegister(usersBean);
				System.out.println("userDAO.usersRegister結束");
			}
			return result;
		}
		public TeamBean teamRegister(TeamBean teamBean){
			System.out.println("進入teamRegister");
			TeamBean teamResult=null;
			if(teamBean!=null){
				teamResult=userDAO.teamRegister(teamBean);
				System.out.println("userDAO.teamRegister結束");
			}
			return teamResult;
		}
	
}
