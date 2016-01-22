package com.iii.twentywork.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.MySchedule;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.DAOinterface.CalDao;


@Component(value="calDAO")
public class CalDAOHibernate implements CalDao {
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
//	@Override
//	public MySchedule findEvent(MySchedule bean) {
//		String queryStart = bean.convertDateToString(bean.getStart());
//		String queryEnd = bean.convertDateToString(bean.getEnd());
//		Query query = getSession().createQuery("from MySchedule where myEvent='"+bean.getTitle()+"' and myEventStartDate='"+queryStart+"' and myEventEndDate='"+queryEnd+"'");
//		MySchedule currMySchedule = (MySchedule) query.uniqueResult();
//		System.out.println("CalDAOHibernate findEvent currMySchedule="+currMySchedule);
//		return currMySchedule;
//	}

//	@Override
//	public MySchedule select(MySchedule bean) {
//		return (MySchedule) getSession().createSQLQuery("select * from MySchedule where myEvent='"+bean.getTitle()+"' and myEventStartDate='"+bean.getStart()+"' and myEventEndDate='"+bean.getEnd()+"'");
//	}

	@Override
	public List<MySchedule> select(String id) {
		System.out.println("CalDAOHibernate 裡面 select方法的ID等於 =>"+id);
		 Query query = getSession().createQuery("from MySchedule where myUserID='" + id + "'");
//	        query.addEntity(MySchedule.class);
		 List<MySchedule> mySchedules = query.list();
	        System.out.println("Hibernate >> List<MySchedule>"+mySchedules);
	        return mySchedules;
	}

	@Override
	public MySchedule insert(MySchedule bean) {
//		CalBean result = (CalBean) getSession().get(CalBean.class, bean.getId());
//		if(result==null) {
			getSession().save(bean);
			return bean;
//		}
//		return null;
	}

	@Override
	public MySchedule update(String title, Date start,
			Date end, String myEventDesp, String myLocation, java.util.Date myRminder, String allday, String color, String myUserID, String id) {
		System.out.println("HiberNate裡面update方法輸入的id等於" + id);
		MySchedule currMySchedule = (MySchedule) getSession().get(MySchedule.class, id);
//		Query query = getSession().createQuery("from MySchedule where myEventID='" + id + "'");
//		System.out.println("查詢from MySchedule where myEventID=id成功");
//		MySchedule currMySchedule = (MySchedule) query.uniqueResult();
//		System.out.println("取得單筆MySchedule成功");
		if(currMySchedule!=null) {
			currMySchedule.setId(id);
			currMySchedule.setTitle(title);
			currMySchedule.setStart(start);
			currMySchedule.setEnd(end);
			currMySchedule.setMyEventDesp(myEventDesp);
			currMySchedule.setMyLocation(myLocation);
			currMySchedule.setMyRminder(myRminder);
			currMySchedule.setAllday(allday);
			currMySchedule.setColor(color);
			currMySchedule.setMyUserID(myUserID);
		}
		return currMySchedule;
	}
	
//	@Override
//	public boolean deleteParticipant(String id) {
//		if(id!=null) {
//			System.out.println("deleteParticipant id不等於null");
//			SQLQuery queryParticipant = getSession().createSQLQuery("delete Participant where MyEventID='" + id + "'");
////			SQLQuery queryMySchedule = getSession().createSQLQuery("delete MySchedule where myEventID='" + id + "'");
//			System.out.println("deleteParticipant 做完SQLQuery return true");
//
//			return true;
//		}
//		System.out.println("deleteParticipant id等於null return false");
//		return false;
//	}
	
	@Override
	public boolean delete(String id) {
		MySchedule bean = (MySchedule) getSession().get(MySchedule.class, id);
		System.out.println("進入Hibernate delete方法");
		if(id!=null) {
			System.out.println("id不等於null");
//			SQLQuery queryParticipant = getSession().createSQLQuery("delete Participant where MyEventID='" + id + "'");
//			SQLQuery queryMySchedule = getSession().createSQLQuery("delete MySchedule where myEventID='" + id + "'");
//			System.out.println("做完SQLQuery return true");
//			Query queryMySchedule = getSession().createQuery("delete MySchedule where MyEventID='" + id + "'");
//			MySchedule currMySchedule = (MySchedule) query.uniqueResult();
//			System.out.println(queryParticipant);
//			System.out.println(queryMySchedule);
//			delete Participant where MyEventID='40289f005243edfb015243eeaf630000'
//			delete MySchedule where MyEventID='40289f005243edfb015243eeaf630000'

			getSession().delete(bean);
			return true;
		}
		System.out.println("id等於null return false");
		return false;
	}
	
	@Override
	public UsersBean selectMyUsersBean(UsersBean userbean) {
		System.out.println("selectMyUsersBean取得的ID等於"+userbean.getUserID());
		Query query = getSession().createQuery("from UsersBean where userID='" + userbean.getUserID() + "'");
		System.out.println("selectMyUsersBean取得的query等於"+query);
		UsersBean currUsersBean = (UsersBean) query.uniqueResult();
		System.out.println("selectMyUsersBean取得的currUsersBean等於"+query);
		return currUsersBean;
	}

}
