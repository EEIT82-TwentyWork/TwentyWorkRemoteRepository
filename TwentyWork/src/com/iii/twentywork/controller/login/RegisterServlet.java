package com.iii.twentywork.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.user.RegisterService;

//@WebServlet("/main/workHome/main")
public class RegisterServlet extends HttpServlet {
	private RegisterService registerService;
	private UsersBean userBean;
	private TeamBean teamBean;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(application);
		this.registerService = (RegisterService) context
				.getBean("registerService");

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 接收資料
		String email = request.getParameter("email");
		String passtemp = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		String fullname = request.getParameter("fname");
		String cellPhone = request.getParameter("cellPhone");
		String birthtemp = request.getParameter("birth");
		String teamName = request.getParameter("teamName");
		String teamAbout = request.getParameter("about");
		String submit = request.getParameter("registersubmit");
		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("erros", errors);
		if (email.equals(null) || email.trim().length() == 0) {
			errors.put("email", "Please provide an email account register");
		}
		if (passtemp.equals(null) || passtemp.trim().length() == 0) {
			errors.put("password", "Please choose a password");
		}
		// PASS match cpass
		if (!cpass.equals(null) || !(cpass.trim().length() == 0)) {
			if (cpass.equals(passtemp)) {
				errors.put("cpass", "password not match");
			}
			errors.put(cpass, "Please choose a password again");
		}
		if (fullname.equals(null) || fullname.trim().length() == 0) {
			errors.put("fname", "Please provide your full name");
		}
		if (cellPhone.equals(null) || cellPhone.trim().length() == 0) {
			errors.put("cellPhone", "This's required field ");
		}
		if (birthtemp.equals(null) || birthtemp.trim().length() == 0) {
			errors.put("birth", "When is your birthday ?");
		}
		if (teamName.equals(null) || teamName.trim().length() == 0) {
			errors.put("teamName", "Create team name!");
		}
		// 轉換資料
		byte[] pass = null;
		if (passtemp != null && passtemp.length() != 0) {
			pass = UsersBean.convertByteArray(passtemp);
			if (pass == null) {
				errors.put("password", "password not match");
			}
		}
		java.util.Date birth = null;
		if (birthtemp != null && birthtemp.length() != 0) {
			birth = UsersBean.convertDate(birthtemp);
			if (birth.equals(new java.util.Date(0))) {
				errors.put("birth", "birth必須是日期必且擁有yyyy-MM-dd的格式");
			}
		}
		// 呼叫Model
		 
		 userBean.setUserName(fullname);
		 userBean.setEmail(email);
		 userBean.setPassword(pass);
		 userBean.setBirth(birth);
		 userBean.setCellPhone(cellPhone);
		 userBean.setPhone(null);
		 userBean.setUserImage(null);
		
		 teamBean.setTeamName(teamName);
		 teamBean.setTeamImage(null);
		 teamBean.setTeamName(teamName);
		 
		
		
		
//		userBean = registerService.insertUserRegister(userBean);
//		teamBean = registerService.accessuserBean(teamName, teamAbout);
		// 根據Model執行結果，呼叫View
//		if ("Submit".equals(submit)) {
//			UsersBean userResult = registerService.userRegister(userBean);
//			TeamBean teamResult = registerService.teamRegister(teamBean);
//			if (userResult == null || teamResult == null) {
//				errors.put("action", "Insert fail");
//			}
//			System.out
//					.println("RegisterServlet -- Line114--sendRedirect(main.workHome.main.jsp)");
			// -----------------------------------------------------------------
			// UsersBean userEmail = userDAO.SelectEmail(email);
			// TeamBean selectTeamName = userDAO.SelectTeamName(teamName);
			// TeamUserIdBean teamUserIdBean = new TeamUserIdBean();
			//
			// teamUserIdBean.setTeamId(selectTeamName.getTeamId());
			// teamUserIdBean.setUserId(userEmail.getUserID());
			// teamUserBean.setTeam(selectTeamName);
			// teamUserBean.setUsers(userEmail);
			// teamUserBean.setRights(1);
			// teamUserBean.setActiveDate(new java.util.Date());
			// teamUserBean.setId(teamUserIdBean);
			//
			// teamUserDAO.insert(teamUserBean);
			// -----------------------------------------------------------------
//			session.setAttribute("LoginOK", userResult);
//			String path = request.getContextPath();
//			response.sendRedirect(path + "/login/invite.jsp");
//		} else {
//			errors.put("action", "Unknown Action:" + submit);
//			System.out
//					.println("RegisterServlet -- Line120--getRequestDispatcher(/login/register.jsp)");
//			request.getRequestDispatcher("/login/register.jsp").forward(
//					request, response);
//		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
