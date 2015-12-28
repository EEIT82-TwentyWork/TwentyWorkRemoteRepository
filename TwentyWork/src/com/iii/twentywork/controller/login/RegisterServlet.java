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
		String pass = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		String fname = request.getParameter("fname");
		String cellPhone = request.getParameter("cellPhone");
		String birth = request.getParameter("birth");
		String teamName = request.getParameter("teamName");
		String about = request.getParameter("about");
		
		// 驗證資料
//		Map<String, String> errors = new HashMap<String, String>();
//		request.setAttribute("erros", errors);
//		if(email.equals(null)||email.trim().length()==0){
//			errors.put("email","Please provide an email account register");
//		}
//		if(pass.equals(null)||pass.trim().length()==0){
//			errors.put("password", "Please choose a password");
//		}
//		//PASS match cpass
//		if(!cpass.equals(null)||!(cpass.trim().length()==0)){
//			if(cpass.equals(pass)){
//				errors.put("cpass","password not match");
//			}
//			errors.put(cpass, "Please choose a password again");
//		}
//		if(fname.equals(null)||fname.trim().length()==0){
//			errors.put("fname", "Please provide your full name");
//		}
//		if(cellPhone.equals(null)||cellPhone.trim().length()==0){
//			errors.put("cellPhone","This's required field ");
//		}
//		if(birth.equals(null)||birth.trim().length()==0){
//			errors.put("birth", "When is your birthday ?");
//		}
//		if(teamName.equals(null)||teamName.trim().length()==0){
//			errors.put("teamName", "Create team name!");
//		}
		
	}
	//呼叫Model

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
