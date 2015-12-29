package com.iii.twentywork.model.bean;

public class MyScheduleBean {
	private int myEventID;
	private String myEvent;
	private java.util.Date myEventStartDate;
	private java.util.Date myEventEndDate;
	private String myEventDesp;
	private String myLocation;
	private java.util.Date myRminder;
	
	@Override
	public String toString() {
		return "MySchedule [myEventID=" + myEventID + ", myEvent=" + myEvent
				+ ", myEventStartDate=" + myEventStartDate
				+ ", myEventEndDate=" + myEventEndDate + ", myEventDesp="
				+ myEventDesp + ", myLocation=" + myLocation + ", myRminder="
				+ myRminder + "]";
	}
	
	public int getMyEventID() {
		return myEventID;
	}
	public void setMyEventID(int myEventID) {
		this.myEventID = myEventID;
	}
	public String getMyEvent() {
		return myEvent;
	}
	public void setMyEvent(String myEvent) {
		this.myEvent = myEvent;
	}
	public java.util.Date getMyEventStartDate() {
		return myEventStartDate;
	}
	public void setMyEventStartDate(java.util.Date myEventStartDate) {
		this.myEventStartDate = myEventStartDate;
	}
	public java.util.Date getMyEventEndDate() {
		return myEventEndDate;
	}
	public void setMyEventEndDate(java.util.Date myEventEndDate) {
		this.myEventEndDate = myEventEndDate;
	}
	public String getMyEventDesp() {
		return myEventDesp;
	}
	public void setMyEventDesp(String myEventDesp) {
		this.myEventDesp = myEventDesp;
	}
	public String getMyLocation() {
		return myLocation;
	}
	public void setMyLocation(String myLocation) {
		this.myLocation = myLocation;
	}
	public java.util.Date getMyRminder() {
		return myRminder;
	}
	public void setMyRminder(java.util.Date myRminder) {
		this.myRminder = myRminder;
	}
	
}
