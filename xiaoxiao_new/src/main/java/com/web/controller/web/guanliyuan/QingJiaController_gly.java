package com.web.controller.web.guanliyuan;

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
import com.web.model.Qingjia;
import com.web.model.XueSheng;
import com.web.model.XueShengChuGuanLiYuan;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.QingjiaService;
import com.web.service.XueShengChuGuanLiYuanService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.util.Util;


@Controller
@LoginStatusAnnotation(status="guanliyuan")
public class QingJiaController_gly {
	
	static Logger logger = Logger.getLogger(QingJiaController_gly.class);
	
	@Autowired
	private XueShengChuGuanLiYuanService xueShengChuGuanLiYuanService;
	
	@Autowired 
	private QingjiaService qingjiaService;
	
	@Autowired
	private XueShengService xueShengService; 
	
	@Autowired
	private YongHuService yongHuService; 
	
	@Autowired
	private BanJiService banJiService;
	
	@RequestMapping(value="qingjiadaichuli_gly")
	public ModelAndView qingJiaDaiChuLi(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if (!Util.isGuanLiYuan(request)) {
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
		XueShengChuGuanLiYuan xueShengChuGuanLiYuan = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid());
		if (xueShengChuGuanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String xueXiaoID = String.valueOf(xueShengChuGuanLiYuan.getXuexiaoid())+"_%";
		int count = qingjiaService.selectCountGuanLiYuanDaiChuLiByXueXiaoID(xueXiaoID);
		logger.debug("总条数："+count);
		int pageSize = 10;
		int pages = (count / pageSize) + 1;
		int page =1;
		List<Qingjia> qingjias = new ArrayList<>();
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid", xueXiaoID);
			map.put("start", "0");
			map.put("stop", String.valueOf(pageSize));
			qingjias = qingjiaService.selectGuanLiYuanDaiChuLiByXueXiaoID(map);
		}else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages){
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid", xueXiaoID);
				map.put("start", String.valueOf((page - 1) * 10));
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectGuanLiYuanDaiChuLiByXueXiaoID(map);
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
					qingjia.setPizhunren(yongHuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
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
		mView.setViewName("guanliyuan/qingjiadaichuli");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		return mView;
	} 
	
	@RequestMapping(value="jiaTiaoDetail_gly")
	public ModelAndView jiaTiaoDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
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
		XueShengChuGuanLiYuan xueShengChuGuanLiYuan = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid());
		if (xueShengChuGuanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
		String xueXiaoXueHao[]=xueSheng.getXuexiaoXuehao().split("_");
		String xueXiaoID = xueXiaoXueHao[0];
		if (!xueXiaoID.equals(xueShengChuGuanLiYuan.getXuexiaoid().toString())) {
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
		mView.setViewName("guanliyuan/jiatiaoxiangqing");
		mView.addObject("jiatiao", qingjia);
		return mView;
	}
	
	@RequestMapping(value="qjtg_gly")
	@ResponseBody
	public String acceptQingJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isGuanLiYuan(request)) {
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
		XueShengChuGuanLiYuan xueShengChuGuanLiYuan = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid());
		if (xueShengChuGuanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
		String xueXiaoXueHao[]=xueSheng.getXuexiaoXuehao().split("_");
		String xueXiaoID = xueXiaoXueHao[0];
		if (!xueXiaoID.equals(xueShengChuGuanLiYuan.getXuexiaoid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		if (qingjia.getZhuangtai()!=7) {
			response.sendRedirect("logout");
			return null;
		}
		if (Util.daysBetween(qingjia.getQingjiakaishishijian(), qingjia.getQingjiajieshushijian())+1<7) {
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
		logger.debug(ret);
		return ret;
	}
	
	@RequestMapping(value="qjjj_gly")
	@ResponseBody
	public String denyQingJia(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		if (!Util.isGuanLiYuan(request)) {
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
		XueShengChuGuanLiYuan xueShengChuGuanLiYuan = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid());
		if (xueShengChuGuanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		Qingjia qingjia = qingjiaService.getById(Integer.parseInt(jiaTiaoID));
		if (qingjia==null) {
			response.sendRedirect("logout");
			return null;
		}
		XueSheng xueSheng = xueShengService.getUserById(qingjia.getXueshengid());
		String xueXiaoXueHao[]=xueSheng.getXuexiaoXuehao().split("_");
		String xueXiaoID = xueXiaoXueHao[0];
		if (!xueXiaoID.equals(xueShengChuGuanLiYuan.getXuexiaoid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		if (qingjia.getZhuangtai()!=7) {
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
		logger.debug(ret);
		return ret;
	}
	
	@RequestMapping(value="qingjiayichuli_gly")
	public ModelAndView qingJiaYiChuLi(HttpServletRequest request,HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
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
		XueShengChuGuanLiYuan xueShengChuGuanLiYuan = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid());
		if (xueShengChuGuanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String xueXiaoID = String.valueOf(xueShengChuGuanLiYuan.getXuexiaoid())+"_%";
		int count = qingjiaService.selectCountGuanLiYuanYiChuLiByXueXiaoIDAndGuanLiYuanID(xueXiaoID, xueShengChuGuanLiYuan.getXueshengchuguanliyuanid().toString());
		logger.debug("总条数："+count);
		int pageSize = 10;
		int pages = (count / pageSize) + 1;
		int page =1;
		List<Qingjia> qingjias = new ArrayList<>();
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			Map<String, String> map = new HashMap<>();
			map.put("xuexiaoid", xueXiaoID);
			map.put("start", "0");
			map.put("stop", String.valueOf(pageSize));
			map.put("pizhunren", user.getYonghuid().toString());
			qingjias = qingjiaService.selectGuanLiYuanYiChuLiByXueXiaoIDAndPiZhunRenID(map);
		}else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages){
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid", xueXiaoID);
				map.put("start", String.valueOf((page - 1) * 10));
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectGuanLiYuanYiChuLiByXueXiaoIDAndPiZhunRenID(map);
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
					qingjia.setPizhunren(yongHuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
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
		mView.setViewName("guanliyuan/qingjiayichuli");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		return mView;
	}
	
	@RequestMapping(value="qingjialist_gly")
	public ModelAndView qingJiaLieBiao(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if (!Util.isGuanLiYuan(request)) {
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
		XueShengChuGuanLiYuan guanLiYuan = xueShengChuGuanLiYuanService.selectByID(user.getYonghuid());
		if (guanLiYuan==null) {
			response.sendRedirect("logout");
			return null;
		}
		String xueXiaoID = String.valueOf(guanLiYuan.getXuexiaoid())+"_%";
		ModelAndView mView = new ModelAndView();
		int page = 1;
		int pages = 1;
		int count = 0;
		int pageSize = 10;
		List<Qingjia> qingjias = new ArrayList<>();
		if (xueHao==null || xueHao.equals("")) {
			count = qingjiaService.selectCountAllGuanLiYuanByXueXiaoID(xueXiaoID);
			logger.debug("总条数："+count);
			pages = (count / pageSize) + 1;
			Map<String, String> map = new HashMap<>();
			map.put("xuehao", xueHao);
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				map.put("xuexiaoid", xueXiaoID);
				map.put("start", "0");
				map.put("stop", String.valueOf(pageSize));
				qingjias = qingjiaService.selectAllByGuanLiYuanXueXiaoID(map);
			}else {
				if (!Util.isNumeric(request.getParameter("page"))) {
					response.sendRedirect("logout");
					return null;
				}
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page <= pages){
					map.put("xuexiaoid", xueXiaoID);
					map.put("start", String.valueOf((page - 1) * 10));
					map.put("stop", String.valueOf(pageSize));
					qingjias = qingjiaService.selectAllByGuanLiYuanXueXiaoID(map);
				}else {
					response.sendRedirect("logout");
					return null;
				}
			}
		}else {
			XueSheng xueSheng = xueShengService.selectXueShengByXueXiaoXueHao(xueXiaoID.substring(0, xueXiaoID.length()-1)+xueHao);
			if(xueSheng == null){
				xueSheng = xueShengService.selectXueShengByXueHao(xueHao);
			}
			if (xueSheng==null) {
				mView.setViewName("guanliyuan/qingjialist");
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
					qingjia.setPizhunren(yongHuService.selectYongHuByID(Integer.parseInt(qingjia.getPizhunren())).getYonghuxingming());
				}
				try {
					qingjia.setTianshu(Util.daysBetween(qingjia.getQingjiakaishishijian(),qingjia.getQingjiajieshushijian())+1);
				} catch (ParseException e) {
					logger.error(e);
				}
			}
		}
		mView.setViewName("guanliyuan/qingjialist");
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.addObject("jiatiao", qingjias);
		mView.addObject("xuehao", xueHao);
		return mView;
	}
}
