package com.iii.twentywork.model.service.calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iii.twentywork.model.bean.MySchedule;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.DAOinterface.CalDao;

@Component(value="calService")
public class CalService {
	@Autowired
	private CalDao calDao;
	public void setCalDao(CalDao calDao) {
		this.calDao = calDao;
	}
	
	public String callCalendar(MySchedule bean) {
		List<MySchedule> result = null;
		result = calDao.select();
		List<Map<String, String>> currCalendar = new ArrayList<>();
		System.out.println(result);
		Iterator<MySchedule> myScheduleIterator = result.iterator();
		while(myScheduleIterator.hasNext()){
			Map<String, String> calMap = new HashMap<String, String>();
			MySchedule thisSchedule = myScheduleIterator.next();
			calMap.put("id", thisSchedule.getId());
			calMap.put("title", thisSchedule.getTitle());
			calMap.put("start", thisSchedule.convertDateToString(thisSchedule.getStart()));
			calMap.put("end", thisSchedule.convertDateToString(thisSchedule.getEnd()));
			calMap.put("myEventDesp", thisSchedule.getMyEventDesp());
			calMap.put("myLocation", thisSchedule.getMyLocation());
			
			if(thisSchedule.getMyRminder()!=null){
				calMap.put("myRminder", thisSchedule.convertDateToString(thisSchedule.getMyRminder()));
			}
			
			calMap.put("allday", thisSchedule.getAllday());
			calMap.put("color", thisSchedule.getColor());
			currCalendar.add(calMap);
		};
		System.out.println("Service callCalendar result=" + currCalendar);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		String json = gson.toJson(currCalendar);
		System.out.println("Service callCalendar json=" + json);
		return json;
	}
	
	public MySchedule selectEvent(MySchedule bean) {
		MySchedule result = null;
		if(bean!=null) {
			result = calDao.findEvent(bean);
		}
		System.out.println("Service selectEvent result="+result);
		return result;
	}
	
	public List<MySchedule> select(MySchedule bean) {
		List<MySchedule> result = null;
		if(bean!=null && bean.getTitle()!=null) {
			MySchedule temp = calDao.select(bean);
			if(temp!=null) {
				result = new ArrayList<MySchedule>();
				result.add(temp);
			}
		} else {
			result = calDao.select(); 
		}
		return result;
	}
	public MySchedule insert(MySchedule bean) {
		MySchedule result = null;
		if(bean!=null) {
			result = calDao.insert(bean);
		}
		return result;
	}
	public MySchedule update(MySchedule bean) {
		MySchedule result = null;
		if(bean!=null) {
			result = calDao.update(bean.getTitle(), bean.getStart(), bean.getEnd(), bean.getMyEventDesp(), 
					bean.getMyLocation(), bean.getMyRminder(), bean.getAllday(), bean.getColor(), bean.getId());
		}
		return result;
	}
	public boolean delete(MySchedule bean) {
		boolean result = false;
		if(bean!=null) {
			result = calDao.delete(bean.getId());
		}
		return result;
	}
	public UsersBean selectUserBean(UsersBean bean){
		UsersBean result =null;
		if(bean!=null) {
			result = calDao.selectMyUsersBean(bean);
		}
		return result;
	}

}
