package com.iii.twentywork.model.misc;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iii.twentywork.model.bean.users.UsersBean;

@WebFilter(
		urlPatterns={"/ShareFile/*"}
)
public class LoginFilter implements Filter {
	
	private FilterConfig fConfig;
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
	}

    public LoginFilter() { }

	public void destroy() {	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		UsersBean bean = (UsersBean) session.getAttribute("LoginOK");
		if(bean==null) {//還沒Login
			String uri = request.getRequestURI();
			session.setAttribute("requestURI", uri);
			String path = request.getContextPath();
			response.sendRedirect(path+"/login/login.jsp");
		}else
		{
			chain.doFilter(request, response);
		}
	}


}
