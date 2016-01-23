package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.Notify;

@Component(value = "notifyDAO")
public class NotifyDAOHibernate {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	public NotifyDAOHibernate() {
	}

	// testing#2
	public String insert(Notify bean) {
		String pk = (String) getSession().save(bean);
		return pk;// ;
	}

	// testing#1
	public Notify selectById(String pk) {
		Notify bean = (Notify) getSession().get(Notify.class, pk);
		return bean;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().beginTransaction();

		// testing#1
		 NotifyDAOHibernate dao = (NotifyDAOHibernate) context.getBean("notifyDAO");
		 System.out.println(dao.selectById("40289fee526ddeb501526ddfc71f0001"));

		// //testing#2
//		NotifyDAOHibernate dao = (NotifyDAOHibernate) context.getBean("notifyDAO");
//		Notify bean = new Notify();
//		bean.setComment("hello test comment");
//		
//		dao.insert( bean);
		
//		
//		teamID varchar(32) references Team  (teamID),
//	    userID varchar(32) references Users (userID),
//		sendUserID varchar(32) references Users (userID),
//	    fileId int references ShareFile(fileId),
//		shareTime datetime not null,
//		comment varchar(max) ,
//		readState  varchar(5),
		

		sessionFactory.getCurrentSession().getTransaction().commit();
	}

}
