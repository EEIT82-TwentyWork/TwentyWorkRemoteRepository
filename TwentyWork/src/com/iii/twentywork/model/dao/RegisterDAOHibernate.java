package com.iii.twentywork.model.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.DAOinterface.RegisterDAO;

/**
 * @author kiwi 1.using DAO Interface 2.save userBean 3.entity Users Team
 *         TeamUser
 */
@Component("registerDAO")
public class RegisterDAOHibernate implements RegisterDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	@Override
	public UsersBean insertUserRegister(UsersBean userBean) {
		getSession().save(userBean);
		return userBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PersonalService(java.lang.String)
	 */
	@Override
	public TeamBean selectTeamPersonal(String teanName) {
		Query query = getSession().createQuery("from TeamBean where teamName='" + teanName + "'");
		TeamBean obj = (TeamBean) query.uniqueResult();
		return obj;
	}

}
