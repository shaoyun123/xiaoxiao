package com.web.controller.web.guanliyuan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.web.model.KeChengJiBen;
import com.web.model.SheTuanChuangJian;
import com.web.model.SheTuanJieSan;
import com.web.model.SheTuanJingFei;
import com.web.model.SheTuanXinXi;
import com.web.model.TiXing;
import com.web.model.XueSheng;
import com.web.model.XueShengChuGuanLiYuan;

import com.web.model.XueShengZuZhiXinXi;
import com.web.model.YiJianXiang;
import com.web.model.YongHu;
import com.web.service.SheTuanService;
import com.web.service.TiXingService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="guanliyuan")
public class SheTuanController_gly {
	static Logger logger = Logger.getLogger(SheTuanController_gly.class);
	
	@Autowired
	private XueShengChuGuanLiYuanService guanLiYuanService;
	
	@Autowired
	private SheTuanService sheTuanService;
	
	@Autowired
	private XueShengService xueShengService;
	
	@Autowired
	private TiXingService tiXingService;
	
	@Autowired
	private YongHuService yongHuService;
	
	@RequestMapping(value="stcjsq")
	public ModelAndView sheTuanChuangJianShenQing(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			logger.info("没有管理员信息"+user.getYonghuid());
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanChuangJian> sheTuanChuangJians = sheTuanService.selectSheTuanChuangJianByXueXiaoID(guanLiYuan.getXuexiaoid());
		if (!sheTuanChuangJians.isEmpty()) {
			for (SheTuanChuangJian sheTuanChuangJian : sheTuanChuangJians) {
				sheTuanChuangJian.setChuangjianrenxingming(xueShengService.getUserById(sheTuanChuangJian.getChuangjianren()).getXingming());
			}
		}
		int count =sheTuanChuangJians.size();
		int pageSize = 10;
		int page=1;
		int pages = (count / pageSize) + 1;
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<SheTuanChuangJian> sheTuanChuangJians2 =new ArrayList<>();
			if(count<10){
				for(int i=0;i<count;i++){
					sheTuanChuangJians2.add(sheTuanChuangJians.get(i));
				}
				mView.addObject("chuangjian", sheTuanChuangJians2);
			}
			else{
				for(int i=0;i<10;i++){
					sheTuanChuangJians2.add(sheTuanChuangJians.get(i));
				}
				mView.addObject("chuangjian", sheTuanChuangJians2);
			}
		}
		else{
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<SheTuanChuangJian> sheTuanChuangJians2 =new ArrayList<>();
						if(count<10){
							for(int i=(page-1)*10;i<count;i++){
								sheTuanChuangJians2.add(sheTuanChuangJians.get(i));
							}
							mView.addObject("chuangjian", sheTuanChuangJians2);
						}
						else {
							for(int i=(page-1)*10;i<(page*10);i++){
								sheTuanChuangJians2.add(sheTuanChuangJians.get(i));
							}
							mView.addObject("chuangjian", sheTuanChuangJians2);
						}
				}
				else if (page == pages) {
					List<SheTuanChuangJian> sheTuanChuangJians2 =new ArrayList<>();
					for(int i=(page-1)*10;i<count;i++){
						sheTuanChuangJians2.add(sheTuanChuangJians.get(i));
					}
					mView.addObject("chuangjian", sheTuanChuangJians2);
				}
				else{
					response.sendRedirect("logout");
					return null;
				}
			}
			else{
				response.sendRedirect("logout");
				return null;
			}
		}
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.setViewName("guanliyuan/shetuanchuangjian");
		return mView;
	}
	
	@RequestMapping(value="stcjDetail")
	public ModelAndView sheTuanChuangJianDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
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
		if (!sheTuanChuangJian.getXuexiaoid().equals(guanLiYuan.getXuexiaoid())) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanChuangJian.setChuangjianrenxingming(xueShengService.getUserById(sheTuanChuangJian.getChuangjianren()).getXingming());
		sheTuanChuangJian.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanChuangJian.getZhidaojiaoshi())).getYonghuxingming());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("guanliyuan/chuangjianxiangqing");
		mView.addObject("chuangjian", sheTuanChuangJian);
		return mView;
	}
	
	@RequestMapping(value="sqty_gly")
	@ResponseBody
	public String acceptSheTuanChuangJian(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
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
		if (!sheTuanChuangJian.getXuexiaoid().equals(guanLiYuan.getXuexiaoid())) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanChuangJian.getLeixing()==false) {
			if (sheTuanChuangJian.getZhuangtai()!=0) {
				response.sendRedirect("logout");
				return null;
			}
			boolean b = sheTuanService.tongYiChuangJianSheTuan(sheTuanChuangJian);
			if (b) {
				return "success";
			}
		}else {
			if (sheTuanChuangJian.getZhuangtai()!=1) {
				response.sendRedirect("logout");
				return null;
			}
			boolean b =sheTuanService.tongYiChuangJianXueShengZuZhi(sheTuanChuangJian);
			if (b) {
				return "success";
			}
		}
		return null;
	}
	
	@RequestMapping(value="sqjj_gly")
	@ResponseBody
	public String denySheTuanChuangJian(HttpServletResponse response,HttpServletRequest request) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
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
		if (!sheTuanChuangJian.getXuexiaoid().equals(guanLiYuan.getXuexiaoid())) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanChuangJian.setZhuangtai(3);
		sheTuanChuangJian.setShenheren(user.getYonghuid());
		String ret ="";
		int i = sheTuanService.updateSheTuanChuangJianBySheTuanChuangJian(sheTuanChuangJian);
		if (i>0) {
			ret = "success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="jfsqcl")
	public ModelAndView JingFeiShengQingChuLi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanJingFei> sheTuanJingFeis = new ArrayList<>();
		sheTuanJingFeis = sheTuanService.selectByXueXiaoID(guanLiYuan.getXuexiaoid());
		if (!sheTuanJingFeis.isEmpty()) {
			SheTuanXinXi sheTuanXinXi = null;
			XueShengZuZhiXinXi xueShengZuZhiXinXi = null;
			for (SheTuanJingFei sheTuanJingFei : sheTuanJingFeis) {
				if (sheTuanJingFei.getShetuanxinxiid()!=null) {
					sheTuanXinXi = new SheTuanXinXi();
					sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanXinXiID(sheTuanJingFei.getShetuanxinxiid());
					sheTuanJingFei.setMingcheng(sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid()).getMingcheng());
				}else {
					xueShengZuZhiXinXi = new XueShengZuZhiXinXi();
					xueShengZuZhiXinXi = sheTuanService.selectByXueShengZuZhiXinXiID(sheTuanJingFei.getXueshengzuzhixinxiid());
					sheTuanJingFei.setMingcheng(sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid()).getMingcheng());
				}
			}
		}
		int count =sheTuanJingFeis.size();
		int pageSize = 10;
		int page=1;
		int pages = (count / pageSize) + 1;
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<SheTuanJingFei> sheTuanJingFeis2 =new ArrayList<>();
			if(count<10){
				for(int i=0;i<count;i++){
					sheTuanJingFeis2.add(sheTuanJingFeis.get(i));
				}
				mView.addObject("jingfei", sheTuanJingFeis2);
			}
			else{
				for(int i=0;i<10;i++){
					sheTuanJingFeis2.add(sheTuanJingFeis.get(i));
				}
				mView.addObject("jingfei", sheTuanJingFeis2);
			}
		}
		else{
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<SheTuanJingFei> sheTuanJingFeis2 =new ArrayList<>();
						if(count<10){
							for(int i=(page-1)*10;i<count;i++){
								sheTuanJingFeis2.add(sheTuanJingFeis.get(i));
							}
							mView.addObject("jingfei", sheTuanJingFeis2);
						}
						else {
							for(int i=(page-1)*10;i<(page*10);i++){
								sheTuanJingFeis2.add(sheTuanJingFeis.get(i));
							}
							mView.addObject("jingfei", sheTuanJingFeis2);
						}
				}
				else if (page == pages) {
					List<SheTuanJingFei> sheTuanJingFeis2 =new ArrayList<>();
					for(int i=(page-1)*10;i<count;i++){
						sheTuanJingFeis2.add(sheTuanJingFeis.get(i));
					}
					mView.addObject("jingfei", sheTuanJingFeis2);
				}
				else{
					response.sendRedirect("logout");
					return null;
				}
			}
			else{
				response.sendRedirect("logout");
				return null;
			}
		}
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.setViewName("guanliyuan/jingfeichuli");
		return mView;
	}
	
	@RequestMapping(value="passjingfei")
	@ResponseBody
	public String PassJingFeiShenQing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String jingFeiID = request.getParameter("CODE");
		if (!Util.isNumeric(jingFeiID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJingFei sheTuanJingFei = sheTuanService.selectByJingFeiID(Integer.parseInt(jingFeiID));
		if (sheTuanJingFei==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanJingFei.getShifoupizhun()!=0) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(sheTuanJingFei.getShenqingren());
		String xueXiaoXueHao[] = xueSheng.getXuexiaoXuehao().split("_");
		if (!guanLiYuan.getXuexiaoid().equals(Integer.parseInt(xueXiaoXueHao[0]))) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanJingFei.setShifoupizhun(1);
		sheTuanJingFei.setPizhunshijian(new Date());
		sheTuanJingFei.setPizhunren(user.getYonghuid());
		int i = sheTuanService.updateSheTuanJingFei(sheTuanJingFei);
		if (i>0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value="denyjingfei")
	@ResponseBody
	public String DenyJingFeiShenQing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String jingFeiID = request.getParameter("CODE");
		if (!Util.isNumeric(jingFeiID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJingFei sheTuanJingFei = sheTuanService.selectByJingFeiID(Integer.parseInt(jingFeiID));
		if (sheTuanJingFei==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanJingFei.getShifoupizhun()!=0) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(sheTuanJingFei.getShenqingren());
		String xueXiaoXueHao[] = xueSheng.getXuexiaoXuehao().split("_");
		if (!guanLiYuan.getXuexiaoid().equals(Integer.parseInt(xueXiaoXueHao[0]))) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanJingFei.setShifoupizhun(2);
		sheTuanJingFei.setPizhunshijian(new Date());
		sheTuanJingFei.setPizhunren(user.getYonghuid());
		int i = sheTuanService.updateSheTuanJingFei(sheTuanJingFei);
		if (i>0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value="jscl")
	public ModelAndView SheTuanJieSanChuLi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanJieSan> sheTuanJieSans = new ArrayList<>();
		sheTuanJieSans = sheTuanService.selectSheTuanJieSanByXueXiaoID(guanLiYuan.getXuexiaoid());
		if (!sheTuanJieSans.isEmpty()) {
			for (SheTuanJieSan sheTuanJieSan : sheTuanJieSans) {
				if (sheTuanJieSan.getShetuanid()!=null) {
					sheTuanJieSan.setMingcheng(sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanJieSan.getShetuanid()).getMingcheng());
				}else {
					sheTuanJieSan.setMingcheng(sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(sheTuanJieSan.getXueshengzuzhiid()).getMingcheng());
				}
			}
		}
		int count =sheTuanJieSans.size();
		int pageSize = 10;
		int page=1;
		int pages = (count / pageSize) + 1;
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<SheTuanJieSan> sheTuanJieSans2=new ArrayList<>();
			if(count<10){
				for(int i=0;i<count;i++){
					sheTuanJieSans2.add(sheTuanJieSans.get(i));
				}
				mView.addObject("jiesan", sheTuanJieSans2);
			}
			else{
				for(int i=0;i<10;i++){
					sheTuanJieSans2.add(sheTuanJieSans.get(i));
				}
				mView.addObject("jiesan", sheTuanJieSans2);
			}
		}
		else{
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<SheTuanJieSan> sheTuanJieSans2=new ArrayList<>();
						if(count<10){
							for(int i=(page-1)*10;i<count;i++){
								sheTuanJieSans2.add(sheTuanJieSans.get(i));
							}
							mView.addObject("jiesan", sheTuanJieSans2);
						}
						else {
							for(int i=(page-1)*10;i<(page*10);i++){
								sheTuanJieSans2.add(sheTuanJieSans.get(i));
							}
							mView.addObject("jiesan", sheTuanJieSans2);
						}
				}
				else if (page == pages) {
					List<SheTuanJieSan> sheTuanJieSans2=new ArrayList<>();
					for(int i=(page-1)*10;i<count;i++){
						sheTuanJieSans2.add(sheTuanJieSans.get(i));
					}
					mView.addObject("jiesan", sheTuanJieSans2);
				}
				else{
					response.sendRedirect("logout");
					return null;
				}
			}
			else{
				response.sendRedirect("logout");
				return null;
			}
		}
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.setViewName("guanliyuan/shetuanjiesan");
		return mView;
	}
	
	@RequestMapping(value="passjiesan")
	@ResponseBody
	public String PassJieSan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String jieSanID = request.getParameter("CODE");
		if (!Util.isNumeric(jieSanID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJieSan sheTuanJieSan = sheTuanService.selectSheTuanJieSanByID(Integer.parseInt(jieSanID));
		if (sheTuanJieSan==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanJieSan.getShifoupizhun()!=0) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(sheTuanJieSan.getFuzeren());
		String xueXiaoXueHao[] = xueSheng.getXuexiaoXuehao().split("_");
		if (!guanLiYuan.getXuexiaoid().equals(Integer.parseInt(xueXiaoXueHao[0]))) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanJieSan.setShifoupizhun(1);
		sheTuanJieSan.setShenheshijian(new Date());
		sheTuanJieSan.setPizhunren(user.getYonghuid());
		boolean b = sheTuanService.tongYiJieSan(sheTuanJieSan);
		if (b==true) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value="denyjiesan")
	@ResponseBody
	public String DenyJieSan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		XueShengChuGuanLiYuan guanLiYuan = guanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String jieSanID = request.getParameter("CODE");
		if (!Util.isNumeric(jieSanID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJieSan sheTuanJieSan = sheTuanService.selectSheTuanJieSanByID(Integer.parseInt(jieSanID));
		if (sheTuanJieSan==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanJieSan.getShifoupizhun()!=0) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(sheTuanJieSan.getFuzeren());
		String xueXiaoXueHao[] = xueSheng.getXuexiaoXuehao().split("_");
		if (!guanLiYuan.getXuexiaoid().equals(Integer.parseInt(xueXiaoXueHao[0]))) {
			response.sendRedirect("logout");
			return null;
		}
		sheTuanJieSan.setShifoupizhun(2);
		sheTuanJieSan.setShenheshijian(new Date());
		sheTuanJieSan.setPizhunren(user.getYonghuid());
		int i = sheTuanService.updateSheTuanJieSan(sheTuanJieSan);
		if (i>0) {
			TiXing tiXing = new TiXing();
			tiXing.setJieshourenid(sheTuanJieSan.getFuzeren());
			tiXing.setNeirong("你的"+sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanJieSan.getShetuanid()).getMingcheng()+"社团解散申请已被拒绝");
			tiXing.setShijian(new Date());
			tiXing.setZhuangtai(0);
			int k = tiXingService.insert(tiXing);
			if (k>0) {
				return "success";
			}else {
				return "fail";
			}
		}else {
			return "fail";
		}
	}
}
