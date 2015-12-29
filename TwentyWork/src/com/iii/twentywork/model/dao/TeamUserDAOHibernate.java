package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.iii.twentywork.model.bean.TeamUserBean;
import com.iii.twentywork.model.bean.TeamUserIdBean;
import com.iii.twentywork.model.daointerface.TeamUserDAO;

@Component(value = "teamUserDAO")
public class TeamUserDAOHibernate implements TeamUserDAO {
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
	


	/* (non-Javadoc)
     * @see com.iii.twentywork.model.dao.TeamUserDAO#insert(com.iii.twentywork.model.bean.teamuser.TeamUserBean)
     */
	@Override
    public TeamUserBean insert(TeamUserBean userBean) {
		return null;
	}

	@Override
    public TeamUserBean select(TeamUserIdBean id) 
	{//testing#1
	    TeamUserBean bean = (TeamUserBean) getSession().get(TeamUserBean.class, id);
		System.out.println("TeamUserDAOHibernate  select結束");
		return bean;
	}

	public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
        
        //testing#1
	    TeamUserIdBean teamUserId = new TeamUserIdBean(100,200);
	    TeamUserDAO dao = (TeamUserDAO) context.getBean("teamUserDAO");
	    TeamUserBean bean= dao.select(teamUserId);
	    System.out.println(bean);
	}

}
