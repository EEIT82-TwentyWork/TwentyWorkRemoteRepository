package com.iii.twentywork.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.Board;
import com.iii.twentywork.model.bean.MyFav;
import com.iii.twentywork.model.bean.Sub;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.board.BoardService;


@WebServlet({"/Board/*","/BoardServlet/*"})
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService;
	@Override
	public void init() throws ServletException{
		ServletContext application = this.getServletContext();
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(application);
		this.boardService = (BoardService) context.getBean("boardService");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收資料
			HttpSession session = request.getSession();
			UsersBean user = (UsersBean) session.getAttribute("LoginOK");
		    TeamBean team = (TeamBean) session.getAttribute("teamBean");
		    String servletPath = request.getServletPath();
		    String pathInfo = request.getPathInfo();
		    System.out.println("servletPath = "+servletPath +"-----pathInfo = "+pathInfo);
		    
		    Map<String,String> errors = new HashMap<String,String>();
		    if(servletPath.equals("/Board") && pathInfo==null)
		    {//討論區列表
		    	List<Board> list = boardService.boardList(team.getTeamId());
		    	request.setAttribute("boardList", list);
//		    	System.out.println("BoardServlet--list:"+list);
		    	request.getRequestDispatcher("/board/main.jsp").forward(request, response);
		    }else if(pathInfo.equals("/insert") &&servletPath.equals("/BoardServlet")) 
		    {//insert board 新增討論版項目
//		        System.out.println("here is /BoardServlet/insert");
		        String boardTitle = request.getParameter("boardTitle");
		        String boardText = request.getParameter("boardText");
		        
		        
		        if(boardText == null || boardText.length() == 0) {
		            errors.put("boardTitleEmpty", "標題不可為空");
		            request.getRequestDispatcher("board/addNewBoard.jsp").forward(request, response);
		            return;
		        }
//		        System.out.println(boardTitle + " : "+boardText);
		        boardService.insertBoard(team, user, boardTitle, boardText);
		        String contextPath = request.getContextPath();
		        response.sendRedirect(contextPath+"/Board");
		        return;
		    }else if(servletPath.equals("/Board")) 
		    {//取得留言板內容
		        String contextPath = request.getContextPath();
		        String[] pathInfoSp = pathInfo.split("/");
		        List<Sub> list ;
		        for(String e:pathInfoSp) {
		            System.out.print(e+"  ,  ");
		            if(!e.isEmpty() && e.length()==32) {
		                Board boardBean = boardService.getBoardBean(e);
		                list = boardService.getSubList(e);
		                request.setAttribute("boardBean",boardBean);
		                request.setAttribute("subList", list);//List<Sub>
		                request.getRequestDispatcher("../board/subMain.jsp").forward(request, response);
		                return;
		            }
		        }
		        response.sendRedirect(contextPath+"/Board");
		    }else if(pathInfo.equals("/subInsert") &&servletPath.equals("/BoardServlet"))
		    {//留言功能
		    	String boardId = request.getParameter("boardID");
		    	String comment = request.getParameter("boardText");
		    	System.out.println("here is /BoardServlet/subInsert");
		    	boardService.addComment(user,boardId,comment);
		    	String contextPath = request.getContextPath();
		        response.sendRedirect(contextPath+"/Board/"+boardId);
		    }else if(pathInfo.equals("/getMyFav") &&servletPath.equals("/BoardServlet"))
		    {//取得關注的聊天版列表
		    	String jsonString = boardService.selectMyFavList( team.getTeamId(), user.getUserID());
	            response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println(jsonString);
//	            System.out.println("getMyFav:"+jsonString);   
		    }else if(pathInfo.equals("/addMyFav") &&servletPath.equals("/BoardServlet"))
		    {//新增關注的討論版
		    	String boardId = request.getParameter("boardId");
		    	String jsonString =boardService.addMyFav( boardId,team,user);
		    	response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println(jsonString);
//	            System.out.println("addMyFav:"+jsonString);  
		    }else if(pathInfo.equals("/deleteMyFav") &&servletPath.equals("/BoardServlet"))
		    {//刪除關注的討論版
		    	String boardId = request.getParameter("boardId");
		    	String jsonString =boardService.deleteMyFav(boardId, user);
		    	response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println(jsonString);
//	            System.out.println("deleteMyFav:"+jsonString); 
		    }
		    else{
		    	System.out.println("wrong path");
		    }
		    
	}
	
	
}
