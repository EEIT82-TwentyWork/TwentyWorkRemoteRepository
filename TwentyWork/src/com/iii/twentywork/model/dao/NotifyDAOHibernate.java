package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.Notify;
import com.iii.twentywork.model.bean.NotifyId;
import com.iii.twentywork.model.bean.ShareFileBean;

@Component(value = "notifyDAO")
public class NotifyDAOHibernate 
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    public NotifyDAOHibernate() {}
    
    public  NotifyId insert(Notify bean)
    {//testing#2
    	NotifyId pk = (NotifyId) getSession().save(bean);
//        System.out.println("ShareFileDAOHibernate--insert--pk="+pk);
        return pk;//;
    }
    public Notify selectById(NotifyId pk)
    {
    	Notify bean = (Notify) getSession().get(Notify.class, pk);
        return bean;
    }
    
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
        NotifyDAOHibernate dao = (NotifyDAOHibernate) context.getBean("notifyDAO");
        NotifyId pk = new NotifyId();
        pk.setFileId(902);
        pk.setUserId("40289fee526da90c01526dba89de0000");
        System.out.println(dao.selectById(pk));
//        40289fee526da90c01526dba89de0000 40289fee526da90c01526dba8a2b0001 
        
//        dao.insert
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    
}
