package com.web.controller.app.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.controller.base.BaseController;
import com.web.model.BanBen;
import com.web.service.BanBenService;
import com.web.util.Tools;

@Controller
public class AppBanBenController extends BaseController{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private BanBenService banBenService;

	// 接受前端传递的用户名、密码、身份等参数，在数据库中查询，根据结果给前端返回相应的retInfo
	@RequestMapping(value = "app_BanBen")
	@ResponseBody
	public void app_BanBen(HttpServletRequest request) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		String appType = request.getParameter("appType");
		Map<String,Object> param = new HashMap<String,Object>();
		if(Tools.isEmpty(appType)){
			appType = "android";
		}
		param.put("appType", appType);
		String retInfo = "fail";
		try {
			BanBen banBen = banBenService.getZuiXinBanBenXinXi(param);
			returnMap.put("data", banBen);
			retInfo = "";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		returnMap.put("retInfo", retInfo);
		renderJson(returnMap);
		
	}
	
	
	@RequestMapping(value = "/app_downLoadApk")
	public ModelAndView app_downLoadApk(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		
			mv.setViewName("login/login");
		
		return mv;
	}
}
