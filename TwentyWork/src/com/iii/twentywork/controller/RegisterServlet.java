package com.iii.twentywork.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 接收資料
		// create account
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String cpass = request.getParameter("Confirm Password");
		// Personal Details
		String fullName = request.getParameter("fname");
		String cellPhone = request.getParameter("cellPhone");
		String birth = request.getParameter("birth");
		// Team Details
		String teamName = request.getParameter("teamname");
		String about = request.getParameter("about");
		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		if (email.equals(null) || email.trim().length() == 0) {
			errors.put("username", "Email不可為空白");
		}
		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/login/register").forward(request,
					response);
			return;
		}
		// 呼叫MODEL

		// forward

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
