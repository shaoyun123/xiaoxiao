package com.web.controller.web.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.model.KeChengJiBen;
import com.web.model.SheTuanBuMenJiBenXinXin;
import com.web.model.SheTuanBuMenXinXin;
import com.web.model.SheTuanHuoDongXinXi;
import com.web.model.SheTuanJiBenXinXi;
import com.web.model.SheTuanXinXi;
import com.web.model.XueSheng;
import com.web.model.XueShengZuZhiJiBenXinXi;
import com.web.model.XueShengZuZhiXinXi;
import com.web.model.YongHu;
import com.web.service.SheTuanHuoDongService;
import com.web.service.SheTuanService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

@Controller
public class SheTuanController_common {
	static Logger logger = Logger.getLogger(SheTuanController_common.class);
	
	@Autowired
	private YuanXiService yuanXiService;
	
	@Autowired
	private SheTuanService sheTuanService;
	
	@Autowired
	private XueShengService xueShengService;
	
	@Autowired
	private YongHuService yongHuService;
	
	@Autowired
	private SheTuanHuoDongService sheTuanHuoDongService;
	
	@Autowired
	private XueShengChuGuanLiYuanService guanLiYuanService;
	
	@RequestMapping(value = "xueshengzuzhi_c")
	public ModelAndView xueShengZuZhi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String xueshengzuzhiid = request.getParameter("id");
		if (!Util.isNumeric(xueshengzuzhiid)) {
			response.sendRedirect("logout");
			return null;
		}
		Map<String, String> map = new HashMap<>();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		Integer xueXiaoID = null;
		if (Util.isGuanLiYuan(request)) {
			 xueXiaoID = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		}else {
			xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
		}
		map.put("xuexiaoid", String.valueOf(xueXiaoID));
		map.put("xueshengzuzhiid", xueshengzuzhiid);
		XueShengZuZhiJiBenXinXi xi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndXueShengZuZhiID(map);
		if (xi == null) {
			response.sendRedirect("logout");
			return null;
		}
		if (xi.getZhidaojiaoshi()!=null) {
			xi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xi.getZhidaojiaoshi())).getYonghuleixing());
		}
		xi.setChuangjianren(xueShengService.getUserById(Integer.parseInt(xi.getChuangjianren())).getXingming());
		ModelAndView mView = new ModelAndView();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		XueShengZuZhiXinXi xueShengZuZhiXinXi = sheTuanService
				.selectXueShengZuZhiXinXiByXueShengZuZhiIDAndNianDu(xi.getXueshengzuzhiid(), format.format(date));
		if (xueShengZuZhiXinXi == null) {
			mView.setViewName("common/xueshengzuzhi");
			mView.addObject("jibenxinxi", xi);
			return mView;
		}
		List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xi.getXueshengzuzhiid());
		if (sheTuanBuMenJiBenXinXins.isEmpty()) {
			xueShengZuZhiXinXi.setFuzeren(
					xueShengService.getUserById(Integer.parseInt(xueShengZuZhiXinXi.getFuzeren())).getXingming());
			mView.setViewName("common/xueshengzuzhi");
			mView.addObject("jibenxinxi", xi);
			mView.addObject("xinxi", xueShengZuZhiXinXi);
			return mView;
		}
		for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
			SheTuanBuMenXinXin buMenXinXin = sheTuanService
					.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
			buMenXinXin
					.setBuzhang(xueShengService.getUserById(Integer.parseInt(buMenXinXin.getBuzhang())).getXingming());
			buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
			buMenXinXin.setZhize(sheTuanBuMenJiBenXinXin.getZhize());
			buMenXinXins.add(buMenXinXin);
		}
		xueShengZuZhiXinXi.setFuzeren(
				xueShengService.getUserById(Integer.parseInt(xueShengZuZhiXinXi.getFuzeren())).getXingming());
		xueShengZuZhiXinXi.setBumen(buMenXinXins);
		xueShengZuZhiXinXi.setZhidaoren(yongHuService
				.selectYongHuByID(Integer.parseInt(xueShengZuZhiXinXi.getZhidaoren())).getYonghuxingming());
		mView.setViewName("common/xueshengzuzhi");
		mView.addObject("jibenxinxi", xi);
		mView.addObject("xinxi", xueShengZuZhiXinXi);
		return mView;

	}

	// 社团详情
	@RequestMapping(value = "shetuan_c")
	public ModelAndView sheTuan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sheTuanID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanID)) {
			response.sendRedirect("logout");
			return null;
		}
		Map<String, String> map = new HashMap<>();
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		Integer xueXiaoID = null;
		if (Util.isGuanLiYuan(request)) {
			 xueXiaoID = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		}else {
			xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
		}
		map.put("xuexiaoid", String.valueOf(xueXiaoID));
		map.put("shetuanid", sheTuanID);
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndSheTuanID(map);
		if (sheTuanJiBenXinXi == null) {
			response.sendRedirect("logout");
			return null;
		}
		if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
			sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
		}
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		SheTuanXinXi sheTuanXinXi = sheTuanService.selectSheTuanXinXiBySheTuanIDAndNianDu(Integer.parseInt(sheTuanID),
				format.format(date));
		if (sheTuanXinXi == null) {
			ModelAndView mView = new ModelAndView();
			mView.setViewName("common/shetuan");
			mView.addObject("shetuanjibenxinxi", sheTuanJiBenXinXi);
			return mView;
		}
		List<SheTuanBuMenXinXin> buMenXinXins = new ArrayList<>();
		List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = sheTuanService
				.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
		if (sheTuanBuMenJiBenXinXins.isEmpty()) {
			ModelAndView mView = new ModelAndView();
			sheTuanJiBenXinXi.setChuanjianren(
					xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
			sheTuanXinXi.setShezhang(
					xueShengService.getUserById(Integer.parseInt(sheTuanXinXi.getShezhang())).getXingming());
			mView.setViewName("common/shetuan");
			mView.addObject("shetuanxinxi", sheTuanXinXi);
			mView.addObject("shetuanjibenxinxi", sheTuanJiBenXinXi);
			return mView;
		}
		for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
			SheTuanBuMenXinXin buMenXinXin = sheTuanService
					.selectSheTuanBuMenByBuMenIDAndNianDu(sheTuanBuMenJiBenXinXin.getBumenid(), format.format(date));
			if (buMenXinXin.getBuzhang()!=null) {
				buMenXinXin
				.setBuzhang(xueShengService.getUserById(Integer.parseInt(buMenXinXin.getBuzhang())).getXingming());
			}
			buMenXinXin.setMingcheng(sheTuanBuMenJiBenXinXin.getBumenmingcheng());
			buMenXinXin.setZhize(sheTuanBuMenJiBenXinXin.getZhize());
			buMenXinXins.add(buMenXinXin);
		}
		sheTuanJiBenXinXi.setChuanjianren(
				xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
		sheTuanXinXi.setBumen(buMenXinXins);
		sheTuanXinXi
				.setShezhang(xueShengService.getUserById(Integer.parseInt(sheTuanXinXi.getShezhang())).getXingming());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("common/shetuan");
		mView.addObject("shetuanjibenxinxi", sheTuanJiBenXinXi);
		mView.addObject("shetuanxinxi", sheTuanXinXi);
		return mView;
	}
	
	@RequestMapping(value="shetuanjieshao_c")
	public ModelAndView sheTuanJieShao(HttpServletResponse response,HttpServletRequest request) throws IOException {
		ModelAndView mView = new ModelAndView();
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		Integer xueXiaoID = null;
		if (Util.isGuanLiYuan(request)) {
			 xueXiaoID = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		}else {
			xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
		}
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = sheTuanService
				.selectXueShengZuZhiJiBenXinXiByXueXiaoID(xueXiaoID);
		if(xueShengZuZhiJiBenXinXis!=null&&!"".equals(xueShengZuZhiJiBenXinXis)){
			for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
				xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
						.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
				if (xueShengZuZhiJiBenXinXi.getZhidaojiaoshi()!=null) {
					xueShengZuZhiJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xueShengZuZhiJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
				}
				else{
					continue;
				}
			}
		}
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = sheTuanService
				.selectSheTuanJiBenXinXiByXueXiaoID(xueXiaoID);
		if(sheTuanJiBenXinXis!=null&&!"".equals(sheTuanJiBenXinXis)){
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanJiBenXinXi.setChuanjianren(
						xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
				if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
					sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
				}
				else{
					continue;
				}
			}
			
		}
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		mView.addObject("shetuan", sheTuanJiBenXinXis);
		mView.setViewName("common/shetuanjieshao");
		return mView;
	}
	
	@RequestMapping(value="chaxunshetuan_c")
	public ModelAndView chaXunSheTuan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		YongHu user = (YongHu)request.getSession().getAttribute("user");
		Integer xueXiaoID = null;
		if (Util.isGuanLiYuan(request)) {
			 xueXiaoID = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
		}else {
			xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
		}
		String xingJi = request.getParameter("xingji");
		String xingZhi = request.getParameter("xingzhi");
		String mingCheng = request.getParameter("mingcheng");
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		if (xingZhi.equals("shetuan")) {
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid",String.valueOf(xueXiaoID));
			map.put("xingji", xingJi);
			map.put("mingcheng", mingCheng);
			sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
		}
		if (xingZhi.equals("xueshengzuzhi")) {
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid",String.valueOf(xueXiaoID));
			map.put("mingcheng", mingCheng);
			xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
			xingJi = "";
		}
		if (xingZhi.equals("")) {
			if (!mingCheng.equals("")) {
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid",String.valueOf(xueXiaoID));
				map.put("mingcheng", mingCheng);
				map.put("xingji", xingJi);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
				xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
			} else if (!xingJi.equals("")) {
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid",String.valueOf(xueXiaoID));
				map.put("xingji", xingJi);
				map.put("mingcheng", mingCheng);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
			} else {
				xueShengZuZhiJiBenXinXis = sheTuanService
						.selectXueShengZuZhiJiBenXinXiByXueXiaoID(xueXiaoID);
				sheTuanJiBenXinXis = sheTuanService
						.selectSheTuanJiBenXinXiByXueXiaoID(xueXiaoID);
			}
		}
		for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
			xueShengZuZhiJiBenXinXi.setChuangjianren(xueShengService
					.getUserById(Integer.parseInt(xueShengZuZhiJiBenXinXi.getChuangjianren())).getXingming());
			if (xueShengZuZhiJiBenXinXi.getZhidaojiaoshi()!=null) {
				xueShengZuZhiJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(xueShengZuZhiJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
		}
		for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
			sheTuanJiBenXinXi.setChuanjianren(
					xueShengService.getUserById(Integer.parseInt(sheTuanJiBenXinXi.getChuanjianren())).getXingming());
			if (sheTuanJiBenXinXi.getZhidaojiaoshi()!=null) {
				sheTuanJiBenXinXi.setZhidaojiaoshi(yongHuService.selectYongHuByID(Integer.parseInt(sheTuanJiBenXinXi.getZhidaojiaoshi())).getYonghuxingming());
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("xingji", xingJi);
		mView.addObject("xingzhi", xingZhi);
		mView.addObject("mingcheng", mingCheng);
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		mView.addObject("shetuan", sheTuanJiBenXinXis);
		mView.setViewName("common/shetuanjieshao");
		return mView;
	}
	
	@RequestMapping(value = "shetuanhuodong")
	public ModelAndView sheTuanHuoDong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer xueXiaoID = null;
		if(Util.isXueSheng(request)){
			XueSheng user = (XueSheng)request.getSession().getAttribute("user");
			String xueXiaoXueHao[] =user.getXuexiaoXuehao().split("_");
			xueXiaoID = Integer.parseInt(xueXiaoXueHao[0]);
		}else {
			YongHu user = (YongHu)request.getSession().getAttribute("user");
			if (Util.isGuanLiYuan(request)) {
				 xueXiaoID = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			}else {
				xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
			}
		}
		List<SheTuanHuoDongXinXi> sheTuanHuoDongXinXis = null;
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoID(xueXiaoID);
		if (!sheTuanJiBenXinXis.isEmpty()) {
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanHuoDongXinXis = new ArrayList<>();
				sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(sheTuanJiBenXinXi.getShetuanid(),0,5);
				if (!sheTuanHuoDongXinXis.isEmpty()) {
					sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
				}
			}
		}
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoID(xueXiaoID);
		if (!xueShengZuZhiJiBenXinXis.isEmpty()) {
			for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
				sheTuanHuoDongXinXis = new ArrayList<>();
				sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),0,5);
				if (!sheTuanHuoDongXinXis.isEmpty()) {
					xueShengZuZhiJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
					sheTuanHuoDongXinXis.clear();
				}
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("common/shetuanhuodong");
		mView.addObject("shetuan", sheTuanJiBenXinXis);
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		return mView;
	}
	
	@RequestMapping(value = "cxsthd")
	public ModelAndView chaXunSheTuanHuoDong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer xueXiaoID = null;
		if(Util.isXueSheng(request)){
			XueSheng user = (XueSheng)request.getSession().getAttribute("user");
			String xueXiaoXueHao[] =user.getXuexiaoXuehao().split("_");
			xueXiaoID = Integer.parseInt(xueXiaoXueHao[0]);
		}else {
			YongHu user = (YongHu)request.getSession().getAttribute("user");
			if (Util.isGuanLiYuan(request)) {
				 xueXiaoID = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			}else {
				xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
			}
		}
		String xingJi = request.getParameter("xingji");
		String xingZhi = request.getParameter("xingzhi");
		String mingCheng = request.getParameter("mingcheng");
		List<SheTuanJiBenXinXi> sheTuanJiBenXinXis = new ArrayList<>();
		List<XueShengZuZhiJiBenXinXi> xueShengZuZhiJiBenXinXis = new ArrayList<>();
		List<SheTuanHuoDongXinXi> sheTuanHuoDongXinXis = null;
		if (xingZhi.equals("shetuan")) {
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid", String.valueOf(xueXiaoID));
			map.put("xingji", xingJi);
			map.put("mingcheng", mingCheng);
			sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
		}
		if (xingZhi.equals("xueshengzuzhi")) {
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid", String.valueOf(xueXiaoID));
			map.put("mingcheng", mingCheng);
			xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
			xingJi = "";
		}
		if (xingZhi.equals("")) {
			if (!mingCheng.equals("")) {
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid",String.valueOf(xueXiaoID));
				map.put("mingcheng", mingCheng);
				map.put("xingji", xingJi);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
				xueShengZuZhiJiBenXinXis = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueXiaoIDAndMingCheng(map);
			} else if (!xingJi.equals("")) {
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid", String.valueOf(xueXiaoID));
				map.put("xingji", xingJi);
				map.put("mingcheng", mingCheng);
				sheTuanJiBenXinXis = sheTuanService.selectSheTuanJiBenXinXiByXueXiaoIDAndXingJiAndMingCheng(map);
			} else {
				xueShengZuZhiJiBenXinXis = sheTuanService
						.selectXueShengZuZhiJiBenXinXiByXueXiaoID(xueXiaoID);
				sheTuanJiBenXinXis = sheTuanService
						.selectSheTuanJiBenXinXiByXueXiaoID(xueXiaoID);
			}
		}
		if (!xueShengZuZhiJiBenXinXis.isEmpty()) {
			for (XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi : xueShengZuZhiJiBenXinXis) {
				sheTuanHuoDongXinXis = new ArrayList<>();
				sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisByXueShengZuZhiIDAndLimit(xueShengZuZhiJiBenXinXi.getXueshengzuzhiid(),0,5);
				if (!sheTuanHuoDongXinXis.isEmpty()) {
					xueShengZuZhiJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
					sheTuanHuoDongXinXis.clear();
				}
			}
		}
		if (!sheTuanJiBenXinXis.isEmpty()) {
			for (SheTuanJiBenXinXi sheTuanJiBenXinXi : sheTuanJiBenXinXis) {
				sheTuanHuoDongXinXis = new ArrayList<>();
				sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisBySheTuanIDAndLimit(sheTuanJiBenXinXi.getShetuanid(),0,5);
				if (!sheTuanHuoDongXinXis.isEmpty()) {
					sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
				}
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("xingji", xingJi);
		mView.addObject("xingzhi", xingZhi);
		mView.addObject("mingcheng", mingCheng);
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXis);
		mView.addObject("shetuan", sheTuanJiBenXinXis);
		mView.setViewName("common/shetuanhuodong");
		return mView;
	}
	
	@RequestMapping(value="hd")
	public ModelAndView huoDongXiangQing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer xueXiaoID = null;
		if(Util.isXueSheng(request)){
			XueSheng user = (XueSheng)request.getSession().getAttribute("user");
			String xueXiaoXueHao[] =user.getXuexiaoXuehao().split("_");
			xueXiaoID = Integer.parseInt(xueXiaoXueHao[0]);
		}else {
			YongHu user = (YongHu)request.getSession().getAttribute("user");
			if (Util.isGuanLiYuan(request)) {
				 xueXiaoID = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			}else {
				xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
			}
		}
		String sheTuanHuoDongXinXiID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanHuoDongXinXiID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanHuoDongXinXi sheTuanHuoDongXinXi = sheTuanHuoDongService.selectByID(Integer.parseInt(sheTuanHuoDongXinXiID));
		if (sheTuanHuoDongXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		SheTuanJiBenXinXi sheTuanJiBenXinXi = null;
		XueShengZuZhiJiBenXinXi xueShengZuZhiJiBenXinXi = null;
		if (sheTuanHuoDongXinXi.getShetuanid()!=null) {
			sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(sheTuanHuoDongXinXi.getShetuanid());
			if (!sheTuanJiBenXinXi.getXuexiaoid().equals(xueXiaoID)) {
				response.sendRedirect("logout");
				return null;
			}
		}else {
			xueShengZuZhiJiBenXinXi = sheTuanService.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(sheTuanHuoDongXinXi.getXueshengzuzhiid());
			if (!xueShengZuZhiJiBenXinXi.getXuexiaoid().equals(xueXiaoID)) {
				response.sendRedirect("logout");
				return null;
			}
		}
		mView.setViewName("common/shetuanhuodongxinxi");
		mView.addObject("shetuan", sheTuanJiBenXinXi);
		mView.addObject("xueshengzuzhi", xueShengZuZhiJiBenXinXi);
		mView.addObject("huodong", sheTuanHuoDongXinXi);
		return mView;
	}
	
	@RequestMapping(value="smore")
	public ModelAndView sheTuanHuoDongMore(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer xueXiaoID = null;
		if(Util.isXueSheng(request)){
			XueSheng user = (XueSheng)request.getSession().getAttribute("user");
			String xueXiaoXueHao[] =user.getXuexiaoXuehao().split("_");
			xueXiaoID = Integer.parseInt(xueXiaoXueHao[0]);
		}else {
			YongHu user = (YongHu)request.getSession().getAttribute("user");
			if (Util.isGuanLiYuan(request)) {
				 xueXiaoID = guanLiYuanService.selectByID(user.getYonghuid()).getXuexiaoid();
			}else {
				xueXiaoID = yuanXiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
			}
		}
		String sheTuanID = request.getParameter("id");
		if (!Util.isNumeric(sheTuanID)) {
			response.sendRedirect("logout");
			return null;
		}
		SheTuanJiBenXinXi sheTuanJiBenXinXi = sheTuanService.selectSheTuanJiBenXinXiBySheTuanID(Integer.parseInt(sheTuanID));
		if (sheTuanJiBenXinXi==null) {
			response.sendRedirect("logout");
			return null;
		}
		if (!sheTuanJiBenXinXi.getXuexiaoid().equals(xueXiaoID)) {
			response.sendRedirect("logout");
			return null;
		}
		List<SheTuanHuoDongXinXi> sheTuanHuoDongXinXis = new ArrayList<>();
		sheTuanHuoDongXinXis = sheTuanHuoDongService.selectSheTuanHuoDongXinXisBySheTuanID(Integer.parseInt(sheTuanID));
		logger.info(sheTuanHuoDongXinXis.size());
		if (!sheTuanHuoDongXinXis.isEmpty()) {
			sheTuanJiBenXinXi.setHuodong(sheTuanHuoDongXinXis);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("common/morehuodongxinxi");
		mView.addObject("shetuan", sheTuanJiBenXinXi);
		return mView;
	}
}
