package com.iii.twentywork.model.bean;

public class SubBean {
	private int subID;
	private String subText;
	
	@Override
	public String toString() {
		return "SubBean [subID=" + subID + ", subText=" + subText + "]";
	}
	
	public int getSubID() {
		return subID;
	}
	public void setSubID(int subID) {
		this.subID = subID;
	}
	public String getSubText() {
		return subText;
	}
	public void setSubText(String subText) {
		this.subText = subText;
	}

}
