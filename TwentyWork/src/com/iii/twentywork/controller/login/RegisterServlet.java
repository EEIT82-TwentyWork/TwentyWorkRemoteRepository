package com.iii.twentywork.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.users.UsersBean;

public class RegisterServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(application);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收資料
		String email = request.getParameter("email");
		String passtemp = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		String fname = request.getParameter("fname");
		String cellPhone = request.getParameter("cellPhone");
		String birthtemp = request.getParameter("birth");
		String teamName = request.getParameter("teamName");
		String about = request.getParameter("about");
		String submit = request.getParameter("submit");
		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("erros", errors);
		if(email.equals(null)||email.trim().length()==0){
			errors.put("email","Please provide an email account register");
		}
		if(passtemp.equals(null)||passtemp.trim().length()==0){
			errors.put("password", "Please choose a password");
		}
		//PASS match cpass
		if(!cpass.equals(null)||!(cpass.trim().length()==0)){
			if(cpass.equals(passtemp)){
				errors.put("cpass","password not match");
			}
			errors.put(cpass, "Please choose a password again");
		}
		if(fname.equals(null)||fname.trim().length()==0){
			errors.put("fname", "Please provide your full name");
		}
		if(cellPhone.equals(null)||cellPhone.trim().length()==0){
			errors.put("cellPhone","This's required field ");
		}
		if(birthtemp.equals(null)||birthtemp.trim().length()==0){
			errors.put("birth", "When is your birthday ?");
		}
		if(teamName.equals(null)||teamName.trim().length()==0){
			errors.put("teamName", "Create team name!");
		}
		//轉換資料
		byte[] pass = null;
		if(passtemp!=null && passtemp.length()!=0) {
			pass = UsersBean.convertByteArray(passtemp);
			if(pass==null) {
				errors.put("password", "password not match");
			}
		}
		java.util.Date birth = null;
		if(birthtemp!=null && birthtemp.length()!=0) {
			birth = UsersBean.convertDate(birthtemp);
			if(birth.equals(new java.util.Date(0))) {
				errors.put("birth", "birth必須是日期必且擁有yyyy-MM-dd的格式");
			}			
		}
		
		
		
		//呼叫Model
		UsersBean uBean = new UsersBean();
		TeamBean tBean = new TeamBean();
		uBean.setUserName(fname);
		uBean.setEmail(email);
		uBean.setPassword(pass);
		uBean.setBirth(birth);
		uBean.setCellPhone(cellPhone);
		tBean.setTeamAbout(about);
		
		//根據Model執行結果，呼叫View
		if("Submit".equals(submit)) {
			UsersBean uresult = RegisterService.usersRegister(uBean);
			TeamBean tresult = RegisterService.teamRegister(tBean);
			if(uresult==null || tresult==null) {
				errors.put("action", "Insert fail");
			}
			request.getRequestDispatcher(
					"/main/workHome/main.jsp").forward(request, response);
		} else  {
			errors.put("action", "Unknown Action:"+ submit);
			request.getRequestDispatcher(
					"/login/register.jsp").forward(request, response);
		}
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
