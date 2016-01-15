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
	
	@Override
	public MySchedule findEvent(MySchedule bean) {
		String queryStart = bean.convertDateToString(bean.getStart());
		String queryEnd = bean.convertDateToString(bean.getEnd());
		Query query = getSession().createQuery("from MySchedule where myEvent='"+bean.getTitle()+"' and myEventStartDate='"+queryStart+"' and myEventEndDate='"+queryEnd+"'");
		MySchedule currMySchedule = (MySchedule) query.uniqueResult();
		System.out.println("CalDAOHibernate findEvent currMySchedule="+currMySchedule);
		return currMySchedule;
	}

	@Override
	public MySchedule select(MySchedule bean) {
		return (MySchedule) getSession().createSQLQuery("select * from MySchedule where myEvent='"+bean.getTitle()+"' and myEventStartDate='"+bean.getStart()+"' and myEventEndDate='"+bean.getEnd()+"'");
	}

	@Override
	public List<MySchedule> select() {
//		List<CalBean>  temp =  (List<CalBean>) getSession().createSQLQuery("select * from calendar").list();
//		List<CalBean> temp = (List<CalBean>) getSession().createQuery("from CalList<CalBean> Bean").list();
		UsersBean bean = (UsersBean) getSession().get(UsersBean.class, "40289f005243513901524352418e0002");
		System.out.println("UsersBean="+bean);
		 SQLQuery query = getSession().createSQLQuery("select * from MySchedule");
	        query.addEntity(MySchedule.class);
	        System.out.println("Hibernate >> List<MySchedule>"+query.list());
	        return query.list();
		
		//格式正常
//		java.util.Date start = temp.get(0).getStarttime();
//		System.out.println(start);
//		System.out.println(temp);
//		return temp;
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
			Date end, String myEventDesp, String myLocation, java.util.Date myRminder, String allday, String color, String id) {
		MySchedule result = (MySchedule) getSession().get(MySchedule.class, id);
		if(result!=null) {
			result.setTitle(title);
			result.setStart(start);
			result.setEnd(end);
			result.setMyEventDesp(myEventDesp);
			result.setMyLocation(myLocation);
			result.setMyRminder(myRminder);
			result.setAllday(allday);
			result.setColor(color);
		}
		return result;
	}

	@Override
	public boolean delete(String id) {
		MySchedule bean = (MySchedule) getSession().get(MySchedule.class, id);
		if(bean!=null) {
			Query queryParticipant = getSession().createQuery("delete Participant where MyEventID='" + id + "'");
			Query queryMySchedule = getSession().createQuery("delete MySchedule where MyEventID='" + id + "'");
//			MySchedule currMySchedule = (MySchedule) query.uniqueResult();
			System.out.println(queryParticipant);
			System.out.println(queryMySchedule);
//			delete Participant where MyEventID='40289f005243edfb015243eeaf630000'
//			delete MySchedule where MyEventID='40289f005243edfb015243eeaf630000'

//			getSession().delete(bean);
			return true;
		}
		return false;
	}
	
	@Override
	public UsersBean selectMyUsersBean(UsersBean userbean) {
		UsersBean currbean = (UsersBean) getSession().get(UsersBean.class, userbean.getUserID());
		return currbean;
	}

}
