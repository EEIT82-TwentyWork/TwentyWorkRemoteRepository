package com.iii.twentywork.model.dao.DAOinterface;

import java.util.Date;
import java.util.List;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.TeamSchedule;
import com.iii.twentywork.model.bean.UsersBean;

public interface TeamCalDAO {

	public abstract List<TeamSchedule> select(String teamid);

	public abstract TeamSchedule insert(TeamSchedule bean);

	public abstract TeamSchedule update(String eventName, java.util.Date eventStartDate, java.util.Date eventEndDate,
			String eventDesp, String location, java.util.Date reminder, String eventAllday, String eventColor,
			String teamScheduleUserID, TeamBean team, String eventId);
	
	public abstract boolean delete(String eventID);

	public abstract TeamBean selectMyTeamBean(TeamBean bean);
}