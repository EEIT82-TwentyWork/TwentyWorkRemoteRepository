package com.iii.twentywork.model.bean;

import java.util.Arrays;

public class MsgBean {
	private int msgID;
	private java.util.Date msgTime;
	private String msgText;
	private byte[] msgFile;
	private String msgFileName;
	private String msgFileType;
	private int msgFIleSize;
	
	@Override
	public String toString() {
		return "MsgBean [msgID=" + msgID + ", msgTime=" + msgTime
				+ ", msgText=" + msgText + ", msgFile="
				+ Arrays.toString(msgFile) + ", msgFileName=" + msgFileName
				+ ", msgFileType=" + msgFileType + ", msgFIleSize="
				+ msgFIleSize + "]";
	}
	
	public int getMsgID() {
		return msgID;
	}
	public void setMsgID(int msgID) {
		this.msgID = msgID;
	}
	public java.util.Date getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(java.util.Date msgTime) {
		this.msgTime = msgTime;
	}
	public String getMsgText() {
		return msgText;
	}
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	public byte[] getMsgFile() {
		return msgFile;
	}
	public void setMsgFile(byte[] msgFile) {
		this.msgFile = msgFile;
	}
	public String getMsgFileName() {
		return msgFileName;
	}
	public void setMsgFileName(String msgFileName) {
		this.msgFileName = msgFileName;
	}
	public String getMsgFileType() {
		return msgFileType;
	}
	public void setMsgFileType(String msgFileType) {
		this.msgFileType = msgFileType;
	}
	public int getMsgFIleSize() {
		return msgFIleSize;
	}
	public void setMsgFIleSize(int msgFIleSize) {
		this.msgFIleSize = msgFIleSize;
	}
	
}
