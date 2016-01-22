package com.iii.twentywork.model.service.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iii.twentywork.model.bean.MySchedule;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.TeamSchedule;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.DAOinterface.CalDao;
import com.iii.twentywork.model.dao.DAOinterface.TeamCalDAO;

@Component(value="calService")
public class CalService {
	@Autowired
	private CalDao calDao;
//	要多重注入時每注入一個Autowired就寫一個
	@Autowired
	private TeamCalDAO teamCalDAO;
	
	public void setCalDao(CalDao calDao) {
		this.calDao = calDao;
	}

	public void setTeamCalDAO(TeamCalDAO teamCalDAO) {
		this.teamCalDAO = teamCalDAO;
	}


	public String callCalendar(UsersBean bean, TeamBean teambean) {
//		定義總合LIST初始化
		List<Map<String, String>> currCalendar = new ArrayList<>();
//		個人及團隊LIST初始化
		List<MySchedule> myScheduleResult = null;
		List<TeamSchedule> teamScheduleResult = null;
//		個人Schedule
		myScheduleResult = calDao.select(bean.getUserID());
		System.out.println("callCalendar中myScheduleResult等於"+myScheduleResult);
		Iterator<MySchedule> myScheduleIterator = myScheduleResult.iterator();
		while(myScheduleIterator.hasNext()){
			Map<String, String> calMap = new HashMap<String, String>();
			MySchedule thisSchedule = myScheduleIterator.next();
			calMap.put("id", thisSchedule.getId());
			calMap.put("title", thisSchedule.getTitle());
			calMap.put("start", thisSchedule.convertDateToString(thisSchedule.getStart()));
			calMap.put("end", thisSchedule.convertDateToString(thisSchedule.getEnd()));
			calMap.put("eventDesp", thisSchedule.getMyEventDesp());
			calMap.put("location", thisSchedule.getMyLocation());
			if(thisSchedule.getMyRminder()!=null){
				calMap.put("rminder", thisSchedule.convertDateToString(thisSchedule.getMyRminder()));
			}
			calMap.put("allday", thisSchedule.getAllday());
			calMap.put("color", thisSchedule.getColor());
			calMap.put("myUserID", thisSchedule.getMyUserID());
			currCalendar.add(calMap);
		};
		
//		團隊Schedule
		System.out.println("callCalendar 的 teambean等於" + teambean);
		String teamid = teambean.getTeamId();
//		if(teamCalDAO.select(teamid) != null){

			teamScheduleResult = teamCalDAO.select(teamid);
			System.out.println("callCalendar 的 teamScheduleResult等於" + teamScheduleResult);
			
			Iterator<TeamSchedule> teamScheduleIterator = teamScheduleResult.iterator();
			
			while(teamScheduleIterator.hasNext()){
				Map<String, String> teamcalMap = new HashMap<String, String>();
				TeamSchedule thisTeamSchedule = teamScheduleIterator.next();
				teamcalMap.put("id", thisTeamSchedule.getEventId());
				teamcalMap.put("title", thisTeamSchedule.getEventName());
				teamcalMap.put("start", thisTeamSchedule.convertDateToString(thisTeamSchedule.getEventStartDate()));
				teamcalMap.put("end", thisTeamSchedule.convertDateToString(thisTeamSchedule.getEventEndDate()));
				teamcalMap.put("eventDesp", thisTeamSchedule.getEventDesp());
				teamcalMap.put("location", thisTeamSchedule.getLocation());
				if(thisTeamSchedule.getReminder()!=null){
					teamcalMap.put("rminder", thisTeamSchedule.convertDateToString(thisTeamSchedule.getReminder()));
				}
				teamcalMap.put("allday", thisTeamSchedule.getEventAllday());
				teamcalMap.put("color", thisTeamSchedule.getEventColor());
				teamcalMap.put("teamScheduleUserID", thisTeamSchedule.getTeamScheduleUserID());
				teamcalMap.put("teamID", thisTeamSchedule.getTeam().getTeamId());
				currCalendar.add(teamcalMap);
			};
			
//		};
		
		
		
		System.out.println("Service callCalendar result=" + currCalendar);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		String json = gson.toJson(currCalendar);
		System.out.println("Service callCalendar json=" + json);
		return json;
	}
	
