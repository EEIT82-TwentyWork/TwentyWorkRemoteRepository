package com.iii.twentywork.model.bean.users;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table(name="USERS")
@Component(value="UsersBean")
public class UsersBean {
	@Id
	private int userID;
	private String userName;
	private String email;
	private byte[] password;
	private java.util.Date birth;
	private byte[] userImage;
	private String cellPhone;
	private String phone;
	
	@Override
	public String toString() {
		return "UsersBean [userID=" + userID + ", userName=" + userName
				+ ", email=" + email + ", password="
				+ Arrays.toString(password) + ", birth=" + birth
				+ ", cellPhone=" + cellPhone + ", phone=" + phone + "]";
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
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

}
