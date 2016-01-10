package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.TeamUserBean;
import com.iii.twentywork.model.bean.TeamUserIdBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.daointerface.TeamUserDAO;
import com.iii.twentywork.model.daointerface.UserDAO;

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
    public TeamUserBean insert(TeamUserBean teamUserBean) {
		System.out.println(teamUserBean);
		getSession().save(teamUserBean);
		return teamUserBean;
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
        UserDAO dao = (UserDAO) context.getBean("userDAO");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
        
        
        //testing#1
        TeamUserBean teamUserBean=new TeamUserBean();

        UsersBean userEmail=dao.SelectEmail("stu70226@gmail.com");
        TeamBean selectTeamName =dao.SelectTeamName("EEIT");
        TeamUserIdBean teamUserIdBean =new TeamUserIdBean();
        
        teamUserIdBean.setTeamId(selectTeamName.getTeamId());
        teamUserIdBean.setUserId(userEmail.getUserID());
        teamUserBean.setTeam(selectTeamName);
        teamUserBean.setUsers(userEmail);
        teamUserBean.setRights(1);
        teamUserBean.setActiveDate(new java.util.Date());
        teamUserBean.setId(teamUserIdBean);
        System.out.println(teamUserBean);
        TeamUserDAO teamUserDAO=new TeamUserDAOHibernate();
        teamUserDAO.insert(teamUserBean);
        
		sessionFactory.getCurrentSession().getTransaction().commit();
	    
	}

}
