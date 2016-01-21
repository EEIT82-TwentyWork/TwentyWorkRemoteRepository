package com.iii.twentywork.model.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.dao.DAOinterface.ShareFileDAO;

@Component(value = "shareFileDAO")
public class ShareFileDAOHibernate implements ShareFileDAO
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
//        System.out.println("ShareFileDAOHibernate setSessionFactory");
    }
    
    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
//        System.out.println("ShareFileDAOHibernate getSession");
        return session;
    }
    public ShareFileDAOHibernate() {}
    
    //selectByFileId = "select * from ShareFile where fileId=?";
    @Override
    public ShareFileBean selectByFileId(int fileId)
    {//testing#1
        ShareFileBean bean = (ShareFileBean) getSession().get(ShareFileBean.class, fileId);
//        System.out.println("ShareFileDAOHibernate  select結束");
        return bean;
    }
    
    // INSERT = "insert into ShareFile values(?,?,?,?,?,?,?)";
    @Override
    public  ShareFileBean insert(ShareFileBean bean)
    {//testing#2
        int pk = (int) getSession().save(bean);
//        System.out.println("ShareFileDAOHibernate--insert--pk="+pk);
        return selectByFileId(pk);//;
    }
   
    //select file list :"select * from ShareFile where upperFolderId=?"
    @Override
    public Set<ShareFileBean> getFileList(int upperFolderId)
    {//testing#3
//    	System.out.println("ShareFileDAOHibernate getFileList");
        ShareFileBean bean = selectByFileId(upperFolderId);
        return bean.getInnerFiles();
    }
    
    //create procedure gen_folder_tree ( @v_teamId  varchar(32) )
    private static final String FOLDER_TREE = "{ call gen_folder_tree (?) }";
    @Override
    public List<FileTreeBean> getGroupFolderTree(String teamId)
    {//testing#4
//    	System.out.println("ShareFileDAOHibernate -- getGroupFolderTree ");
        SQLQuery query = getSession().createSQLQuery(FOLDER_TREE);
        query.setParameter(0, teamId);
        query.addEntity(FileTreeBean.class);
//        System.out.println(query.list());
        return query.list();
    }

    private static final String DELETE = "delete from ShareFile where fileId = ?";
    private static final String DELETE_FOLDER = "{call find_delete_files(?)}";
    /**
     * 回傳fileId或是folderId
     */
    @Override
    public int deleteFile(int fileId, boolean isFolder)
    {//testing#6
        if (!isFolder)
        {//刪除(Delete):delete(bean)
            ShareFileBean bean = (ShareFileBean) getSession().get(ShareFileBean.class, fileId);
            getSession().delete(bean);
            return fileId;
        } else
        {// create procedure find_delete_files ( @v_fileId int)
        	SQLQuery query = getSession().createSQLQuery(DELETE_FOLDER);
        	query.setParameter(0, fileId);
        	if(query.executeUpdate()>0){
        		return fileId;
        	}
        }
        return -1;
    }

    @Override
    public ShareFileBean updateFileName(int fileId,String newFileName)
    {
    	ShareFileBean newFolderBean = (ShareFileBean) getSession().get(ShareFileBean.class,fileId);
    	newFolderBean.setFileName(newFileName);
        return newFolderBean;
    }
    
    @Override
    public ShareFileBean updateFileUpperFolder(int fileId,int newUpperFolderId)
    {
    	ShareFileBean newFolderBean = (ShareFileBean) getSession().get(ShareFileBean.class,fileId);
    	ShareFileBean upperFolderBean = selectByFileId(newUpperFolderId);
    	newFolderBean.setUpperFolder(upperFolderBean);
        return newFolderBean;
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
//        ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//        TeamUserDAO teamUserDAO=(TeamUserDAO) context.getBean("teamUserDAO");
//        ShareFileBean bean = new ShareFileBean();
//        //100,200,"New Text Document - Copy - Copy - Copy.txt",904
//        String filePath = "New Text Document - Copy - Copy - Copy.txt";
//        TeamUserBean teamUserBean = teamUserDAO.select(new TeamUserIdBean(100,200)) ;
//        ShareFileBean shareFileBean = dao.selectByFileId(904);
//        bean.setTeamBean(teamUserBean.getTeam());;
//        bean.setUserBean(teamUserBean.getUsers());
//        bean.insertSetFileName(filePath);
//        bean.insertSetFileSize(filePath);
//        bean.insertSetFileType(filePath);
//        bean.insertSetUpdateTime();
//        bean.setUpperFolder(shareFileBean);;
//        System.out.println("bean:" +bean);
//        ShareFileBean result =  dao.insert(bean);
//        System.out.println("resutl: " +result);
        
        
//        ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//        Set<ShareFileBean> list = dao.getFileList(900);
//        System.out.println(list);
//        System.out.println(dao.selectByFileId(902));
//        System.out.println(list.contains(dao.selectByFileId(902)));
        
      //testing#7 ShareFileBean updateFile(ShareFileBean bean, int newFolderId,String newFileName)
//        System.out.println("testing#7");
//      ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//      ShareFileBean changedBean = dao.selectByFileId(910);
//      dao.updateFile(changedBean, 909, changedBean.getFileName());
//      System.out.println(changedBean);
//      ShareFileBean changedBean2 = dao.selectByFileId(911);
//      dao.updateFile(changedBean2, changedBean2.getUpperFolder().getFileId(), "喵的哩");
//      System.out.println(changedBean2);
//      ShareFileBean changedBean3 = dao.selectByFileId(908);
//      dao.updateFile(changedBean3, 907, "!!!!!");
//      System.out.println(changedBean3);
        
        
      //testing#1
//        System.out.println("testing#1");
//      ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//      for(int i=900;i<=915;i++) {
//          ShareFileBean bean = dao.selectByFileId(i);
//          System.out.println(bean);
//      }
      
      
     
      //testing#2  ShareFileBean insert(ShareFileBean bean)
//        System.out.println("testing#2");
//        ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//        int upperFolderId = 905;
//        ShareFileBean upperFolder = dao.selectByFileId(upperFolderId);
//        
//        String path = "C:\\測試test\\貓cat6.jpg";
//        ShareFileBean newbean = new ShareFileBean(105, 200, path);
//        newbean.setUpperFolder(upperFolder);
//        System.out.println(newbean);
//        System.out.println(dao.insert(newbean));

        
      //testing#3  List<ShareFileBean> getFileList(int upperFolderId)
//        System.out.println("testing#3");
//      ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//      int upperFolderId=901;
//      List<ShareFileBean> beans = dao.getFileList(upperFolderId);
//      for(Iterator<ShareFileBean> item = beans.iterator();item.hasNext(); ) {
//          System.out.println(item.next());
//      }
        
      //testing#4 List<FileTreeBean> getGroupFolderTree(int teamId)
//        System.out.println("testing#4");
//        ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//        String teamId = "F617582D839E4CC2B0B50DB977DEFF59";
//        List<FileTreeBean> fileList =  dao.getGroupFolderTree(teamId);
//        for(Iterator<FileTreeBean> item = fileList.iterator();item.hasNext(); ) {
//          System.out.println(item.next());
//        }
        
        
        //testing#5 List<FileTreeBean> findFileByFileName(String queryString,int upperFolderId)
//        System.out.println("testing#5");
//        ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//        List<FileTreeBean> list = dao.findFileByFileName("g", 901);
//        for(Iterator<FileTreeBean> item = list.iterator();item.hasNext();){
//        	System.out.println(item.next());
//        }
        
      //testing#6 int deleteFile(int fileId, boolean isFolder)
//        System.out.println("testing#6");
//      ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//      
//      System.out.println(dao.deleteFile(903, true));
//      System.out.println(dao.deleteFile(916, false));
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    private static final String SELECT_BY_TEAMID = "select * from ShareFile where teamId=? and upperFolderId=900";
    @Override
    public ShareFileBean getGroupRootBean(int teamId) {
        SQLQuery query = getSession().createSQLQuery(SELECT_BY_TEAMID);
        query.setParameter(0, teamId);
        query.addEntity(ShareFileBean.class);
        return (ShareFileBean) query.list();
    }
    
    
    //create procedure find_file_by_fileName ( @v_fileId  int,@v_queryString nvarchar(50))
//    private static final String FIND = "{call find_file_by_fileName (?,?)}";
//    @Override
//    public List<FileTreeBean> findFileByFileName(String queryString,int upperFolderId)
//    {//testing#5
//    	SQLQuery query = getSession().createSQLQuery(FIND);
//    	query.setParameter(0, upperFolderId);
//    	query.setParameter(1, queryString);
//    	query.addEntity(FileTreeBean.class);
//        return query.list();
//    }
    
    @Override
    public ShareFileBean updateFile(int fileId, int newFolderId,String newFileName)
    {//testing#7
    	ShareFileBean newFolderBean = (ShareFileBean) getSession().get(ShareFileBean.class,fileId);
    	newFolderBean.setUpperFolder(newFolderBean);
    	newFolderBean.setFileName(newFileName);
        return newFolderBean;
    }
}
