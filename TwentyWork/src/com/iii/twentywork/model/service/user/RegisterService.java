package com.iii.twentywork.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.DAOinterface.RegisterDAO;

@Component(value = "registerService")
public class RegisterService {

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
}
