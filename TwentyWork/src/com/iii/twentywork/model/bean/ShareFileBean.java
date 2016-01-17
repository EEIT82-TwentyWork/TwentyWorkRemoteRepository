package com.iii.twentywork.model.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ShareFileBean implements java.io.Serializable
{

    private String fileId;
    private String fileName;
    private String fileType;
    private Integer fileSize;
    private Timestamp updateTime;
    private TeamBean team;
    private UsersBean users;
    private ShareFileBean upperFolder;
    private Set<ShareFileBean> innerFiles = new HashSet<ShareFileBean>(0);


    @Override
	public String toString() {
		return "ShareFile [fileId=" + fileId + ", fileName=" + fileName + ", fileType=" + fileType + ", fileSize="
				+ fileSize + ", updateTime=" + updateTime + ", team=" + team + ", users=" + users + ", upperFolder="
				+ upperFolder + ", innerFiles=" + innerFiles + "]";
	}



	/**
     * 空的建構子，沒有自動設定任何屬性
     */
    public ShareFileBean()
    {
    }

    

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public TeamBean getTeam() {
		return team;
	}

	public void setTeam(TeamBean team) {
		this.team = team;
	}

	public UsersBean getUsers() {
		return users;
	}

	public void setUsers(UsersBean users) {
		this.users = users;
	}

	public ShareFileBean getUpperFolder() {
		return upperFolder;
	}

	public void setUpperFolder(ShareFileBean upperFolder) {
		this.upperFolder = upperFolder;
	}

	public Set<ShareFileBean> getInnerFiles() {
		return innerFiles;
	}

	public void setInnerFiles(Set<ShareFileBean> innerFiles) {
		this.innerFiles = innerFiles;
	}


}
