package com.web.controller.web.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sun.javadoc.DocErrorReporter;
import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.BeiWL;
import com.web.model.HuoDong;
import com.web.model.JCSJ;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.JieCiFangAn;
import com.web.model.KeCheng;
import com.web.model.SheTuanXinXi;
import com.web.model.TiXing;
import com.web.model.XiaoQu;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.XueShengZuZhiXinXi;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.BeiWLService;
import com.web.service.FuDaoYuanService;
import com.web.service.HuoDongService;
import com.web.service.JCSJService;
import com.web.service.JiGuangService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.JieCiFangAnService;
import com.web.service.KeChengService;
import com.web.service.NianFenService;
import com.web.service.SheTuanService;
import com.web.service.TiXingService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "xuesheng")
public class RiChengController {
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private JieCiFangAnService jiecifanganService;
	@Autowired
	private HuoDongService huodongService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private BeiWLService beiwlService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private FuDaoYuanService fudaoyuanService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private SheTuanService shetuanSerivce;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private TiXingService tixingService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private YuanXiService yuanXiService;
	@Autowired
	private NianFenService nianFenService;

	@RequestMapping(value = "wodekecheng") // 我的课程格子显示
	public ModelAndView wodekecheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			// 获取学校id
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.lastIndexOf("_"));
			// 获取当前学期
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			map.put("riqi", sdf.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			// 获取节次时间
			Map<String, Integer> map2 = new HashMap<>();
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			map2.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			map2.put("zhuangtai", 1);
			int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map2).getId();
			int jiecinum = jcsjService.getCountByJieCiFangAnID(jiecifanganid);
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			// 获取节次时间段
			int shangwunum = 0;
			int xiawunum = 0;
			int wanshangnum = 0;
			for (JCSJ jcsj : jcsjs) {
				if (jcsj.getShijianduan() == 1)
					shangwunum++;
				if (jcsj.getShijianduan() == 2)
					xiawunum++;
				if (jcsj.getShijianduan() == 3)
					wanshangnum++;
			}
			if (xueQi == null) {
				List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
				if (xueqis != null && xueqis.size() > 0)
					xueQi = xueqis.get(0);
			}
			if (xueQi == null) {
				mv.addObject("xuenians", xuenians);
				mv.addObject("shangwunum", shangwunum);
				mv.addObject("xiawunum", xiawunum);
				mv.addObject("wanshangnum", wanshangnum);
				mv.addObject("jicinum", jiecinum);
				mv.addObject("jcsj", jcsjs);
				mv.setViewName("stu/wodekecheng");
				return mv;
			}
			// 获取上课周总数
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			int nf = nianFenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
			String xuenian = nf + "~" + (nf + 1);
			String xueqi = xueQi.getXueqi().toString();
			// 计算今天是当前学期的第几周
			Date kaishiriqi = simpleDateFormat.parse(xueQi.getKaishiriqi());
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(kaishiriqi);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date);
			int day1 = cal1.get(Calendar.DAY_OF_YEAR);
			int day2 = cal2.get(Calendar.DAY_OF_YEAR);
			int year1 = cal1.get(Calendar.YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			int timeDistance = 0;
			if (year1 != year2)
				for (int i = year1; i < year2; i++) {
					if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
						timeDistance += 366;
					else
						timeDistance += 365;
					timeDistance += day2 - day1;
				}
			else
				timeDistance = day2 - day1;
			int zhou = timeDistance / 7 + 1;
			mv.addObject("xuenian", xuenian);
			mv.addObject("xueqi", xueqi);
			mv.addObject("zhou", zhou);
			mv.setViewName("forward:chaxunkecheng");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "kechengliebiao") // 课程列表显示
	public ModelAndView kechengliebiao(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.lastIndexOf("_"));
			map.put("riqi", riqi.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = new XueQi();
			xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			if (xueQi == null) {
				List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
				if (xueqis != null && xueqis.size() > 0)
					xueQi = xueqis.get(0);
			}
			if (xueQi == null) {
				mv.setViewName("stu/kechengliebiao");
				return mv;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date kaishiriqi = simpleDateFormat.parse(xueQi.getKaishiriqi());
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(kaishiriqi);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date);
			int day1 = cal1.get(Calendar.DAY_OF_YEAR);
			int day2 = cal2.get(Calendar.DAY_OF_YEAR);
			int year1 = cal1.get(Calendar.YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			int timeDistance = 0;
			if (year1 != year2)
				for (int i = year1; i < year2; i++) {
					if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
						timeDistance += 366;
					else
						timeDistance += 365;
					timeDistance += day2 - day1;
				}
			else
				timeDistance = day2 - day1;
			int currentzhou = timeDistance / 7 + 1;
			String xuenian = request.getParameter("xuenian") == null
					? nianFenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen() + "~"
							+ (nianFenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen() + 1)
					: request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi") == null ? xueQi.getXueqi().toString()
					: request.getParameter("xueqi");
			String zhou = request.getParameter("zhou") == null ? String.valueOf(currentzhou)
					: request.getParameter("zhou");
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			// 获取当前周的周一和周日
			Map<String, String> map3 = new HashMap<>();
			map3.put("xuenian", xuenian);
			map3.put("xueqi", xueqi);
			map3.put("xuexiaoid", xueXiaoID);
			map3.put("nianfen", xuenian.split("~")[0]);
			Map<String, Object> xueQi2 = xueqiService.getMapXueQiByXueXiaoIDAndXueNianAndXueQi(map3);
			List<Map<String, Object>> keChengs = kechengService.getByBanJiIDAndXueShengID(user.getBanjiid(),
					user.getXueshengid().toString());
			List<Map<String, String>> tingKeMaps = new ArrayList<>();
			for (Map<String, Object> map2 : keChengs) {
				if (xueQi2.get("ID").toString().equals(map2.get("xueQiID").toString())) {
					if (map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("5")) {
						Map<String, String> tingKeMap = new HashMap<>();
						tingKeMap.put("ID", map2.get("ID").toString());
						tingKeMap.put("jiaoShiID", map2.get("jiaoShiID").toString());
						tingKeMap.put("zhouCi", map2.get("zhouCi").toString());
						tingKeMap.put("kaiShiJieCi", map2.get("kaiShiJieCi").toString());
						tingKeMap.put("jieShuJieCi", map2.get("jieShuJieCi").toString());
						tingKeMap.put("kaiShiZhou", map2.get("kaiShiZhou").toString());
						tingKeMaps.add(tingKeMap);
					}
				}
			}
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (int i = 0; i < keChengs.size(); i++) {
				if (xueQi2.get("ID").toString().equals(keChengs.get(i).get("xueQiID").toString())) {
					KeCheng kecheng = new KeCheng();
					kecheng.setId(Integer.parseInt(keChengs.get(i).get("ID").toString()));
					kecheng.setKechengmingcheng(keChengs.get(i).get("keChengMingCheng").toString());
					kecheng.setRenkejiaoshi(keChengs.get(i).get("renKeJiaoShi").toString());
					kecheng.setTianjiarenid(keChengs.get(i).get("tianJiaRenID").toString());
					List<Map<String, String>> maps = new ArrayList<>();

					if (keChengs.get(i).containsKey("shiJianLeiXing")
							&& (keChengs.get(i).get("shiJianLeiXing").toString().equals("1")
									|| keChengs.get(i).get("shiJianLeiXing").toString().equals("2")
									|| keChengs.get(i).get("shiJianLeiXing").toString().equals("3"))) {
						kecheng.setKaishizhou(Integer.parseInt(keChengs.get(i).get("kaiShiZhou").toString()));
						kecheng.setJieshuzhou(Integer.parseInt(keChengs.get(i).get("jieShuZhou").toString()));
						for (int j = 0; j < keChengs.size(); j++) {
							// 判断id相等时，
							if (i != j
									&& keChengs.get(i).get("ID").toString().equals(keChengs.get(j).get("ID").toString())
									&& keChengs.get(i).containsKey("shiJianLeiXing")
									&& (keChengs.get(j).get("shiJianLeiXing").toString().equals("1")
											|| keChengs.get(j).get("shiJianLeiXing").toString().equals("2")
											|| keChengs.get(j).get("shiJianLeiXing").toString().equals("3"))) {
								if (keChengs.get(j).get("shiJianLeiXing").toString().equals("1")) {
									Map<String, String> kechengmap = new HashMap<>();
									kechengmap.put("kaishizhou", keChengs.get(j).get("kaiShiZhou").toString());
									kechengmap.put("jieshuzhou", keChengs.get(j).get("jieShuZhou").toString());
									kechengmap.put("zhouci", keChengs.get(j).get("zhouCi").toString());
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("jieShuJieCi").toString()));
									kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
									kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("jiaoShiID").toString()));
									String jiaoshiming = jshi.getJiaoshiming();
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									String jiaoXueLouMing = jxl.getJiaoXueLouMing();
									String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId())
											.getMingcheng();
									kechengmap.put("jiaoshiming", jiaoshiming);
									kechengmap.put("xiaoquming", xiaoquming);
									kechengmap.put("jiaoXueLouMing", jiaoXueLouMing);
									maps.add(kechengmap);
								}
								if (keChengs.get(j).get("shiJianLeiXing").toString().equals("2")) {
									Map<String, String> kechengmap = new HashMap<>();
									kechengmap.put("kaishizhou", keChengs.get(j).get("kaiShiZhou").toString());
									kechengmap.put("jieshuzhou", keChengs.get(j).get("jieShuZhou").toString());
									kechengmap.put("zhouci", keChengs.get(j).get("zhouCi").toString());
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("jieShuJieCi").toString()));
									kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
									kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("jiaoShiID").toString()));
									String jiaoshiming = jshi.getJiaoshiming();
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									String jiaoXueLouMing = jxl.getJiaoXueLouMing();
									String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId())
											.getMingcheng();
									kechengmap.put("jiaoshiming", jiaoshiming);
									kechengmap.put("xiaoquming", xiaoquming);
									kechengmap.put("jiaoXueLouMing", jiaoXueLouMing);
									kechengmap.put("ds", "1");
									maps.add(kechengmap);
								}
								if (keChengs.get(j).get("shiJianLeiXing").toString().equals("3")) {
									Map<String, String> kechengmap = new HashMap<>();
									kechengmap.put("kaishizhou", keChengs.get(j).get("kaiShiZhou").toString());
									kechengmap.put("jieshuzhou", keChengs.get(j).get("jieShuZhou").toString());
									kechengmap.put("zhouci", keChengs.get(j).get("zhouCi").toString());
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("jieShuJieCi").toString()));
									kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
									kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
											Integer.parseInt(keChengs.get(j).get("jiaoShiID").toString()));
									String jiaoshiming = jshi.getJiaoshiming();
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									String jiaoXueLouMing = jxl.getJiaoXueLouMing();
									String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId())
											.getMingcheng();
									kechengmap.put("jiaoshiming", jiaoshiming);
									kechengmap.put("xiaoquming", xiaoquming);
									kechengmap.put("jiaoXueLouMing", jiaoXueLouMing);
									kechengmap.put("ds", "2");
									maps.add(kechengmap);
								}
							}
						}
						// 把第一级i对应的值加进去
						if (keChengs.get(i).get("shiJianLeiXing").toString().equals("1")) {
							Map<String, String> kechengmap = new HashMap<>();
							kechengmap.put("kaishizhou", keChengs.get(i).get("kaiShiZhou").toString());
							kechengmap.put("jieshuzhou", keChengs.get(i).get("jieShuZhou").toString());
							kechengmap.put("zhouci", keChengs.get(i).get("zhouCi").toString());
							JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
									Integer.parseInt(keChengs.get(i).get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
									Integer.parseInt(keChengs.get(i).get("jieShuJieCi").toString()));
							kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
							kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
							JiaoShi jshi = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jiaoShiID").toString()));
							String jiaoshiming = jshi.getJiaoshiming();
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
							String jiaoXueLouMing = jxl.getJiaoXueLouMing();
							String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng();
							kechengmap.put("jiaoshiming", jiaoshiming);
							kechengmap.put("xiaoquming", xiaoquming);
							kechengmap.put("jiaoXueLouMing", jiaoXueLouMing);
							maps.add(kechengmap);
						}
						if (keChengs.get(i).get("shiJianLeiXing").toString().equals("2")) {
							Map<String, String> kechengmap = new HashMap<>();
							kechengmap.put("kaishizhou", keChengs.get(i).get("kaiShiZhou").toString());
							kechengmap.put("jieshuzhou", keChengs.get(i).get("jieShuZhou").toString());
							kechengmap.put("zhouci", keChengs.get(i).get("zhouCi").toString());
							JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
									Integer.parseInt(keChengs.get(i).get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
									Integer.parseInt(keChengs.get(i).get("jieShuJieCi").toString()));
							kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
							kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
							JiaoShi jshi = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jiaoShiID").toString()));
							String jiaoshiming = jshi.getJiaoshiming();
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
							String jiaoXueLouMing = jxl.getJiaoXueLouMing();
							String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng();
							kechengmap.put("jiaoshiming", jiaoshiming);
							kechengmap.put("xiaoquming", xiaoquming);
							kechengmap.put("jiaoXueLouMing", jiaoXueLouMing);
							kechengmap.put("ds", "1");
							maps.add(kechengmap);
						}
						if (keChengs.get(i).get("shiJianLeiXing").toString().equals("3")) {
							Map<String, String> kechengmap = new HashMap<>();
							kechengmap.put("kaishizhou", keChengs.get(i).get("kaiShiZhou").toString());
							kechengmap.put("jieshuzhou", keChengs.get(i).get("jieShuZhou").toString());
							kechengmap.put("zhouci", keChengs.get(i).get("zhouCi").toString());
							JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
									Integer.parseInt(keChengs.get(i).get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
									Integer.parseInt(keChengs.get(i).get("jieShuJieCi").toString()));
							kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
							kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
							JiaoShi jshi = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jiaoShiID").toString()));
							String jiaoshiming = jshi.getJiaoshiming();
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
							String jiaoXueLouMing = jxl.getJiaoXueLouMing();
							String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng();
							kechengmap.put("jiaoshiming", jiaoshiming);
							kechengmap.put("xiaoquming", xiaoquming);
							kechengmap.put("jiaoXueLouMing", jiaoXueLouMing);
							kechengmap.put("ds", "2");
							maps.add(kechengmap);
						}
					}
					if (keChengs.get(i).containsKey("shiJianLeiXing")
							&& keChengs.get(i).get("shiJianLeiXing").toString().equals("4")) { // 按次上课
						kecheng.setLeixing(3);
						Map<String, String> firstMap = new HashMap<>();
						Map<String, String> secondMap = new HashMap<>();
						String secondZhou = "";
						String firstZhou = keChengs.get(i).get("kaiShiZhou").toString() + ",";

						for (int j = 0; j < keChengs.size(); j++)
							if (i != j
									&& keChengs.get(i).get("ID").toString().equals(keChengs.get(j).get("ID").toString())
									&& keChengs.get(j).get("shiJianLeiXing").toString().equals("4"))
								if (keChengs.get(i).get("zhouCi").toString()
										.equals(keChengs.get(j).get("zhouCi").toString())
										&& keChengs.get(i).get("kaiShiJieCi").toString()
												.equals(keChengs.get(j).get("kaiShiJieCi").toString())
										&& keChengs.get(i).get("jieShuJieCi").toString()
												.equals(keChengs.get(j).get("jieShuJieCi").toString())
										&& keChengs.get(i).get("jiaoShiID").toString()
												.equals(keChengs.get(j).get("jiaoShiID").toString())) {
									int l = 0;
									if (tingKeMaps != null && tingKeMaps.size() > 0) {
										for (Map<String, String> map2 : tingKeMaps) {
											if (map2.get("ID").toString()
													.equals(keChengs.get(j).get("ID").toString())) {
												if (map2.get("zhouCi").toString()
														.equals(keChengs.get(j).get("zhouCi").toString())
														&& map2.get("kaiShiJieCi").toString()
																.equals(keChengs.get(j).get("kaiShiJieCi").toString())
														&& map2.get("jieShuJieCi").toString()
																.equals(keChengs.get(j).get("jieShuJieCi").toString())
														&& map2.get("kaiShiZhou").toString()
																.equals(keChengs.get(j).get("kaiShiZhou").toString())
														&& map2.get("jiaoShiID").toString()
																.equals(keChengs.get(j).get("jiaoShiID").toString())) {
													l = 1;
												}
											}
										}
									}
									if (l == 0) {
										firstZhou += keChengs.get(j).get("kaiShiZhou").toString() + ",";
										firstMap.put("zhouci", keChengs.get(i).get("zhouCi").toString());
										JCSJ jcsj1 = jcsjService
												.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("kaiShiJieCi").toString()));
										JCSJ jcsj2 = jcsjService
												.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jieShuJieCi").toString()));
										firstMap.put("kaishijieci", jcsj1.getJieci().toString());
										firstMap.put("jieshujieci", jcsj2.getJieci().toString());
										JiaoShi jshi = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jiaoShiID").toString()));
										String jiaoshiming = jshi.getJiaoshiming();
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										String jiaoXueLouMing = jxl.getJiaoXueLouMing();
										String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng();
										firstMap.put("jiaoshiming", jiaoshiming);
										firstMap.put("xiaoquming", xiaoquming);
										firstMap.put("jiaoXueLouMing", jiaoXueLouMing);
									}
								} else {
									int l = 0;
									if (tingKeMaps != null && tingKeMaps.size() > 0) {
										for (Map<String, String> map2 : tingKeMaps) {
											if (map2.get("ID").toString()
													.equals(keChengs.get(j).get("ID").toString())) {
												if (map2.get("zhouCi").toString()
														.equals(keChengs.get(j).get("zhouCi").toString())
														&& map2.get("kaiShiJieCi").toString()
																.equals(keChengs.get(j).get("kaiShiJieCi").toString())
														&& map2.get("jieShuJieCi").toString()
																.equals(keChengs.get(j).get("jieShuJieCi").toString())
														&& map2.get("kaiShiZhou").toString()
																.equals(keChengs.get(j).get("kaiShiZhou").toString())
														&& map2.get("jiaoShiID").toString()
																.equals(keChengs.get(j).get("jiaoShiID").toString())) {
													l = 1;
												}
											}
										}
									}
									if (l == 0) {
										secondZhou += keChengs.get(j).get("kaiShiZhou").toString() + ",";
										secondMap.put("zhouci", keChengs.get(j).get("zhouCi").toString());
										JCSJ jcsj12 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengs.get(j).get("kaiShiJieCi").toString()));
										JCSJ jcsj22 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengs.get(j).get("jieShuJieCi").toString()));
										secondMap.put("kaishijieci", jcsj12.getJieci().toString());
										secondMap.put("jieshujieci", jcsj22.getJieci().toString());
										JiaoShi jshi2 = jiaoshiService.selectByPrimaryKey(
												Integer.parseInt(keChengs.get(j).get("jiaoShiID").toString()));
										String jiaoshiming2 = jshi2.getJiaoshiming();
										JiaoXueLou jxl2 = jiaoXueLouService.selectByPrimaryKey(jshi2.getJiaoxuelouid());
										String jiaoXueLouMing2 = jxl2.getJiaoXueLouMing();
										String xiaoquming2 = xiaoquService.selectByPrimaryKey(jxl2.getXiaoQuId())
												.getMingcheng();
										secondMap.put("jiaoshiming", jiaoshiming2);
										secondMap.put("xiaoquming", xiaoquming2);
										secondMap.put("jiaoXueLouMing", jiaoXueLouMing2);
									}
								}
						
						if (secondZhou != null && !"".equals(secondZhou)) {
							secondMap.put("zhoushu", secondZhou.substring(0, secondZhou.lastIndexOf(",")));
							maps.add(secondMap);
						}
						if (firstMap != null && firstMap.size() > 0) {
							firstMap.put("zhoushu", firstZhou.substring(0, firstZhou.lastIndexOf(",")));
							maps.add(firstMap);
						}
					}
					if(maps != null && maps.size() > 0){
						kecheng.setMaps(maps);
						int m = 0;
						for (KeCheng kc : keChengs2){
							if (kc.getId().toString().equals(kecheng.getId().toString())) {
								m = 1;
								break;
							}
						}
						if (m == 0)
							keChengs2.add(kecheng);
					}
				}
			}
			for (int i = 0; i < keChengs.size(); i++){
				if (xueQi2.get("ID").toString().equals(keChengs.get(i).get("xueQiID").toString())) {
					List<Map<String, String>> maps = new ArrayList<>();
					if (keChengs.get(i).containsKey("shiJianLeiXing")
							&& keChengs.get(i).get("shiJianLeiXing").toString().equals("6")) { // 按次上课
						KeCheng kecheng = new KeCheng();
						kecheng.setId(Integer.parseInt(keChengs.get(i).get("ID").toString()));
						kecheng.setKechengmingcheng(keChengs.get(i).get("keChengMingCheng").toString());
						kecheng.setRenkejiaoshi(keChengs.get(i).get("renKeJiaoShi").toString());
						kecheng.setTianjiarenid(keChengs.get(i).get("tianJiaRenID").toString());
						kecheng.setLeixing(3);
						Map<String, String> firstMap = new HashMap<>();
						firstMap.put("zhouci", keChengs.get(i).get("zhouCi").toString());
						kecheng.setKaishizhou(Integer.parseInt(keChengs.get(i).get("kaiShiZhou").toString()));
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jieShuJieCi").toString()));
						firstMap.put("kaishijieci", jcsj1.getJieci().toString());
						firstMap.put("jieshujieci", jcsj2.getJieci().toString());
						JiaoShi jshi = jiaoshiService
								.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jiaoShiID").toString()));
						String jiaoshiming = jshi.getJiaoshiming();
						JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
						String jiaoXueLouMing = jxl.getJiaoXueLouMing();
						String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng();
						firstMap.put("jiaoshiming", jiaoshiming);
						firstMap.put("xiaoquming", xiaoquming);
						firstMap.put("jiaoXueLouMing", jiaoXueLouMing);
						maps.add(firstMap);
						kecheng.setMaps(maps);
						keChengs2.add(kecheng);
					}
				}
			}

			/**
			 * map3.put("banjiid1", user.getBanjiid() + ",%");
			 * map3.put("banjiid2", "%," + user.getBanjiid() + ",%");
			 * map3.put("xueshengid1", user.getXueshengid() + ",%");
			 * map3.put("xueshengid2", "%," + user.getXueshengid() + ",%"); List
			 * <KeCheng> keChengs =
			 * kechengService.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(map3);
			 * for (KeCheng keCheng : keChengs) { if
			 * (!(keCheng.getDanshuangzhoushuoming().isEmpty())) { String
			 * danShuangZhouShuoMing[] =
			 * keCheng.getDanshuangzhoushuoming().split("zqxj"); switch
			 * (danShuangZhouShuoMing[0]) { case "1": // 连续 String zhouCi[] =
			 * danShuangZhouShuoMing[1].split(","); // 判断周次是否在 if
			 * (Integer.parseInt(zhouCi[0]) <= Integer.parseInt(zhou) &&
			 * Integer.parseInt(zhouCi[1]) >= Integer.parseInt(zhou) &&
			 * keCheng.getXuenian().equals(xuenian) &&
			 * keCheng.getXueqi().toString().equals(xueqi)) { // 判断 是否调停 String
			 * tiaoTing[] = danShuangZhouShuoMing[3].split("zqjx"); String
			 * tiaoKe[] = danShuangZhouShuoMing[4].split("zqjx"); // 获取周几，节次，教室
			 * String total[] = danShuangZhouShuoMing[2].split("zxqj"); int
			 * tiaoTingZhou = 0; for (String string2 : total) { String xinxi[] =
			 * string2.split(","); // 根据当前周，判断日期 int i = Integer.parseInt(zhou)
			 * - Integer.parseInt(zhouCi[0]) + 1; // 获取本周距离学期开始的天数 int day =
			 * (Integer.parseInt(zhouCi[0]) - 1 + (i - 1)) * 7 +
			 * Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 String shangkeriqi =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi2.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); int k = 0; for (String string
			 * : tiaoTing) { if (shangkeriqi.equals(string)) { k = 1; break; } }
			 * if (k == 0) { KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * 
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); } kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } if (tiaoKe.length > 0) { int i =
			 * Integer.parseInt(zhou) - Integer.parseInt(zhouCi[0]) + 1; for
			 * (int ii = 1; ii <= 7; ii++) { int days =
			 * (Integer.parseInt(zhouCi[0]) - 1 + (i - 1)) * 7 + ii - 1; //
			 * 获取每次上课日期 String s = simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi2.getKaishiriqi()).getTime() +
			 * (long) days * 24 * 60 * 60 * 1000)); for (String string : tiaoKe)
			 * { int k2 = 0; if (s.equals(string.split(",")[0])) { k2 = 1;
			 * tiaoTingZhou = ii; } if (k2 == 1) { String kc[] =
			 * string.split(","); KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * 
			 * kecheng.setZhouci(tiaoTingZhou); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
			 * .getJiaoXueLouMing()); kecheng.setXiaoquming(xiaoquService
			 * .selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng()); }
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } } }
			 * 
			 * } break;
			 * 
			 * case "2": // 单双周 String zhouCi2[] =
			 * danShuangZhouShuoMing[1].split(","); // 判断 是否调停 String tiaoTing[]
			 * = danShuangZhouShuoMing[3].split("zqjx"); String tiaoKe[] =
			 * danShuangZhouShuoMing[4].split("zqjx"); // 判断周次是否在 if
			 * (Integer.parseInt(zhouCi2[0]) <= Integer.parseInt(zhou) &&
			 * Integer.parseInt(zhouCi2[1]) >= Integer.parseInt(zhou) &&
			 * keCheng.getXuenian().equals(xuenian) &&
			 * keCheng.getXueqi().toString().equals(xueqi)) { // int
			 * tiaoTingZhou = 0; // 判断单双周 String danShuang[] =
			 * danShuangZhouShuoMing[2].split("zqjx"); if
			 * (Integer.parseInt(zhou) % 2 == 0) { // 双周 // 获取周几，节次，教室 String
			 * total[] = danShuang[1].split("zxqj"); for (String string2 :
			 * total) { String xinxi[] = string2.split(","); // 根据当前周，判断日期 int i
			 * = Integer.parseInt(zhou) - Integer.parseInt(zhouCi2[0]) + 1; //
			 * 获取本周距离学期开始的天数 int day = (Integer.parseInt(zhouCi2[0]) - 1 + (i -
			 * 1)) * 7 + Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 String
			 * shangkeriqi = simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi2.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); int k = 0; for (String string
			 * : tiaoTing) { if (shangkeriqi.equals(string)) { k = 1; break; } }
			 * 
			 * if (k == 0) { KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * 
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()) .getMingcheng()); }
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } }
			 * 
			 * } else { // 单周 // 获取周几，节次，教室 String total[] =
			 * danShuang[0].split("zxqj"); for (String string2 : total) { String
			 * xinxi[] = string2.split(","); // 根据当前周，判断日期 int i =
			 * Integer.parseInt(zhou) - Integer.parseInt(zhouCi2[0]) + 1; //
			 * 获取本周距离学期开始的天数 int day = (Integer.parseInt(zhouCi2[0]) - 1 + (i -
			 * 1)) * 7 + Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 String
			 * shangkeriqi = simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi2.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); int k = 0; for (String string
			 * : tiaoTing) { if (shangkeriqi.equals(string)) { k = 1; // break;
			 * } } if (k == 0) { KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()) .getMingcheng()); }
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } }
			 * 
			 * } if (tiaoKe.length > 0) { int i = Integer.parseInt(zhou) -
			 * Integer.parseInt(zhouCi2[0]) + 1; int tiaoTingZhou = 0; for (int
			 * ii = 1; ii <= 7; ii++) { int days = (Integer.parseInt(zhouCi2[0])
			 * - 1 + (i - 1)) * 7 + ii - 1; // 获取每次上课日期 String s =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi2.getKaishiriqi()).getTime() +
			 * (long) days * 24 * 60 * 60 * 1000)); for (String string : tiaoKe)
			 * { int k2 = 0; if (s.equals(string.split(",")[0])) { k2 = 1;
			 * tiaoTingZhou = ii; } if (k2 == 1) { String kc[] =
			 * string.split(","); KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(tiaoTingZhou); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()) .getMingcheng()); }
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } } } break; case "3": // 单次，不连续,只需比对时间
			 * String kcheng[] = danShuangZhouShuoMing[1].split("zqjx"); int i =
			 * Integer.parseInt(zhou); int zhous = 0; for (int ii = 1; ii <= 7;
			 * ii++) { int days = (i - 1) * 7 + ii - 1; // 获取每次上课日期 String s =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi2.getKaishiriqi()).getTime() +
			 * (long) days * 24 * 60 * 60 * 1000)); for (String string : kcheng)
			 * { int k2 = 0; if (s.equals(string.split(",")[0])) { k2 = 1; zhous
			 * = ii; } if (k2 == 1) { String kc[] = string.split(","); KeCheng
			 * kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi()); //
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0])); //
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(zhous); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); } kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } } break; } } }
			 */

			int count = keChengs2.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<KeCheng> keChengs3 = new ArrayList<>();
				if (count < 10) {

					for (int i = 0; i < count; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				} else {
					for (int i = 0; i < 10; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				}
			} else if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<KeCheng> keChengs3 = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++)
							keChengs3.add(keChengs2.get(i));
						mv.addObject("kecheng", keChengs3);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++)
							keChengs3.add(keChengs2.get(i));
						mv.addObject("kecheng", keChengs3);
					}
				} else if (page == pages) {
					List<KeCheng> keChengs3 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				} else {
					response.sendRedirect("logout");
					return null;
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}

			mv.addObject("user", user);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xuenians", xuenians);
			mv.addObject("xueqi", xueqi);
			// mv.addObject("zhou", zhou);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("stu/kechengliebiao");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "show_zhounum") // 根据学年学期，显示周数
	@ResponseBody
	public JSONObject show_zhounum(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		if (Util.checkSession(request)) {
			JSONObject json = new JSONObject();
			HttpSession session = request.getSession();
			XueSheng user = (XueSheng) session.getAttribute("user");
			BanJi banJi = banjiService.selectByPrimaryKey(user.getBanjiid());
			int xuexiaoid = Integer
					.parseInt((yuanXiService.selectByPrimaryKey(banJi.getYuanxiid()).getXuexiaoid()).toString());
			int zhounum = 0;
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			Map<String, String> map = new HashMap<>();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			XueQi xueQi2 = new XueQi();
			if (xuenian.equals("") || "".equals(xueqi)) {
				Date date = new Date();
				String riqi = simpleDateFormat.format(date);
				map.put("xueXiaoID", xuexiaoid + "");
				map.put("riqi", riqi);
				xueQi2 = xueqiService.getByxueXiaoIDandriQi(map);
			} else {
				map.put("xuexiaoid", xuexiaoid + "");
				map.put("xuenian", xuenian);
				map.put("xueqi", xueqi);
				map.put("nianfen", xuenian.split("~")[0]);
				xueQi2 = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			}
			Date date1 = simpleDateFormat.parse(xueQi2.getKaishiriqi());
			Date date2 = simpleDateFormat.parse(xueQi2.getJieshuriqi());
			zhounum = Util.WeeksBetweenDays(date1, date2);
			json.put("zhounum", zhounum);
			return json;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "chaxunkecheng") // 查询课程格子显示
	public ModelAndView chaxunkecheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NumberFormatException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String xuenian = request.getParameter("xuenian") == null ? request.getAttribute("xuenian").toString()
					: request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi") == null ? request.getAttribute("xueqi").toString()
					: request.getParameter("xueqi");
			String zhou = request.getParameter("zhou") == null ? request.getAttribute("zhou").toString()
					: request.getParameter("zhou");
			XueSheng user = (XueSheng) session.getAttribute("user");

			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.lastIndexOf("_"));
			// 获取节次时间
			Map<String, Integer> map2 = new HashMap<>();
			map2.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			map2.put("zhuangtai", 1);
			int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map2).getId();
			int jiecinum = jcsjService.getCountByJieCiFangAnID(jiecifanganid);
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			// 获取节次时间段
			int shangwunum = 0;
			int xiawunum = 0;
			int wanshangnum = 0;
			for (JCSJ jcsj : jcsjs) {
				if (jcsj.getShijianduan() == 1)
					shangwunum++;
				if (jcsj.getShijianduan() == 2)
					xiawunum++;
				if (jcsj.getShijianduan() == 3)
					wanshangnum++;
			}
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			// 获取当前周的周一和周日
			Map<String, String> map = new HashMap<>();
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			map.put("xuexiaoid", xueXiaoID);
			map.put("nianfen", xuenian.split("~")[0]);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			if (xueQi == null) {
				mv.addObject("shangwunum", shangwunum);
				mv.addObject("xiawunum", xiawunum);
				mv.addObject("wanshangnum", wanshangnum);
				mv.addObject("jicinum", jiecinum);
				mv.addObject("jcsj", jcsjs);
				mv.setViewName("stu/wodekecheng");
				return mv;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			int zhounum = Util.WeeksBetweenDays(simpleDateFormat.parse(xueQi.getKaishiriqi()),
					simpleDateFormat.parse(xueQi.getJieshuriqi()));
			if (Integer.parseInt(zhou) > zhounum)
				zhou = "1";
			List<KeCheng> keChengs2 = new ArrayList<>();
			List<Map<String, Object>> keChengMaps = kechengService.getByBanJiIDAndXueShengID(user.getBanjiid(),
					user.getXueshengid().toString());
			for (Map<String, Object> keChengMap : keChengMaps)
				if (xueQi.getXueqiid().toString().equals(keChengMap.get("xueQiID").toString())) {
					// 停课情况
					List<Map<String, Object>> tingkes = new ArrayList<>();
					for (Map<String, Object> tingKe : keChengMaps)
						if (keChengMap.get("ID").toString().equals(tingKe.get("ID").toString()))
							if (tingKe.containsKey("shiJianLeiXing")
									&& tingKe.get("shiJianLeiXing").toString().equals("5"))
								if (zhou.equals(tingKe.get("kaiShiZhou").toString())) {
									Map<String, Object> map3 = new HashMap<>();
									map3.put("zhouCi", tingKe.get("zhouCi").toString());
									map3.put("kaiShiJieCi", tingKe.get("kaiShiJieCi").toString());
									map3.put("jieShuJieCi", tingKe.get("jieShuJieCi").toString());
									map3.put("jiaoShiID", tingKe.get("jiaoShiID").toString());
									tingkes.add(map3);
								}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("1")
							&& Integer.parseInt(zhou) >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
							&& Integer.parseInt(zhou) <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (Map<String, Object> map3 : tingkes)
								if (map3.get("zhouCi").toString().equals(keChengMap.get("zhouCi").toString())
										&& map3.get("kaiShiJieCi").toString()
												.equals(keChengMap.get("kaiShiJieCi").toString())
										&& map3.get("jieShuJieCi").toString()
												.equals(keChengMap.get("jieShuJieCi").toString())
										&& map3.get("jiaoShiID").toString()
												.equals(keChengMap.get("jiaoShiID").toString())) {
									i = 1;
									break;
								}
							if (i == 0) {
								KeCheng kecheng = new KeCheng();
								kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
								kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
								kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
								kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
								kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
								JCSJ jcsj1 = jcsjService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
								JCSJ jcsj2 = jcsjService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
								kecheng.setKaishijieci(jcsj1.getJieci());
								kecheng.setJieshujieci(jcsj2.getJieci());
								kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
								JiaoShi jshi = jiaoshiService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
								kecheng.setJiaoshiming(jshi.getJiaoshiming());
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
								kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
								kecheng.setXiaoquming(
										xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
								keChengs2.add(kecheng);
							}
						} else {
							KeCheng kecheng = new KeCheng();
							kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
							kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
							kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
							kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
							kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
							JCSJ jcsj1 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
							kecheng.setKaishijieci(jcsj1.getJieci());
							kecheng.setJieshujieci(jcsj2.getJieci());
							kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
							JiaoShi jshi = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
							kecheng.setJiaoshiming(jshi.getJiaoshiming());
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
							kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
							kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
							keChengs2.add(kecheng);

						}
					}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("2")
							&& Integer.parseInt(zhou) >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
							&& Integer.parseInt(zhou) <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (Map<String, Object> map3 : tingkes)
								if (map3.get("zhouCi").toString().equals(keChengMap.get("zhouCi").toString())
										&& map3.get("kaiShiJieCi").toString()
												.equals(keChengMap.get("kaiShiJieCi").toString())
										&& map3.get("jieShuJieCi").toString()
												.equals(keChengMap.get("jieShuJieCi").toString())
										&& map3.get("jiaoShiID").toString()
												.equals(keChengMap.get("jiaoShiID").toString())) {
									i = 1;
									break;
								}
							if (i == 0)
								if (Integer.parseInt(zhou) % 2 != 0) {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
									kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
									kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
									kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
									kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());
									kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
									keChengs2.add(kecheng);
								}
						} else if (Integer.parseInt(zhou) % 2 != 0) {
							KeCheng kecheng = new KeCheng();
							kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
							kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
							kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
							kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
							kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
							JCSJ jcsj1 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
							kecheng.setKaishijieci(jcsj1.getJieci());
							kecheng.setJieshujieci(jcsj2.getJieci());
							kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
							JiaoShi jshi = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
							kecheng.setJiaoshiming(jshi.getJiaoshiming());
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
							kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
							kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
							keChengs2.add(kecheng);
						}
					}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("3")
							&& Integer.parseInt(zhou) >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
							&& Integer.parseInt(zhou) <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (Map<String, Object> map3 : tingkes)
								if (map3.get("zhouCi").toString().equals(keChengMap.get("zhouCi").toString())
										&& map3.get("kaiShiJieCi").toString()
												.equals(keChengMap.get("kaiShiJieCi").toString())
										&& map3.get("jieShuJieCi").toString()
												.equals(keChengMap.get("jieShuJieCi").toString())
										&& map3.get("jiaoShiID").toString()
												.equals(keChengMap.get("jiaoShiID").toString())) {
									i = 1;
									break;
								}
							if (i == 0)
								if (Integer.parseInt(zhou) % 2 == 0) {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
									kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
									kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
									kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
									kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());
									kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
									keChengs2.add(kecheng);
								}
						} else if (Integer.parseInt(zhou) % 2 == 0) {
							KeCheng kecheng = new KeCheng();
							kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
							kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
							kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
							kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
							kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
							JCSJ jcsj1 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
							kecheng.setKaishijieci(jcsj1.getJieci());
							kecheng.setJieshujieci(jcsj2.getJieci());
							kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
							JiaoShi jshi = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
							kecheng.setJiaoshiming(jshi.getJiaoshiming());
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
							kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
							kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
							keChengs2.add(kecheng);
						}
					}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("4")
							&& Integer.parseInt(zhou) == Integer.parseInt(keChengMap.get("kaiShiZhou").toString())) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (Map<String, Object> map3 : tingkes)
								if (map3.get("zhouCi").toString().equals(keChengMap.get("zhouCi").toString())
										&& map3.get("kaiShiJieCi").toString()
												.equals(keChengMap.get("kaiShiJieCi").toString())
										&& map3.get("jieShuJieCi").toString()
												.equals(keChengMap.get("jieShuJieCi").toString())
										&& map3.get("jiaoShiID").toString()
												.equals(keChengMap.get("jiaoShiID").toString())) {
									i = 1;
									break;
								}
							if (i == 0) {
								KeCheng kecheng = new KeCheng();
								kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
								kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
								kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
								kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
								// kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
								JCSJ jcsj1 = jcsjService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
								JCSJ jcsj2 = jcsjService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
								kecheng.setKaishijieci(jcsj1.getJieci());
								kecheng.setJieshujieci(jcsj2.getJieci());
								kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
								JiaoShi jshi = jiaoshiService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
								kecheng.setJiaoshiming(jshi.getJiaoshiming());
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
								kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
								kecheng.setXiaoquming(
										xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
								keChengs2.add(kecheng);
							}
						} else {
							KeCheng kecheng = new KeCheng();
							kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
							kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
							kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
							kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
							// kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
							JCSJ jcsj1 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
							kecheng.setKaishijieci(jcsj1.getJieci());
							kecheng.setJieshujieci(jcsj2.getJieci());
							kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
							JiaoShi jshi = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
							kecheng.setJiaoshiming(jshi.getJiaoshiming());
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
							kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
							kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
							keChengs2.add(kecheng);

						}
					}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("6")
							&& Integer.parseInt(zhou) == Integer.parseInt(keChengMap.get("kaiShiZhou").toString())) {
						KeCheng kecheng = new KeCheng();
						kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
						kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
						kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
						kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
						// kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
						kecheng.setKaishijieci(jcsj1.getJieci());
						kecheng.setJieshujieci(jcsj2.getJieci());
						kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
						JiaoShi jshi = jiaoshiService
								.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
						kecheng.setJiaoshiming(jshi.getJiaoshiming());
						JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
						kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
						kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
						keChengs2.add(kecheng);
					}
				}
			/**
			 * map3.put("banjiid1", user.getBanjiid() + ",%");
			 * map3.put("banjiid2", "%," + user.getBanjiid() + ",%");
			 * map3.put("xueshengid1", user.getXueshengid() + ",%");
			 * map3.put("xueshengid2", "%," + user.getXueshengid() + ",%"); List
			 * <KeCheng> keChengs =
			 * kechengService.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(map3);
			 * for (KeCheng keCheng : keChengs) { if
			 * (!(keCheng.getDanshuangzhoushuoming().isEmpty())) { String
			 * danShuangZhouShuoMing[] =
			 * keCheng.getDanshuangzhoushuoming().split("zqxj"); switch
			 * (danShuangZhouShuoMing[0]) { case "1": // 连续 String zhouCi[] =
			 * danShuangZhouShuoMing[1].split(","); // 判断周次是否在 if
			 * (Integer.parseInt(zhouCi[0]) <= Integer.parseInt(zhou) &&
			 * Integer.parseInt(zhouCi[1]) >= Integer.parseInt(zhou) &&
			 * keCheng.getXuenian().equals(xuenian) &&
			 * keCheng.getXueqi().toString().equals(xueqi)) { // 判断 是否调停 String
			 * tiaoTing[] = danShuangZhouShuoMing[3].split("zqjx"); String
			 * tiaoKe[] = danShuangZhouShuoMing[4].split("zqjx"); // 获取周几，节次，教室
			 * String total[] = danShuangZhouShuoMing[2].split("zxqj"); int
			 * tiaoTingZhou = 0; for (String string2 : total) { String xinxi[] =
			 * string2.split(","); // 根据当前周，判断日期 int i = Integer.parseInt(zhou)
			 * - Integer.parseInt(zhouCi[0]) + 1; // 获取本周距离学期开始的天数 int day =
			 * (Integer.parseInt(zhouCi[0]) - 1 + (i - 1)) * 7 +
			 * Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 String shangkeriqi =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); int k = 0; for (String string
			 * : tiaoTing) { if (shangkeriqi.equals(string)) { k = 1; break; } }
			 * if (k == 0) { KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * 
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); } kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } if (tiaoKe.length > 0) { int i =
			 * Integer.parseInt(zhou) - Integer.parseInt(zhouCi[0]) + 1; for
			 * (int ii = 1; ii <= 7; ii++) { int days =
			 * (Integer.parseInt(zhouCi[0]) - 1 + (i - 1)) * 7 + ii - 1; //
			 * 获取每次上课日期 String s = simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) days * 24 * 60 * 60 * 1000)); for (String string : tiaoKe)
			 * { int k2 = 0; if (s.equals(string.split(",")[0])) { k2 = 1;
			 * tiaoTingZhou = ii; } if (k2 == 1) { String kc[] =
			 * string.split(","); KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * 
			 * kecheng.setZhouci(tiaoTingZhou); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
			 * .getJiaoXueLouMing()); kecheng.setXiaoquming(xiaoquService
			 * .selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng()); }
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } } }
			 * 
			 * } break;
			 * 
			 * case "2": // 单双周 String zhouCi2[] =
			 * danShuangZhouShuoMing[1].split(","); // 判断 是否调停 String tiaoTing[]
			 * = danShuangZhouShuoMing[3].split("zqjx"); String tiaoKe[] =
			 * danShuangZhouShuoMing[4].split("zqjx"); // 判断周次是否在 if
			 * (Integer.parseInt(zhouCi2[0]) <= Integer.parseInt(zhou) &&
			 * Integer.parseInt(zhouCi2[1]) >= Integer.parseInt(zhou) &&
			 * keCheng.getXuenian().equals(xuenian) &&
			 * keCheng.getXueqi().toString().equals(xueqi)) { // int
			 * tiaoTingZhou = 0; // 判断单双周 String danShuang[] =
			 * danShuangZhouShuoMing[2].split("zqjx"); if
			 * (Integer.parseInt(zhou) % 2 == 0) { // 双周 // 获取周几，节次，教室 String
			 * total[] = danShuang[1].split("zxqj"); for (String string2 :
			 * total) { String xinxi[] = string2.split(","); // 根据当前周，判断日期 int i
			 * = Integer.parseInt(zhou) - Integer.parseInt(zhouCi2[0]) + 1; //
			 * 获取本周距离学期开始的天数 int day = (Integer.parseInt(zhouCi2[0]) - 1 + (i -
			 * 1)) * 7 + Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 String
			 * shangkeriqi = simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); int k = 0; for (String string
			 * : tiaoTing) { if (shangkeriqi.equals(string)) { k = 1; break; } }
			 * 
			 * if (k == 0) { KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * 
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()) .getMingcheng()); }
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } }
			 * 
			 * } else { // 单周 // 获取周几，节次，教室 String total[] =
			 * danShuang[0].split("zxqj"); for (String string2 : total) { String
			 * xinxi[] = string2.split(","); // 根据当前周，判断日期 int i =
			 * Integer.parseInt(zhou) - Integer.parseInt(zhouCi2[0]) + 1; //
			 * 获取本周距离学期开始的天数 int day = (Integer.parseInt(zhouCi2[0]) - 1 + (i -
			 * 1)) * 7 + Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 String
			 * shangkeriqi = simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); int k = 0; for (String string
			 * : tiaoTing) { if (shangkeriqi.equals(string)) { k = 1; // break;
			 * } } if (k == 0) { KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()) .getMingcheng()); }
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } }
			 * 
			 * } if (tiaoKe.length > 0) { int i = Integer.parseInt(zhou) -
			 * Integer.parseInt(zhouCi2[0]) + 1; int tiaoTingZhou = 0; for (int
			 * ii = 1; ii <= 7; ii++) { int days = (Integer.parseInt(zhouCi2[0])
			 * - 1 + (i - 1)) * 7 + ii - 1; // 获取每次上课日期 String s =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) days * 24 * 60 * 60 * 1000)); for (String string : tiaoKe)
			 * { int k2 = 0; if (s.equals(string.split(",")[0])) { k2 = 1;
			 * tiaoTingZhou = ii; } if (k2 == 1) { String kc[] =
			 * string.split(","); KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(tiaoTingZhou); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()) .getMingcheng()); }
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } } } break; case "3": // 单次，不连续,只需比对时间
			 * String kcheng[] = danShuangZhouShuoMing[1].split("zqjx"); int i =
			 * Integer.parseInt(zhou); int zhous = 0; for (int ii = 1; ii <= 7;
			 * ii++) { int days = (i - 1) * 7 + ii - 1; // 获取每次上课日期 String s =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) days * 24 * 60 * 60 * 1000)); for (String string : kcheng)
			 * { int k2 = 0; if (s.equals(string.split(",")[0])) { k2 = 1; zhous
			 * = ii; } if (k2 == 1) { String kc[] = string.split(","); KeCheng
			 * kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi()); //
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0])); //
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(zhous); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3])); if
			 * (jshi.getJiaoshiming().equals("待定")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * if (jshi.getJiaoshiming().equals("现场参观")) {
			 * kecheng.setJiaoXueLouMing(""); kecheng.setXiaoquming(""); } else
			 * { kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); } kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * keChengs2.add(kecheng); } } } break; } } }
			 */
			// 获取上课周总数
			int count = keChengs2.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<KeCheng> keChengs3 = new ArrayList<>();
				if (count < 10) {

					for (int i = 0; i < count; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				} else {
					for (int i = 0; i < 10; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				}
			} else if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<KeCheng> keChengs3 = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++)
							keChengs3.add(keChengs2.get(i));
						mv.addObject("kecheng", keChengs3);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++)
							keChengs3.add(keChengs2.get(i));
						mv.addObject("kecheng", keChengs3);
					}
				} else if (page == pages) {
					List<KeCheng> keChengs3 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				} else {
					response.sendRedirect("logout");
					return null;
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
			mv.addObject("shangwunum", shangwunum);
			mv.addObject("xiawunum", xiawunum);
			mv.addObject("wanshangnum", wanshangnum);
			mv.addObject("jicinum", jiecinum);
			mv.addObject("xuenians", xuenians);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xueqi", xueqi);
			mv.addObject("zhou", zhou);
			mv.addObject("zhounum", zhounum);
			mv.addObject("jcsj", jcsjs);
			mv.addObject("kecheng", keChengs2);
			mv.setViewName("stu/wodekecheng");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "chaxunkechenglist") // 查询课程列表显示
	public ModelAndView chaxunkechenglist(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String xueXiaoID = (String) session.getAttribute("xuexiaoid");
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			String zhou = request.getParameter("zhou");
			XueSheng user = (XueSheng) session.getAttribute("user");
			String banjiid1 = user.getBanjiid().toString() + ",%";
			String banjiid2 = "%," + user.getBanjiid().toString() + ",%";
			String fudaoyuanid = fudaoyuanService.getBybanJiID(banjiid1, banjiid2).getFudaoyuanid().toString();
			/*
			 * String xueshengid1 = user.getXueshengid().toString()+",1;%";
			 * String xueshengid2 = "%;"+user.getXueshengid().toString()+",1;%";
			 * List<KeCheng> keChengs =
			 * kechengService.getAllByxueShengID(xueshengid1,xueshengid2);
			 */
			Map<String, String> map3 = new HashMap<>();
			map3.put("banjiid1", user.getBanjiid() + ",%");
			map3.put("banjiid2", "%," + user.getBanjiid() + ",%");
			map3.put("xueshengid1", user.getXueshengid() + ",%");
			map3.put("xueshengid2", "%," + user.getXueshengid() + ",%");
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			List<KeCheng> keChengs = kechengService.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(map3);
			// 获取当前周的周一和周日
			Map<String, String> map = new HashMap<>();
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			map.put("xuexiaoid", xueXiaoID);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			if (xueQi == null) {
				mv.addObject("xuenians", xuenians);
				mv.setViewName("fudaoyuan/xueshengkecheng");
				return mv;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (KeCheng keCheng : keChengs)
				if (!(keCheng.getDanshuangzhoushuoming().isEmpty())) {
					String danShuangZhouShuoMing[] = keCheng.getDanshuangzhoushuoming().split("zqxj");
					switch (danShuangZhouShuoMing[0]) {
					case "1": // 连续
						String zhouCi[] = danShuangZhouShuoMing[1].split(",");
						// 判断周次是否在
						if (Integer.parseInt(zhouCi[0]) <= Integer.parseInt(zhou)
								&& Integer.parseInt(zhouCi[1]) >= Integer.parseInt(zhou)
								&& keCheng.getXuenian().equals(xuenian)
								&& keCheng.getXueqi().toString().equals(xueqi)) {
							// 判断 是否调停
							String tiaoTing[] = danShuangZhouShuoMing[3].split("zqjx");
							String tiaoKe[] = danShuangZhouShuoMing[4].split("zqjx");
							// 获取周几，节次，教室
							String total[] = danShuangZhouShuoMing[2].split("zxqj");
							int tiaoTingZhou = 0;
							for (String string2 : total) {
								String xinxi[] = string2.split(",");
								// 根据当前周，判断日期
								int i = Integer.parseInt(zhou) - Integer.parseInt(zhouCi[0]) + 1;
								// 获取本周距离学期开始的天数
								int day = (Integer.parseInt(zhouCi[0]) - 1 + (i - 1)) * 7 + Integer.parseInt(xinxi[0])
										- 1;
								// 获取每次上课日期
								String shangkeriqi = simpleDateFormat
										.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
												+ (long) day * 24 * 60 * 60 * 1000));
								int k = 0;
								for (String string : tiaoTing)
									if (shangkeriqi.equals(string)) {
										k = 1;
										break;
									}
								if (k == 0) {
									KeCheng kecheng = new KeCheng();
									kecheng.setKechengid(keCheng.getKechengid());
									kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
									kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
									kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
									kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));

									kecheng.setZhouci(Integer.parseInt(xinxi[0]));
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());

									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
									if (jshi.getJiaoshiming().equals("待定")) {
										kecheng.setJiaoXueLouMing("");
										kecheng.setXiaoquming("");
									} else if (jshi.getJiaoshiming().equals("现场参观")) {
										kecheng.setJiaoXueLouMing("");
										kecheng.setXiaoquming("");
									} else {
										kecheng.setJiaoXueLouMing(jiaoXueLouService
												.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
									}
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									keChengs2.add(kecheng);
								}
							}
							if (tiaoKe.length > 0) {
								int i = Integer.parseInt(zhou) - Integer.parseInt(zhouCi[0]) + 1;
								for (int ii = 1; ii <= 7; ii++) {
									int days = (Integer.parseInt(zhouCi[0]) - 1 + (i - 1)) * 7 + ii - 1;
									// 获取每次上课日期
									String s = simpleDateFormat
											.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
													+ (long) days * 24 * 60 * 60 * 1000));
									for (String string : tiaoKe) {
										int k2 = 0;
										if (s.equals(string.split(",")[0])) {
											k2 = 1;
											tiaoTingZhou = ii;
										}
										if (k2 == 1) {
											String kc[] = string.split(",");
											KeCheng kecheng = new KeCheng();
											kecheng.setKechengid(keCheng.getKechengid());
											kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
											kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
											kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
											kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));

											kecheng.setZhouci(tiaoTingZhou);
											JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1]));
											JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
											kecheng.setKaishijieci(jcsj1.getJieci());
											kecheng.setJieshujieci(jcsj2.getJieci());

											JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
											if (jshi.getJiaoshiming().equals("待定")) {
												kecheng.setJiaoXueLouMing("");
												kecheng.setXiaoquming("");
											} else if (jshi.getJiaoshiming().equals("现场参观")) {
												kecheng.setJiaoXueLouMing("");
												kecheng.setXiaoquming("");
											} else {
												kecheng.setJiaoXueLouMing(
														jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
																.getJiaoXueLouMing());
												kecheng.setXiaoquming(xiaoquService
														.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
											}
											kecheng.setJiaoshiming(jshi.getJiaoshiming());
											keChengs2.add(kecheng);
										}
									}
								}
							}

						}
						break;

					case "2": // 单双周
						String zhouCi2[] = danShuangZhouShuoMing[1].split(",");
						// 判断 是否调停
						String tiaoTing[] = danShuangZhouShuoMing[3].split("zqjx");
						String tiaoKe[] = danShuangZhouShuoMing[4].split("zqjx");
						// 判断周次是否在
						if (Integer.parseInt(zhouCi2[0]) <= Integer.parseInt(zhou)
								&& Integer.parseInt(zhouCi2[1]) >= Integer.parseInt(zhou)
								&& keCheng.getXuenian().equals(xuenian)
								&& keCheng.getXueqi().toString().equals(xueqi)) {
							// int tiaoTingZhou = 0;
							// 判断单双周
							String danShuang[] = danShuangZhouShuoMing[2].split("zqjx");
							if (Integer.parseInt(zhou) % 2 == 0) { // 双周
								// 获取周几，节次，教室
								String total[] = danShuang[1].split("zxqj");
								for (String string2 : total) {
									String xinxi[] = string2.split(",");
									// 根据当前周，判断日期
									int i = Integer.parseInt(zhou) - Integer.parseInt(zhouCi2[0]) + 1;
									// 获取本周距离学期开始的天数
									int day = (Integer.parseInt(zhouCi2[0]) - 1 + (i - 1)) * 7
											+ Integer.parseInt(xinxi[0]) - 1;
									// 获取每次上课日期
									String shangkeriqi = simpleDateFormat
											.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
													+ (long) day * 24 * 60 * 60 * 1000));
									int k = 0;
									for (String string : tiaoTing)
										if (shangkeriqi.equals(string)) {
											k = 1;
											break;
										}

									if (k == 0) {
										KeCheng kecheng = new KeCheng();
										kecheng.setKechengid(keCheng.getKechengid());
										kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
										kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
										kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
										kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));

										kecheng.setZhouci(Integer.parseInt(xinxi[0]));
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());

										JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
										if (jshi.getJiaoshiming().equals("待定")) {
											kecheng.setJiaoXueLouMing("");
											kecheng.setXiaoquming("");
										} else if (jshi.getJiaoshiming().equals("现场参观")) {
											kecheng.setJiaoXueLouMing("");
											kecheng.setXiaoquming("");
										} else {
											kecheng.setJiaoXueLouMing(jiaoXueLouService
													.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
											kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid())
													.getMingcheng());
										}
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										keChengs2.add(kecheng);
									}
								}

							} else { // 单周
										// 获取周几，节次，教室
								String total[] = danShuang[0].split("zxqj");
								for (String string2 : total) {
									String xinxi[] = string2.split(",");
									// 根据当前周，判断日期
									int i = Integer.parseInt(zhou) - Integer.parseInt(zhouCi2[0]) + 1;
									// 获取本周距离学期开始的天数
									int day = (Integer.parseInt(zhouCi2[0]) - 1 + (i - 1)) * 7
											+ Integer.parseInt(xinxi[0]) - 1;
									// 获取每次上课日期
									String shangkeriqi = simpleDateFormat
											.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
													+ (long) day * 24 * 60 * 60 * 1000));
									int k = 0;
									for (String string : tiaoTing)
										if (shangkeriqi.equals(string))
											k = 1;
									// break;
									if (k == 0) {
										KeCheng kecheng = new KeCheng();
										kecheng.setKechengid(keCheng.getKechengid());
										kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
										kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
										kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
										kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
										kecheng.setZhouci(Integer.parseInt(xinxi[0]));
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());

										JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
										if (jshi.getJiaoshiming().equals("待定")) {
											kecheng.setJiaoXueLouMing("");
											kecheng.setXiaoquming("");
										} else if (jshi.getJiaoshiming().equals("现场参观")) {
											kecheng.setJiaoXueLouMing("");
											kecheng.setXiaoquming("");
										} else {
											kecheng.setJiaoXueLouMing(jiaoXueLouService
													.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
											kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid())
													.getMingcheng());
										}
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										keChengs2.add(kecheng);
									}

									// if (tiaoKe.length > 0) {
									// for (int ii = 1; ii <= 7; ii++) {
									// int days = (Integer.parseInt(zhouCi2[0])
									// - 1 + (i - 1)) * 7 + ii - 1;
									// // 获取每次上课日期
									// String s = simpleDateFormat.format(
									// new
									// Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
									// + (long) days * 24 * 60 * 60 * 1000));
									// for (String string : tiaoKe) {
									// int k2 = 0;
									// if (s.equals(string.split(",")[0])) {
									// k2 = 1;
									// tiaoTingZhou = ii;
									// }
									// if (k2 == 1) {
									// String kc[] = string.split(",");
									// KeCheng kecheng = new KeCheng();
									// kecheng.setKechengid(keCheng.getKechengid());
									// kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
									// kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
									// kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
									// kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
									// kecheng.setZhouci(tiaoTingZhou);
									// JCSJ jcsj1 = jcsjService
									// .selectByPrimaryKey(Integer.parseInt(kc[1]));
									// JCSJ jcsj2 = jcsjService
									// .selectByPrimaryKey(Integer.parseInt(kc[2]));
									// kecheng.setKaishijieci(jcsj1.getJieci());
									// kecheng.setJieshujieci(jcsj2.getJieci());
									//
									// JiaoShi jshi = jiaoshiService
									// .selectByPrimaryKey(Integer.parseInt(kc[3]));
									// kecheng.setJiaoshiming(jshi.getJiaoshiming());
									// kecheng.setJiaoXueLouMing(
									// jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
									// .getJiaoXueLouMing());
									// kecheng.setXiaoquming(xiaoquService
									// .selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
									// keChengs2.add(kecheng);
									// }
									// }
									// }
									// }
								}
							}

						}
						if (tiaoKe.length > 0) {
							int i = Integer.parseInt(zhou) - Integer.parseInt(zhouCi2[0]) + 1;
							int tiaoTingZhou = 0;
							for (int ii = 1; ii <= 7; ii++) {
								int days = (Integer.parseInt(zhouCi2[0]) - 1 + (i - 1)) * 7 + ii - 1;
								// 获取每次上课日期
								String s = simpleDateFormat
										.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
												+ (long) days * 24 * 60 * 60 * 1000));
								for (String string : tiaoKe) {
									int k2 = 0;
									if (s.equals(string.split(",")[0])) {
										k2 = 1;
										tiaoTingZhou = ii;
									}
									if (k2 == 1) {
										String kc[] = string.split(",");
										KeCheng kecheng = new KeCheng();
										kecheng.setKechengid(keCheng.getKechengid());
										kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
										kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
										kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
										kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
										kecheng.setZhouci(tiaoTingZhou);
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1]));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());

										JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
										if (jshi.getJiaoshiming().equals("待定")) {
											kecheng.setJiaoXueLouMing("");
											kecheng.setXiaoquming("");
										} else if (jshi.getJiaoshiming().equals("现场参观")) {
											kecheng.setJiaoXueLouMing("");
											kecheng.setXiaoquming("");
										} else {
											kecheng.setJiaoXueLouMing(jiaoXueLouService
													.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
											kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid())
													.getMingcheng());
										}
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										keChengs2.add(kecheng);
									}
								}
							}
						}
						break;
					case "3": // 单次，不连续,只需比对时间
						String kcheng[] = danShuangZhouShuoMing[1].split("zqjx");
						int i = Integer.parseInt(zhou);
						int zhous = 0;
						for (int ii = 1; ii <= 7; ii++) {
							int days = (i - 1) * 7 + ii - 1;
							// 获取每次上课日期
							String s = simpleDateFormat
									.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
											+ (long) days * 24 * 60 * 60 * 1000));
							for (String string : kcheng) {
								int k2 = 0;
								if (s.equals(string.split(",")[0])) {
									k2 = 1;
									zhous = ii;
								}
								if (k2 == 1) {
									String kc[] = string.split(",");
									KeCheng kecheng = new KeCheng();
									kecheng.setKechengid(keCheng.getKechengid());
									kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
									kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
									// kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
									// kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
									kecheng.setZhouci(zhous);
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1]));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());

									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
									if (jshi.getJiaoshiming().equals("待定")) {
										kecheng.setJiaoXueLouMing("");
										kecheng.setXiaoquming("");
									} else if (jshi.getJiaoshiming().equals("现场参观")) {
										kecheng.setJiaoXueLouMing("");
										kecheng.setXiaoquming("");
									} else {
										kecheng.setJiaoXueLouMing(jiaoXueLouService
												.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
									}
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									keChengs2.add(kecheng);
								}
							}
						}
						break;
					}
				}
			// 获取上课周总数
			Date date1 = simpleDateFormat.parse(xueQi.getKaishiriqi());
			Date date2 = simpleDateFormat.parse(xueQi.getJieshuriqi());
			int zhounum = Util.WeeksBetweenDays(date1, date2);
			int count = keChengs2.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<KeCheng> keChengs3 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				} else {
					for (int i = 0; i < 10; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				}
			} else if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<KeCheng> keChengs3 = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++)
							keChengs3.add(keChengs2.get(i));
						mv.addObject("kecheng", keChengs3);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++)
							keChengs3.add(keChengs2.get(i));
						mv.addObject("kecheng", keChengs3);
					}
				} else if (page == pages) {
					List<KeCheng> keChengs3 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++)
						keChengs3.add(keChengs2.get(i));
					mv.addObject("kecheng", keChengs3);
				} else {
					response.sendRedirect("logout");
					return null;
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
			mv.addObject("user", user);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xuenians", xuenians);
			mv.addObject("zhounum", zhounum);
			mv.addObject("zhou", zhou);
			mv.addObject("xueqi", xueqi);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			// System.out.println(keChengs.size());
			mv.setViewName("stu/kechengliebiao");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "chakancanyuren_kecheng_inkecheng") // 查看参与人
	public ModelAndView chakancanyuren_kecheng_inkecheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (keCheng == null) {
				response.sendRedirect("logout");
				return null;
			}
			List<String> xueShengIdList = new ArrayList<>();
			if (keCheng.getKeDaiBiao() != null && !keCheng.getKeDaiBiao().equals("")) {
				String[] keDaiBiao = keCheng.getKeDaiBiao().split(";");
				for (int i = 0; i < keDaiBiao.length; i++)
					xueShengIdList.add(keDaiBiao[i].split(",")[0]);
			}
			List<Map<String, String>> maps = new ArrayList<>();
			List<String> banJiIDs = kechengService.getByAllBanJiIDByID(id);
			List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
			List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(id);
			if (keCheng.getLeixing() == 1) {
				if (banJiIDs != null && banJiIDs.size() > 0)
					for (String string : banJiIDs) {
						List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng : xueShengs) {
							int i = 0;
							Map<String, String> map = new HashMap<>();
							if (mianXiuIDs != null && mianXiuIDs.size() > 0)
								for (String string2 : mianXiuIDs)
									if (xueSheng.getXueshengid().toString().equals(string2)) {
										i = 1;
										break;
									}
							if (i == 1)
								map.put("mianxiu", "1");
							else
								map.put("mianxiu", "0");
							if (xueShengIdList.contains(xueSheng.getXueshengid().toString()))
								map.put("kedaibiao", "1");
							else
								map.put("kedaibiao", "0");
							String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
									.getBanjimingcheng();
							map.put("xueshengid", xueSheng.getXueshengid().toString());
							map.put("banji", banjimingcheng);
							map.put("xuehao", xueSheng.getXuehao());
							map.put("xingming", xueSheng.getXingming());
							map.put("banjiid", xueSheng.getBanjiid().toString());
							maps.add(map);
						}
					}
				if (xuanXiuIDs != null && xuanXiuIDs.size() > 0)
					for (String string : xuanXiuIDs) {
						int i = 0;
						for (Map<String, String> m : maps)
							if (string.equals(m.get("xueshengid"))) {
								i = 1;
								break;
							}
						if (i == 0) {
							Map<String, String> map = new HashMap<>();
							XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(string));
							String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
									.getBanjimingcheng();
							map.put("xueshengid", xueSheng.getXueshengid().toString());
							map.put("banji", banjimingcheng);
							map.put("xuehao", xueSheng.getXuehao());
							map.put("xingming", xueSheng.getXingming());
							maps.add(map);
						}
					}
			}
			if (keCheng.getLeixing() == 2)
				if (xuanXiuIDs != null && xuanXiuIDs.size() > 0)
					for (String string : xuanXiuIDs) {
						XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(string));
						int i = 0;
						Map<String, String> map = new HashMap<>();
						if (mianXiuIDs != null && mianXiuIDs.size() > 0)
							for (String string2 : mianXiuIDs)
								if (xueSheng.getXueshengid().toString().equals(string2)) {
									i = 1;
									break;
								}
						if (i == 1)
							map.put("mianxiu", "1");
						else
							map.put("mianxiu", "0");
						if (xueShengIdList.contains(xueSheng.getXueshengid().toString()))
							map.put("kedaibiao", "1");
						else
							map.put("kedaibiao", "0");
						String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
								.getBanjimingcheng();
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("banji", banjimingcheng);
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						map.put("banjiid", xueSheng.getBanjiid().toString());
						maps.add(map);
					}

			/**
			 * if (keCheng.getBanjiids() != null &&
			 * !keCheng.getBanjiids().equals("")) { String banjiids =
			 * keCheng.getBanjiids(); String banjiid[] = banjiids.split(",");
			 * String mianxiuids = keCheng.getMianxiuids(); for (String string :
			 * banjiid) { List<XueSheng> xueShengs =
			 * xueshengService.getAllByBanJiID(Integer.parseInt(string)); for
			 * (XueSheng xueSheng : xueShengs) { int i = 0; Map<String, String>
			 * map = new HashMap<>(); if (mianxiuids != null &&
			 * !mianxiuids.equals("")) { String mianxiuid[] =
			 * mianxiuids.split(","); for (String string2 : mianxiuid) { if
			 * (xueSheng.getXueshengid().toString().equals(string2)) { i = 1;
			 * break; } } } if (i == 1) { map.put("mianxiu", "1"); } else {
			 * map.put("mianxiu", "0"); } String banjimingcheng =
			 * banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
			 * .getBanjimingcheng(); map.put("xueshengid",
			 * xueSheng.getXueshengid().toString()); map.put("banji",
			 * banjimingcheng); map.put("xuehao", xueSheng.getXuehao());
			 * map.put("xingming", xueSheng.getXingming()); maps.add(map); } } }
			 * if (keCheng.getXuanxiuids() != null &&
			 * !keCheng.getXuanxiuids().equals("")) { String xuanxiuids =
			 * keCheng.getXuanxiuids(); String xuanxiuid[] =
			 * xuanxiuids.split(","); for (String string : xuanxiuid) {
			 * Map<String, String> map = new HashMap<>(); XueSheng xueSheng =
			 * xueshengService.getUserById(Integer.parseInt(string)); String
			 * banjimingcheng =
			 * banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).
			 * getBanjimingcheng(); map.put("xueshengid",
			 * xueSheng.getXueshengid().toString()); map.put("banji",
			 * banjimingcheng); map.put("xuehao", xueSheng.getXuehao());
			 * map.put("xingming", xueSheng.getXingming()); maps.add(map); } }
			 */

			keCheng.setMaps(maps);
			mv.addObject("kecheng", keCheng);
			mv.setViewName("stu/chakancanyuren_kecheng_inkecheng");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "delkecheng") // 删除课程
	public void delkecheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return;
			}
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (kecheng == null) {
				response.sendRedirect("logout");
				return;
			}
			String canyurens = "";
			List<String> mianXius = kechengService.getAllMianXiuIDByID(id);
			if (kecheng.getLeixing() == 1) {
				List<String> banJis = kechengService.getByAllBanJiIDByID(id);
				if (banJis != null && banJis.size() > 0) {
					for (String string : banJis) {
						List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng : xueshengs) {
							int n = 0;
							for (String s : mianXius) {
								if (s.equals(xueSheng.getXueshengid().toString())) {
									n = 1;
								}
							}
							if (n == 0) {
								canyurens += xueSheng.getXueshengid().toString() + ",";
							}
						}
					}
				}
			}
			if (kecheng.getLeixing() == 2) {
				List<String> xuanXius = kechengService.getAllXuanXiuIDByID(id);
				for (String string : xuanXius) {
					int n = 0;
					for (String s : mianXius) {
						if (s.equals(string)) {
							n = 1;
						}
					}
					if (n == 0) {
						canyurens += string + ",";
					}
				}
			}
			Map<String, Integer> mapss = new HashMap<>();
			mapss.put("xuexiaoid", Integer.parseInt(user.getXuexiaoXuehao().split("_")[0]));
			mapss.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(mapss);
			int jiecifanganid = jieCiFangAn.getId();
			int j = 0;
			List<Map<String, Object>> shiJianMap = kechengService.getShangKeShiJianByID(id);
			if (shiJianMap != null && !"".equals(shiJianMap)) {
				if (shiJianMap.size() == 1) {
					Map<String, String> map = new HashMap<>();
					map.put("kechengid", id);
					// 没课的话---删班级、删时间、删课程
					try {
						kechengService.delete_allshangkexuanxiurenByID(id);
						kechengService.delete_shangkemianxiuren(map);
						kechengService.delete_allshangkebanjiByID(id);
						kechengService.delete_allshangkeshijianByID(id);
						kechengService.deleteByPrimaryKey(Integer.parseInt(id));
						j = 1;
					} catch (Exception e) {
						e.printStackTrace();
						j = 0;
					}
				} else {
					// 只删时间
					if (!"".equals(request.getParameter("xinxi")) && request.getParameter("xinxi") != null) {
						for (Map<String, Object> keChengMap : shiJianMap) {
							String xinxi[] = request.getParameter("xinxi").split("/");
							if (xinxi[0].equals("3")) {
								if (keChengMap.containsKey("shiJianLeiXing")
										&& (keChengMap.get("shiJianLeiXing").toString().equals("4")
												|| keChengMap.get("shiJianLeiXing").toString().equals("6"))) {
									// ${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}
									String zhoushu[] = xinxi[2].split(",");
									for (String string : zhoushu) {
										if (string.equals(keChengMap.get("kaiShiZhou").toString())
												&& xinxi[1].equals(keChengMap.get("zhouCi").toString())) {
											String kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
													xinxi[3]) + "";
											String jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
													xinxi[4]) + "";
											if (kaishi.equals(keChengMap.get("kaiShiJieCi").toString())
													&& jieshu.equals(keChengMap.get("jieShuJieCi").toString())) {
												try {
													Map<String, String> map = new HashMap<>();
													map.put("kechengid", id);
													map.put("kaishizhou", string);
													map.put("zhouci", xinxi[1]);
													map.put("kaishijieci", kaishi);
													map.put("jieshujieci", jieshu);
													if (keChengMap.get("shiJianLeiXing").toString().equals("4")) {
														map.put("leixing", "4");
													}
													if (keChengMap.get("shiJianLeiXing").toString().equals("6")) {
														map.put("leixing", "6");
													}
													kechengService.delete_shangkeshijian(map);
													j = 1;
												} catch (Exception e) {
													e.printStackTrace();
													j = 0;
												}
											}
										}
									}
								}
							} else {
								// ${KeCheng.leixing}/${
								// maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.kaishizhou}/${maps.jieshuzhou}
								if (xinxi[2].equals("1")) {
									if (keChengMap.containsKey("shiJianLeiXing")
											&& keChengMap.get("shiJianLeiXing").toString().equals("2")) {
										if (xinxi[5].equals(keChengMap.get("kaiShiZhou").toString())
												&& xinxi[6].equals(keChengMap.get("jieShuZhou").toString())
												&& xinxi[1].equals(keChengMap.get("zhouCi").toString())) {
											String kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
													xinxi[3]) + "";
											String jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
													xinxi[4]) + "";
											if (kaishi.equals(keChengMap.get("kaiShiJieCi").toString())
													&& jieshu.equals(keChengMap.get("jieShuJieCi").toString())) {
												try {
													Map<String, String> map = new HashMap<>();
													map.put("kechengid", id);
													map.put("kaishizhou", xinxi[5]);
													map.put("jieshuzhou", xinxi[6]);
													map.put("zhouci", xinxi[1]);
													map.put("kaishijieci", kaishi);
													map.put("jieshujieci", jieshu);
													map.put("leixing", "2");
													kechengService.delete_shangkeshijian(map);
													j = 1;
												} catch (Exception e) {
													e.printStackTrace();
													j = 0;
												}
											}
										}
									}
								} else if (xinxi[2].equals("2")) {
									if (keChengMap.containsKey("shiJianLeiXing")
											&& keChengMap.get("shiJianLeiXing").toString().equals("3")) {
										if (xinxi[5].equals(keChengMap.get("kaiShiZhou").toString())
												&& xinxi[6].equals(keChengMap.get("jieShuZhou").toString())
												&& xinxi[1].equals(keChengMap.get("zhouCi").toString())) {
											String kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
													xinxi[3]) + "";
											String jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
													xinxi[4]) + "";
											if (kaishi.equals(keChengMap.get("kaiShiJieCi").toString())
													&& jieshu.equals(keChengMap.get("jieShuJieCi").toString())) {
												try {
													Map<String, String> map = new HashMap<>();
													map.put("kechengid", id);
													map.put("kaishizhou", xinxi[5]);
													map.put("jieshuzhou", xinxi[6]);
													map.put("zhouci", xinxi[1]);
													map.put("kaishijieci", kaishi);
													map.put("jieshujieci", jieshu);
													map.put("leixing", "3");
													kechengService.delete_shangkeshijian(map);
													j = 1;
												} catch (Exception e) {
													e.printStackTrace();
													j = 0;
												}
											}
										}
									}
								} else {
									if (keChengMap.containsKey("shiJianLeiXing")
											&& keChengMap.get("shiJianLeiXing").toString().equals("1")) {
										if (xinxi[5].equals(keChengMap.get("kaiShiZhou").toString())
												&& xinxi[6].equals(keChengMap.get("jieShuZhou").toString())
												&& xinxi[1].equals(keChengMap.get("zhouCi").toString())) {
											String kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
													xinxi[3]) + "";
											String jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
													xinxi[4]) + "";
											if (kaishi.equals(keChengMap.get("kaiShiJieCi").toString())
													&& jieshu.equals(keChengMap.get("jieShuJieCi").toString())) {
												try {
													Map<String, String> map = new HashMap<>();
													map.put("kechengid", id);
													map.put("kaishizhou", xinxi[5]);
													map.put("jieshuzhou", xinxi[6]);
													map.put("zhouci", xinxi[1]);
													map.put("kaishijieci", kaishi);
													map.put("jieshujieci", jieshu);
													map.put("leixing", "1");
													kechengService.delete_shangkeshijian(map);
													j = 1;
												} catch (Exception e) {
													e.printStackTrace();
													j = 0;
												}
											}
										}
									}
								}

							}
						}
					} else {
						response.sendRedirect("login");
					}
				}
			}
			if (j == 1) {
				// // 发送极光消息
				// XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				// xiaoXiFaSong.setXiaoXiMingCheng(user.getXingming() +
				// "取消了这一门课《" + kecheng.getKechengmingcheng() + "》");
				// xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "取消了这一门课《"
				// + kecheng.getKechengmingcheng() + "》");
				// xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
				// xiaoXiFaSong.setShuJuLeiXing(1);
				// xiaoXiFaSong.setFaSongLeiXing(2);
				// xiaoXiFaSong.setShiFouChengGong(0);
				// xiaoXiFaSong.setXueXiaoId(user.getXuexiaoXuehao().split("_")[0]);
				// // 发送提醒消息
				// TiXing tiXing = new TiXing();
				// Date date2 = new Date();
				// tiXing.setNeirong(user.getXingming() + "取消了这一门课《" +
				// kecheng.getKechengmingcheng() + "》");
				// tiXing.setShijian(date2);
				// tiXing.setZhuangtai(0);
				// tiXing.setShujuid(Integer.parseInt(id));
				// tiXing.setType(1);
				// if (canyurens != null && !"".equals(canyurens)) {
				// String s[] = canyurens.split(",");
				// for (String xueshengid : s) {
				// tiXing.setJieshourenid(Integer.parseInt(xueshengid));
				// xiaoXiFaSong.setFaSongMuBiao(xueshengid.toString());
				// tixingService.insert(tiXing);
				// jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				// }
				// }
				out.print("<script>alert('删除成功！');</script>");
				out.print("<script>location='kechengliebiao';</script>");
			}
			if (j == 0) {
				out.print("<script>alert('操作失败！');</script>");
				out.print("<script>location='kechengliebiao';</script>");
			}

			/**
			 * int i = 0; String newxuanxiuids = ""; if (kecheng.getXuanxiuids()
			 * != null && kecheng.getXuanxiuids() != "") { String xuanxiuids =
			 * kecheng.getXuanxiuids(); String xuanxiuid[] =
			 * xuanxiuids.split(","); for (String string : xuanxiuid) { if
			 * (string.equals(user.getXueshengid().toString())) { i = 1;
			 * continue; } newxuanxiuids += string + ","; } } if (i == 1) {
			 * KeCheng keCheng2 = new KeCheng();
			 * keCheng2.setId(Integer.parseInt(id));
			 * keCheng2.setXuanxiuids(newxuanxiuids); int j =
			 * kechengService.updateBJandXXandMXByPrimaryKey(keCheng2); if (j !=
			 * 0) { if (!(user.getXueshengid().toString().equals("")) &&
			 * user.getIsbanzhang()) { // 发送极光消息 XiaoXiFaSong xiaoXiFaSong = new
			 * XiaoXiFaSong(); xiaoXiFaSong.setXiaoXiMingCheng(
			 * user.getXingming() + "取消了这一门课《" + kecheng.getKechengmingcheng() +
			 * "》"); xiaoXiFaSong.setXiaoXiNeiRong( user.getXingming() +
			 * "取消了这一门课《" + kecheng.getKechengmingcheng() + "》");
			 * xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
			 * xiaoXiFaSong.setShuJuLeiXing(1);
			 * xiaoXiFaSong.setFaSongLeiXing(2);
			 * xiaoXiFaSong.setShiFouChengGong(0);
			 * xiaoXiFaSong.setXueXiaoId(user.getXuexiaoXuehao().split("_")[0]);
			 * // 发送提醒消息 TiXing tiXing = new TiXing(); Date date2 = new Date();
			 * tiXing.setNeirong(user.getXingming() + "取消了这一门课《" +
			 * kecheng.getKechengmingcheng() + "》"); tiXing.setShijian(date2);
			 * tiXing.setZhuangtai(0); tiXing.setShujuid(Integer.parseInt(id));
			 * tiXing.setType(1); List<Integer> xueShengid =
			 * xueshengService.getXueShengsByBanJiID(user.getBanjiid()); for
			 * (Integer xueshengid : xueShengid) {
			 * tiXing.setJieshourenid(xueshengid);
			 * xiaoXiFaSong.setFaSongMuBiao(xueshengid.toString());
			 * tixingService.insert(tiXing);
			 * jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong); } }
			 * 
			 * out.print("<script>alert('删除成功！');</script>"); out.print(
			 * "<script>location='kechengliebiao';</script>"); } else {
			 * out.print("<script>alert('删除失败！');</script>"); out.print(
			 * "<script>location='kechengliebiao';</script>"); } } else { String
			 * mianxiuids = kecheng.getMianxiuids(); String newmianxiuids =
			 * mianxiuids + user.getXueshengid().toString() + ","; KeCheng
			 * keCheng2 = new KeCheng(); keCheng2.setId(Integer.parseInt(id));
			 * keCheng2.setMianxiuids(newmianxiuids); int j =
			 * kechengService.updateBJandXXandMXByPrimaryKey(keCheng2); if (j !=
			 * 0) { if (!(user.getXueshengid().toString().equals("")) &&
			 * user.getIsbanzhang()) { // 发送极光消息 XiaoXiFaSong xiaoXiFaSong = new
			 * XiaoXiFaSong(); xiaoXiFaSong.setXiaoXiMingCheng(
			 * user.getXingming() + "取消了这一门课《" + kecheng.getKechengmingcheng() +
			 * "》"); xiaoXiFaSong.setXiaoXiNeiRong( user.getXingming() +
			 * "取消了这一门课《" + kecheng.getKechengmingcheng() + "》");
			 * xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
			 * xiaoXiFaSong.setShuJuLeiXing(1);
			 * xiaoXiFaSong.setFaSongLeiXing(2);
			 * xiaoXiFaSong.setShiFouChengGong(0);
			 * xiaoXiFaSong.setXueXiaoId(user.getXuexiaoXuehao().split("_")[0]);
			 * // 发送提醒消息 TiXing tiXing = new TiXing(); Date date2 = new Date();
			 * tiXing.setNeirong(user.getXingming() + "取消了这一门课《" +
			 * kecheng.getKechengmingcheng() + "》"); tiXing.setShijian(date2);
			 * tiXing.setZhuangtai(0); tiXing.setShujuid(Integer.parseInt(id));
			 * tiXing.setType(1); List<Integer> xueShengid =
			 * xueshengService.getXueShengsByBanJiID(user.getBanjiid()); for
			 * (Integer xueshengid : xueShengid) {
			 * tiXing.setJieshourenid(xueshengid);
			 * xiaoXiFaSong.setFaSongMuBiao(xueshengid.toString());
			 * tixingService.insert(tiXing);
			 * jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong); } }
			 * 
			 * out.print("<script>alert('删除成功！');</script>"); out.print(
			 * "<script>location='kechengliebiao';</script>"); } else {
			 * out.print("<script>alert('删除失败！');</script>"); out.print(
			 * "<script>location='kechengliebiao';</script>"); } }
			 */
		} else
			response.sendRedirect("login");
	}

	@RequestMapping(value = "mianxiukecheng") // 免修课程
	public void mianxiukecheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return;
			}
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (kecheng == null) {
				response.sendRedirect("logout");
				return;
			}
			int j = 0;
			String cyr = "";
			try {
				Map<String, String> map = new HashMap<>();
				map.put("kechengid", id);
				map.put("xueshengid", user.getXueshengid().toString());
				kechengService.insert_shangkemianxiuren(map);
				kechengService.delete_shangkexuanxiuren(map);
				j = 2;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (j == 2) {
				out.print("<script>alert('免修成功！');</script>");
				out.print("<script>location='kechengliebiao';</script>");
			}
			if (j == 0) {
				out.print("<script>alert('操作失败！');</script>");
				out.print("<script>location='kechengliebiao';</script>");
			}
		} else
			response.sendRedirect("login");
	}

	/**
	 * @RequestMapping(value = "addkecheng")//新增课程——选择添加 public ModelAndView
	 *                       addkecheng(HttpServletRequest request,
	 *                       HttpServletResponse response) throws IOException {
	 *                       ModelAndView mv = new ModelAndView(); HttpSession
	 *                       session = request.getSession(); if
	 *                       (Util.checkSession(request)) { XueSheng user =
	 *                       (XueSheng) session.getAttribute("user"); int
	 *                       banjiid = user.getBanjiid(); String yuanxiid =
	 *                       banjiService.selectByPrimaryKey(banjiid).
	 *                       getYuanxiid().toString(); //获取当前学年、学期 Date date =
	 *                       new Date(); SimpleDateFormat riqi = new
	 *                       SimpleDateFormat("yyyy-MM-dd"); String
	 *                       xuexiao_xuehao = user.getXuexiaoXuehao(); String
	 *                       xueXiaoID = xuexiao_xuehao.substring(0,
	 *                       xuexiao_xuehao.lastIndexOf("_")); Map<String,
	 *                       String> map = new HashMap<>(); map.put("riqi",
	 *                       riqi.format(date)); map.put("xueXiaoID",
	 *                       xueXiaoID); XueQi xueQi =
	 *                       xueqiService.getByxueXiaoIDandriQi(map);
	 *                       if(xueQi==null){ mv.setViewName("stu/addkecheng");
	 *                       return mv; } String xuenian = xueQi.getXuenian();
	 *                       String xueqi = xueQi.getXueqi().toString();
	 * 
	 *                       Map<String, String> map3 = new HashMap<>();
	 *                       map3.put("canyuren1",
	 *                       user.getXueshengid().toString()+",0;%");
	 *                       map3.put("canyuren2",
	 *                       "%;"+user.getXueshengid().toString()+",0;%");
	 *                       map3.put("canyuren3",
	 *                       user.getXueshengid().toString()+",1;%");
	 *                       map3.put("canyuren4",
	 *                       "%;"+user.getXueshengid().toString()+",1;%");
	 *                       map3.put("yuanxiid1", yuanxiid+",%");
	 *                       map3.put("yuanxiid2", "%,"+yuanxiid+",%");
	 *                       map3.put("xuenian", xuenian); map3.put("xueqi",
	 *                       xueqi); List<KeCheng> keChengs =
	 *                       kechengService.getAllBycanYuRenandxueNianxueQi(map3
	 *                       ); for (KeCheng keCheng : keChengs) { String
	 *                       canyuren = keCheng.getCanyuren(); int isadd = 0;
	 *                       int sumcanyuren = 0; if(!canyuren.equals("")){
	 *                       String canyurenid[] = canyuren.split(";"); for
	 *                       (String string : canyurenid) { String cyr[] =
	 *                       string.split(","); if(cyr[1].equals("1")){
	 *                       sumcanyuren++; }
	 *                       if(string.equals(user.getXueshengid().toString()+
	 *                       ",1")){ isadd = 1; continue; } } }
	 *                       keCheng.setSumcanyuren(sumcanyuren);
	 *                       keCheng.setIsadd(isadd); String xiaoquming =
	 *                       xiaoquService.selectByPrimaryKey(Integer.parseInt(
	 *                       keCheng.getXiaoqu())).getMingcheng(); String
	 *                       jiaoshiming =
	 *                       jiaoshiService.selectByPrimaryKey(Integer.parseInt(
	 *                       keCheng.getShangkejiaoshi())).getJiaoshiming();
	 *                       keCheng.setXiaoquming(xiaoquming);
	 *                       keCheng.setJiaoshiming(jiaoshiming); }
	 *                       mv.addObject("kecheng", keChengs);
	 *                       mv.setViewName("stu/addkecheng"); return mv; } else
	 *                       { response.sendRedirect("login"); } return null; }
	 * @RequestMapping(value = "savekecheng")//保存新增课程——选择添加 public ModelAndView
	 *                       savekecheng(HttpServletRequest request,
	 *                       HttpServletResponse response) throws IOException {
	 *                       HttpSession session = request.getSession();
	 *                       request.setCharacterEncoding("UTF-8");
	 *                       response.setContentType("text/html; charset=utf-8"
	 *                       ); PrintWriter out = response.getWriter(); if
	 *                       (Util.checkSession(request)) { XueSheng user =
	 *                       (XueSheng) session.getAttribute("user"); String id
	 *                       = request.getParameter("id"); if
	 *                       (!Util.isNumeric(id)) {
	 *                       response.sendRedirect("logout"); return null; }
	 *                       KeCheng keCheng =
	 *                       kechengService.selectByPrimaryKey(Integer.parseInt(
	 *                       id)); if(keCheng==null){
	 *                       response.sendRedirect("logout"); return null; }
	 *                       String yuanxiid =
	 *                       banjiService.selectByPrimaryKey(user.getBanjiid()).
	 *                       getYuanxiid().toString(); String kaifangyuanxi =
	 *                       keCheng.getKaifangyuanxi();
	 *                       if(kaifangyuanxi!=null){ String yuanxiids[] =
	 *                       kaifangyuanxi.split(","); int i = 0; for (String
	 *                       string : yuanxiids) { if(string.equals(yuanxiid)){
	 *                       i = 1; break; } } if(i==0){
	 *                       response.sendRedirect("logout"); return null; } }
	 *                       String canyuren = keCheng.getCanyuren(); String
	 *                       canyurenid[] = canyuren.split(";"); String
	 *                       newcanyuren = ""; int j = 0; for (String string :
	 *                       canyurenid) {
	 *                       if(string.equals(user.getXueshengid().toString()+
	 *                       ",0")){ j=1; newcanyuren +=
	 *                       user.getXueshengid().toString()+",1;"; continue;
	 *                       }else{ newcanyuren += string+";"; } } if(j==0){
	 *                       newcanyuren +=
	 *                       user.getXueshengid().toString()+",1;"; }
	 * 
	 *                       KeCheng keCheng2 = new KeCheng();
	 *                       keCheng2.setId(Integer.parseInt(id));
	 *                       keCheng2.setCanyuren(newcanyuren); int k =
	 *                       kechengService.updatecanYuRenByPrimaryKey(keCheng2)
	 *                       ; if(k!=0){ out.print(
	 *                       "<script>alert('添加成功！');</script>"); out.print(
	 *                       "<script>location='addkecheng';</script>"); }else{
	 *                       out.print("<script>alert('添加失败！');</script>");
	 *                       out.print("<script>location='addkecheng';</script>"
	 *                       ); } } else { response.sendRedirect("login"); }
	 *                       return null; }
	 */

	@RequestMapping(value = "addkecheng_") // 新增课程
	public ModelAndView addkecheng_(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xuexiaoid = xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_"));
			// 校区列表
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(Integer.parseInt(xuexiaoid));
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xuexiaoid));
			List<String> xueqis = xueqiService.getXueqiByXuexiaoID(Integer.parseInt(xuexiaoid));
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("xuenians", xuenians);
			mv.addObject("xueqis", xueqis);
			mv.setViewName("stu/addkecheng_");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "getjiecishuju_stu") // 获得节次
	@ResponseBody
	public Object getjiecishuju_stu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			Map<String, Object> mp = new HashMap<>();
			XueSheng user = (XueSheng) session.getAttribute("user");
			int xuexiaoid = Integer.parseInt(user.getXuexiaoXuehao().split("_")[0]);
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("xuexiaoid", xuexiaoid);
			map.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map);
			List<JCSJ> jcsj = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			mp.put("xiaoqus", xiaoQus);
			mp.put("zongjieci", jcsj);
			return JSON.toJSON(mp);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "showjiaoxuelou") // 根据校区，显示所有教学楼
	@ResponseBody
	public List<JiaoXueLou> show_jiaoxuelou(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (Util.checkSession(request)) {
			String xiaoQuId = request.getParameter("CODE");
			List<JiaoXueLou> jiaoXueLous = new ArrayList<>();
			if (!xiaoQuId.equals(""))
				jiaoXueLous = jiaoXueLouService.selectByXiaoQuId(Integer.parseInt(xiaoQuId));
			return jiaoXueLous;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "showjiaoshi") // 根据教学楼，显示所有教室
	@ResponseBody
	public List<JiaoShi> showjiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			String jiaoXueLouId = request.getParameter("CODE");
			List<JiaoShi> jiaoShis = new ArrayList<>();
			if (!jiaoXueLouId.equals(""))
				jiaoShis = jiaoshiService.getAllByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			return jiaoShis;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "showjiaoshibyxiaoquid")
	@ResponseBody
	public Object showjiaoshibyxiaoquid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (Util.checkSession(request)) {
			String xiaoquid = request.getParameter("CODE");
			List<JiaoShi> jiaoshis = jiaoshiService.getAllByxiaoQuID(Integer.parseInt(xiaoquid));

			return JSONArray.fromObject(jiaoshis);
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "addkecheng_xuanze") // 添加课程——选择
	public ModelAndView selectkecheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.lastIndexOf("_"));
			// 获取当前学年学期
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			map.put("riqi", riqi.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			if (xueQi == null) {
				List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
				if (xueqis != null && xueqis.size() > 0) {
					xueQi = xueqis.get(0);
				}
			}
			String xiaoquid = request.getParameter("xiaoqu");
			String shangkejiaoshiid = request.getParameter("shangkejiaoshi");
			String zhouciid = request.getParameter("zhouci");
			int nf = nianFenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
			String xuenian = request.getParameter("xuenian") == null ? nf + "~" + (nf + 1)
					: request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi") == null ? xueQi.getXueqi().toString()
					: request.getParameter("xueqi");

			map.put("xuexiaoid", xueXiaoID);
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			map.put("nianfen", xuenian.split("~")[0]);
			XueQi xueQi2 = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int zhounum = Util.WeeksBetweenDays(sdf.parse(xueQi2.getKaishiriqi()), sdf.parse(xueQi2.getJieshuriqi()));
			XiaoQu xiaoqu = xiaoquService.selectByPrimaryKey(Integer.parseInt(xiaoquid));
			JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(shangkejiaoshiid));
			Map<String, String> map2 = new HashMap<>();
			map2.put("shangkejiaoshi", shangkejiaoshiid);
			map2.put("zhouci", zhouciid);
			map2.put("xueqiid", xueQi2.getXueqiid().toString());
			List<Map<String, Object>> keChengMaps = kechengService.getByJiaoShiIDAndZhouCiAndXueQiID(map2);
			if (keChengMaps != null && keChengMaps.size() > 0) {
				List<KeCheng> keChengs = new ArrayList<>();
				for (Map<String, Object> keChengMap : keChengMaps) {
					KeCheng keCheng = new KeCheng();
					if (user.getIsbanzhang()) {
						keCheng.setLeixing(Integer.parseInt(keChengMap.get("leiXing").toString()));
						keCheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
						keCheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
						keCheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
						JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(shangkejiaoshiid));
						String jiaoshiming = js.getJiaoshiming();
						JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
						String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng();
						keCheng.setXiaoquming(xiaoquming);
						keCheng.setJiaoshiming(jiaoshiming);
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
						keCheng.setKaishijieci(jcsj1.getJieci());
						keCheng.setJieshujieci(jcsj2.getJieci());
						String tianjiarenid = keChengMap.get("tianJiaRenID").toString();
						YongHu yongHu = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
						if (yongHu != null) {
							if (yongHu.getJueseid().equals(2)) {
								keCheng.setTianjiarenleixing("辅导员");
							} else {
								keCheng.setTianjiarenleixing("教师");
							}
						} else {
							keCheng.setTianjiarenleixing("学生");
						}
						keChengs.add(keCheng);
					} else {
						if (keChengMap.get("leiXing").toString().equals("2")) {
							keCheng.setLeixing(Integer.parseInt(keChengMap.get("leiXing").toString()));
							keCheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
							keCheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
							keCheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
							JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(shangkejiaoshiid));
							String jiaoshiming = js.getJiaoshiming();
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
							String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng();
							keCheng.setXiaoquming(xiaoquming);
							keCheng.setJiaoshiming(jiaoshiming);
							JCSJ jcsj1 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
							keCheng.setKaishijieci(jcsj1.getJieci());
							keCheng.setJieshujieci(jcsj2.getJieci());
							String tianjiarenid = keChengMap.get("tianJiaRenID").toString();
							YongHu yongHu = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
							if (yongHu != null) {
								if (yongHu.getJueseid().equals(2)) {
									keCheng.setTianjiarenleixing("辅导员");
								} else {
									keCheng.setTianjiarenleixing("教师");
								}
							} else {
								keCheng.setTianjiarenleixing("学生");
							}
							keChengs.add(keCheng);
						}
					}
				}
				mv.addObject("xuenian", xuenian);
				mv.addObject("xueqi", xueqi);
				mv.addObject("zhounum", zhounum);
				mv.addObject("xiaoqu", xiaoqu);
				mv.addObject("jiaoshi", jiaoShi);
				mv.addObject("zhouci", zhouciid);
				mv.addObject("kecheng", keChengs);
				mv.setViewName("stu/addkecheng_xuanze");
			} else {
				// 获取当前学期使用的节次列表
				Map<String, Integer> map3 = new HashMap<>();
				map3.put("xuexiaoid", Integer.parseInt(xueXiaoID));
				map3.put("zhuangtai", 1);
				int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map3).getId();
				List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
				mv.addObject("xuenian", xuenian);
				mv.addObject("xueqi", xueqi);
				mv.addObject("zhounum", zhounum);
				mv.addObject("jieci", jcsjs);
				mv.addObject("xiaoqu", xiaoqu);
				mv.addObject("jiaoshi", jiaoShi);
				mv.addObject("zhouci", zhouciid);
				mv.setViewName("stu/addkecheng_zizhu");
			}

			/**
			 * List<KeCheng> keChengs =
			 * kechengService.getAllByxiaoQuandshangKeJiaoShiandzhouCi(map2);
			 * XiaoQu xiaoqu =
			 * xiaoquService.selectByPrimaryKey(Integer.parseInt(xiaoquid));
			 * JiaoShi jiaoShi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(
			 * shangkejiaoshiid)); if (keChengs == null || keChengs.size() == 0)
			 * { // 获取当前学期使用的节次列表 Map<String, Integer> map3 = new HashMap<>();
			 * map3.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			 * map3.put("zhuangtai", 1); int jiecifanganid =
			 * jiecifanganService.selectByxueXiaoIDAndZhuangTai(map3).getId();
			 * List<JCSJ> jcsjs =
			 * jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			 * mv.addObject("jieci", jcsjs); mv.addObject("xiaoqu", xiaoqu);
			 * mv.addObject("jiaoshi", jiaoShi); mv.addObject("zhouci",
			 * zhouciid); mv.setViewName("stu/addkecheng_zizhu"); } else {
			 * mv.addObject("xiaoqu", xiaoqu); mv.addObject("jiaoshi", jiaoShi);
			 * mv.addObject("zhouci", zhouciid); mv.addObject("kecheng",
			 * keChengs); mv.setViewName("stu/addkecheng_xuanze"); }
			 * 
			 */
			return mv;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "addkecheng_zizhu") // 新增课程——自主添加
	public ModelAndView addkecheng_zizhu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String xinxis = request.getParameter("id");
			String xinxi[] = xinxis.split("/");
			String xiaoquid = xinxi[0];
			String jiaoshiid = xinxi[1];
			String zhouci = xinxi[2];
			String zhounum = xinxi[3];

			XiaoQu xiaoqu = xiaoquService.selectByPrimaryKey(Integer.parseInt(xiaoquid));
			JiaoShi jiaoshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(jiaoshiid));

			// 获取学校id
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.lastIndexOf("_"));
			// 获取当前学期使用的节次列表
			Map<String, Integer> map3 = new HashMap<>();
			map3.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			map3.put("zhuangtai", 1);
			int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map3).getId();
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			// 获取当前学年学期
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			map.put("riqi", riqi.format(date));
			map.put("xueXiaoID", xueXiaoID);
			mv.addObject("zhounum", zhounum);
			mv.addObject("jieci", jcsjs);
			mv.addObject("xiaoqu", xiaoqu);
			mv.addObject("jiaoshi", jiaoshi);
			mv.addObject("zhouci", zhouci);
			mv.setViewName("stu/addkecheng_zizhu");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "savekecheng_xuanze") // 保存新增课程——选择添加
	public String savekecheng_xuanze(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			String data = "";
			XueSheng user = (XueSheng) session.getAttribute("user");
			String codes = request.getParameter("CODE");
			String code[] = codes.split(",");
			String id = code[0];
			String ziji_banji = code[1];
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			String[] xueXiaoXueHao = user.getXuexiaoXuehao().split("_");
			if (ziji_banji.equals("0")) { // 只给自己添加
				if (keCheng.getLeixing() == 2) {
					int k = 0;
					List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(id);
					List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
					int t = 0;
					if (xuanXiuIDs != null && xuanXiuIDs.size() > 0) {
						for (String string : xuanXiuIDs) {
							if (string.equals(user.getXueshengid().toString())) {
								t = 1;
								break;
							}
						}
						if (t == 1) {
							data = "yitianjia";
						} else {
							Map<String, String> map = new HashMap<>();
							map.put("kechengid", id);
							map.put("xueshengid", user.getXueshengid().toString());
							if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
								for (String string : mianXiuIDs) {
									if (string.equals(user.getXueshengid().toString())) {
										kechengService.delete_shangkemianxiuren(map);
									}
								}
							}
							if (!keCheng.getTianjiarenid().equals(user.getXueshengid().toString())) {
								k = kechengService.insert_shangkexuanxiuren(map);
								if (k != 0) {
									data = "success";
								} else {
									data = "fail";
								}
							} else {
								data = "yitianjia";
							}
						}
					} else {
						Map<String, String> map = new HashMap<>();
						map.put("kechengid", id);
						map.put("xueshengid", user.getXueshengid().toString());
						if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
							for (String string : mianXiuIDs) {
								if (string.equals(user.getXueshengid().toString())) {
									kechengService.delete_shangkemianxiuren(map);
								}
							}
						}
						if (!keCheng.getTianjiarenid().equals(user.getXueshengid().toString())) {
							k = kechengService.insert_shangkexuanxiuren(map);
							if (k != 0) {
								data = "success";
							} else {
								data = "fail";
							}
						} else {
							data = "yitianjia";
						}
					}
				} else {
					data = "fail";
				}
			}
			if (ziji_banji.equals("1")) { // 给本班添加
				if (keCheng.getLeixing() == 1) {
					String banjiid = user.getBanjiid().toString();
					int samebanji = 0;
					int k = 0;
					List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
					Map<String, String> map = new HashMap<>();
					map.put("kechengid", id);
					map.put("banjiid", banjiid);
					List<String> banJis = kechengService.getByAllBanJiIDByID(id);
					List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
					if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
						for (String string : mianXiuIDs) {
							if (string.equals(user.getXueshengid().toString())) {
								kechengService.delete_shangkemianxiuren(map);
							}
						}
					}
					if (banJis != null && banJis.size() > 0) {
						for (String string : banJis) {
							if (string.equals(banjiid)) {
								samebanji = 1;
								break;
							}
						}
						if (samebanji == 1) {
							data = "yitianjia";
						} else {
							k = kechengService.insert_shangkebanji(map);
						}
					} else {
						k = kechengService.insert_shangkebanji(map);
					}
					if (k != 0) {
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date2 = new Date();
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						tiXing.setNeirong(user.getXingming() + "给你加了一门课《" + keCheng.getKechengmingcheng() + "》");
						tiXing.setShijian(date2);
						tiXing.setZhuangtai(0);
						tiXing.setShujuid(keCheng.getId());
						tiXing.setType(1);
						xiaoXiFaSong.setXiaoXiMingCheng("新加课程");
						xiaoXiFaSong.setXiaoXiNeiRong(
								user.getXingming() + "给你加了一门课《" + keCheng.getKechengmingcheng() + "》");
						xiaoXiFaSong.setShuJuId(keCheng.getId());
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(xueXiaoXueHao[0]);
						for (XueSheng xueSheng : xueShengs) {
							tiXing.setJieshourenid(xueSheng.getXueshengid());
							xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
							tixingService.insert(tiXing);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
						data = "success";
					} else {
						data = "fail";
					}
				} else {
					data = "fail";
				}
			}
			return data;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "savekecheng_zizhu") // 保存新增课程——自主添加
	public ModelAndView savekecheng_zizhu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String[] xueXiaoXueHao = user.getXuexiaoXuehao().split("_");
			String xueXiaoID = xueXiaoXueHao[0];
			String kechengmingcheng = request.getParameter("kechengmingcheng");
			String renkejiaoshi = request.getParameter("renkejiaoshi");// 教师
			String danshuangzhou = request.getParameter("danshuangzhou");
			String lianxukecheng = request.getParameter("lianxukecheng");
			// 参与人
			String ziji_banji = "0";
			if (user.getIsbanzhang() != null) {
				if (user.getIsbanzhang()) {
					ziji_banji = request.getParameter("ziji_banji");
				}
			}
			List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(user.getBanjiid());
			Map<String, String> map = new HashMap<>();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			XueQi xueQi2 = new XueQi();
			if (xuenian != null && !"".equals(xuenian) && xueqi != null && !"".equals(xueqi)) {
				map.put("xuexiaoid", xueXiaoID);
				map.put("nianfen", xuenian.split("~")[0]);
				map.put("xueqi", xueqi);
				xueQi2 = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			} else {
				Date date = new Date();
				map.put("riqi", riqi.format(date));
				map.put("xueXiaoID", xueXiaoID);
				xueQi2 = xueqiService.getByxueXiaoIDandriQi(map);
			}
			if (xueQi2 == null) {
				Date date = new Date();
				map.put("xueXiaoID", xueXiaoID);
				map.put("riqi", riqi.format(date));
				List<XueQi> xueqis2 = xueqiService.getNewerXueQiByXueQi(map);
				if (xueqis2 != null && xueqis2.size() > 0) {
					xueQi2 = xueqis2.get(0);
				}
			}

			int o = 0;
			if (danshuangzhou.equals("2")) {
				String xinxi[] = lianxukecheng.split(";");
				for (String string : xinxi) {
					String str[] = string.split(",");
					if (str[0].compareTo(xueQi2.getKaishiriqi()) < 0 || str[0].compareTo(xueQi2.getJieshuriqi()) > 0) {
						out.print("<script>alert('选课时间超出学期范围！！');</script>");
						out.print("<script>location='addkecheng_';</script>");
						o = 1;
						break;
					}
				}
			}
			if (o == 0) {
				int j = 0;
				KeCheng keCheng = new KeCheng();
				if (ziji_banji.equals("0")) {
					keCheng.setLeixing(2);
				} else {
					keCheng.setLeixing(1);
				}
				keCheng.setXueqiid(xueQi2.getXueqiid());
				keCheng.setKechengmingcheng(kechengmingcheng);
				keCheng.setRenkejiaoshi(renkejiaoshi);
				keCheng.setTianjiarenid(user.getXueshengid().toString());
				j = kechengService.insertSelective(keCheng);
				if (j != 0) {
					map.put("kechengid", keCheng.getId().toString());
					// 添加班级或对象
					if (ziji_banji.equals("1")) {// 以班级
						// 将上课班级转为参与人
						map.put("banjiid", user.getBanjiid().toString());
						kechengService.insert_shangkebanji(map);
					} else { // 以学生 --- 自己 即添加人

					}
					// 添加时间
					if (danshuangzhou.equals("1")) { // 连续 1每周，2单周，3双周
						// da += danshuang + "," + zhouci + "," + kaishijieci +
						// ","
						// + jieshujieci + "," + shangkejiaoshi
						// +","+kaishizhou+","+jieshuzhou+ ";";
						String xinxi[] = lianxukecheng.split(";");
						for (String string : xinxi) {
							String str[] = string.split(",");
							if (str[0].equals("1")) { // 每周
								map.put("leixing", "1");
								map.put("kaishizhou", str[5]);
								map.put("jieshuzhou", str[6]);
								map.put("kaishijieci", str[2]);
								map.put("jieshujieci", str[3]);
								map.put("zhouci", str[1]);
								map.put("jiaoshiid", str[4]);
								kechengService.insert_shangkeshijian(map);
							}
							if (str[0].equals("2")) { // 单周
								map.put("leixing", "2");
								map.put("kaishizhou", str[5]);
								map.put("jieshuzhou", str[6]);
								map.put("kaishijieci", str[2]);
								map.put("jieshujieci", str[3]);
								map.put("zhouci", str[1]);
								map.put("jiaoshiid", str[4]);
								kechengService.insert_shangkeshijian(map);
							}
							if (str[0].equals("3")) { // 双周
								map.put("leixing", "3");
								map.put("kaishizhou", str[5]);
								map.put("jieshuzhou", str[6]);
								map.put("kaishijieci", str[2]);
								map.put("jieshujieci", str[3]);
								map.put("zhouci", str[1]);
								map.put("jiaoshiid", str[4]);
								kechengService.insert_shangkeshijian(map);
							}
						}
					} else { // 次
								// da += riqi + "," + kaishijieci + "," +
								// jieshujieci
								// + "," + shangkejiaoshi + ";";
						String xinxi[] = lianxukecheng.split(";");
						for (String string : xinxi) {
							String str[] = string.split(",");
							map.put("leixing", "4");
							Calendar cal1 = Calendar.getInstance();
							cal1.setTime(riqi.parse(xueQi2.getKaishiriqi()));
							Calendar cal2 = Calendar.getInstance();
							cal2.setTime(riqi.parse(str[0]));
							int day1 = cal1.get(Calendar.DAY_OF_YEAR);
							int day2 = cal2.get(Calendar.DAY_OF_YEAR);
							int year1 = cal1.get(Calendar.YEAR);
							int year2 = cal2.get(Calendar.YEAR);
							int timeDistance = 0;
							if (year1 != year2) { // 不同年
								for (int i = year1; i < year2; i++) {
									if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
										timeDistance += 366;
									} else { // 不是闰年
										timeDistance += 365;
									}
									timeDistance += day2 - day1;
								}
							} else {// 同年
								timeDistance = day2 - day1;
							}
							int zhou = timeDistance / 7 + 1;

							Calendar c = Calendar.getInstance();
							c.setTime(riqi.parse(str[0]));
							int zhouCi = 0;
							if (c.get(Calendar.DAY_OF_WEEK) == 1) {
								zhouCi = 7;
							} else {
								zhouCi = c.get(Calendar.DAY_OF_WEEK) - 1;
							}

							map.put("kaishizhou", zhou + "");
							map.put("jieshuzhou", "");
							map.put("kaishijieci", str[1]);
							map.put("jieshujieci", str[2]);
							map.put("jiaoshiid", str[3]);
							map.put("zhouci", zhouCi + "");
							kechengService.insert_shangkeshijian(map);
						}
					}

					if (ziji_banji.equals("1")) { // 给本班添加
						// 发送极光消息
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						xiaoXiFaSong.setXiaoXiMingCheng(user.getXingming() + "给你加了一门课《" + kechengmingcheng + "》");
						xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "给你加了一门课《" + kechengmingcheng + "》");
						xiaoXiFaSong.setShuJuId(keCheng.getId());
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(xueXiaoXueHao[0]);
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date2 = new Date();
						tiXing.setNeirong(user.getXingming() + "给你加了一门课《" + kechengmingcheng + "》");
						tiXing.setShijian(date2);
						tiXing.setZhuangtai(0);
						tiXing.setShujuid(keCheng.getId());
						tiXing.setType(1);
						for (XueSheng xueSheng : xueShengs) {
							tiXing.setJieshourenid(xueSheng.getXueshengid());
							xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
							tixingService.insert(tiXing);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}
					out.print("<script>alert('添加成功！');</script>");
					out.print("<script>location='addkecheng_';</script>");
				} else {
					out.print("<script>alert('添加失败！');</script>");
					out.print("<script>location='addkecheng_';</script>");
				}
			}
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "tiaotingkecheng") // 调停课程
	public ModelAndView tiaotingkecheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String ids[] = request.getParameter("id").split("/");
			if (!Util.isNumeric(ids[0])) {
				response.sendRedirect("logout");
				return null;
			}
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(ids[0]));
			if (keCheng == null) {
				response.sendRedirect("logout");
				return null;
			}
			String tianjiarenid = keCheng.getTianjiarenid();
			if (!user.getXueshengid().toString().equals(tianjiarenid)) {
				response.sendRedirect("logout");
				return null;
			}
			// 可选校区
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xuexiaoid = xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_"));
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(Integer.parseInt(xuexiaoid));
			// 可选教室列表
			List<JiaoShi> jiaoShis = new ArrayList<>();
			// 获取当前学期使用的节次列表
			Map<String, Integer> mapp = new HashMap<>();
			mapp.put("xuexiaoid", Integer.parseInt(xuexiaoid));
			mapp.put("zhuangtai", 1);
			int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(mapp).getId();
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			XueQi xueQi = xueqiService.selectByID(keCheng.getXueqiid());

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<String> riqi = new ArrayList<>();
			// 通过id，得所有上课时间
			List<Map<String, Object>> shangKeShiJianMap = kechengService.getShangKeShiJianByID(ids[0]);
			List<Map<String, String>> tingKeShiJianMap = new ArrayList<>();
			if (shangKeShiJianMap.size() > 0 && shangKeShiJianMap != null) {
				// 从 所有上课时间 中，得所有停课时间 --- shiJianLeiXing=5
				for (Map<String, Object> map3 : shangKeShiJianMap) {
					if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("5")) {
						if (ids[1] != null && !("".equals(ids[1])) && ids[1].equals("3")) {
							String zhoushu[] = ids[2].split(",");
							for (String string : zhoushu) {
								if (string.equals(map3.get("kaiShiZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[4]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[5]);
									if (ids[3].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										Map<String, String> map = new HashMap<>();
										map.put("kaishizhou", map3.get("kaiShiZhou").toString());
										map.put("zhouci", map3.get("zhouCi").toString());
										tingKeShiJianMap.add(map);
										break;
									}
								}
							}
						} else {
							for (int o = Integer.parseInt(ids[4]); o <= Integer.parseInt(ids[5]); o++) {
								if (String.valueOf(o).equals(map3.get("kaiShiZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[6]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[7]);
									if (ids[2].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										Map<String, String> map = new HashMap<>();
										map.put("kaishizhou", map3.get("kaiShiZhou").toString());
										map.put("zhouci", map3.get("zhouCi").toString());
										tingKeShiJianMap.add(map);
									}
								}
							}
						}
					}
				}

				if (ids[1] != null && !("".equals(ids[1])) && ids[1].equals("3")) { // 次--
																					// shiJianLeiXing=4
					for (Map<String, Object> map3 : shangKeShiJianMap) {
						if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("4")) {
							String zhoushu[] = ids[2].split(",");
							for (String string : zhoushu) {
								if (string.equals(map3.get("kaiShiZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[4]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[5]);
									if (ids[3].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										keCheng.setLeixing(3);
										keCheng.setZhouci(Integer.parseInt(ids[3]));
										keCheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
												+ map3.get("kaiShiJieCi").toString() + ","
												+ map3.get("jieShuJieCi").toString());
										keCheng.setKaishijieci(Integer.parseInt(ids[4]));
										keCheng.setJieshujieci(Integer.parseInt(ids[5]));
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
										XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
										keCheng.setJiaoshiming(js.getJiaoshiming());
										keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										keCheng.setXiaoquming(xq.getMingcheng());
										keCheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());
										keCheng.setXiaoqu(xq.getXiaoquid().toString());
										jiaoShis = jiaoshiService.getAllByxiaoQuID(jxl.getXiaoQuId());
										// zhoushu
										keCheng.setKaifangyuanxi(ids[2]);
										// 获取每次上课距离学期开始的天数
										int day = (Integer.parseInt(string) - 1) * 7 + Integer.parseInt(ids[3]) - 1;
										// 获取每次上课日期
										String date = simpleDateFormat
												.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
														+ (long) day * 24 * 60 * 60 * 1000));
										riqi.add(date);
									}
								}

							}
						}
					}
				} else {
					// ${KeCheng.id}/${KeCheng.leixing}/${
					// maps.zhouci}/${maps.ds}/${maps.kaishizhou}/${maps.jieshuzhou}/${maps.kaishijieci}/${maps.jieshujieci}
					if (ids[3].equals("1")) { // 单周 shiJianLeiXing=2
						for (Map<String, Object> map3 : shangKeShiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("2")) {
								if (ids[4].equals(map3.get("kaiShiZhou").toString())
										&& ids[5].equals(map3.get("jieShuZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[6]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[7]);
									if (ids[2].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										keCheng.setLeixing(2);
										keCheng.setKeDaiBiao("1");
										keCheng.setZhouci(Integer.parseInt(ids[2]));
										keCheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
												+ map3.get("kaiShiJieCi").toString() + ","
												+ map3.get("jieShuJieCi").toString());
										keCheng.setKaishijieci(Integer.parseInt(ids[6]));
										keCheng.setJieshujieci(Integer.parseInt(ids[7]));
										keCheng.setKaishizhou(Integer.parseInt(ids[4]));
										keCheng.setJieshuzhou(Integer.parseInt(ids[5]));
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
										XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
										keCheng.setJiaoshiming(js.getJiaoshiming());
										keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										keCheng.setXiaoquming(xq.getMingcheng());
										keCheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());
										keCheng.setXiaoqu(xq.getXiaoquid().toString());
										jiaoShis = jiaoshiService.getAllByxiaoQuID(jxl.getXiaoQuId());

										// 根据开始周 结束周，判断日期
										String[] shangkeriqi = new String[Integer.parseInt(ids[5])
												- Integer.parseInt(ids[4]) + 1];
										// 判断开始周是 单周还是双周
										int o = Integer.parseInt(ids[4]) % 2;
										if (o == 0) {// 偶数
											for (int a = 0; a < shangkeriqi.length; a++) {
												if (a % 2 != 0) {
													// 获取每次上课距离学期开始的天数
													int day = (Integer.parseInt(ids[4]) - 1 + a) * 7
															+ Integer.parseInt(ids[2]) - 1;
													// 获取每次上课日期
													shangkeriqi[a] = simpleDateFormat.format(new Date(
															simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
																	+ (long) day * 24 * 60 * 60 * 1000));
													riqi.add(shangkeriqi[a]);
												}
											}
										} else {// 奇数
											for (int a = 0; a < shangkeriqi.length; a++) {
												if ((a + 1) % 2 != 0) {
													// 获取每次上课距离学期开始的天数
													int day = (Integer.parseInt(ids[4]) - 1 + a) * 7
															+ Integer.parseInt(ids[2]) - 1;
													// 获取每次上课日期
													shangkeriqi[a] = simpleDateFormat.format(new Date(
															simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
																	+ (long) day * 24 * 60 * 60 * 1000));
													riqi.add(shangkeriqi[a]);
												}
											}
										}
									}
								}
							}
						}
					} else if (ids[3].equals("2")) { // 双周 shiJianLeiXing=3
						for (Map<String, Object> map3 : shangKeShiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("3")) {
								if (ids[4].equals(map3.get("kaiShiZhou").toString())
										&& ids[5].equals(map3.get("jieShuZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[6]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[7]);
									if (ids[2].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										keCheng.setLeixing(2);
										keCheng.setKeDaiBiao("2");
										keCheng.setZhouci(Integer.parseInt(ids[2]));
										keCheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
												+ map3.get("kaiShiJieCi").toString() + ","
												+ map3.get("jieShuJieCi").toString());
										keCheng.setKaishijieci(Integer.parseInt(ids[6]));
										keCheng.setJieshujieci(Integer.parseInt(ids[7]));
										keCheng.setKaishizhou(Integer.parseInt(ids[4]));
										keCheng.setJieshuzhou(Integer.parseInt(ids[5]));
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
										XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
										keCheng.setJiaoshiming(js.getJiaoshiming());
										keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										keCheng.setXiaoquming(xq.getMingcheng());
										keCheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());
										keCheng.setXiaoqu(xq.getXiaoquid().toString());
										jiaoShis = jiaoshiService.getAllByxiaoQuID(jxl.getXiaoQuId());

										// 根据开始周 结束周，判断日期
										String[] shangkeriqi = new String[Integer.parseInt(ids[5])
												- Integer.parseInt(ids[4]) + 1];
										// 判断开始周是 单周还是双周
										int o = Integer.parseInt(ids[4]) % 2;
										if (o == 0) {// 偶数
											for (int a = 0; a < shangkeriqi.length; a++) {
												if (a % 2 == 0) {
													// 获取每次上课距离学期开始的天数
													int day = (Integer.parseInt(ids[4]) - 1 + a) * 7
															+ Integer.parseInt(ids[2]) - 1;
													// 获取每次上课日期
													shangkeriqi[a] = simpleDateFormat.format(new Date(
															simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
																	+ (long) day * 24 * 60 * 60 * 1000));
													riqi.add(shangkeriqi[a]);
												}
											}
										} else {// 奇数
											for (int a = 0; a < shangkeriqi.length; a++) {
												if ((a + 1) % 2 == 0) {
													// 获取每次上课距离学期开始的天数
													int day = (Integer.parseInt(ids[4]) - 1 + a) * 7
															+ Integer.parseInt(ids[2]) - 1;
													// 获取每次上课日期
													shangkeriqi[a] = simpleDateFormat.format(new Date(
															simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
																	+ (long) day * 24 * 60 * 60 * 1000));
													riqi.add(shangkeriqi[a]);
												}
											}
										}
									}
								}
							}
						}
					} else { // 每周 shiJianLeiXing=1
						for (Map<String, Object> map3 : shangKeShiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("1")) {
								if (ids[4].equals(map3.get("kaiShiZhou").toString())
										&& ids[5].equals(map3.get("jieShuZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[6]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ids[7]);
									if (ids[2].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										keCheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
												+ map3.get("kaiShiJieCi").toString() + ","
												+ map3.get("jieShuJieCi").toString());
										keCheng.setLeixing(2);
										keCheng.setKeDaiBiao("0");
										keCheng.setZhouci(Integer.parseInt(ids[2]));
										keCheng.setKaishijieci(Integer.parseInt(ids[6]));
										keCheng.setJieshujieci(Integer.parseInt(ids[7]));
										keCheng.setKaishizhou(Integer.parseInt(ids[4]));
										keCheng.setJieshuzhou(Integer.parseInt(ids[5]));
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
										XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
										keCheng.setJiaoshiming(js.getJiaoshiming());
										keCheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										keCheng.setXiaoquming(xq.getMingcheng());
										keCheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());
										keCheng.setXiaoqu(xq.getXiaoquid().toString());
										jiaoShis = jiaoshiService.getAllByxiaoQuID(jxl.getXiaoQuId());
										// 根据开始周 结束周，判断日期
										String[] shangkeriqi = new String[Integer.parseInt(ids[5])
												- Integer.parseInt(ids[4]) + 1];
										for (int a = 0; a < shangkeriqi.length; a++) {
											// 获取每次上课距离学期开始的天数
											int day = (Integer.parseInt(ids[4]) - 1 + a) * 7 + Integer.parseInt(ids[2])
													- 1;
											// 获取每次上课日期
											shangkeriqi[a] = simpleDateFormat.format(
													new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
															+ (long) day * 24 * 60 * 60 * 1000));
											riqi.add(shangkeriqi[a]);
										}
									}
								}
							}
						}
					}
				}
				// 去掉停课日期
				for (Map<String, String> tiaokemap : tingKeShiJianMap) {
					// 获取每次上课距离学期开始的天数
					int day = (Integer.parseInt(tiaokemap.get("kaishizhou").toString()) - 1) * 7
							+ Integer.parseInt(tiaokemap.get("zhouci").toString()) - 1;
					// 获取每次上课日期
					String date = simpleDateFormat
							.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
									+ (long) day * 24 * 60 * 60 * 1000));
					if (riqi.contains(date)) {
						riqi.remove(date);
					}
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}

			mv.addObject("kaishiriqi", xueQi.getKaishiriqi());
			mv.addObject("jieshuriqi", xueQi.getJieshuriqi());
			mv.addObject("jieci", jcsjs);
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("jiaoshi", jiaoShis);
			mv.addObject("riqi", riqi);
			mv.addObject("kecheng", keCheng);
			mv.setViewName("stu/tiaotingkecheng");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "savetiaotingkecheng") // 保存调停课程
	public ModelAndView savetiaotingkecheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String[] xueXiaoXueHao = user.getXuexiaoXuehao().split("_");
			int xuexiaoid = Integer.parseInt(xueXiaoXueHao[0]);
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (kecheng == null) {
				response.sendRedirect("logout");
				return null;
			}
			String tianjiarenid = kecheng.getTianjiarenid();
			if (!user.getXueshengid().toString().equals(tianjiarenid)) {
				response.sendRedirect("logout");
				return null;
			}
			if (kecheng.getLeixing() == 1) {
				List<String> banjiids = kechengService.getByAllBanJiIDByID(id);
				int o = 0;
				for (String string : banjiids) {
					if (string.equals(user.getBanjiid().toString())) {
						o = 1;
						break;
					}
				}
				if (o == 0) {
					response.sendRedirect("logout");
					return null;
				}
			}
			String canyurens = "";
			List<String> xuanxiuids = kechengService.getAllXuanXiuIDByID(id);
			List<String> mianxiuids = kechengService.getAllMianXiuIDByID(id);
			if (kecheng.getLeixing() == 1) {
				List<String> banjiids = kechengService.getByAllBanJiIDByID(id);
				for (String string : banjiids) {
					List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueshengs) {
						int o = 0;
						for (String s : mianxiuids) {
							if (s.equals(xueSheng.getXueshengid().toString())) {
								o = 1;
								break;
							}
						}
						if (o == 0) {
							canyurens += xueSheng.getXueshengid() + ",";
						}
					}
				}
			}
			if (kecheng.getLeixing() == 2) {
				for (String string : xuanxiuids) {
					int o = 0;
					for (String s : mianxiuids) {
						if (s.equals(string)) {
							o = 1;
							break;
						}
					}
					if (o == 0) {
						canyurens += string + ",";
					}
				}
			}

			String tiaoting = request.getParameter("tiaoting");
			Map<String, String> map = new HashMap<>();
			map.put("kechengid", id);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String shangkeshijian[] = request.getParameter("shangkeriqi").split(",");
			if (tiaoting.equals("0")) {
				String tingkeriqi = request.getParameter("tiaotingriqi");
				XueQi xueQi = xueqiService.selectByID(kecheng.getXueqiid());
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(format.parse(xueQi.getKaishiriqi()));
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(format.parse(tingkeriqi));
				int day1 = cal1.get(Calendar.DAY_OF_YEAR);
				int day2 = cal2.get(Calendar.DAY_OF_YEAR);
				int year1 = cal1.get(Calendar.YEAR);
				int year2 = cal2.get(Calendar.YEAR);
				int timeDistance = 0;
				if (year1 != year2) { // 不同年
					for (int i = year1; i < year2; i++) {
						if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
							timeDistance += 366;
						} else { // 不是闰年
							timeDistance += 365;
						}
						timeDistance += day2 - day1;
					}
				} else {// 同年
					timeDistance = day2 - day1;
				}
				int zhou = timeDistance / 7 + 1;

				Calendar c = Calendar.getInstance();
				c.setTime(format.parse(tingkeriqi));
				int zhouci = 0;
				if (c.get(Calendar.DAY_OF_WEEK) == 1) {
					zhouci = 7;
				} else {
					zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
				}
				map.put("zhouci", zhouci + "");
				map.put("kaishizhou", zhou + "");
				map.put("jiaoshiid", shangkeshijian[0]);
				map.put("kaishijieci", shangkeshijian[1]);
				map.put("jieshujieci", shangkeshijian[2]);
				map.put("leixing", "5");
				int i = kechengService.insert_shangkeshijian(map);
				if (i != 0) {
					if (!canyurens.equals("")) {
						// 发送激光消息
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						xiaoXiFaSong.setXiaoXiMingCheng("停课");
						xiaoXiFaSong.setXiaoXiNeiRong(
								user.getXingming() + "将" + tingkeriqi + "的《" + kecheng.getKechengmingcheng() + "》停课一次");
						xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(String.valueOf(xuexiaoid));
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date = new Date();
						tiXing.setNeirong(
								user.getXingming() + "将" + tingkeriqi + "的《" + kecheng.getKechengmingcheng() + "》停课一次");
						tiXing.setShijian(date);
						tiXing.setZhuangtai(0);
						tiXing.setShujuid(Integer.parseInt(id));
						tiXing.setType(1);
						if (canyurens != null && !"".equals(canyurens)) {
							String cyr[] = canyurens.split(",");
							for (String string : cyr) {
								tiXing.setJieshourenid(Integer.parseInt(string));
								tixingService.insert(tiXing);
								xiaoXiFaSong.setFaSongMuBiao(string);
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
							}
						}
					}
					out.print("<script>alert('停课成功！');</script>");
					out.print("<script>location='kechengliebiao';</script>");
				} else {
					out.print("<script>alert('停课失败！');</script>");
					out.print("<script>location='kechengliebiao';</script>");
				}
			}
			if (tiaoting.equals("1")) {
				String tiaokeriqi = request.getParameter("tiaotingriqi");
				String newriqi = request.getParameter("newriqi");
				String shangkejiaoshi = request.getParameter("shangkejiaoshi");
				String kaishijieci = request.getParameter("kaishijieci");
				String jieshujieci = request.getParameter("jieshujieci");
				map.put("xueXiaoID", xuexiaoid + "");
				map.put("riqi", tiaokeriqi);
				XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(format.parse(xueQi.getKaishiriqi()));
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(format.parse(tiaokeriqi));
				int day1 = cal1.get(Calendar.DAY_OF_YEAR);
				int day2 = cal2.get(Calendar.DAY_OF_YEAR);
				int year1 = cal1.get(Calendar.YEAR);
				int year2 = cal2.get(Calendar.YEAR);
				int timeDistance = 0;
				if (year1 != year2) { // 不同年
					for (int i = year1; i < year2; i++) {
						if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
							timeDistance += 366;
						} else { // 不是闰年
							timeDistance += 365;
						}
						timeDistance += day2 - day1;
					}
				} else {// 同年
					timeDistance = day2 - day1;
				}
				int zhou = timeDistance / 7 + 1;

				Calendar c = Calendar.getInstance();
				c.setTime(format.parse(tiaokeriqi));
				int zhouci = 0;
				if (c.get(Calendar.DAY_OF_WEEK) == 1) {
					zhouci = 7;
				} else {
					zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
				}
				map.put("zhouci", zhouci + "");
				map.put("kaishizhou", zhou + "");
				map.put("jiaoshiid", shangkeshijian[0]);
				map.put("kaishijieci", shangkeshijian[1]);
				map.put("jieshujieci", shangkeshijian[2]);
				map.put("leixing", "5");
				int i = kechengService.insert_shangkeshijian(map);
				int m = 0;
				if (i != 0) {
					Map<String, String> newmap = new HashMap<>();
					newmap.put("xueXiaoID", xuexiaoid + "");
					newmap.put("riqi", newriqi);
					XueQi newxueQi = xueqiService.getByxueXiaoIDandriQi(newmap);
					Calendar newcal1 = Calendar.getInstance();
					newcal1.setTime(format.parse(newxueQi.getKaishiriqi()));
					Calendar newcal2 = Calendar.getInstance();
					newcal2.setTime(format.parse(newriqi));
					int newday1 = newcal1.get(Calendar.DAY_OF_YEAR);
					int newday2 = newcal2.get(Calendar.DAY_OF_YEAR);
					int newyear1 = newcal1.get(Calendar.YEAR);
					int newyear2 = newcal2.get(Calendar.YEAR);
					int newtimeDistance = 0;
					if (newyear1 != newyear2) { // 不同年
						for (int newi = newyear1; newi < newyear2; newi++) {
							if (newi % 4 == 0 && newi % 100 != 0 || newi % 400 == 0) {// 闰年
								newtimeDistance += 366;
							} else { // 不是闰年
								newtimeDistance += 365;
							}
							newtimeDistance += newday2 - newday1;
						}
					} else {// 同年
						newtimeDistance = newday2 - newday1;
					}
					int newzhou = newtimeDistance / 7 + 1;
					Calendar newc = Calendar.getInstance();
					newc.setTime(format.parse(newriqi));
					int newzhouci = 0;
					if (newc.get(Calendar.DAY_OF_WEEK) == 1) {
						newzhouci = 7;
					} else {
						newzhouci = newc.get(Calendar.DAY_OF_WEEK) - 1;
					}
					newmap.put("kechengid", id);
					newmap.put("zhouci", newzhouci + "");
					newmap.put("kaishizhou", newzhou + "");
					newmap.put("jiaoshiid", shangkejiaoshi);
					newmap.put("kaishijieci", kaishijieci);
					newmap.put("jieshujieci", jieshujieci);
					newmap.put("leixing", "6");
					m = kechengService.insert_shangkeshijian(newmap);
				}
				if (m != 0) {
					if (!canyurens.equals("")) {
						// 发送激光消息
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						xiaoXiFaSong.setXiaoXiMingCheng("调课");
						xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "将" + tiaokeriqi + "的《"
								+ kecheng.getKechengmingcheng() + "》调到了" + newriqi);
						xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(String.valueOf(xuexiaoid));
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date = new Date();
						tiXing.setNeirong(user.getXingming() + "将" + tiaokeriqi + "的《" + kecheng.getKechengmingcheng()
								+ "》调到了" + newriqi);
						tiXing.setShijian(date);
						tiXing.setZhuangtai(0);
						if (canyurens != null && !"".equals(canyurens)) {
							String cyr[] = canyurens.split(",");
							for (String string : cyr) {
								tiXing.setJieshourenid(Integer.parseInt(string));
								tixingService.insert(tiXing);
								xiaoXiFaSong.setFaSongMuBiao(string);
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
							}
						}
					}
					out.print("<script>alert('调课成功！');</script>");
					out.print("<script>location='kechengliebiao';</script>");
				} else {
					out.print("<script>alert('调课失败！');</script>");
					out.print("<script>location='kechengliebiao';</script>");
				}
			}
			if (tiaoting.equals("2")) {
				String newriqi = request.getParameter("newriqi");
				String shangkejiaoshi = request.getParameter("shangkejiaoshi");
				String kaishijieci = request.getParameter("kaishijieci");
				String jieshujieci = request.getParameter("jieshujieci");
				Map<String, String> newmap = new HashMap<>();
				newmap.put("xueXiaoID", xuexiaoid + "");
				newmap.put("riqi", newriqi);
				XueQi newxueQi = xueqiService.getByxueXiaoIDandriQi(newmap);
				Calendar newcal1 = Calendar.getInstance();
				newcal1.setTime(format.parse(newxueQi.getKaishiriqi()));
				Calendar newcal2 = Calendar.getInstance();
				newcal2.setTime(format.parse(newriqi));
				int newday1 = newcal1.get(Calendar.DAY_OF_YEAR);
				int newday2 = newcal2.get(Calendar.DAY_OF_YEAR);
				int newyear1 = newcal1.get(Calendar.YEAR);
				int newyear2 = newcal2.get(Calendar.YEAR);
				int newtimeDistance = 0;
				if (newyear1 != newyear2) { // 不同年
					for (int newi = newyear1; newi < newyear2; newi++) {
						if (newi % 4 == 0 && newi % 100 != 0 || newi % 400 == 0) {// 闰年
							newtimeDistance += 366;
						} else { // 不是闰年
							newtimeDistance += 365;
						}
						newtimeDistance += newday2 - newday1;
					}
				} else {// 同年
					newtimeDistance = newday2 - newday1;
				}
				int newzhou = newtimeDistance / 7 + 1;

				Calendar newc = Calendar.getInstance();
				newc.setTime(format.parse(newriqi));
				int newzhouci = 0;
				if (newc.get(Calendar.DAY_OF_WEEK) == 1) {
					newzhouci = 7;
				} else {
					newzhouci = newc.get(Calendar.DAY_OF_WEEK) - 1;
				}
				newmap.put("kechengid", id);
				newmap.put("zhouci", newzhouci + "");
				newmap.put("kaishizhou", newzhou + "");
				newmap.put("jiaoshiid", shangkejiaoshi);
				newmap.put("kaishijieci", kaishijieci);
				newmap.put("jieshujieci", jieshujieci);
				newmap.put("leixing", "6");
				int m = kechengService.insert_shangkeshijian(newmap);
				if (m != 0) {
					if (!canyurens.equals("")) {
						// 发送激光消息
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						xiaoXiFaSong.setXiaoXiMingCheng("加课");
						xiaoXiFaSong.setXiaoXiNeiRong(
								user.getXingming() + "在" + newriqi + "添加了一次《" + kecheng.getKechengmingcheng() + "》");
						xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(String.valueOf(xuexiaoid));
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date = new Date();
						tiXing.setNeirong(
								user.getXingming() + "在" + newriqi + "添加了一次《" + kecheng.getKechengmingcheng() + "》");
						tiXing.setShijian(date);
						tiXing.setZhuangtai(0);
						tiXing.setShujuid(Integer.parseInt(id));
						tiXing.setType(1);
						if (canyurens != null && !"".equals(canyurens)) {
							String cyr[] = canyurens.split(",");
							for (String string : cyr) {
								tiXing.setJieshourenid(Integer.parseInt(string));
								tixingService.insert(tiXing);
								xiaoXiFaSong.setFaSongMuBiao(string);
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
							}
						}
					}
					out.print("<script>alert('加课成功！');</script>");
					out.print("<script>location='kechengliebiao';</script>");
				} else {
					out.print("<script>alert('加课失败！');</script>");
					out.print("<script>location='kechengliebiao';</script>");
				}
			}
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "xiugaikecheng") // 修改课程
	public ModelAndView xiugaikecheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (kecheng == null) {
				response.sendRedirect("logout");
				return null;
			}
			if (!kecheng.getTianjiarenid().equals(user.getXueshengid().toString())) {
				response.sendRedirect("logout");
				return null;
			}
			if (kecheng.getLeixing() == 1) {
				int o = 0;
				List<String> banjiids = kechengService.getByAllBanJiIDByID(id);
				for (String string : banjiids) {
					if (string.equals(user.getBanjiid().toString())) {
						o = 1;
						break;
					}
				}
				if (o == 0) {
					response.sendRedirect("logout");
					return null;
				}
			}
			// 获取学校id
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			int xuexiaoid = Integer.parseInt(xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_")));
			// 校区列表
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			// 获取当前学期使用的节次列表
			Map<String, Integer> mapp = new HashMap<>();
			mapp.put("xuexiaoid", xuexiaoid);
			mapp.put("zhuangtai", 1);
			int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(mapp).getId();
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			// 获取上课周总数
			XueQi xueQi = xueqiService.selectByID(kecheng.getXueqiid());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = simpleDateFormat.parse(xueQi.getKaishiriqi());
			Date date2 = simpleDateFormat.parse(xueQi.getJieshuriqi());
			int zhounum = Util.WeeksBetweenDays(date1, date2);

			List<Map<String, String>> maps = new ArrayList<>();
			List<Map<String, Object>> shiJianMap = kechengService.getShangKeShiJianByID(id);
			if (shiJianMap != null && shiJianMap.size() > 0) {
				String ewaixinxi[] = request.getParameter("xinxi").split("/");
				if (ewaixinxi[0] != null && !("".equals(ewaixinxi[0])) && ewaixinxi[0].equals("3")) { // 次
					// xinxi=${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}
					for (Map<String, Object> map3 : shiJianMap) {
						if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("4")) {
							if (ewaixinxi[1].equals(map3.get("zhouCi").toString())) {
								String kaishijcsj = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ewaixinxi[3])
										+ "";
								String jieshujcsj = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid, ewaixinxi[4])
										+ "";
								if (kaishijcsj.equals(map3.get("kaiShiJieCi").toString())
										&& map3.get("jieShuJieCi").toString().equals(jieshujcsj)) {
									String zhoushu[] = ewaixinxi[2].split(",");
									for (String string : zhoushu) {
										if (string.equals(map3.get("kaiShiZhou").toString())) {
											kecheng.setLeixing(3);
											Map<String, String> map = new HashMap<>();
											int day = (Integer.parseInt(string) - 1) * 7
													+ Integer.parseInt(ewaixinxi[1]) - 1;
											// 获取每次上课日期
											String time = simpleDateFormat.format(
													new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
															+ (long) day * 24 * 60 * 60 * 1000));
											map.put("riqi", time);
											map.put("zhouci", ewaixinxi[1]);
											map.put("kaishijieci", ewaixinxi[3]);
											map.put("jieshujieci", ewaixinxi[4]);
											map.put("jiaoshiid", map3.get("jiaoShiID").toString());
											JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(
													Integer.parseInt(map3.get("jiaoShiID").toString()));
											JiaoXueLou jiaoXueLou = jiaoXueLouService
													.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
											map.put("jiaoxuelouid", jiaoXueLou.getJiaoXueLouId().toString());
											map.put("xiaoquid", jiaoXueLou.getXiaoQuId().toString());
											map.put("jiaoshiming", jiaoShi.getJiaoshiming());
											map.put("jiaoxuelouming", jiaoXueLou.getJiaoXueLouMing());
											map.put("xiaoquming", xiaoquService
													.selectByPrimaryKey(jiaoXueLou.getXiaoQuId()).getMingcheng());
											// 教室列表
											List<JiaoShi> jiaoShis = jiaoshiService
													.getAllByxiaoQuID(jiaoXueLou.getXiaoQuId());
											map.put("zhoushu", ewaixinxi[2]);
											List<JiaoXueLou> jiaoxuelous = jiaoXueLouService
													.selectByXiaoQuId(jiaoXueLou.getXiaoQuId());
											mv.addObject("jiaoxuelou", jiaoxuelous);
											mv.addObject("jiaoshi", jiaoShis);

											maps.add(map);
										}
									}
									mv.addObject("maps", maps);
								}
							}
						}
					}
				} else {
					// xinxi=${KeCheng.leixing}/${
					// maps.zhouci}/${maps.ds}/${maps.kaishijieci}/${maps.jieshujieci}/${KeCheng.kaishizhou}/${KeCheng.jieshuzhou}
					if (ewaixinxi[2].equals("1")) {// 单周
						for (Map<String, Object> map3 : shiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("2")) {
								if (ewaixinxi[5].equals(map3.get("kaiShiZhou").toString())
										&& ewaixinxi[6].equals(map3.get("jieShuZhou").toString())) {
									if (ewaixinxi[1].equals(map3.get("zhouCi").toString())) {
										String kaishijcsj = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
												ewaixinxi[3]) + "";
										String jieshujcsj = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
												ewaixinxi[4]) + "";
										if (kaishijcsj.equals(map3.get("kaiShiJieCi").toString())
												&& jieshujcsj.equals(map3.get("jieShuJieCi").toString())) {
											kecheng.setLeixing(2);
											kecheng.setKaishizhou(Integer.parseInt(ewaixinxi[5]));
											kecheng.setJieshuzhou(Integer.parseInt(ewaixinxi[6]));
											Map<String, String> map = new HashMap<>();
											map.put("zhouci", ewaixinxi[1]);
											map.put("kaishijieci", ewaixinxi[3]);
											map.put("jieshujieci", ewaixinxi[4]);
											map.put("jiaoshiid", map3.get("jiaoShiID").toString());
											JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(
													Integer.parseInt(map3.get("jiaoShiID").toString()));
											JiaoXueLou jiaoXueLou = jiaoXueLouService
													.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
											map.put("jiaoxuelouid", jiaoXueLou.getJiaoXueLouId().toString());
											map.put("xiaoquid", jiaoXueLou.getXiaoQuId().toString());
											map.put("jiaoshiming", jiaoShi.getJiaoshiming());
											map.put("jiaoxuelouming", jiaoXueLou.getJiaoXueLouMing());
											map.put("xiaoquming", xiaoquService
													.selectByPrimaryKey(jiaoXueLou.getXiaoQuId()).getMingcheng());
											// 教室列表
											List<JiaoShi> jiaoShis = jiaoshiService
													.getAllByxiaoQuID(jiaoXueLou.getXiaoQuId());
											map.put("ds", "1");
											maps.add(map);
											List<JiaoXueLou> jiaoxuelous = jiaoXueLouService
													.selectByXiaoQuId(jiaoXueLou.getXiaoQuId());
											mv.addObject("jiaoxuelou", jiaoxuelous);
											mv.addObject("jiaoshi", jiaoShis);
											mv.addObject("maps", map);
										}
									}
								}
							}
						}

					} else if (ewaixinxi[2].equals("2")) { // 双周
						for (Map<String, Object> map3 : shiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("3")) {
								if (ewaixinxi[5].equals(map3.get("kaiShiZhou").toString())
										&& ewaixinxi[6].equals(map3.get("jieShuZhou").toString())) {
									if (ewaixinxi[1].equals(map3.get("zhouCi").toString())) {
										String kaishijcsj = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
												ewaixinxi[3]) + "";
										String jieshujcsj = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
												ewaixinxi[4]) + "";
										if (kaishijcsj.equals(map3.get("kaiShiJieCi").toString())
												&& jieshujcsj.equals(map3.get("jieShuJieCi").toString())) {
											kecheng.setLeixing(2);
											kecheng.setKaishizhou(Integer.parseInt(ewaixinxi[5]));
											kecheng.setJieshuzhou(Integer.parseInt(ewaixinxi[6]));
											Map<String, String> map = new HashMap<>();
											map.put("zhouci", ewaixinxi[1]);
											map.put("kaishijieci", ewaixinxi[3]);
											map.put("jieshujieci", ewaixinxi[4]);
											map.put("jiaoshiid", map3.get("jiaoShiID").toString());
											JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(
													Integer.parseInt(map3.get("jiaoShiID").toString()));
											JiaoXueLou jiaoXueLou = jiaoXueLouService
													.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
											map.put("jiaoxuelouid", jiaoXueLou.getJiaoXueLouId().toString());
											map.put("xiaoquid", jiaoXueLou.getXiaoQuId().toString());
											map.put("jiaoshiming", jiaoShi.getJiaoshiming());
											map.put("jiaoxuelouming", jiaoXueLou.getJiaoXueLouMing());
											map.put("xiaoquming", xiaoquService
													.selectByPrimaryKey(jiaoXueLou.getXiaoQuId()).getMingcheng());
											// 教室列表
											List<JiaoShi> jiaoShis = jiaoshiService
													.getAllByxiaoQuID(jiaoXueLou.getXiaoQuId());
											map.put("ds", "2");
											maps.add(map);
											List<JiaoXueLou> jiaoxuelous = jiaoXueLouService
													.selectByXiaoQuId(jiaoXueLou.getXiaoQuId());
											mv.addObject("jiaoxuelou", jiaoxuelous);
											mv.addObject("jiaoshi", jiaoShis);
											mv.addObject("maps", map);
										}
									}
								}
							}
						}
					} else { // 每周
						for (Map<String, Object> map3 : shiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("1")) {
								if (ewaixinxi[5].equals(map3.get("kaiShiZhou").toString())
										&& ewaixinxi[6].equals(map3.get("jieShuZhou").toString())) {
									if (ewaixinxi[1].equals(map3.get("zhouCi").toString())) {
										String kaishijcsj = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
												ewaixinxi[3]) + "";
										String jieshujcsj = jcsjService.findJCSJIDbyFangAnIdandJcsj(jiecifanganid,
												ewaixinxi[4]) + "";
										if (kaishijcsj.equals(map3.get("kaiShiJieCi").toString())
												&& jieshujcsj.equals(map3.get("jieShuJieCi").toString())) {
											kecheng.setKaishizhou(Integer.parseInt(ewaixinxi[5]));
											kecheng.setJieshuzhou(Integer.parseInt(ewaixinxi[6]));
											Map<String, String> map = new HashMap<>();
											map.put("zhouci", ewaixinxi[1]);
											map.put("kaishijieci", ewaixinxi[3]);
											map.put("jieshujieci", ewaixinxi[4]);
											map.put("jiaoshiid", map3.get("jiaoShiID").toString());
											JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(
													Integer.parseInt(map3.get("jiaoShiID").toString()));
											JiaoXueLou jiaoXueLou = jiaoXueLouService
													.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
											map.put("jiaoxuelouid", jiaoXueLou.getJiaoXueLouId().toString());
											map.put("xiaoquid", jiaoXueLou.getXiaoQuId().toString());
											map.put("jiaoshiming", jiaoShi.getJiaoshiming());
											map.put("jiaoxuelouming", jiaoXueLou.getJiaoXueLouMing());
											map.put("xiaoquming", xiaoquService
													.selectByPrimaryKey(jiaoXueLou.getXiaoQuId()).getMingcheng());
											// 教室列表
											List<JiaoShi> jiaoShis = jiaoshiService
													.getAllByxiaoQuID(jiaoXueLou.getXiaoQuId());
											map.put("ds", "0");
											maps.add(map);
											List<JiaoXueLou> jiaoxuelous = jiaoXueLouService
													.selectByXiaoQuId(jiaoXueLou.getXiaoQuId());
											mv.addObject("jiaoxuelou", jiaoxuelous);
											mv.addObject("jiaoshi", jiaoShis);
											mv.addObject("maps", map);
										}
									}
								}
							}
						}
					}
				}
			} else {
				response.sendRedirect("login");
				return null;
			}
			mv.addObject("zhounum", zhounum);
			mv.addObject("jieci", jcsjs);
			mv.addObject("kecheng", kecheng);
			mv.addObject("xiaoqu", xiaoQus);
			mv.setViewName("stu/xiugaikecheng");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "savekecheng_update") // 保存修改课程
	public ModelAndView savekecheng_update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String[] xueXiaoXueHao = user.getXuexiaoXuehao().split("_");
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			KeCheng keCheng2 = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (keCheng2 == null) {
				response.sendRedirect("logout");
				return null;
			}
			if (!keCheng2.getTianjiarenid().equals(user.getXueshengid().toString())) {
				response.sendRedirect("logout");
				return null;
			}
			String canyurens = "";
			List<String> mianxiuids = kechengService.getAllMianXiuIDByID(id);
			if (keCheng2.getLeixing() == 1) {
				List<String> banjiids = kechengService.getByAllBanJiIDByID(id);
				for (String string : banjiids) {
					List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueshengs) {
						int o = 0;
						for (String s : mianxiuids) {
							if (s.equals(xueSheng.getXueshengid().toString())) {
								o = 1;
								break;
							}
						}
						if (o == 0) {
							canyurens += xueSheng.getXueshengid() + ",";
						}
					}
				}
			}
			String leixing = request.getParameter("leixing");
			Map<String, Integer> mapp = new HashMap<>();
			mapp.put("xuexiaoid", Integer.parseInt(xueXiaoXueHao[0]));
			mapp.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(mapp);
			XueQi xueQi2 = xueqiService.selectByID(keCheng2.getXueqiid());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			List<Map<String, Object>> shiJianMap = kechengService.getShangKeShiJianByID(id);
			int i = 0;
			if (shiJianMap != null && shiJianMap.size() > 0) {
				for (Map<String, Object> map3 : shiJianMap) {
					Map<String, String> paramMap = new HashMap<>();
					paramMap.put("kechengid", id);
					if (leixing != null && !("".equals(leixing)) && leixing.equals("3")) { // 次
						String yuanriqi[] = request.getParameter("yuanriqi").split(",");
						// ${maps.riqi
						// }/${maps.kaishijieci}/${maps.jieshujieci}/${maps.jiaoshiid}
						String cixinxi[] = request.getParameter("cixinxi").split(";");
						// cixinxi +=
						// riqi+","+kaishijieci+","+jieshujieci+","+shangkejiaoshi+";";
						if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("4")) {
							if (yuanriqi != null && yuanriqi.length > 0) {
								int same = yuanriqi.length + 6;
								for (int m = 0; m < yuanriqi.length; m++) {
									if (!"".equals(yuanriqi[m]) && yuanriqi[m] != null) {
										String[] yuanxinxi = yuanriqi[m].split("/");

										Calendar cal1 = Calendar.getInstance();
										cal1.setTime(format.parse(xueQi2.getKaishiriqi()));
										Calendar cal2 = Calendar.getInstance();
										cal2.setTime(format.parse(yuanxinxi[0]));
										int day1 = cal1.get(Calendar.DAY_OF_YEAR);
										int day2 = cal2.get(Calendar.DAY_OF_YEAR);
										int year1 = cal1.get(Calendar.YEAR);
										int year2 = cal2.get(Calendar.YEAR);
										int timeDistance = 0;
										if (year1 != year2) { // 不同年
											for (int y = year1; y < year2; y++) {
												if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {// 闰年
													timeDistance += 366;
												} else { // 不是闰年
													timeDistance += 365;
												}
												timeDistance += day2 - day1;
											}
										} else {// 同年
											timeDistance = day2 - day1;
										}
										int zhou = timeDistance / 7 + 1;

										Calendar c = Calendar.getInstance();
										c.setTime(format.parse(yuanxinxi[0]));
										int zhouci = 0;
										if (c.get(Calendar.DAY_OF_WEEK) == 1) {
											zhouci = 7;
										} else {
											zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
										}
										if (map3.get("kaiShiZhou").toString().equals(zhou + "")
												&& map3.get("zhouCi").toString().equals("" + zhouci)) {
											String kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													yuanxinxi[1]) + "";
											String jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													yuanxinxi[2]) + "";

											if (kaishi.equals(map3.get("kaiShiJieCi").toString())
													&& jieshu.equals(map3.get("jieShuJieCi").toString())
													&& map3.get("jiaoShiID").toString().equals(yuanxinxi[3])) {
												same = m;
												paramMap.put("yuanjiaoshiid", yuanxinxi[3]);
												paramMap.put("yuankaishijieci", kaishi);
												paramMap.put("yuanjieshujieci", jieshu);
												paramMap.put("yuanleixing", "4");
												paramMap.put("yuankaishizhou", zhou + "");
												paramMap.put("yuanzhouci", zhouci + "");
											}
										}
										if (same != (yuanriqi.length + 6)) {
											String newxinxi[] = cixinxi[same].split(",");
											Calendar ccal1 = Calendar.getInstance();
											ccal1.setTime(format.parse(xueQi2.getKaishiriqi()));
											Calendar ccal2 = Calendar.getInstance();
											ccal2.setTime(format.parse(newxinxi[0]));
											int cday1 = ccal1.get(Calendar.DAY_OF_YEAR);
											int cday2 = ccal2.get(Calendar.DAY_OF_YEAR);
											int cyear1 = ccal1.get(Calendar.YEAR);
											int cyear2 = ccal2.get(Calendar.YEAR);
											int ctimeDistance = 0;
											if (cyear1 != cyear2) { // 不同年
												for (int y = year1; y < year2; y++) {
													if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {// 闰年
														ctimeDistance += 366;
													} else { // 不是闰年
														ctimeDistance += 365;
													}
													ctimeDistance += cday2 - cday1;
												}
											} else {// 同年
												ctimeDistance = cday2 - cday1;
											}
											int czhou = ctimeDistance / 7 + 1;

											Calendar cc = Calendar.getInstance();
											cc.setTime(format.parse(newxinxi[0]));
											int czhouci = 0;
											if (cc.get(Calendar.DAY_OF_WEEK) == 1) {
												czhouci = 7;
											} else {
												czhouci = cc.get(Calendar.DAY_OF_WEEK) - 1;
											}
											paramMap.put("jiaoshiid", newxinxi[3]);
											paramMap.put("kaishijieci", newxinxi[1]);
											paramMap.put("jieshujieci", newxinxi[2]);
											paramMap.put("leixing", "4");
											paramMap.put("kaishizhou", czhou + "");
											paramMap.put("zhouci", czhouci + "");

											i = kechengService.update_shangkeshijian(paramMap);
										}
									}
								}
							}
						}
					} else {
						String shangkejiaoshi = request.getParameter("shangkejiaoshi");
						String kaishizhou = request.getParameter("kaishizhou");
						String jieshuzhou = request.getParameter("jieshuzhou");
						String zhouci = request.getParameter("zhouci");
						String kaishijieci = request.getParameter("kaishijieci");
						String jieshujieci = request.getParameter("jieshujieci");
						String ds = request.getParameter("ds");
						// ${maps.ds}/${maps.zhouci}/${maps.kaishijieci}/${maps.jieshujieci}/${maps.jiaoshiid}/${kecheng.kaishizhou}/${kecheng.jieshuzhou}
						if (!"".equals(request.getParameter("maps")) && request.getParameter("maps") != null) {
							String shuoming[] = request.getParameter("maps").split("/");
							if (shuoming != null && shuoming.length > 0) {
								if (shuoming[0].equals("1")) {// 单周
									if (map3.containsKey("shiJianLeiXing")
											&& map3.get("shiJianLeiXing").toString().equals("2")) {
										if (shuoming[4].equals(map3.get("jiaoShiID").toString())
												&& map3.get("kaiShiZhou").toString().equals(shuoming[5])
												&& map3.get("jieShuZhou").toString().equals(shuoming[6])) {
											String kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													shuoming[2]) + "";
											String jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													shuoming[3]) + "";
											if (map3.get("zhouCi").toString().equals(shuoming[1])
													&& map3.get("kaiShiJieCi").toString().equals(kaishi)
													&& map3.get("jieShuJieCi").toString().equals(jieshu)) {
												paramMap.put("yuanjiaoshiid", shuoming[4]);
												paramMap.put("yuankaishijieci", kaishi);
												paramMap.put("yuanjieshujieci", jieshu);
												paramMap.put("yuanleixing", "2");
												paramMap.put("yuankaishizhou", shuoming[5]);
												paramMap.put("yuanjieshuzhou", shuoming[6]);
												paramMap.put("yuanzhouci", shuoming[1]);
												paramMap.put("jiaoshiid", shangkejiaoshi);
												paramMap.put("kaishijieci", kaishijieci);
												paramMap.put("jieshujieci", jieshujieci);
												if (ds.equals("0")) {
													paramMap.put("leixing", "1");
												}
												if (ds.equals("1")) {
													paramMap.put("leixing", "2");
												}
												if (ds.equals("2")) {
													paramMap.put("leixing", "3");
												}
												paramMap.put("kaishizhou", kaishizhou);
												paramMap.put("jieshuzhou", jieshuzhou);
												paramMap.put("zhouci", zhouci);
												i = kechengService.update_shangkeshijian(paramMap);
												break;
											}
										}
									}
								} else if (shuoming[0].equals("2")) {// 双周
									if (map3.containsKey("shiJianLeiXing")
											&& map3.get("shiJianLeiXing").toString().equals("3")) {
										if (shuoming[4].equals(map3.get("jiaoShiID").toString())
												&& map3.get("kaiShiZhou").toString().equals(shuoming[5])
												&& map3.get("jieShuZhou").toString().equals(shuoming[6])) {
											String kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													shuoming[2]) + "";
											String jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													shuoming[3]) + "";
											if (map3.get("zhouCi").toString().equals(shuoming[1])
													&& map3.get("kaiShiJieCi").toString().equals(kaishi)
													&& map3.get("jieShuJieCi").toString().equals(jieshu)) {
												paramMap.put("yuanjiaoshiid", shuoming[4]);
												paramMap.put("yuankaishijieci", kaishi);
												paramMap.put("yuanjieshujieci", jieshu);
												paramMap.put("yuanleixing", "3");
												paramMap.put("yuankaishizhou", shuoming[5]);
												paramMap.put("yuanjieshuzhou", shuoming[6]);
												paramMap.put("yuanzhouci", shuoming[1]);
												paramMap.put("jiaoshiid", shangkejiaoshi);
												paramMap.put("kaishijieci", kaishijieci);
												paramMap.put("jieshujieci", jieshujieci);
												if (ds.equals("0")) {
													paramMap.put("leixing", "1");
												}
												if (ds.equals("1")) {
													paramMap.put("leixing", "2");
												}
												if (ds.equals("2")) {
													paramMap.put("leixing", "3");
												}
												paramMap.put("kaishizhou", kaishizhou);
												paramMap.put("jieshuzhou", jieshuzhou);
												paramMap.put("zhouci", zhouci);
												i = kechengService.update_shangkeshijian(paramMap);
												break;
											}
										}
									}
								} else {// 每周
									if (map3.containsKey("shiJianLeiXing")
											&& map3.get("shiJianLeiXing").toString().equals("1")) {
										if (shuoming[4].equals(map3.get("jiaoShiID").toString())
												&& map3.get("kaiShiZhou").toString().equals(shuoming[5])
												&& map3.get("jieShuZhou").toString().equals(shuoming[6])) {
											String kaishi = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													shuoming[2]) + "";
											String jieshu = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													shuoming[3]) + "";
											if (map3.get("zhouCi").toString().equals(shuoming[1])
													&& map3.get("kaiShiJieCi").toString().equals(kaishi)
													&& map3.get("jieShuJieCi").toString().equals(jieshu)) {
												paramMap.put("yuanjiaoshiid", shuoming[4]);
												paramMap.put("yuankaishijieci", kaishi);
												paramMap.put("yuanjieshujieci", jieshu);
												paramMap.put("yuanleixing", "1");
												paramMap.put("yuankaishizhou", shuoming[5]);
												paramMap.put("yuanjieshuzhou", shuoming[6]);
												paramMap.put("yuanzhouci", shuoming[1]);
												paramMap.put("jiaoshiid", shangkejiaoshi);
												paramMap.put("kaishijieci", kaishijieci);
												paramMap.put("jieshujieci", jieshujieci);
												if (ds.equals("0")) {
													paramMap.put("leixing", "1");
												}
												if (ds.equals("1")) {
													paramMap.put("leixing", "2");
												}
												if (ds.equals("2")) {
													paramMap.put("leixing", "3");
												}
												paramMap.put("kaishizhou", kaishizhou);
												paramMap.put("jieshuzhou", jieshuzhou);
												paramMap.put("zhouci", zhouci);
												i = kechengService.update_shangkeshijian(paramMap);
												break;
											}
										}
									}
								}
							}
						} else {
							response.sendRedirect("login");
							return null;
						}
					}
				}
			} else {
				response.sendRedirect("login");
				return null;
			}
			if (i != 0) {
				// 发送极光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("修改课程");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "修改了课程《" + keCheng2.getKechengmingcheng() + "》");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
				xiaoXiFaSong.setShuJuLeiXing(1);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoXueHao[0]);
				// 发送消息提醒
				TiXing tiXing = new TiXing();
				Date date2 = new Date();
				tiXing.setNeirong(user.getXingming() + "修改了课程《" + keCheng2.getKechengmingcheng() + "》");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(Integer.parseInt(id));
				tiXing.setType(1);
				if (canyurens != null && !"".equals(canyurens)) {
					String cyr[] = canyurens.split(",");
					for (String string : cyr) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						xiaoXiFaSong.setFaSongMuBiao(string);
						tixingService.insert(tiXing);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
				}
				out.print("<script>alert('修改成功！');</script>");
				out.print("<script>location='kechengliebiao';</script>");
			} else {
				out.print("<script>alert('修改失败！');</script>");
				out.print("<script>location='kechengliebiao';</script>");
			}
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping("wodericheng") // 我的日程
	public ModelAndView wodericheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView();
		if (request.getSession().getAttribute("user") != null && request.getSession().getAttribute("user") != "") {
			model.setViewName("stu/wodericheng");
			return model;
		} else
			response.sendRedirect("login");
		return model;
	}

	@RequestMapping(value = "chaxunrichengbydate")
	@ResponseBody
	public JSONObject wodericheng_bydate(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		JSONObject json = new JSONObject();
		Map<String, Object> returnMap = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			if (user != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String riqi = request.getParameter("riqi");
				String nextriqi = sdf.format(sdf.parse(riqi).getTime() + 1000 * 60 * 60 * 24);
				map.put("renid", user.getXueshengid().toString());
				map.put("banjiid", user.getBanjiid().toString());
				map.put("riqi", riqi);
				map.put("renleixing", "0");
				List<HuoDong> huodongs = huodongService.getAllByRiQiAndRenIDAndRenLeiXing(map);
				for (HuoDong huoDong : huodongs) {
					map.put("huodongid", huoDong.getHuodongid().toString());
					List<Map<String, Object>> canyuren = huodongService
							.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
					List<Map<String, Object>> jujueren = huodongService
							.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
					if (huoDong.getTianjiarenid().toString().equals(user.getXueshengid().toString())) {
						if (jujueren != null && jujueren.size() > 0)
							huoDong.setZhuangtai(2);
						else
							huoDong.setZhuangtai(1);
					} else {
						int i = 0;
						if (canyuren != null && canyuren.size() > 0) {
							i = 1;
							huoDong.setZhuangtai(1);
						}
						if (jujueren != null && jujueren.size() > 0) {
							i = 1;
							huoDong.setZhuangtai(2);
						}
						if (i == 0)
							huoDong.setZhuangtai(0);
					}
				}
				List<KeCheng> keChengs2 = new ArrayList<>();
				Map<String, String> m = new HashMap<>();
				String xuexiaoid = user.getXuexiaoXuehao().split("_")[0];
				m.put("xueXiaoID", xuexiaoid);
				m.put("riqi", riqi);
				XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(m);
				if (xueQi == null) {
					returnMap.put("kechengs", keChengs2);
				} else {
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(sdf.parse(xueQi.getKaishiriqi()));
					Calendar cal2 = Calendar.getInstance();
					cal2.setTime(sdf.parse(riqi));
					int day1 = cal1.get(Calendar.DAY_OF_YEAR);
					int day2 = cal2.get(Calendar.DAY_OF_YEAR);
					int year1 = cal1.get(Calendar.YEAR);
					int year2 = cal2.get(Calendar.YEAR);
					int timeDistance = 0;
					if (year1 != year2) { // 不同年
						for (int i = year1; i < year2; i++) {
							if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
								timeDistance += 366;
							} else { // 不是闰年
								timeDistance += 365;
							}
							timeDistance += day2 - day1;
						}
					} else {// 同年
						timeDistance = day2 - day1;
					}
					int zhou = timeDistance / 7 + 1;

					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(riqi));
					int zhouci = 0;
					if (c.get(Calendar.DAY_OF_WEEK) == 1) {
						zhouci = 7;
					} else {
						zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
					}
					List<Map<String, Object>> keChengMaps = kechengService.getByBanJiIDAndXueShengID(user.getBanjiid(),
							user.getXueshengid().toString());
					for (Map<String, Object> keChengMap : keChengMaps) {
						if (xueQi.getXueqiid().toString().equals(keChengMap.get("xueQiID").toString())) {
							// 停课情况
							List<String> tingkes = new ArrayList<>();
							for (Map<String, Object> tingKe : keChengMaps) {
								if (keChengMap.get("ID").toString().equals(tingKe.get("ID").toString())) {
									if (tingKe.containsKey("shiJianLeiXing")
											&& tingKe.get("shiJianLeiXing").toString().equals("5")) {
										if ((zhou + "").equals(tingKe.get("kaiShiZhou").toString())) {
											tingkes.add(tingKe.get("zhouCi").toString());
										}
									}
								}
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("1")
									&& zhou >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
									&& zhou <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())
									&& zhouci == Integer.parseInt(keChengMap.get("zhouCi").toString())) {
								int i = 0;
								if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
									for (String zhouCi : tingkes) {
										if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
											i = 1;
											break;
										}
									}
									if (i == 0) {
										KeCheng kecheng = new KeCheng();
										kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
										kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
										kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
										kecheng.setKaishizhou(
												Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
										kecheng.setJieshuzhou(
												Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());
										kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
										JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
										kecheng.setKaishishijian(jcsj1.getKaishishijian());
										kecheng.setJieshushijian(jcsj2.getJieshushijian());
										keChengs2.add(kecheng);
									}
								} else {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
									kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
									kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
									kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
									kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());
									kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
									kecheng.setKaishishijian(jcsj1.getKaishishijian());
									kecheng.setJieshushijian(jcsj2.getJieshushijian());
									keChengs2.add(kecheng);

								}
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("2")
									&& zhou >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
									&& zhou <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())
									&& zhouci == Integer.parseInt(keChengMap.get("zhouCi").toString())) {
								int i = 0;
								if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
									for (String zhouCi : tingkes) {
										if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
											i = 1;
											break;
										}
									}
									if (i == 0) {
										if (zhou % 2 != 0) {
											KeCheng kecheng = new KeCheng();
											kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
											kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
											kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
											kecheng.setKaishizhou(
													Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
											kecheng.setJieshuzhou(
													Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
											JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
													Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
											JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
													Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
											kecheng.setKaishijieci(jcsj1.getJieci());
											kecheng.setJieshujieci(jcsj2.getJieci());
											kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
											JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
													Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
											kecheng.setJiaoshiming(jshi.getJiaoshiming());
											JiaoXueLou jxl = jiaoXueLouService
													.selectByPrimaryKey(jshi.getJiaoxuelouid());
											kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
											kecheng.setXiaoquming(
													xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
											kecheng.setKaishishijian(jcsj1.getKaishishijian());
											kecheng.setJieshushijian(jcsj2.getJieshushijian());
											keChengs2.add(kecheng);
										}
									}
								} else {
									if (zhou % 2 != 0) {
										KeCheng kecheng = new KeCheng();
										kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
										kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
										kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
										kecheng.setKaishizhou(
												Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
										kecheng.setJieshuzhou(
												Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());
										kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
										JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
										kecheng.setKaishishijian(jcsj1.getKaishishijian());
										kecheng.setJieshushijian(jcsj2.getJieshushijian());
										keChengs2.add(kecheng);
									}
								}
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("3")
									&& zhou >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
									&& zhou <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())
									&& zhouci == Integer.parseInt(keChengMap.get("zhouCi").toString())) {
								int i = 0;
								if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
									for (String zhouCi : tingkes) {
										if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
											i = 1;
											break;
										}
									}
									if (i == 0) {
										if (zhou % 2 == 0) {
											KeCheng kecheng = new KeCheng();
											kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
											kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
											kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
											kecheng.setKaishizhou(
													Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
											kecheng.setJieshuzhou(
													Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
											JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
													Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
											JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
													Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
											kecheng.setKaishijieci(jcsj1.getJieci());
											kecheng.setJieshujieci(jcsj2.getJieci());
											kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
											JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
													Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
											kecheng.setJiaoshiming(jshi.getJiaoshiming());
											JiaoXueLou jxl = jiaoXueLouService
													.selectByPrimaryKey(jshi.getJiaoxuelouid());
											kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
											kecheng.setXiaoquming(
													xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
											kecheng.setKaishishijian(jcsj1.getKaishishijian());
											kecheng.setJieshushijian(jcsj2.getJieshushijian());
											keChengs2.add(kecheng);
										}
									}
								} else {
									if (zhou % 2 == 0) {
										KeCheng kecheng = new KeCheng();
										kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
										kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
										kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
										kecheng.setKaishizhou(
												Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
										kecheng.setJieshuzhou(
												Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());
										kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
										JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
										kecheng.setKaishishijian(jcsj1.getKaishishijian());
										kecheng.setJieshushijian(jcsj2.getJieshushijian());
										keChengs2.add(kecheng);
									}
								}
							}

							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("4")
									&& zhou == Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
									&& zhouci == Integer.parseInt(keChengMap.get("zhouCi").toString())) {
								int i = 0;
								if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
									for (String zhouCi : tingkes) {
										if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
											i = 1;
											break;
										}
									}
									if (i == 0) {
										KeCheng kecheng = new KeCheng();
										kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
										kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
										kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
										kecheng.setKaishizhou(
												Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
										// kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());
										kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
										JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
										kecheng.setKaishishijian(jcsj1.getKaishishijian());
										kecheng.setJieshushijian(jcsj2.getJieshushijian());
										keChengs2.add(kecheng);
									}
								} else {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
									kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
									kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
									kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
									// kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());
									kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(
											Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
									kecheng.setKaishishijian(jcsj1.getKaishishijian());
									kecheng.setJieshushijian(jcsj2.getJieshushijian());
									keChengs2.add(kecheng);

								}
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("6")
									&& zhou == Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
									&& zhouci == Integer.parseInt(keChengMap.get("zhouCi").toString())) {

								KeCheng kecheng = new KeCheng();
								kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
								kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
								kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
								kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
								// kecheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
								JCSJ jcsj1 = jcsjService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("kaiShiJieCi").toString()));
								JCSJ jcsj2 = jcsjService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jieShuJieCi").toString()));
								kecheng.setKaishijieci(jcsj1.getJieci());
								kecheng.setJieshujieci(jcsj2.getJieci());
								kecheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
								JiaoShi jshi = jiaoshiService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
								kecheng.setJiaoshiming(jshi.getJiaoshiming());
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
								kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());
								kecheng.setXiaoquming(
										xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
								kecheng.setKaishishijian(jcsj1.getKaishishijian());
								kecheng.setJieshushijian(jcsj2.getJieshushijian());
								keChengs2.add(kecheng);
							}
						}
					}
					returnMap.put("kechengs", keChengs2);
				}
				map.put("kaishi", riqi + " 00:00");
				map.put("jieshu", nextriqi + " 00:00");
				List<BeiWL> beiwanglus = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(map);
				// for (BeiWL beiWL : beiwanglus) {
				// YongHu yongHu =
				// yonghuService.selectYongHuByID(Integer.parseInt(beiWL.getUserid()));
				// if (yongHu != null)
				// beiWL.setUsername(yongHu.getYonghuxingming());
				// else {
				// XueSheng xueSheng =
				// xueshengService.getUserById(Integer.parseInt(beiWL.getUserid()));
				// beiWL.setUsername(xueSheng.getXingming());
				// }
				// if (beiWL.getHuizhi() != null && beiWL.getHuizhi() == 1) {
				// String jieshourens[] = beiWL.getJieshouren().split(";");
				// if (beiWL.getLeixing() == 1)
				// for (String jieshourenn : jieshourens) {
				// String jieshou[] = jieshourenn.split(",");
				// if (jieshou[0].equals(user.getXueshengid()))
				// beiWL.setZhuangtai(jieshou[1]);
				// }
				// if (beiWL.getLeixing() == 2)
				// for (String jieshourenn : jieshourens) {
				// String jieshou[] = jieshourenn.split(":")[1].split("!");
				// for (String string : jieshou) {
				// String str[] = string.split(",");
				// if (str[0].equals(user.getXueshengid()))
				// beiWL.setZhuangtai(str[1]);
				// }
				// }
				// }
				// }
				returnMap.put("huodongs", huodongs);
				returnMap.put("beiwanglus", beiwanglus);
			} else {
				returnMap.put("huodongs", null);
				returnMap.put("kechengs", null);
				returnMap.put("beiwanglus", null);
			}
			json.put("shuju", returnMap);
			return json;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "chaxunricheng") // 查询日程
	public ModelAndView chaxunricheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String canyuren1 = "0," + user.getXueshengid().toString() + ",1;%";
			String canyuren2 = "%;0," + user.getXueshengid().toString() + ",1;%";
			List<HuoDong> huodongs = huodongService.getAllBycanYuRen(canyuren1, canyuren2);
			for (HuoDong huoDong : huodongs) {
				String tianjiarenid = huoDong.getTianjiarenid().toString();
				YongHu tianjiaren = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
				String faqiren = "";
				int faqirenleixing;
				if (tianjiaren == null) {
					faqirenleixing = 0;
					faqiren = xueshengService.getUserById(Integer.parseInt(tianjiarenid)).getXingming();
				} else {
					faqirenleixing = 1;
					faqiren = tianjiaren.getYonghuxingming();
				}
				huoDong.setFaqirenleixing(faqirenleixing);
				huoDong.setFaqiren(faqiren);
			}
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String chaxunriqi = year + "-" + month + "-" + day;
			// 课程
			/*
			 * Map<String, String> map = new HashMap<>(); map.put("canyuren1",
			 * user.getXueshengid().toString()+",1;%"); map.put("shangkeriqi1",
			 * chaxunriqi+"%"); map.put("canyuren2",
			 * "%;"+user.getXueshengid().toString()+",1;%");
			 * map.put("shangkeriqi2", "%"+chaxunriqi+"%"); List<KeCheng>
			 * keChengs = kechengService.getAllByxueShengIDandshangKeRiQi(map);
			 */
			Map<String, String> map = new HashMap<>();
			map.put("banjiid1", user.getBanjiid() + ",%");
			map.put("banjiid2", "%," + user.getBanjiid() + ",%");
			map.put("xueshengid1", user.getXueshengid() + ",%");
			map.put("xueshengid2", "%," + user.getXueshengid() + ",%");
			map.put("shangkeriqi1", chaxunriqi + "%");
			map.put("shangkeriqi2", "%" + chaxunriqi + "%");
			List<KeCheng> keChengs = kechengService.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDsAndshangKeRiQi(map);
			for (KeCheng keCheng : keChengs) {
				String shangkeriqi[] = keCheng.getShangkeriqi().split(",");
				for (String string : shangkeriqi) {
					String riqi1[] = string.split(";");
					if (riqi1[0].equals(chaxunriqi) && riqi1.length != 1) {
						keCheng.setXiaoqu(riqi1[1]);
						keCheng.setShangkejiaoshi(riqi1[2]);
						keCheng.setKaishijieci(Integer.parseInt(riqi1[3]));
						keCheng.setJieshujieci(Integer.parseInt(riqi1[4]));
					}
				}
				int kaishijieci = keCheng.getKaishijieci();
				int jieshujieci = keCheng.getJieshujieci();
				String kaishishijian = jcsjService.selectByPrimaryKey(kaishijieci).getKaishishijian();
				String jieshushijian = jcsjService.selectByPrimaryKey(jieshujieci).getJieshushijian();
				keCheng.setKaishishijian(kaishishijian);
				keCheng.setJieshushijian(jieshushijian);
				String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
						.getMingcheng();
				String jiaoshiming = jiaoshiService.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi()))
						.getJiaoshiming();
				keCheng.setXiaoquming(xiaoquming);
				keCheng.setJiaoshiming(jiaoshiming);
			}

			mv.addObject("year", year);
			mv.addObject("month", month);
			mv.addObject("day", day);
			mv.addObject("chaxunriqi", chaxunriqi);
			mv.addObject("huodong", huodongs);
			mv.addObject("kecheng", keChengs);
			mv.addObject("user", user);
			mv.setViewName("stu/wodericheng");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	// 查询日程表中的单门课程
	@RequestMapping("chaxundanmenkecheng_stu")
	@ResponseBody
	public JSONObject chaxundanmenkecheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		String id = request.getParameter("id");
		String riqi = request.getParameter("riqi");
		String zhouci = request.getParameter("zhouci");
		String kaishijieci = request.getParameter("kaishijieci");
		String jieshujieci = request.getParameter("jieshujieci");
		String jiaoshiid = request.getParameter("jiaoshiid");
		JSONObject json = new JSONObject();
		if (request.getSession().getAttribute("user") != null && request.getSession().getAttribute("user") != "") {
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			XueSheng user = (XueSheng) request.getSession().getAttribute("user");
			String xueXiaoID = user.getXuexiaoXuehao().split("_")[0];
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("riqi", riqi);
			paramMap.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(paramMap);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(format.parse(xueQi.getKaishiriqi()));
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(format.parse(riqi));
			int day1 = cal1.get(Calendar.DAY_OF_YEAR);
			int day2 = cal2.get(Calendar.DAY_OF_YEAR);
			int year1 = cal1.get(Calendar.YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			int timeDistance = 0;
			if (year1 != year2) { // 不同年
				for (int i = year1; i < year2; i++) {
					if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
						timeDistance += 366;
					} else { // 不是闰年
						timeDistance += 365;
					}
					timeDistance += day2 - day1;
				}
			} else {// 同年
				timeDistance = day2 - day1;
			}
			int zhou = timeDistance / 7 + 1;
			KeCheng kecheng = new KeCheng();
			List<Map<String, Object>> shiJianMap = kechengService.getShangKeShiJianByID(id);
			if (shiJianMap != null && shiJianMap.size() > 0) {
				for (Map<String, Object> map : shiJianMap) {
					if (zhou >= Integer.parseInt(map.get("kaiShiZhou").toString())) {
						if (zhouci.equals(map.get("zhouCi").toString())
								&& jiaoshiid.equals(map.get("jiaoShiID").toString())) {
							JCSJ jcsj1 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(map.get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(map.get("jieShuJieCi").toString()));
							if (jcsj1.getJieci().toString().equals(kaishijieci)
									&& jcsj2.getJieci().toString().equals(jieshujieci)) {
								kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
								kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
								kecheng.setKaishishijian(jcsj1.getKaishishijian());
								kecheng.setJieshushijian(jcsj2.getJieshushijian());
								JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(jiaoshiid));
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
								kecheng.setJiaoshiming(js.getJiaoshiming());
								kecheng.setXiaoquming(
										xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
								if (map.get("shiJianLeiXing").toString().equals("4")
										|| map.get("shiJianLeiXing").toString().equals("6")) {
									kecheng.setKaishizhou(zhou);
								}
								if (map.get("shiJianLeiXing").toString().equals("1")
										|| map.get("shiJianLeiXing").toString().equals("2")
										|| map.get("shiJianLeiXing").toString().equals("3")) {
									kecheng.setKaishizhou(Integer.parseInt(map.get("kaiShiZhou").toString()));
									kecheng.setJieshuzhou(Integer.parseInt(map.get("jieShuZhou").toString()));
								}
							}
						}
					}
				}
			}
			json.put("kecheng", kecheng);
		} else {
			response.sendRedirect("login");
			return null;
		}
		return json;
	}

	// 查询日程中的活动
	@RequestMapping("richeng_huodong_stu")
	public ModelAndView riChengHuoDong(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isXueSheng(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			XueSheng xueSheng = xueshengService.selectByPrimaryKey(user.getXueshengid());
			if (xueSheng != null) {
				String id = request.getParameter("id");
				String zhuangtai = request.getParameter("zhuangtai");
				String qufen = request.getParameter("qufen");
				List<HuoDong> huodongs = new ArrayList<>();
				HuoDong huodong = new HuoDong();
				if (id == null || id == "") {
					mv.addObject("huodongs", huodongs);
				} else {
					huodong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
					if (huodong != null) {
						if ("0".equals(zhuangtai))
							huodong.setZhuangtai(0);
						if ("1".equals(zhuangtai))
							huodong.setZhuangtai(1);
						if ("2".equals(zhuangtai))
							huodong.setZhuangtai(2);
						huodongs.add(huodong);
						mv.addObject("huodongs", huodongs);
						mv.addObject("qufen", qufen);
					}
					mv.addObject("xueshengid", xueSheng.getXueshengid().toString());
				}
			} else {
				response.sendRedirect("login");
				return null;
			}
		}
		mv.setViewName("stu/myhuodong");
		return mv;
	}

	@RequestMapping(value = "chakancanyuren_kecheng") // 查看参与人——课程
	public ModelAndView chakancanyuren_kecheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (keCheng == null) {
				response.sendRedirect("logout");
				return null;
			}
			List<Map<String, String>> maps = new ArrayList<>();
			if (keCheng.getBanjiids() != null && !keCheng.getBanjiids().equals("")) {
				String banjiids = keCheng.getBanjiids();
				String banjiid[] = banjiids.split(",");
				String mianxiuids = keCheng.getMianxiuids();
				for (String string : banjiid) {
					List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs) {
						int i = 0;
						Map<String, String> map = new HashMap<>();
						if (mianxiuids != null && !mianxiuids.equals("")) {
							String mianxiuid[] = mianxiuids.split(",");
							for (String string2 : mianxiuid)
								if (xueSheng.getXueshengid().toString().equals(string2)) {
									i = 1;
									break;
								}
						}
						if (i == 1)
							map.put("mianxiu", "1");
						else
							map.put("mianxiu", "0");
						String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
								.getBanjimingcheng();
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("banji", banjimingcheng);
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						maps.add(map);
					}
				}
			}
			if (keCheng.getXuanxiuids() != null && !keCheng.getXuanxiuids().equals("")) {
				String xuanxiuids = keCheng.getXuanxiuids();
				String xuanxiuid[] = xuanxiuids.split(",");
				for (String string : xuanxiuid) {
					Map<String, String> map = new HashMap<>();
					XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(string));
					String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
					map.put("xueshengid", xueSheng.getXueshengid().toString());
					map.put("banji", banjimingcheng);
					map.put("xuehao", xueSheng.getXuehao());
					map.put("xingming", xueSheng.getXingming());
					maps.add(map);
				}
			}
			keCheng.setMaps(maps);
			mv.addObject("kecheng", keCheng);
			mv.setViewName("stu/chakancanyuren_kecheng");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "chakancanyuren_huodong") // 查看参与人——活动
	public ModelAndView chakancanyuren_huodong(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String huodongid = request.getParameter("id");
			List<Map<String, String>> maps = new ArrayList<>();
			List<Map<String, String>> maps2 = new ArrayList<>();
			List<BanJi> banJis = new ArrayList<>();
			String banjiid = request.getParameter("banjiid");
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int leixing = huoDong.getLeixing();
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("huodongid", huodongid);
			List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
			List<Map<String, Object>> juJueRens = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
			if (leixing == 1) {
				List<String> banJIs = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(huodongid));
				if (banJIs.size() > 0 && banJIs != null) {
					for (String string : banJIs) {
						banJis.add(banjiService.selectByPrimaryKey(Integer.parseInt(string)));
					}
					if (banjiid == null || "".equals(banjiid)) {
						banjiid = banJIs.get(0);
					}
					if (!banJIs.contains(banjiid)) {
						response.sendRedirect("logout");
						return null;
					}
					List<XueSheng> xueShengList = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
					for (XueSheng xueSheng : xueShengList) {
						int c = 0;
						int j = 0;
						if (canYuRens != null && canYuRens.size() > 0) {
							for (Map<String, Object> map : canYuRens) {
								if (xueSheng.getXueshengid().toString().equals(map.get("canYuRenID").toString())
										&& map.get("leiXing").toString().equals("0")) {
									c = 1;
									break;
								}
							}
						}
						if (juJueRens != null && juJueRens.size() > 0) {
							for (Map<String, Object> map : juJueRens) {
								if (xueSheng.getXueshengid().toString().equals(map.get("juJueRenID").toString())
										&& map.get("leiXing").toString().equals("0")) {
									j = 1;
									break;
								}
							}
						}
						if (c == 0 && j == 1) {
							paramMap.put("renid", xueSheng.getXueshengid().toString());
							paramMap.put("renleixing", "0");
							List<Map<String, Object>> mapS = huodongService
									.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
							Map<String, String> map = new HashMap<>();
							String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
									.getBanjimingcheng();
							map.put("banjimingcheng", banjimingcheng);
							map.put("xueshengid", xueSheng.getXueshengid().toString());
							map.put("xuehao", xueSheng.getXuehao());
							map.put("xingming", xueSheng.getXingming());
							map.put("canyuzhuangtai", "2");
							if (mapS.get(0).containsKey("liYou") && mapS.get(0).get("liYou").toString() != null
									&& !"".equals(mapS.get(0).get("liYou").toString())) {
								map.put("liyou", mapS.get(0).get("liYou").toString());
							}
							maps.add(map);
						} else if (c == 1 && j == 0) {
							Map<String, String> map = new HashMap<>();
							String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
									.getBanjimingcheng();
							map.put("banjimingcheng", banjimingcheng);
							map.put("xueshengid", xueSheng.getXueshengid().toString());
							map.put("xuehao", xueSheng.getXuehao());
							map.put("xingming", xueSheng.getXingming());
							map.put("canyuzhuangtai", "1");
							maps.add(map);
						} else {
							Map<String, String> map = new HashMap<>();
							String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
									.getBanjimingcheng();
							map.put("banjimingcheng", banjimingcheng);
							map.put("xueshengid", xueSheng.getXueshengid().toString());
							map.put("xuehao", xueSheng.getXuehao());
							map.put("xingming", xueSheng.getXingming());
							map.put("canyuzhuangtai", "0");
							maps.add(map);
						}
					}
				}
			}
			if (leixing == 2) {
				List<Map<String, Object>> yaoQingRens = huodongService
						.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(paramMap);
				if (yaoQingRens.size() > 0 && yaoQingRens != null) {
					for (Map<String, Object> mapS : yaoQingRens) {
						int c = 0;
						int j = 0;
						if (canYuRens != null && canYuRens.size() > 0) {
							for (Map<String, Object> map : canYuRens) {
								if (mapS.get("yaoQingRenID").toString().equals(map.get("canYuRenID").toString())) {
									c = 1;
									break;
								}
							}
						}
						if (juJueRens != null && juJueRens.size() > 0) {
							for (Map<String, Object> map : juJueRens) {
								if (mapS.get("yaoQingRenID").toString().equals(map.get("juJueRenID").toString())) {
									j = 1;
									break;
								}
							}
						}
						if (c == 0 && j == 1) {
							paramMap.put("renid", mapS.get("yaoQingRenID").toString());
							paramMap.put("renleixing", mapS.get("leiXing").toString());
							List<Map<String, Object>> jmapS = huodongService
									.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
							Map<String, String> map = new HashMap<>();
							if (mapS.get("leiXing").toString().equals("0")) {
								XueSheng xueSheng = xueshengService
										.selectByPrimaryKey(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
										.getBanjimingcheng();
								map.put("banjimingcheng", banjimingcheng);
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "2");
								if (jmapS.get(0).containsKey("liYou") && jmapS.get(0).get("liYou").toString() != null
										&& !"".equals(jmapS.get(0).get("liYou").toString())) {
									map.put("liyou", jmapS.get(0).get("liYou").toString());
								}
								maps.add(map);
							} else {
								YongHu fudaoyuan = yonghuService
										.selectYongHuByID(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
								map.put("fudaoyuanid", mapS.get("yaoQingRenID").toString());
								map.put("xingming", fudaoyuan.getYonghuxingming());
								map.put("canyuzhuangtai", "2");
								if (jmapS.get(0).containsKey("liYou") && jmapS.get(0).get("liYou").toString() != null
										&& !"".equals(jmapS.get(0).get("liYou").toString())) {
									map.put("liyou", jmapS.get(0).get("liYou").toString());
								}
								maps2.add(map);
							}
						}
						if (c == 1 && j == 0) {
							Map<String, String> map = new HashMap<>();
							if (mapS.get("leiXing").toString().equals("0")) {
								XueSheng xueSheng = xueshengService
										.selectByPrimaryKey(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
										.getBanjimingcheng();
								map.put("banjimingcheng", banjimingcheng);
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "1");
								maps.add(map);
							} else {
								YongHu fudaoyuan = yonghuService
										.selectYongHuByID(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
								map.put("fudaoyuanid", mapS.get("yaoQingRenID").toString());
								map.put("xingming", fudaoyuan.getYonghuxingming());
								map.put("canyuzhuangtai", "1");
								maps2.add(map);
							}
						}
						if (c == 0 && j == 0) {
							Map<String, String> map = new HashMap<>();
							if (mapS.get("leiXing").toString().equals("0")) {
								XueSheng xueSheng = xueshengService
										.selectByPrimaryKey(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
										.getBanjimingcheng();
								map.put("banjimingcheng", banjimingcheng);
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "0");
								maps.add(map);
							} else {
								YongHu fudaoyuan = yonghuService
										.selectYongHuByID(Integer.parseInt(mapS.get("yaoQingRenID").toString()));
								map.put("fudaoyuanid", mapS.get("yaoQingRenID").toString());
								map.put("xingming", fudaoyuan.getYonghuxingming());
								map.put("canyuzhuangtai", "0");
								maps2.add(map);
							}
						}
					}
				}
			}

			String tianjiarenid = huoDong.getTianjiarenid().toString();
			if (huoDong.getTianjiarenleixing().toString().equals("0")) {// 学生
				Map<String, String> map = new HashMap<>();
				if (leixing == 2) {
					if (canYuRens != null && canYuRens.size() > 0) {
						for (Map<String, Object> map2 : canYuRens) {
							if (tianjiarenid.equals(map2.get("canYuRenID").toString())) {
								XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(tianjiarenid));
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
										.getBanjimingcheng();
								map.put("banjimingcheng", banjimingcheng);
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "1");
								maps.add(map);
								break;
							}
						}
					}
					if (juJueRens != null && juJueRens.size() > 0) {
						for (Map<String, Object> map2 : juJueRens) {
							if (tianjiarenid.equals(map2.get("juJueRenID").toString())) {
								XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(tianjiarenid));
								paramMap.put("renid", xueSheng.getXueshengid().toString());
								paramMap.put("renleixing", "0");
								List<Map<String, Object>> mapS = huodongService
										.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid())
										.getBanjimingcheng();
								map.put("banjimingcheng", banjimingcheng);
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "2");
								if (mapS.get(0).containsKey("liYou") && mapS.get(0).get("liYou").toString() != null
										&& !"".equals(mapS.get(0).get("liYou").toString())) {
									map.put("liyou", mapS.get(0).get("liYou").toString());
								}
								maps.add(map);
								break;
							}
						}
					}
				}
			} else {
				paramMap.put("renid", tianjiarenid.toString());
				paramMap.put("renleixing", "1");
				List<Map<String, Object>> jmapS = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
				Map<String, String> map = new HashMap<>();
				if (jmapS != null && jmapS.size() > 0) {
					YongHu fudaoyuan = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
					map.put("fudaoyuanid", tianjiarenid.toString());
					map.put("xingming", fudaoyuan.getYonghuxingming());
					map.put("canyuzhuangtai", "2");
					if (jmapS.get(0).containsKey("liYou") && jmapS.get(0).get("liYou").toString() != null
							&& !"".equals(jmapS.get(0).get("liYou").toString())) {
						map.put("liyou", jmapS.get(0).get("liYou").toString());
					}
					maps2.add(map);
				} else {
					YongHu fudaoyuan = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
					map.put("fudaoyuanid", tianjiarenid.toString());
					map.put("xingming", fudaoyuan.getYonghuxingming());
					map.put("canyuzhuangtai", "1");
					maps2.add(map);
				}
			}
			mv.addObject("banjis", banJis);
			mv.addObject("bjid", banjiid);
			mv.addObject("student", maps);
			mv.addObject("fudaoyuan", maps2);
			mv.addObject("huodong", huoDong);
			mv.addObject("userid", user.getXueshengid());
			mv.setViewName("stu/chakancanyuren_huodong");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "delhuodong") // 删除活动
	public void delhuodong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			map.put("huodongid", id);
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			int sign = 0;
			if (huoDong == null) {
				response.sendRedirect("logout");
				return;
			}
			try {
				if (huoDong.getLeixing() == 1)
					huodongService.delete_huodongbanji(map);
				if (huoDong.getLeixing() == 2)
					huodongService.delete_huodongren(map);
				sign = 1;
			} catch (Exception e) {
				e.printStackTrace();
				sign = 0;
			}
			if (sign == 1) {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>alert('删除成功!');</script>");
				out.print("<script>location='wodericheng';</script>");
			} else {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>alert('删除失败!');</script>");
				out.print("<script>location='wodericheng';</script>");
				response.sendRedirect("logout");
			}
		} else
			response.sendRedirect("login");
	}

	@RequestMapping(value = "tijiaoliyou") // 提交理由
	@ResponseBody
	public JSONObject tijiaoliyou(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			String my = request.getParameter("my");
			if (!Util.isNumeric(id))
				response.sendRedirect("logout");
			// System.out.println(id);
			XueSheng user = (XueSheng) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
			}
			String liyou = request.getParameter("liyou");
			if (liyou == "") {
				liyou = "";
			}
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getXueshengid().toString());
			map.put("renleixing", "0");
			List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (canYuRens != null && canYuRens.size() > 0) {
				try {
					huodongService.delete_huodongcanyuren(map);
					map.put("liyou", liyou);
					huodongService.insert_huodongjujueren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			} else {
				try {
					map.put("liyou", liyou);
					huodongService.insert_huodongjujueren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			}
			if (sign == 1) {
				json.put("success", true);
			} else {
				json.put("success", false);
			}
			json.put("qufen", qufen);
			json.put("my", my);
		} else {
			response.sendRedirect("login");
		}
		return json;
	}

	@RequestMapping(value = "cleanhuodong") // 彻底清除活动
	public void cleanhuodong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return;
			}

			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return;
			}
			int sign = 0;
			try {
				map.put("huodongid", id);
				if (huoDong.getLeixing() == 1) {
					huodongService.delete_huodongbanji(map);
				}
				if (huoDong.getLeixing() == 2) {
					huodongService.delete_huodongren(map);
				}
				huodongService.delete_huodongcanyuren(map);
				huodongService.delete_huodongjujueren(map);
				huodongService.deleteByPrimaryKey(Integer.parseInt(id));
				sign = 1;
			} catch (Exception e) {
				e.printStackTrace();
				sign = 0;
			}
			if (sign == 1) {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>alert('删除成功!');</script>");
				out.print("<script>location='newhuodong';</script>");
			} else {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>alert('删除失败!');</script>");
				out.print("<script>location='newhuodong';</script>");
			}
		} else
			response.sendRedirect("login");
	}

	@RequestMapping(value = "myhuodong") // 我的活动
	public ModelAndView myhuodong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			List<HuoDong> huoDongs = new ArrayList<>();
			Map<String, String> map = new HashMap<>();
			map.put("renid", user.getXueshengid().toString());
			map.put("renleixing", "0");
			map.put("banjiid", user.getBanjiid().toString());
			huoDongs = huodongService.getAllByRenIDAndRenLeiXing(map);
			for (HuoDong huoDong : huoDongs) {
				map.put("huodongid", huoDong.getHuodongid().toString());
				List<Map<String, Object>> canyuren = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				List<Map<String, Object>> jujueren = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				if (!(huoDong.getTianjiarenid().toString().equals(user.getXueshengid().toString()))) {
					if (jujueren.size() > 0 && jujueren != null) {
						for (Map<String, Object> mapP : jujueren)
							if (mapP.get("juJueRenID").toString().equals(user.getXueshengid().toString())
									&& mapP.get("leiXing").toString().equals("0")) {
								huoDong.setZhuangtai(2);
								continue;
							}
					} else if (canyuren.size() > 0 && canyuren != null) {
						for (Map<String, Object> mapP : canyuren)
							if (mapP.get("canYuRenID").toString().equals(user.getXueshengid().toString())
									&& mapP.get("leiXing").toString().equals("0")) {
								huoDong.setZhuangtai(1);
								continue;
							}
					} else {
						huoDong.setZhuangtai(0);
						continue;
					}
				} else if (huoDong.getTianjiarenid().toString().equals(user.getXueshengid().toString())
						&& "0".equals(huoDong.getTianjiarenleixing().toString())) {
					if (jujueren != null && jujueren.size() > 0) {
						huoDong.setZhuangtai(2);
						continue;
					} else {
						huoDong.setZhuangtai(1);
						continue;
					}
				} else {
					response.sendRedirect("login");
					return null;
				}
			}
			int count = huoDongs.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0)
				pages = count / pageSize;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<HuoDong> huoDongs2 = new ArrayList<>();
				if (count < 10) {

					for (int i = 0; i < count; i++)
						huoDongs2.add(huoDongs.get(i));
					mv.addObject("huodongs", huoDongs2);
				} else {
					for (int i = 0; i < 10; i++)
						huoDongs2.add(huoDongs.get(i));
					mv.addObject("huodongs", huoDongs2);
				}
			} else if (Util.isNumeric(request.getParameter("page"))) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page > 0 && page < pages) {
					List<HuoDong> huoDongs2 = new ArrayList<>();
					if (count < 10) {
						for (int i = (page - 1) * 10; i < count; i++)
							huoDongs2.add(huoDongs.get(i));
						mv.addObject("huodongs", huoDongs2);
					} else {
						for (int i = (page - 1) * 10; i < (page * 10); i++)
							huoDongs2.add(huoDongs.get(i));
						mv.addObject("huodongs", huoDongs2);
					}
				} else if (page == pages) {
					List<HuoDong> huoDongs2 = new ArrayList<>();
					for (int i = (page - 1) * 10; i < count; i++)
						huoDongs2.add(huoDongs.get(i));
					mv.addObject("huodongs", huoDongs2);
				} else {
					response.sendRedirect("logout");
					return null;
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
			mv.addObject("xueshengid", user.getXueshengid().toString());
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("stu/myhuodong");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "addhuodong") // 新增活动
	public ModelAndView addhuodong(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(user.getBanjiid());
			String xueshengid1 = "%," + user.getXueshengid().toString() + ",%";
			String xueshengid2 = user.getXueshengid().toString() + ",%";
			Date date = new Date();
			SimpleDateFormat dFormat = new SimpleDateFormat("yyyy");
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			List<SheTuanXinXi> sheTuanXinXis = shetuanSerivce.selectSheTuanXinXiByXueShengIDAndNianDuAndXueXiaoID(
					xueshengid1, xueshengid2, dFormat.format(date), Integer.parseInt(xueXiaoXueHao[0]));
			List<Map<String, String>> maps = new ArrayList<>();
			for (SheTuanXinXi sheTuanXinXi : sheTuanXinXis) {
				String shetuanmingcheng = shetuanSerivce.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid())
						.getMingcheng();
				String sheyuans = sheTuanXinXi.getSheyuanids();
				String sheyuanids[] = sheyuans.split(",");
				for (String string : sheyuanids) {
					Map<String, String> map = new HashMap<>();
					XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(string));
					map.put("shetuanmingcheng", shetuanmingcheng);
					map.put("xueshengid", xueSheng.getXueshengid().toString());
					map.put("xuehao", xueSheng.getXuehao());
					map.put("xingming", xueSheng.getXingming());
					if (!maps.isEmpty()) {
						int i = 0;
						for (Map<String, String> map2 : maps)
							if (map2.get("xueshengid").equals(map.get("xueshengid"))) {
								String newshetuanmingcheng = map2.get("shetuanmingcheng") + "/"
										+ map.get("shetuanmingcheng");
								map2.put("shetuanmingcheng", newshetuanmingcheng);
								i = 1;
								continue;
							}
						if (i == 0)
							maps.add(map);
					} else
						maps.add(map);
				}
			}
			List<XueShengZuZhiXinXi> xueShengZuZhiXinXis = shetuanSerivce
					.selectXueShengZuZhiXinXiByXueShengIDAndNianDu(xueshengid1, xueshengid2, dFormat.format(date));
			for (XueShengZuZhiXinXi xueShengZuZhiXinXi : xueShengZuZhiXinXis) {
				String zuzhimingcheng = shetuanSerivce
						.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid())
						.getMingcheng();
				String sheyuans = xueShengZuZhiXinXi.getRenyuanids();
				String sheyuanids[] = sheyuans.split(",");
				for (String string : sheyuanids) {
					Map<String, String> map = new HashMap<>();
					XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(string));
					map.put("shetuanmingcheng", zuzhimingcheng);
					map.put("xueshengid", xueSheng.getXueshengid().toString());
					map.put("xuehao", xueSheng.getXuehao());
					map.put("xingming", xueSheng.getXingming());
					if (!maps.isEmpty()) {
						int i = 0;
						for (Map<String, String> map2 : maps)
							if (map2.get("xueshengid").equals(map.get("xueshengid"))) {
								String newshetuanmingcheng = map2.get("shetuanmingcheng") + "/"
										+ map.get("shetuanmingcheng");
								map2.put("shetuanmingcheng", newshetuanmingcheng);
								i = 1;
								continue;
							}
						if (i == 0)
							maps.add(map);
					} else
						maps.add(map);
				}
			}
			List<Map<String, String>> maps2 = new ArrayList<>();
			for (Map<String, String> map3 : maps)
				if (!map3.get("xueshengid").equals(user.getXueshengid().toString()))
					maps2.add(map3);
			mv.addObject("qufen", request.getParameter("qufen"));
			mv.addObject("sheyuan", maps2);
			mv.addObject("xuesheng", xueShengs);
			mv.setViewName("stu/addhuodong");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "xiugaihuodong") // 修改活动
	public ModelAndView xiugaihuodong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(user.getBanjiid());
			String huodongid = request.getParameter("id");
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			List<Map<String, Object>> mapsS = new ArrayList<>();
			Map<String, String> mapP = new HashMap<>();
			mapP.put("huodongid", huodongid);
			mapsS = huodongService.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(mapP);
			int sign = 0;
			if (huoDong.getTianjiarenid().toString().equals(user.getXueshengid().toString()))
				sign = 1;
			else
				sign = 0;
			if (sign == 1) {
				String newcanyuren = "";
				for (Map<String, Object> string : mapsS) {
					XueSheng xueSheng = xueshengService
							.getUserById(Integer.parseInt(string.get("yaoQingRenID").toString()));
					String xuehao = xueSheng.getXuehao();
					String xingming = xueSheng.getXingming();
					mapP.put("renid", string.get("yaoQingRenID").toString());
					mapP.put("renleixing", "0");
					List<Map<String, Object>> jujuerens = huodongService
							.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(mapP);
					List<Map<String, Object>> canyurens = huodongService
							.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(mapP);
					if (jujuerens != null && jujuerens.size() > 0)
						newcanyuren += string.get("yaoQingRenID") + "," + xuehao + "," + xingming + "," + "2" + ";";
					else if (canyurens != null && canyurens.size() > 0)
						newcanyuren += string.get("yaoQingRenID") + "," + xuehao + "," + xingming + "," + "1" + ";";
					else
						newcanyuren += string.get("yaoQingRenID") + "," + xuehao + "," + xingming + "," + "0" + ";";
				}
				String xueshengid1 = "%," + user.getXueshengid().toString() + ",%";
				String xueshengid2 = user.getXueshengid().toString() + ",%";
				String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
				Date date = new Date();
				SimpleDateFormat dFormat = new SimpleDateFormat("yyyy");
				List<SheTuanXinXi> sheTuanXinXis = shetuanSerivce.selectSheTuanXinXiByXueShengIDAndNianDuAndXueXiaoID(
						xueshengid1, xueshengid2, dFormat.format(date), Integer.parseInt(xueXiaoXueHao[0]));
				List<Map<String, String>> maps = new ArrayList<>();
				for (SheTuanXinXi sheTuanXinXi : sheTuanXinXis) {
					String shetuanmingcheng = shetuanSerivce
							.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid()).getMingcheng();
					String sheyuans = sheTuanXinXi.getSheyuanids();
					String sheyuanids[] = sheyuans.split(",");
					for (String string : sheyuanids) {
						Map<String, String> map = new HashMap<>();
						XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(string));
						map.put("shetuanmingcheng", shetuanmingcheng);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						if (!maps.isEmpty()) {
							int i = 0;
							for (Map<String, String> map2 : maps)
								if (map2.get("xueshengid").equals(map.get("xueshengid"))) {
									String newshetuanmingcheng = map2.get("shetuanmingcheng") + "/"
											+ map.get("shetuanmingcheng");
									map2.put("shetuanmingcheng", newshetuanmingcheng);
									i = 1;
									continue;
								}
							if (i == 0)
								maps.add(map);
						} else
							maps.add(map);
					}
				}
				List<XueShengZuZhiXinXi> xueShengZuZhiXinXis = shetuanSerivce
						.selectXueShengZuZhiXinXiByXueShengIDAndNianDu(xueshengid1, xueshengid2, dFormat.format(date));
				for (XueShengZuZhiXinXi xueShengZuZhiXinXi : xueShengZuZhiXinXis) {
					String zuzhimingcheng = shetuanSerivce
							.selectXueShengZuZhiJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid())
							.getMingcheng();
					String sheyuans = xueShengZuZhiXinXi.getRenyuanids();
					String sheyuanids[] = sheyuans.split(",");
					for (String string : sheyuanids) {
						Map<String, String> map = new HashMap<>();
						XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(string));
						map.put("shetuanmingcheng", zuzhimingcheng);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						if (!maps.isEmpty()) {
							int i = 0;
							for (Map<String, String> map2 : maps)
								if (map2.get("xueshengid").equals(map.get("xueshengid"))) {
									String newshetuanmingcheng = map2.get("shetuanmingcheng") + "/"
											+ map.get("shetuanmingcheng");
									map2.put("shetuanmingcheng", newshetuanmingcheng);
									i = 1;
									continue;
								}
							if (i == 0)
								maps.add(map);
						} else
							maps.add(map);
					}
				}
				List<Map<String, String>> maps2 = new ArrayList<>();
				for (Map<String, String> map3 : maps) {
					if (!map3.get("xueshengid").toString().equals(user.getXueshengid().toString()))
						maps2.add(map3);
				}
				mv.addObject("canyuren", newcanyuren);
				mv.addObject("huodong", huoDong);
				mv.addObject("xuesheng", xueShengs);
				mv.addObject("sheyuan", maps2);
				mv.addObject("qufen", request.getParameter("qufen"));
				mv.setViewName("stu/xiugaihuodong");
				return mv;
			} else
				response.sendRedirect("logout");
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "savehuodong") // 保存新增活动
	public void savehuodong(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			// 获取学校id
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_"));

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String shuoming = request.getParameter("beizhu");
			String canyuren = request.getParameter("canyuren");
			String riqi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			String qufen = request.getParameter("qufen");

			map.put("huodongmingcheng", mingcheng);
			map.put("didian", didian);
			map.put("shuoming", shuoming);
			map.put("tianjiarenid", user.getXueshengid().toString());
			map.put("riqi", riqi);
			map.put("kaishishijian", kaishishijian);
			map.put("jieshushijian", jieshushijian);
			map.put("tianjiarenleixing", "0");
			map.put("leixing", "2");
			int i = huodongService.insert(map);
			if (canyuren.equals("")) {
				canyuren = "0," + user.getXueshengid().toString() + ",1;";
			} else {
				int k = 0;
				String canyurens[] = canyuren.split(";");
				try {
					for (int j = 0; j < canyurens.length; j++) {
						if (canyurens[j].split(",") != null && !"".equals(canyurens[j].split(","))) {
							Map<String, String> mapP = new HashMap<>();
							mapP.put("huodongid", map.get("huodongid").toString());
							mapP.put("renid", canyurens[j].split(",")[0]);
							mapP.put("renleixing", "0");
							if (!(canyurens[j].split(",")[0].toString().equals(user.getXueshengid().toString()))) {
								huodongService.insert_huodongren(mapP);
							}
						}
					}
					k = 1;
				} catch (Exception e) {

				}
				if (k != 0) {
					// 发送激光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("新增活动");
					xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "邀请你参加活动#" + mingcheng + "#");
					xiaoXiFaSong.setShuJuId(Integer.parseInt(map.get("huodongid")));
					xiaoXiFaSong.setShuJuLeiXing(2);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xueXiaoID);
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date = new Date();
					tiXing.setNeirong(user.getXingming() + "邀请你参加活动#" + mingcheng + "#");
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(Integer.parseInt(map.get("huodongid")));
					tiXing.setType(2);
					for (int j = 0; j < canyurens.length; j++) {
						tiXing.setJieshourenid(Integer.parseInt(canyurens[j].split(",")[0]));
						tixingService.insert(tiXing);
						xiaoXiFaSong.setFaSongMuBiao(canyurens[j].split(",")[0]);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
					if (qufen.equals("1")) {
						out.print("<script>alert('新增成功！');</script>");
						out.print("<script>location='wodericheng';</script>");
					} else {
						out.print("<script>alert('新增成功！');</script>");
						out.print("<script>location='myhuodong';</script>");
					}
				} else {
					if (qufen.equals("1")) {
						out.print("<script>alert('新增失败！');</script>");
						out.print("<script>location='wodericheng';</script>");
					} else {
						out.print("<script>alert('新增失败！');</script>");
						out.print("<script>location='myhuodong';</script>");
					}
				}
			}
		} else
			response.sendRedirect("login");
	}

	@RequestMapping(value = "saveupdatehuodong") // 保存修改活动
	public void saveupdatehuodong(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HuoDong huoDong = new HuoDong();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			// 获取学校id
			String xuexiao_xuehao = user.getXuexiaoXuehao();
			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_"));
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String huodongid = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			Map<String, String> map = new HashMap<>();
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return;
			}
			HuoDong huodong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huodong == null) {
				response.sendRedirect("logout");
				return;
			}
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String shuoming = request.getParameter("beizhu");
			String canyuren = request.getParameter("canyuren");
			String riqi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			huoDong.setHuodongid(Integer.parseInt(huodongid));
			huoDong.setHuodongmingcheng(mingcheng);
			huoDong.setDidian(didian);
			huoDong.setRiqi(df.parse(riqi));
			// huoDong.setCanyuren(canyuren);
			huoDong.setKaishishijian(kaishishijian);
			huoDong.setJieshushijian(jieshushijian);
			huoDong.setTianjiarenid(user.getXueshengid());
			huoDong.setShuoming(shuoming);
			huoDong.setLeixing(2);
			huoDong.setTianjiarenleixing(0);
			int i = huodongService.updateByPrimaryKey(huoDong);
			if (i != 0) {
				// 将之前与之先关的班级或人删除，再加进来
				map.put("huodongid", huodongid);
				huodongService.delete_huodongren(map);
				String canyurens[] = canyuren.split(";");
				int k = 0;
				try {
					for (int j = 0; j < canyurens.length; j++) {
						if (canyurens[j].split(",") != null && !"".equals(canyurens[j].split(","))) {
							Map<String, String> mapP = new HashMap<>();
							mapP.put("huodongid", map.get("huodongid").toString());
							mapP.put("renid", canyurens[j].split(",")[0]);
							mapP.put("renleixing", "0");
							if (!(canyurens[j].split(",")[0].toString().equals(user.getXueshengid().toString()))) {
								k = huodongService.insert_huodongren(mapP);
							}
						}
					}
				} catch (Exception e) {

				}
				if (k != 0) {
					// 发送极光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
					xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "修改了活动#" + huodong.getHuodongmingcheng() + "#");
					xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
					xiaoXiFaSong.setShuJuLeiXing(2);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xueXiaoID);
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date = new Date();
					tiXing.setNeirong(user.getXingming() + "修改了活动#" + huodong.getHuodongmingcheng() + "#");
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(Integer.parseInt(huodongid));
					tiXing.setType(2);
					for (int j = 0; j < canyurens.length; j++) {
						String cyr[] = canyurens[j].split(",");
						tiXing.setJieshourenid(Integer.parseInt(cyr[0]));
						tixingService.insert(tiXing);
						xiaoXiFaSong.setFaSongMuBiao(cyr[0]);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
					if (qufen.equals("1")) {
						out.print("<script>alert('修改成功！');</script>");
						out.print("<script>location='wodericheng';</script>");
					} else {
						out.print("<script>alert('修改成功！');</script>");
						out.print("<script>location='myhuodong';</script>");
					}
				} else {
					if (qufen.equals("1")) {
						out.print("<script>alert('修改失败！');</script>");
						out.print("<script>location='wodericheng';</script>");
					} else {
						out.print("<script>alert('修改失败！');</script>");
						out.print("<script>location='myhuodong';</script>");
					}
				}
			} else
				response.sendRedirect("logout");

		} else
			response.sendRedirect("login");
	}

	@RequestMapping(value = "newhuodong") // 待确认活动
	public ModelAndView newhuodong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			XueSheng user = (XueSheng) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			map.put("renid", user.getXueshengid().toString());
			map.put("renleixing", "0");
			map.put("banjiid", user.getBanjiid().toString());
			List<HuoDong> huodongs = huodongService.getAllByRenIDAndRenLeiXing(map);
			List<HuoDong> huoDongs = new ArrayList<>();
			for (HuoDong huoDong : huodongs) {
				map.put("huodongid", huoDong.getHuodongid().toString());
				List<Map<String, Object>> juJueRen = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				List<Map<String, Object>> canYuRen = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				String tianjiarenid = huoDong.getTianjiarenid().toString();
				YongHu tianjiaren = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
				String faqiren = "";
				if (tianjiaren == null) {
					faqiren = xueshengService.getUserById(Integer.parseInt(tianjiarenid)).getXingming();
				} else {
					faqiren = tianjiaren.getYonghuxingming();
				}
				if (tianjiarenid.equals(user.getXueshengid().toString())) {
					if (juJueRen != null && juJueRen.size() > 0) {
						huoDong.setFaqiren(faqiren);
						huoDong.setZhuangtai(2);
						huoDongs.add(huoDong);
					} else {
						// huoDong.setFaqiren(faqiren);
						// huoDong.setZhuangtai(1);
						// huoDongs.add(huoDong);

					}
				} else {
					if (juJueRen == null || juJueRen.size() <= 0) {
						if (canYuRen == null || canYuRen.size() <= 0) {
							huoDong.setFaqiren(faqiren);
							huoDong.setZhuangtai(0);
							huoDongs.add(huoDong);
						} else {
							// huoDong.setFaqiren(faqiren);
							// huoDong.setZhuangtai(1);
							// huoDongs.add(huoDong);
						}
					} else {
						if (canYuRen == null || canYuRen.size() <= 0) {
							huoDong.setFaqiren(faqiren);
							huoDong.setZhuangtai(2);
							huoDongs.add(huoDong);
						}
					}
				}
			}
			mv.addObject("huodong", huoDongs);
			mv.setViewName("stu/newhuodong");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "confirmhuodong") // 确认参加活动
	public ModelAndView confirmhuodong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		if (Util.checkSession(request)) {
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			String my = request.getParameter("my");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			XueSheng user = (XueSheng) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getXueshengid().toString());
			map.put("renleixing", "0");
			List<Map<String, Object>> jujueren = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (jujueren != null && jujueren.size() > 0) {
				try {
					huodongService.delete_huodongjujueren(map);
					huodongService.insert_huodongcanyuren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			} else {
				try {
					huodongService.insert_huodongcanyuren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			}
			if (sign == 1) {
				if (qufen != null && !"".equals(qufen) && qufen.equals("1")) {
					out.print("<script>alert('参与成功！');</script>");
					out.print("<script>location='wodericheng';</script>");
				} else if (my != null && !"".equals(my) && my.equals("1")) {
					out.print("<script>alert('参与成功！');</script>");
					out.print("<script>location='myhuodong';</script>");
				} else {
					out.print("<script>alert('参与成功！');</script>");
					out.print("<script>location='newhuodong';</script>");
				}
			} else {
				if (qufen != null && !"".equals(qufen) && qufen.equals("1")) {
					out.print("<script>alert('参与失败！');</script>");
					out.print("<script>location='wodericheng';</script>");
				} else if (my != null && !"".equals(my) && my.equals("1")) {
					out.print("<script>alert('参与失败！');</script>");
					out.print("<script>location='myhuodong';</script>");
				} else {
					out.print("<script>alert('参与失败！');</script>");
					out.print("<script>location='newhuodong';</script>");
				}
			}
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "refusehuodong") // 拒绝参加活动
	public ModelAndView refusehuodong(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		if (Util.checkSession(request)) {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			XueSheng user = (XueSheng) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getXueshengid().toString());
			map.put("renleixing", "0");
			List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (canYuRens != null && canYuRens.size() > 0)
				try {
					huodongService.delete_huodongcanyuren(map);
					huodongService.insert_huodongjujueren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			else
				try {
					huodongService.insert_huodongjujueren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			if (sign == 1) {
				out.print("<script>alert('拒绝成功！');</script>");
				out.print("<script>location='wodericheng';</script>");

			} else {
				out.print("<script>alert('拒绝失败！');</script>");
				out.print("<script>location='wodericheng';</script>");
			}
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "mybeiwanglu") // 我的备忘录
	public ModelAndView mybeiwanglu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			List<BeiWL> beiWLs = new ArrayList<>();
			Map<String, String> map = new HashMap<>();
			if (id == null || "".equals(id)) {
				map.put("renid", user.getXueshengid().toString());
				map.put("renleixing", "0");
				map.put("banjiid", user.getBanjiid().toString());
				beiWLs = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(map);
			} else {
				beiWLs.add(beiwlService.selectByPrimaryKey(Integer.parseInt(id)));
			}
			for (BeiWL beiWL : beiWLs) {
				map.put("beiwlid", beiWL.getId().toString());
				List<Map<String, Object>> huizhi = beiwlService.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(map);
				if (huizhi != null && huizhi.size() > 0) {
					beiWL.setZhuangtai(huizhi.get(0).get("zhuangTai").toString());
				} else {
					beiWL.setZhuangtai("0");
				}
			}
			mv.addObject("qufen", qufen);
			mv.addObject("beiwl", beiWLs);
			mv.setViewName("stu/mybeiwanglu");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "delbeiwl") // 删除备忘录
	public void delbeiwl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String xueshengid = user.getXueshengid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return;
			}
			response.setContentType("text/html; charset=utf-8");
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if (beiWL != null && beiWL.getUserid().equals(xueshengid)) {
				int o = 0;
				try {
					Map<String, String> map = new HashMap<>();
					map.put("beiwlid", id);
					beiwlService.delete_beiwanglubanji(map);
					beiwlService.delete_beiwangluren(map);
					beiwlService.delete_beiwlhuizhi(map);
					beiwlService.deleteByPrimaryKey(Integer.parseInt(id));
					o = 1;
				} catch (Exception e) {

				}
				if (o != 0) {
					if (qufen.equals("1")) {
						out.print("<script>alert('删除成功！');</script>");
						out.print("<script>location='wodericheng';</script>");
					} else {
						out.print("<script>alert('删除成功！');</script>");
						out.print("<script>location='mybeiwanglu';</script>");
					}
				} else {
					if (qufen.equals("1")) {
						out.print("<script>alert('删除失败！');</script>");
						out.print("<script>location='wodericheng';</script>");
					} else {
						out.print("<script>alert('删除失败！');</script>");
						out.print("<script>location='mybeiwanglu';</script>");
					}
				}
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
	}

	@RequestMapping(value = "addbeiwang") // 新增备忘录
	public ModelAndView addbeiwang(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			mv.setViewName("stu/addbeiwang");
			return mv;
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "savebeiwang") // 保存新增备忘录
	public void savebeiwang(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String date = request.getParameter("riqi");
			String time = request.getParameter("shijian");
			String shijian = date + " " + time;
			/*
			 * System.out.println(neirong); System.out.println(didian);
			 * System.out.println(shijian);
			 */
			map.put("leixing", "0");
			map.put("neirong", neirong);
			// map.put("didian", didian);
			map.put("userid", user.getXueshengid().toString());
			map.put("shijian", shijian);
			map.put("huizhi", "0");
			int i = beiwlService.insert(map);
			if (i != 0) {
				out.print("<script>alert('新增成功！');</script>");
				out.print("<script>location='mybeiwanglu';</script>");
			} else {
				out.print("<script>alert('新增失败！');</script>");
				out.print("<script>location='mybeiwanglu';</script>");
			}
		} else
			response.sendRedirect("login");
	}

	@RequestMapping(value = "xiugaibeiwanglu") // 修改备忘录
	public ModelAndView xiugaibeiwanglu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String xueshengid = user.getXueshengid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if ((beiWL != null) && (beiWL.getUserid().equals(xueshengid))) {
				String datetime = beiWL.getShijian();
				String date = datetime.substring(0, datetime.indexOf(" "));
				String time = datetime.substring(datetime.indexOf(" ") + 1, datetime.length());
				mv.addObject("beiwanglu", beiWL);
				mv.addObject("date", date);
				mv.addObject("time", time);
				mv.addObject("qufen", qufen);
				mv.setViewName("stu/xiugaibeiwanglu");
				return mv;
			} else
				response.sendRedirect("logout");
		} else
			response.sendRedirect("login");
		return null;
	}

	@RequestMapping(value = "saveupdatebeiwanglu") // 保存修改备忘录
	public void saveupdatebeiwanglu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		BeiWL beiWL = new BeiWL();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			XueSheng user = (XueSheng) session.getAttribute("user");
			String xueshengid = user.getXueshengid().toString();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return;
			}
			BeiWL beiwanglu = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if (beiwanglu != null && beiwanglu.getUserid().equals(xueshengid)) {
				String neirong = request.getParameter("neirong");
				// String didian = request.getParameter("didian");
				String date = request.getParameter("riqi");
				String time = request.getParameter("shijian");
				String shijian = date + " " + time;

				beiWL.setId(Integer.parseInt(id));
				beiWL.setLeixing(0);
				beiWL.setNeirong(neirong);
				// beiWL.setDidian(didian);
				beiWL.setShijian(shijian);
				beiWL.setUserid(user.getXueshengid().toString());
				beiWL.setHuizhi(0);
				int i = beiwlService.updateByPrimaryKey(beiWL);
				if (i != 0) {
					out.print("<script>alert('修改成功！');</script>");
					if (qufen != null && !"".equals(qufen) && qufen.equals("1"))
						out.print("<script>location='wodericheng';</script>");
					else
						out.print("<script>location='mybeiwanglu';</script>");
				} else {
					out.print("<script>alert('修改失败！');</script>");
					if (qufen != null && !"".equals(qufen) && qufen.equals("1"))
						out.print("<script>location='wodericheng';</script>");
					else
						out.print("<script>location='mybeiwanglu';</script>");
				}
			} else
				response.sendRedirect("logout");
		} else
			response.sendRedirect("login");
	}

	@RequestMapping("xiugaihuizhineirong")
	@ResponseBody
	public JSONObject xiugaihuizhineirong(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		if (request.getSession().getAttribute("user") != null || request.getSession().getAttribute("user") != "") {
			XueSheng xueSheng = (XueSheng) request.getSession().getAttribute("user");
			if (xueSheng != null) {
				String id = request.getParameter("id");
				String huizhi = request.getParameter("huizhi");
				String qufen = request.getParameter("qufen");
				BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
				if (beiWL != null) {
					Map<String, String> map = new HashMap<>();
					map.put("beiwlid", id);
					map.put("huizhirenid", xueSheng.getXueshengid().toString());
					map.put("leixing", "0");
					map.put("zhuangtai", huizhi);
					int i = beiwlService.insert_beiwlhuizhi(map);
					if (i > 0) {
						// 发送激光消息
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						xiaoXiFaSong.setXiaoXiMingCheng("回执信息");
						xiaoXiFaSong.setXiaoXiNeiRong(xueSheng.getXingming() + "回执事件$" + beiWL.getNeirong() + "$");
						xiaoXiFaSong.setShuJuId(beiWL.getId());
						xiaoXiFaSong.setShuJuLeiXing(3);
						xiaoXiFaSong.setFaSongLeiXing(2);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(xueSheng.getXuexiaoXuehao().split("_")[0]);
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date = new Date();
						tiXing.setNeirong(xueSheng.getXingming() + "回执事件$" + beiWL.getNeirong() + "$");
						tiXing.setShijian(date);
						tiXing.setZhuangtai(0);
						tiXing.setShujuid(beiWL.getId());
						tiXing.setType(2);
						tiXing.setJieshourenid(Integer.parseInt(beiWL.getUserid()));
						tixingService.insert(tiXing);
						xiaoXiFaSong.setFaSongMuBiao(beiWL.getUserid());
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						json.put("success", true);
						json.put("qufen", qufen);
					} else
						json.put("success", false);
				} else
					json.put("success", false);
			} else
				response.sendRedirect("logout");
		} else
			response.sendRedirect("login");
		return json;
	}
}
