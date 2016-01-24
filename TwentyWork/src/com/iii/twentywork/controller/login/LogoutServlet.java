package com.iii.twentywork.controller.login;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
@WebServlet("/com/iii/tewntywork/controller/login/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = 
		WebApplicationContextUtils.getWebApplicationContext(application);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("logout doGet GOGO");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String path=req.getContextPath();
		session.invalidate();	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
