package com.iii.twentywork.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.TeamUserBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.user.LoginService;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService loginService;
	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		this.loginService = (LoginService) context.getBean("loginService");
		System.out.println("LoginServlet--1.init-loginService");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	    
	    HttpSession session = req.getSession();
		//接收資料
	            String groupID = req.getParameter("groupID");
				String struid = req.getParameter("userID");
				String password = req.getParameter("password");
				String rm = req.getParameter("rememberMe");
		        String requestURI = (String) session.getAttribute("requestURI");
				System.out.println("LoginServlet--2.接收資料結束");
		//驗證資料
				Map<String, String> errors = new HashMap<String, String>();
				req.setAttribute("errors", errors);
				
				if(groupID==null || groupID.length()==0) {
                    errors.put("groupID", "請輸入groupID");
                }
				if(struid==null || struid.length()==0) {
					errors.put("userID", "請輸入userID");
				}
				if(password==null || password.length()==0) {
					errors.put("password", "請輸入密碼");
				}
				// 如果 errorMsgMap 不是空的，表示有錯誤，交給login.jsp
				if(errors!=null && !errors.isEmpty()) {
					req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
				}
			
				setCookie(req, resp, rm, groupID, struid, password);
				System.out.println("LoginServlet--3.驗證資料結束");
		//資料型態轉換:int轉字串
				int userID = Integer.parseInt(req.getParameter("userID"));
				int int_groupID = Integer.parseInt(req.getParameter("groupID"));
				
		//呼叫Model
				UsersBean bean = loginService.login(userID, password);
				System.out.println("LoginServlet--Line72-login結束");
				TeamUserBean teamUserBean = loginService.loginTeam(userID,int_groupID);
				System.out.println("LoginServlet--Line72-loginTeam結束");
		//根據Model執行結果，呼叫View
				if(bean==null || teamUserBean==null) {
					errors.put("password", "登入失敗，請再試一次");
					req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
					return;
				} else {
				    session.setAttribute("LoginOK", bean);
				    session.setAttribute("teamUserBean", teamUserBean);
				}
		// 依照 Business Logic 運算結果來挑選適當的畫面
		        
				if (requestURI != null && requestURI.length()!=0) {
					// 回到進來前的頁面
					session.removeAttribute("requestURI");
					resp.sendRedirect(requestURI);
					return;
				} else {
					String path = req.getContextPath();
					resp.sendRedirect(path + "/main/workHome/main.jsp");
					return;
				}
			}

	// 記住密碼功能**Remember Me 
	private void setCookie(HttpServletRequest req, HttpServletResponse resp,String rm,String groupID,String userID,String password){
					Cookie cookieGroup = null;
			        Cookie cookieUser = null;
			        Cookie cookiePassword = null;
			        Cookie cookieRememberMe = null;
			        int t;
			        if (rm != null) {   // rm存放瀏覽器送來之RememberMe的選項
			            t = 30*60*60;
			        } else {
			            t=0; // MaxAge==0 表示要請瀏覽器刪除此Cookie
			        }
		            cookieGroup = new Cookie("group", groupID);
			        cookieGroup.setMaxAge(t);
	                cookieGroup.setPath(req.getContextPath());
	                cookieUser = new Cookie("user", userID);
	                cookieUser.setMaxAge(t);
	                cookieUser.setPath(req.getContextPath());
	                // 密碼完全沒加密或編碼
	                cookiePassword = new Cookie("password", password);
	                cookiePassword.setMaxAge(t);
	                cookiePassword.setPath(req.getContextPath());
	                cookieRememberMe = new Cookie("rm", "true");
	                cookieRememberMe.setMaxAge(t);
	                cookieRememberMe.setPath(req.getContextPath());
			        resp.addCookie(cookieGroup);
			        resp.addCookie(cookieUser);
			        resp.addCookie(cookiePassword);
			        resp.addCookie(cookieRememberMe);
	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	public static void main(String[] args) {

	}

}
