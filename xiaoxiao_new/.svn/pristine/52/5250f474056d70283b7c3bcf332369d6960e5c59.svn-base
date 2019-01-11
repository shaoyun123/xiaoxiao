package com.web.controller.web.fudaoyuan;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.FuDaoYuan;
import com.web.model.SheTuanChuangJian;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.FuDaoYuanService;
import com.web.service.SheTuanService;
import com.web.service.XueShengService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="fudaoyuan")
public class SheTuanController_fdy {
	static Logger logger = Logger.getLogger(SheTuanController_fdy.class);
	
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	
	@Autowired
	private SheTuanService sheTuanService;
	
	@Autowired
	private XueShengService xueShengService;
	
	@RequestMapping(value="xszzcjsq")
	public ModelAndView xueShengZuZhiChuangJianShenQing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (fuDaoYuan.getBanjiid()==null||fuDaoYuan.getBanjiid().equals("")){
			ModelAndView mView = new ModelAndView();
			mView.setViewName("fudaoyuan/xueshengzuzhichuangjian");
			return mView;
		}
		List<SheTuanChuangJian> sheTuanChuangJians = sheTuanService.selectSheTuanChuangJianByBanJiIDs(fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length()-1));
		if (!sheTuanChuangJians.isEmpty()) {
			for (SheTuanChuangJian sheTuanChuangJian : sheTuanChuangJians) {
				sheTuanChuangJian.setChuangjianrenxingming(xueShengService.getUserById(sheTuanChuangJian.getChuangjianren()).getXingming());
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/xueshengzuzhichuangjian");
		mView.addObject("chuangjian", sheTuanChuangJians);
		return mView;
	}
	
	@RequestMapping(value="xszzcjDetail")
	public ModelAndView xueShengZuZhiChuangJianDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String chuangJianID = request.getParameter("id");
		if (!Util.isNumeric(chuangJianID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanChuangJian sheTuanChuangJian = sheTuanService.selectByID(Integer.parseInt(chuangJianID));
		if (sheTuanChuangJian==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng =xueShengService.getUserById(sheTuanChuangJian.getChuangjianren());
		String banJiIDs[]=fuDaoYuan.getBanjiid().split(",");
		String banJiID = String.valueOf(xueSheng.getBanjiid());
		boolean isIn =false;
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiID.equals(banJiIDs[i])) {
				isIn = true;
			}else {
				continue;
			}
		}
		if (isIn==false) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanChuangJian.setChuangjianrenxingming(xueSheng.getXingming());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/xszzchuangjianxiangqing");
		mView.addObject("chuangjian", sheTuanChuangJian);
		return mView;
	}
	
	@RequestMapping(value="xxzzsqty_fdy")
	@ResponseBody
	public String acceptXueShengZuZhiChuangJian(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String chuangJianID = request.getParameter("CODE");
		if (!Util.isNumeric(chuangJianID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanChuangJian sheTuanChuangJian = sheTuanService.selectByID(Integer.parseInt(chuangJianID));
		if (sheTuanChuangJian==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanChuangJian.getZhuangtai()!=0) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng =xueShengService.getUserById(sheTuanChuangJian.getChuangjianren());
		String banJiIDs[]=fuDaoYuan.getBanjiid().split(",");
		String banJiID = String.valueOf(xueSheng.getBanjiid());
		boolean isIn =false;
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiID.equals(banJiIDs[i])) {
				isIn = true;
			}else {
				continue;
			}
		}
		if (isIn==false) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanChuangJian.setZhuangtai(1);
		String ret = "";
		int i = sheTuanService.updateSheTuanChuangJianBySheTuanChuangJian(sheTuanChuangJian);
		if (i>0) {
			ret= "success";
		}else {
			ret = "fail";
		}
		return ret;
	}
	
	@RequestMapping(value="xszzsqjj_fdy")
	@ResponseBody
	public String denyXueShengZuZhiChuangJian(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String chuangJianID = request.getParameter("CODE");
		if (!Util.isNumeric(chuangJianID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanChuangJian sheTuanChuangJian = sheTuanService.selectByID(Integer.parseInt(chuangJianID));
		if (sheTuanChuangJian==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanChuangJian.getZhuangtai()!=0) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng =xueShengService.getUserById(sheTuanChuangJian.getChuangjianren());
		String banJiIDs[]=fuDaoYuan.getBanjiid().split(",");
		String banJiID = String.valueOf(xueSheng.getBanjiid());
		boolean isIn =false;
		for (int i = 0; i < banJiIDs.length; i++) {
			if (banJiID.equals(banJiIDs[i])) {
				isIn = true;
			}else {
				continue;
			}
		}
		if (isIn==false) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanChuangJian.setZhuangtai(3);
		String ret = "";
		int i = sheTuanService.updateSheTuanChuangJianBySheTuanChuangJian(sheTuanChuangJian);
		if (i>0) {
			ret= "success";
		}else {
			ret = "fail";
		}
		return ret;
	}
}
