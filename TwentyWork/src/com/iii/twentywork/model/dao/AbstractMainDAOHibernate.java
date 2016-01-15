package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;

public abstract class AbstractMainDAOHibernate {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	public TeamBean selectTeamMain(String teamID) {
		TeamBean obj = (TeamBean) getSession().get(TeamBean.class, teamID);
		return obj;
	}
	public UsersBean selecUserMain(String userID){
		UsersBean obj=(UsersBean)getSession().get(UsersBean.class, userID);
		return obj;
	}
}
