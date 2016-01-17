package com.iii.twentywork.model.bean;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;


public class ShareFileBean implements Serializable
{
	private static final long serialVersionUID = 1L;
    private Integer fileId; //PK
    private String fileName; //not null
    private String fileType; //not null
    private Integer fileSize;
    private Timestamp updateTime;
    private TeamBean teamBean;
    private UsersBean userBean;
    private ShareFileBean upperFolder;
    private Set<ShareFileBean> innerFiles = new HashSet<ShareFileBean>(0);
    
    
    /**
     * 空的建構子，沒有自動設定任何屬性
     */
    public ShareFileBean() {
    }
    
    /**
     * vvvvvvvvv
     * Hibernate版本<br>
     * 新增檔案時使用的建構子<br>
     * 此建構子會將fileName,fileType,fileSize,updateTime,userId,teamId,upperFolder設定好<br>
     * 沒設定的屬性:fileId
     */
//    public ShareFileBean(TeamUserBean teamUser,String fileName,ShareFileBean upperFolder) {
//    	this.setTeamBean(teamUser.getTeam());
//    	this.setUserBean(teamUser.getUsers());
//    	this.setUpperFolder(upperFolder);
//        insertSetFileName(fileName);
//        insertSetFileType(fileName);
//        insertSetFileSize(fileName);
//        insertSetUpdateTime();
//    }
    
    /**
     * 新增folder時使用的建構子<br>
     * 此建構子會將fileName,fileType,userId,teamId,upperFolderId設定好<br>
     * fileSize,updateTime屬性為Null不設定<br>
     * fileId沒設定
     * @param userId
     * @param teamId
     * @param folderName
     * @param upperFolderId
     */
//    public ShareFileBean(TeamUserBean teamUser,String folderName,ShareFileBean upperFolder,boolean isFolder) {
//	    if(isFolder){
//	    	this.setTeamBean(teamUser.getTeam());
//	    	this.setUserBean(teamUser.getUsers());
//	    	this.setUpperFolder(upperFolder);
//	    	this.fileName = folderName;
//	        this.fileType = "資料夾";
//	    }else{
//	    	this.setTeamBean(teamUser.getTeam());
//	    	this.setUserBean(teamUser.getUsers());
//	    	this.setUpperFolder(upperFolder);
//	        insertSetFileName(folderName);
//	        insertSetFileType(folderName);
//	        insertSetFileSize(folderName);
//	        insertSetUpdateTime();
//	    }
//    }

    /**
     * vvvvvvvvv
     * 從filePath擷取檔案名稱(包含副檔名)，設定為fileName
     * @param filePath
     */
    public void insertSetFileName(String filePath)
    {
        int begin = new String(filePath).lastIndexOf("\\");
        this.fileName = filePath.substring(begin+1);
    }

    /**
     * vvvvvvvvv
     * 從filePath擷取副檔名，改成全大寫，設定為fileType
     * @param filePath
     */
    public void insertSetFileType(String filePath)
    {
        int end = new String(filePath).lastIndexOf(".");
        this.fileType =new String(filePath.substring(end+1)).toLowerCase();
    }

    
    /**
     * vvvvvvvvv
     * 從filePath讀取檔案，計算檔案size，單位:byte，設定為fileSize
     * @param filePath
     */
    public void insertSetFileSize(String filePath)
    {
        File file = new File(filePath);        
        this.fileSize = (int) file.length();
    }

    /**
     * vvvvvvvvv
     * 取得當下系統時間，設定為updateTime
     */
    public void insertSetUpdateTime()
    {
        java.util.Date date= new java.util.Date();
        this.updateTime =new Timestamp(date.getTime());
    }


     
    
    
    public Integer getFileId() { return fileId; }
    public void setFileId(Integer fileId) { this.fileId = fileId; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    
    public Integer getFileSize() { return fileSize; }
    public void setFileSize(Integer fileSize) { this.fileSize = fileSize; }
    
    public Timestamp getUpdateTime() { return updateTime; }
    public void setUpdateTime(Timestamp updateTime) { this.updateTime = updateTime; }
    
    @Autowired
    public TeamBean getTeamBean() { return teamBean; }
	public void setTeamBean(TeamBean team) { this.teamBean = team;}

	@Autowired
	public UsersBean getUserBean() { return userBean;}
	public void setUserBean(UsersBean user) { this.userBean = user;	}

	public ShareFileBean getUpperFolder() { return this.upperFolder; }
    public void setUpperFolder(ShareFileBean upperFolder) { this.upperFolder = upperFolder; }
    
    public Set<ShareFileBean> getInnerFiles() { return this.innerFiles; }
    public void setInnerFiles(Set<ShareFileBean> innerFiles) { this.innerFiles = innerFiles; }
    
    
    

    @Override
    public String toString()
    {
        return "ShareFileBean [fileId=" + fileId + ", fileName=" + fileName
                + ", fileType=" + fileType + ", fileSize=" + fileSize
                + ", updateTime=" + updateTime ;
//                + ", userId=" + userBean.getUserID()
//                + ", teamId=" + teamBean.getTeamId(); 
    }


    public static void main(String[] args)
    {
        
        /*ShareFileBean file = new ShareFileBean();
        String filePath = "D:\\測試test\\貓cat4.jpg";
        file.insertSetFileName(filePath);
        boolean testing1 = "貓cat4".equals(file.getFileName());
        System.out.println("testing1:  "+testing1);
        
        file.insertSetFileType(filePath);
        boolean testing2 = "JPG".equals(file.getFileType());
        System.out.println("testing2:  "+testing2);
        
        file.insertSetFileSize(filePath);
        System.out.println("testing3:  "+file.getFileSize());
        
        file.insertSetUpdateTime();
        System.out.println("testing4:  "+file.getUpdateTime());*/
        
//        String filePath = "D:\\測試test\\貓cat4.jpg";
//        ShareFileBean file = new ShareFileBean(1, 1, filePath);
//        boolean testing1 = "貓cat4".equals(file.getFileName());
//        System.out.println("testing1:  "+testing1);
//        boolean testing2 = "JPG".equals(file.getFileType());
//        System.out.println("testing2:  "+testing2);
//        System.out.println("testing3:  "+file.getFileSize());
//        System.out.println("testing4:  "+file.getUpdateTime());
//        System.out.println(file.getUpperFolderId());
        
        
        System.out.println("ShareFileBean main()");
    }


}
