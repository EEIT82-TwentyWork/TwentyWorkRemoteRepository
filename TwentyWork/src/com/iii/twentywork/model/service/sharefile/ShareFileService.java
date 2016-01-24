package com.iii.twentywork.model.service.sharefile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.CheckPathInfoBean;
import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.Notify;
import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.NotifyDAOHibernate;
import com.iii.twentywork.model.dao.TeamDAOHibernate;
import com.iii.twentywork.model.dao.DAOinterface.ShareFileDAO;
import com.sun.org.apache.bcel.internal.generic.IXOR;

@Component(value = "shareFileService")
public class ShareFileService
{
    @Autowired
    private ShareFileDAO shareFileDAO ;
    public void setShareFileDAO(ShareFileDAO shareFileDAO)
    {
        this.shareFileDAO = shareFileDAO;
    }
    
    @Autowired
    private TeamDAOHibernate teamFileDAO;
    public void setTeamFileDAO(TeamDAOHibernate teamFileDAO) {
		this.teamFileDAO = teamFileDAO;
	}
    @Autowired
    private NotifyDAOHibernate notifyDAO;
	public void setNotifyDAO(NotifyDAOHibernate notifyDAO) {
		this.notifyDAO = notifyDAO;
	}
	
	//testing#2
    public CheckPathInfoBean checkPathInfo(String pathInfo, TeamBean team,UsersBean user)  {
        String teamId = team.getTeamId();
        List<FileTreeBean> folderTree = getGroupFolderTree(teamId);
        
        List<FileTreeBean> folders = new ArrayList<FileTreeBean>();
        folders.add(folderTree.get(0));//group root folder
//        System.out.println("ShareFileService--CheckPathInfoBean--"+folderTree);
        if(pathInfo==null) { //測過web
//            System.out.println("pathInfo==null");
            return new CheckPathInfoBean(true,folders,folderTree);               
        }
        String[] pathName = pathInfo.split("/");//取得各層folder名稱
        int folderTreeSize = folderTree.size();
        int folderTreeMaxLevel = folderTree.get(folderTreeSize-1).getFileLevel();
//        System.out.println("folderTree:");
//        for(Object i:folderTree){
//        	System.out.println(i);
//        }
        if(pathName.length==0) 
        {//pathInfo="/"  pathName=[]
//            System.out.println("不用比對path1:pathName.length==0"
//                                +"  pathLevel= "+ (pathName.length-1)
//                                +"  folderTreeMaxLevel= "+folderTreeMaxLevel);
            return new CheckPathInfoBean(true,folders,folderTree);
        }else if(folderTreeMaxLevel<(pathName.length-1)) 
        {//pathInfo階層高於 folderTree最高階層，path錯誤
//            System.out.println("不用比對path2:folderTreeMaxLevel<pathName.length"
//                    +"  pathLevel= "+ (pathName.length-1)
//                    +"  folderTreeMaxLevel= "+folderTreeMaxLevel);
            return new CheckPathInfoBean(false,folders,folderTree);
        }else
        {//比對各階層目錄名稱
            int folderTreeIndex = 1;// folderTreeIndex=0:folder根目錄
            FileTreeBean compared = folderTree.get(folderTreeIndex);
            for (int pathLevel = 1; pathLevel < pathName.length; pathLevel++)
            {
//                System.out.println("pathLevel= " + pathLevel);
                String pathElement = pathName[pathLevel];
//                System.out.println("+++pathElement="+pathElement);
                int comparedLevel;
                boolean isConformity=false;
                do
                {// 比對folderName
//                	System.out.println("+++compared.getFileName()="+compared.getFileName());
                    if(pathElement.equals(compared.getFileName())) {
                       isConformity = true;
                       folders.add(compared);
                    }
                    folderTreeIndex++;
                    if(folderTree.size()!=folderTreeIndex){
                    compared = folderTree.get(folderTreeIndex);
                    comparedLevel = compared.getFileLevel();
                    }else{
                    	break;
                    }
                } while (comparedLevel == pathLevel && folderTreeIndex <= folderTreeSize-1);
                if(!isConformity) {
//                    System.out.println("找不到符合的Folder");
                    return new CheckPathInfoBean(false,folders,folderTree);
                }
            }
//            System.out.println("final return all folders");
            return new CheckPathInfoBean(true,folders,folderTree);
        }
    }
  //testing#1
    public List<FileTreeBean> getGroupFolderTree(String teamId)
    {
        return shareFileDAO.getGroupFolderTree(teamId);
    }
  //testing#3 
    public List<ShareFileBean> getSortedFileList(int upperFolderId)
    {
        Set<ShareFileBean> set = shareFileDAO.getFileList(upperFolderId);
        List<ShareFileBean> list = new ArrayList<ShareFileBean>(set);
        Collections.sort (list , new Comparator< ShareFileBean >(){
            public int compare( ShareFileBean o1, ShareFileBean o2 ) {
                int compareByFileType =- o1.getFileType().compareTo(o2.getFileType());
                if(compareByFileType==0) {
                    int compareByFileName = o1.getFileName().compareTo(o2.getFileName());
                    return compareByFileName;
                }else {
                    return compareByFileType;
                }
          }
        });
        return list;
    }
  //Web testing pass
    public ShareFileBean insertFile(TeamBean team,UsersBean user,String fileName,int upperFolderId){
    	ShareFileBean upperFolder = shareFileDAO.selectByFileId(upperFolderId);
		ShareFileBean bean = new ShareFileBean(team,user,fileName,upperFolder);
		return shareFileDAO.insert(bean);
	}
  //Child of testing#4 
    public int deleteFile(int fileId, boolean isFolder){
    	return shareFileDAO.deleteFile(fileId,isFolder);
    }
  //testing#4   
    public List<Map<String, String>> deleteFileFunction(List<String> fileIdList) {
        int fileId=-1;
        Boolean isFolder=null;
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        for (String e : fileIdList)
        {
//            System.out.println(e);
            if (e.startsWith("file"))
            {
                fileId = Integer.parseInt(e.substring(4));
                isFolder = false;
            } else if (e.startsWith("folder"))
            {
                fileId = Integer.parseInt(e.substring(6));
                isFolder = true;
            } else
            {
                System.out.println("wrong entiry!!!!");
            }
            int resultId = shareFileDAO.deleteFile(fileId,isFolder);
//            System.out.println(fileId+","+isFolder+","+resultId);
            if(resultId == fileId) {
                Map<String, String> m1 = new HashMap<String, String>();       
                m1.put("fileID",e);   
                result.add(m1);
            }else {
                System.out.println("delete fail!!!");
            }
        }
        return result;
    }
   
