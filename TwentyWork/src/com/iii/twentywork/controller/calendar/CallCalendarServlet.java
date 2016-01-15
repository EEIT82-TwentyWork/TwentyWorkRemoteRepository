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
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.calendar.CalService;

@WebServlet(urlPatterns="/com/iii/tewntywork/controller/calendar/CallCalendarServlet.controller")
public class CallCalendarServlet extends HttpServlet {
	public CalService calService;
	private MySchedule mySchedulebean;
	private UsersBean userbean;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(application);
		this.calService = (CalService) context.getBean("calService");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet GOGO");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String path=req.getContextPath();
	//接收資料
		System.out.println("requestURI GOGO");
		String requestURI = (String) session.getAttribute("requestURI");
		System.out.println("req.getParameter GOGO");
				String id = req.getParameter("id");
				String title = req.getParameter("title");
				String temp2 = req.getParameter("start");
				String startdate = req.getParameter("startdate");
				String starttimehour = req.getParameter("starttimehour");
				String starttimemin = req.getParameter("starttimemin");
				String temp3 = req.getParameter("end");
				String enddate = req.getParameter("enddate");
				String endtimehour = req.getParameter("endtimehour");
				String endtimemin = req.getParameter("endtimemin");
				String allday = req.getParameter("allday");
				String color = req.getParameter("color");
				String prodaction = req.getParameter("prodaction");
		System.out.println("(UsersBean)session.getAttribute GOGO");
				userbean = (UsersBean)session.getAttribute("UserInfo");
				

	//驗證資料
		System.out.println("驗證資料 GOGO");
			Map<String, String> errors = new HashMap<String, String>();
			req.setAttribute("errors", errors);
			
			if("Insert".equals(prodaction) || "Update".equals(prodaction) || "Delete".equals(prodaction)) {
				if(title==null || title.length()==0) {
					errors.put("title", "請輸入title以便執行"+prodaction);
				}
			}
	//轉換資料
			System.out.println("轉換資料 GOGO");
			java.util.Date start = null;
			if(temp2!=null && temp2.length()!=0) {
				start = MySchedule.convertDate(temp2);
				if(start.equals(new java.util.Date(0))) {
					errors.put("starttime", "starttime必須是日期必且擁有yyyy-MM-dd的格式");
				}
			} else if(startdate!=null && starttimehour!=null && starttimemin!=null){
				String startString = startdate + " " + starttimehour + ":" + starttimemin;
				start = MySchedule.convertDate(startString);
				if(start.equals(new java.util.Date(0))) {
					errors.put("starttime", "starttime必須是日期必且擁有yyyy-MM-dd的格式");
				}
			}
			java.util.Date end = null;
			if(temp3!=null && temp3.length()!=0) {
				end = MySchedule.convertDate(temp3);
				if(end.equals(new java.util.Date(0))) {
					errors.put("endtime", "endtime必須是日期必且擁有yyyy-MM-dd的格式");
				}
			} else if(enddate!=null && endtimehour!=null && endtimemin!=null){
				String endString = enddate + " " + endtimehour + ":" + endtimemin;
				end = MySchedule.convertDate(endString);
				if(end.equals(new java.util.Date(0))) {
					errors.put("endtime", "endtime必須是日期必且擁有yyyy-MM-dd的格式");
				}
			}
			
			if(errors!=null && !errors.isEmpty()) {
				req.getRequestDispatcher(
						"/main/calendar/cal_opt.jsp").forward(req, resp);
				return;
			}
			
	//呼叫Model
			System.out.println("呼叫Model GOGO");
			
	//根據Model執行結果，呼叫View
			System.out.println("根據Model執行結果，呼叫View GOGO");
			if("Call".equals(prodaction)) {
				resp.setContentType("application/json; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				String result = calService.callCalendar(mySchedulebean);
				out.print(result);
				out.flush();
				out.close();
				System.out.println("servlet result=" + result);
			} else  {
				errors.put("action", "Unknown Action:"+prodaction);
				resp.sendRedirect(path+"/main/calendar/cal_opt.jsp");
			}
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
