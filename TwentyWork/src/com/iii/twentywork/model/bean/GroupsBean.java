package com.iii.twentywork.model.bean;

public class GroupsBean {
	private int groupID;
	private String groupName;
	
	@Override
	public String toString() {
		return "GroupsBean [groupID=" + groupID + ", groupName=" + groupName
				+ "]";
	}
	
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