  //Web testing pass
	public ShareFileBean insertFolder(TeamBean team,UsersBean user, String folderName, int upperFolderId) {
		ShareFileBean upperFolder = shareFileDAO.selectByFileId(upperFolderId);
		ShareFileBean bean = new ShareFileBean(team,user,folderName,upperFolder,true);
		return shareFileDAO.insert(bean);
	}
  //Web testing pass   
	public String beanConvert2JSON(ShareFileBean bean,boolean isFolder){
		List<Map<String, String>>  list = new ArrayList<Map<String, String>>();
  		 Map<String, String> m1 = new HashMap<String, String>();  
  		 
  		 m1.put("fileId",bean.getFileId().toString());   
  		 m1.put("fileName", bean.getFileName()); 
  		 m1.put("fileType",bean.getFileType()); 
  		 
  		 if(bean.getUpdateTime()==null){
  			m1.put("updateTime", "-");
  		 }else{
  			m1.put("updateTime", bean.getUpdateTime().toString());
  		 }
  		 String userId = bean.getUserBean().getUserID();
  		 String teamId = bean.getTeamBean().getTeamId();
   		 m1.put("userId",userId.toString() ); 
   		 m1.put("userName",bean.getUserBean().getUserName() ); 
 		 m1.put("teamId",teamId.toString()); 
 		 m1.put("teamName",bean.getTeamBean().getTeamName()); 
  		 
  		list.add(m1);
  	 
   	String jsonString = JSONValue.toJSONString(list); 
   	return jsonString;
	}
	//Web testing pass  
	public ShareFileBean selectByFileId(int fileId){
		return shareFileDAO.selectByFileId(fileId);
	}
	
	//Web testing pass 
	public ShareFileBean renameFile(int fileId,String newFileName){
		return shareFileDAO.updateFileName(fileId,newFileName);
	}
	
	
	/**
	 * getGroupFolder的list轉json
	 */
	public String fileTreeConvert2JSON(List<FileTreeBean> inputList)
	{//testing#5
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(int i=0;i<inputList.size();i++){//
			
			FileTreeBean bean = inputList.get(i);
			Map<String, String> e = new HashMap<String, String>();
			e.put("fileId", Integer.toString(bean.getFileId()));
			e.put("fileName", bean.getFileName()); 
			e.put("fileLevel", Integer.toString(bean.getFileLevel()));
			e.put("upperFolderId", Integer.toString(bean.getUpperFolderId()));
			list.add(e);
		}
		String jsonString = JSONValue.toJSONString(list); 
		return jsonString;
 	}
	