	public String callTeamCalendar(TeamBean teambean) {
//		定義總合LIST初始化
		List<Map<String, String>> currCalendar = new ArrayList<>();
//		團隊LIST初始化
		List<TeamSchedule> teamScheduleResult = null;

//		團隊Schedule
		System.out.println("callCalendar 的 teambean等於" + teambean);
		String teamid = teambean.getTeamId();
//		if(teamCalDAO.select(teamid) != null){

			teamScheduleResult = teamCalDAO.select(teamid);
			System.out.println("callCalendar 的 teamScheduleResult等於" + teamScheduleResult);
			
			Iterator<TeamSchedule> teamScheduleIterator = teamScheduleResult.iterator();
//			List<String> AA = new ArrayList<>();
//			AA.set(0, teamid);
//			AA.set(1, teamid);
//			System.out.println("我的AA就等於"+AA);
			while(teamScheduleIterator.hasNext()){
				Map<String, String> teamcalMap = new HashMap<String, String>();
				TeamSchedule thisTeamSchedule = teamScheduleIterator.next();
				teamcalMap.put("id", thisTeamSchedule.getEventId());
				teamcalMap.put("title", thisTeamSchedule.getEventName());
				teamcalMap.put("start", thisTeamSchedule.convertDateToString(thisTeamSchedule.getEventStartDate()));
				teamcalMap.put("end", thisTeamSchedule.convertDateToString(thisTeamSchedule.getEventEndDate()));
				teamcalMap.put("eventDesp", thisTeamSchedule.getEventDesp());
				teamcalMap.put("location", thisTeamSchedule.getLocation());
				if(thisTeamSchedule.getReminder()!=null){
					teamcalMap.put("rminder", thisTeamSchedule.convertDateToString(thisTeamSchedule.getReminder()));
				}
				teamcalMap.put("allday", thisTeamSchedule.getEventAllday());
				teamcalMap.put("color", thisTeamSchedule.getEventColor());
				teamcalMap.put("teamScheduleUserID", thisTeamSchedule.getTeamScheduleUserID());
				teamcalMap.put("teamID", thisTeamSchedule.getTeam().getTeamId());
				currCalendar.add(teamcalMap);
			};
			
//		};
		
		
		
		System.out.println("Service callCalendar result=" + currCalendar);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		String json = gson.toJson(currCalendar);
		System.out.println("Service callCalendar json=" + json);
		return json;
	}
	
//	public MySchedule selectEvent(MySchedule bean) {
//		MySchedule result = null;
//		if(bean!=null) {
//			result = calDao.findEvent(bean);
//		}
//		System.out.println("Service selectEvent result="+result);
//		return result;
//	}
	
//	public List<MySchedule> select(MySchedule bean) {
//		List<MySchedule> result = null;
//		if(bean!=null && bean.getTitle()!=null) {
//			MySchedule temp = calDao.select(bean);
//			if(temp!=null) {
//				result = new ArrayList<MySchedule>();
//				result.add(temp);
//			}
//		} else {
//			result = calDao.select(bean.getId()); 
//		}
//		return result;
//	}
	public MySchedule insert(MySchedule bean) {
		MySchedule result = null;
		if(bean!=null) {
			result = calDao.insert(bean);
		}
		return result;
	}
	public TeamSchedule insertTeam(TeamSchedule bean) {
		TeamSchedule result = null;
		if(bean!=null) {
			result = teamCalDAO.insert(bean);
		}
		return result;
	}
	public MySchedule update(MySchedule bean) {
		MySchedule result = null;
		System.out.println("Service的Bean的Title等於" + bean.getTitle());
		System.out.println("Service的Bean的getStart等於" + bean.getStart());
		System.out.println("Service的Bean的getEnd等於" + bean.getEnd());
		System.out.println("Service的Bean的getMyEventDesp等於" + bean.getMyEventDesp());
		System.out.println("Service的Bean的getMyLocation等於" + bean.getMyLocation());
		System.out.println("Service的Bean的getMyRminder等於" + bean.getMyRminder());
		System.out.println("Service的Bean的getAllday等於" + bean.getAllday());
		System.out.println("Service的Bean的getColor等於" + bean.getColor());
		System.out.println("Service的Bean的getMyUserID等於" + bean.getMyUserID());
		System.out.println("Service的Bean的getId等於" + bean.getId());
		if(bean!=null) {
			result = calDao.update(bean.getTitle(), bean.getStart(), bean.getEnd(), bean.getMyEventDesp(), 
					bean.getMyLocation(), bean.getMyRminder(), bean.getAllday(), bean.getColor(), bean.getMyUserID(), bean.getId());
		}
		return result;
	}
	public TeamSchedule updateTeam(TeamSchedule bean) {
		TeamSchedule result = null;
		if(bean!=null) { 
			result = teamCalDAO.update(bean.getEventName(), bean.getEventStartDate(), bean.getEventEndDate(), bean.getEventDesp(), bean.getLocation(), bean.getReminder(), bean.getEventAllday(), bean.getEventColor(), bean.getTeamScheduleUserID(), bean.getTeam(), bean.getEventId());
		}
		return result;
	}
	public boolean delete(MySchedule bean) {
		boolean result = false;
		if(bean!=null) {
//			calDao.deleteParticipant(bean.getId());
			result = calDao.delete(bean.getId());
			
		}
		return result;
	}
	public boolean deleteTeam(TeamSchedule bean) {
		boolean result = false;
		if(bean!=null) {
			result = teamCalDAO.delete(bean.getEventId());
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
	public TeamBean selectTeamBean(TeamBean bean){
		TeamBean result =null;
		if(bean!=null) {
			result = teamCalDAO.selectMyTeamBean(bean);
		}
		return result;
	}
	

}
