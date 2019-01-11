package com.web.controller.web.fudaoyuan;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.web.model.Qingjia;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.service.BanJiService;
import com.web.service.FuDaoYuanService;
import com.web.service.QingjiaService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="fudaoyuan")
public class QingJiaController {
	static Logger logger = Logger.getLogger(QingJiaController.class);
	
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	
	@Autowired
	private QingjiaService qingjiaService;
	
	@Autowired
	private XueShengService xueShengService;
	
	@Autowired
	private YuanXiService yuanXiService;
	
	@Autowired
	private BanJiService banJiService;
	
	@Autowired
	private YongHuService yongHuService;
	
	@RequestMapping(value="qingjiadaichuli_fdy")
	public ModelAndView qingJiaDaiChuLi(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		int pageSize = 10;
		int pages= 1;
		int page=1;
		List<Qingjia> qingjias = new ArrayList<>();
		if (fuDaoYuan.getBanjiid()!=null&&!fuDaoYuan.getBanjiid().equals("")) {
			String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length()-1);
			int count = qingjiaService.selectCountFuDaoYuanDaiChuLiByBanJiIDs(banJiIDs);
			logger.debug("总条数："+count);
			pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				Map<String, String> map = new HashMap<>();
				map.put("banjiids", banJiIDs);
				map.put("start", "0");
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectFuDaoYuanDaiChuLiByBanJiID(map);
			}else {
				if (!Util.isNumeric(request.getParameter("page"))) {
					response.sendRedirect("logout");
					return null;
				}
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page <= pages){
					Map<String, String> map = new HashMap<>();
					map.put("banjiids", banJiIDs);
					map.put("start", String.valueOf((page - 1) * 10));
					map.put("stop", String.valueOf(pageSize));
					qingjias = qingjiaService.selectFuDaoYuanDaiChuLiByBanJiID(map);
				}else {
					response.sendRedirect("logout");
					return null;
				}
			}
		}
		if (!qingjias.isEmpty()) {
			XueSheng xueSheng = null;
			for (Qingjia qingjia : qingjias) {
				xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
				qingjia.setXueshengxingming(xueSheng.getXingming());
				qingjia.setXuehao(xueSheng.getXuehao());
				qingjia.setBanjimingcheng(banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng());
				try {
					qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		logger.info(qingjias.size());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/qingjiadaichuli");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		return mView;
	}
	
	@RequestMapping(value="qingjiayichuli_fdy")
	public ModelAndView qingJiaYiChuLi(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		int pageSize = 10;
		int pages= 1;
		int page=1;
		List<Qingjia> qingjias = new ArrayList<>();
		if (fuDaoYuan.getBanjiid()!=null&&!fuDaoYuan.getBanjiid().equals("")) {
			String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length()-1);
			Map<String, String> map = new HashMap<>();
			map.put("banjiids", banJiIDs);
			map.put("pizhunren", user.getYonghuid().toString());
			int count = qingjiaService.selectCountFuDaoYuanYiChuLiByBanJiIDsAndPiZhunRenID(map);
			logger.debug("总条数："+count);
			pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				map.put("start", "0");
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectFuDaoYuanYiChuLiByBanJiID(map);
			}else {
				if (!Util.isNumeric(request.getParameter("page"))) {
					response.sendRedirect("logout");
					return null;
				}
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page <= pages){
					map.put("start", String.valueOf((page - 1) * 10));
					map.put("stop", String.valueOf(pageSize));
					qingjias = qingjiaService.selectFuDaoYuanYiChuLiByBanJiID(map);
				}else {
					response.sendRedirect("logout");
					return null;
				}
			}
		}
		
		if (!qingjias.isEmpty()) {
			XueSheng xueSheng = null;
			for (Qingjia qingjia : qingjias) {
				xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
				qingjia.setXueshengxingming(xueSheng.getXingming());
				qingjia.setXuehao(xueSheng.getXuehao());
				qingjia.setBanjimingcheng(banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng());
				try {
					qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		logger.info(qingjias.size());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/qingjiayichuli");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		return mView;
	}
	
	/*@RequestMapping(value="qingjiayishangjiao_fdy")
	public ModelAndView qingJiaYiShangJiao(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length()-1);
		Map<String, String> map = new HashMap<>();
		map.put("banjiids", banJiIDs);
		map.put("pizhunren", user.getYonghuid().toString());
		int count = qingjiaService.selectCountFuDaoYuanYiShangJiaoByBanJiIDsAndPiZhunRenID(map);
		logger.debug("总条数："+count);
		int pageSize = 10;
		int pages = (count / pageSize) + 1;
		int page =1;
		List<Qingjia> qingjias = new ArrayList<>();
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			map.put("start", "0");
			map.put("stop", String.valueOf(pageSize));
			qingjias = qingjiaService.selectFuDaoYuanYiShangJiaoByBanJiID(map);
		}else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages){
				map.put("start", String.valueOf((page - 1) * 10));
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectFuDaoYuanYiShangJiaoByBanJiID(map);
			}else {
				response.sendRedirect("logout");
				return null;
			}
		}
		if (!qingjias.isEmpty()) {
			XueSheng xueSheng = null;
			for (Qingjia qingjia : qingjias) {
				xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
				qingjia.setXueshengxingming(xueSheng.getXingming());
				qingjia.setXuehao(xueSheng.getXuehao());
				try {
					qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		logger.info(qingjias.size());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/qingjiayishangbao");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		return mView;
	}*/
	
	/*@RequestMapping(value="qingjiadaixiaojia_fdy")
	public ModelAndView qingJiaDaiXiaoJia(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length()-1);
		Map<String, String> map = new HashMap<>();
		map.put("banjiids", banJiIDs);
		int count = qingjiaService.selectCountFuDaoYuanDaiXiaoJiaByBanJiIDs(map);
		logger.debug("总条数："+count);
		int pageSize = 10;
		int pages = (count / pageSize) + 1;
		int page =1;
		List<Qingjia> qingjias = new ArrayList<>();
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			map.put("start", "0");
			map.put("stop", String.valueOf(pageSize));
			qingjias = qingjiaService.selectFuDaoYuanDaiXiaoJiaByBanJiID(map);
		}else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages){
				map.put("start", String.valueOf((page - 1) * 10));
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectFuDaoYuanDaiXiaoJiaByBanJiID(map);
			}else {
				response.sendRedirect("logout");
				return null;
			}
		}
		if (!qingjias.isEmpty()) {
			XueSheng xueSheng = null;
			for (Qingjia qingjia : qingjias) {
				xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
				qingjia.setXueshengxingming(xueSheng.getXingming());
				qingjia.setXuehao(xueSheng.getXuehao());
				try {
					qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		logger.info(qingjias.size());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/qingjiadaixiaojia");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		return mView;
	}*/
	
	@RequestMapping(value="jiaTiaoDetail")
	public ModelAndView jiaTiaoDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		String jiaTiaoID = request.getParameter("id");
		if (!Util.isNumeric(jiaTiaoID)) {
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiIDs[]=fuDaoYuan.getBanjiid().split(",");
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
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
		qingjia.setXueshengxingming(xueSheng.getXingming());
		qingjia.setXuehao(xueSheng.getXuehao());
		qingjia.setBanjimingcheng(banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng());
		try {
			qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
		} catch (ParseException e) {
			logger.error(e);
		}
		if (qingjia.getBingjiazhengming()!=null&&!qingjia.getBingjiazhengming().equals("")) {
			List<String> tuPian = new ArrayList<>();
			String tuPianS[]=qingjia.getBingjiazhengming().split(",");
			for (int i = 0; i < tuPianS.length; i++) {
				tuPian.add(tuPianS[i]);
			}
			qingjia.setTuPian(tuPian);
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/jiatiaoxiangqing");
		mView.addObject("jiatiao", qingjia);
		return mView;
	}
	
	@RequestMapping(value="qjsb_fdy")
	@ResponseBody
	public String shangBaoQingJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		String jiaTiaoID = request.getParameter("CODE");
		if (!Util.isNumeric(jiaTiaoID)) {
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiIDs[]=fuDaoYuan.getBanjiid().split(",");
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
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
		if (qingjia.getZhuangtai()!=1) {
			response.sendRedirect("logout");
			return null;
		}
		if (Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian())+1<=3) {
			response.sendRedirect("logout");
			return null;
		}
		qingjia.setZhuangtai(6);
		qingjia.setPizhunren(user.getYonghuid().toString());
		int i = qingjiaService.updateByQingJia(qingjia);
		String ret="";
		if (i>0) {
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="qjxj_fdy")
	@ResponseBody
	public String xiaoJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		String jiaTiaoID = request.getParameter("CODE");
		if (!Util.isNumeric(jiaTiaoID)) {
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiIDs[]=fuDaoYuan.getBanjiid().split(",");
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
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
		if (qingjia.getZhuangtai()!=4) {
			response.sendRedirect("logout");
			return null;
		}
		qingjia.setZhuangtai(5);
		qingjia.setPizhunren(user.getYonghuid().toString());
		int i = qingjiaService.updateByQingJia(qingjia);
		String ret="";
		if (i>0) {
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="qjtg_fdy")
	@ResponseBody
	public String acceptQingJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		String jiaTiaoID = request.getParameter("CODE");
		if (!Util.isNumeric(jiaTiaoID)) {
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiIDs[]=fuDaoYuan.getBanjiid().split(",");
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
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
		if (qingjia.getZhuangtai()!=1) {
			response.sendRedirect("logout");
			return null;
		}
		if (Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian())+1>3) {
			response.sendRedirect("logout");
			return null;
		}
		qingjia.setZhuangtai(2);
		qingjia.setPizhunren(user.getYonghuid().toString());
		int i = qingjiaService.updateByQingJia(qingjia);
		String ret="";
		if (i>0) {
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="qjjj_fdy")
	@ResponseBody
	public String denyQingJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		String jiaTiaoID = request.getParameter("CODE");
		String tongzhineirong = request.getParameter("tongzhineirong");
		if (!Util.isNumeric(jiaTiaoID)) {
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		String banJiIDs[]=fuDaoYuan.getBanjiid().split(",");
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
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
		if (qingjia.getZhuangtai()!=1) {
			response.sendRedirect("logout");
			return null;
		}
		qingjia.setZhuangtai(3);
		qingjia.setPizhunren(user.getYonghuid().toString());
		qingjia.setTongzhineirong(tongzhineirong);
		int i = qingjiaService.updateByQingJia(qingjia);
		String ret="";
		if (i>0) {
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="qingjialist_fdy")
	public ModelAndView qingJiaLieBiao(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu)request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		String xueHao = request.getParameter("xuehao");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		if (fuDaoYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mView = new ModelAndView();
		int page = 1;
		int pages = 1;
		int count = 0;
		int pageSize = 10;
		List<Qingjia> qingjias = new ArrayList<>();
		if (fuDaoYuan.getBanjiid()!=null&&!fuDaoYuan.getBanjiid().equals("")){
			String banJiIDs = fuDaoYuan.getBanjiid().substring(0, fuDaoYuan.getBanjiid().length()-1);
			if (xueHao==null||xueHao.equals("")) {
				count = qingjiaService.selectCountAllByFuDaoYuanBanJiIDs(banJiIDs);
				logger.debug("总条数："+count);
				pages = (count / pageSize) + 1;
				Map<String, String> map = new HashMap<>();
				map.put("xuehao", xueHao);
				if (request.getParameter("page") == null || request.getParameter("page") == "") {
					map.put("banjiids", banJiIDs);
					map.put("start", "0");
					map.put("stop", String.valueOf(pageSize));
					qingjias = qingjiaService.selectAllByFuDaoYuanBanJiIDs(map);
				}else {
					if (!Util.isNumeric(request.getParameter("page"))) {
						response.sendRedirect("logout");
						return null;
					}
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages){
						map.put("banjiids", banJiIDs);
						map.put("start", String.valueOf((page - 1) * 10));
						map.put("stop", String.valueOf(pageSize));
						qingjias = qingjiaService.selectAllByFuDaoYuanBanJiIDs(map);
					}else {
						response.sendRedirect("logout");
						return null;
					}
				}
			}else {
				YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
				XueSheng xueSheng = xueShengService.selectXueShengByXueXiaoXueHao(yuanXi.getXuexiaoid()+"_"+xueHao);
				if(xueSheng == null ){
					xueSheng = xueShengService.selectXueShengByXueHao(xueHao);
				}
				if (xueSheng==null) {
					mView.setViewName("fudaoyuan/qingjialist");
					mView.addObject("pages", pages);
					mView.addObject("page", page);
					mView.addObject("jiatiao", qingjias);
					mView.addObject("xuehao", xueHao);
					return mView;
				}
				qingjias = qingjiaService.getAllByXueShengID(xueSheng.getXueshengid());
			}
		}
		if (!qingjias.isEmpty()) {
			XueSheng xueSheng = null;
			for (Qingjia qingjia : qingjias) {
				xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
				qingjia.setXueshengxingming(xueSheng.getXingming());
				qingjia.setXuehao(xueSheng.getXuehao());
				qingjia.setBanjimingcheng(banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng());
				if (qingjia.getPizhunren()!=null) {
					qingjia.setPizhunren(yongHuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
				}
				try {
					qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		mView.setViewName("fudaoyuan/qingjialist");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		mView.addObject("xuehao", xueHao);
		return mView;
	}
}
