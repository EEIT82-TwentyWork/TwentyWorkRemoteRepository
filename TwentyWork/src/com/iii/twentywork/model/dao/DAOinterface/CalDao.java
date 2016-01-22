package com.iii.twentywork.model.dao.DAOinterface;

import java.util.Date;
import java.util.List;

import com.iii.twentywork.model.bean.MySchedule;
import com.iii.twentywork.model.bean.UsersBean;


public interface CalDao {
	
//	public abstract MySchedule findEvent(MySchedule bean);
	
//	public abstract MySchedule select(MySchedule bean);

	public abstract List<MySchedule> select(String id);

	public abstract MySchedule insert(MySchedule bean);

	public abstract MySchedule update(String title, java.util.Date start,
			java.util.Date end, String myEventDesp, String myLocation, java.util.Date myRminder, String allday, String color, String myUserID, String id);
	
//	public abstract boolean deleteParticipant(String id);
	
	public abstract boolean delete(String id);
	
	public abstract UsersBean selectMyUsersBean(UsersBean userbean);

}