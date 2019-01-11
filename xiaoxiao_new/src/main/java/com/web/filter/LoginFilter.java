package com.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginFilter implements Filter{

	/**  
	* 需要排除的页面  
	*/    
	private String excludedPages;    
	    
	private String[] excludedPageArray; 
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludedPages = filterConfig.getInitParameter("excludedPages");
		excludedPageArray = excludedPages.split(",");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		boolean isIn = false;
		for (String page : excludedPageArray) {
			if (req.getServletPath().equals(page)||req.getServletPath().startsWith("/static")||req.getServletPath().startsWith("/druid")||req.getServletPath().startsWith("/system")) {
				isIn = true;
				break;
			}else if(req.getServletPath().startsWith("/app_")){
				isIn = true;
				req.getSession().invalidate();
				break;
			}
		}
		if (isIn) {
			chain.doFilter(request, response);
		}else {
			if (session.getAttribute("user") != null||session.getAttribute("user")=="") {
	    		chain.doFilter(request, response);
				return;
			}else {
				res.sendRedirect("login");
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
