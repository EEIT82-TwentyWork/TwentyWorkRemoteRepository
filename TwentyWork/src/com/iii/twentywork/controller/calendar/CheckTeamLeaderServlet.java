package com.iii.twentywork.controller.calendar;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
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

import com.iii.twentywork.model.bean.MySchedule;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.TeamSchedule;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.calendar.CalService;
@WebServlet(urlPatterns="/com/iii/twentywork/controller/calendar/CheckTeamLeaderServlet")
public class CheckTeamLeaderServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(application);

	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet GOGO");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String path=req.getContextPath();
	//接收資料
				String checkteamleader = (String)session.getAttribute("userPhone");
				System.out.println("checkteamleader等於" + checkteamleader);

	//驗證資料

	//轉換資料

	//呼叫Model
			
	//根據Model執行結果，呼叫View
			System.out.println("根據Model執行結果，呼叫View GOGO");
			if (checkteamleader.equals("1")){
				resp.setContentType("charset=UTF-8");
				PrintWriter out = resp.getWriter();
				String result = "1";
				out.print(result);
				out.flush();
				out.close();
				System.out.println("servlet result=" + result);
//				resp.sendRedirect(path+"/main/workHome/main.jsp");
			} else {
				resp.setContentType("charset=UTF-8");
				PrintWriter out = resp.getWriter();
				String result = "0";
				out.print(result);
				out.flush();
				out.close();
				System.out.println("servlet result=" + result);
//				resp.sendRedirect(path+"/main/workHome/main.jsp");
			}
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}



}
