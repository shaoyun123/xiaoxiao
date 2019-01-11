package com.web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.web.service.JiGuangService;
import com.web.thread.JiGuangThread;

public class InitJiGuangMessagerServlet implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext context  = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		JiGuangService jiGuangService = (JiGuangService) context.getBean("jiGuangService");
		/*XiaoXiFaSong x = new XiaoXiFaSong();
		x.setXiaoXiMingCheng("测试名称");
		x.setFaSongLeiXing(1);
		x.setFaSongMuBiao("2017040001");
		x.setFaSongShiJian(new Date());
		x.setShiFouChengGong(0);
		x.setShuJuId(1);
		x.setShuJuLeiXing(3);
		x.setXiaoXiNeiRong("测试内容");
		jiGuangService.insertXiaoXiFaSong(x);*/
		//JiGuangThread jiGuangThread=new JiGuangThread(jiGuangService);
		//jiGuangThread.xiaoXiThread();
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	
	
}
