/**
 * @Title:StatusInterceptor.java
 * @Package:com.web.interceptor
 * @Description:TODO 
 * @Author :libc
 * @date:2018年4月3日 下午5:35:03
 * @Version: V1.0.0
 */
package com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.web.annotation.LoginStatusAnnotation;

/**
 * @类名:StatusInterceptor
 * @描述:身份验证Interceptor
 * @Author: libc
 * @date: 2018年4月3日 下午5:35:03
 */
public class StatusInterceptor extends HandlerInterceptorAdapter  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Class<?> controllerClass = handlerMethod.getMethod().getDeclaringClass();
		LoginStatusAnnotation annotation = controllerClass.getAnnotation(LoginStatusAnnotation.class);
		if (annotation!=null) {
			String status = (String) request.getSession().getAttribute("status");
			if (!annotation.status().equals(status)) {
				response.sendRedirect("logout");
				return false;
			}
		}
		return true;
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
