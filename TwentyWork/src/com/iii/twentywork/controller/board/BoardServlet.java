package com.iii.twentywork.controller.board;

import java.io.IOException;
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
			UsersBean usersBean = (UsersBean) session.getAttribute("LoginOK");
		    TeamBean teamBean = (TeamBean) session.getAttribute("teamBean");
		    String servletPath = request.getServletPath();
		    String pathInfo = request.getPathInfo();
		    System.out.println("servletPath = "+servletPath +"-----pathInfo = "+pathInfo);
		    
		    Map<String,String> errors = new HashMap<String,String>();
		    if(servletPath.equals("/Board") && pathInfo==null){
		    	List<Board> list = boardService.boardList(teamBean.getTeamId());
		    	request.setAttribute("boardList", list);
		    	System.out.println("BoardServlet--list:"+list);
		    	request.getRequestDispatcher("/board/main.jsp").forward(request, response);
		    }else if(pathInfo.equals("/insert") &&servletPath.equals("/BoardServlet")) 
		    {//insert board 新增討論版項目
		        System.out.println("here is /BoardServlet/insert");
		        String boardTitle = request.getParameter("boardTitle");
		        String boardText = request.getParameter("boardText");
		        
		        
		        if(boardText == null || boardText.length() == 0) {
		            errors.put("boardTitleEmpty", "標題不可為空");
		            request.getRequestDispatcher("board/addNewBoard.jsp").forward(request, response);
		            return;
		        }
		        System.out.println(boardTitle + " : "+boardText);
		        boardService.insert(teamBean, usersBean, boardTitle, boardText);
		        String contextPath = request.getContextPath();
		        response.sendRedirect(contextPath+"/Board");
		        return;
		    }else if(servletPath.equals("/Board")) 
		    {
		        String contextPath = request.getContextPath();
		        String[] pathInfoSp = pathInfo.split("/");
		        List<Sub> list ;
//		        System.out.println(pathInfoSp.length);
		        for(String e:pathInfoSp) {
		            System.out.print(e+"  ,  ");
		            if(!e.isEmpty() && e.length()==32) {
		                Board boardBean = boardService.getBoardBean(e);
		                list = boardService.getSubList(e);
		                
		                request.setAttribute("boardBean",boardBean);
		                request.setAttribute("subList", list);//List<Sub>
		                request.getRequestDispatcher("../board/subMain.jsp").forward(request, response);
		                System.out.println("before return");
		                return;
		            }
		        }
		        response.sendRedirect(contextPath+"/Board");
		    }
		    else{
		    	System.out.println("wrong path");
		    }
		    
	}
	
	
}
