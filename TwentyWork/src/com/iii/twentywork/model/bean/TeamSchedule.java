package com.iii.twentywork.model.bean;
// Generated 2016/1/10 �U�� 06:05:34 by Hibernate Tools 4.3.1.Final

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

/**
 * TeamSchedule generated by hbm2java
 */
@Entity
@Table(name="TeamSchedule")
@Component("teamSchedule")
public class TeamSchedule implements java.io.Serializable {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "eventID", unique = true)
    private String eventId;

    private TeamBean team;

    private String eventName;

    private Date eventStartDate;

    private Date eventEndDate;

    private String eventDesp;

    private String location;

    private Date reminder;
    
    private String eventAllday;
    
    private String eventColor;
    
    private String teamScheduleUserID;

    @Transient
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static java.util.Date convertDate(String data) {
		java.util.Date result = null;
		try {
			result = sFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			result = new java.util.Date(0);
		}
		return result;
	}
	@Transient
	private static SimpleDateFormat sFormatto = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static String convertDateToString(java.util.Date data) {
		String result = null;
		
			try {
				result = sFormatto.format(data);
			} catch (Exception e) {
				e.printStackTrace();
				result = null;
			}
		
		return result;
	}
    
    
    public TeamSchedule()
    {
    }

    public TeamSchedule(String eventId, String eventName, Date eventStartDate,
            Date eventEndDate)
    {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
    }

    public TeamSchedule(String eventId, TeamBean team,
            String eventName, Date eventStartDate, Date eventEndDate,
            String eventDesp, String location, Date reminder, String eventAllday, String eventColor, String teamScheduleUserID)
    {
        this.eventId = eventId;
        this.team = team;
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventDesp = eventDesp;
        this.location = location;
        this.reminder = reminder;
        this.eventAllday = eventAllday;
        this.eventColor = eventColor;
        this.teamScheduleUserID = teamScheduleUserID;
    }

    public String getEventId()
    {
        return this.eventId;
    }

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

    public TeamBean getTeam()
    {
        return this.team;
    }

    public void setTeam(TeamBean team)
    {
        this.team = team;
    }

    public String getEventName()
    {
        return this.eventName;
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public Date getEventStartDate()
    {
        return this.eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate)
    {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate()
    {
        return this.eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate)
    {
        this.eventEndDate = eventEndDate;
    }

    public String getEventDesp()
    {
        return this.eventDesp;
    }

    public void setEventDesp(String eventDesp)
    {
        this.eventDesp = eventDesp;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Date getReminder()
    {
        return this.reminder;
    }

    public void setReminder(Date reminder)
    {
        this.reminder = reminder;
    }

	public String getEventAllday() {
		return eventAllday;
	}

	public void setEventAllday(String eventAllday) {
		this.eventAllday = eventAllday;
	}

	public String getEventColor() {
		return eventColor;
	}

	public void setEventColor(String eventColor) {
		this.eventColor = eventColor;
	}

	public String getTeamScheduleUserID() {
		return teamScheduleUserID;
	}

	public void setTeamScheduleUserID(String teamScheduleUserID) {
		this.teamScheduleUserID = teamScheduleUserID;
	}


	@Override
	public String toString() {
		return "TeamSchedule [eventId=" + eventId + ", team=" + team + ", eventName=" + eventName + ", eventStartDate="
				+ eventStartDate + ", eventEndDate=" + eventEndDate + ", eventDesp=" + eventDesp + ", location="
				+ location + ", reminder=" + reminder + ", eventAllday=" + eventAllday + ", eventColor=" + eventColor
				+ ", teamScheduleUserID=" + teamScheduleUserID + "]";
	}
	
}
