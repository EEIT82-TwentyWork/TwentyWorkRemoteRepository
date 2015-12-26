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

import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.user.LoginService;

public class LoginServlet extends HttpServlet {
	private LoginService loginService;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		this.loginService = (LoginService) context.getBean("loginService");
		System.out.println("1.init-loginService");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//接收資料
				int userID = Integer.parseInt(req.getParameter("userID"));
				String password = req.getParameter("password");
				System.out.println("2.接收資料結束");
		//驗證資料
				Map<String, String> errors = new HashMap<String, String>();
				req.setAttribute("errors", errors);
				//int轉字串
				String struid = req.getParameter("userID");
				
				if(struid==null || struid.length()==0) {
					errors.put("userID", "請輸入userID");
				}
				if(password==null || password.length()==0) {
					errors.put("password", "請輸入密碼");
				}
				if(errors!=null && !errors.isEmpty()) {
					req.getRequestDispatcher(
							"/login/login.jsp").forward(req, resp);
					return;
				}
				System.out.println("3.驗證資料結束");
				
		//呼叫Model
				UsersBean bean = loginService.login(userID, password);
				System.out.println("呼叫Model結束");
		//根據Model執行結果，呼叫View
				if(bean==null) {
					errors.put("password", "登入失敗，請再試一次");
					req.getRequestDispatcher(
							"/login/login.jsp").forward(req, resp);
				} else {
					HttpSession session = req.getSession();
					session.setAttribute("users", bean);

					String path = req.getContextPath();
					resp.sendRedirect(path+"/index.jsp");
				}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	public static void main(String[] args) {

	}

}
