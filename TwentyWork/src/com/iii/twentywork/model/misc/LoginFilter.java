package com.iii.twentywork.model.misc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.iii.twentywork.model.bean.UsersBean;

@WebFilter(
		urlPatterns={"/*"},
		initParams= {
		        @WebInitParam(name="url_1" , value="/ShareFile/*"),
		        @WebInitParam(name="url_2" , value="/main/*")}
)
public class LoginFilter implements Filter {
	private Collection<String> url = new ArrayList<String>();
    
    
	private FilterConfig fConfig;
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig=fConfig;
		
		Enumeration<String> e = fConfig.getInitParameterNames();
		while(e.hasMoreElements()) {
		    String name = e.nextElement();
		    url.add(fConfig.getInitParameter(name));
		}
	}

    public LoginFilter() { }

	public void destroy() {	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();
		if(mustLogin(servletPath)) {
    		UsersBean bean = (UsersBean) session.getAttribute("LoginOK");
    		if(bean==null) {//還沒Login
    			String uri = request.getRequestURI();
    			session.setAttribute("requestURI", uri);
    			String path = request.getContextPath();
    			response.sendRedirect(path+"/login/login.controller");
    		}else
    		{
    			chain.doFilter(request, response);
    		}
		}else {
		    chain.doFilter(request, response);
		}
	}
	
	private boolean mustLogin(String servletPath) {
	    boolean login = false;
	    for (String sURL : url) {
	        if(sURL.endsWith("*")) {
	            sURL = sURL.substring(0, sURL.length()-1);
	            if(servletPath.startsWith(sURL)) {
	                login = true;
	                break;
	            }
	        }else {
	            if(servletPath.equals(sURL)) {
	                login = true;
                    break;  
	            }
	        }
	    }
	    return login;
	}
	
	


}
