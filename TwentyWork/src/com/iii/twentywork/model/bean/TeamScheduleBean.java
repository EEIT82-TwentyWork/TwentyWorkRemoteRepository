package com.iii.twentywork.model.bean;

public class TeamScheduleBean {
	private int eventID;
	private String eventName;
	private java.util.Date eventStartDate;
	private java.util.Date eventEndDate;
	private String eventDesp;
	private String location;
	private java.util.Date reminder;
	
	@Override
	public String toString() {
		return "TeamSchedule [eventID=" + eventID + ", eventName=" + eventName
				+ ", eventStartDate=" + eventStartDate + ", eventEndDate="
				+ eventEndDate + ", eventDesp=" + eventDesp + ", location="
				+ location + ", reminder=" + reminder + "]";
	}
	
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public java.util.Date getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(java.util.Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public java.util.Date getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(java.util.Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getEventDesp() {
		return eventDesp;
	}
	public void setEventDesp(String eventDesp) {
		this.eventDesp = eventDesp;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public java.util.Date getReminder() {
		return reminder;
	}
	public void setReminder(java.util.Date reminder) {
		this.reminder = reminder;
	}
	
}
