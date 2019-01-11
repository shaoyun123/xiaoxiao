package com.web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.web.service.YongHuService;
import com.web.thread.EmailThread;

public class InitEmailMessagerServlet implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext context  = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		YongHuService yongHuService = (YongHuService) context.getBean("yongHuService");
		
		//EmailThread emailThread=new EmailThread(yongHuService);
		//emailThread.xiaoXiThread();
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	
	
}
