package com.iii.twentywork.model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.users.UsersBean;
import com.iii.twentywork.model.daointerface.UserDAO;

@Component(value = "userDAO")
public class UserDAOHibernate implements UserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("setSessionFactory結束");
	}
	
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("getSession結束");
		return session;
	}
	


	@Override
	public UsersBean insert(UsersBean userBean) {
		
		
		return null;
	}

	@Override
	public UsersBean select(int userID) {
		
		UsersBean temp = (UsersBean) getSession().get(UsersBean.class, userID);
		System.out.println("select結束");
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
	public static void main(String[] args) {

	}

}
