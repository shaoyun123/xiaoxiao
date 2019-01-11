package com.web.controller.web.shuji;

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
import com.web.controller.web.shuji.QingJiaController_sj;
import com.web.model.BanJi;
import com.web.model.Qingjia;
import com.web.model.ShuJi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.service.BanJiService;
import com.web.service.QingjiaService;
import com.web.service.ShuJiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

@Controller
@LoginStatusAnnotation(status="shuji")
public class QingJiaController_sj {
	@Autowired
	private ShuJiService shuJiService;
	
	@Autowired
	private QingjiaService qingjiaService;
	
	@Autowired
	private YongHuService yonghuService;
	
	@Autowired
	private XueShengService xueShengService;
	
	@Autowired 
	private BanJiService banJiService;
	
	@Autowired 
	private YuanXiService yuanXiService;
	
	static Logger logger = Logger.getLogger(QingJiaController_sj.class);
	
	@RequestMapping(value="qingjiadaichuli_sj")
	public ModelAndView qingJiaDaiChuLi(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (!Util.isShuJi(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu) request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		int yuanXiID = shuJi.getYuanxiid();
		int count = qingjiaService.selectCountShuJiDaiChuLiByYuanXiID(yuanXiID);
		logger.debug("总条数："+count);
		int pageSize = 10;
		int pages = (count / pageSize) + 1;
		int page =1;
		List<Qingjia> qingjias = new ArrayList<>();
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			Map<String, String> map = new HashMap<>();
			map.put("yuanxiid", String.valueOf(yuanXiID));
			map.put("start", "0");
			map.put("stop", String.valueOf(pageSize));
			qingjias = qingjiaService.selectShuJiDaiChuLiByYuanXiID(map);
		}else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages){
				Map<String, String> map = new HashMap<>();
				map.put("yuanxiid", String.valueOf(yuanXiID));
				map.put("start", String.valueOf((page - 1) * 10));
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectFuDaoYuanDaiChuLiByBanJiID(map);
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
				qingjia.setBanjimingcheng(banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng());
				if (qingjia.getPizhunren()!=null) {
					qingjia.setPizhunren(yonghuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
				}
				try {
					qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		logger.info(qingjias.size());
		ModelAndView mView = new ModelAndView();
		mView.setViewName("shuji/qingjiadaichuli");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		return mView;
	}
	
	@RequestMapping(value="qingjiayichuli_sj")
	public ModelAndView qingJiaYiChuLi(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (!Util.isShuJi(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = null;
		try {
			user = (YongHu) request.getSession().getAttribute("user");
		} catch (Exception e) {
			logger.error(e);
			response.sendRedirect("logout");
			return null;
		}
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		int yuanXiID = shuJi.getYuanxiid();
		Map<String, String> map = new HashMap<>();
		map.put("yuanxiid", String.valueOf(yuanXiID));
		map.put("pizhunren", String.valueOf(user.getYonghuid()));
		int count = qingjiaService.selectCountShuJiYiChuLiByYuanXiIDAndPiZhunRenID(map);
		logger.debug("总条数："+count);
		int pageSize = 10;
		int pages = (count / pageSize) + 1;
		int page =1;
		List<Qingjia> qingjias = new ArrayList<>();
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			map.put("start", "0");
			map.put("stop", String.valueOf(pageSize));
			qingjias = qingjiaService.selectShujiYiChuLiByYuanXiIDAndShenPiRenID(map);
		}else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages){
				map.put("start", String.valueOf((page - 1) * 10));
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectShujiYiChuLiByYuanXiIDAndShenPiRenID(map);
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
		mView.setViewName("shuji/qingjiayichuli");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		return mView;
	}
	
	@RequestMapping(value="jiaTiaoDetail_sj")
	public ModelAndView jiaTiaoDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isShuJi(request)) {
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
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		int yuanXiID = shuJi.getYuanxiid();
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
		BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
		if (banJi.getYuanxiid()!=yuanXiID) {
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
		mView.setViewName("shuji/jiatiaoxiangqing");
		mView.addObject("jiatiao", qingjia);
		return mView;
	}
	
	@RequestMapping(value="qjsb_sj")
	@ResponseBody
	public String shangBaoQingJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isShuJi(request)) {
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
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		int yuanXiID = shuJi.getYuanxiid();
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
		BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
		if (banJi.getYuanxiid()!=yuanXiID) {
			response.sendRedirect("logout");
			return null;
		}
		if (qingjia.getZhuangtai()!=6) {
			response.sendRedirect("logout");
			return null;
		}
		if (Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian())+1<=7) {
			response.sendRedirect("logout");
			return null;
		}
		qingjia.setZhuangtai(7);
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
	
	@RequestMapping(value="qjtg_sj")
	@ResponseBody
	public String acceptQingJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isShuJi(request)) {
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
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		int yuanXiID = shuJi.getYuanxiid();
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
		BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
		if (banJi.getYuanxiid()!=yuanXiID) {
			response.sendRedirect("logout");
			return null;
		}
		if (qingjia.getZhuangtai()!=6) {
			response.sendRedirect("logout");
			return null;
		}
		if (Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian())+1>7) {
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
	
	@RequestMapping(value="qjjj_sj")
	@ResponseBody
	public String denyQingJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isShuJi(request)) {
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
		String liyou = request.getParameter("liyou");
		if (!Util.isNumeric(jiaTiaoID)) {
			response.sendRedirect("logout");
			return null;
		}
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		int yuanXiID = shuJi.getYuanxiid();
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
		BanJi banJi = banJiService.selectByPrimaryKey(xueSheng.getBanjiid());
		if (banJi.getYuanxiid()!=yuanXiID) {
			response.sendRedirect("logout");
			return null;
		}
		if (qingjia.getZhuangtai()!=6) {
			response.sendRedirect("logout");
			return null;
		}
		qingjia.setZhuangtai(3);
		qingjia.setPizhunren(user.getYonghuid().toString());
		qingjia.setTongzhineirong(liyou);
		int i = qingjiaService.updateByQingJia(qingjia);
		String ret="";
		if (i>0) {
			ret="success";
		}else {
			ret="fail";
		}
		return ret;
	}
	
	@RequestMapping(value="qingjialist_sj")
	public ModelAndView qingJiaLieBiao(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (!Util.isShuJi(request)) {
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
		String xueHao = request.getParameter("xh");
		ShuJi shuJi = shuJiService.selectByPrimaryKey(user.getYonghuid());
		if (shuJi==null) {
			response.sendRedirect("logout");
			return null;
		}
		String yuanXiID = shuJi.getYuanxiid().toString();
		ModelAndView mView = new ModelAndView();
		int page = 1;
		int pages = 1;
		int count = 0;
		int pageSize = 10;
		List<Qingjia> qingjias = new ArrayList<>();
		Map<String, String> map = new HashMap<>();
		map.put("xuehao", xueHao);
		if (xueHao==null||xueHao.equals("")) {
			count = qingjiaService.selectCountAllByShuJiYuanXiID(yuanXiID);
			logger.debug("总条数："+count);
			pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				map.put("yuanxiid", yuanXiID);
				map.put("start", "0");
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectAllByShuJiYuanXiID(map);
			}else {
				if (!Util.isNumeric(request.getParameter("page"))) {
					response.sendRedirect("logout");
					return null;
				}
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page <= pages){
					map.put("yuanxiid", yuanXiID);
					map.put("start", String.valueOf((page - 1) * 10));
					map.put("stop", String.valueOf(pageSize));
					qingjias = qingjiaService.selectAllByShuJiYuanXiID(map);
				}else {
					response.sendRedirect("logout");
					return null;
				}
			}
		}else {
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
			XueSheng xueSheng = xueShengService.selectXueShengByXueXiaoXueHao(yuanXi.getXuexiaoid()+"_"+xueHao);
			if(xueSheng == null){
				xueSheng = xueShengService.selectXueShengByXueHao(xueHao);
			}
			if (xueSheng==null) {
				mView.setViewName("shuji/qingjialist");
				mView.addObject("pages", pages);
				mView.addObject("page", page);
				mView.addObject("jiatiao", qingjias);
				mView.addObject("xuehao", xueHao);
				return mView;
			}
			qingjias = qingjiaService.getAllByXueShengID(xueSheng.getXueshengid());
		}
		if (!qingjias.isEmpty()) {
			XueSheng xueSheng = null;
			for (Qingjia qingjia : qingjias) {
				xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
				qingjia.setXueshengxingming(xueSheng.getXingming());
				qingjia.setXuehao(xueSheng.getXuehao());
				qingjia.setBanjimingcheng(banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng());
				if (qingjia.getPizhunren()!=null) {
					qingjia.setPizhunren(yonghuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
				}
				try {
					qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		mView.setViewName("shuji/qingjialist");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		mView.addObject("xuehao", xueHao);
		return mView;
	}
	
}
