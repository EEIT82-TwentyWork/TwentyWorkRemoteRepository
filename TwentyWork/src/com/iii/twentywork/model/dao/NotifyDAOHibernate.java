package com.iii.twentywork.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.Notify;
import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;

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

	public UsersBean selectByUserId(String pk) {
		UsersBean bean = (UsersBean) getSession().get(UsersBean.class, pk);
		return bean;
	}

//	testing#3
	private static final String SELECT_NOTIFY_BY_USERID = "select * from notify where userID =?";
	public List<Notify> selectNotifyByUserId(String pk) {
		System.out.println("here is notifyDAO.selectNotifyByUserId");
		SQLQuery query = getSession().createSQLQuery(SELECT_NOTIFY_BY_USERID);
		query.setParameter(0, pk);
		query.addEntity(Notify.class);
		List<Notify> list = query.list();
		for (int i=0;i<list.size();i++){
			System.out.println(i+":"+list.get(i));
		}
		System.out.println("end--------------------------");
		return list;
	}
	
	private static final String SELECT_NOTIFY_BY_SENDUSERID = "select * from notify where sendUserID =? ";
	public List<Notify> selectNotifyBySendUserId(String pk) {
		System.out.println("here is notifyDAO.selectNotifyBySendUserId");
		SQLQuery query = getSession().createSQLQuery(SELECT_NOTIFY_BY_SENDUSERID);
		query.setParameter(0, pk);
		query.addEntity(Notify.class);
		List<Notify> list = query.list();
		for (int i=0;i<list.size();i++){
			System.out.println(i+":"+list.get(i));
		}
		System.out.println("end--------------------------");
		return list;
	}
	
	public Notify updateReadState(String notifyId)
    {
		Notify newFolderBean = (Notify) getSession().get(Notify.class,notifyId);
    	newFolderBean.setReadState("yes");
        return newFolderBean;
    }
	

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().beginTransaction();
		
		//testing#4
		NotifyDAOHibernate dao = (NotifyDAOHibernate) context.getBean("notifyDAO");
		List<Notify> list = dao.selectNotifyBySendUserId("8a80808452735c8c0152736c21d80006");
		for (int i=0;i<list.size();i++){
			System.out.println(i+":"+list.get(i));
		}

		//testing#3
//		NotifyDAOHibernate dao = (NotifyDAOHibernate) context.getBean("notifyDAO");
//		List<Notify> list = dao.selectNotifyBySendUserId("8a80808452735c8c0152736c21d80006");
//		for (int i=0;i<list.size();i++){
//			System.out.println(i+":"+list.get(i));
//		}
		
		// testing#1
//		NotifyDAOHibernate dao = (NotifyDAOHibernate) context.getBean("notifyDAO");
//		System.out.println(dao.selectById("40289fee526ddeb501526ddfc71f0001"));

		// //testing#2
		// NotifyDAOHibernate dao = (NotifyDAOHibernate)
		// context.getBean("notifyDAO");
		// Notify bean = new Notify();
		// bean.setComment("hello test comment");
		// dao.insert( bean);


		sessionFactory.getCurrentSession().getTransaction().commit();
	}

}
