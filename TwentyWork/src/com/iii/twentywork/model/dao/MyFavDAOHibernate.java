package com.iii.twentywork.model.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.MyFav;
import com.iii.twentywork.model.bean.MyFavId;

@Component("myFavDAO")
public class MyFavDAOHibernate {
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public MyFavDAOHibernate() {}
	
	private static final String SELECT_MY_FAV_LIST = "select * from myFav where teamId = ? and userId = ? ";
	public List<MyFav> selectMyFavList(String teamId,String userId)
    {
        SQLQuery query = getSession().createSQLQuery(SELECT_MY_FAV_LIST);
        query.setParameter(0, teamId);
        query.setParameter(1, userId);
        query.addEntity(MyFav.class);
        return query.list();
    }
	
	public MyFav selectByPk(String pk){
		return (MyFav) getSession().get(MyFav.class,pk);
	}
	public MyFav insert(MyFav bean){
		String pk = (String) getSession().save(bean);
		return selectByPk(pk);
	}
	
	public void delete(MyFavId pk){
		MyFav bean = (MyFav) getSession().get(MyFav.class,pk);
        getSession().delete(bean);
	}
	
	public static void main(String[] args) {
		 ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
	     SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
	     Session session = sessionFactory.getCurrentSession();
	     sessionFactory.getCurrentSession().beginTransaction();
	       
	     MyFavDAOHibernate dao = (MyFavDAOHibernate) context.getBean("myFavDAO");
	     List<MyFav> list = dao.selectMyFavList("F617582D839E4CC2B0B50DB977DEFF59","D1DF6344923B41B794C71AB9DE038C4A");
	     System.out.println(list);
	     
	     sessionFactory.getCurrentSession().getTransaction().commit();
	}

}
