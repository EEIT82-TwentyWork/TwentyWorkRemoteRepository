package com.iii.twentywork.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.misc.JMailBody;
import com.iii.twentywork.model.service.invite.JMail;

@WebServlet("/login/invite.controller")
public class inviteServlet extends HttpServlet {
	private JMail jMail;
	private JMailBody jMailBody;

	@Override
	public void init() throws ServletException {

		ServletContext application = this.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		this.jMail = (JMail) context.getBean("jMail");
		this.jMailBody = (JMailBody) context.getBean("jMailBody");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		// 接收資料
		String count = request.getParameter("count");
		int countServlet = Integer.parseInt(count);
		// ----------陣列一定要先NEW [] 長度-----------------------------------------
		String[] emails = new String[countServlet];
		// -------------------------------------------------------------------------
		for (int i = 0; i < countServlet; i++) {
			emails[i] = request.getParameter("inviteEmail" + (i + 1));
			System.out.println(emails[i]);
		}
		// 驗證資料
		Map<String, String> emailerrors = new HashMap<String, String>();
		request.setAttribute("eamilerrors", emailerrors);

		if (emails.equals(null) || emails.length == 0) {
			emailerrors.put("emtpy", "請輸入EMail");
		}
		if (emailerrors != null && !emailerrors.isEmpty()) {
			request.getRequestDispatcher("/login/invite.jsp").forward(request, response);
		}
		// 呼叫Model---------------------------------------------------------
		UsersBean userInfo = (UsersBean) session.getAttribute("UserInfo");
		String teanName = userInfo.getTeams().iterator().next().getTeamName();
		
		
		try {
			jMail.generateAndSendEmail(emails, teanName);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/login/welcome.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
