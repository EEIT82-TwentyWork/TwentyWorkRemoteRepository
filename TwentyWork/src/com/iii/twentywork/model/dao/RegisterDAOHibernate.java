package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		return session;
	}

	@Override
	public UsersBean insertUserRegister(UsersBean userBean) {
		System.out.println("開始SAVE USERS");
		getSession().save(userBean);
//		Query query=getSession().createQuery("from users where email='"+ userBean.getEmail()+"'");
//		UsersBean obj= (UsersBean)query.uniqueResult();
		return userBean;
	}

	@Override
	public TeamBean insertTeamRegister(TeamBean teamBean) {
		System.out.println("開始SAVE team");
		getSession().save(teamBean);
//		Query query=getSession().createQuery("from users where teamName='"+ teamBean.getTeamName()+"'");
//		TeamBean obj=(TeamBean)query.uniqueResult();
		System.out.println("結束");
		return teamBean;
	}

}
