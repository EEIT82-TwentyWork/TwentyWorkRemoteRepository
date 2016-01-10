package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.DAOinterface.RegisterDAO;
@Component("registerDAO")
public class RegisterDAOHibernate implements RegisterDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return null;
	}

	@Override
	public UsersBean insertUserRegister(UsersBean userBean) {
		getSession().save(userBean);
		return userBean;
	}

	@Override
	public TeamBean insertTeamRegister(TeamBean teamBean) {
		getSession().save(teamBean);
		return teamBean;
	}

}
