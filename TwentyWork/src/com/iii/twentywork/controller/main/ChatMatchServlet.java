package com.iii.twentywork.controller.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.main.AbstractMainService;

@WebServlet("/com/iii/twentywork/controller/main/ChatMatchServlet")
public class ChatMatchServlet extends HttpServlet {
	private AbstractMainService abstractMainService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		this.abstractMainService = (AbstractMainService) context.getBean("abstractMainService");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 接收資料
		String ownID = (String) session.getAttribute("ownID");
		String matchID = request.getParameter("matchID");
		String ownName=(String)session.getAttribute("userName");
		UsersBean obj=abstractMainService.selectName(matchID);
		String matchName=obj.getUserName();
		System.out.println(ownName);
		System.out.println(matchName);
		// 驗證資料
		CharSequence own = ownID.subSequence(26, 32);
		CharSequence match = matchID.subSequence(26, 32);
		int ownint = Integer.parseInt(own.toString(), 16);
		int matchint = Integer.parseInt(match.toString(), 16);
		System.out.println("轉" + ownint);
		System.out.println("轉" + matchint);
		System.out.println("切完" + own);
		System.out.println("切完" + match);
		System.out.println("原本" + ownID);
		System.out.println("原本" + matchID);
		//比大小
		 String chatID=null;
		if (ownint < matchint) {
			chatID = matchID + ownID;
			System.out.println("小於"+chatID);
		} else if (ownint > matchint) {
			chatID = ownID + matchID;
			System.out.println("大於"+chatID);
		} else if (ownint == matchint) {
			chatID =Integer.toString(ownint)+Integer.toString(matchint) ;
			System.out.println("等於"+chatID);
		}
			
			Gson gson =new GsonBuilder().create();
		String jsonchatID = gson.toJson(chatID);	
				
		session.setAttribute("chatID", chatID);
	
		session.setAttribute("chatOwnName", ownName);
		response.setContentType("appliction/json;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(chatID);
		out.flush();
		out.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
