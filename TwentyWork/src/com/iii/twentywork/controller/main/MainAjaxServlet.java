package com.iii.twentywork.controller.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.main.AbstractMainService;

@WebServlet("/com/iii/twentywork/controller/main/MainAjaxServlet")
public class MainAjaxServlet extends HttpServlet {
	private AbstractMainService abstractMainService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		this.abstractMainService = (AbstractMainService) context.getBean("abstractMainService");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("進入Servlet");
		List<Map<String, String>> TeamUserID = new ArrayList<Map<String, String>>();
		HttpSession session = request.getSession();
		UsersBean obj = (UsersBean) session.getAttribute("UserInfo");
		String ownID = obj.getUserID();
		// getTeamId
		String teamID = (String) obj.getTeams().iterator().next().getTeamId();
		// selectTeam
		TeamBean teamBean = abstractMainService.selectTeam(teamID);
		// 宣告 Iterator
		Iterator<UsersBean> teamUsers = teamBean.getUserses().iterator();
		// thisUserID
		String thisUserID = obj.getUserID();

		while (teamUsers.hasNext()) {

			Map thisMap = new HashMap();
			UsersBean temp = teamUsers.next();
			if (!thisUserID.equals(temp.getUserID())) {
				String thisID = temp.getUserID();
				String thisName = temp.getUserName();
				thisMap.put("userID", thisID);
				thisMap.put("userName", thisName);
				TeamUserID.add(thisMap);
			}
		}
		System.out.println(TeamUserID);

		// new Gson to json
		Gson gson = new GsonBuilder().create();
		String jsonTeamUserID = gson.toJson(TeamUserID);

		System.out.println(jsonTeamUserID);
		session.setAttribute("TeamUserIDList", jsonTeamUserID);
		session.setAttribute("ownID", ownID);
		response.setContentType("appliction/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonTeamUserID);
		out.flush();
		out.close();

		String path = request.getContextPath();

		// response.sendRedirect(path + "/main/workHome/main.jsp");

		// request.getRequestDispatcher("/main/workHome/main.jsp").forward(request,
		// response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);

	}

}
