package com.iii.twentywork.model.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;

public abstract class AbstractLoginDAOHibernate {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	public UsersBean SelectLoginEmail(String email) {
		Query query = getSession().createQuery("from UsersBean where email='" + email + "'");
		UsersBean obj = (UsersBean) query.uniqueResult();
		return obj;
	}

}
