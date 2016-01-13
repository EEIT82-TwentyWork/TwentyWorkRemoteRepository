package com.iii.twentywork.controller.login;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.user.PersonalService;
import com.iii.twentywork.model.service.user.RegisterService;

/**
 * Servlet implementation class PersonalServlet
 */
@WebServlet("/login/Personal")
public class PersonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonalService personalService;
	private RegisterService registerService;
	private TeamBean teamBean;
	private UsersBean userBean;

	public PersonalServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		this.personalService = (PersonalService) context.getBean("personalService");
		this.registerService = (RegisterService) context.getBean("registerService");
		this.userBean = (UsersBean) context.getBean("userBean");
		this.teamBean = (TeamBean) context.getBean("teamBean");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 接收資料
		String teanName = request.getParameter("teamName");
		String email = request.getParameter("email");
		String passtemp = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String cellPhone = request.getParameter("cellPhone");
		String birthtemp = request.getParameter("birth");
		// 轉換資料
		byte[] pass = null;
		if (passtemp != null && passtemp.length() != 0) {
			pass = UsersBean.convertByteArray(passtemp);
		}
		java.util.Date birth = null;
		if (birthtemp != null && birthtemp.length() != 0) {
			birth = UsersBean.convertDate(birthtemp);
			if (birth.equals(new java.util.Date(0))) {
				// errors.put("birth", "birth必須是日期必且擁有yyyy-MM-dd的格式");
			}
		}
		userBean.setEmail(email);
		userBean.setPassword(pass);
		userBean.setUserName(fullName);
		userBean.setCellPhone(cellPhone);
		userBean.setBirth(birth);

		userBean.setTeams(new HashSet<TeamBean>());
		teamBean.setUserses(new HashSet<UsersBean>());
		TeamBean teamInfo = personalService.selectTeamName(teanName);
		teamInfo.getUserses().add(userBean);
		UsersBean userResult = registerService.insertUserRegister(userBean);

		System.out.println(teamInfo);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
