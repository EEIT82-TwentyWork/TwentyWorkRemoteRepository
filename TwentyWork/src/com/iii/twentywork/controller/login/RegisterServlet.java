package com.iii.twentywork.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.sharefile.ShareFileService;
import com.iii.twentywork.model.service.user.RegisterService;

@WebServlet("/main/workHome/main")
public class RegisterServlet extends HttpServlet {
	private RegisterService registerService;
	private ShareFileService shareFileService;
//	private UsersBean userBean;
	private TeamBean teamBean;
	private UsersBean userBean=new UsersBean();

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		this.registerService = (RegisterService) context.getBean("registerService");
		this.shareFileService = (ShareFileService) context.getBean("shareFileService");
		this.userBean = (UsersBean) context.getBean("userBean");
		this.teamBean = (TeamBean) context.getBean("teamBean");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
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
		userBean.setUserImage(null);
		userBean.setCellPhone(cellPhone);
		userBean.setPhone("1");
		userBean.setTeams(new HashSet<TeamBean>());

		teamBean.setTeamName(teamName);
		teamBean.setTeamImage(null);
		teamBean.setTeamAbout(teamAbout);
		teamBean.setUserses(new HashSet<UsersBean>());

		userBean.getTeams().add(teamBean);
		teamBean.getUserses().add(userBean);
		System.out.println("結束塞值init");
		// 根據Model執行結果，呼叫View
		if ("Submit".equals(submit)) {
			UsersBean userResult = registerService.insertUserRegister(userBean);
			if (userResult == null) {
				errors.put("action", "Insert fail");
			}
			
			Set<TeamBean> temp = userResult.getTeams();
			System.out.println(temp);
			TeamBean teambean2shareFile =null;
			for(TeamBean e:temp) {
			    if(e.getTeamName().equals(teamName)) {
			    	teambean2shareFile=e;
			        break;
			    }
			}
			shareFileService.insertGroupRootFolder(teambean2shareFile);
			session.setAttribute("UserInfo", userBean);
			String path = request.getContextPath();
			response.sendRedirect(path + "/login/invite.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.doGet(req, resp);
	}
}
