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
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.TeamSchedule;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.DAOinterface.TeamCalDAO;
@Component(value="teamCalDao")
public class TeamCalDAOHibernate implements TeamCalDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<TeamSchedule> select(String teamid) {
//		UsersBean bean = (UsersBean) getSession().get(UsersBean.class, "40289f005243513901524352418e0002");

		
		System.out.println("Team select 中 team id 等於" + teamid);
		Query query = getSession().createQuery("from TeamSchedule where teamID='" + teamid + "'");
//	       query.addEntity(TeamSchedule.class);
		List<TeamSchedule> teamSchedules = query.list();
	       System.out.println("Hibernate >> List<TeamSchedule>"+teamSchedules);
	       return teamSchedules;
	}
	

	@Override
	public TeamSchedule insert(TeamSchedule bean) {
//		CalBean result = (CalBean) getSession().get(CalBean.class, bean.getId());
//		if(result==null) {
			getSession().save(bean);
			return bean;
//		}
//		return null;
	}
	
	@Override
	public TeamSchedule update(String eventName, Date eventStartDate, Date eventEndDate, String eventDesp,
			String location, Date reminder, String eventAllday, String eventColor, String teamScheduleUserID,
			TeamBean team, String eventID) {

		TeamSchedule result = (TeamSchedule) getSession().get(TeamSchedule.class, eventID);

		if(result!=null) {
			result.setEventName(eventName);
			result.setEventStartDate(eventStartDate);
			result.setEventEndDate(eventEndDate);
			result.setEventDesp(eventDesp);
			result.setLocation(location);
			result.setReminder(reminder);
			result.setEventAllday(eventAllday);
			result.setEventColor(eventColor);
			result.setTeamScheduleUserID(teamScheduleUserID);
			result.setTeam(team);
		}
		return result;
	}
	

	@Override
	public boolean delete(String eventID) {
		System.out.println("HiberNate裡面 Delete時 getSession取得的TeamSchedule等於" + eventID);
		TeamSchedule bean = (TeamSchedule) getSession().get(TeamSchedule.class, eventID);
		System.out.println("HiberNate裡面 Delete時 getSession取得的TeamSchedule等於" + bean);
		if(eventID!=null) {
//			Query queryTeamSchedule = getSession().createQuery("delete TeamSchedule where eventID='" + eventID + "'");
//			SQLQuery queryTeamSchedule = getSession().createSQLQuery("DELETE * FROM TeamSchedule WHERE eventID ='" + eventID + "'");
			
//			TeamSchedule currMySchedule = (TeamSchedule) queryTeamSchedule.uniqueResult();
//			System.out.println(queryTeamSchedule);
			getSession().delete(bean);
			System.out.println("刪除成功成功成功成功成功成功成功成功成功成功成功成功");
			return true;
		}
		System.out.println("刪除失敗失敗失敗失敗失敗失敗失敗失敗失敗失敗");
		return false;
	}
	@Override
	public TeamBean selectMyTeamBean(TeamBean bean) {
		TeamBean currbean = (TeamBean) getSession().get(UsersBean.class, bean.getTeamId());
		return currbean;

	}

	
	
	
}
