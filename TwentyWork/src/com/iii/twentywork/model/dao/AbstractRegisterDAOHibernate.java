package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;

/**
 * @author kiwi; SessionFactory Home; Entity UsersBean、TeamBean、TeamUserBean;
 *         Register and Login all Method;
 */
public abstract class AbstractRegisterDAOHibernate {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		// Session session = sessionFactory.getCurrentSession();
		return null;
	}

	public UsersBean insertUserRegister(UsersBean userbean) {
		return userbean;
	}

	public TeamBean insertTeamRegister(TeamBean teamBean) {
		return teamBean;
	}

}
