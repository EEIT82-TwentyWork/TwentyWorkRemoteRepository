package com.iii.twentywork.misc;

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
@WebFilter("/main/*")
public class CheckLoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest)req;
		HttpServletResponse response =(HttpServletResponse)resp;
		HttpSession session =request.getSession();
		
		UsersBean bean=(UsersBean)session.getAttribute("users");
		System.out.println(bean);
		if(bean!=null){
			chain.doFilter(request, response);
		}else{
			String path=request.getContextPath();
			response.sendRedirect(path+"/login/login.jsp");
		}
	}

	@Override
	public void destroy() {
		
	}

}
