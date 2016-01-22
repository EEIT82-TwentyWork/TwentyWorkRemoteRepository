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

@WebServlet(urlPatterns="/com/iii/tewntywork/controller/calendar/CalServlet.controller")
public class CalServlet extends HttpServlet {
	public CalService calService;
	private MySchedule mySchedulebean;
	private TeamSchedule teamSchedule;
	private UsersBean userbean;
	private TeamBean teambean;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(application);
//		new Bean
		this.calService = (CalService) context.getBean("calService");
		this.mySchedulebean = (MySchedule) context.getBean("mySchedule");
		this.teamSchedule = (TeamSchedule) context.getBean("teamSchedule");
		this.userbean = (UsersBean) context.getBean("userBean");
		this.teambean = (TeamBean) context.getBean("teamBean");
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
				String eventDesp = req.getParameter("eventDesp");
				String location = req.getParameter("location");
				String rmindercheckbox = req.getParameter("rmindercheckbox");
				String temp4 = req.getParameter("rminder");
				String rminderdate = req.getParameter("rminderdate");
				String rmindertimehour = req.getParameter("rmindertimehour");
				String rmindertimemin = req.getParameter("rmindertimemin");
				String allday = req.getParameter("allday");
				String color = req.getParameter("color");
				String prodaction = req.getParameter("prodaction");
				UsersBean myuserbean = (UsersBean)session.getAttribute("UserInfo");
				String myUserID = myuserbean.getUserID();
				TeamBean myteambean = myuserbean.getTeams().iterator().next();

				System.out.println("接收資料 id =  " + id);
				System.out.println("接收資料 title =  " + title);
				System.out.println("接收資料 start =  " + temp2);
				System.out.println("接收資料 startdate =  " + startdate);
				System.out.println("接收資料 starttimehour =  " + starttimehour);
				System.out.println("接收資料 starttimemin =  " + starttimemin);
				System.out.println("接收資料 end =  " + temp3);
				System.out.println("接收資料 enddate =  " + enddate);
				System.out.println("接收資料 endtimehour =  " + endtimehour);
				System.out.println("接收資料 endtimemin =  " + endtimemin);
				System.out.println("接收資料 eventDesp =  " + eventDesp);
				System.out.println("接收資料 location =  " + location);
				System.out.println("接收資料 rmindercheckbox =  " + rmindercheckbox);
				System.out.println("接收資料 rminder =  " + temp4);
				System.out.println("接收資料 rminderdate =  " + rminderdate);
				System.out.println("接收資料 rmindertimehour =  " + rmindertimehour);
				System.out.println("接收資料 rmindertimemin =  " + rmindertimemin);
				System.out.println("接收資料 allday =  " + allday);
				System.out.println("接收資料 color =  " + color);
				System.out.println("接收資料 prodaction =  " + prodaction);
				System.out.println("Servlet接收資料UsersBean等於" + myuserbean);
				System.out.println("Servlet接收資料UserID等於" + myUserID);
				System.out.println("Servlet接收資料TeamBean等於" + myteambean);
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
			System.out.println("轉換資料GOGO");
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
			System.out.println("轉換資料start成功,Start=" + start);
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
			System.out.println("轉換資料end成功,end=" + end);
			
			System.out.println("myRminder創造前 temp4="+temp4);
			java.util.Date rminder = null;
			System.out.println("提醒時間進入判斷前"+temp4);
			if(rmindercheckbox=="1"){
				System.out.println("提醒時間進入判斷後"+temp4);
				if(temp4!=null && temp4.length()!=0) {
					rminder = MySchedule.convertDate(temp4);
					if(rminder.equals(new java.util.Date(0))) {
						errors.put("myRminder", "myRminder必須是日期必且擁有yyyy-MM-dd的格式");
					}
				} else if(enddate!=null && endtimehour!=null && endtimemin!=null){
					String myRminderString = rminderdate + " " + rmindertimehour + ":" + rmindertimemin;
					rminder = MySchedule.convertDate(myRminderString);
					if(rminder.equals(new java.util.Date(0))) {
						errors.put("myRminder", "myRminder必須是日期必且擁有yyyy-MM-dd的格式");
					}
				}
				System.out.println("提醒時間轉換結束rminder="+temp4);
			}
			System.out.println("提醒時間轉換成功rminder="+rminder);
			
//			String allday = null;
//			if (temp5==undefined) {
//				allday=null;
//			}
//			System.out.println("轉換完後allday等於" + allday);
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
			
