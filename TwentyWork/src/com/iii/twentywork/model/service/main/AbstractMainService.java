package com.iii.twentywork.model.service.main;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.AbstractMainDAOHibernate;
@Component("abstractMainService")
public class AbstractMainService extends AbstractMainDAOHibernate {
	public TeamBean selectTeam(String teamID){
		TeamBean result =selectTeamMain(teamID);
		return result;
	}
	/**
	 * @param chatMatchServlet
	 * 
	 */
	public String toHex(String arg) {
		return String.format("%x", new BigInteger(1, arg.getBytes()));
	}
	public UsersBean selectName(String userID){
		UsersBean obj =selecUserMain(userID);
		return obj;
	}
	
	
}
