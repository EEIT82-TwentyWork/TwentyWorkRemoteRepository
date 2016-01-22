package com.iii.twentywork.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.service.user.LoginAbstractService;

/**
 * @author
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginAbstractService loginAbstractService;

	@Override
	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		this.loginAbstractService = (LoginAbstractService) context.getBean("loginAbstractService");
		System.out.println("init GOGO");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet GOGO");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 接收資料
		String teamName = request.getParameter("TeanName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rm = request.getParameter("rememberMe");
		String requestURI = (String) session.getAttribute("requestURI");
		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		if (teamName == null || teamName.length() == 0) {
			errors.put("groupID", "請輸入groupID");
		}
		if (teamName == null || teamName.length() == 0) {
			errors.put("userID", "請輸入userID");
		}
		if (password == null || password.length() == 0) {
			errors.put("password", "請輸入密碼");
		}
		// 如果 errorMsgMap 不是空的，表示有錯誤，交給login.jsp
		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
		setCookie(request, response, rm, teamName, email, password);
		// 呼叫Model
		System.out.println("呼叫Service");
		UsersBean userinfo = loginAbstractService.loginUserInfoCheck(teamName, email, password);
		String userName=userinfo.getUserName();
		String userPhone = userinfo.getPhone();
		System.out.println("回傳"+userinfo);
		// 根據Model執行結果，呼叫View
		if (userinfo == null) {
			errors.put("password", "登入失敗，請再試一次");
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			return;
		} else {
			session.setAttribute("LoginOK", userinfo);
			Set<TeamBean> temp = userinfo.getTeams();
			TeamBean teambean =null;
			for(TeamBean e:temp) {
			    if(e.getTeamName().equals(teamName)) {
			        teambean=e;
			        break;
			    }
			}
			session.setAttribute("teamBean", teambean);
		}
		// 依照 Business Logic 運算結果來挑選適當的畫面
		if (requestURI != null && requestURI.length() != 0) {
			// 回到進來前的頁面
			session.removeAttribute("requestURI");
			response.sendRedirect(requestURI);
			return;
		} else {
			String path = request.getContextPath();
			session.setAttribute("UserInfo", userinfo);
			session.setAttribute("userName", userName);
			session.setAttribute("userPhone", userPhone);
			
			response.sendRedirect(path + "/main/workHome/main.jsp");
			return;
		}
	}

	// 記住密碼功能**Remember Me
	private void setCookie(HttpServletRequest req, HttpServletResponse resp, String rm, String groupID, String userID,
			String password) {
		Cookie cookieGroup = null;
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		int t;
		if (rm != null) { // rm存放瀏覽器送來之RememberMe的選項
			t = 30 * 60 * 60;
		} else {
			t = 0; // MaxAge==0 表示要請瀏覽器刪除此Cookie
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
