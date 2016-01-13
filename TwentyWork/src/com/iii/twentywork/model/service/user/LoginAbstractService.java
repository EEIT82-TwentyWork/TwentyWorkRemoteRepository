package com.iii.twentywork.model.service.user;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.AbstractLoginDAOHibernate;

@Component("loginAbstractService")
public class LoginAbstractService extends AbstractLoginDAOHibernate {

	public UsersBean loginUserInfoCheck(String teamName, String email, String password) {
		UsersBean usersInfo = SelectLoginEmail(email);
		if (usersInfo != null) {
			if (password != null && password.trim().length() != 0) {
				byte[] pass = usersInfo.getPassword();
				byte[] temp = password.getBytes();
				TeamBean teamInfo = usersInfo.getTeams().iterator().next();
				String DBTeamName = teamInfo.getTeamName();
				if (Arrays.equals(pass, temp) && teamName.equals(DBTeamName)) {
					return usersInfo;
				}
			}
		}
		return null;
	}
}
