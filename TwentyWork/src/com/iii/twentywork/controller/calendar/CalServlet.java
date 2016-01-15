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

@WebServlet(urlPatterns="/com/iii/tewntywork/controller/calendar/CalServlet.controller")
public class CalServlet extends HttpServlet {
	public CalService calService;
	private MySchedule mySchedulebean;
	private UsersBean userbean;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(application);
		this.calService = (CalService) context.getBean("calService");
		this.mySchedulebean = (MySchedule) context.getBean("MySchedule");
		this.userbean = (UsersBean) context.getBean("userBean");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet GOGO");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String path=req.getContextPath();
	//接收資料
		String requestURI = (String) session.getAttribute("requestURI");
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
				String myEventDesp = req.getParameter("myEventDesp");
				String myLocation = req.getParameter("myLocation");
				String myRmindercheckbox = req.getParameter("myRmindercheckbox");
				String temp4 = req.getParameter("myRminder");
				String myRminderdate = req.getParameter("myRminderdate");
				String myRmindertimehour = req.getParameter("myRmindertimehour");
				String myRmindertimemin = req.getParameter("myRmindertimemin");
				String allday = req.getParameter("allday");
				String color = req.getParameter("color");
				String prodaction = req.getParameter("prodaction");
				UsersBean myuserbean = (UsersBean)session.getAttribute("userinfo");
				

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
			System.out.println("轉換資料start="+temp2);
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
			System.out.println("轉換資料start成功,轉換資料end="+temp3);
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
			
			
			System.out.println("myRminder創造前 temp4="+temp4);
			java.util.Date myRminder = null;
			System.out.println("提醒時間進入判斷前"+temp4);
			if(myRmindercheckbox=="1"){
				System.out.println("提醒時間進入判斷後"+temp4);
				if(temp4!=null && temp4.length()!=0) {
					myRminder = MySchedule.convertDate(temp4);
					if(myRminder.equals(new java.util.Date(0))) {
						errors.put("myRminder", "myRminder必須是日期必且擁有yyyy-MM-dd的格式");
					}
				} else if(enddate!=null && endtimehour!=null && endtimemin!=null){
					String myRminderString = myRminderdate + " " + myRmindertimehour + ":" + myRmindertimemin;
					myRminder = MySchedule.convertDate(myRminderString);
					if(myRminder.equals(new java.util.Date(0))) {
						errors.put("myRminder", "myRminder必須是日期必且擁有yyyy-MM-dd的格式");
					}
				}
				System.out.println("提醒時間轉換結束"+temp4);
			}
				
				
			if(errors!=null && !errors.isEmpty()) {
				req.getRequestDispatcher(
						"/main/calendar/cal_opt.jsp").forward(req, resp);
				return;
			}
			
	//呼叫Model
			mySchedulebean.setId(id);
			mySchedulebean.setTitle(title);
			mySchedulebean.setStart(start);
			mySchedulebean.setEnd(end);
			
			mySchedulebean.setMyEventDesp(myEventDesp);
			mySchedulebean.setMyLocation(myLocation);
//			if(myRmindercheckbox.equals("true")){
				mySchedulebean.setMyRminder(myRminder);
//			} else {
//				mySchedulebean.setMyRminder(null);
//			}
			mySchedulebean.setAllday(allday);
			mySchedulebean.setColor(color);
			
			
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
			} else if("FindEvent".equals(prodaction)) {
				System.out.println(start);
				System.out.println(end);
				MySchedule result = calService.selectEvent(mySchedulebean);
				System.out.println("FindEvent Servlet="+result);
				session.setAttribute("FindEventBean", result);
//				req.setAttribute("FindEventBean", result);
				req.getRequestDispatcher("/main/calendar/cal_opt.jsp").forward(req, resp);
//				resp.sendRedirect(path+"/display.jsp");
			} else if("Select".equals(prodaction)) {
				List<MySchedule> result = calService.select(mySchedulebean);
				req.setAttribute("select", result);
//				req.getRequestDispatcher("/display.jsp").forward(req, resp);
				req.getRequestDispatcher("/main/calendar/cal_opt.jsp").forward(req, resp);
//				resp.sendRedirect(path+"/display.jsp");
			} else if("Insert".equals(prodaction)) {
				mySchedulebean.setUserses(new HashSet<UsersBean>());
				userbean.setMySchedules(new HashSet<MySchedule>());
				
				UsersBean userInfo = calService.selectUserBean(myuserbean);
				userInfo.getMySchedules().add(mySchedulebean);
				MySchedule result = calService.insert(mySchedulebean);
				if(result==null) {
					errors.put("action", "Insert fail");
				} else {
					req.setAttribute("insert", result);
				}
				resp.sendRedirect(path+"/main/calendar/cal_opt.jsp");
			} else if("Update".equals(prodaction)) {
				MySchedule result = calService.update(mySchedulebean);
				if(result==null) {
					errors.put("action", "Update fail");
				} else {
					req.setAttribute("update", result);
				}
				resp.sendRedirect(path+"/main/calendar/cal_opt.jsp");
//				req.getRequestDispatcher("/cal_opt.jsp").forward(req, resp);
			} else if("Delete".equals(prodaction)) {
				boolean result = calService.delete(mySchedulebean);
				if(!result) {
					req.setAttribute("delete", 0);
				} else {
					req.setAttribute("delete", 1);
				}
				resp.sendRedirect(path+"/main/calendar/cal_opt.jsp");
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
