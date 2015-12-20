package com.iii.twentywork.model.bean;

public class ClassBean {
	private int classID;
	private String classText;
	
	@Override
	public String toString() {
		return "ClassBean [classID=" + classID + ", classText=" + classText
				+ "]";
	}
	
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public String getClassText() {
		return classText;
	}
	public void setClassText(String classText) {
		this.classText = classText;
	}
	
}
