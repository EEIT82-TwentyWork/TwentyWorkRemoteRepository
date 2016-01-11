package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "teamUserDAO")
public class TeamUserDAOHibernate  {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("TeamUserDAOHibernate setSessionFactory");

	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("TeamUserDAOHibernate getSession");
		return session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iii.twentywork.model.dao.TeamUserDAO#insert(com.iii.twentywork.model
	 * .bean.teamuser.TeamUserBean)
	 */
	/**
	 * @param teamUserBean
	 * @return
	 */
//	@Override
//	public TeamUserBean insert(TeamUserBean teamUserBean) {
//		System.out.println(teamUserBean);
//		getSession().save(teamUserBean);
//		return teamUserBean;
//	}
//
//	public static void main(String[] args) {
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"beans.config.xml");
//		SessionFactory sessionFactory = (SessionFactory) context
//				.getBean("sessionFactory");
//		UserDAO dao = (UserDAO) context.getBean("userDAO");
//		Session session = sessionFactory.getCurrentSession();
//		sessionFactory.getCurrentSession().beginTransaction();
//
//		// testing#1
//		TeamUserBean teamUserBean = new TeamUserBean();
//
//		UsersBean userEmail = dao.SelectEmail("stu70226@gmail.com");
//		TeamBean selectTeamName = dao.SelectTeamName("EEIT");
//
//		sessionFactory.getCurrentSession().getTransaction().commit();
//
//	}

}
