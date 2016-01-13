package com.iii.twentywork.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.dao.DAOinterface.RegisterDAO;

@Component("personalService")
public class PersonalService {
	@Autowired
	private RegisterDAO registerDAO;

	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}

	public TeamBean selectTeamName(String teamName) {
		TeamBean result = registerDAO.selectTeamPersonal(teamName);
		return result;
	}

}
