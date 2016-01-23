package com.iii.twentywork.controller.sharefile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.CheckPathInfoBean;
import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.sharefile.ShareFileService;

@WebServlet({"/ShareFile/*","/ShareFileServlet/*"})
public class ShareFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShareFileServlet() {
        super();
    }
    private ShareFileService shareFileService;
    @Override
    public void init() throws ServletException {
        ServletContext application = this.getServletContext();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
        this.shareFileService = (ShareFileService) context.getBean("shareFileService");
    }
    
    private static final String UPLOAD_DIRECTORY="C:\\userFileUpload";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//接收資料
	    HttpSession session = request.getSession();
	    String pathInfo = request.getPathInfo();
	    String servletPath = request.getServletPath();
	    UsersBean usersBean = (UsersBean) session.getAttribute("LoginOK");
	    TeamBean teamBean = (TeamBean) session.getAttribute("teamBean");
	    
	    System.out.println("ShareFileServlet--Line61----usersBean:  "+usersBean);
	    System.out.println("ShareFileServlet--Line61----teamBean:  "+teamBean);
	    System.out.println("ShareFileServlet--Line64++++++++++++++pathInfo: "+pathInfo+"++++++++++++++servletPath: "+servletPath);
//	    System.out.println(teamUser);
	    if(servletPath.equals("/ShareFile"))
        {//取得頁面列表功能
              //驗證資料
                Map<String, String> errors = new HashMap<String, String>();
                request.setAttribute("errors", errors);
                CheckPathInfoBean check = shareFileService.checkPathInfo(pathInfo,teamBean,usersBean);
                boolean isPass = check.isPass();
                if(!isPass) {
                    System.out.println("ShareFileServlet -- Line74--找不到此資料夾");
                    errors.put("pathError", "找不到此資料夾");}
                
                
               //呼叫Model
                int lastFolder = (check.getFolders().size()-1);
                int upperFolderId = check.getFolders().get(lastFolder).getFileId();
                List<ShareFileBean> fileList = shareFileService.getSortedFileList(upperFolderId);
                session.setAttribute("upperFolderId", upperFolderId); //int
                session.setAttribute("fileList", fileList);  //List<ShareFileBean>
                session.setAttribute("folders", check.getFolders());//List<FileTreeBean>
//                session.setAttribute("folderTree", check.getFolderTree());
//                System.out.println("ShareFileServlet--check.isPass()-"+check.isPass());
//                System.out.println("ShareFileServlet--check.getFolderTree()-"+check.getFolderTree());
                //呼叫View
                if(errors!=null && !errors.isEmpty()) {
//                	System.out.println("hello here is ShareFile error ");
                	errors.remove("pathError");
                    String path = request.getContextPath();
                    response.sendRedirect(path+"/ShareFile");
                    return;
                }else {
                    String uri = request.getRequestURI();
//                    System.out.println("ShareFileServlet--Line91--session.setAttribute(\"requestURI\", uri)="+uri);
                    session.setAttribute("requestURI", uri);
                    request.getRequestDispatcher("/shareFile/main.jsp").forward(request, response);
                    return;
                }
            }
	    else if(servletPath.equals("/ShareFileServlet")  && pathInfo.equals("/fileUpload"))
	    {//檔案上傳功能
	    	System.out.println("ShareFileServlet--Line99--servletPath.equals(\"/shareFile/fileUpload\")");
	    	String requestURI =(String) session.getAttribute("requestURI");
//	        List<FileTreeBean> folders = (List<FileTreeBean>) session.getAttribute("folders");  
	    	int upperFolderId = (int) session.getAttribute("upperFolderId");
//	    	System.out.println(upperFolderId);
	    	if (ServletFileUpload.isMultipartContent(request)) {
					try {
						List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
						for (FileItem item : multiparts)
						{
						    if (!item.isFormField())
						    {
						        String fileName = new File(item.getName()).getName();
//						        System.out.println(fileName);
						        ShareFileBean bean = shareFileService.insertFile(teamBean,usersBean, fileName,upperFolderId);
//						        System.out.println(bean);
						        item.write(new File(UPLOAD_DIRECTORY+File.separator+bean.getFileId()+"."+bean.getFileType()));
						    }
						}
						session.setAttribute("message", "File Uploaded Successfully");
					} catch ( Exception e) {
						session.setAttribute("message", "File Upload Failed due to " + e);
						e.printStackTrace();
					}
			} else {
				session.setAttribute("message", "Sorry this Servlet only handles file upload request");
			}
		    response.sendRedirect(requestURI);
	    }
	    
	    else if(servletPath.equals("/ShareFileServlet")  && pathInfo.equals("/deletefile"))
	    {//刪除檔案功能
//	    	System.out.println("ShareFileServlet--132--here is deleteFile  & downloadfile");
	    	String fileIdList = request.getParameter("list");//{"list":[{"fileID":"folder906"},{"fileID":"file911"},{"fileID":"file912"}]}
	    	
	    	JSONObject myjson = new JSONObject(fileIdList);
            JSONArray valArray = myjson.toJSONArray(myjson.names()); //getValue Key對應的Value  [[{"fileID":"folder906"},{"fileID":"file911"},{"fileID":"file912"}]]
            JSONArray lv2Array = (JSONArray)valArray.get(0);//[{"fileID":"folder905"},{"fileID":"folder906"},{"fileID":"file922"}]
            
            List<String> list = new ArrayList<String>();
            for(int i=0;i<lv2Array.length();i++) {
                JSONObject element = lv2Array.getJSONObject(i);
                JSONArray fileId = element.toJSONArray(element.names());
                list.add((String) fileId.get(0));
            }
			List<Map<String, String>> result = shareFileService.deleteFileFunction(list);

			String jsonString = JSONValue.toJSONString(result);// [{"fileID":"folder904"},{"fileID":"folder937"},{"fileID":"file936"},{"fileID":"file928"}]
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);
//			System.out.println(jsonString);
	    }else if(servletPath.equals("/ShareFileServlet")  && pathInfo.equals("/newFolder"))
	    {//新增Folder功能
//	    	System.out.println("ShareFileServlet--161--here is newFolder");
	    	response.setContentType("text/html; charset=UTF-8");
	    	request.setCharacterEncoding("UTF-8");
	    	String folderName = request.getParameter("newfolderName");
	    	int upperFolderId = (int) session.getAttribute("upperFolderId");
	    	System.out.println(folderName);
	    	ShareFileBean result = shareFileService.insertFolder(teamBean,usersBean,folderName,upperFolderId);
	   	 
	    	String jsonString = shareFileService.beanConvert2JSON(result,true); 
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(jsonString);
            System.out.println(jsonString);    
	    }else if(servletPath.equals("/ShareFileServlet")  && pathInfo.equals("/downloadfile"))
	    {//下載檔案功能
		    String fileID = request.getParameter("fileID");
            int fileId = Integer.parseInt(fileID.substring(4));
        	ShareFileBean bean = shareFileService.selectByFileId(fileId);
        	String fileName = bean.getFileName();
        	System.out.println(bean);
        	System.out.println(fileName);
        	        	
    	    PrintWriter out = response.getWriter();
    	    response.setContentType("APPLICATION/OCTET-STREAM");
    	    fileName = new String(fileName.getBytes(), "ISO8859-1");
    	    response.setHeader("Content-Disposition", "attachment; filename=\""	+ fileName + "\"");
    	    FileInputStream fis = new FileInputStream(UPLOAD_DIRECTORY+ "\\" + fileId+"."+bean.getFileType());    	    
    	    int i;
    	    while((i = fis.read()) != -1) {
    	        out.write(i);
    	    }        	
	    }else if (servletPath.equals("/ShareFileServlet") && pathInfo.equals("/renamefile"))
	    {//重新命名功能
	    	response.setContentType("text/html; charset=UTF-8");
	    	request.setCharacterEncoding("UTF-8");
	    	int fileId = Integer.parseInt(request.getParameter("fileId"));
	    	String fileName = request.getParameter("fileName");
	    	
	    	System.out.println(fileId);
	    	System.out.println(fileName);
	    	shareFileService.renameFile(fileId,fileName);
	    }else if (pathInfo.equals("/getFolderTree") && servletPath.equals("/ShareFileServlet") )
	    {//getFolderTree
	    	System.out.println("ShareFileServlet  - getFolderTree");
	    	String teamId =teamBean.getTeamId();
	    	List<FileTreeBean> folderTree =  shareFileService.getGroupFolderTree(teamId);
	    	String jsonString = shareFileService.fileTreeConvert2JSON(folderTree);
	    	response.setContentType("text/html; charset=UTF-8");
	    	PrintWriter out = response.getWriter();
            out.println(jsonString);
            System.out.println(jsonString);   
	    }else if(pathInfo.equals("/updateFolder") && servletPath.equals("/ShareFileServlet"))
	    {//移動檔案功能
	    	System.out.println("ShareFileServlet  - updateFolder");
	    	String moveObj = request.getParameter("moveObj");
	    	System.out.println(moveObj);
	    	//{"list":[{"fileID":"folder1052"},{"fileID":"file1046"},{"fileID":"file1048"},{"fileID":"file1043"}],"newFolderId":"1053"}
	    	JSONObject jsonObject = new JSONObject(moveObj);
	    	JSONArray listArray = jsonObject.getJSONArray("list");//[{"fileID":"folder1051"},{"fileID":"file1044"},{"fileID":"file1042"},{"fileID":"file1040"},{"fileID":"file1041"}]
	    	int newFolderId = Integer.parseInt((String)jsonObject.get("newFolderId"));
	    	System.out.println(listArray);
	    	
	    	List<String> list = new ArrayList<String>();
            for(int i=0;i<listArray.length();i++) {
            	JSONObject fileIdObj =(JSONObject) listArray.get(i);
            	String fileID = (String)fileIdObj.get("fileID");
            	list.add(fileID);
            }
			List<Map<String, String>> result = shareFileService.moveFileFunction(list,newFolderId);

			String jsonString = JSONValue.toJSONString(result);// [{"fileID":"folder904"},{"fileID":"folder937"},{"fileID":"file936"},{"fileID":"file928"}]
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);
			return;
			
	    }else if(pathInfo.equals("/getMember") && servletPath.equals("/ShareFileServlet")){
	    	System.out.println("here is ShareFileServlet  getMember");
	    	System.out.println("999teamID"+teamBean.getTeamId());
	    	String jsonString  = shareFileService.getTeamMember(teamBean.getTeamId());
	    	response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println(jsonString);
	    	return;
	    }
	    else{
	    	System.out.println("What the Hall");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public static void main(String[] args) {
		char one = '1';
		String two = "1";
		int r  ;
		
		r = Character.getNumericValue(one)+Integer.parseInt(two);
		System.out.println(r);
		
//	    ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
//        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
//        Session session = sessionFactory.getCurrentSession();
//        sessionFactory.getCurrentSession().beginTransaction();
//        
//        TeamUserDAO dao = (TeamUserDAO) context.getBean("teamUserDAO");
//        TeamUserBean teamUser= dao.select(new TeamUserIdBean(100,200));
//        System.out.println(teamUser);
//        ShareFileService shareFileService = (ShareFileService) context.getBean("shareFileService");
//	    String pathInfo = "/Group1-1/Group1-1-1/sdsd";//  /0   /p1:[,p1]   /p1/p2/p3:[,p1,p2,p3]
//        
//        int teamId = teamUser.getTeam().getTeamId();
//        List<FileTreeBean> folderTree = shareFileService.getGroupFolderTree(teamId);
//        System.out.println(folderTree);
//        String[] pathName = pathInfo.split("/");//取得各層folder名稱
//        
//        int folderTreeSize = folderTree.size();
//        int folderTreeMaxLevel = folderTree.get(folderTreeSize-1).getFileLevel();
//        
	}

}
