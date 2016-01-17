package com.iii.twentywork.model.bean;

import java.sql.Timestamp;

public class FileTreeBean implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;
    //findFile (fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , fileLevel)
    private String fileId; 
    private String fileName; 
    private String fileType; 
    private Integer fileSize;
    private Timestamp updateTime;
    private String userId; 
    private String teamId; 
    private String upperFolderId; 
    private Integer fileLevel;
    
    public String getFileId()
    { return fileId; }
    public void setFileId(String fileId)
    { this.fileId = fileId; }
    
    public String getFileName()
    { return fileName; }
    public void setFileName(String fileName)
    { this.fileName = fileName; }
    
    public String getFileType()
    { return fileType; }
    public void setFileType(String fileType)
    { this.fileType = fileType; }
    
    public Integer getFileSize()
    { return fileSize; }
    public void setFileSize(Integer fileSize)
    { this.fileSize = fileSize; }
    
    public String getTeamId()
    { return teamId; }
    public void setTeamId(String teamId)
    { this.teamId = teamId; }
    
    public String getUpperFolderId()
    { return upperFolderId; }
    public void setUpperFolderId(String upperFolderId)
    { this.upperFolderId = upperFolderId; }
    
    public int getFileLevel()
    { return fileLevel; }
    public void setFileLevel(Integer fileLevel)
    { this.fileLevel = fileLevel; }
    
    public Timestamp getUpdateTime() { return updateTime; }
    public void setUpdateTime(Timestamp updateTime) { this.updateTime = updateTime; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
	@Override
	public String toString() {
		return "FileTreeBean [fileId=" + fileId + ", fileName=" + fileName + ", fileType=" + fileType + ", fileSize="
				+ fileSize + ", updateTime=" + updateTime + ", userId=" + userId + ", teamId=" + teamId
				+ ", upperFolderId=" + upperFolderId + ", fileLevel=" + fileLevel + "]";
	}
    
}
