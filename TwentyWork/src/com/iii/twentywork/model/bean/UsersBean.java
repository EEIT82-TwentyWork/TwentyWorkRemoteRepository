package com.iii.twentywork.model.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "USERS")
@Component("userBean")
public class UsersBean implements Serializable {
	public UsersBean() {
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "Userid", unique = true)
	private String userID;
	private String userName;
	private String email;
	private byte[] password;
	private java.util.Date birth;
	private byte[] userImage;
	private String cellPhone;
	private String phone;
	private Set<TeamBean> teams;
	private Set<MySchedule> mySchedules;
	/**
	 * 1.userID="TeamUser.userID"(target) 2.userID="UserBean" (Own)
	 * 3.teamId="TeamUser.teamId"(match-target)
	 * 4.teamId="TeamBean.teamId"(match)
	 */
	/*
	 * @OneToMany(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "TeamUser", joinColumns = {
	 * 
	 * @JoinColumn(name = "userID", referencedColumnName = "userID") },
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "teamId", referencedColumnName = "teamId") }) private
	 * List<TeamBean> TeamBeanList;
	 * 
	 * public List<TeamBean> getTeamBeanList() { return TeamBeanList; }
	 * 
	 * public void setTeamBeanList(List<TeamBean> teamBeanList) { TeamBeanList =
	 * teamBeanList; }
	 */
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public java.util.Date getBirth() {
		return birth;
	}

	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<TeamBean> getTeams() {
		return teams;
	}

	public void setTeams(Set<TeamBean> teams) {
		this.teams = teams;
	}
	
	public Set<MySchedule> getMySchedules() {
		return mySchedules;
	}

	public void setMySchedules(Set<MySchedule> mySchedules) {
		this.mySchedules = mySchedules;
	}

	@Override
	public String toString() {
		return "UsersBean [userID=" + userID + ", userName=" + userName + ", email=" + email + ", password="
				+ Arrays.toString(password) + ", birth=" + birth + ", userImage=" + Arrays.toString(userImage)
				+ ", cellPhone=" + cellPhone + ", phone=" + phone + "]";
	}

	public static UUID convertUUID(String data) {
		UUID result = null;
		try {
			result = UUID.fromString(data);
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	public UsersBean(String[] array) {
		this.userID = array[0];
		this.userName = array[1];
		this.email = array[2];
		this.password = UsersBean.convertByteArray(array[3]);
		this.birth = UsersBean.convertDate(array[4]);
		this.userImage = UsersBean.convertByteArray(array[5]);
		this.cellPhone = array[6];
		this.phone = array[7];
	}

	@Transient
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

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

	public static int convertInt(String data) {
		int result = 0;
		try {
			result = Integer.parseInt(data);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result = -1000;
		}
		return result;
	}

	public static byte[] convertByteArray(String data) {
		byte[] result = null;
		try {
			result = data.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}

}
