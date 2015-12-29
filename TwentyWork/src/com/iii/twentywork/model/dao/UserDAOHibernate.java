package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.daointerface.UserDAO;




@Component(value = "userDAO")
public class UserDAOHibernate implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("UserDAOHibernate setSessionFactory結束");
	}
	
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("UserDAOHibernate getSession結束");
		return session;
	}
	


	@Override
	public UsersBean usersRegister(UsersBean usersBean) {
		UsersBean result=(UsersBean)getSession().get(UsersBean.class,usersBean.getUserID());
		if(result == null){
			getSession().save(usersBean);
			System.out.println("usersRegister結束");
			return usersBean;
		}
		System.out.println("usersRegisternull結束");
		return null;
	}

	@Override
	public TeamBean teamRegister(TeamBean teamBean) {
		TeamBean result=(TeamBean)getSession().get(TeamBean.class,teamBean.getTeamName());
		if(result==null){
			getSession().save(teamBean);
			System.out.println("teamRegister結束");
			return teamBean;
		}
		System.out.println("teamRegisternull結束");
		return null;
	}

	@Override
	public UsersBean select(int userID) {
		
		UsersBean temp = (UsersBean) getSession().get(UsersBean.class, userID);
		System.out.println("UserDAOHibernate select結束");
		return temp;
	}

	// @Override
	// public boolean update(String userName, byte[] password, java.util.Date
	// birth, byte[] userImage, String cellPhone, String phone, String email) {
	// UsersBean bean = (UsersBean) getSession().get(UsersBean.class, email);
	// if(bean!=null) {
	// bean.setUserName(userName);
	// bean.setPassword(password);
	// bean.setBirth(birth);
	// bean.setUserImage(userImage);
	// bean.setCellPhone(cellPhone);
	// bean.setPhone(phone);
	// return true;
	// }
	// return false;
	// }
	
//	@Override
//	public UsersBean insert(UsersBean userBean, TeamBean teamBean) {
//		UsersBean usersBeanResult = (UsersBean) getSession().get(
//				UsersBean.class, userBean.getEmail());
//		TeamBean teamBeanResult = (TeamBean) getSession().get(TeamBean.class,
//				teamBean.getTeamName());
//		if (usersBeanResult == null&&teamBeanResult==null) {
//			getSession().save(userBean);
//			getSession().save(teamBean);
//			return userBean;
//		}
//		return null;
//	}
	
		public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
	    SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
	    UserDAO dao = (UserDAO) context.getBean("userDAO");
	    UsersBean bean = dao.select(100);
	    System.out.println(bean);
	}

}
