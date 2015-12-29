package com.iii.twentywork.model.bean;

public class MyFavBean {
	private int MyFavID;

	@Override
	public String toString() {
		return "MyFavBean [MyFavID=" + MyFavID + "]";
	}

	public int getMyFavID() {
		return MyFavID;
	}

	public void setMyFavID(int myFavID) {
		MyFavID = myFavID;
	}
	
}
