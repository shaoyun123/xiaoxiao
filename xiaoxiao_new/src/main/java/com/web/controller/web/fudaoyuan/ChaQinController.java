package com.web.controller.web.fudaoyuan;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.ChaQinAnPai;
import com.web.model.ChaQinJieGuo;
import com.web.model.ChaQinZhanShi;
import com.web.model.FuDaoYuan;
import com.web.model.Qingjia;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.XueShengSuShe;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.service.BanJiService;
import com.web.service.ChaQinService;
import com.web.service.FuDaoYuanService;
import com.web.service.JiGuangService;
import com.web.service.NianFenService;
import com.web.service.QingjiaService;
import com.web.service.SuSheLouService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.XueShengSuSheService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "fudaoyuan")
public class ChaQinController {
	@Autowired
	private ChaQinService chaQinService;

	@Autowired
	private XueQiService xueQiService;

	@Autowired
	private YuanXiService yuanXiService;

	@Autowired
	private FuDaoYuanService fuDaoYuanService;

	@Autowired
	private XueShengService xueShengService;

	@Autowired
	private XueShengSuSheService suSheService;
	@Autowired
	private SuSheLouService suSheLouService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private QingjiaService qingjiaService;
	@Autowired
	private NianFenService nianfenService;

	@RequestMapping(value = "chaqin")
	public ModelAndView ChaQin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");

