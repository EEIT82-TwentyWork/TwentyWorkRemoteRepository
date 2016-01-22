package com.iii.twentywork.model.bean;
// Generated 2016/1/10 �U�� 06:05:34 by Hibernate Tools 4.3.1.Final

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name="MySchedule")
@Component("mySchedule")
public class MySchedule implements java.io.Serializable {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "myEventID", unique = true)
    private String id;
	@Column(name = "myEvent")
	private String title;
	@Column(name = "myEventStartDate")
	private java.util.Date start;
	@Column(name = "myEventEndDate")
	private java.util.Date end;
	@Column(name = "myEventDesp")
    private String myEventDesp;
	@Column(name = "myLocation")
    private String myLocation;
	@Column(name = "myRminder")
    private Date myRminder;
	@Column(name = "myEventAllday")
	private String allday;
	@Column(name = "myEventColor")
	private String color;
	@Column(name = "myUserID")
	private String myUserID;

    private Set<UsersBean> userses;

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
    
    
    public MySchedule() {
    	
    }

    public MySchedule(String id, String title, Date start, Date end) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
	}

	public MySchedule(String id, String title, Date start, Date end, String myEventDesp, String myLocation,
			Date myRminder, String allday, String color, UsersBean users, String myUserID, Set<UsersBean> userses) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.myEventDesp = myEventDesp;
		this.myLocation = myLocation;
		this.myRminder = myRminder;
		this.allday = allday;
		this.color = color;
		this.myUserID = myUserID;
		this.userses = userses;
	}

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public java.util.Date getStart() {
		return start;
	}

	public void setStart(java.util.Date start) {
		this.start = start;
	}

	public java.util.Date getEnd() {
		return end;
	}

	public void setEnd(java.util.Date end) {
		this.end = end;
	}

	public String getMyEventDesp() {
        return this.myEventDesp;
    }

    public void setMyEventDesp(String myEventDesp) {
        this.myEventDesp = myEventDesp;
    }

    public String getMyLocation() {
        return this.myLocation;
    }

    public void setMyLocation(String myLocation) {
        this.myLocation = myLocation;
    }

    public Date getMyRminder() {
        return this.myRminder;
    }

    public void setMyRminder(Date myRminder) {
        this.myRminder = myRminder;
    }
    
    public String getAllday() {
		return allday;
	}

	public void setAllday(String allday) {
		this.allday = allday;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

    public String getMyUserID() {
		return myUserID;
	}


	public void setMyUserID(String myUserID) {
		this.myUserID = myUserID;
	}


	public Set<UsersBean> getUserses() {
        return this.userses;
    }

    public void setUserses(Set<UsersBean> userses) {
        this.userses = userses;
    }

	@Override
	public String toString() {
		return "MySchedule [id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", myEventDesp="
				+ myEventDesp + ", myLocation=" + myLocation + ", myRminder=" + myRminder + ", allday=" + allday
				+ ", color=" + color + ", myUserID=" + myUserID + "]";
	}

}
