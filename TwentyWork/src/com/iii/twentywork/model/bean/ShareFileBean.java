package com.iii.twentywork.model.bean;

import java.util.Arrays;

public class ShareFileBean {
	private int fileID;
	private String shareFileName;
	private String shareFileType;
	private int shareFileSize;
	private java.util.Date shareFileUpDateTime;
	private byte[] shareFIle;
	private int upperFolderID;
	
	@Override
	public String toString() {
		return "ShareFileBean [fileID=" + fileID + ", shareFileName="
				+ shareFileName + ", shareFileType=" + shareFileType
				+ ", shareFileSize=" + shareFileSize + ", shareFileUpDateTime="
				+ shareFileUpDateTime + ", shareFIle="
				+ Arrays.toString(shareFIle) + ", upperFolderID="
				+ upperFolderID + "]";
	}
	
	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	public String getShareFileName() {
		return shareFileName;
	}
	public void setShareFileName(String shareFileName) {
		this.shareFileName = shareFileName;
	}
	public String getShareFileType() {
		return shareFileType;
	}
	public void setShareFileType(String shareFileType) {
		this.shareFileType = shareFileType;
	}
	public int getShareFileSize() {
		return shareFileSize;
	}
	public void setShareFileSize(int shareFileSize) {
		this.shareFileSize = shareFileSize;
	}
	public java.util.Date getShareFileUpDateTime() {
		return shareFileUpDateTime;
	}
	public void setShareFileUpDateTime(java.util.Date shareFileUpDateTime) {
		this.shareFileUpDateTime = shareFileUpDateTime;
	}
	public byte[] getShareFIle() {
		return shareFIle;
	}
	public void setShareFIle(byte[] shareFIle) {
		this.shareFIle = shareFIle;
	}
	public int getUpperFolderID() {
		return upperFolderID;
	}
	public void setUpperFolderID(int upperFolderID) {
		this.upperFolderID = upperFolderID;
	}
	
}