		Map<String, String> paramMap = new HashMap<>();
		int page = 1;
		int pages = 1;
		int pageSize = 10;
		paramMap.put("fudaoyuanid", user.getYonghuid().toString());
		int total = chaQinService.getTotalCount(paramMap);
		pages = (total / pageSize) + 1;
		paramMap.put("yonghuid", user.getYonghuid().toString());

		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			paramMap.put("start", "0");
			paramMap.put("stop", String.valueOf(pageSize));
		} else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages) {
				paramMap.put("start", String.valueOf((page - 1) * 10));
				paramMap.put("stop", String.valueOf(pageSize));
			} else {
				response.sendRedirect("logout");
				return null;
			}
		}
		// List<ChaQinAnPai> anPais =
		// chaQinService.selectByYongHuId(user.getYonghuid());
		List<ChaQinAnPai> anPais = chaQinService.selectByFuDaoYuanId(paramMap);
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/chaqin");
		mView.addObject("anpais", anPais);
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		return mView;
	}

	@RequestMapping(value = "addchaqin")
	public ModelAndView AddChaQin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
		Map<String, String> map = new HashMap<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		map.put("riqi", format.format(date));
		map.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
		XueQi xueQi = new XueQi();
		xueQi = xueQiService.getByxueXiaoIDandriQi(map);
		if (xueQi == null) {
			List<XueQi> xueqis = xueQiService.getNewerXueQiByXueQi(map);
			if (xueqis != null && xueqis.size() > 0) {
				xueQi = xueqis.get(0);
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.addObject("now", format.format(date));
		System.out.println(xueQi.getJieshuriqi());
		mView.addObject("end", format.parse(xueQi.getJieshuriqi()));
		mView.setViewName("fudaoyuan/addchaqin");
		return mView;
	}

	@RequestMapping(value = "subchaqinanpai")
	@ResponseBody
	public String SubChaQinAnPai(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String leiXing = request.getParameter("pl");
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
		Map<String, String> map = new HashMap<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		map.put("riqi", format.format(date));
		map.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
		XueQi xueQi = new XueQi();
		xueQi = xueQiService.getByxueXiaoIDandriQi(map);
		// if (xueQi == null) {
		// List<XueQi> xueqis = xueQiService.getNewerXueQiByXueQi(map);
		// if (xueqis != null && xueqis.size() > 0) {
		// xueQi = xueqis.get(0);
		// }
		// }
		if (xueQi == null) {
			response.sendError(500, "无当前学期信息");
			return null;
		}
		Date jieShuRiQi = format.parse(xueQi.getJieshuriqi());
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(jieShuRiQi);
		calendar2.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
		System.out.println(leiXing);
		if (leiXing.equals("0")) { // 单次
			ChaQinAnPai chaQinAnPai = new ChaQinAnPai();
			chaQinAnPai.setJiaoshiid(user.getYonghuid());
			chaQinAnPai.setRiqi(format.parse(request.getParameter("oncetime")));
			chaQinAnPai.setKaishishijian(format2.parse(request.getParameter("kaishishijian")));
			chaQinAnPai.setJieshushijian(format2.parse(request.getParameter("jiezhishijian")));
			chaQinAnPai.setPaizhaoyaoqiu(request.getParameter("yaoqiu"));
			chaQinAnPai.setZhuangtai(1);
			int i = chaQinService.insertChaQinAnPai(chaQinAnPai);
			if (i > 0) {
				String[] banJiIds = fuDaoYuan.getBanjiid().split(",");
				for (int j = 0; j < banJiIds.length; j++) {
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("辅导员发布了新的查寝安排！");
					xiaoXiFaSong.setXiaoXiNeiRong("辅导员发布了新的查寝安排，请按时上传照片！");
					xiaoXiFaSong.setShuJuId(chaQinAnPai.getAnpaiid());
					xiaoXiFaSong.setShuJuLeiXing(5);
					xiaoXiFaSong.setFaSongMuBiao(banJiIds[j]);
					xiaoXiFaSong.setFaSongLeiXing(2);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(yuanXi.getXuexiaoid().toString());
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				}
				return "success";
			}
		} else if (leiXing.equals("1")) { // 每天
			List<ChaQinAnPai> anPais = new ArrayList<>();
			ChaQinAnPai anPai = null;
			String startTime = request.getParameter("starttime");
			Date date1 = format.parse(startTime);
			String endTime = request.getParameter("endtime");
			Date date2 = format.parse(endTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date2);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			while (date1.before(calendar.getTime())) {
				anPai = new ChaQinAnPai();
				anPai.setJiaoshiid(user.getYonghuid());
				anPai.setKaishishijian(format2.parse(request.getParameter("kaishishijian")));
				anPai.setJieshushijian(format2.parse(request.getParameter("jiezhishijian")));
				anPai.setPaizhaoyaoqiu(request.getParameter("yaoqiu"));
				anPai.setZhuangtai(1);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date1);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				anPai.setRiqi(date1);
				anPais.add(anPai);
				date1 = cal.getTime();
			}
			Boolean bool = chaQinService.insertChaQinAnPaiList(anPais, request);
			if (bool) {
				return "success";
			} else {
				return "fail";
			}
		} else if (leiXing.equals("2")) { // 每周
			String week = request.getParameter("week");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int i = calendar.get(Calendar.DAY_OF_WEEK) - 1 - (Integer.parseInt(week));
			if (i > 0) {
				calendar.add(Calendar.DAY_OF_MONTH, 7 - i);
			} else {
				calendar.add(Calendar.DAY_OF_MONTH, -i);
			}
			List<ChaQinAnPai> list = new ArrayList<>();
			ChaQinAnPai chaQinAnPai = null;
			while (calendar.getTime().before(jieShuRiQi)) {
				chaQinAnPai = new ChaQinAnPai();
				chaQinAnPai.setJiaoshiid(user.getYonghuid());
				chaQinAnPai.setKaishishijian(format2.parse(request.getParameter("kaishishijian")));
				chaQinAnPai.setJieshushijian(format2.parse(request.getParameter("jiezhishijian")));
				chaQinAnPai.setPaizhaoyaoqiu(request.getParameter("yaoqiu"));
				chaQinAnPai.setZhuangtai(1);
				chaQinAnPai.setRiqi(calendar.getTime());
				list.add(chaQinAnPai);
				calendar.add(Calendar.DAY_OF_MONTH, 7);
			}
			Boolean bool = chaQinService.insertChaQinAnPaiList(list, request);
			if (bool) {
				return "success";
			} else {
				return "fail";
			}
		} else if (leiXing.equals("3")) { // 每月
			String day = request.getParameter("day");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (calendar.get(Calendar.DAY_OF_MONTH) > (Integer.parseInt(day))) {
				calendar.add(Calendar.MONTH, 1);
				if (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) < Integer.parseInt(day)) {
					calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				} else {
					calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
				}
				calendar.add(Calendar.MONTH, 1);
			}
			List<ChaQinAnPai> list = new ArrayList<>();
			ChaQinAnPai chaQinAnPai = null;
			while (calendar.getTime().before(jieShuRiQi)) {
				chaQinAnPai = new ChaQinAnPai();
				chaQinAnPai.setJiaoshiid(user.getYonghuid());
				chaQinAnPai.setKaishishijian(format2.parse(request.getParameter("kaishishijian")));
				chaQinAnPai.setJieshushijian(format2.parse(request.getParameter("jiezhishijian")));
				chaQinAnPai.setPaizhaoyaoqiu(request.getParameter("yaoqiu"));
				chaQinAnPai.setZhuangtai(1);
				chaQinAnPai.setRiqi(calendar.getTime());
				list.add(chaQinAnPai);
				System.out.println(calendar.getTime().toString());
				calendar.add(Calendar.MONTH, 1);
			}
			Boolean bool = chaQinService.insertChaQinAnPaiList(list, request);
			if (bool) {
				return "success";
			} else {
				return "fail";
			}
		}
		return null;
	}

	@RequestMapping(value = "chaqinanpai")
	public ModelAndView ChaQinAnPai(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mView = new ModelAndView();
		if (request.getSession().getAttribute("status") != null) {
			String status = (String) request.getSession().getAttribute("status");
			if (!(status.equals("fudaoyuan"))) {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("logout");
			return null;
		}

		String id = request.getParameter("id");
		if (id == null || id.equals("")) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		ChaQinAnPai anPai = chaQinService.selectChaQinAnPaiByPrimaryKey(Integer.parseInt(id));
		if (anPai == null || anPai.getJiaoshiid().equals(user.getYonghuid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		String[] banjis = fuDaoYuan.getBanjiid().split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(new Date());
		List<String> banJis = new ArrayList<>();
		for (String string2 : banjis) {
			BanJi bj = banJiService.selectByPrimaryKey(Integer.parseInt(string2));
			Integer ruXueNianFen = nianfenService.selectByPrimaryKey(bj.getRuxuenianfenid()).getRuxuenianfen();
			String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-09-01";
			if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
				banJis.add(string2);
			}
		}
		List<Integer> suSheIDs = new LinkedList<>();
		List<XueSheng> xueShengs = null;
		for (String string2 : banJis) {
			xueShengs = new ArrayList<>();
			xueShengs = xueShengService.getAllByBanJiID(Integer.parseInt(string2));
			for (XueSheng xueSheng : xueShengs) {
				if (xueSheng.getSusheid() == null) {
					continue;
				} else {
					if (!suSheIDs.contains(xueSheng.getSusheid())) {
						suSheIDs.add(xueSheng.getSusheid());
					}
				}
			}
		}
		List<ChaQinJieGuo> chaQinJieGuos = null;
		List<ChaQinZhanShi> chaQinZhanShis = new LinkedList<>();
		Map<String, Integer> map = new HashMap<>();
		XueShengSuShe suShe = null;
		ChaQinZhanShi chaQinZhanShi = null;
		List<XueSheng> xList = null;
		List<XueSheng> qList = null;
		map.put("anpaiid", Integer.parseInt(id));
		for (int i = 0; i < suSheIDs.size(); i++) {
			chaQinZhanShi = new ChaQinZhanShi();
			suShe = new XueShengSuShe();
			xList = new ArrayList<>();
			qList = new ArrayList<>();
			suShe = suSheService.selectByPrimaryKey(suSheIDs.get(i));
			chaQinZhanShi.setSusheid(suShe.getSuSheId());
			chaQinZhanShi.setAnpaiid(Integer.parseInt(id));
			chaQinZhanShi.setSushemingcheng(
					suSheLouService.selectByPrimaryKey(suShe.getSuSheLouId()).getMingCheng() + suShe.getMenPaiHao());
			chaQinJieGuos = new ArrayList<>();
			map.put("susheid", suSheIDs.get(i));
			chaQinJieGuos = chaQinService.selectChaQinJieGuoByanPaiIDAndsuSheID(map);
			if (chaQinJieGuos.isEmpty()) {
				chaQinZhanShi.setZhuangtai(0);
			} else {
				System.out.println(chaQinJieGuos.get(0).getJieguoid());
				chaQinZhanShi.setJieguoid(chaQinJieGuos.get(0).getJieguoid());
				chaQinZhanShi.setZhuangtai(1);
				XueSheng xueSheng = null;
				boolean b = false;
				for (ChaQinJieGuo chaQinJieGuo : chaQinJieGuos) {
					if (chaQinJieGuo.getZhuangtai() == 0) { // 未到
						xueSheng = xueShengService.getUserById(Integer.parseInt(chaQinJieGuo.getXueshengid()));

						// 判断是否请假
						Map<String, Object> paramMap = new HashMap<>();
						paramMap.put("zhuangtai", 2);
						paramMap.put("xueshengid", xueSheng.getXueshengid());
						paramMap.put("kaishishijian", anPai.getRiqi() + anPai.getKaishishijian());
						paramMap.put("jieshushijian", anPai.getRiqi() + anPai.getJieshushijian());
						Qingjia qingjia = qingjiaService.getByZhuangTaiAndShijianAndXueShengid(paramMap);
						if (qingjia == null) {
							xList.add(xueSheng);
						} else {
							qList.add(xueSheng);
						}
					}
					if (chaQinJieGuo.getZhuangtai() != -1) {
						b = true;
					}
				}
				if (b) {
					chaQinZhanShi.setShifoushenhe(1);
				} else {
					chaQinZhanShi.setShifoushenhe(0);
				}
				chaQinZhanShi.setWeidaoxuesheng(xList);
				chaQinZhanShi.setQingjiaxuesheng(qList);
			}
			chaQinZhanShis.add(chaQinZhanShi);
		}
		int count = chaQinZhanShis.size();
		int pageSize = 10;
		int page = 1;
		int pages = (count / pageSize) + 1;
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			List<ChaQinZhanShi> chaQinZhanShis2 = new LinkedList<>();
			if (count < 10) {
				for (int i = 0; i < count; i++) {
					chaQinZhanShis2.add(chaQinZhanShis.get(i));
				}
				mView.addObject("zhanshis", chaQinZhanShis2);
			} else {
				for (int i = 0; i < 10; i++) {
					chaQinZhanShis2.add(chaQinZhanShis.get(i));
				}
				mView.addObject("zhanshis", chaQinZhanShis2);
			}
		} else {
			if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<ChaQinZhanShi> chaQinZhanShis2 = new LinkedList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++) {
							chaQinZhanShis2.add(chaQinZhanShis.get(i));
						}
						mView.addObject("zhanshis", chaQinZhanShis2);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++) {
							chaQinZhanShis2.add(chaQinZhanShis.get(i));
						}
						mView.addObject("zhanshis", chaQinZhanShis2);
					}
				} else if (page == pages) {
					List<ChaQinZhanShi> chaQinZhanShis2 = new LinkedList<>();
					for (int i = (page - 1) * 10; i < count; i++) {
						chaQinZhanShis2.add(chaQinZhanShis.get(i));
					}
					mView.addObject("zhanshis", chaQinZhanShis2);
				} else {
					response.sendRedirect("logout");
					return null;
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
		}
		mView.addObject("anpaiid", id);
		mView.addObject("pages", pages);
		mView.addObject("page", page);
		mView.setViewName("fudaoyuan/chaqinanpai");
		return mView;
	}

	@RequestMapping(value = "chaqinjilu")
	public ModelAndView chaQinJiLu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		String id = request.getParameter("id");
		ChaQinJieGuo chaQinJieGuo = chaQinService.selectByByPrimaryKey(Integer.parseInt(id));
		if (chaQinJieGuo == null) {
			response.sendRedirect("logout");
			return null;
		}
		ChaQinAnPai chaQinAnPai = chaQinService.selectChaQinAnPaiByPrimaryKey(chaQinJieGuo.getAnpaiid());
		if (!chaQinAnPai.getJiaoshiid().toString().equals(user.getYonghuid().toString())) {
			response.sendRedirect("logout");
			return null;
		}
		if (chaQinJieGuo.getZhaopian() == null || "".equals(chaQinJieGuo.getZhaopian())) {
			chaQinJieGuo.setZhaopian(null);
		} else {
			chaQinJieGuo.setZhaopian(chaQinJieGuo.getZhaopian());
		}
		List<XueSheng> list = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		map.put("anpaiid", chaQinJieGuo.getAnpaiid());
		map.put("susheid", chaQinJieGuo.getSusheid());
		List<ChaQinJieGuo> chaQinJieGuos = chaQinService.selectChaQinJieGuoByanPaiIDAndsuSheID(map);
		boolean b = false;
		if (!chaQinJieGuos.isEmpty()) {
			XueSheng xueSheng = null;
			if (chaQinJieGuos.get(0).getZhuangtai() != -1) {
				b = true;
			}
			for (ChaQinJieGuo chaQinJieGuo2 : chaQinJieGuos) {
				xueSheng = new XueSheng();
				xueSheng = xueShengService.getUserById(Integer.parseInt(chaQinJieGuo2.getXueshengid()));
				if (chaQinJieGuo2.getZhuangtai() == 1) {
					// xueSheng.setCwms("trues");
					xueSheng.setBanjimingcheng("yidao");
				} else {
					xueSheng.setBanjimingcheng("");
				}
				list.add(xueSheng);
			}
		}
		ModelAndView mView = new ModelAndView();
		mView.setViewName("fudaoyuan/chaqinjilu");
		mView.addObject("chaqinjieguo", chaQinJieGuo);
		mView.addObject("xueshengs", list);
		mView.addObject("shifoushenhe", b);
		return mView;
	}

	@RequestMapping(value = "subchaqinjl")
	@ResponseBody
	public String subChaQinJiLu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return "fail";
		}
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		String id = request.getParameter("id");
		ChaQinJieGuo chaQinJieGuo = chaQinService.selectByByPrimaryKey(Integer.parseInt(id));
		if (chaQinJieGuo == null) {
			response.sendRedirect("logout");
			return "fail";
		}
		ChaQinAnPai chaQinAnPai = chaQinService.selectChaQinAnPaiByPrimaryKey(chaQinJieGuo.getAnpaiid());
		if (!chaQinAnPai.getJiaoshiid().toString().equals(user.getYonghuid().toString())) {
			response.sendRedirect("logout");
			return "fail";
		}
		String[] xueShengIDs = request.getParameterValues("yidao");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < xueShengIDs.length; i++) {
			list.add(Integer.parseInt(xueShengIDs[i]));
		}
		Map<String, Integer> map = new HashMap<>();
		map.put("anpaiid", chaQinJieGuo.getAnpaiid());
		map.put("susheid", chaQinJieGuo.getSusheid());
		List<ChaQinJieGuo> chaQinJieGuos = chaQinService.selectChaQinJieGuoByanPaiIDAndsuSheID(map);
		Boolean b = chaQinService.updateChaQinJieGuoByXueShengIDsAndJieGuoList(list, chaQinJieGuos);
		if (b) {
			return "success";
		} else {
			return "fail";
		}
	}

	/**
	 * 通过学年、学期、班级 获得学生出勤情况
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("chaqintongji")
	public ModelAndView getXueShengChaQinTongJi(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, String> paramMap = new HashMap<>();
		if (!(Util.isFuDaoYuan(request))) {
			response.sendRedirect("logout");
			return null;
		}
		YongHu yongHu = (YongHu) request.getSession().getAttribute("user");
		FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(yongHu.getYonghuid());
		if (fuDaoYuan == null) {
			response.sendRedirect("logout");
			return null;
		}
		YuanXi yuanXi = yuanXiService.selectByPrimaryKey(yongHu.getYuanxiid());
		if (fuDaoYuan.getBanjiid() == null || fuDaoYuan.getBanjiid().equals("")) {
			ModelAndView mView = new ModelAndView();
			mView.setViewName("fudaoyuan/chaqintongji");
			return mView;
		}
		String banJiIDs[] = fuDaoYuan.getBanjiid().split(",");
		List<BanJi> banJis = new ArrayList<>();
		List<String> xueNianList = new ArrayList<>();
		BanJi banJi = null;
		for (int i = 0; i < banJiIDs.length; i++) {
			banJi = new BanJi();
			banJi = banJiService.selectByPrimaryKey(Integer.parseInt(banJiIDs[i]));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String riqi = sdf.format(new Date());
			Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
			String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
			if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
				if (banJi != null) {
					banJis.add(banJi);
				}
			}
		}
		xueNianList = xueQiService.getXuenianByXuexiaoID(yuanXi.getXuexiaoid());
		String xueNian = request.getParameter("xuenian");
		String xueQi = request.getParameter("xueqi");
		String banJiID = request.getParameter("banji");
		String xueshengid = request.getParameter("xueshengid");
		XueQi xQi = new XueQi();
		Map<String, Object> xueQiMap = new HashMap<>();
		if (xueNian == null && xueQi == null) {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			paramMap.put("riqi", format.format(date));
			paramMap.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
			xQi = xueQiService.getByxueXiaoIDandriQi(paramMap);
			xueQiMap = xueQiService.getMapXueQiByxueXiaoIDandriQi(paramMap);
		} else {
			paramMap.put("xuenian", xueNian);
			paramMap.put("xueqi", xueQi);
			paramMap.put("xuexiaoid", yuanXi.getXuexiaoid().toString());
			paramMap.put("nianfen", xueNian.split("~")[0]);
			xQi = xueQiService.getByXueXiaoIDAndXueNianAndXueQi(paramMap);
			xueQiMap = xueQiService.getMapXueQiByXueXiaoIDAndXueNianAndXueQi(paramMap);
		}
		if (xueQiMap == null || xQi == null) {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			paramMap.put("riqi", format.format(date));
			paramMap.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
			List<Map<String, Object>> xueqis = xueQiService.getNewerXueQi(paramMap);
			if (xueqis != null && xueqis.size() > 0) {
				xueQiMap = xueqis.get(0);
			}
			List<XueQi> xqs = xueQiService.getNewerXueQiByXueQi(paramMap);
			if (xqs != null && xqs.size() > 0) {
				xQi = xqs.get(0);
			}
		}
		if (banJiID == null) {
			banJiID = banJiIDs[0];
		}
		List<JSONObject> objList = new ArrayList<>();
		int pageSize = 20;
		int pages = 1;
		int page = 1;
		/**
		 * 该学期下的查寝总次数 select count(anPaiID) from chaqinanpai where riQi between
		 * '2018-02-26' and '2018-07-22' and jiaoShiID=#{fudaoyuanid}
		 */
		if (xQi == null) {
			ModelAndView mView = new ModelAndView();
			mView.addObject("xuenian", xueNian);
			mView.addObject("xueshengs", null);
			mView.addObject("xueshengid", 0);
			mView.addObject("xueqi", xueQi);
			mView.addObject("banjiid", banJiID);
			mView.addObject("banjis", banJis);
			mView.addObject("xueNianList", xueNianList);
			mView.addObject("pages", pages);
			mView.addObject("page", page);
			mView.setViewName("fudaoyuan/chaqintongji");
			return mView;
		}
		xueNian = xueQiMap.get("ruXueNianFen").toString();
		xueQi = xQi.getXueqi().toString();
		paramMap.put("fudaoyuanid", fuDaoYuan.getFudaoyuanid().toString());
		paramMap.put("kaishiriqi", xQi.getKaishiriqi());
		paramMap.put("jieshuriqi", xQi.getJieshuriqi());
		int total = chaQinService.selectChaQinTotal(paramMap);
		List<XueSheng> xueShengs = xueShengService.getAllByBanJiID(Integer.parseInt(banJiID));
		int xueShengTotal = xueShengs.size();
		pages = (xueShengTotal / pageSize) + 1;
		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			// paramMap.put("start", "0");
			// paramMap.put("stop", String.valueOf(pageSize));
		} else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages) {
				// paramMap.put("start", String.valueOf((page - 1) * 20));
				// paramMap.put("stop", String.valueOf(pageSize));
			} else {
				response.sendRedirect("logout");
				return null;
			}
		}
		if (total > 0) {
			if (xueshengid == null || xueshengid.equals("")) {
				paramMap.put("banjiid", banJiID.toString());
				// 通过班级id得到对应的全部学生
				List<XueSheng> xueShengList = xueShengService.getByBanJiIDPage(paramMap);
				for (XueSheng xueSheng : xueShengList) {
					JSONObject json = new JSONObject();
					// 统计已完成的查寝次数
					paramMap.put("xueshengid", xueSheng.getXueshengid().toString());
					int count = chaQinService.selectWanChengChaQin(paramMap);

					// 获得门牌号和宿舍楼
					String sushemingcheng = "";
					if (xueSheng.getSusheid() == null || "".equals(xueSheng.getSusheid())) {
						sushemingcheng = "没有对应的宿舍";
					} else {
						Map<String, Object> suSheMap = suSheService.getBySuSheID(xueSheng.getSusheid());
						if (suSheMap == null && suSheMap.size() <= 0) {
							sushemingcheng = "没有对应的宿舍";
						} else {
							sushemingcheng = suSheMap.get("mingcheng") + "" + suSheMap.get("menpaihao");
						}
					}
					json.put("xuesheng", xueSheng);
					json.put("wanchengcishu", count);
					json.put("weiwancheng", total - count);
					json.put("sushe", sushemingcheng);
					objList.add(json);
				}
			} else {
				JSONObject json = new JSONObject();
				paramMap.put("xueshengid", xueshengid);
				XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
				int count = chaQinService.selectWanChengChaQin(paramMap);
				// 获得门牌号和宿舍楼
				Map<String, Object> suSheMap = suSheService.getBySuSheID(xueSheng.getSusheid());
				String sushemingcheng = suSheMap.get("mingcheng") + "" + suSheMap.get("menpaihao");
				json.put("xuesheng", xueSheng);
				json.put("wanchengcishu", count);
				json.put("weiwancheng", total - count);
				json.put("sushe", sushemingcheng);
				objList.add(json);
			}
		} else {
			ModelAndView mView = new ModelAndView();
			mView.addObject("xuenian", xueNian);
			mView.addObject("xueshengid", xueshengid);
			mView.addObject("xueqi", xueQi);
			mView.addObject("xueshengs", xueShengs);
			mView.addObject("banjiid", banJiID);
			mView.addObject("banjis", banJis);
			mView.addObject("xueNianList", xueNianList);
			mView.addObject("pages", pages);
			mView.addObject("page", page);
			mView.setViewName("fudaoyuan/chaqintongji");
			return mView;
		}

		ModelAndView mView = new ModelAndView();
		mView.addObject("objList", objList);
		mView.addObject("xueshengs", xueShengs);
		mView.addObject("xueshengid", xueshengid);
		mView.addObject("total", total);
		mView.addObject("xuenian", xueNian);
		mView.addObject("xueqi", xueQi);
		mView.addObject("banjiid", banJiID);
		mView.addObject("banjis", banJis);
		mView.addObject("xueNianList", xueNianList);
		mView.addObject("pages", pages);
		mView.addObject("page", page);

		mView.setViewName("fudaoyuan/chaqintongji");
		return mView;

	}

	// 通过 学年 学期 学生id 查找对应的查寝安排
	@RequestMapping("tongjiDetail_fdy")
	public ModelAndView chaQinStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!(Util.isFuDaoYuan(request))) {
			response.sendRedirect("logout");
			return null;
		}
		String zhuangtai = request.getParameter("zhuangtai");
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		String xueshengid = request.getParameter("id");
		String xuehao = request.getParameter("xuehao");
		YongHu user = (YongHu) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("logout");
			return null;
		}
		FuDaoYuan fudaoyuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
		YuanXi yuanxi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());

		XueSheng xueSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
		Map<String, Object> suSheMap = suSheService.getBySuSheID(xueSheng.getSusheid());
		String sushemingcheng = suSheMap.get("mingcheng") + "" + suSheMap.get("menpaihao");
		xueSheng.setBanjimingcheng(banJiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng());

		List<String> xueNianList = xueQiService.getXuenianByXuexiaoID(yuanxi.getXuexiaoid());

		int pageSize = 20;
		int pages = 1;
		int page = 1;

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("xuenian", xuenian);
		paramMap.put("xueqi", xueqi);
		paramMap.put("xuexiaoid", yuanxi.getXuexiaoid().toString());
		paramMap.put("nianfen", xuenian.split("~")[0]);
		XueQi xueQi = xueQiService.getByXueXiaoIDAndXueNianAndXueQi(paramMap);
		if (xueQi == null) {
			ModelAndView model = new ModelAndView();
			model.addObject("xuesheng", xueSheng);
			model.addObject("sushemingcheng", sushemingcheng);
			model.addObject("xueNianList", xueNianList);
			model.addObject("xuenian", xuenian);
			model.addObject("xueqi", xueqi);
			model.addObject("pages", pages);
			model.addObject("page", page);
			model.setViewName("fudaoyuan/tongjidetail");
			return model;
		}

		paramMap.put("fudaoyuanid", fudaoyuan.getFudaoyuanid().toString());
		paramMap.put("kaishiriqi", xueQi.getKaishiriqi());
		paramMap.put("jieshuriqi", xueQi.getJieshuriqi());
		paramMap.put("xueshengid", xueshengid);

		List<JSONObject> objList = new ArrayList<>();

		int total = chaQinService.getTotalCount(paramMap);
		int count = chaQinService.selectWanChengChaQin(paramMap);

		pages = (total / pageSize) + 1;

		if (request.getParameter("page") == null || request.getParameter("page") == "") {
			paramMap.put("start", "0");
			paramMap.put("stop", String.valueOf(pageSize));
		} else {
			if (!Util.isNumeric(request.getParameter("page"))) {
				response.sendRedirect("logout");
				return null;
			}
			page = Integer.parseInt(request.getParameter("page"));
			if (page > 0 && page <= pages) {
				paramMap.put("start", String.valueOf((page - 1) * 20));
				paramMap.put("stop", String.valueOf(pageSize));
			} else {
				response.sendRedirect("logout");
				return null;
			}
		}
		List<ChaQinAnPai> chaQinAnPaiList = new ArrayList<>();
		if (zhuangtai == null || "".equals(zhuangtai)) {
			chaQinAnPaiList = chaQinService.getByYongHuIdAndRiQi(paramMap);
		} else if (zhuangtai.equals("0")) {
			chaQinAnPaiList = chaQinService.getChaQinByWeiShangChuan(paramMap);
		} else if (zhuangtai.equals("1")) {
			chaQinAnPaiList = chaQinService.getChaQinByWeiShenHe(paramMap);
		} else if (zhuangtai.equals("2")) {
			chaQinAnPaiList = chaQinService.getChaQinByQueQin(paramMap);
		} else if (zhuangtai.equals("3")) {
			chaQinAnPaiList = chaQinService.getChaQinByBuQueQin(paramMap);
		}

		if (chaQinAnPaiList != null || chaQinAnPaiList.size() > 0) {
			for (ChaQinAnPai chaQinAnPai : chaQinAnPaiList) {
				JSONObject json = new JSONObject();
				String queqin = "";
				ChaQinZhanShi chaQinZhanShi = new ChaQinZhanShi();
				json.put("chaqinanpai", chaQinAnPai);
				chaQinZhanShi.setAnpaiid(chaQinAnPai.getAnpaiid());
				// chaQinZhanShi.setSushemingcheng(sushemingcheng);

				paramMap.put("anpaiid", chaQinAnPai.getAnpaiid().toString());
				ChaQinJieGuo chaQinJieGuo = chaQinService.selectByAnPaiIDAndXueShengID(paramMap);
				if (zhuangtai == null || "".equals(zhuangtai)) {

					if (chaQinJieGuo == null) {
						chaQinZhanShi.setShifoushenhe(0);
						chaQinZhanShi.setZhuangtai(0);
						queqin = "缺寝";
					} else {
						chaQinZhanShi.setJieguoid(chaQinJieGuo.getJieguoid());
						if (chaQinJieGuo.getZhuangtai() != -1) {
							chaQinZhanShi.setShifoushenhe(1);
							if (chaQinJieGuo.getZhuangtai() == 1) {
								queqin = "不缺寝";
							} else {
								queqin = "缺寝";
							}
						} else {
							chaQinZhanShi.setShifoushenhe(0);
							queqin = "缺寝";
						}
						chaQinZhanShi.setZhuangtai(1);
					}
				} else if (zhuangtai.equals("0")) {
					if (chaQinJieGuo == null) {
						chaQinZhanShi.setShifoushenhe(0);
						chaQinZhanShi.setZhuangtai(0);
						queqin = "缺寝";
					}
				} else if (zhuangtai.equals("1")) {
					if (chaQinJieGuo != null && chaQinJieGuo.getZhuangtai() == -1) {
						chaQinZhanShi.setJieguoid(chaQinJieGuo.getJieguoid());
						chaQinZhanShi.setShifoushenhe(0);
						chaQinZhanShi.setZhuangtai(1);
						queqin = "缺寝";
					}
				} else if (zhuangtai.equals("2")) {
					if (chaQinJieGuo == null) {
						chaQinZhanShi.setShifoushenhe(0);
						chaQinZhanShi.setZhuangtai(0);
						queqin = "缺寝";
					}
					if (chaQinJieGuo != null && chaQinJieGuo.getZhuangtai() == -1) {
						chaQinZhanShi.setJieguoid(chaQinJieGuo.getJieguoid());
						chaQinZhanShi.setShifoushenhe(0);
						chaQinZhanShi.setZhuangtai(1);
						queqin = "缺寝";
					}
					if (chaQinJieGuo != null && chaQinJieGuo.getZhuangtai() == 0) {
						chaQinZhanShi.setJieguoid(chaQinJieGuo.getJieguoid());
						chaQinZhanShi.setShifoushenhe(1);
						queqin = "缺寝";
						chaQinZhanShi.setZhuangtai(1);
					}

				} else if (zhuangtai.equals("3")) {
					if (chaQinJieGuo != null && chaQinJieGuo.getZhuangtai() == 1) {
						chaQinZhanShi.setJieguoid(chaQinJieGuo.getJieguoid());
						chaQinZhanShi.setShifoushenhe(1);
						queqin = "不缺寝";
						chaQinZhanShi.setZhuangtai(1);
					}
				}
				json.put("chaqinzhanshi", chaQinZhanShi);
				json.put("queqin", queqin);
				objList.add(json);
			}
			ModelAndView model = new ModelAndView();
			model.addObject("total", total);
			model.addObject("weiwancheng", total - count);
			model.addObject("objList", objList);
			model.addObject("sushemingcheng", sushemingcheng);
			model.addObject("xuesheng", xueSheng);
			model.addObject("xueNianList", xueNianList);
			model.addObject("xuenian", xuenian);
			model.addObject("xueqi", xueqi);
			model.addObject("zhuangtai", zhuangtai);
			model.addObject("pages", pages);
			model.addObject("page", page);
			model.setViewName("fudaoyuan/tongjidetail");
			return model;
		} else {
			ModelAndView model = new ModelAndView();
			model.addObject("total", total);
			model.addObject("weiwancheng", total - count);
			model.addObject("objList", objList);
			model.addObject("sushemingcheng", sushemingcheng);
			model.addObject("xuesheng", xueSheng);
			model.addObject("xueNianList", xueNianList);
			model.addObject("xuenian", xuenian);
			model.addObject("xueqi", xueqi);
			model.addObject("pages", pages);
			model.addObject("page", page);
			model.setViewName("fudaoyuan/tongjidetail");
			return model;
		}

	}

	@RequestMapping(value = "show_stuss")
	@ResponseBody
	public JSONArray show_stu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject mv = new JSONObject();
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("login");
			return null;
		}
		String banjiid = request.getParameter("banjiid");
		List<XueSheng> list = xueShengService.getAllByBanJiID(Integer.parseInt(banjiid));
		mv.put("xueshengs", list);
		return JSONArray.fromObject(list);

	}
}
