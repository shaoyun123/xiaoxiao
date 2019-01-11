package com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.web.model.MemoryData;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.util.Util;

public class SingleUserInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String sessionid ="";
		if (Util.isXueSheng(request)) {
			XueSheng xueSheng = (XueSheng) request.getSession().getAttribute("user");
			sessionid = MemoryData.getSessionIDMap().get(xueSheng.getXueshengid().toString());
		}else {
			YongHu yongHu = (YongHu) request.getSession().getAttribute("user");
			sessionid = MemoryData.getSessionIDMap().get(yongHu.getYonghuid().toString());
		}
		if (request.getRequestedSessionId()==null||sessionid==null) {
			response.sendRedirect("logout");
			return false;
		}
		if (sessionid.equals(request.getRequestedSessionId())) {
			return true;
		}else {
			//如果请求的sessionID和此账号Map中存放的sessionID不一致，跳转到登陆页
			response.sendRedirect("logout");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