			mySchedulebean.setMyEventDesp(eventDesp);
			mySchedulebean.setMyLocation(location);
//			if(rmindercheckbox=="1"){
				mySchedulebean.setMyRminder(rminder);
//			} else {
//				mySchedulebean.setMyRminder(null);
//			}
			mySchedulebean.setAllday(allday);
			mySchedulebean.setColor(color);
			mySchedulebean.setMyUserID(myUserID);
			
			
			teamSchedule.setEventId(id);
			teamSchedule.setEventName(title);
			teamSchedule.setEventStartDate(start);
			teamSchedule.setEventEndDate(end);
			teamSchedule.setEventDesp(eventDesp);
			teamSchedule.setLocation(location);
			teamSchedule.setReminder(rminder);
			teamSchedule.setEventAllday(allday);
			System.out.println(color);
			if(color==null){
				color = "red";
				teamSchedule.setEventColor(color);
			}
			teamSchedule.setTeamScheduleUserID(myUserID);
			teamSchedule.setTeam(myteambean);
			
	//根據Model執行結果，呼叫View
			System.out.println("根據Model執行結果，呼叫View GOGO");
			if("Call".equals(prodaction)) {
				resp.setContentType("application/json; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				String result = calService.callCalendar(myuserbean, myteambean);
				out.print(result);
				out.flush();
				out.close();
				System.out.println("servlet result=" + result);
			} else if("CallTeam".equals(prodaction)) {
				resp.setContentType("application/json; charset=UTF-8");
				PrintWriter teamout = resp.getWriter();
				String result = calService.callTeamCalendar(myteambean);
				teamout.print(result);
				teamout.flush();
				teamout.close();
				System.out.println("servlet result=" + result);
//			} else if("FindEvent".equals(prodaction)) {
//				System.out.println(start);
//				System.out.println(end);
//				MySchedule result = calService.selectEvent(mySchedulebean);
//				System.out.println("FindEvent Servlet="+result);
//				session.setAttribute("FindEventBean", result);
////				req.setAttribute("FindEventBean", result);
//				req.getRequestDispatcher("/main/calendar/cal_opt.jsp").forward(req, resp);
////				resp.sendRedirect(path+"/display.jsp");
//			} else if("Select".equals(prodaction)) {
//				List<MySchedule> result = calService.select(mySchedulebean);
//				req.setAttribute("select", result);
//				req.getRequestDispatcher("/main/calendar/cal_opt.jsp").forward(req, resp);
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
			} else if("InsertTeam".equals(prodaction)) {
//				teambean.setTeamSchedules(new HashSet<TeamSchedule>());
				
				TeamBean teamInfo = calService.selectTeamBean(myteambean);
//				teamInfo.getTeamSchedules().add(teamSchedule);

				TeamSchedule result = calService.insertTeam(teamSchedule);
				if(result==null) {
					errors.put("action", "Insert fail");
				} else {
					req.setAttribute("insert", result);
				}
				resp.sendRedirect(path+"/main/calendar/teamcal_opt.jsp");
			} else if("Update".equals(prodaction)) {
				MySchedule result = calService.update(mySchedulebean);
				if(result==null) {
					errors.put("action", "Update fail");
				} else {
					req.setAttribute("update", result);
				}
				resp.sendRedirect(path+"/main/calendar/cal_opt.jsp");
//				req.getRequestDispatcher("/cal_opt.jsp").forward(req, resp);
			} else if("UpdateTeam".equals(prodaction)) {
				TeamSchedule result = calService.updateTeam(teamSchedule);
				if(result==null) {
					errors.put("action", "Update fail");
				} else {
					req.setAttribute("update", result);
				}
				resp.sendRedirect(path+"/main/calendar/teamcal_opt.jsp");
//				req.getRequestDispatcher("/cal_opt.jsp").forward(req, resp);
			} else if("Delete".equals(prodaction)) {
				boolean result = calService.delete(mySchedulebean);
				if(!result) {
					req.setAttribute("delete", 0);
				} else {
					req.setAttribute("delete", 1);
				}
				resp.sendRedirect(path+"/main/calendar/cal_opt.jsp");
			} else if("DeleteTeam".equals(prodaction)) {
				boolean result = calService.deleteTeam(teamSchedule);
				if(!result) {
					req.setAttribute("delete", 0);
				} else {
					req.setAttribute("delete", 1);
				}
				resp.sendRedirect(path+"/main/calendar/teamcal_opt.jsp");
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
