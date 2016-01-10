package com.iii.twentywork.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.DAOinterface.RegisterDAO;

@Component(value = "registerService")
public class RegisterService {
	private UsersBean userBean;
	private TeamBean teamBean;

	@Autowired
	private RegisterDAO registerDAO;

	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}

	public UsersBean insertUserRegister(UsersBean userBean) {
		UsersBean result = null;
		result = registerDAO.insertUserRegister(userBean);
		return result;
	}

	public TeamBean insertTeamRegister(TeamBean teamBean) {
		TeamBean result = null;
		result = registerDAO.insertTeamRegister(teamBean);
		return result;
	}

	// @Autowired
	// private SessionFactory sessionFactory;
	//
	// public void setSessionFactory(SessionFactory sessionFactory) {
	// this.sessionFactory = sessionFactory;
	// }
	//
	// public Session getSession() {
	// Session session = sessionFactory.getCurrentSession();
	// return session;
	// }

	// public UsersBean accessUserBean(String fullname, String email, byte[]
	// password, Date birth, String cellPhone) {
	// accessUserBean.setUserName(fullname);
	// accessUserBean.setEmail(email);
	// accessUserBean.setPassword(password);
	// accessUserBean.setBirth(birth);
	// accessUserBean.setCellPhone(cellPhone);
	// accessUserBean.setPhone(null);
	// accessUserBean.setUserImage(null);
	// return null;
	// }
	// public TeamBean accessuserBean(String teamName,String teamAbout){
	// accessTeamBean.setTeamName(teamName);
	// accessTeamBean.setteamAbout(teamAbout);
	// return null;
	// }
	//
	// public UsersBean userRegister(UsersBean userBean) {
	// UsersBean result = null;
	// if (userBean != null) {
	//
	// result = insertUserRegister(userBean);
	// }
	// return result;
	// }
	//
	// public TeamBean teamRegister(TeamBean teamBean) {
	// TeamBean result = null;
	// if (teamBean != null) {
	// result = insertTeamRegister(teamBean);
	// }
	// return result;
	// }
}
