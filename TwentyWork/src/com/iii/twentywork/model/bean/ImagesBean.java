package com.iii.twentywork.model.bean;

import java.util.Arrays;

public class ImagesBean {
	private int imageID;
	private byte[] msgImage;
	
	@Override
	public String toString() {
		return "ImagesBean [imageID=" + imageID + ", msgImage="
				+ Arrays.toString(msgImage) + "]";
	}
	
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public byte[] getMsgImage() {
		return msgImage;
	}
	public void setMsgImage(byte[] msgImage) {
		this.msgImage = msgImage;
	}
	
}
