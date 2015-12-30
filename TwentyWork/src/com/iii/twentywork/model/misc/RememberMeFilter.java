package com.iii.twentywork.model.misc;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

/**
 * Servlet Filter implementation class RememberMeFilter
 */
@WebFilter("/login/login.jsp")
public class RememberMeFilter implements Filter {


    public RememberMeFilter() {}
	public void destroy() {}
	
	FilterConfig fConfig ;
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// **********Remember Me****************
		String cookieName = "";
		String group = "";
		String user = "";
		String password = "";
		String rememberMe = "false";

		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			
			for (int i = 0; i < cookies.length; i++) {
			cookieName = cookies[i].getName();
				if(cookieName.equals("group")){
					group= cookies[i].getValue();
				}else if (cookieName.equals("user")) {
					user = cookies[i].getValue();
				} else if (cookieName.equals("password")) {
					password  = cookies[i].getValue();
				} else if (cookieName.equals("rm")) {
					rememberMe = cookies[i].getValue();
				}
			}
		} 
		session.setAttribute("groupID", group);
		session.setAttribute("userID", user);
		session.setAttribute("password", password);
		session.setAttribute("rememberMe", rememberMe);
		
		
		chain.doFilter(request, response);
	}



}