	/**
	 * 把"file1594"或"folder9876"截字轉回數字1594、9876
	 */
	public int fileIdConver2Int(String idString){
		if (idString.startsWith("file"))
        {
            return Integer.parseInt(idString.substring(4));
        } else if (idString.startsWith("folder"))
        {
        	return Integer.parseInt(idString.substring(6));
        }else{
        	return -1;
        }
	}
	public List<Map<String, String>> moveFileFunction(List<String> listFileId,int newUpperFolderId){
		List<Map<String, String>> listResult = new ArrayList<Map<String,String>>();
		for(int i =0;i<listFileId.size();i++){
			String fileID = listFileId.get(i);
			int fileId = fileIdConver2Int(fileID);
			if(fileId!=newUpperFolderId){
				int feedBack = shareFileDAO.updateFileUpperFolder(fileId,newUpperFolderId).getFileId();
				if(fileId==feedBack){
					Map<String, String> e = new HashMap<String, String>();
					e.put("fileId", fileID);
					listResult.add(e);
				}
			}
		}
		
		return listResult;
	}
	
	
	//Web testing pass
	public ShareFileBean insertGroupRootFolder(TeamBean team) {
			ShareFileBean upperFolder = shareFileDAO.selectByFileId(900);
			
			ShareFileBean bean = new ShareFileBean();
			bean.setTeamBean(team);
			bean.setUpperFolder(upperFolder);
			bean.setFileName(team.getTeamName()+"根目錄");
			bean.setFileType("資料夾");
			return shareFileDAO.insert(bean);
		}
		
	//testing#6
	public String getTeamMember(String teamId) {
		TeamBean teamBean = teamFileDAO.selectByTeamId(teamId);
		List<UsersBean> list = new ArrayList(teamBean.getUserses());
		List<Map<String, String>> memberList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < list.size(); i++) {
			UsersBean bean = list.get(i);
			Map<String, String> map = new HashMap<String, String>();
			map.put("userID", bean.getUserID());
			map.put("userName", bean.getUserName());
			memberList.add(map);
		}
		String jsonString = JSONValue.toJSONString(memberList);
		return jsonString;
	}
	
	
	
	public void insertNotify(List<String> usersId,UsersBean sendPerson,TeamBean team,int fileId){
		for(int i=0;i<usersId.size();i++){
		Notify bean = new Notify();
		bean.setTeam(team);
		bean.setSendUser(sendPerson);
		bean.setFile(selectByFileId(fileId));
		bean.setShareTime(new Date());
		bean.setReadState("no");
//			System.out.println("++++i="+i);
			bean.setUsers(notifyDAO.selectByUserId(usersId.get(i)));
			String pk = notifyDAO.insert(bean);
//			System.out.println(pk);
		}
	}
	
	public List<Notify> getShareFileRecord(String pk){
		List<Notify> list =notifyDAO.selectNotifyByUserId(pk);
		Collections.sort (list , new Comparator< Notify >(){
            public int compare( Notify o1, Notify o2 ) {
                int compareByFileType =- o1.getShareTime().compareTo(o2.getShareTime());
                    return compareByFileType;
          }
        });
		return list;
	}
	
	public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
       
      //testing#6
        ShareFileService service = (ShareFileService) context.getBean("shareFileService");
        System.out.println(service.getTeamMember("8a808084526c94bc01526c97eb1a0001"));
        
        
      //testing#5
//        ShareFileService service = (ShareFileService) context.getBean("shareFileService");
//        System.out.println(service.getGroupFolderTree(201));
//        System.out.println(service.fileTreeConvert2JSON(service.getGroupFolderTree(201)));
//       
      //testing#1
//        System.out.println(service.getGroupFolderTree(201));
//        System.out.println(service.getGroupFolderTree(203));
      //test#2  
//        String pathInfo = "/";
//        TeamUserBean teamUser = new TeamUserBean();
//        TeamBean beanChild = new TeamBean();
//        beanChild.setTeamId(200);
//        teamUser.setTeam(beanChild);
//        CheckPathInfoBean bean = service. checkPathInfo(pathInfo, teamUser); 
//        System.out.println(pathInfo);
//        System.out.println(bean.isPass());
//        for( int i = 0; i < bean.getFolders() .size (); i++ ) {
//            Object o = bean.getFolders() .get (i );
//            System .out . println( "Object = " + o) ;
//        }
      //test#3  
//        List<ShareFileBean> list = service.getSortedFileList(901);
//        for(int i = 0; i<list.size(); i++) {System.out.println(list.get(i));}

      //testing#4
//        ShareFileService service = new ShareFileService();//{"fileID":["file917","file918","file919"]}
//        List<String> fileIdList = new ArrayList<String>();
//        fileIdList.add("folder906");
//        fileIdList.add("file922");
//        fileIdList.add("file930");
//        System.out.println(fileIdList);
//        service.deleteFileFunction(fileIdList);
        
        
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    
    
    public ShareFileBean getGroupRootFolder(int teamId) {
        return shareFileDAO.getGroupRootBean(teamId);
    }
    
    

}
