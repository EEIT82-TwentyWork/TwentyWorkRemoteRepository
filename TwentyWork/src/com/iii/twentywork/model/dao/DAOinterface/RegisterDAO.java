package com.iii.twentywork.model.dao.DAOinterface;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;

public interface RegisterDAO {

	public abstract UsersBean insertUserRegister(UsersBean userBean);

	public abstract TeamBean selectTeamPersonal(String teanName);
}