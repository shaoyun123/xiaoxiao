package com.web.controller.web.fudaoyuan;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.maven.artifact.repository.metadata.RepositoryMetadataResolutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.web.annotation.LoginStatusAnnotation;
import com.web.model.BanJi;
import com.web.model.BeiWL;
import com.web.model.ChaQinAnPai;
import com.web.model.FuDaoYuan;
import com.web.model.HuoDong;
import com.web.model.JCSJ;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.JieCiFangAn;
import com.web.model.KeCheng;
import com.web.model.KeChengJiBen;
import com.web.model.TiXing;
import com.web.model.XiaoQu;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.model.YuanXi;
import com.web.service.BanJiService;
import com.web.service.BeiWLService;
import com.web.service.ChaQinService;
import com.web.service.FuDaoYuanService;
import com.web.service.HuoDongService;
import com.web.service.JCSJService;
import com.web.service.JiGuangService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.JieCiFangAnService;
import com.web.service.KeChengJiBenService;
import com.web.service.KeChengService;
import com.web.service.NianFenService;
import com.web.service.TiXingService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YiJianXiangService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "fudaoyuan")
public class RiChengController_fdy {

	@Autowired
	private KeChengService kechengService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private JieCiFangAnService jiecifanganService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private FuDaoYuanService fudaoyuanService;
	@Autowired
	private HuoDongService huodongService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private BeiWLService beiwlService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private KeChengJiBenService kechengjibenService;
	@Autowired
	private TiXingService tixingService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private YiJianXiangService yiJianXiangService;
	@Autowired
	private ChaQinService chaQinService;
	@Autowired
	private NianFenService nianfenService;

	@RequestMapping(value = "xueshengkecheng") // 学生课程——格子显示
	public ModelAndView xueshengkecheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NumberFormatException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取当前学期
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			map.put("riqi", sdf.format(date));
			map.put("xueXiaoID", xueXiaoID);
			// XueQi xueQi2 = xueqiService.getByxueXiaoIDandriQi(map);
			Map<String, Object> xueQi2 = new HashMap<>();
			xueQi2 = xueqiService.getMapXueQiByxueXiaoIDandriQi(map);
			if (xueQi2 == null) {
				List<Map<String, Object>> xueqis = xueqiService.getNewerXueQi(map);
				if (xueqis != null && xueqis.size() > 0) {
					xueQi2 = xueqis.get(0);
				}
			}
			if (xueQi2 == null) {
				mv.addObject("shangwunum", null);
				mv.addObject("xiawunum", null);
				mv.addObject("wanshangnum", null);
				mv.addObject("jicinum", null);
				mv.addObject("jcsj", null);
				mv.setViewName("fudaoyuan/xueshengkecheng");
				return mv;
			}

			// 获取上课周总数
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = simpleDateFormat.parse(xueQi2.get("kaiShiRiQi").toString());
			Date date2 = simpleDateFormat.parse(xueQi2.get("jieShuRiQi").toString());
			int zhounum = Util.WeeksBetweenDays(date1, date2);
			String xuenian = xueQi2.get("ruXueNianFen").toString();
			String xueqi = xueQi2.get("xueQi").toString();
			// //计算今天是当前学期的第几周
			Date kaishiriqi = simpleDateFormat.parse(xueQi2.get("kaiShiRiQi").toString());
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(kaishiriqi);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date);
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
			mv.addObject("banji", "");
			mv.addObject("xuenian", xuenian);
			mv.addObject("xueqi", xueqi);
			mv.addObject("zhou", zhou);
			mv.addObject("xuesheng", "");
			mv.setViewName("forward:chaxunkecheng_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "show_student") // 根据班级，显示班级所有学生
	@ResponseBody
	public List<XueSheng> show_student(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			HttpSession session = request.getSession();
			YongHu user = (YongHu) session.getAttribute("user");
			String banjiid = request.getParameter("CODE");
			List<XueSheng> xueShengs = new ArrayList<>();
			if (banjiid.equals("")) {
				String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
				String banji[] = banjiids.split(",");
				for (String string : banji) {
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs2) {
						xueShengs.add(xueSheng);
					}
				}
			} else {
				xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
			}
			return xueShengs;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "show_zhoushu") // 根据学年学期，显示周数
	@ResponseBody
	public JSONObject show_zhoushu(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		if (Util.checkSession(request)) {
			JSONObject json = new JSONObject();
			HttpSession session = request.getSession();
			YongHu user = (YongHu) session.getAttribute("user");
			int xuexiaoid = yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
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
				map.put("nianfen", xuenian.split("~")[0]);
				map.put("xueqi", xueqi);
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

	@RequestMapping(value = "chaxunkecheng_fdy") // 查询学生课程——格子显示
	public ModelAndView chaxunkecheng_fdy(HttpServletRequest request, HttpServletResponse response)
			throws IOException, NumberFormatException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String xuenian = request.getParameter("xuenian") == null ? request.getAttribute("xuenian").toString()
					: request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi") == null ? request.getAttribute("xueqi").toString()
					: request.getParameter("xueqi");
			String zhou = request.getParameter("zhou") == null ? request.getAttribute("zhou").toString()
					: request.getParameter("zhou");
			String banjiid = request.getParameter("banji") == null ? request.getAttribute("banji").toString()
					: request.getParameter("banji");
			String xueshengid = request.getParameter("xuesheng") == null
					? (request.getAttribute("xuesheng") == null ? "" : request.getAttribute("xuesheng").toString())
					: request.getParameter("xuesheng");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// 获取学校ID
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
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
				if (jcsj.getShijianduan() == 1) {
					shangwunum++;
				}
				if (jcsj.getShijianduan() == 2) {
					xiawunum++;
				}
				if (jcsj.getShijianduan() == 3) {
					wanshangnum++;
				}
			}
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			List<KeCheng> keChengs = new ArrayList<>();
			List<Map<String, Object>> keChengMaps = new ArrayList<>();
			// 获取辅导员管理的班级列表
			String banjiids[] = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid().split(",");
			List<BanJi> banJis = new ArrayList<>();
			for (String string : banjiids) {
				BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(string));
				String riqi = simpleDateFormat.format(new Date());
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(bj.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-09-01";
				if (simpleDateFormat.parse(biYeNianFen).getTime() > simpleDateFormat.parse(riqi).getTime()) {
					banJis.add(bj);
				}
			}
			String parse = "(";
			String parse1 = "";
			List<XueSheng> xueShengs = new ArrayList<>();
			if (banjiid == null || banjiid.equals("")) {
				banjiid = banJis.get(0).getBanjiid().toString();
				parse += "banJiIDs like '" + banjiid + ",%' or banJiIDs like '%," + banjiid + ",%' or ";
				parse1 += parse.substring(0, parse.lastIndexOf(" or ")) + ")";
				for (String id : banjiids) {
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(id));
					for (XueSheng xueSheng2 : xueShengs2) {
						xueShengs.add(xueSheng2);
					}
				}
				if (xueshengid == null || "null".equals(xueshengid) || "".equals(xueshengid)
						|| xueshengid.equals("0")) {
					// keChengs = kechengService.getAllBybanJiIDs(parse1);
					keChengMaps = kechengService.getByOneBanJiID(banjiid);
				} else {
					int banjiid2 = xueshengService.getUserById(Integer.parseInt(xueshengid)).getBanjiid();
					Map<String, String> map = new HashMap<>();
					map.put("banjiid1", banjiid2 + ",%");
					map.put("banjiid2", "%," + banjiid2 + ",%");
					map.put("xueshengid1", xueshengid + ",%");
					map.put("xueshengid2", "%," + xueshengid + ",%");
					// keChengs =
					// kechengService.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(map);
					keChengMaps = kechengService.getByBanJiIDAndXueShengID(banjiid2, xueshengid);
				}
			} else if (!"0".equals(banjiid)) {
				xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
				if (xueshengid == null || "null".equals(xueshengid) || "".equals(xueshengid)
						|| xueshengid.equals("0")) {
					// keChengs = kechengService.getAllBybanJiID(banjiid + ",%",
					// "%," + banjiid + ",%");
					keChengMaps = kechengService.getByOneBanJiID(banjiid);
				} else {
					int banjiid2 = xueshengService.getUserById(Integer.parseInt(xueshengid)).getBanjiid();
					Map<String, String> map = new HashMap<>();
					map.put("banjiid1", banjiid2 + ",%");
					map.put("banjiid2", "%," + banjiid2 + ",%");
					map.put("xueshengid1", xueshengid + ",%");
					map.put("xueshengid2", "%," + xueshengid + ",%");
					// keChengs =
					// kechengService.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(map);
					keChengMaps = kechengService.getByBanJiIDAndXueShengID(banjiid2, xueshengid);
				}
			} else {
				mv.addObject("xuenian", xuenian);
				mv.addObject("xueqi", xueqi);
				mv.addObject("zhou", zhou);
				mv.addObject("shangwunum", shangwunum);
				mv.addObject("xiawunum", xiawunum);
				mv.addObject("wanshangnum", wanshangnum);
				mv.addObject("jicinum", jiecinum);
				mv.addObject("xuenians", xuenians);
				mv.addObject("jcsj", jcsjs);
				mv.setViewName("forward:allbanjikecheng");
				return mv;
			}
			// 获取当前周的周一和周日
			Map<String, String> map = new HashMap<>();
			String nianfen = xuenian.split("~")[0];
			map.put("nianfen", nianfen);
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			map.put("xuexiaoid", xueXiaoID);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			if (xueQi == null) {
				mv.addObject("shangwunum", shangwunum);
				mv.addObject("xiawunum", xiawunum);
				mv.addObject("wanshangnum", wanshangnum);
				mv.addObject("jicinum", jiecinum);
				mv.addObject("xuenians", xuenians);
				mv.addObject("jcsj", jcsjs);
				mv.setViewName("fudaoyuan/xueshengkecheng");
				return mv;
			}

			// 获取上课周总数
			Date date1 = simpleDateFormat.parse(xueQi.getKaishiriqi());
			Date date2 = simpleDateFormat.parse(xueQi.getJieshuriqi());
			int zhounum = Util.WeeksBetweenDays(date1, date2);
			if (Integer.parseInt(zhou) > zhounum) {
				zhou = "1";
			}
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (Map<String, Object> keChengMap : keChengMaps) {
				if (xueQi.getXueqiid().toString().equals(keChengMap.get("xueQiID").toString())) {
					// 停课情况
					List<String> tingkes = new ArrayList<>();
					for (Map<String, Object> tingKe : keChengMaps) {
						if (keChengMap.get("ID").toString().equals(tingKe.get("ID").toString())) {
							if (tingKe.containsKey("shiJianLeiXing")
									&& tingKe.get("shiJianLeiXing").toString().equals("5")) {
								if (zhou.equals(tingKe.get("kaiShiZhou").toString())) {
									tingkes.add(tingKe.get("zhouCi").toString());
								}
							}
						}
					}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("1")
							&& Integer.parseInt(zhou) >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
							&& Integer.parseInt(zhou) <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())) {
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
								kecheng.setXueqiid(xueQi.getXueqiid());
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
							kecheng.setXueqiid(xueQi.getXueqiid());
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
							for (String zhouCi : tingkes) {
								if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
									i = 1;
									break;
								}
							}
							if (i == 0) {
								if (Integer.parseInt(zhou) % 2 != 0) {
									KeCheng kecheng = new KeCheng();
									kecheng.setXueqiid(xueQi.getXueqiid());
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
							}
						} else {
							if (Integer.parseInt(zhou) % 2 != 0) {
								KeCheng kecheng = new KeCheng();
								kecheng.setXueqiid(xueQi.getXueqiid());
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
						}
					}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("3")
							&& Integer.parseInt(zhou) >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
							&& Integer.parseInt(zhou) <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (String zhouCi : tingkes) {
								if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
									i = 1;
									break;
								}
							}
							if (i == 0) {
								if (Integer.parseInt(zhou) % 2 == 0) {
									KeCheng kecheng = new KeCheng();
									kecheng.setXueqiid(xueQi.getXueqiid());
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
							}
						} else {
							if (Integer.parseInt(zhou) % 2 == 0) {
								KeCheng kecheng = new KeCheng();
								kecheng.setXueqiid(xueQi.getXueqiid());
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
						}
					}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("4")
							&& zhou.equals(keChengMap.get("kaiShiZhou").toString())) {
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
								kecheng.setXueqiid(xueQi.getXueqiid());
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
							kecheng.setXueqiid(xueQi.getXueqiid());
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
							&& zhou.equals(keChengMap.get("kaiShiZhou").toString())) {

						KeCheng kecheng = new KeCheng();
						kecheng.setXueqiid(xueQi.getXueqiid());
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
			}
			/**
			 * for (KeCheng keCheng : keChengs) { if
			 * (!(keCheng.getDanshuangzhoushuoming().isEmpty())) { String
			 * danShuangZhouShuoMing[] =
			 * keCheng.getDanshuangzhoushuoming().split("zqxj"); switch
			 * (danShuangZhouShuoMing[0]) { case "1": // 连续 String zhouCi[] =
			 * danShuangZhouShuoMing[1].split(","); String tiaoKe[] =
			 * danShuangZhouShuoMing[4].split("zqjx"); int tiaoTingZhou = 0; //
			 * 判断周次是否在 if (Integer.parseInt(zhouCi[0]) <= Integer.parseInt(zhou)
			 * && Integer.parseInt(zhouCi[1]) >= Integer.parseInt(zhou) &&
			 * keCheng.getXuenian().equals(xuenian) &&
			 * keCheng.getXueqi().toString().equals(xueqi)) { // 判断 是否调停 String
			 * tiaoTing[] = danShuangZhouShuoMing[3].split("zqjx"); //
			 * 获取周几，节次，教室 String total[] =
			 * danShuangZhouShuoMing[2].split("zxqj"); for (String string2 :
			 * total) { String xinxi[] = string2.split(","); // 根据当前周，判断日期 int i
			 * = Integer.parseInt(zhou) - Integer.parseInt(zhouCi[0]) + 1; //
			 * 获取本周距离学期开始的天数 int day = (Integer.parseInt(zhouCi[0]) - 1 + (i -
			 * 1)) * 7 + Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 String
			 * shangkeriqi = simpleDateFormat .format(new
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
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); keChengs2.add(kecheng); } } } if (tiaoKe.length > 0) { int i
			 * = Integer.parseInt(zhou) - Integer.parseInt(zhouCi[0]) + 1; for
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
			 * kecheng.setKaishizhou(Integer.parseInt(zhou));
			 * kecheng.setZhouci(tiaoTingZhou); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()) .getMingcheng()); keChengs2.add(kecheng); } } } }
			 * 
			 * 
			 * break;
			 * 
			 * case "2": // 单双周 String zhouCi2[] =
			 * danShuangZhouShuoMing[1].split(","); // 判断 是否调停 String tiaoTing[]
			 * = danShuangZhouShuoMing[3].split("zqjx"); String tiaoKe2[] =
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
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); keChengs2.add(kecheng); } }
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
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); keChengs2.add(kecheng); }
			 * 
			 * } } } if (tiaoKe2.length > 0) { int i = Integer.parseInt(zhou) -
			 * Integer.parseInt(zhouCi2[0]) + 1; int tiaoTingZhou2 = 0; for (int
			 * ii = 1; ii <= 7; ii++) { int days = (Integer.parseInt(zhouCi2[0])
			 * - 1 + (i - 1)) * 7 + ii - 1; // 获取每次上课日期 String s =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) days * 24 * 60 * 60 * 1000)); for (String string :
			 * tiaoKe2) { int k2 = 0; if (s.equals(string.split(",")[0])) { k2 =
			 * 1; tiaoTingZhou2 = ii; } if (k2 == 1) { String kc[] =
			 * string.split(","); KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhou));
			 * kecheng.setZhouci(tiaoTingZhou2); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); keChengs2.add(kecheng); } } } } break; case "3": //
			 * 单次，不连续,只需比对时间 String kcheng[] =
			 * danShuangZhouShuoMing[1].split("zqjx"); int i =
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
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(Integer.parseInt(zhou)); //
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(zhous); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService
			 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); keChengs2.add(kecheng); } } } break; } } }
			 */
			mv.addObject("zhounum", zhounum);
			mv.addObject("shangwunum", shangwunum);
			mv.addObject("xiawunum", xiawunum);
			mv.addObject("wanshangnum", wanshangnum);
			mv.addObject("jicinum", jiecinum);
			mv.addObject("banji", banJis);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xueqi", xueqi);
			mv.addObject("zhou", zhou);
			mv.addObject("banjiid", banjiid);
			mv.addObject("xuesheng", xueShengs);
			mv.addObject("xueshengid", xueshengid);
			mv.addObject("jcsj", jcsjs);
			mv.addObject("xuenians", xuenians);
			mv.addObject("kecheng", keChengs2);
			mv.addObject("xueqiid", xueQi.getXueqiid());
			// System.out.println(keChengs.size());
			mv.setViewName("fudaoyuan/xueshengkecheng");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "allbanjikecheng")
	public ModelAndView allbanjikecheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			if (Util.isFuDaoYuan(request)) {
				YongHu yonghu = (YongHu) session.getAttribute("user");
				FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(yonghu.getYonghuid());
				int xuexiaoid = yuanxiService.selectByPrimaryKey(yonghu.getYuanxiid()).getXuexiaoid();
				String xuenian = request.getAttribute("xuenian").toString();
				String xueqi = request.getAttribute("xueqi").toString();
				String zhou = request.getAttribute("zhou").toString();
				String shangwunum = request.getAttribute("shangwunum").toString();
				String xiawunum = request.getAttribute("xiawunum").toString();
				String wanshangnum = request.getAttribute("wanshangnum").toString();
				String jiecinum = request.getAttribute("jicinum").toString();
				List<KeCheng> keChengs = new ArrayList<>();
				List<Map<String, Object>> KeChengS = new ArrayList<>();
				String banjiids = fuDaoYuan.getBanjiid();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Map<String, String> map = new HashMap<>();
				map.put("xuexiaoid", xuexiaoid + "");
				map.put("xuenian", xuenian);
				map.put("nianfen", xuenian.split("~")[0]);
				map.put("xueqi", xueqi);
				XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
				Map<String, Integer> maps = new HashMap<>();
				maps.put("xuexiaoid", xuexiaoid);
				maps.put("zhuangtai", 1);
				JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(maps);
				List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
				List<String> xuenians = xueqiService.getXuenianByXuexiaoID(xuexiaoid);
				if (xueQi == null) {
					mv.addObject("xuenian", xuenian);
					mv.addObject("zhounum", "");
					mv.addObject("xueqi", xueqi);
					mv.addObject("zhou", zhou);
					mv.addObject("shangwunum", shangwunum);
					mv.addObject("xiawunum", xiawunum);
					mv.addObject("wanshangnum", wanshangnum);
					mv.addObject("jicinum", jiecinum);
					mv.addObject("xuenians", xuenians);
					mv.addObject("jcsj", jcsjs);
					mv.addObject("kechengs", keChengs);
					mv.setViewName("fudaoyuan/allbanjikecheng");
					return mv;
				} else {
					// 获取上课周总数
					Date date1 = format.parse(xueQi.getKaishiriqi());
					Date date2 = format.parse(xueQi.getJieshuriqi());
					int zhounum = Util.WeeksBetweenDays(date1, date2);
					mv.addObject("zhounum", zhounum);
					if ("".equals(banjiids) || null == banjiids) {
						mv.addObject("xuenian", xuenian);
						mv.addObject("xueqi", xueqi);
						mv.addObject("zhou", zhou);
						mv.addObject("shangwunum", shangwunum);
						mv.addObject("xiawunum", xiawunum);
						mv.addObject("wanshangnum", wanshangnum);
						mv.addObject("jicinum", jiecinum);
						mv.addObject("xuenians", xuenians);
						mv.addObject("jcsj", jcsjs);
						mv.addObject("kechengs", keChengs);
						mv.setViewName("fudaoyuan/allbanjikecheng");
						return mv;
					} else {
						String parse = "";
						String parse1 = "";
						for (String banjiid : banjiids.split(",")) {
							parse1 += banjiid + ",";
						}
						parse = parse1.substring(0, parse1.lastIndexOf(","));
						// kechengs = kechengService.getAllBybanJiIDs(parse);
						KeChengS = kechengService.getByAllBanJiID(parse);
						// 本周内的所有课程，周次，开始节次，结束节次，上课教室，任课教师
						for (Map<String, Object> keChengMap : KeChengS) {
							if (xueQi.getXueqiid().toString().equals(keChengMap.get("xueQiID").toString())) {
								// 停课情况
								List<String> tingkes = new ArrayList<>();
								for (Map<String, Object> tingKe : KeChengS) {
									if (keChengMap.get("ID").toString().equals(tingKe.get("ID").toString())
											&& tingKe.containsKey("shiJianLeiXing")
											&& tingKe.get("shiJianLeiXing").toString().equals("5")
											&& zhou.equals(tingKe.get("kaiShiZhou").toString())) {
										tingkes.add(tingKe.get("zhouCi").toString());
									}
								}

								if (keChengMap.containsKey("shiJianLeiXing")
										&& (keChengMap.get("shiJianLeiXing").toString().equals("1")
												|| keChengMap.get("shiJianLeiXing").toString().equals("2")
												|| keChengMap.get("shiJianLeiXing").toString().equals("3"))
										&& Integer.parseInt(zhou) >= Integer
												.parseInt(keChengMap.get("kaiShiZhou").toString())
										&& Integer.parseInt(zhou) <= Integer
												.parseInt(keChengMap.get("jieShuZhou").toString())) {
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
											List<String> banjiidS = kechengService
													.getByAllBanJiIDByID(keChengMap.get("ID").toString());
											String banJiMingCheng = "";
											for (String string : banjiidS) {
												banJiMingCheng += banjiService
														.selectByPrimaryKey(Integer.parseInt(string))
														.getBanjimingcheng() + ",";
											}
											kecheng.setBanJiMingCheng(banJiMingCheng);
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
											kecheng.setJiaoXueLouMing(jiaoXueLouService
													.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
											kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid())
													.getMingcheng());
											keChengs.add(kecheng);
										}
									} else {
										KeCheng kecheng = new KeCheng();
										List<String> banjiidS = kechengService
												.getByAllBanJiIDByID(keChengMap.get("ID").toString());
										String banJiMingCheng = "";
										for (String string : banjiidS) {
											banJiMingCheng += banjiService.selectByPrimaryKey(Integer.parseInt(string))
													.getBanjimingcheng() + ",";
										}
										kecheng.setBanJiMingCheng(banJiMingCheng);
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
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
										keChengs.add(kecheng);

									}
								}

								if (keChengMap.containsKey("shiJianLeiXing")
										&& keChengMap.get("shiJianLeiXing").toString().equals("4")
										&& zhou.equals(keChengMap.get("kaiShiZhou").toString())) {
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
											List<String> banjiidS = kechengService
													.getByAllBanJiIDByID(keChengMap.get("ID").toString());
											String banJiMingCheng = "";
											for (String string : banjiidS) {
												banJiMingCheng += banjiService
														.selectByPrimaryKey(Integer.parseInt(string))
														.getBanjimingcheng() + ",";
											}
											kecheng.setBanJiMingCheng(banJiMingCheng);
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
											kecheng.setJiaoXueLouMing(jiaoXueLouService
													.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
											kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.getXiaoquid())
													.getMingcheng());
											keChengs.add(kecheng);
										}
									} else {
										KeCheng kecheng = new KeCheng();
										List<String> banjiidS = kechengService
												.getByAllBanJiIDByID(keChengMap.get("ID").toString());
										String banJiMingCheng = "";
										for (String string : banjiidS) {
											banJiMingCheng += banjiService.selectByPrimaryKey(Integer.parseInt(string))
													.getBanjimingcheng() + ",";
										}
										kecheng.setBanJiMingCheng(banJiMingCheng);
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
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
										keChengs.add(kecheng);

									}
								}

								if (keChengMap.containsKey("shiJianLeiXing")
										&& keChengMap.get("shiJianLeiXing").toString().equals("6")
										&& zhou.equals(keChengMap.get("kaiShiZhou").toString())) {

									KeCheng kecheng = new KeCheng();
									List<String> banjiidS = kechengService
											.getByAllBanJiIDByID(keChengMap.get("ID").toString());
									String banJiMingCheng = "";
									for (String string : banjiidS) {
										banJiMingCheng += banjiService.selectByPrimaryKey(Integer.parseInt(string))
												.getBanjimingcheng() + ",";
									}
									kecheng.setBanJiMingCheng(banJiMingCheng);
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
									kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
									keChengs.add(kecheng);
								}
							}
						}
						/**
						 * for (KeCheng keCheng : kechengs) { if
						 * (keCheng.getDanshuangzhoushuoming() != null &&
						 * !("".equals(keCheng.getDanshuangzhoushuoming()))) {
						 * if (xuenian.equals(keCheng.getXuenian()) &&
						 * xueqi.equals(keCheng.getXueqi().toString())) { String
						 * banJiIDs[] = keCheng.getBanjiids().split(","); String
						 * banJiMingCheng = ""; for (String string : banJiIDs) {
						 * banJiMingCheng +=
						 * banjiService.selectByPrimaryKey(Integer.parseInt(
						 * string)) .getBanjimingcheng() + ","; }
						 * keCheng.setBanJiMingCheng(banJiMingCheng); String
						 * danshuangzhoushuoming[] =
						 * keCheng.getDanshuangzhoushuoming().split("zqxj");
						 * switch (danshuangzhoushuoming[0]) { case "1": String
						 * zhoushu[] = danshuangzhoushuoming[1].split(","); if
						 * (Integer.parseInt(zhou) >=
						 * Integer.parseInt(zhoushu[0]) &&
						 * Integer.parseInt(zhou) <=
						 * Integer.parseInt(zhoushu[1])) {
						 * keCheng.setKaishizhou(Integer.parseInt(zhoushu[0]));
						 * keCheng.setJieshuzhou(Integer.parseInt(zhoushu[1]));
						 * // 先判断停课时间 是否有在zhou范围 内的 String zhoucis = ""; String
						 * tingkes[] = danshuangzhoushuoming[3].split("zqjx");
						 * if (tingkes.length > 0) { for (String string :
						 * tingkes) { Calendar cal1 = Calendar.getInstance();
						 * cal1.setTime(format.parse(xueQi.getKaishiriqi()));
						 * Calendar cal2 = Calendar.getInstance();
						 * cal2.setTime(format.parse(string)); int day1 =
						 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
						 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
						 * cal1.get(Calendar.YEAR); int year2 =
						 * cal2.get(Calendar.YEAR); int timeDistance = 0; if
						 * (year1 != year2) { // 不同年 for (int i = year1; i <
						 * year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
						 * 400 == 0) {// 闰年 timeDistance += 366; } else { //
						 * 不是闰年 timeDistance += 365; } timeDistance += day2 -
						 * day1; } } else {// 同年 timeDistance = day2 - day1; }
						 * int tingKeZhou = timeDistance / 7 + 1; if
						 * (Integer.parseInt(zhou) == tingKeZhou) { Calendar c =
						 * Calendar.getInstance();
						 * c.setTime(format.parse(string)); int zhouci = 0; if
						 * (c.get(Calendar.DAY_OF_WEEK) == 1) { zhouci = 7; }
						 * else { zhouci = c.get(Calendar.DAY_OF_WEEK) - 1; }
						 * zhoucis += zhouci + ","; } } } List<String> zhouCis =
						 * new ArrayList<>(); if (!("".equals(zhoucis)) && null
						 * != zhoucis) { for (String s : zhoucis.split(",")) {
						 * if (!(zhouCis.contains(s))) { zhouCis.add(s); } } }
						 * String zhouxinxi[] =
						 * danshuangzhoushuoming[2].split("zxqj"); for (String
						 * string : zhouxinxi) { String xinxi[] =
						 * string.split(","); int i = 0; if (zhouCis.size() > 0)
						 * { for (String s : zhouCis) { if (s.equals(xinxi[0]))
						 * { i = 1; break; } } } if (i == 0) {
						 * keCheng.setZhouci(Integer.parseInt(xinxi[0])); int
						 * kaishijieci = jcsjService
						 * .selectByPrimaryKey(Integer.parseInt(xinxi[1])).
						 * getJieci(); int jieshujieci = jcsjService
						 * .selectByPrimaryKey(Integer.parseInt(xinxi[2])).
						 * getJieci(); keCheng.setKaishijieci(kaishijieci);
						 * keCheng.setJieshujieci(jieshujieci);
						 * 
						 * JiaoShi jiaoshi = jiaoshiService
						 * .selectByPrimaryKey(Integer.parseInt(xinxi[3]));
						 * String jiaoXueLouMing = jiaoXueLouService
						 * .selectByPrimaryKey(jiaoshi.getJiaoxuelouid())
						 * .getJiaoXueLouMing(); String xiaoQuMing =
						 * xiaoquService
						 * .selectByPrimaryKey(jiaoshi.getXiaoquid()).
						 * getMingcheng(); String jiaoShiMing =
						 * jiaoshi.getJiaoshiming();
						 * keCheng.setJiaoshiming(jiaoShiMing);
						 * keCheng.setJiaoXueLouMing(jiaoXueLouMing);
						 * keCheng.setXiaoquming(xiaoQuMing);
						 * 
						 * keChengs.add(keCheng); } } } // 加课 String jiaKes[] =
						 * danshuangzhoushuoming[4].split("zqjx"); if
						 * (jiaKes.length > 0) { for (String string : jiaKes) {
						 * String s[] = string.split(","); Calendar cal1 =
						 * Calendar.getInstance();
						 * cal1.setTime(format.parse(xueQi.getKaishiriqi()));
						 * Calendar cal2 = Calendar.getInstance();
						 * cal2.setTime(format.parse(s[0])); int day1 =
						 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
						 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
						 * cal1.get(Calendar.YEAR); int year2 =
						 * cal2.get(Calendar.YEAR); int timeDistance = 0; if
						 * (year1 != year2) { // 不同年 for (int i = year1; i <
						 * year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
						 * 400 == 0) {// 闰年 timeDistance += 366; } else { //
						 * 不是闰年 timeDistance += 365; } timeDistance += day2 -
						 * day1; } } else {// 同年 timeDistance = day2 - day1; }
						 * int jiaKeZhou = timeDistance / 7 + 1; if
						 * (Integer.parseInt(zhou) == jiaKeZhou) { Calendar c =
						 * Calendar.getInstance();
						 * c.setTime(format.parse(s[0])); int zhouci = 0; if
						 * (c.get(Calendar.DAY_OF_WEEK) == 1) { zhouci = 7; }
						 * else { zhouci = c.get(Calendar.DAY_OF_WEEK) - 1; }
						 * 
						 * keCheng.setZhouci(zhouci);
						 * keCheng.setKaishizhou(Integer.parseInt(zhou)); int
						 * kaishijieci = jcsjService
						 * .selectByPrimaryKey(Integer.parseInt(s[1])).getJieci(
						 * ); int jieshujieci = jcsjService
						 * .selectByPrimaryKey(Integer.parseInt(s[2])).getJieci(
						 * ); keCheng.setKaishijieci(kaishijieci);
						 * keCheng.setJieshujieci(jieshujieci); JiaoShi jiaoshi
						 * = jiaoshiService
						 * .selectByPrimaryKey(Integer.parseInt(s[3])); String
						 * jiaoXueLouMing = jiaoXueLouService
						 * .selectByPrimaryKey(jiaoshi.getJiaoxuelouid())
						 * .getJiaoXueLouMing(); String xiaoQuMing =
						 * xiaoquService
						 * .selectByPrimaryKey(jiaoshi.getXiaoquid()).
						 * getMingcheng(); String jiaoShiMing =
						 * jiaoshi.getJiaoshiming();
						 * keCheng.setJiaoshiming(jiaoShiMing);
						 * keCheng.setJiaoXueLouMing(jiaoXueLouMing);
						 * keCheng.setXiaoquming(xiaoQuMing);
						 * 
						 * keChengs.add(keCheng); } } }
						 * 
						 * break;
						 * 
						 * case "2": String danshuangzhou[] =
						 * danshuangzhoushuoming[1].split(","); if
						 * (Integer.parseInt(zhou) >=
						 * Integer.parseInt(danshuangzhou[0]) &&
						 * Integer.parseInt(zhou) <=
						 * Integer.parseInt(danshuangzhou[1])) {
						 * keCheng.setKaishizhou(Integer.parseInt(danshuangzhou[
						 * 0]));
						 * keCheng.setJieshuzhou(Integer.parseInt(danshuangzhou[
						 * 1])); // 先判断停课时间 是否有在zhou范围 内的 String zhoucis = "";
						 * String tingkes[] =
						 * danshuangzhoushuoming[3].split("zqjx"); if
						 * (tingkes.length > 0) { for (String string : tingkes)
						 * { Calendar cal1 = Calendar.getInstance();
						 * cal1.setTime(format.parse(xueQi.getKaishiriqi()));
						 * Calendar cal2 = Calendar.getInstance();
						 * cal2.setTime(format.parse(string)); int day1 =
						 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
						 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
						 * cal1.get(Calendar.YEAR); int year2 =
						 * cal2.get(Calendar.YEAR); int timeDistance = 0; if
						 * (year1 != year2) { // 不同年 for (int i = year1; i <
						 * year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
						 * 400 == 0) {// 闰年 timeDistance += 366; } else { //
						 * 不是闰年 timeDistance += 365; } timeDistance += day2 -
						 * day1; } } else {// 同年 timeDistance = day2 - day1; }
						 * int tingKeZhou = timeDistance / 7 + 1; if
						 * (Integer.parseInt(zhou) == tingKeZhou) { Calendar c =
						 * Calendar.getInstance();
						 * c.setTime(format.parse(string)); int zhouci = 0; if
						 * (c.get(Calendar.DAY_OF_WEEK) == 1) { zhouci = 7; }
						 * else { zhouci = c.get(Calendar.DAY_OF_WEEK) - 1; }
						 * zhoucis += zhouci + ","; } } } List<String> zhouCis =
						 * new ArrayList<>(); if (!("".equals(zhoucis)) && null
						 * != zhoucis) { for (String s : zhoucis.split(",")) {
						 * if (!(zhouCis.contains(s))) { zhouCis.add(s); } } }
						 * String danShuang[] =
						 * danshuangzhoushuoming[2].split("zqjx"); String dan[]
						 * = danShuang[0].split("zxqj"); List<Map<String,
						 * String>> danMap = new ArrayList<>(); List<Map<String,
						 * String>> shuangMap = new ArrayList<>(); String
						 * shuang[] = danShuang[1].split("zxqj"); // 单周 if
						 * (dan.length > 0) { for (String string : dan) { String
						 * xinxi[] = string.split(","); int i = 0; if
						 * (zhouCis.size() > 0) { for (String s : zhouCis) { if
						 * (s.equals(xinxi[0])) { i = 1; break; } } } if (i ==
						 * 0) { Map<String, String> mapp = new HashMap<>();
						 * mapp.put("zhouci", xinxi[0]); mapp.put("kaishijieci",
						 * xinxi[1]); mapp.put("jieshujieci", xinxi[2]);
						 * mapp.put("jiaoshiid", xinxi[3]); danMap.add(mapp); }
						 * } } // 双周 if (shuang.length > 0) { for (String string
						 * : shuang) { String xinxi[] = string.split(","); int i
						 * = 0; if (zhouCis.size() > 0) { for (String s :
						 * zhouCis) { if (s.equals(xinxi[0])) { i = 1; break; }
						 * } } if (i == 0) { Map<String, String> mapp = new
						 * HashMap<>(); mapp.put("zhouci", xinxi[0]);
						 * mapp.put("kaishijieci", xinxi[1]);
						 * mapp.put("jieshujieci", xinxi[2]);
						 * mapp.put("jiaoshiid", xinxi[3]); shuangMap.add(mapp);
						 * } } } List<Map<String, String>> listAll = new
						 * ArrayList<>(); if (danMap.size() > 0) {
						 * listAll.addAll(danMap); } if (shuangMap.size() > 0) {
						 * for (Map<String, String> map2 : shuangMap) { if
						 * (!listAll.contains(map2)) { listAll.add(map2); } } }
						 * if (listAll.size() > 0) { for (Map<String, String>
						 * map2 : listAll) { KeCheng kecheng = new KeCheng();
						 * kecheng.setKechengmingcheng(keCheng.
						 * getKechengmingcheng());
						 * kecheng.setBanJiMingCheng(keCheng.getBanJiMingCheng()
						 * ); kecheng.setJieshuzhou(keCheng.getJieshuzhou());
						 * kecheng.setKaishizhou(keCheng.getKaishizhou());
						 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
						 * kecheng.setZhouci(Integer.parseInt(map2.get("zhouci")
						 * )); int kaishijieci = jcsjService
						 * .selectByPrimaryKey(
						 * Integer.parseInt(map2.get("kaishijieci")))
						 * .getJieci(); int jieshujieci = jcsjService
						 * .selectByPrimaryKey(
						 * Integer.parseInt(map2.get("jieshujieci")))
						 * .getJieci(); kecheng.setKaishijieci(kaishijieci);
						 * kecheng.setJieshujieci(jieshujieci);
						 * 
						 * JiaoShi jiaoshi = jiaoshiService.selectByPrimaryKey(
						 * Integer.parseInt(map2.get("jiaoshiid"))); String
						 * jiaoXueLouMing = jiaoXueLouService
						 * .selectByPrimaryKey(jiaoshi.getJiaoxuelouid())
						 * .getJiaoXueLouMing(); String xiaoQuMing =
						 * xiaoquService
						 * .selectByPrimaryKey(jiaoshi.getXiaoquid()).
						 * getMingcheng(); String jiaoShiMing =
						 * jiaoshi.getJiaoshiming();
						 * kecheng.setJiaoshiming(jiaoShiMing);
						 * kecheng.setJiaoXueLouMing(jiaoXueLouMing);
						 * kecheng.setXiaoquming(xiaoQuMing);
						 * 
						 * keChengs.add(kecheng); } } } // 加课 String
						 * danShuangJiaKes[] =
						 * danshuangzhoushuoming[4].split("zqjx"); if
						 * (danShuangJiaKes.length > 0) { for (String string :
						 * danShuangJiaKes) { String s[] = string.split(",");
						 * Calendar cal1 = Calendar.getInstance();
						 * cal1.setTime(format.parse(xueQi.getKaishiriqi()));
						 * Calendar cal2 = Calendar.getInstance();
						 * cal2.setTime(format.parse(s[0])); int day1 =
						 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
						 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
						 * cal1.get(Calendar.YEAR); int year2 =
						 * cal2.get(Calendar.YEAR); int timeDistance = 0; if
						 * (year1 != year2) { // 不同年 for (int i = year1; i <
						 * year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
						 * 400 == 0) {// 闰年 timeDistance += 366; } else { //
						 * 不是闰年 timeDistance += 365; } timeDistance += day2 -
						 * day1; } } else {// 同年 timeDistance = day2 - day1; }
						 * int jiaKeZhou = timeDistance / 7 + 1; if
						 * (Integer.parseInt(zhou) == jiaKeZhou) { Calendar c =
						 * Calendar.getInstance();
						 * c.setTime(format.parse(s[0])); int zhouci = 0; if
						 * (c.get(Calendar.DAY_OF_WEEK) == 1) { zhouci = 7; }
						 * else { zhouci = c.get(Calendar.DAY_OF_WEEK) - 1; }
						 * 
						 * keCheng.setZhouci(zhouci);
						 * keCheng.setKaishizhou(Integer.parseInt(zhou)); int
						 * kaishijieci = jcsjService
						 * .selectByPrimaryKey(Integer.parseInt(s[1])).getJieci(
						 * ); int jieshujieci = jcsjService
						 * .selectByPrimaryKey(Integer.parseInt(s[2])).getJieci(
						 * ); keCheng.setKaishijieci(kaishijieci);
						 * keCheng.setJieshujieci(jieshujieci); JiaoShi jiaoshi
						 * = jiaoshiService
						 * .selectByPrimaryKey(Integer.parseInt(s[3])); String
						 * jiaoXueLouMing = jiaoXueLouService
						 * .selectByPrimaryKey(jiaoshi.getJiaoxuelouid())
						 * .getJiaoXueLouMing(); String xiaoQuMing =
						 * xiaoquService
						 * .selectByPrimaryKey(jiaoshi.getXiaoquid()).
						 * getMingcheng(); String jiaoShiMing =
						 * jiaoshi.getJiaoshiming();
						 * keCheng.setJiaoshiming(jiaoShiMing);
						 * keCheng.setJiaoXueLouMing(jiaoXueLouMing);
						 * keCheng.setXiaoquming(xiaoQuMing);
						 * 
						 * keChengs.add(keCheng); } } }
						 * 
						 * break;
						 * 
						 * case "3": String cixinxi[] =
						 * danshuangzhoushuoming[1].split("zqjx"); if
						 * (cixinxi.length > 0) { for (String string : cixinxi)
						 * { String s[] = string.split(","); Calendar cal1 =
						 * Calendar.getInstance();
						 * cal1.setTime(format.parse(xueQi.getKaishiriqi()));
						 * Calendar cal2 = Calendar.getInstance();
						 * cal2.setTime(format.parse(s[0])); int day1 =
						 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
						 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
						 * cal1.get(Calendar.YEAR); int year2 =
						 * cal2.get(Calendar.YEAR); int timeDistance = 0; if
						 * (year1 != year2) { // 不同年 for (int i = year1; i <
						 * year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
						 * 400 == 0) {// 闰年 timeDistance += 366; } else { //
						 * 不是闰年 timeDistance += 365; } timeDistance += day2 -
						 * day1; } } else {// 同年 timeDistance = day2 - day1; }
						 * int jiaKeZhou = timeDistance / 7 + 1; if
						 * (Integer.parseInt(zhou) == jiaKeZhou) { Calendar c =
						 * Calendar.getInstance();
						 * c.setTime(format.parse(s[0])); int zhouci = 0; if
						 * (c.get(Calendar.DAY_OF_WEEK) == 1) { zhouci = 7; }
						 * else { zhouci = c.get(Calendar.DAY_OF_WEEK) - 1; }
						 * 
						 * keCheng.setZhouci(zhouci);
						 * keCheng.setKaishizhou(Integer.parseInt(zhou)); int
						 * kaishijieci = jcsjService
						 * .selectByPrimaryKey(Integer.parseInt(s[1])).getJieci(
						 * ); int jieshujieci = jcsjService
						 * .selectByPrimaryKey(Integer.parseInt(s[2])).getJieci(
						 * ); keCheng.setKaishijieci(kaishijieci);
						 * keCheng.setJieshujieci(jieshujieci); JiaoShi jiaoshi
						 * = jiaoshiService
						 * .selectByPrimaryKey(Integer.parseInt(s[3])); String
						 * jiaoXueLouMing = jiaoXueLouService
						 * .selectByPrimaryKey(jiaoshi.getJiaoxuelouid())
						 * .getJiaoXueLouMing(); String xiaoQuMing =
						 * xiaoquService
						 * .selectByPrimaryKey(jiaoshi.getXiaoquid()).
						 * getMingcheng(); String jiaoShiMing =
						 * jiaoshi.getJiaoshiming();
						 * keCheng.setJiaoshiming(jiaoShiMing);
						 * keCheng.setJiaoXueLouMing(jiaoXueLouMing);
						 * keCheng.setXiaoquming(xiaoQuMing);
						 * 
						 * keChengs.add(keCheng); } } } break; } } } }
						 */
						System.out.println(keChengs.size() + "----------");
						mv.addObject("xuenian", xuenian);
						mv.addObject("xueqi", xueqi);
						mv.addObject("zhou", zhou);
						mv.addObject("shangwunum", shangwunum);
						mv.addObject("xiawunum", xiawunum);
						mv.addObject("wanshangnum", wanshangnum);
						mv.addObject("jicinum", jiecinum);
						mv.addObject("xuenians", xuenians);
						mv.addObject("jcsj", jcsjs);
						mv.addObject("kechengs", keChengs);
						mv.setViewName("fudaoyuan/allbanjikecheng");
						return mv;
					}
				}
			} else {
				response.sendRedirect("logout");
			}
		} else

		{
			response.sendRedirect("login");
		}
		return mv;
	}

	@RequestMapping(value = "xueshengkecheng_liebiao") // 学生课程——列表显示
	public ModelAndView xueshengkecheng_liebiao(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			// String xuenian = request.getParameter("xuenian") == null ?
			// xueQi.get("ruXueNianFen").toString()
			// : request.getParameter("xuenian");
			// String xueqi = request.getParameter("xueqi") == null ?
			// xueQi.get("xueQi").toString()
			// : request.getParameter("xueqi");
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			Map<String, Object> xueQi = new HashMap<>();
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			if ((xuenian != null && !("".equals(xuenian))) && (xueqi != null && !("".equals(xueqi)))) {
				map.put("xueqi", xueqi);
				map.put("xuexiaoid", xueXiaoID);
				map.put("nianfen", xuenian.split("~")[0]);
				xueQi = xueqiService.getMapXueQiByXueXiaoIDAndXueNianAndXueQi(map);
			} else {
				map.put("riqi", riqi.format(date));
				map.put("xueXiaoID", xueXiaoID);
				// XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
				xueQi = xueqiService.getMapXueQiByxueXiaoIDandriQi(map);
				if (xueQi == null) {
					List<Map<String, Object>> xueqis = xueqiService.getNewerXueQi(map);
					if (xueqis != null && xueqis.size() > 0) {
						xueQi = xueqis.get(0);
						xuenian = xueQi.get("ruXueNianFen").toString();
						xueqi = xueQi.get("xueQi").toString();
					}
				}
			}
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			if (xueQi == null) {
				mv.addObject("xuenians", xuenians);
				mv.addObject("kecheng", null);
				mv.setViewName("fudaoyuan/xueshengkecheng_liebiao");
				return mv;
			}
			String banjiid = request.getParameter("banji");

			// 把辅导员管理的班级拼接成查询条件string
			String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
			String banjiID[] = banjiids.split(",");
			List<BanJi> banJis = new ArrayList<>();

			String parse = "";
			List<XueSheng> xueShengs = new ArrayList<>();
			for (String id : banjiID) {
				BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(id));
				String riqis = riqi.format(new Date());
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(bj.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-09-01";
				if (riqi.parse(biYeNianFen).getTime() > riqi.parse(riqis).getTime()) {
					parse += id + ",";
					banJis.add(banjiService.selectByPrimaryKey(Integer.parseInt(id)));
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(id));
					for (XueSheng xueSheng : xueShengs2) {
						xueShengs.add(xueSheng);
					}
				}
			}
			// String parse1 = parse.substring(0, parse.lastIndexOf(" or ")) +
			// ")";
			String parse1 = parse.substring(0, parse.lastIndexOf(","));
			// List<KeCheng> keChengs = new ArrayList<>();
			List<Map<String, Object>> keChengs = new ArrayList<>();
			if ("".equals(banjiid) || null == banjiid) {
				// keChengs = kechengService.getAllBybanJiIDs(parse1);// 全部班级课程
				keChengs = kechengService.getByAllBanJiID(parse1);
			} else {
				// keChengs = kechengService.getAllBybanJiID(banjiid + ",%",
				// "%," + banjiid + ",%");// 单个班级课程
				keChengs = kechengService.getByOneBanJiID(banjiid);
			}
			// List<KeCheng> keChengs =
			// kechengService.getAllBybanJiIDs(parse1);// 查询所有辅导员管理班级的课程
			List<Map<String, String>> tingKeMaps = new ArrayList<>();
			for (Map<String, Object> map2 : keChengs) {
				if (xueQi.get("ID").toString().equals(map2.get("xueQiID").toString())) {
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
			for (int i = 0; i < keChengs.size(); i++) { // 第一级
				if (xueQi.get("ID").toString().equals(keChengs.get(i).get("xueQiID").toString())) {
					KeCheng kecheng = new KeCheng();
					kecheng.setId(Integer.parseInt(keChengs.get(i).get("ID").toString()));
					kecheng.setKechengmingcheng(keChengs.get(i).get("keChengMingCheng").toString());
					kecheng.setRenkejiaoshi(keChengs.get(i).get("renKeJiaoShi").toString());
					List<Map<String, String>> maps = new ArrayList<>();

					if (keChengs.get(i).containsKey("shiJianLeiXing")
							&& (keChengs.get(i).get("shiJianLeiXing").toString().equals("1")
									|| keChengs.get(i).get("shiJianLeiXing").toString().equals("2")
									|| keChengs.get(i).get("shiJianLeiXing").toString().equals("3"))) {
						kecheng.setKaishizhou(Integer.parseInt(keChengs.get(i).get("kaiShiZhou").toString()));
						kecheng.setJieshuzhou(Integer.parseInt(keChengs.get(i).get("jieShuZhou").toString()));
						for (int j = 0; j < keChengs.size(); j++) { // 第二级
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
						if (keChengs.get(i).containsKey("shiJianLeiXing")
								&& keChengs.get(i).get("shiJianLeiXing").toString().equals("1")) {
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
						if (keChengs.get(i).containsKey("shiJianLeiXing")
								&& keChengs.get(i).get("shiJianLeiXing").toString().equals("2")) {
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
						if (keChengs.get(i).containsKey("shiJianLeiXing")
								&& keChengs.get(i).get("shiJianLeiXing").toString().equals("3")) {
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

						for (int j = 0; j < keChengs.size(); j++) {
							if (i != j
									&& keChengs.get(i).get("ID").toString().equals(keChengs.get(j).get("ID").toString())
									&& keChengs.get(j).get("shiJianLeiXing").toString().equals("4")) {
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
							}
						}
						firstMap.put("zhoushu", firstZhou.substring(0, firstZhou.lastIndexOf(",")));
						if (secondZhou != null && !"".equals(secondZhou)) {
							secondMap.put("zhoushu", secondZhou.substring(0, secondZhou.lastIndexOf(",")));
							maps.add(secondMap);
						}
						maps.add(firstMap);
					}

					kecheng.setMaps(maps);
					int m = 0;
					for (KeCheng kc : keChengs2) {
						if (kc.getId().toString().equals(kecheng.getId().toString())) {
							m = 1;
							break;
						}
					}
					if (m == 0) {
						keChengs2.add(kecheng);
					}
				}
			}
			int count = keChengs2.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = (count / pageSize);
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<KeCheng> keChengs3 = new ArrayList<>();
				if (count < 10) {
					for (int i = 0; i < count; i++) {
						keChengs3.add(keChengs2.get(i));
					}
					mv.addObject("kecheng", keChengs3);
				} else {
					for (int i = 0; i < 10; i++) {
						keChengs3.add(keChengs2.get(i));
					}
					mv.addObject("kecheng", keChengs3);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<KeCheng> keChengs3 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								keChengs3.add(keChengs2.get(i));
							}
							mv.addObject("kecheng", keChengs3);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								keChengs3.add(keChengs2.get(i));
							}
							mv.addObject("kecheng", keChengs3);
						}
					} else if (page == pages) {
						List<KeCheng> keChengs3 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							keChengs3.add(keChengs2.get(i));
						}
						mv.addObject("kecheng", keChengs3);
					} else {
						response.sendRedirect("logout");
						return null;
					}
				} else {
					response.sendRedirect("logout");
					return null;
				}
			}
			mv.addObject("banji", banJis);
			mv.addObject("banjiid", banjiid);
			mv.addObject("xuenians", xuenians);
			mv.addObject("user", user);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xueqi", xueqi);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("fudaoyuan/xueshengkecheng_liebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chaxunkechenglist_fdy") // 查询学生课程——列表显示
	public ModelAndView chaxunkechenglist_fdy(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			String banjiid = request.getParameter("banji");
			String xueXiaoID = yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString();
			Map<String, String> map = new HashMap<>();
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			map.put("xuexiaoid", xueXiaoID);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			if (xueQi == null) {
				mv.addObject("xuenians", xuenians);
				mv.addObject("kecheng", null);
				mv.setViewName("fudaoyuan/xueshengkecheng_liebiao");
				return mv;
			}
			// 获取辅导员管理的班级列表
			String banjiids[] = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid().split(",");
			List<BanJi> banJis = new ArrayList<>();
			String parse = "(";
			for (String string : banjiids) {
				parse += "banJiIDs like '" + string + ",%' or banJiIDs like '%," + string + ",%' or ";
				banJis.add(banjiService.selectByPrimaryKey(Integer.parseInt(string)));
			}
			String parse1 = parse.substring(0, parse.lastIndexOf(" or ")) + ")";
			List<KeCheng> keChengs = new ArrayList<>();
			if (banjiid.equals("")) {
				keChengs = kechengService.getAllBybanJiIDs(parse1);// 全部班级课程
			} else {
				keChengs = kechengService.getAllBybanJiID(banjiid + ",%", "%," + banjiid + ",%");// 单个班级课程
			}
			// List<KeCheng> keChengs =
			// kechengService.getAllByTianJiaRen(user.getYonghuid().toString());
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (KeCheng keCheng : keChengs) {
				String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
						.getMingcheng();
				JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi()));
				JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
				int kaiShiJieCi = jcsjService.selectByPrimaryKey(keCheng.getKaishijieci()).getJieci();
				int jieShuJieCi = jcsjService.selectByPrimaryKey(keCheng.getJieshujieci()).getJieci();
				keCheng.setKaishijieci(kaiShiJieCi);
				keCheng.setJieshujieci(jieShuJieCi);
				keCheng.setXiaoquming(xiaoquming);
				keCheng.setJiaoshiming(jiaoShi.getJiaoshiming());
				keCheng.setJiaoXueLouMing(jiaoXueLou.getJiaoXueLouMing());
				if (keCheng.getXuenian().equals(xuenian) && keCheng.getXueqi().toString().equals(xueqi)) {
					keChengs2.add(keCheng);
				}
			}
			int count = keChengs2.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = (count / pageSize);
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<KeCheng> keChengs3 = new ArrayList<>();
				if (count < 10) {

					for (int i = 0; i < count; i++) {
						keChengs3.add(keChengs2.get(i));
					}
					mv.addObject("kecheng", keChengs3);
				} else {
					for (int i = 0; i < 10; i++) {
						keChengs3.add(keChengs2.get(i));
					}
					mv.addObject("kecheng", keChengs3);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<KeCheng> keChengs3 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								keChengs3.add(keChengs2.get(i));
							}
							mv.addObject("kecheng", keChengs3);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								keChengs3.add(keChengs2.get(i));
							}
							mv.addObject("kecheng", keChengs3);
						}
					} else if (page == pages) {
						List<KeCheng> keChengs3 = new ArrayList<>();

						for (int i = (page - 1) * 10; i < count; i++) {
							keChengs3.add(keChengs2.get(i));
						}
						mv.addObject("kecheng", keChengs3);
					} else {
						response.sendRedirect("logout");
					}
				} else {
					response.sendRedirect("logout");
					return null;
				}
			}
			mv.addObject("banji", banJis);
			mv.addObject("xuenians", xuenians);
			mv.addObject("user", user);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xueqi", xueqi);
			mv.addObject("banjiid", banjiid);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("fudaoyuan/xueshengkecheng_liebiao");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakancanyuren_inkecheng_fdy") // 查看参与人
	public ModelAndView chakancanyuren_inkecheng_fdy(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String kechengid = request.getParameter("id");
			if (!Util.isNumeric(kechengid)) {
				response.sendRedirect("logout");
				return null;
			}
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(kechengid));
			if (keCheng == null) {
				response.sendRedirect("logout");
				return null;
			}

			List<String> xueShengIdList = new ArrayList<>();
			if (keCheng.getKeDaiBiao() != null && !keCheng.getKeDaiBiao().equals("")) {
				String[] keDaiBiao = keCheng.getKeDaiBiao().split(";");
				for (int i = 0; i < keDaiBiao.length; i++) {
					xueShengIdList.add(keDaiBiao[i].split(",")[0]);
				}
			}
			List<Map<String, String>> maps = new ArrayList<>();
			List<String> banJiIDs = kechengService.getByAllBanJiIDByID(kechengid);
			List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(kechengid);
			List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(kechengid);

			if (banJiIDs != null && banJiIDs.size() > 0) {
				for (String string : banJiIDs) {
					List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs) {
						int i = 0;
						Map<String, String> map = new HashMap<>();
						if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
							for (String string2 : mianXiuIDs) {
								if (xueSheng.getXueshengid().toString().equals(string2)) {
									i = 1;
									break;
								}
							}
						}
						if (i == 1) {
							map.put("mianxiu", "1");
						} else {
							map.put("mianxiu", "0");
						}
						if (xueShengIdList.contains(xueSheng.getXueshengid().toString())) {
							map.put("kedaibiao", "1");
						} else {
							map.put("kedaibiao", "0");
						}
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
			}
			if (xuanXiuIDs != null && xuanXiuIDs.size() > 0) {
				for (String string : xuanXiuIDs) {
					int i = 0;
					for (Map<String, String> m : maps) {
						if (string.equals(m.get("xueshengid"))) {
							i = 1;
							break;
						}
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
			mv.addObject("kecheng", keCheng);
			mv.addObject("canyuren", maps);
			mv.setViewName("fudaoyuan/chakancanyuren_inkecheng_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "setKeDaiBiao")
	@ResponseBody
	public String setKeDaiBiao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String keDaiBiao = request.getParameter("CODE");
		String xueShengId = keDaiBiao.split(",")[0];
		String banJiId = keDaiBiao.split(",")[1];
		String setKeDaiBiao = xueShengId + "," + banJiId + ";";
		String keChengId = keDaiBiao.split(",")[2];
		List<KeCheng> keChengs = kechengService.selectByKeChengId(Integer.parseInt(keChengId));
		List<String> xueShengIdList = new ArrayList<>();
		List<String> banJiIdList = new ArrayList<>();
		if (keChengs.size() != 0) {
			String keDaiBiaoList = keChengs.get(0).getKeDaiBiao();
			if (keDaiBiaoList != null && !keDaiBiaoList.equals("")) {
				String[] kedaibiaolist = keDaiBiaoList.split(";");

				for (int i = 0; i < kedaibiaolist.length; i++) {
					xueShengIdList.add(kedaibiaolist[i].split(",")[0]);
					banJiIdList.add(kedaibiaolist[i].split(",")[1]);
				}
				if (!xueShengIdList.contains(xueShengId) && !banJiIdList.contains(banJiId)) {
					keDaiBiaoList += setKeDaiBiao;
					keChengs.get(0).setKeDaiBiao(keDaiBiaoList);
					int j = kechengService.updateByKeDaiBiao(keChengs.get(0));
					if (j > 0) {
						return "success";
					} else {
						return "fail";
					}
				} else {
					return "existed";
				}

			} else {
				keChengs.get(0).setKeDaiBiao(setKeDaiBiao);
				int j = kechengService.updateByKeDaiBiao(keChengs.get(0));
				if (j > 0) {
					return "success";
				} else {
					return "fail";
				}
			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "cancelKeDaiBiao")
	@ResponseBody
	public String cancelKeDaiBiao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String keDaiBiao = request.getParameter("CODE");
		String xueShengId = keDaiBiao.split(",")[0];
		String banJiId = keDaiBiao.split(",")[1];
		String keChengId = keDaiBiao.split(",")[2];
		List<KeCheng> keChengs = kechengService.selectByKeChengId(Integer.parseInt(keChengId));

		List<String> xueShengIdList = new ArrayList<>();
		List<String> banJiIdList = new ArrayList<>();
		String setKeDaiBiao = "";
		if (keChengs.size() != 0) {
			String keDaiBiaoList = keChengs.get(0).getKeDaiBiao();
			if (keDaiBiaoList != null && !keDaiBiaoList.equals("")) {
				String[] kedaibiaolist = keDaiBiaoList.split(";");
				for (int i = 0; i < kedaibiaolist.length; i++) {
					xueShengIdList.add(kedaibiaolist[i].split(",")[0]);
					banJiIdList.add(kedaibiaolist[i].split(",")[1]);
				}
				if (xueShengIdList.contains(xueShengId) && banJiIdList.contains(banJiId)) {
					xueShengIdList.remove(xueShengId);
					banJiIdList.remove(banJiId);
					if (xueShengIdList.size() == banJiIdList.size() && xueShengIdList.size() != 0
							&& banJiIdList.size() != 0) {
						for (int i = 0; i < xueShengIdList.size(); i++) {
							setKeDaiBiao = setKeDaiBiao + xueShengIdList.get(i) + "," + banJiIdList.get(i) + ";";

						}
						keChengs.get(0).setKeDaiBiao(setKeDaiBiao);
						int j = kechengService.updateByKeDaiBiao(keChengs.get(0));
						if (j > 0) {
							return "success";
						} else {
							return "fail";
						}
					} else {
						keChengs.get(0).setKeDaiBiao("");
						int j = kechengService.updateByKeDaiBiao(keChengs.get(0));
						if (j > 0) {
							return "success";
						} else {
							return "fail";
						}
					}
				}
			}
		} else {
			return null;
		}
		return null;

	}

	@RequestMapping(value = "addkecheng_fdy") // 新增课程
	public ModelAndView addkecheng_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();
			// 校区列表
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(xuexiaoid);
			List<String> xueqis = xueqiService.getXueqiByXuexiaoID(xuexiaoid);
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("xuenians", xuenians);
			mv.addObject("xueqis", xueqis);
			mv.setViewName("fudaoyuan/addkecheng_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "getxiaoqu_fdy") // 获得校区
	@ResponseBody
	public List<XiaoQu> getxiaoqu_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// JSONObject mv = new JSONObject();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();
			// 校区列表
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			// mv.put("xiaoqu", xiaoQus);
			return xiaoQus;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "getjiecishuju_fdy") // 获得节次
	@ResponseBody
	public List<JCSJ> getjiecishuju_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// JSONObject mv = new JSONObject();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("xuexiaoid", xuexiaoid);
			map.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map);
			List<JCSJ> list = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			List<XiaoQu> xiaoqus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			// mv.put("jcsjs", list);
			return list;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "getjiecishuju_fdy1") // 获得节次
	@ResponseBody
	public Object getjiecishuju_fdy1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// JSONObject mv = new JSONObject();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			Map<String, Object> mapp = new HashMap<>();
			YongHu user = (YongHu) session.getAttribute("user");
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("xuexiaoid", xuexiaoid);
			map.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map);
			List<JCSJ> list = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			List<XiaoQu> xiaoqus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			mapp.put("jieci", list);
			mapp.put("xiaoqu", xiaoqus);
			// mv.put("jcsjs", list);
			return JSON.toJSON(mapp);
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "show_jiaoxuelou") // 根据校区，显示所有教学楼
	@ResponseBody
	public List<JiaoXueLou> show_jiaoxuelou(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (Util.checkSession(request)) {
			String xiaoQuId = request.getParameter("CODE");
			List<JiaoXueLou> jiaoXueLous = new ArrayList<>();
			if (!xiaoQuId.equals("")) {
				jiaoXueLous = jiaoXueLouService.selectByXiaoQuId(Integer.parseInt(xiaoQuId));
			}
			return jiaoXueLous;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "show_jiaoshi") // 根据教学楼，显示所有教室
	@ResponseBody
	public List<JiaoShi> show_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			String jiaoXueLouId = request.getParameter("CODE");
			List<JiaoShi> jiaoShis = new ArrayList<>();
			if (!jiaoXueLouId.equals("")) {
				jiaoShis = jiaoshiService.getAllByPrimaryKey(Integer.parseInt(jiaoXueLouId));
			}
			return jiaoShis;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "selectkecheng") // 添加课程——选择
	public ModelAndView selectkecheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");

			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			List<String> xueqis = xueqiService.getXueqiByXuexiaoID(Integer.parseInt(xueXiaoID));
			// 获取当前学期
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			map.put("riqi", riqi.format(date));
			map.put("xueXiaoID", xueXiaoID);
			// XueQi xueQi2 = xueqiService.getByxueXiaoIDandriQi(map);
			Map<String, Object> xueQiMap = new HashMap<>();
			xueQiMap = xueqiService.getMapXueQiByxueXiaoIDandriQi(map);
			if (xueQiMap == null) {
				// List<Map<String, Object>> xueqis2 =
				// xueqiService.getNewerXueQi(map);
				// if (xueqis2 != null && xueqis2.size() > 0) {
				// xueQiMap = xueqis2.get(0);
				// }
				out.print("<script>alert('选课时间超出学期范围！！');</script>");
				out.print("<script>location='index';</script>");
				return null;
			}
			String xiaoquid = request.getParameter("xiaoqu");
			String shangkejiaoshiid = request.getParameter("shangkejiaoshi");
			String zhouciid = request.getParameter("zhouci");
			String jiaoXueLouId = request.getParameter("jiaoxuelou");
			String xuenian = request.getParameter("xuenian") == null ? xueQiMap.get("ruXueNianFen").toString()
					: request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi") == null ? xueQiMap.get("xueQi").toString()
					: request.getParameter("xueqi");
			map.put("xuexiaoid", xueXiaoID);
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			map.put("nianfen", xuenian.split("~")[0]);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			Map<String, String> map2 = new HashMap<>();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			map2.put("xiaoqu", xiaoquid);
			map2.put("shangkejiaoshi", shangkejiaoshiid);
			map2.put("zhouci", zhouciid);
			map2.put("xuenian", xuenian);
			map2.put("xueqi", xueqi);
			map2.put("xueqiid", xueQi.getXueqiid().toString());
			map2.put("leixing", "1");

			// List<KeCheng> keChengs =
			// kechengService.getAllByShangKeJiaoShiAndXuenianAndXueqi(map2);
			List<Map<String, Object>> keChengs = kechengService.getByJiaoShiIDAndZhouCiAndXueQiID(map2);

			if (keChengs == null || keChengs.size() == 0) {
				// 可选基本课程---》学校id，，，xuexiaokemu表
				// String yuanxiid1 = "%," + user.getYuanxiid().toString() +
				// ",%";
				// String yuanxiid2 = user.getYuanxiid().toString() + ",%";
				// List<KeChengJiBen> keChengJiBens =
				// kechengjibenService.getAllByyuanXiIDs(yuanxiid1, yuanxiid2);
				List<KeChengJiBen> keChengJiBens = new ArrayList<>();
				List<Map<String, Object>> xueXiaoKeMus = kechengjibenService
						.getAllJiBenKeMuByxueXiaoID(Integer.parseInt(xueXiaoID));
				for (Map<String, Object> xueXiaoMap : xueXiaoKeMus) {
					KeChengJiBen kechengjiben = new KeChengJiBen();
					kechengjiben.setId(Integer.parseInt(xueXiaoMap.get("ID").toString()));
					kechengjiben.setKechengid(Integer.parseInt(xueXiaoMap.get("keMuID").toString()));
					kechengjiben.setKechengmingcheng(xueXiaoMap.get("mingCheng").toString());
					if (xueXiaoMap.containsKey("daiMa")) {
						kechengjiben.setDaima(xueXiaoMap.get("daiMa").toString());
					}
					keChengJiBens.add(kechengjiben);
				}

				// 院系名 任课教师名
				List<YuanXi> yuanXiList = yuanxiService.getAllByxueXiaoID(Integer.parseInt(xueXiaoID));
				List<YongHu> yongHuList = new ArrayList<>();
				for (YuanXi yuanXi : yuanXiList) {
					List<YongHu> list = yonghuService.getAllByYuanXiID(yuanXi.getYuanxiid());
					for (YongHu yongHu : list) {
						yongHu.setYuanximingcheng(yuanXi.getYuanximingcheng());
						yongHuList.add(yongHu);
					}
				}

				// for (KeChengJiBen keChengJiBen : keChengJiBens) {
				// String renkejiaoshiids = keChengJiBen.getRenkejiaoshiids();
				// String jiaoshiid[] = renkejiaoshiids.split(",");
				// String renkejiaoshi = "";
				// for (String string : jiaoshiid) {
				// String teacher =
				// yonghuService.selectYongHuByID(Integer.parseInt(string)).getYonghuxingming();
				// renkejiaoshi += teacher + ",";
				// }
				// renkejiaoshi = renkejiaoshi.substring(0,
				// renkejiaoshi.lastIndexOf(","));
				// keChengJiBen.setRenkejiaoshi(renkejiaoshi);
				// }
				// 可选班级
				List<BanJi> banJis = new ArrayList<>();
				List<BanJi> banJiS = banjiService.getAllByYuanXiID(yuanxiid);
				for (BanJi banJi : banJiS) {
					String riqis = simpleDateFormat.format(new Date());
					Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
							.getRuxuenianfen();
					String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
					if (simpleDateFormat.parse(biYeNianFen).getTime() > simpleDateFormat.parse(riqis).getTime()) {
						banJis.add(banJi);
					}
				}
				String banjiids = "";
				for (BanJi banJi : banJis) {
					banjiids += banJi.getBanjiid() + ",";
				}
				String banjiid[] = banjiids.split(",");

				List<Map<String, String>> xueshengs = new ArrayList<>();
				for (String string : banjiid) {
					// banJis.add(banjiService.selectByPrimaryKey(Integer.parseInt(string)));
					List<XueSheng> xuesheng = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng2 : xuesheng) {
						Map<String, String> mapp = new HashMap<>();
						String banji = banjiService.selectByPrimaryKey(xueSheng2.getBanjiid()).getBanjimingcheng();
						mapp.put("banjiid", xueSheng2.getBanjiid().toString());
						mapp.put("banji", banji);
						mapp.put("xueshengid", xueSheng2.getXueshengid().toString());
						mapp.put("xuehao", xueSheng2.getXuehao());
						mapp.put("xingming", xueSheng2.getXingming());
						xueshengs.add(mapp);
					}

				}
				// 获取节次时间
				Map<String, Integer> map3 = new HashMap<>();
				map3.put("xuexiaoid", Integer.parseInt(xueXiaoID));
				map3.put("zhuangtai", 1);
				int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map3).getId();
				List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
				// 获取上课周总数

				Date date1 = simpleDateFormat.parse(xueQi.getKaishiriqi());
				Date date2 = simpleDateFormat.parse(xueQi.getJieshuriqi());
				int zhounum = Util.WeeksBetweenDays(date1, date2);

				XiaoQu xiaoqu = xiaoquService.selectByPrimaryKey(Integer.parseInt(xiaoquid));
				JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(shangkejiaoshiid));
				JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(Integer.parseInt(jiaoXueLouId));
				mv.addObject("xuenians", xuenians);
				mv.addObject("xueqis", xueqis);
				mv.addObject("xuenian", xuenian);
				mv.addObject("xueqi", xueqi);
				mv.addObject("zhounum", zhounum);
				mv.addObject("xuesheng", xueshengs);
				mv.addObject("jieci", jcsjs);
				mv.addObject("xiaoqu", xiaoqu);
				mv.addObject("jiaoshi", jiaoShi);
				mv.addObject("jiaoxuelou", jiaoXueLou);
				mv.addObject("zhouci", zhouciid);
				mv.addObject("kecheng", keChengJiBens);
				mv.addObject("yonghu", yongHuList);
				mv.addObject("banji", banJis);
				mv.setViewName("fudaoyuan/addkecheng_xinjia");
				return mv;
			} else {
				List<BanJi> banJis = new ArrayList<>();
				List<BanJi> banJiS = banjiService.getAllByYuanXiID(yuanxiid);
				for (BanJi banJi : banJiS) {
					String riqis = simpleDateFormat.format(new Date());
					Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
							.getRuxuenianfen();
					String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
					if (simpleDateFormat.parse(biYeNianFen).getTime() > simpleDateFormat.parse(riqis).getTime()) {
						banJis.add(banJi);
					}
				}
				String banjiids = "";
				for (BanJi banJi : banJis) {
					banjiids += banJi.getBanjiid() + ",";
				}
				String banjiid[] = banjiids.split(",");
				String banJiId = null;
				List<KeCheng> kechengs = new ArrayList<>();
				// 若对应课程 班级id不为空，取班级名称，即上课时间点

				for (Map<String, Object> keChengMap : keChengs) {
					KeCheng kecheng = new KeCheng();
					kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
					kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
					kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
					List<String> banJiIDs = kechengService.getByAllBanJiIDByID(keChengMap.get("ID").toString());
					if (banJiIDs.size() > 0 && !(banJiIDs.isEmpty())) {
						String banJiMingCheng = "";
						for (String string : banJiIDs) {
							banJiMingCheng += banjiService.selectByPrimaryKey(Integer.parseInt(string))
									.getBanjimingcheng() + ",";
						}
						kecheng.setBanJiMingCheng(banJiMingCheng.substring(0, banJiMingCheng.lastIndexOf(",")));
					}
					List<Map<String, String>> maps = new ArrayList<>();
					Map<String, String> kechengmap = new HashMap<>();
					if (keChengMap.containsKey("shangKeShiJianID")) {
						if (keChengMap.containsKey("shiJianLeiXing")
								&& (keChengMap.get("shiJianLeiXing").toString().equals("1")
										|| keChengMap.get("shiJianLeiXing").toString().equals("2")
										|| keChengMap.get("shiJianLeiXing").toString().equals("3"))) {
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("1")) {
								kechengmap.put("kaishizhou", keChengMap.get("kaiShiZhou").toString());
								kechengmap.put("jieshuzhou", keChengMap.get("jieShuZhou").toString());
								kechengmap
										.put("kaishijieci",
												jcsjService
														.selectByPrimaryKey(Integer
																.parseInt(keChengMap.get("kaiShiJieCi").toString()))
														.getJieci().toString());
								kechengmap
										.put("jieshujieci",
												jcsjService
														.selectByPrimaryKey(Integer
																.parseInt(keChengMap.get("jieShuJieCi").toString()))
														.getJieci().toString());
								kechengmap.put("zhouci", keChengMap.get("zhouCi").toString());
								JiaoShi js = jiaoshiService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
								String jiaoshiming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng()
										+ "  " + jxl.getJiaoXueLouMing() + "  " + js.getJiaoshiming();
								kechengmap.put("jiaoshiming", jiaoshiming);
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("2")) {
								kechengmap.put("ds", "1");
								kechengmap.put("kaishizhou", keChengMap.get("kaiShiZhou").toString());
								kechengmap.put("jieshuzhou", keChengMap.get("jieShuZhou").toString());
								kechengmap
										.put("kaishijieci",
												jcsjService
														.selectByPrimaryKey(Integer
																.parseInt(keChengMap.get("kaiShiJieCi").toString()))
														.getJieci().toString());
								kechengmap
										.put("jieshujieci",
												jcsjService
														.selectByPrimaryKey(Integer
																.parseInt(keChengMap.get("jieShuJieCi").toString()))
														.getJieci().toString());
								kechengmap.put("zhouci", keChengMap.get("zhouCi").toString());
								JiaoShi js = jiaoshiService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
								String jiaoshiming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng()
										+ "  " + jxl.getJiaoXueLouMing() + "  " + js.getJiaoshiming();
								kechengmap.put("jiaoshiming", jiaoshiming);
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("3")) {
								kechengmap.put("ds", "2");
								kechengmap.put("kaishizhou", keChengMap.get("kaiShiZhou").toString());
								kechengmap.put("jieshuzhou", keChengMap.get("jieShuZhou").toString());
								kechengmap
										.put("kaishijieci",
												jcsjService
														.selectByPrimaryKey(Integer
																.parseInt(keChengMap.get("kaiShiJieCi").toString()))
														.getJieci().toString());
								kechengmap
										.put("jieshujieci",
												jcsjService
														.selectByPrimaryKey(Integer
																.parseInt(keChengMap.get("jieShuJieCi").toString()))
														.getJieci().toString());
								kechengmap.put("zhouci", keChengMap.get("zhouCi").toString());
								JiaoShi js = jiaoshiService
										.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
								JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
								String jiaoshiming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng()
										+ "  " + jxl.getJiaoXueLouMing() + "  " + js.getJiaoshiming();
								kechengmap.put("jiaoshiming", jiaoshiming);
							}

						}
						if (keChengMap.containsKey("shiJianLeiXing")
								&& keChengMap.get("shiJianLeiXing").toString().equals("4")) {
							kechengmap.put("leixing", "3");
							kechengmap.put("kaishizhou", keChengMap.get("kaiShiZhou").toString());
							kechengmap.put("kaishijieci", keChengMap.get("kaiShiJieCi").toString());
							kechengmap.put("jieshujieci", keChengMap.get("jieShuJieCi").toString());
							kechengmap.put("zhouci", keChengMap.get("zhouCi").toString());
							JiaoShi js = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get("jiaoShiID").toString()));
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
							String jiaoshiming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng()
									+ "  " + jxl.getJiaoXueLouMing() + "  " + js.getJiaoshiming();
							kechengmap.put("jiaoshiming", jiaoshiming);
						}
						maps.add(kechengmap);
						kecheng.setMaps(maps);
					}
					if (kechengs.size() > 0) {
						int i = 0;
						for (KeCheng kc : kechengs) {
							if (kc.getId().toString().equals(kecheng.getId().toString())) {
								i = 1;
								List<Map<String, String>> maps2 = kc.getMaps();
								for (Map<String, String> map3 : maps2) {
									if ((map3.containsKey("leixing") && kechengmap.containsKey("leixing"))
											&& map3.get("leixing").equals("3")
											&& kechengmap.get("leixing").equals("3")) {
										if (map3.get("kaishijieci").equals(kechengmap.get("kaishijieci"))
												&& map3.get("jieshujieci").equals(kechengmap.get("jieshujieci"))
												&& map3.get("zhouci").equals(kechengmap.get("zhouci"))) {
											String zhoushu = map3.get("kaishizhou") + ","
													+ kechengmap.get("kaishizhou");
											map3.remove("kaishizhou");
											map3.put("kaishizhou", zhoushu);
										} else {
											if (kechengmap != null && !kechengmap.isEmpty()) {
												maps2.add(kechengmap);
											}
										}
									} else {
										if (kechengmap != null && !kechengmap.isEmpty()) {
											maps2.add(kechengmap);
										}
									}
								}
								kc.setMaps(maps2);
							}
						}
						if (i == 0) {
							kechengs.add(kecheng);
						}
					} else {
						kechengs.add(kecheng);
					}
				}
				if (kechengs.size() < 1) {
					List<KeChengJiBen> keChengJiBens = new ArrayList<>();
					List<Map<String, Object>> xueXiaoKeMus = kechengjibenService
							.getAllJiBenKeMuByxueXiaoID(Integer.parseInt(xueXiaoID));
					for (Map<String, Object> xueXiaoMap : xueXiaoKeMus) {
						KeChengJiBen kechengjiben = new KeChengJiBen();
						kechengjiben.setId(Integer.parseInt(xueXiaoMap.get("ID").toString()));
						kechengjiben.setKechengid(Integer.parseInt(xueXiaoMap.get("keMuID").toString()));
						kechengjiben.setKechengmingcheng(xueXiaoMap.get("mingCheng").toString());
						if (xueXiaoMap.containsKey("daiMa")) {
							kechengjiben.setDaima(xueXiaoMap.get("daiMa").toString());
						}
						keChengJiBens.add(kechengjiben);
					}

					// 院系名 任课教师名
					List<YuanXi> yuanXiList = yuanxiService.getAllByxueXiaoID(Integer.parseInt(xueXiaoID));
					List<YongHu> yongHuList = new ArrayList<>();
					for (YuanXi yuanXi : yuanXiList) {
						List<YongHu> list = yonghuService.getAllByYuanXiID(yuanXi.getYuanxiid());
						for (YongHu yongHu : list) {
							yongHu.setYuanximingcheng(yuanXi.getYuanximingcheng());
							yongHuList.add(yongHu);
						}
					}

					// 可选班级
					List<BanJi> banJiss = new ArrayList<>();
					List<BanJi> banJiSs = banjiService.getAllByYuanXiID(yuanxiid);
					for (BanJi banJi : banJiSs) {
						String riqis = simpleDateFormat.format(new Date());
						Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
								.getRuxuenianfen();
						String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
						if (simpleDateFormat.parse(biYeNianFen).getTime() > simpleDateFormat.parse(riqis).getTime()) {
							banJiss.add(banJi);
						}
					}
					String banjiidss = "";
					for (BanJi banJi : banJiss) {
						banjiidss += banJi.getBanjiid() + ",";
					}
					String banjiidSs[] = banjiidss.split(",");
					List<Map<String, String>> xueshengs = new ArrayList<>();
					for (String string : banjiidSs) {
						// banJis.add(banjiService.selectByPrimaryKey(Integer.parseInt(string)));
						List<XueSheng> xuesheng = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng2 : xuesheng) {
							Map<String, String> mapp = new HashMap<>();
							String banji = banjiService.selectByPrimaryKey(xueSheng2.getBanjiid()).getBanjimingcheng();
							mapp.put("banjiid", xueSheng2.getBanjiid().toString());
							mapp.put("banji", banji);
							mapp.put("xueshengid", xueSheng2.getXueshengid().toString());
							mapp.put("xuehao", xueSheng2.getXuehao());
							mapp.put("xingming", xueSheng2.getXingming());
							xueshengs.add(mapp);
						}

					}
					// 获取节次时间
					Map<String, Integer> map3 = new HashMap<>();
					map3.put("xuexiaoid", Integer.parseInt(xueXiaoID));
					map3.put("zhuangtai", 1);
					int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map3).getId();
					List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
					// 获取上课周总数
					Date date1 = simpleDateFormat.parse(xueQi.getKaishiriqi());
					Date date2 = simpleDateFormat.parse(xueQi.getJieshuriqi());
					int zhounum = Util.WeeksBetweenDays(date1, date2);

					XiaoQu xiaoqu = xiaoquService.selectByPrimaryKey(Integer.parseInt(xiaoquid));
					JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(shangkejiaoshiid));
					JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(Integer.parseInt(jiaoXueLouId));
					mv.addObject("xuenians", xuenians);
					mv.addObject("xueqis", xueqis);
					mv.addObject("xuenian", xuenian);
					mv.addObject("xueqi", xueqi);
					mv.addObject("zhounum", zhounum);
					mv.addObject("xuesheng", xueshengs);
					mv.addObject("jieci", jcsjs);
					mv.addObject("xiaoqu", xiaoqu);
					mv.addObject("jiaoshi", jiaoShi);
					mv.addObject("jiaoxuelou", jiaoXueLou);
					mv.addObject("zhouci", zhouciid);
					mv.addObject("kecheng", keChengJiBens);
					mv.addObject("yonghu", yongHuList);
					mv.addObject("banji", banJiss);
					mv.setViewName("fudaoyuan/addkecheng_xinjia");
					return mv;
				} else {
					XiaoQu xiaoqu = xiaoquService.selectByPrimaryKey(Integer.parseInt(xiaoquid));
					JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(shangkejiaoshiid));
					JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(Integer.parseInt(jiaoXueLouId));
					mv.addObject("xuenians", xuenians);
					mv.addObject("xueqis", xueqis);
					mv.addObject("xuenian", xuenian);
					mv.addObject("xueqi", xueqi);
					mv.addObject("xiaoqu", xiaoqu);
					mv.addObject("jiaoshi", jiaoShi);
					mv.addObject("jiaoxuelou", jiaoXueLou);
					mv.addObject("zhouci", zhouciid);
					mv.addObject("kecheng", kechengs);
					mv.addObject("banji", banJis);
					mv.setViewName("fudaoyuan/addkecheng_chazhao");
					return mv;
				}
			}
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "getteacher") // 从基础课程指定的老师里面，选择需要给某个班级上课的教师
	@ResponseBody
	public List<YongHu> getteacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (Util.checkSession(request)) {
			String keChengId = request.getParameter("CODE");
			KeChengJiBen keChengJiBen = kechengjibenService.selectByPrimaryKey(Integer.parseInt(keChengId));
			String[] renKeJiaoShiId = keChengJiBen.getRenkejiaoshiids().split(",");
			List<YongHu> renKeJiaoShiList = new ArrayList<>();
			for (int i = 0; i < renKeJiaoShiId.length; i++) {
				YongHu teacher = yonghuService.selectYongHuByID(Integer.parseInt(renKeJiaoShiId[i]));
				renKeJiaoShiList.add(teacher);
			}
			return renKeJiaoShiList;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "xinjiakecheng") // 添加课程——新加 下一步
	public ModelAndView xinjiakecheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String xinxis = request.getParameter("id");
			String xinxi[] = xinxis.split("/");
			String xiaoquid = xinxi[0];
			String shangkejiaoshiid = xinxi[1];
			String zhouci = xinxi[2];
			String jiaoXueLouId = xinxi[3];

			XiaoQu xiaoQu = xiaoquService.selectByPrimaryKey(Integer.parseInt(xiaoquid));
			JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(shangkejiaoshiid));
			JiaoXueLou jiaoXueLou = jiaoXueLouService.selectByPrimaryKey(Integer.parseInt(jiaoXueLouId));

			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			List<String> xueqis = xueqiService.getXueqiByXuexiaoID(Integer.parseInt(xueXiaoID));
			// 获取节次时间
			Map<String, Integer> map2 = new HashMap<>();
			map2.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			map2.put("zhuangtai", 1);
			int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map2).getId();
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			// 获取当前学期
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			Map<String, Object> xueQi = new HashMap<>();
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			if (xuenian != null && !("".equals(xuenian)) && xueqi != null && !("".equals(xueqi))) {
				map.put("xuexiaoid", xueXiaoID);
				map.put("nianfen", xuenian.split("~")[0]);
				map.put("xueqi", xueqi);
				xueQi = xueqiService.getMapXueQiByXueXiaoIDAndXueNianAndXueQi(map);
			} else {
				map.put("xueXiaoID", xueXiaoID);
				map.put("riqi", riqi.format(date));
				xueQi = xueqiService.getMapXueQiByxueXiaoIDandriQi(map);
				if (xueQi == null) {
					map.put("xueXiaoID", xueXiaoID);
					map.put("riqi", riqi.format(date));
					List<Map<String, Object>> xueqis2 = xueqiService.getNewerXueQi(map);
					if (xueqis2 != null && xueqis2.size() > 0) {
						xueQi = xueqis2.get(0);
						xuenian = xueQi.get("ruXueNianFen").toString();
						xueqi = xueQi.get("xueQi").toString();
					}
				}
			}

			// 可选择课程列表
			List<KeChengJiBen> keChengJiBens = new ArrayList<>();
			List<Map<String, Object>> xueXiaoKeMus = kechengjibenService
					.getAllJiBenKeMuByxueXiaoID(Integer.parseInt(xueXiaoID));
			for (Map<String, Object> xueXiaoMap : xueXiaoKeMus) {
				KeChengJiBen kechengjiben = new KeChengJiBen();
				kechengjiben.setId(Integer.parseInt(xueXiaoMap.get("ID").toString()));
				kechengjiben.setKechengid(Integer.parseInt(xueXiaoMap.get("keMuID").toString()));
				kechengjiben.setKechengmingcheng(xueXiaoMap.get("mingCheng").toString());
				if (xueXiaoMap.containsKey("daiMa") && xueXiaoMap.get("daiMa").toString() != null
						&& !"".equals(xueXiaoMap.get("daiMa").toString())) {
					kechengjiben.setDaima(xueXiaoMap.get("daiMa").toString());
				}
				keChengJiBens.add(kechengjiben);
			}

			// 院系名 任课教师名
			List<YuanXi> yuanXiList = yuanxiService.getAllByxueXiaoID(Integer.parseInt(xueXiaoID));
			List<YongHu> yongHuList = new ArrayList<>();
			for (YuanXi yuanXi : yuanXiList) {
				List<YongHu> list = yonghuService.getAllByYuanXiID(yuanXi.getYuanxiid());
				for (YongHu yongHu : list) {
					yongHu.setYuanximingcheng(yuanXi.getYuanximingcheng());
					yongHuList.add(yongHu);
				}
			}
			// 可选班级
			List<BanJi> banJis = new ArrayList<>();
			List<BanJi> banJiS = banjiService.getAllByYuanXiID(yuanxiid);
			for (BanJi banJi : banJiS) {
				String riqis = riqi.format(new Date());
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
				if (riqi.parse(biYeNianFen).getTime() > riqi.parse(riqis).getTime()) {
					banJis.add(banJi);
				}
			}
			String banjiids = "";
			for (BanJi banJi : banJis) {
				banjiids += banJi.getBanjiid() + ",";
			}
			String banjiid[] = banjiids.split(",");
			List<Map<String, String>> xueshengs = new ArrayList<>();
			for (String string : banjiid) {
				// banJis.add(banjiService.selectByPrimaryKey(Integer.parseInt(string)));
				List<XueSheng> xuesheng = xueshengService.getAllByBanJiID(Integer.parseInt(string));
				for (XueSheng xueSheng2 : xuesheng) {
					Map<String, String> maps = new HashMap<>();
					String banji = banjiService.selectByPrimaryKey(xueSheng2.getBanjiid()).getBanjimingcheng();
					maps.put("banjiid", xueSheng2.getBanjiid().toString());
					maps.put("banji", banji);
					maps.put("xueshengid", xueSheng2.getXueshengid().toString());
					maps.put("xuehao", xueSheng2.getXuehao());
					maps.put("xingming", xueSheng2.getXingming());
					xueshengs.add(maps);
				}
			}
			// 获取上课周总数
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = simpleDateFormat.parse(xueQi.get("kaiShiRiQi").toString());
			Date date2 = simpleDateFormat.parse(xueQi.get("jieShuRiQi").toString());
			int zhounum = Util.WeeksBetweenDays(date1, date2);
			mv.addObject("xuenians", xuenians);
			mv.addObject("xueqis", xueqis);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xueqi", xueqi);
			mv.addObject("zhounum", zhounum);
			mv.addObject("xuesheng", xueshengs);
			mv.addObject("jieci", jcsjs);
			mv.addObject("xiaoqu", xiaoQu);
			mv.addObject("jiaoxuelou", jiaoXueLou);
			mv.addObject("jiaoshi", jiaoShi);
			mv.addObject("zhouci", zhouci);
			mv.addObject("banji", banJis);
			mv.addObject("kecheng", keChengJiBens);
			mv.addObject("yonghu", yongHuList);
			mv.setViewName("fudaoyuan/addkecheng_xinjia");
			return mv;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "savekecheng_select") // 保存新增课程——选择
	public String savekecheng_select(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			String data = "";
			YongHu user = (YongHu) session.getAttribute("user");
			String codes = request.getParameter("CODE");
			String code[] = codes.split("/");
			String id = code[0];
			String shangkebanji = code[1];
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			// 剔除已有的班级，并将上课班级转为参与人
			List<String> xuanxiuids = kechengService.getAllXuanXiuIDByID(id);
			String xinjiabanjis = "";
			List<String> yiyoubanji = kechengService.getByAllBanJiIDByID(id);
			if (yiyoubanji != null && yiyoubanji.size() > 0) {
				String banjiid[] = shangkebanji.split(",");
				for (String string : banjiid) {
					int same = 0;
					for (String string2 : yiyoubanji) {
						if (string.equals(string2)) {
							same = 1;
							break;
						}
					}
					if (same == 0) {
						xinjiabanjis += string + ",";
					}
				}
			} else {
				xinjiabanjis += shangkebanji;
			}
			String canyuren = "";
			String delxuanxiuids = "";
			if (!xinjiabanjis.equals("")) {
				String banjiid[] = xinjiabanjis.split(",");
				for (String string : banjiid) {
					List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs) {
						canyuren += xueSheng.getXueshengid().toString() + ",";
					}
				}
				if (xuanxiuids != null && xuanxiuids.size() > 0) {
					for (String string2 : xuanxiuids) {
						int same = 0;
						String cyr[] = canyuren.split(",");
						for (String string : cyr) {
							if (string.equals(string2)) {
								same = 1;
								break;
							}
						}
						if (same == 1) {
							delxuanxiuids += string2 + ",";
						}
					}
				}
				Map<String, String> map = new HashMap<>();
				map.put("kechengid", id);
				int j = 0;
				for (String banJiID : banjiid) {
					if (Util.isNumeric(banJiID)) {
						map.put("banjiid", banJiID);
						j = kechengService.insert_shangkebanji(map);
					}
				}
				String delxueshengid[] = delxuanxiuids.split(",");
				for (String string : delxueshengid) {
					map.put("xueshengid", string);
					kechengService.delete_shangkexuanxiuren(map);
				}

				if (j != 0) {
					// 发送极光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng(
							user.getYonghuxingming() + "给你添加了一门课《" + keCheng.getKechengmingcheng() + "》");
					xiaoXiFaSong.setXiaoXiNeiRong(
							user.getYonghuxingming() + "给你添加了一门课《" + keCheng.getKechengmingcheng() + "》");
					xiaoXiFaSong.setShuJuLeiXing(1);
					xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
					xiaoXiFaSong.setFaSongLeiXing(2);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(
							yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString());
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date2 = new Date();
					tiXing.setNeirong(user.getYonghuxingming() + "给你添加了一门课《" + keCheng.getKechengmingcheng() + "》");
					tiXing.setShijian(date2);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(Integer.parseInt(id));
					tiXing.setType(1);
					String cyr[] = canyuren.split(",");
					for (String string : cyr) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						tixingService.insert(tiXing);

					}
					for (String banji : banjiid) {
						xiaoXiFaSong.setFaSongMuBiao(banji);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
					data = "success";
				} else {
					data = "fail";
				}
			} else {
				data = "yitianjia";
			}
			System.out.println("data:" + data);
			return data;

		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "savekecheng_xinjia") // 保存新增课程——新加
	@ResponseBody
	public String savekecheng_xinjia(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String kechengid = request.getParameter("kecheng");
			String renKeJiaoShiId = request.getParameter("teacher");
			String tianjialeixing = request.getParameter("tianjia");
			String shangkebanji = request.getParameter("shangkebanji");
			String cyr = "";
			// 取得当前学年、学期
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
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

			String banjiid[] = null;
			String xuanxiuid[] = null;
			KeCheng keCheng = new KeCheng();

			map.put("id", kechengid);
			Map<String, Object> xueXiaoKeMuMap = kechengjibenService.selectByIDAndXueXiaoID(map);
			String renkejiaoshi = "";
			if (renKeJiaoShiId != null && !renKeJiaoShiId.equals("")) {
				renkejiaoshi = yonghuService.selectYongHuByID(Integer.parseInt(renKeJiaoShiId)).getYonghuxingming();
			}

			String xiaoqu = request.getParameter("xiaoqu");
			String shangkejiaoshi = request.getParameter("jiaoshi");// 教室
			String kaishizhou = request.getParameter("kaishizhou");
			String jieshuzhou = request.getParameter("jieshuzhou");
			String zhouci = request.getParameter("zhouci");
			String danshuangzhou = request.getParameter("danshuangzhou");
			String danshuangzhoushuoming = "";
			String kaishijieci = request.getParameter("kaishijieci");
			String jieshujieci = request.getParameter("jieshujieci");
			String lianxukecheng = request.getParameter("lianxukecheng");
			String danshuang1 = request.getParameter("danshuang");
			int o = 0;
			if (danshuangzhou.equals("2")) {
				String xinxi[] = lianxukecheng.split(";");
				for (String string : xinxi) {
					String str[] = string.split(",");
					if (str[0].compareTo(xueQi2.getKaishiriqi()) < 0 || str[0].compareTo(xueQi2.getJieshuriqi()) > 0) {
						// out.print("<script>alert('选课时间超出学期范围！！');</script>");
						// out.print("<script>location='index';</script>");
						o = 1;
						// break;
						return "shijianyichu";
					}
				}
			}
			if (o == 0) {
				if (tianjialeixing.equals("1")) {
					keCheng.setLeixing(1);
				} else {
					keCheng.setLeixing(2);
				}
				keCheng.setXueqiid(xueQi2.getXueqiid());
				keCheng.setKechengid(Integer.parseInt(kechengid));
				keCheng.setKechengmingcheng(xueXiaoKeMuMap.get("mingCheng").toString());
				keCheng.setRenkejiaoshi(renkejiaoshi);
				keCheng.setTianjiarenid(user.getYonghuid().toString());
				keCheng.setRenkelaoshiid(renKeJiaoShiId);
				int j = kechengService.insertSelective(keCheng);

				if (j != 0) {
					map.put("kechengid", keCheng.getId().toString());

					// 添加班级或对象
					if (tianjialeixing.equals("1")) {// 以班级
						// 将上课班级转为参与人
						banjiid = shangkebanji.split(",");
						for (String string : banjiid) {
							List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
							for (XueSheng xueSheng : xueShengs) {
								cyr += xueSheng.getXueshengid().toString() + ",";
							}
							map.put("banjiid", string);
							kechengService.insert_shangkebanji(map);
						}
					} else { // 以学生
						xuanxiuid = shangkebanji.split(",");
						for (String string : xuanxiuid) {
							cyr += string + ",";
							map.put("xueshengid", string);
							kechengService.insert_shangkexuanxiuren(map);
						}
					}

					// 添加时间
					if (danshuangzhou.equals("1")) { // 连续 1每周，2单周，3双周
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

					} else {
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

					// 发送极光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng(
							user.getYonghuxingming() + "给你添加了一门课《" + keCheng.getKechengmingcheng() + "》");
					xiaoXiFaSong.setXiaoXiNeiRong(
							user.getYonghuxingming() + "给你添加了一门课《" + keCheng.getKechengmingcheng() + "》");
					xiaoXiFaSong.setShuJuLeiXing(1);
					xiaoXiFaSong.setShuJuId(keCheng.getId());
					xiaoXiFaSong.setFaSongLeiXing(2);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(
							yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString());
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date2 = new Date();
					tiXing.setNeirong(user.getYonghuxingming() + "给你添加了一门课《" + keCheng.getKechengmingcheng() + "》");
					tiXing.setShijian(date2);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(keCheng.getId());
					tiXing.setType(1);
					String canyurens[] = cyr.split(",");
					for (String string : canyurens) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						tixingService.insert(tiXing);
						xiaoXiFaSong.setFaSongMuBiao(string);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
					// out.print("<script>alert('加课成功！');</script>");
					// out.print("<script>location='addkecheng_fdy';</script>");
					return "success";
				} else {
					// out.print("<script>alert('加课失败！');</script>");
					// out.print("<script>location='addkecheng_fdy';</script>");
				}

			}

		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "tiaotingkecheng_fdy") // 调停课程
	public ModelAndView tiaotingkecheng_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// 连续周:
			// id=${KeCheng.id}/${KeCheng.leixing}/${
			// maps.zhouci}/${maps.ds}/${maps.kaishizhou}/${maps.jieshuzhou}/${maps.kaishijieci}/${maps.jieshujieci}
			// 次：
			// id=${KeCheng.id}/${KeCheng.leixing
			// }/${maps.zhoushu}/${maps.zhouci}/${maps.kaishijieci}/${maps.jieshujieci}
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
			List<String> canyubanji = kechengService.getByAllBanJiIDByID(ids[0]);
			// List<BanJi> banJis =
			// banjiService.getAllByYuanXiID(user.getYuanxiid());
			List<BanJi> banJis = new ArrayList<>();
			List<BanJi> banJiS = banjiService.getAllByYuanXiID(user.getYuanxiid());
			for (BanJi banJi : banJiS) {
				String riqis = simpleDateFormat.format(new Date());
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
				if (simpleDateFormat.parse(biYeNianFen).getTime() > simpleDateFormat.parse(riqis).getTime()) {
					banJis.add(banJi);
				}
			}
			int i = 0;
			for (BanJi banji : banJis) {
				for (String string2 : canyubanji) {
					if (banji.getBanjiid().toString().equals(string2)) {
						i = 1;
						break;
					}
				}
			}
			if (i == 0) {
				response.sendRedirect("logout");
				return null;
			}
			// 可选择校区
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			XueQi xueQi = xueqiService.selectByID(keCheng.getXueqiid());
			// 教室列表
			List<JiaoShi> jiaoShis = new ArrayList<>();
			// 获取节次时间
			Map<String, Integer> map2 = new HashMap<>();
			map2.put("xuexiaoid", xuexiaoid);
			map2.put("zhuangtai", 1);
			int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map2).getId();
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			List<String> riqi = new ArrayList<>();
			// 通过id，得所有上课时间
			List<Map<String, Object>> shangKeShiJianMap = kechengService.getShangKeShiJianByID(ids[0]);
			List<Map<String, String>> tingKeShiJianMap = new ArrayList<>();
			if (shangKeShiJianMap.size() > 0 && shangKeShiJianMap != null) {
				// 从 所有上课时间 中，得所有停课时间 --- shiJianLeiXing=5
				for (Map<String, Object> map3 : shangKeShiJianMap) {
					if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("5")) {
						// Map<String, String> map = new HashMap<>();
						// map.put("kaishizhou",
						// map3.get("kaiShiZhou").toString());
						// map.put("zhouci", map3.get("zhouCi").toString());
						// tingKeShiJianMap.add(map);
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
								// if
								// (String.valueOf(o).equals(map3.get("kaiShiZhou").toString())
								// &&
								// ids[5].equals(map3.get("jieShuZhou").toString()))
								// {
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
								// }
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

				// 从 所有上课时间 中，得所有 加课或调课 时间 --- shiJianLeiXing=6
				// for (Map<String, Object> map3 : shangKeShiJianMap) {
				// if(map3.containsKey("shiJianLeiXing") &&
				// map3.get("shiJianLeiXing").toString().equals("6")){
				// // 获取每次上课距离学期开始的天数
				// int day =
				// (Integer.parseInt(map3.get("kaiShiZhou").toString()) - 1) * 7
				// + Integer.parseInt(map3.get("zhouCi").toString()) - 1;
				// // 获取每次上课日期
				// String date = simpleDateFormat
				// .format(new
				// Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
				// + (long) day * 24 * 60 * 60 * 1000));
				// riqi.add(date);
				// }
				// }
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
			mv.setViewName("fudaoyuan/tiaotingkecheng_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "show_jiaoshibyxiaoquid")
	@ResponseBody
	public Object show_jiaoshibyxiaoquid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (Util.checkSession(request)) {
			String xiaoquid = request.getParameter("CODE");
			List<JiaoShi> jiaoshis = jiaoshiService.getAllByxiaoQuID(Integer.parseInt(xiaoquid));

			return JSONArray.fromObject(jiaoshis);
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "savetiaotingkecheng_fdy") // 保存调停课程
	@ResponseBody
	public String savetiaotingkecheng_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();

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
			List<String> canyubanji = kechengService.getByAllBanJiIDByID(id);
			List<BanJi> banjiid = banjiService.getAllByYuanXiID(yuanxiid);
			// String banjiid[] =
			// fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid().split(",");
			int j = 0;
			for (BanJi banji : banjiid) {
				for (String string2 : canyubanji) {
					if (banji.getBanjiid().toString().equals(string2)) {
						j = 1;
						break;
					}
				}
			}
			if (j == 0) {
				response.sendRedirect("logout");
				return null;
			}

			String canyurens = "";
			List<String> mianxiuid = kechengService.getAllMianXiuIDByID(id);
			List<String> xuanxiuid = kechengService.getAllXuanXiuIDByID(id);
			String xuanxiuids = "";
			if (xuanxiuid != null && xuanxiuid.size() > 0) {
				for (String string : xuanxiuid) {
					xuanxiuids += string + ",";
				}
			}
			if (canyubanji != null && canyubanji.size() > 0) {
				for (String string : canyubanji) {
					List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs) {
						int same = 0;
						if (mianxiuid != null && mianxiuid.size() > 0) {
							for (String string2 : mianxiuid) {
								if (string2.equals(xueSheng.getXueshengid().toString())) {
									same = 1;
									break;
								}
							}
						}
						if (same == 0) {
							canyurens += xueSheng.getXueshengid() + ",";
						}
					}
				}
			}
			if (xuanxiuids != null && !xuanxiuids.equals("")) {
				canyurens += xuanxiuids;
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
						xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "将" + tingkeriqi + "的《"
								+ kecheng.getKechengmingcheng() + "》停课一次");
						xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(String.valueOf(xuexiaoid));
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date = new Date();
						tiXing.setNeirong(user.getYonghuxingming() + "将" + tingkeriqi + "的《"
								+ kecheng.getKechengmingcheng() + "》停课一次");
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
					// out.print("<script>alert('停课成功！');</script>");
					// out.print("<script>location='xueshengkecheng_liebiao';</script>");
					return "success";
				} else {
					// out.print("<script>alert('停课失败！');</script>");
					// out.print("<script>location='xueshengkecheng_liebiao';</script>");
				}
			}
			if (tiaoting.equals("1")) {
				String tiaokeriqi = request.getParameter("tiaotingriqi");
				String newriqi = request.getParameter("newriqi");
				String xiaoqu = request.getParameter("xiaoqu");
				String shangkejiaoshi = request.getParameter("shangkejiaoshi");
				String kaishijieci = request.getParameter("kaishijieci");
				String jieshujieci = request.getParameter("jieshujieci");
				String newshangkeriqi = "";

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
						xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "将" + tiaokeriqi + "的《"
								+ kecheng.getKechengmingcheng() + "》调到了" + newriqi);
						xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(String.valueOf(xuexiaoid));
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date = new Date();
						tiXing.setNeirong(user.getYonghuxingming() + "将" + tiaokeriqi + "的《"
								+ kecheng.getKechengmingcheng() + "》调到了" + newriqi);
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
					// out.print("<script>alert('调课成功！');</script>");
					// out.print("<script>location='xueshengkecheng_liebiao';</script>");
					return "success";
				} else {
					// out.print("<script>alert('调课失败！');</script>");
					// out.print("<script>location='xueshengkecheng_liebiao';</script>");
				}
			}
			if (tiaoting.equals("2")) {
				String newriqi = request.getParameter("newriqi");
				String xiaoqu = request.getParameter("xiaoqu");
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
						xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "在" + newriqi + "添加了一次《"
								+ kecheng.getKechengmingcheng() + "》");
						xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(String.valueOf(xuexiaoid));
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date = new Date();
						tiXing.setNeirong(user.getYonghuxingming() + "在" + newriqi + "添加了一次《"
								+ kecheng.getKechengmingcheng() + "》");
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
					// out.print("<script>alert('加课成功！');</script>");
					// out.print("<script>location='xueshengkecheng_liebiao';</script>");
					return "success";
				} else {
					// out.print("<script>alert('加课失败！');</script>");
					// out.print("<script>location='xueshengkecheng_liebiao';</script>");
				}
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiugaikecheng_fdy") // 修改课程
	public ModelAndView xiugaikecheng_fdy(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
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
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();
			// 获取当前学期
			Date date = new Date();
			Map<String, String> mapa = new HashMap<>();
			XueQi xueQi = xueqiService.selectByID(kecheng.getXueqiid());
			// 获取上课周总数
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = simpleDateFormat.parse(xueQi.getKaishiriqi());
			Date date2 = simpleDateFormat.parse(xueQi.getJieshuriqi());
			int zhounum = Util.WeeksBetweenDays(date1, date2);
			// 获取节次时间
			Map<String, Integer> map2 = new HashMap<>();
			map2.put("xuexiaoid", xuexiaoid);
			map2.put("zhuangtai", 1);
			int jiecifanganid = jiecifanganService.selectByxueXiaoIDAndZhuangTai(map2).getId();
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			// 校区列表
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			List<BanJi> banJis = new ArrayList<>();
			List<BanJi> banJiS = banjiService.getAllByYuanXiID(yuanxiid);
			for (BanJi banJi : banJiS) {
				String riqis = simpleDateFormat.format(new Date());
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
				if (simpleDateFormat.parse(biYeNianFen).getTime() > simpleDateFormat.parse(riqis).getTime()) {
					banJis.add(banJi);
				}
			}
			String banjiids = "";
			for (BanJi banJi : banJis) {
				banjiids += banJi.getBanjiid() + ",";
			}
			String banjiid[] = banjiids.split(",");
			// 之前已选班级id
			List<String> banji = kechengService.getByAllBanJiIDByID(id);
			List<String> banjiming = new ArrayList<>();
			int i = 0;
			for (String string2 : banji) {
				banjiming.add(banjiService.selectByPrimaryKey(Integer.parseInt(string2)).getBanjimingcheng());
			}
			for (String string : banjiid) {
				for (String string2 : banji) {
					if (string.equals(string2)) {
						i = 1;
						break;
					}
				}
			}
			if (i == 0) {
				response.sendRedirect("logout");
				return null;
			}
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
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("banji", banJis);
			mv.addObject("yixuanbanji", banji);
			mv.addObject("yixuanbanjiming", banjiming);
			mv.addObject("kecheng", kecheng);
			mv.setViewName("fudaoyuan/xiugaikecheng_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatekecheng_fdy") // 保存修改课程
	@ResponseBody
	public String saveupdatekecheng_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html; charset=utf-8");
		// PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
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
			String leixing = request.getParameter("leixing");
			String shangkebanji = request.getParameter("shangkebanji");
			// 取得当前学年、学期
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			Map<String, Integer> mapp = new HashMap<>();
			mapp.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			mapp.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(mapp);
			XueQi xueQi2 = xueqiService.selectByID(kecheng.getXueqiid());
			List<String> canyubanji = kechengService.getByAllBanJiIDByID(id);
			List<BanJi> guanlibanjiid = banjiService.getAllByYuanXiID(user.getYuanxiid());
			int j = 0;
			for (BanJi banji : guanlibanjiid) {
				for (String string2 : canyubanji) {
					if (banji.getBanjiid().toString().equals(string2)) {
						j = 1;
						break;
					}
				}
			}
			if (j == 0) {
				response.sendRedirect("logout");
				return null;
			}
			// 将上课班级转为参与人
			String banjiid[] = shangkebanji.split(",");
			String canyurens = "";
			for (String string : banjiid) {
				List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
				for (XueSheng xueSheng : xueShengs) {
					canyurens += xueSheng.getXueshengid() + ",";
				}
			}

			// 构建新的上课班级：将前的班级delete，新加班级insert
			int d = kechengService.delete_allshangkebanjiByID(id);
			map.put("kechengid", id);
			for (String string : banjiid) {
				map.put("banjiid", string);
				kechengService.insert_shangkebanji(map);
			}

			// 剔除班级里已有的选修人，构建新的选修人ids：前全部delete、新的insert
			List<String> xuanxiuids = kechengService.getAllXuanXiuIDByID(id);
			String newxuanxiuids = "";
			if (xuanxiuids != null && xuanxiuids.size() > 0) {
				for (String string : xuanxiuids) {
					int same = 0;
					for (String string2 : banjiid) {
						String xueshengbanji = xueshengService.getUserById(Integer.parseInt(string)).getBanjiid()
								.toString();
						if (xueshengbanji.equals(string2)) {
							same = 1;
							break;
						}
					}
					if (same == 1) {
						newxuanxiuids += string + ",";
					}
				}
			}
			kechengService.delete_allshangkexuanxiurenByID(id);
			String newxuanxiuid[] = newxuanxiuids.split(",");
			if (newxuanxiuid != null && newxuanxiuid.length > 0) {
				for (String s : newxuanxiuid) {
					if (!"".equals(s) && s != null) {
						map.put("xueshengid", s);
						kechengService.insert_shangkexuanxiuren(map);
					}
				}
			}

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
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("修改课程");
				xiaoXiFaSong
						.setXiaoXiNeiRong(user.getYonghuxingming() + "修改了课程《" + kecheng.getKechengmingcheng() + "》");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
				xiaoXiFaSong.setShuJuLeiXing(1);
				xiaoXiFaSong.setFaSongLeiXing(2);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// 发送消息提醒
				TiXing tiXing = new TiXing();
				Date date2 = new Date();
				tiXing.setNeirong(user.getYonghuxingming() + "修改了课程《" + kecheng.getKechengmingcheng() + "》");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(Integer.parseInt(id));
				tiXing.setType(1);
				String cyr[] = canyurens.split(",");
				for (String string : cyr) {
					tiXing.setJieshourenid(Integer.parseInt(string));
					tixingService.insert(tiXing);
				}
				for (String string : banjiid) {
					xiaoXiFaSong.setFaSongMuBiao(string);
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				}
				// out.print("<script>alert('修改课程成功！');</script>");
				// out.print("<script>location='xueshengkecheng_liebiao';</script>");
				return "success";
			} else {
				// out.print("<script>alert('修改课程失败！');</script>");
				// out.print("<script>location='xueshengkecheng_liebiao';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delkecheng_fdy") // 删除课程
	@ResponseBody
	public String delkecheng_fdy(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		HttpSession session = request.getSession();
		// PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html; charset=utf-8");
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
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
			int all = 0;
			// 获取当前学期
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			XueQi xueQi = xueqiService.selectByID(kecheng.getXueqiid());
			Map<String, Integer> mapss = new HashMap<>();
			mapss.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			mapss.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jiecifanganService.selectByxueXiaoIDAndZhuangTai(mapss);
			int jiecifanganid = jieCiFangAn.getId();
			int j = 0;
			String canyurens = "";
			List<String> canyubanjis = kechengService.getByAllBanJiIDByID(id);
			String banjiid[] = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid().split(",");
			String newbanjiids = "";
			String delbanjis = "";
			if (canyubanjis != null && canyubanjis.size() > 0) {
				for (String string : canyubanjis) {
					int same = 0;
					for (String string2 : banjiid) {
						if (string.equals(string2)) {
							same = 1;
							break;
						}
					}
					if (same == 0) {
						newbanjiids += string + ",";
					} else {
						delbanjis += string + ",";
					}
				}
			}
			String delbanji[] = delbanjis.split(",");
			for (String string : delbanji) {
				List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
				for (XueSheng xueSheng : xueShengs) {
					canyurens += xueSheng.getXueshengid() + ",";
				}
			}
			List<Map<String, Object>> shiJianMap = kechengService.getShangKeShiJianByID(id);
			if (shiJianMap != null && !"".equals(shiJianMap)) {
				// 判断其他辅导员的班级是否有课，
				if (newbanjiids == null || "".equals(newbanjiids)) {
					if (shiJianMap.size() == 1) {
						// 没课的话---删班级、删时间、删课程
						try {
							kechengService.delete_allshangkebanjiByID(id);
							kechengService.delete_allshangkeshijianByID(id);
							kechengService.deleteByPrimaryKey(Integer.parseInt(id));
							j = 1;
						} catch (Exception e) {
							e.printStackTrace();
							j = 0;
						}

					}
					if (shiJianMap.size() > 1) {
						// 只删时间
						if (!"".equals(request.getParameter("xinxi")) && request.getParameter("xinxi") != null) {
							for (Map<String, Object> keChengMap : shiJianMap) {
								String xinxi[] = request.getParameter("xinxi").split("/");
								if (xinxi[0].equals("3")) {
									if (keChengMap.containsKey("shiJianLeiXing")
											&& keChengMap.get("shiJianLeiXing").toString().equals("4")) {
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
														map.put("leixing", "4");
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
				} else {
					// 有课的话--只删除班级
					try {
						Map<String, String> map = new HashMap<>();
						map.put("kechengid", id);
						for (String s : delbanji) {
							if (!"".equals(s) && s != null) {
								map.put("banjiid", s);
								kechengService.delete_shangkebanji(map);
							}
						}
						j = 1;
					} catch (Exception e) {
						e.printStackTrace();
						j = 0;
					}
				}

			} else {
				response.sendRedirect("login");
			}
			if (j != 0) {
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("删除课程");
				xiaoXiFaSong
						.setXiaoXiNeiRong(user.getYonghuxingming() + "删除了课程《" + kecheng.getKechengmingcheng() + "》");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
				xiaoXiFaSong.setShuJuLeiXing(1);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// 发送消息提醒
				TiXing tiXing = new TiXing();
				Date date2 = new Date();
				tiXing.setNeirong(user.getYonghuxingming() + "删除了课程《" + kecheng.getKechengmingcheng() + "》");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(Integer.parseInt(id));
				tiXing.setType(1);
				String cyr[] = canyurens.split(",");
				for (String string : cyr) {
					tiXing.setJieshourenid(Integer.parseInt(string));
					tixingService.insert(tiXing);
					xiaoXiFaSong.setFaSongMuBiao(string);
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				}
				// out.print("<script>alert('删除成功！');</script>");
				// out.print("<script>location='xueshengkecheng_liebiao';</script>");
				return "success";
			} else {
				// out.print("<script>alert('删除失败！');</script>");
				// out.print("<script>location='xueshengkecheng_liebiao';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "wodericheng_fdy") // 我的日程
	public ModelAndView wodericheng_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			mv.addObject("t", new Date().getTime());
			mv.setViewName("fudaoyuan/wodericheng");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping("chaxunrichengbydate_fdy")
	@ResponseBody
	public JSONObject chaxunrichengbydate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		JSONObject json = new JSONObject();
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			Map<String, String> paramMap = new HashMap<>();
			YongHu user = (YongHu) session.getAttribute("user");
			String riqi = request.getParameter("riqi");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			long time = format.parse(riqi).getTime();
			time = time + 24 * 60 * 60 * 1000;
			Date date = new Date(time);
			String nextriqi = format.format(date);
			FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid());
			List<HuoDong> huodongs = new ArrayList<>();
			List<KeCheng> kechengs = new ArrayList<>();
			List<BeiWL> beiwanglus = new ArrayList<>();
			List<ChaQinAnPai> anPais = new ArrayList<>();
			if (fuDaoYuan != null) {
				String xuexiaoid = yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString();
				paramMap.put("riqi", riqi);
				paramMap.put("xueXiaoID", xuexiaoid);
				XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(paramMap);
				if (xueQi == null) {
					returnMap.put("kechengs", kechengs);
				} else {
					// int zhoushu = Util.WeeksBetweenDays(format.parse(riqi),
					// format.parse(xueQi.getKaishiriqi()));
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

					Calendar c = Calendar.getInstance();
					c.setTime(format.parse(riqi));
					int zhouci = 0;
					if (c.get(Calendar.DAY_OF_WEEK) == 1) {
						zhouci = 7;
					} else {
						zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
					}
					List<Map<String, Object>> keChengs = new ArrayList<>();
					String banjiids[] = fuDaoYuan.getBanjiid().split(",");
					if (banjiids.length <= 0) {
						returnMap.put("kechengs", kechengs);
					} else {
						String parse = "";
						for (String string : banjiids) {
							parse += string + ",";
						}
						paramMap.put("banjiid", parse.substring(0, parse.lastIndexOf(",")));
						paramMap.put("zhouci", zhouci + "");
						paramMap.put("zhou", zhou + "");
						paramMap.put("xueqiid", xueQi.getXueqiid().toString());
						keChengs = kechengService.selectAllByRiQiAndBanjiAndXueQiID(paramMap);

						for (Map<String, Object> maps : keChengs) {
							if (maps.containsKey("shiJianLeiXing")
									&& maps.get("shiJianLeiXing").toString().equals("1")) {
								if (zhou >= Integer.parseInt(maps.get("kaiShiZhou").toString())
										&& zhou <= Integer.parseInt(maps.get("jieShuZhou").toString())
										&& String.valueOf(zhouci).equals(maps.get("zhouCi").toString())) {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(maps.get("ID").toString()));
									kecheng.setKechengmingcheng(maps.get("keChengMingCheng").toString());
									kecheng.setZhouci(zhouci);
									JCSJ jcsj1 = jcsjService
											.selectByPrimaryKey(Integer.parseInt(maps.get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService
											.selectByPrimaryKey(Integer.parseInt(maps.get("jieShuJieCi").toString()));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());
									kecheng.setKaishishijian(jcsj1.getKaishishijian());
									kecheng.setJieshushijian(jcsj2.getJieshushijian());
									JiaoShi jshi = jiaoshiService
											.selectByPrimaryKey(Integer.parseInt(maps.get("jiaoShiID").toString()));
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());// 教室id
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
									kechengs.add(kecheng);
								}
							}
							if (maps.containsKey("shiJianLeiXing")
									&& maps.get("shiJianLeiXing").toString().equals("2")) {
								if (zhou >= Integer.parseInt(maps.get("kaiShiZhou").toString())
										&& zhou <= Integer.parseInt(maps.get("jieShuZhou").toString())
										&& String.valueOf(zhouci).equals(maps.get("zhouCi").toString())) {
									if (zhou % 2 != 0) {
										KeCheng kecheng = new KeCheng();
										kecheng.setId(Integer.parseInt(maps.get("ID").toString()));
										kecheng.setKechengmingcheng(maps.get("keChengMingCheng").toString());
										kecheng.setZhouci(zhouci);
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(maps.get("kaiShiJieCi").toString()));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(maps.get("jieShuJieCi").toString()));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());
										kecheng.setKaishishijian(jcsj1.getKaishishijian());
										kecheng.setJieshushijian(jcsj2.getJieshushijian());
										JiaoShi jshi = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(maps.get("jiaoShiID").toString()));
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());// 教室id
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
										kechengs.add(kecheng);
									}
								}
							}
							if (maps.containsKey("shiJianLeiXing")
									&& maps.get("shiJianLeiXing").toString().equals("3")) {
								if (zhou >= Integer.parseInt(maps.get("kaiShiZhou").toString())
										&& zhou <= Integer.parseInt(maps.get("jieShuZhou").toString())
										&& String.valueOf(zhouci).equals(maps.get("zhouCi").toString())) {
									if (zhou % 2 == 0) {
										KeCheng kecheng = new KeCheng();
										kecheng.setId(Integer.parseInt(maps.get("ID").toString()));
										kecheng.setKechengmingcheng(maps.get("keChengMingCheng").toString());
										kecheng.setZhouci(zhouci);
										JCSJ jcsj1 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(maps.get("kaiShiJieCi").toString()));
										JCSJ jcsj2 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(maps.get("jieShuJieCi").toString()));
										kecheng.setKaishijieci(jcsj1.getJieci());
										kecheng.setJieshujieci(jcsj2.getJieci());
										kecheng.setKaishishijian(jcsj1.getKaishishijian());
										kecheng.setJieshushijian(jcsj2.getJieshushijian());
										JiaoShi jshi = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(maps.get("jiaoShiID").toString()));
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());// 教室id
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
										kechengs.add(kecheng);
									}
								}
							}
							if (maps.containsKey("shiJianLeiXing")
									&& maps.get("shiJianLeiXing").toString().equals("4")) {
								if (String.valueOf(zhou).equals(maps.get("kaiShiZhou").toString())
										&& String.valueOf(zhouci).equals(maps.get("zhouCi").toString())) {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(maps.get("ID").toString()));
									kecheng.setKechengmingcheng(maps.get("keChengMingCheng").toString());
									kecheng.setZhouci(zhouci);
									JCSJ jcsj1 = jcsjService
											.selectByPrimaryKey(Integer.parseInt(maps.get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService
											.selectByPrimaryKey(Integer.parseInt(maps.get("jieShuJieCi").toString()));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());
									kecheng.setKaishishijian(jcsj1.getKaishishijian());
									kecheng.setJieshushijian(jcsj2.getJieshushijian());
									JiaoShi jshi = jiaoshiService
											.selectByPrimaryKey(Integer.parseInt(maps.get("jiaoShiID").toString()));
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());// 教室id
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
									kechengs.add(kecheng);
								}
							}
							if (maps.containsKey("shiJianLeiXing")
									&& maps.get("shiJianLeiXing").toString().equals("6")) {
								if (String.valueOf(zhou).equals(maps.get("kaiShiZhou").toString())
										&& String.valueOf(zhouci).equals(maps.get("zhouCi").toString())) {
									KeCheng kecheng = new KeCheng();
									kecheng.setId(Integer.parseInt(maps.get("ID").toString()));
									kecheng.setKechengmingcheng(maps.get("keChengMingCheng").toString());
									kecheng.setZhouci(zhouci);
									JCSJ jcsj1 = jcsjService
											.selectByPrimaryKey(Integer.parseInt(maps.get("kaiShiJieCi").toString()));
									JCSJ jcsj2 = jcsjService
											.selectByPrimaryKey(Integer.parseInt(maps.get("jieShuJieCi").toString()));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());
									kecheng.setKaishishijian(jcsj1.getKaishishijian());
									kecheng.setJieshushijian(jcsj2.getJieshushijian());
									JiaoShi jshi = jiaoshiService
											.selectByPrimaryKey(Integer.parseInt(maps.get("jiaoShiID").toString()));
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									kecheng.setJiaoXueLouMing(jshi.getJiaoshiid().toString());// 教室id
									JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
									kechengs.add(kecheng);
								}
							}
						}
					}
					returnMap.put("kechengs", kechengs);
				}

				paramMap.put("renid", fuDaoYuan.getFudaoyuanid().toString());
				paramMap.put("renleixing", "1");
				huodongs = huodongService.getAllByRiQiAndRenIDAndRenLeiXing(paramMap);
				// huodongs = huodongService.getAllBycanYuRenAndRiQi(paramMap);
				for (HuoDong huoDong : huodongs) {
					paramMap.put("huodongid", huoDong.getHuodongid().toString());
					List<Map<String, Object>> canyuren = huodongService
							.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
					List<Map<String, Object>> jujueren = huodongService
							.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
					int i = 0;
					if (jujueren.size() > 0 && jujueren != null) {
						for (Map<String, Object> map : jujueren) {
							if (map.get("juJueRenID").toString().equals(fuDaoYuan.getFudaoyuanid().toString())
									&& map.get("leiXing").toString().equals("1")) {
								huoDong.setZhuangtai(2);
								i = 1;
								break;
							}
						}
					}
					if (canyuren.size() > 0 && canyuren != null) {
						for (Map<String, Object> map : canyuren) {
							if (map.get("canYuRenID").toString().equals(fuDaoYuan.getFudaoyuanid().toString())
									&& map.get("leiXing").toString().equals("1")) {
								huoDong.setZhuangtai(1);
								i = 1;
								break;
							}
						}
					}
					if (huoDong.getTianjiarenid().toString().equals(fuDaoYuan.getFudaoyuanid().toString())
							&& huoDong.getTianjiarenleixing() == 1) {
						huoDong.setZhuangtai(1);
						i = 1;
					}
					if (i == 0) {
						huoDong.setZhuangtai(0);
					}
				}

				paramMap.put("kaishi", riqi + " 00:00");
				paramMap.put("jieshu", nextriqi + " 00:00");
				// beiwanglus =
				// beiwlService.getBeiWLByJieShouRenAndRiQi(paramMap);
				beiwanglus = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(paramMap);
				paramMap.put("id", fuDaoYuan.getFudaoyuanid().toString());
				anPais = chaQinService.getChaQinByYongHuIdAndRiQi(paramMap);
				returnMap.put("huodongs", huodongs);
				returnMap.put("beiwanglus", beiwanglus);
				returnMap.put("anPais", anPais);

			} else {
				returnMap.put("huodongs", huodongs);
				returnMap.put("kechengs", kechengs);
				returnMap.put("beiwanglus", beiwanglus);
				returnMap.put("anPais", anPais);
			}
			json.put("shuju", returnMap);
		} else {
			response.sendRedirect("login");
			return null;
		}

		return json;
	}

	@RequestMapping("richeng_huodong_fdy")
	public ModelAndView riChengHuoDong(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isFuDaoYuan(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid());
			if (fuDaoYuan != null) {
				String id = request.getParameter("id");
				String zhuangtai = request.getParameter("zhuangtai");
				String qufen = request.getParameter("qufen");
				List<HuoDong> huodongs = new ArrayList<>();
				HuoDong huodong = new HuoDong();
				if (id == null || id == "") {
					huodongs.add(huodong);
					mv.addObject("huodongs", huodongs);
				} else {
					huodong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
					if (huodong != null) {
						if ("0".equals(zhuangtai)) {
							huodong.setZhuangtai(0);
						}
						if ("1".equals(zhuangtai)) {
							huodong.setZhuangtai(1);
						}
						if ("2".equals(zhuangtai)) {
							huodong.setZhuangtai(2);
						}
					}
					huodongs.add(huodong);
					mv.addObject("huodongs", huodongs);
				}
				mv.addObject("page", 1);
				mv.addObject("qufen", qufen);
				mv.addObject("fudaoyuanid", fuDaoYuan.getFudaoyuanid());
				mv.addObject("shijian", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			} else {
				response.sendRedirect("login");
				return null;
			}
		}
		mv.setViewName("fudaoyuan/myhuodong_fdy");
		return mv;
	}

	@RequestMapping("chaxundanmenkecheng")
	@ResponseBody
	public JSONObject chaxundanmenkecheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isFuDaoYuan(request)) {
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
			YongHu user = (YongHu) request.getSession().getAttribute("user");
			String xueXiaoID = yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString();
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
								if (map.containsKey("shiJianLeiXing")
										&& (map.get("shiJianLeiXing").toString().equals("4")
												|| map.get("shiJianLeiXing").toString().equals("6"))) {
									kecheng.setKaishizhou(zhou);
								}
								if (map.containsKey("shiJianLeiXing")
										&& (map.get("shiJianLeiXing").toString().equals("1")
												|| map.get("shiJianLeiXing").toString().equals("2")
												|| map.get("shiJianLeiXing").toString().equals("3"))) {
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

	@RequestMapping(value = "chaxunricheng_fdy") // 查询日程
	public ModelAndView chaxunricheng_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String canyuren1 = "1," + user.getYonghuid().toString() + ",1;%";
			String canyuren2 = "%;1," + user.getYonghuid().toString() + ",1;%";

			List<HuoDong> huodongs = huodongService.getAllBycanYuRen(canyuren1, canyuren2);
			for (HuoDong huoDong : huodongs) {
				String tianjiarenid = huoDong.getTianjiarenid().toString();
				YongHu tianjiaren = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
				String faqiren = "";
				if (tianjiaren == null) {
					faqiren = xueshengService.getUserById(Integer.parseInt(tianjiarenid)).getXingming();
				} else {
					faqiren = tianjiaren.getYonghuxingming();
				}
				huoDong.setFaqiren(faqiren);
			}
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String chaxunriqi = year + "-" + month + "-" + day;
			mv.addObject("year", year);
			mv.addObject("month", month);
			mv.addObject("day", day);
			mv.addObject("user", user);
			mv.addObject("chaxunriqi", chaxunriqi);
			mv.addObject("huodong", huodongs);
			mv.setViewName("fudaoyuan/wodericheng_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakancanyuren_fdy") // 查看参与人
	public ModelAndView chakancanyuren_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String huodongid = request.getParameter("id");
			String banjiid = request.getParameter("banjiid");
			List<BanJi> banJis = new ArrayList<>();
			List<String> banjiidlist = new ArrayList<>();
			List<Map<String, String>> maps = new ArrayList<>();
			List<Map<String, String>> maps2 = new ArrayList<>();
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
					map.put("fudaoyuanid", tianjiarenid.toString());
					map.put("xingming", user.getYonghuxingming());
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
			mv.setViewName("fudaoyuan/chakancanyuren_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "myhuodong_fdy") // 我的活动
	public ModelAndView myhuodong_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid());
			Map<String, String> map = new HashMap<>();
			List<HuoDong> huoDongs = new ArrayList<>();
			map.put("renid", fuDaoYuan.getFudaoyuanid().toString());
			map.put("renleixing", "1");
			huoDongs = huodongService.getAllByRenIDAndRenLeiXing(map);
			for (HuoDong huoDong : huoDongs) {
				map.put("huodongid", huoDong.getHuodongid().toString());
				List<Map<String, Object>> canyuren = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				List<Map<String, Object>> jujueren = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);

				if (huoDong.getTianjiarenid().toString().equals(fuDaoYuan.getFudaoyuanid().toString())) {
					if (jujueren != null && jujueren.size() > 0) {
						huoDong.setZhuangtai(2);
					} else {
						huoDong.setZhuangtai(1);
					}
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
					if (i == 0) {
						huoDong.setZhuangtai(0);
					}
				}
			}
			int count = huoDongs.size();
			int pageSize = 10;
			int page = 1;
			int pages = (count / pageSize) + 1;
			if (count % pageSize == 0) {
				pages = (count / pageSize);
			}
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				List<HuoDong> huoDongs2 = new ArrayList<>();
				if (count < 10) {

					for (int i = 0; i < count; i++) {
						huoDongs2.add(huoDongs.get(i));
					}
					mv.addObject("huodongs", huoDongs2);
				} else {
					for (int i = 0; i < 10; i++) {
						huoDongs2.add(huoDongs.get(i));
					}
					mv.addObject("huodongs", huoDongs2);
				}
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page < pages) {
						List<HuoDong> huoDongs2 = new ArrayList<>();
						if (count < 10) {
							for (int i = (page - 1) * 10; i < count; i++) {
								huoDongs2.add(huoDongs.get(i));
							}
							mv.addObject("huodongs", huoDongs2);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								huoDongs2.add(huoDongs.get(i));
							}
							mv.addObject("huodongs", huoDongs2);
						}
					} else if (page == pages) {
						List<HuoDong> huoDongs2 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							huoDongs2.add(huoDongs.get(i));
						}
						mv.addObject("huodongs", huoDongs2);
					} else {
						response.sendRedirect("logout");
						return null;
					}
				} else {
					response.sendRedirect("logout");
					return null;
				}
			}
			mv.addObject("shijian", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			mv.addObject("fudaoyuanid", fuDaoYuan.getFudaoyuanid());
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("fudaoyuan/myhuodong_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addhuodong_fdy") // 新增活动
	public ModelAndView addhuodong_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
			String banjiid[] = banjiids.split(",");
			List<Map<String, String>> xueShengs = new ArrayList<>();
			List<Map<String, String>> banJis = new ArrayList<>();
			for (String string : banjiid) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String riqi = sdf.format(new Date());
				BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(string));
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
				if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
					// 班级
					Map<String, String> map2 = new HashMap<>();
					map2.put("banjiid", banJi.getBanjiid().toString());
					map2.put("banjimingcheng", banJi.getBanjimingcheng());
					banJis.add(map2);
					// 学生
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs2) {
						Map<String, String> map = new HashMap<>();
						String banji = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
						map.put("banjiid", xueSheng.getBanjiid().toString());
						map.put("banji", banji);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						xueShengs.add(map);
					}
				}
			}
			mv.addObject("qufen", request.getParameter("qufen"));
			mv.addObject("xuesheng", xueShengs);
			mv.addObject("banji", banJis);
			mv.setViewName("fudaoyuan/addhuodong_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "show_wodexuesheng") // 添加活动参与人——根据班级显示我的学生
	@ResponseBody
	public List<Map<String, String>> show_wodexuesheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String banjiid = request.getParameter("CODE");
			List<Map<String, String>> xueShengs = new ArrayList<>();
			if (banjiid.equals("")) {
				String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
				String banjiid2[] = banjiids.split(",");
				for (String string : banjiid2) {
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs2) {
						Map<String, String> map = new HashMap<>();
						String banji = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
						map.put("banjiid", xueSheng.getBanjiid().toString());
						map.put("banji", banji);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						xueShengs.add(map);
					}
				}
			} else {
				List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
				for (XueSheng xueSheng : xueShengs2) {
					Map<String, String> map = new HashMap<>();
					String banji = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
					map.put("banjiid", xueSheng.getBanjiid().toString());
					map.put("banji", banji);
					map.put("xueshengid", xueSheng.getXueshengid().toString());
					map.put("xuehao", xueSheng.getXuehao());
					map.put("xingming", xueSheng.getXingming());
					xueShengs.add(map);
				}
			}
			return xueShengs;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "savehuodong_fdy") // 保存新增活动
	@ResponseBody
	public String savehuodong_fdy(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			// request.setCharacterEncoding("UTF-8");
			// response.setContentType("text/html; charset=utf-8");
			// PrintWriter out = response.getWriter();
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String shuoming = request.getParameter("shuoming");
			String canyuren = request.getParameter("canyuren");
			String riQi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			String huodongbanji = request.getParameter("huodongbanji");
			String leiXing = request.getParameter("leixing");
			String leiXing2 = request.getParameter("leixing2");
			String isChecked = request.getParameter("ischecked");
			String beiWLRiQi = request.getParameter("booktime");
			String qufen = request.getParameter("qufen");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date riqi = simpleDateFormat.parse(riQi);
			long kaiShiShiJian = Integer.parseInt(kaishishijian.split(":")[0]) * 60 * 60 * 1000
					+ Integer.parseInt((kaishishijian.split(":")[1])) * 60 * 1000;
			long beiwlriqi = riqi.getTime() + kaiShiShiJian - (Integer.parseInt(beiWLRiQi) * 60 * 60 * 1000);
			Date date2 = new Date();
			date2.setTime(beiwlriqi);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String beiwangluriqi = sdf.format(date2);
			if (leiXing.equals("按学生添加")) {// 以学生为单位添加活动

				String canyurens[] = canyuren.split(";");
				String newcanyuren = "";
				// String newcanyuren2 = "";
				for (String string : canyurens) {
					newcanyuren += "0," + string + ";";
					// newcanyuren2 += string.split(",")[0] + ",0;";
				}
				canyuren = "1," + user.getYonghuid().toString() + ",1;" + newcanyuren;
				map.put("huodongmingcheng", mingcheng);
				map.put("didian", didian);
				map.put("shuoming", shuoming);
				map.put("tianjiarenid", user.getYonghuid().toString());
				map.put("tianjiarenleixing", "1");
				// map.put("canyuren", canyuren);
				map.put("riqi", riQi);
				map.put("kaishishijian", kaishishijian);
				map.put("jieshushijian", jieshushijian);
				map.put("leixing", "2");
				int i = huodongService.insert(map);
				HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(map.get("huodongid").toString()));
				if (i != 0) {
					// 添加huodongren
					for (String s : canyurens) {
						if (s.split(",")[0] != null && !"".equals(s.split(",")[0])) {
							Map<String, String> mapP = new HashMap<>();
							mapP.put("huodongid", map.get("huodongid").toString());
							mapP.put("renid", s.split(",")[0]);
							mapP.put("renleixing", "0");
							huodongService.insert_huodongren(mapP);
						}
					}
					String canyurens2[] = canyuren.split(";");
					if (isChecked.equals("1")) {// 添加活动的时候，添加备忘录
						BeiWL beiWL = new BeiWL();
						beiWL.setLeixing(2);
						beiWL.setHuizhi(1);
						beiWL.setHuodongid(huoDong.getHuodongid());
						beiWL.setNeirong(huoDong.getHuodongmingcheng());
						// beiWL.setJieshouren(newcanyuren2);
						beiWL.setShijian(beiwangluriqi);
						beiWL.setUserid(user.getYonghuid().toString());
						int j = beiwlService.insertforxuesheng(beiWL);
						if (j != 0) {
							// 添加beiwangluren
							for (String s : canyurens) {
								if (s.split(",")[0] != null && !"".equals(s.split(",")[0])) {
									Map<String, String> mapP = new HashMap<>();
									mapP.put("beiwlid", beiWL.getId().toString());
									mapP.put("jieshourenid", s.split(",")[0]);
									mapP.put("leixing", "0");
									beiwlService.insert_beiwangluren(mapP);
								}
							}
							// 发送激光消息(新增活动)
							XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
							xiaoXiFaSong.setXiaoXiMingCheng("新增活动");
							xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
							xiaoXiFaSong.setShuJuLeiXing(2);
							xiaoXiFaSong.setFaSongLeiXing(1);
							xiaoXiFaSong.setShiFouChengGong(0);
							xiaoXiFaSong.setXueXiaoId(xueXiaoID);
							// 发送提醒消息(新增活动)
							TiXing tiXing = new TiXing();
							Date date = new Date();
							tiXing.setShijian(date);
							tiXing.setZhuangtai(0);
							tiXing.setShujuid(huoDong.getHuodongid());
							tiXing.setType(2);
							// 发送激光消息(新增备忘)
							XiaoXiFaSong xiaoXiFaSong2 = new XiaoXiFaSong();
							xiaoXiFaSong2.setXiaoXiMingCheng("添加事件");
							xiaoXiFaSong2.setShuJuId(beiWL.getId());
							xiaoXiFaSong2.setShuJuLeiXing(3);
							xiaoXiFaSong2.setFaSongLeiXing(1);
							xiaoXiFaSong2.setShiFouChengGong(0);
							xiaoXiFaSong2.setXueXiaoId(xueXiaoID);
							// 发送提醒消息(新增备忘)
							TiXing tiXing2 = new TiXing();
							tiXing2.setShijian(date);
							tiXing2.setZhuangtai(0);
							tiXing2.setShujuid(beiWL.getId());
							tiXing2.setType(3);
							for (String string : canyurens2) {
								String cyr[] = string.split(",");
								if (cyr[0].equals("1")) {// 用户身份为辅导员
									tiXing.setNeirong("您添加了活动#" + mingcheng + "#");
									tiXing2.setNeirong("您添加了一个事件$" + beiWL.getNeirong() + "$");
									xiaoXiFaSong.setXiaoXiNeiRong("您添加了活动#" + mingcheng + "#");
									xiaoXiFaSong2.setXiaoXiNeiRong("您添加了一个事件$" + beiWL.getNeirong() + "$");

								} else {// 用户身份是学生
									tiXing.setNeirong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
									tiXing2.setNeirong(
											user.getYonghuxingming() + "给你添加了一个事件$" + beiWL.getNeirong() + "$");
									xiaoXiFaSong
											.setXiaoXiNeiRong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
									xiaoXiFaSong2.setXiaoXiNeiRong(
											user.getYonghuxingming() + "给你添加了一个事件$" + beiWL.getNeirong() + "$");
								}
								tiXing.setJieshourenid(Integer.parseInt(cyr[1]));
								tixingService.insert(tiXing);
								xiaoXiFaSong.setFaSongMuBiao(cyr[1]);
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
								tiXing2.setJieshourenid(Integer.parseInt(cyr[1]));
								tixingService.insert(tiXing2);
								xiaoXiFaSong2.setFaSongMuBiao(cyr[1]);
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong2);
							}
							// if (qufen.equals("1")) {
							// out.print("<script>alert('新增成功！');</script>");
							// out.print("<script>location='wodericheng_fdy';</script>");
							// } else {
							// out.print("<script>alert('新增成功！');</script>");
							// out.print("<script>location='myhuodong_fdy';</script>");
							// }
							return "success";
						} else {
							// if (qufen.equals("1")) {
							// out.print("<script>alert('新增失败！');</script>");
							// out.print("<script>location='wodericheng_fdy';</script>");
							// } else {
							// out.print("<script>alert('新增失败！');</script>");
							// out.print("<script>location='myhuodong_fdy';</script>");
							// }
						}
					} else {// 只添加活动，不添加备忘录
						// 发送激光消息(添加活动)
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						xiaoXiFaSong.setXiaoXiMingCheng("新增活动");
						xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
						xiaoXiFaSong.setShuJuLeiXing(2);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(xueXiaoID);
						// 发送提醒消息(添加活动)
						TiXing tiXing = new TiXing();
						Date date = new Date();
						tiXing.setShijian(date);
						tiXing.setZhuangtai(0);
						tiXing.setShujuid(huoDong.getHuodongid());
						tiXing.setType(2);
						for (String string : canyurens2) {
							String cyr[] = string.split(",");
							if (cyr[0].equals("1")) {// 用户身份为辅导员
								tiXing.setNeirong("您添加了活动#" + mingcheng + "#");
								xiaoXiFaSong.setXiaoXiNeiRong("您添加了活动#" + mingcheng + "#");

							} else {// 用户身份是学生
								tiXing.setNeirong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
								xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
							}
							tiXing.setJieshourenid(Integer.parseInt(cyr[1]));
							tixingService.insert(tiXing);
							xiaoXiFaSong.setFaSongMuBiao(cyr[1]);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
						// if (qufen.equals("1")) {
						// out.print("<script>alert('新增成功！');</script>");
						// out.print("<script>location='wodericheng_fdy';</script>");
						// } else {
						// out.print("<script>alert('新增成功！');</script>");
						// out.print("<script>location='myhuodong_fdy';</script>");
						// }
						return "success";
					}
				} else {
					// if (qufen.equals("1")) {
					// out.print("<script>alert('新增失败！');</script>");
					// out.print("<script>location='wodericheng_fdy';</script>");
					// } else {
					// out.print("<script>alert('新增失败！');</script>");
					// out.print("<script>location='myhuodong_fdy';</script>");
					// }
				}
				// 以班级为单位，添加活动
			} else if (leiXing2.equals("按班级添加")) {
				// 把参与人的类型加上，此处全为0——学生
				if (huodongbanji != null && !"".equals(huodongbanji)) {
					String banjiids[] = huodongbanji.split(",");

					map.put("huodongmingcheng", mingcheng);
					map.put("didian", didian);
					map.put("shuoming", shuoming);
					map.put("tianjiarenid", user.getYonghuid().toString());
					map.put("tianjiarenleixing", "1");
					// map.put("canyuren", canyuren2);
					map.put("riqi", riQi);
					map.put("kaishishijian", kaishishijian);
					map.put("jieshushijian", jieshushijian);
					map.put("leixing", "1");
					// map.put("banjiids", huodongbanji);
					int i = huodongService.insert(map);
					HuoDong huoDong = huodongService
							.selectByPrimaryKey(Integer.parseInt(map.get("huodongid").toString()));
					if (i != 0) {
						for (String s : banjiids) {
							if (s != null && !"".equals(s)) {
								Map<String, String> mapP = new HashMap<>();
								mapP.put("huodongid", map.get("huodongid").toString());
								mapP.put("banjiid", s);
								huodongService.insert_huodongbanji(mapP);
							}
						}
						if (isChecked.equals("1")) {// 添加活动的同时，添加备忘
							BeiWL beiWL = new BeiWL();
							beiWL.setLeixing(1);
							beiWL.setHuizhi(1);
							beiWL.setHuodongid(huoDong.getHuodongid());
							beiWL.setNeirong(huoDong.getHuodongmingcheng());
							// beiWL.setBanjiids(huodongbanji);
							beiWL.setShijian(beiwangluriqi);
							beiWL.setUserid(user.getYonghuid().toString());
							int j = beiwlService.insertforbanji(beiWL);
							if (j != 0) {
								for (String s : banjiids) {
									if (s != null && !"".equals(s)) {
										Map<String, String> mapP = new HashMap<>();
										mapP.put("beiwlid", beiWL.getId().toString());
										mapP.put("banjiid", s);
										beiwlService.insert_beiwanglubanji(mapP);
									}
								}
								// 发送激光消息(添加活动)
								XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
								xiaoXiFaSong.setXiaoXiMingCheng("新增活动");
								xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
								xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
								xiaoXiFaSong.setShuJuLeiXing(2);
								xiaoXiFaSong.setFaSongLeiXing(1);
								xiaoXiFaSong.setShiFouChengGong(0);
								xiaoXiFaSong.setXueXiaoId(xueXiaoID);
								// 发送提醒消息(添加活动)
								TiXing tiXing = new TiXing();
								Date date = new Date();
								tiXing.setNeirong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
								tiXing.setShijian(date);
								tiXing.setZhuangtai(0);
								tiXing.setShujuid(huoDong.getHuodongid());
								tiXing.setType(2);
								for (int k = 0; k < banjiids.length; k++) {
									List<XueSheng> xueShengs = xueshengService
											.getAllByBanJiID(Integer.parseInt(banjiids[k]));
									for (XueSheng xueSheng : xueShengs) {
										tiXing.setJieshourenid(xueSheng.getXueshengid());
										tixingService.insert(tiXing);
										xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
										jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
										// 发送激光消息(添加备忘)
										XiaoXiFaSong xiaoXiFaSong2 = new XiaoXiFaSong();
										xiaoXiFaSong2.setXiaoXiMingCheng("添加事件");
										xiaoXiFaSong2.setXiaoXiNeiRong(
												user.getYonghuxingming() + "给你添加了一个事件$" + beiWL.getNeirong() + "$");
										xiaoXiFaSong2.setShuJuId(beiWL.getId());
										xiaoXiFaSong2.setShuJuLeiXing(3);
										xiaoXiFaSong2.setFaSongLeiXing(1);
										xiaoXiFaSong2.setShiFouChengGong(0);
										xiaoXiFaSong2.setXueXiaoId(xueXiaoID);
										xiaoXiFaSong2.setFaSongMuBiao(xueSheng.getXueshengid().toString());
										jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong2);
										// 发送提醒消息(添加备忘)
										TiXing tiXing2 = new TiXing();
										// Date date6 = new Date();
										tiXing2.setNeirong(
												user.getYonghuxingming() + "给你添加了一个事件$" + beiWL.getNeirong() + "$");
										tiXing2.setShijian(date);
										tiXing2.setZhuangtai(0);
										tiXing2.setShujuid(beiWL.getId());
										tiXing2.setType(3);
										tiXing2.setJieshourenid(xueSheng.getXueshengid());
										tixingService.insert(tiXing2);
									}
								}
								// 给辅导员发送激光消息(添加备忘)
								XiaoXiFaSong xiaoXiFaSong3 = new XiaoXiFaSong();
								xiaoXiFaSong3.setXiaoXiMingCheng("添加事件");
								xiaoXiFaSong3.setXiaoXiNeiRong("您添加了一个事件$" + beiWL.getNeirong() + "$");
								xiaoXiFaSong3.setShuJuId(beiWL.getId());
								xiaoXiFaSong3.setShuJuLeiXing(3);
								xiaoXiFaSong3.setFaSongLeiXing(1);
								xiaoXiFaSong3.setShiFouChengGong(0);
								xiaoXiFaSong3.setXueXiaoId(xueXiaoID);
								xiaoXiFaSong3.setFaSongMuBiao(user.getYonghuid().toString());
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong3);
								// 给辅导员发送提醒消息(添加备忘)
								TiXing tiXing3 = new TiXing();
								tiXing3.setNeirong("您添加了一个事件$" + beiWL.getNeirong() + "$");
								tiXing3.setShijian(date);
								tiXing3.setZhuangtai(0);
								tiXing3.setShujuid(beiWL.getId());
								tiXing3.setType(3);
								tiXing3.setJieshourenid(user.getYonghuid());
								tixingService.insert(tiXing3);

								// 给辅导员发送激光消息(新增活动)
								XiaoXiFaSong xiaoXiFaSong4 = new XiaoXiFaSong();
								xiaoXiFaSong4.setXiaoXiMingCheng("新增活动");
								xiaoXiFaSong4.setXiaoXiNeiRong("您添加了活动#" + mingcheng + "#");
								xiaoXiFaSong4.setShuJuId(huoDong.getHuodongid());
								xiaoXiFaSong4.setShuJuLeiXing(2);
								xiaoXiFaSong4.setFaSongLeiXing(1);
								xiaoXiFaSong4.setShiFouChengGong(0);
								xiaoXiFaSong4.setXueXiaoId(xueXiaoID);
								xiaoXiFaSong4.setFaSongMuBiao(user.getYonghuid().toString());
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong4);
								// 给辅导员发送提醒消息(新增活动)
								TiXing tiXing4 = new TiXing();
								tiXing4.setNeirong("您添加了活动#" + mingcheng + "#");
								tiXing4.setShijian(date);
								tiXing4.setZhuangtai(0);
								tiXing4.setShujuid(huoDong.getHuodongid());
								tiXing4.setType(2);
								tiXing4.setJieshourenid(user.getYonghuid());
								tixingService.insert(tiXing4);

								// if (qufen.equals("1")) {
								// out.print("<script>alert('新增成功！');</script>");
								// out.print("<script>location='wodericheng_fdy';</script>");
								// } else {
								// out.print("<script>alert('新增成功！');</script>");
								// out.print("<script>location='myhuodong_fdy';</script>");
								// }
								return "success";
							} else {
								// if (qufen.equals("1")) {
								// out.print("<script>alert('新增失败！');</script>");
								// out.print("<script>location='wodericheng_fdy';</script>");
								// } else {
								// out.print("<script>alert('新增失败！');</script>");
								// out.print("<script>location='myhuodong_fdy';</script>");
								// }
							}
						} else {// 只添加活动，不添加备忘
							// 发送激光消息(添加活动)
							XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
							xiaoXiFaSong.setXiaoXiMingCheng("新增活动");
							xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
							xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
							xiaoXiFaSong.setShuJuLeiXing(2);
							xiaoXiFaSong.setFaSongLeiXing(1);
							xiaoXiFaSong.setShiFouChengGong(0);
							xiaoXiFaSong.setXueXiaoId(xueXiaoID);
							// 发送提醒消息(添加活动)
							TiXing tiXing = new TiXing();
							Date date = new Date();
							tiXing.setNeirong(user.getYonghuxingming() + "邀请你参加活动#" + mingcheng + "#");
							tiXing.setShijian(date);
							tiXing.setZhuangtai(0);
							tiXing.setShujuid(huoDong.getHuodongid());
							tiXing.setType(2);
							for (int k = 0; k < banjiids.length; k++) {
								List<XueSheng> xueShengs = xueshengService
										.getAllByBanJiID(Integer.parseInt(banjiids[k]));
								for (XueSheng xueSheng : xueShengs) {
									tiXing.setJieshourenid(xueSheng.getXueshengid());
									tixingService.insert(tiXing);
									xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
									jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);

								}
							}
							// 给辅导员发送激光消息(添加活动)
							XiaoXiFaSong xiaoXiFaSong3 = new XiaoXiFaSong();
							xiaoXiFaSong3.setXiaoXiMingCheng("新增活动");
							xiaoXiFaSong3.setXiaoXiNeiRong("您添加了活动#" + mingcheng + "#");
							xiaoXiFaSong3.setShuJuId(huoDong.getHuodongid());
							xiaoXiFaSong3.setShuJuLeiXing(2);
							xiaoXiFaSong3.setFaSongLeiXing(1);
							xiaoXiFaSong3.setShiFouChengGong(0);
							xiaoXiFaSong3.setXueXiaoId(xueXiaoID);
							xiaoXiFaSong3.setFaSongMuBiao(user.getYonghuid().toString());
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong3);
							// 给辅导员发送提醒消息(添加活动)
							TiXing tiXing3 = new TiXing();
							tiXing3.setNeirong("您添加了活动#" + mingcheng + "#");
							tiXing3.setShijian(date);
							tiXing3.setZhuangtai(0);
							tiXing3.setShujuid(huoDong.getHuodongid());
							tiXing3.setType(2);
							tiXing3.setJieshourenid(user.getYonghuid());
							tixingService.insert(tiXing3);
						}
						// if (qufen.equals("1")) {
						// out.print("<script>alert('新增成功！');</script>");
						// out.print("<script>location='wodericheng_fdy';</script>");
						// } else {
						// out.print("<script>alert('新增成功！');</script>");
						// out.print("<script>location='myhuodong_fdy';</script>");
						// }
						return "success";
					} else {
						// if (qufen.equals("1")) {
						// out.print("<script>alert('新增失败！');</script>");
						// out.print("<script>location='wodericheng_fdy';</script>");
						// } else {
						// out.print("<script>alert('新增失败！');</script>");
						// out.print("<script>location='myhuodong_fdy';</script>");
						// }
					}
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("logout");
			return null;

		}
		return null;
	}

	@RequestMapping(value = "xiugaihuodong_fdy") // 修改活动
	public ModelAndView xiugaihuodong_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String huodongid = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(huodongid)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiWL = beiwlService.getByHuoDongId(huoDong.getHuodongid());
			if (beiWL != null && !"".equals(beiWL)) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
				String beiwlshijian = beiWL.getShijian();
				String kaishishijian = huoDong.getKaishishijian();
				try {
					Date date2 = simpleDateFormat.parse(beiwlshijian);
					long huodongkaishishijian = (simpleDateFormat2.parse(huoDong.getRiqi())).getTime()
							+ Integer.parseInt(kaishishijian.split(":")[0]) * 60 * 60 * 1000
							+ Integer.parseInt(kaishishijian.split(":")[1]) * 60 * 1000;
					long subhour = (huodongkaishishijian - date2.getTime()) / (1000 * 60 * 60);
					mv.addObject("prehour", String.valueOf(subhour).substring(0, 1));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			int i = 0;
			if (huoDong.getTianjiarenid().toString().equals(user.getYonghuid().toString())
					&& huoDong.getTianjiarenleixing() == 1) {
				i = 1;
			}
			if (i == 0) {
				response.sendRedirect("logout");
				return null;
			}
			List<BanJi> banJis = new ArrayList<>();
			List<Map<String, String>> xueShengs = new ArrayList<>();
			FuDaoYuan fuDaoYuan = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid());
			String banjiids = fuDaoYuan.getBanjiid();
			for (int k = 0; k < banjiids.split(",").length; k++) {
				BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(banjiids.split(",")[k]));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String riqi = sdf.format(new Date());
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
				if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
					banJis.add(banJi);
					// 获取辅导员管理班级的全部学生
					List<XueSheng> xueShengs2 = xueshengService
							.getAllByBanJiID(Integer.parseInt(banjiids.split(",")[k]));
					for (XueSheng xueSheng : xueShengs2) {
						Map<String, String> map = new HashMap<>();
						String banji = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
						map.put("banjiid", xueSheng.getBanjiid().toString());
						map.put("banji", banji);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						xueShengs.add(map);
					}
				}
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("huodongid", huodongid);
			// 活动按班级添加
			if (huoDong.getLeixing() == 1) {
				List<String> banjiid = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(huodongid));
				List<Map<String, String>> huoDongBanJis = new ArrayList<>();
				if (banjiid.size() > 0 && banjiid != null) {
					for (String s : banjiid) {
						Map<String, String> map = new HashMap<>();
						BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(s));
						map.put("banjimingcheng", banJi.getBanjimingcheng());
						map.put("banjiid", banJi.getBanjiid().toString());
						huoDongBanJis.add(map);
					}
				}
				mv.addObject("huodongbanji", huoDongBanJis);
			}
			// 按学生添加
			if (huoDong.getLeixing() == 2) {
				List<Map<String, Object>> canYuRens = huodongService
						.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(paramMap);
				List<Map<String, String>> xueshengs = new ArrayList<>();
				if (canYuRens.size() > 0 && canYuRens != null) {
					for (Map<String, Object> mapp : canYuRens) {
						Map<String, String> map = new HashMap<>();
						XueSheng xueSheng = xueshengService
								.selectByPrimaryKey(Integer.parseInt(mapp.get("yaoQingRenID").toString()));
						BanJi banJi = banjiService.selectByPrimaryKey(xueSheng.getBanjiid());
						map.put("banjiid", xueSheng.getBanjiid().toString());
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						map.put("banjimingcheng", banJi.getBanjimingcheng());
						xueshengs.add(map);
					}
				}
				mv.addObject("xueshengs", xueshengs);
			}
			mv.addObject("qufen", qufen);
			mv.addObject("banjis", banJis);
			mv.addObject("xuesheng", xueShengs);
			mv.addObject("huodong", huoDong);
			mv.addObject("beiwl", beiWL);

			mv.setViewName("fudaoyuan/xiugaihuodong_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatehuodong_fdy") // 保存修改活动
	@ResponseBody
	public String saveupdatehuodong_fdy(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String huodongid = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			request.setCharacterEncoding("UTF-8");
			// response.setContentType("text/html; charset=utf-8");
			// PrintWriter out = response.getWriter();
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String shuoming = request.getParameter("shuoming");
			String canyuren = request.getParameter("canyuren");
			String riQi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			String huodongbanji = request.getParameter("huodongbanji");
			String leiXing = request.getParameter("leixing");
			String leiXing2 = request.getParameter("leixing2");
			String isChecked = request.getParameter("ischecked");
			String beiWLRiQi = request.getParameter("booktime");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date riqi = simpleDateFormat.parse(riQi);
			String beiwangluriqi = "";
			if (beiWLRiQi != null && !"".equals(beiWLRiQi)) {
				long kaiShiShiJian = Integer.parseInt(kaishishijian.split(":")[0]) * 60 * 60 * 1000
						+ Integer.parseInt((kaishishijian.split(":")[1])) * 60 * 1000;
				long beiwlriqi = riqi.getTime() + kaiShiShiJian - (Integer.parseInt(beiWLRiQi) * 60 * 60 * 1000);
				Date date2 = new Date();
				date2.setTime(beiwlriqi);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				beiwangluriqi = sdf.format(date2);
			}

			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(huodongid));
			BeiWL beiWL = beiwlService.getByHuoDongId(huoDong.getHuodongid());
			if (isChecked == null || "".equals(isChecked)) {// 修改活动时,复选框未触发,默认为原来状态
				if (beiWL == null || "".equals(beiWL)) {
					isChecked = "0";
				} else {
					isChecked = "1";
				}
			}
			// 以学生为单位，添加活动。
			if (leiXing.equals("按学生添加")) {
				String canyurens[] = canyuren.split(";");
				String newcanyuren = "";
				// String newcanyuren2 = "";
				for (String string : canyurens) {
					newcanyuren += "0," + string + ";";
					// newcanyuren2 += string.split(",")[0] + ",0;";
				}
				canyuren = "1," + user.getYonghuid().toString() + ",1;" + newcanyuren;
				huoDong.setHuodongmingcheng(mingcheng);
				huoDong.setLeixing(2);
				huoDong.setDidian(didian);
				huoDong.setBeizhu(shuoming);
				// huoDong.setCanyuren(canyuren);
				huoDong.setRiqi(riqi);
				huoDong.setKaishishijian(kaishishijian);
				huoDong.setJieshushijian(jieshushijian);
				// huoDong.setBanjiids("");
				int i = huodongService.updateByPrimaryKey(huoDong);
				// map.put("huodongmingcheng", mingcheng);
				// map.put("didian", didian);
				// map.put("beizhu", beizhu);
				// map.put("tianjiaren", user.getYonghuid().toString());
				// map.put("canyuren", canyuren);
				// map.put("riqi", riQi);
				// map.put("kaishishijian", kaishishijian);
				// map.put("jieshushijian", jieshushijian);
				// int i = huodongService.insert(map);
				// HuoDong huoDong =
				// huodongService.selectByPrimaryKey(Integer.parseInt(map.get("huodongid")));
				if (i != 0) {
					// 将之前与之先关的班级或人删除，再加进来
					map.put("huodongid", huodongid);
					huodongService.delete_huodongbanji(map);
					huodongService.delete_huodongren(map);
					for (String s : canyurens) {
						if (s.split(",")[0] != null && !"".equals(s.split(",")[0])) {
							map.put("renid", s.split(",")[0]);
							map.put("renleixing", "0");
							huodongService.insert_huodongren(map);
						}
					}
					String canyurens2[] = canyuren.split(";");
					int j = 0;
					if (isChecked.equals("1")) {// 添加或者修改备忘
						BeiWL newbeiWL = new BeiWL();
						if (beiWL != null && !"".equals(beiWL)) {// 备忘录不空，修改备忘。
							beiWL.setLeixing(2);
							beiWL.setHuizhi(1);
							beiWL.setHuodongid(huoDong.getHuodongid());
							beiWL.setNeirong(huoDong.getHuodongmingcheng());
							// beiWL.setJieshouren(newcanyuren2);
							beiWL.setShijian(beiwangluriqi);
							beiWL.setUserid(user.getYonghuid().toString());
							// beiWL.setBanjiids("");
							j = beiwlService.updateShiJian(beiWL);
							if (j != 0) {
								map.put("beiwlid", beiWL.getId().toString());
								beiwlService.delete_beiwanglubanji(map);
								beiwlService.delete_beiwangluren(map);
								for (String s : canyurens) {
									if (s.split(",")[0] != null && !"".equals(s.split(",")[0])) {
										map.put("jieshourenid", s.split(",")[0]);
										map.put("leixing", "0");
										beiwlService.insert_beiwangluren(map);
									}
								}
							}
						} else {// 新增备忘
							newbeiWL.setLeixing(2);
							newbeiWL.setHuizhi(1);
							newbeiWL.setHuodongid(huoDong.getHuodongid());
							newbeiWL.setNeirong(huoDong.getHuodongmingcheng());
							// newbeiWL.setJieshouren(newcanyuren2);
							newbeiWL.setShijian(beiwangluriqi);
							newbeiWL.setUserid(user.getYonghuid().toString());
							j = beiwlService.insertforxuesheng(newbeiWL);
							if (j != 0) {
								map.put("beiwlid", newbeiWL.getId().toString());
								for (String s : canyurens) {
									if (s.split(",")[0] != null && !"".equals(s.split(",")[0])) {
										map.put("jieshourenid", s.split(",")[0]);
										map.put("leixing", "0");
										beiwlService.insert_beiwangluren(map);
									}
								}
							}
						}

						if (j != 0) {
							// 发送激光消息(修改活动)
							XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
							xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
							xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
							xiaoXiFaSong.setShuJuLeiXing(2);
							xiaoXiFaSong.setFaSongLeiXing(1);
							xiaoXiFaSong.setShiFouChengGong(0);
							xiaoXiFaSong.setXueXiaoId(xueXiaoID);
							// 发送提醒消息(修改活动)
							TiXing tiXing = new TiXing();
							Date date = new Date();
							tiXing.setShijian(date);
							tiXing.setZhuangtai(0);
							tiXing.setShujuid(huoDong.getHuodongid());
							tiXing.setType(2);
							// 发送激光消息(新增或修改备忘)
							XiaoXiFaSong xiaoXiFaSong2 = new XiaoXiFaSong();
							xiaoXiFaSong2.setShuJuLeiXing(3);
							xiaoXiFaSong2.setFaSongLeiXing(1);
							xiaoXiFaSong2.setShiFouChengGong(0);
							xiaoXiFaSong2.setXueXiaoId(xueXiaoID);
							// 发送提醒消息(新增或修改备忘)
							TiXing tiXing2 = new TiXing();
							tiXing2.setShijian(date);
							tiXing2.setZhuangtai(0);
							tiXing2.setType(3);
							for (String string : canyurens2) {
								String cyr[] = string.split(",");
								if (cyr[0].equals("1")) {// 用户身份为辅导员
									tiXing.setNeirong("您修改了活动#" + mingcheng + "#");
									xiaoXiFaSong.setXiaoXiNeiRong("您修改了活动#" + mingcheng + "#");
									if (beiWL == null || "".equals(beiWL)) {// 新增备忘
										xiaoXiFaSong2.setShuJuId(newbeiWL.getId());
										tiXing2.setShujuid(newbeiWL.getId());
										xiaoXiFaSong2.setXiaoXiMingCheng("添加事件");
										tiXing2.setNeirong("您添加了一个事件$" + newbeiWL.getNeirong() + "$");
										xiaoXiFaSong2.setXiaoXiNeiRong("您添加了一个事件$" + newbeiWL.getNeirong() + "$");
									} else {// 修改备忘
										xiaoXiFaSong2.setShuJuId(beiWL.getId());
										tiXing2.setShujuid(beiWL.getId());
										xiaoXiFaSong2.setXiaoXiMingCheng("修改事件");
										tiXing2.setNeirong("您修改了事件$" + beiWL.getNeirong() + "$");
										xiaoXiFaSong2.setXiaoXiNeiRong("您修改了事件$" + beiWL.getNeirong() + "$");
									}

								} else {// 用户身份为学生
									if (beiWL == null || "".equals(beiWL)) {// 新增备忘
										tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
										tiXing2.setNeirong(
												user.getYonghuxingming() + "给你添加了一个事件$" + newbeiWL.getNeirong() + "$");
										xiaoXiFaSong.setXiaoXiNeiRong(
												user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
										xiaoXiFaSong2.setXiaoXiNeiRong(
												user.getYonghuxingming() + "给你添加了一个事件$" + newbeiWL.getNeirong() + "$");
									} else {// 修改备忘
										tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
										tiXing2.setNeirong(
												user.getYonghuxingming() + "修改了事件$" + beiWL.getNeirong() + "$");
										xiaoXiFaSong.setXiaoXiNeiRong(
												user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
										xiaoXiFaSong2.setXiaoXiNeiRong(
												user.getYonghuxingming() + "修改了事件$" + beiWL.getNeirong() + "$");
									}

								}
								tiXing.setJieshourenid(Integer.parseInt(cyr[1]));
								tixingService.insert(tiXing);
								xiaoXiFaSong.setFaSongMuBiao(cyr[1]);
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
								tiXing2.setJieshourenid(Integer.parseInt(cyr[1]));
								tixingService.insert(tiXing2);
								xiaoXiFaSong2.setFaSongMuBiao(cyr[1]);
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong2);
							}
							// if (qufen.equals("1")) {
							// out.print("<script>alert('修改成功！');</script>");
							// out.print("<script>location='wodericheng_fdy';</script>");
							// } else {
							// out.print("<script>alert('修改成功！');</script>");
							// out.print("<script>location='myhuodong_fdy';</script>");
							// }
							return "success";
						} else {
							// if (qufen.equals("1")) {
							// out.print("<script>alert('修改失败！');</script>");
							// out.print("<script>location='wodericheng_fdy';</script>");
							// } else {
							// out.print("<script>alert('修改失败！');</script>");
							// out.print("<script>location='myhuodong_fdy';</script>");
							// }
						}
					} else {// 只修改活动，或者修改活动的同时删除附加的备忘
						if (beiWL != null && !"".equals(beiWL)) {// 删除备忘
							map.put("beiwlid", beiWL.getId().toString());
							beiwlService.delete_beiwlhuizhi(map);
							beiwlService.delete_beiwanglubanji(map);
							beiwlService.delete_beiwangluren(map);
							int k = beiwlService.deleteByPrimaryKey(beiWL.getId());
							if (k != 0) {
								// 发送激光消息(修改活动)
								XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
								xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
								xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
								xiaoXiFaSong.setShuJuLeiXing(2);
								xiaoXiFaSong.setFaSongLeiXing(1);
								xiaoXiFaSong.setShiFouChengGong(0);
								xiaoXiFaSong.setXueXiaoId(xueXiaoID);
								// 发送提醒消息(修改活动)
								TiXing tiXing = new TiXing();
								Date date = new Date();
								tiXing.setShijian(date);
								tiXing.setZhuangtai(0);
								tiXing.setShujuid(huoDong.getHuodongid());
								tiXing.setType(2);

								// 发送激光消息(删除备忘)
								XiaoXiFaSong xiaoXiFaSong2 = new XiaoXiFaSong();
								xiaoXiFaSong.setXiaoXiMingCheng("删除事件!");
								xiaoXiFaSong2.setShuJuId(beiWL.getId());
								xiaoXiFaSong2.setShuJuLeiXing(3);
								xiaoXiFaSong2.setFaSongLeiXing(1);
								xiaoXiFaSong2.setShiFouChengGong(0);
								xiaoXiFaSong2.setXueXiaoId(xueXiaoID);
								// 发送提醒消息(删除备忘)
								TiXing tiXing2 = new TiXing();
								tiXing2.setShijian(date);
								tiXing2.setZhuangtai(0);
								tiXing2.setShujuid(beiWL.getId());
								tiXing2.setType(3);
								for (String string : canyurens2) {
									String cyr[] = string.split(",");
									if (cyr[0].equals("1")) {
										tiXing.setNeirong("您修改了活动#" + mingcheng + "#");
										xiaoXiFaSong.setXiaoXiNeiRong("您修改了活动#" + mingcheng + "#");
										tiXing2.setNeirong("您删除了事件$" + mingcheng + "$");
										xiaoXiFaSong2.setXiaoXiNeiRong("您删除了事件$" + mingcheng + "$");
									} else {
										tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
										xiaoXiFaSong.setXiaoXiNeiRong(
												user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
										tiXing2.setNeirong(user.getYonghuxingming() + "删除了事件$" + mingcheng + "$");
										xiaoXiFaSong2.setXiaoXiNeiRong(
												user.getYonghuxingming() + "删除了事件$" + mingcheng + "$");
									}
									tiXing.setJieshourenid(Integer.parseInt(cyr[1]));
									tixingService.insert(tiXing);
									xiaoXiFaSong.setFaSongMuBiao(cyr[1]);
									jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
									tiXing2.setJieshourenid(Integer.parseInt(cyr[1]));
									tixingService.insert(tiXing2);
									xiaoXiFaSong2.setFaSongMuBiao(cyr[1]);
									jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong2);
								}
								// if (qufen.equals("1")) {
								// out.print("<script>alert('修改成功！');</script>");
								// out.print("<script>location='wodericheng_fdy';</script>");
								// } else {
								// out.print("<script>alert('修改成功！');</script>");
								// out.print("<script>location='myhuodong_fdy';</script>");
								// }
								return "success";
							} else {
								// if (qufen.equals("1")) {
								// out.print("<script>alert('修改失败！');</script>");
								// out.print("<script>location='wodericheng_fdy';</script>");
								// } else {
								// out.print("<script>alert('修改失败！');</script>");
								// out.print("<script>location='myhuodong_fdy';</script>");
								// }
							}
						} else {// 只修改活动,活动没有附加的备忘
							// 发送激光消息(修改活动)
							XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
							xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
							xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
							xiaoXiFaSong.setShuJuLeiXing(2);
							xiaoXiFaSong.setFaSongLeiXing(1);
							xiaoXiFaSong.setShiFouChengGong(0);
							xiaoXiFaSong.setXueXiaoId(xueXiaoID);
							// 发送提醒消息(修改活动)
							TiXing tiXing = new TiXing();
							Date date = new Date();
							tiXing.setShijian(date);
							tiXing.setZhuangtai(0);
							tiXing.setShujuid(huoDong.getHuodongid());
							tiXing.setType(2);
							for (String string : canyurens2) {
								String cyr[] = string.split(",");
								if (cyr[0].equals("1")) {// 用户身份为辅导员
									tiXing.setNeirong("您修改了活动#" + mingcheng + "#");
									xiaoXiFaSong.setXiaoXiNeiRong("您修改了活动#" + mingcheng + "#");

								} else {// 用户身份为学生
									tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
									xiaoXiFaSong
											.setXiaoXiNeiRong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");

								}
								tiXing.setJieshourenid(Integer.parseInt(cyr[1]));
								tixingService.insert(tiXing);
								xiaoXiFaSong.setFaSongMuBiao(cyr[1]);
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);

							}
						}
						// if (qufen.equals("1")) {
						// out.print("<script>alert('修改成功！');</script>");
						// out.print("<script>location='wodericheng_fdy';</script>");
						// } else {
						// out.print("<script>alert('修改成功！');</script>");
						// out.print("<script>location='myhuodong_fdy';</script>");
						// }
						return "success";
					}
				} else {
					// if (qufen.equals("1")) {
					// out.print("<script>alert('修改失败！');</script>");
					// out.print("<script>location='wodericheng_fdy';</script>");
					// } else {
					// out.print("<script>alert('修改失败！');</script>");
					// out.print("<script>location='myhuodong_fdy';</script>");
					// }
				}
			}
			// 以班级为单位添加活动
			else if (leiXing2.equals("按班级添加")) {
				// 把参与人的类型加上，此处全为0——学生
				if (huodongbanji != null && !"".equals(huodongbanji)) {
					String canyuren2 = "1," + user.getYonghuid().toString() + ",1;";
					huoDong.setHuodongmingcheng(mingcheng);
					huoDong.setDidian(didian);
					huoDong.setShuoming(shuoming);
					// huoDong.setCanyuren(canyuren2);
					huoDong.setRiqi(riqi);
					huoDong.setLeixing(1);
					huoDong.setKaishishijian(kaishishijian);
					huoDong.setJieshushijian(jieshushijian);
					// huoDong.setBanjiids(huodongbanji);
					int i = huodongService.updateByPrimaryKey(huoDong);
					if (i != 0) {
						String banjiids[] = huodongbanji.split(",");
						map.put("huodongid", huoDong.getHuodongid().toString());
						huodongService.delete_huodongbanji(map);
						huodongService.delete_huodongren(map);
						for (String s : banjiids) {
							map.put("banjiid", s);
							huodongService.insert_huodongbanji(map);
						}
						int j = 0;
						if (isChecked.equals("1")) {// 修改活动的同时,添加,修改,或者删除附加备忘
							BeiWL newbeiWL = new BeiWL();
							if (beiWL != null && !"".equals(beiWL)) {// 活动附加有备忘，修改备忘
								beiWL.setLeixing(1);
								beiWL.setHuizhi(1);
								beiWL.setHuodongid(huoDong.getHuodongid());
								beiWL.setNeirong(huoDong.getHuodongmingcheng());
								// beiWL.setBanjiids(huodongbanji);
								beiWL.setShijian(beiwangluriqi);
								beiWL.setUserid(user.getYonghuid().toString());
								// beiWL.setJieshouren("");
								j = beiwlService.updateShiJian(beiWL);
								if (j != 0) {
									map.put("beiwlid", beiWL.getId().toString());
									beiwlService.delete_beiwanglubanji(map);
									beiwlService.delete_beiwangluren(map);
									for (String s : banjiids) {
										map.put("banjiid", s);
										beiwlService.insert_beiwanglubanji(map);
									}
								}
							} else {// 修改活动的同时,为活动添加备忘
								newbeiWL.setLeixing(1);
								newbeiWL.setHuizhi(1);
								newbeiWL.setHuodongid(huoDong.getHuodongid());
								newbeiWL.setNeirong(huoDong.getHuodongmingcheng());
								// newbeiWL.setBanjiids(huodongbanji);
								newbeiWL.setShijian(beiwangluriqi);
								newbeiWL.setUserid(user.getYonghuid().toString());
								j = beiwlService.insertforbanji(newbeiWL);
								if (j != 0) {
									map.put("beiwlid", newbeiWL.getId().toString());
									for (String s : banjiids) {
										map.put("banjiid", s);
										beiwlService.insert_beiwanglubanji(map);
									}
								}
							}

							if (j != 0) {
								// 发送激光消息(修改活动)
								XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
								xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
								xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
								xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
								xiaoXiFaSong.setShuJuLeiXing(2);
								xiaoXiFaSong.setFaSongLeiXing(1);
								xiaoXiFaSong.setShiFouChengGong(0);
								xiaoXiFaSong.setXueXiaoId(xueXiaoID);
								// 发送提醒消息(修改活动)
								TiXing tiXing = new TiXing();
								Date date = new Date();
								tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
								tiXing.setShijian(date);
								tiXing.setZhuangtai(0);
								tiXing.setShujuid(huoDong.getHuodongid());
								tiXing.setType(2);
								for (int k = 0; k < banjiids.length; k++) {
									List<XueSheng> xueShengs = xueshengService
											.getAllByBanJiID(Integer.parseInt(banjiids[k]));
									for (XueSheng xueSheng : xueShengs) {// 用户身份为学生
										tiXing.setJieshourenid(xueSheng.getXueshengid());
										tixingService.insert(tiXing);
										xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
										jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);

										if (beiWL == null || "".equals(beiWL)) {
											// 发送激光消息(新增备忘)
											XiaoXiFaSong xiaoXiFaSong2 = new XiaoXiFaSong();
											xiaoXiFaSong2.setXiaoXiMingCheng("添加事件");
											xiaoXiFaSong2.setXiaoXiNeiRong(user.getYonghuxingming() + "给你添加了一个事件$"
													+ newbeiWL.getNeirong() + "$");
											xiaoXiFaSong2.setShuJuId(newbeiWL.getId());
											xiaoXiFaSong2.setShuJuLeiXing(3);
											xiaoXiFaSong2.setFaSongLeiXing(1);
											xiaoXiFaSong2.setShiFouChengGong(0);
											xiaoXiFaSong2.setXueXiaoId(xueXiaoID);
											xiaoXiFaSong2.setFaSongMuBiao(xueSheng.getXueshengid().toString());
											jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong2);
											// 发送提醒消息(新增备忘)
											TiXing tiXing2 = new TiXing();
											// Date date6 = new Date();
											tiXing2.setNeirong(user.getYonghuxingming() + "给你添加了一个事件$"
													+ newbeiWL.getNeirong() + "$");
											tiXing2.setShijian(date);
											tiXing2.setZhuangtai(0);
											tiXing2.setShujuid(newbeiWL.getId());
											tiXing2.setType(3);
											tiXing2.setJieshourenid(xueSheng.getXueshengid());
											tixingService.insert(tiXing2);

										}
										// 修改备忘
										else {
											// 发送激光消息(修改备忘)
											XiaoXiFaSong xiaoXiFaSong2 = new XiaoXiFaSong();
											xiaoXiFaSong2.setXiaoXiMingCheng("修改事件");
											xiaoXiFaSong2.setXiaoXiNeiRong(
													user.getYonghuxingming() + "修改了事件$" + beiWL.getNeirong() + "$");
											xiaoXiFaSong2.setShuJuId(beiWL.getId());
											xiaoXiFaSong2.setShuJuLeiXing(3);
											xiaoXiFaSong2.setFaSongLeiXing(1);
											xiaoXiFaSong2.setShiFouChengGong(0);
											xiaoXiFaSong2.setXueXiaoId(xueXiaoID);
											xiaoXiFaSong2.setFaSongMuBiao(xueSheng.getXueshengid().toString());
											jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong2);
											// 发送提醒消息(修改备忘)
											TiXing tiXing2 = new TiXing();
											// Date date6 = new Date();
											tiXing2.setNeirong(
													user.getYonghuxingming() + "修改了事件$" + beiWL.getNeirong() + "$");
											tiXing2.setShijian(date);
											tiXing2.setZhuangtai(0);
											tiXing2.setShujuid(beiWL.getId());
											tiXing2.setType(3);
											tiXing2.setJieshourenid(xueSheng.getXueshengid());
											tixingService.insert(tiXing2);
										}

									}
								}

								if (beiWL == null || "".equals(beiWL)) {
									// 给辅导员发送激光消息
									XiaoXiFaSong xiaoXiFaSong3 = new XiaoXiFaSong();
									xiaoXiFaSong3.setXiaoXiMingCheng("添加事件");
									xiaoXiFaSong3.setXiaoXiNeiRong("您添加了一个事件$" + newbeiWL.getNeirong() + "$");
									xiaoXiFaSong3.setShuJuId(newbeiWL.getId());
									xiaoXiFaSong3.setShuJuLeiXing(3);
									xiaoXiFaSong3.setFaSongLeiXing(1);
									xiaoXiFaSong3.setShiFouChengGong(0);
									xiaoXiFaSong3.setXueXiaoId(xueXiaoID);
									xiaoXiFaSong3.setFaSongMuBiao(user.getYonghuid().toString());
									jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong3);
									// 给辅导员发送提醒消息
									TiXing tiXing3 = new TiXing();
									tiXing3.setNeirong("您添加了一个事件$" + newbeiWL.getNeirong() + "$");
									tiXing3.setShijian(date);
									tiXing3.setZhuangtai(0);
									tiXing3.setShujuid(newbeiWL.getId());
									tiXing3.setType(3);
									tiXing3.setJieshourenid(user.getYonghuid());
									tixingService.insert(tiXing3);

									// 给辅导员发送激光消息
									XiaoXiFaSong xiaoXiFaSong4 = new XiaoXiFaSong();
									xiaoXiFaSong4.setXiaoXiMingCheng("修改活动");
									xiaoXiFaSong4.setXiaoXiNeiRong("您修改了活动#" + mingcheng + "#");
									xiaoXiFaSong4.setShuJuId(huoDong.getHuodongid());
									xiaoXiFaSong4.setShuJuLeiXing(2);
									xiaoXiFaSong4.setFaSongLeiXing(1);
									xiaoXiFaSong4.setShiFouChengGong(0);
									xiaoXiFaSong4.setXueXiaoId(xueXiaoID);
									xiaoXiFaSong4.setFaSongMuBiao(user.getYonghuid().toString());
									jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong4);
									// 给辅导员发送提醒消息
									TiXing tiXing4 = new TiXing();
									tiXing4.setNeirong("您修改了活动#" + mingcheng + "#");
									tiXing4.setShijian(date);
									tiXing4.setZhuangtai(0);
									tiXing4.setShujuid(huoDong.getHuodongid());
									tiXing4.setType(2);
									tiXing4.setJieshourenid(user.getYonghuid());
									tixingService.insert(tiXing4);
								} else {
									// 给辅导员发送激光消息
									XiaoXiFaSong xiaoXiFaSong3 = new XiaoXiFaSong();
									xiaoXiFaSong3.setXiaoXiMingCheng("修改事件");
									xiaoXiFaSong3.setXiaoXiNeiRong("您修改了事件$" + beiWL.getNeirong() + "$");
									xiaoXiFaSong3.setShuJuId(beiWL.getId());
									xiaoXiFaSong3.setShuJuLeiXing(3);
									xiaoXiFaSong3.setFaSongLeiXing(1);
									xiaoXiFaSong3.setShiFouChengGong(0);
									xiaoXiFaSong3.setXueXiaoId(xueXiaoID);
									xiaoXiFaSong3.setFaSongMuBiao(user.getYonghuid().toString());
									jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong3);
									// 给辅导员发送提醒消息
									TiXing tiXing3 = new TiXing();
									tiXing3.setNeirong("您修改了事件$" + beiWL.getNeirong() + "$");
									tiXing3.setShijian(date);
									tiXing3.setZhuangtai(0);
									tiXing3.setShujuid(beiWL.getId());
									tiXing3.setType(3);
									tiXing3.setJieshourenid(user.getYonghuid());
									tixingService.insert(tiXing3);

									// 给辅导员发送激光消息
									XiaoXiFaSong xiaoXiFaSong4 = new XiaoXiFaSong();
									xiaoXiFaSong4.setXiaoXiMingCheng("修改活动");
									xiaoXiFaSong4.setXiaoXiNeiRong("您修改了活动#" + mingcheng + "#");
									xiaoXiFaSong4.setShuJuId(huoDong.getHuodongid());
									xiaoXiFaSong4.setShuJuLeiXing(2);
									xiaoXiFaSong4.setFaSongLeiXing(1);
									xiaoXiFaSong4.setShiFouChengGong(0);
									xiaoXiFaSong4.setXueXiaoId(xueXiaoID);
									xiaoXiFaSong4.setFaSongMuBiao(user.getYonghuid().toString());
									jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong4);
									// 给辅导员发送提醒消息
									TiXing tiXing4 = new TiXing();
									tiXing4.setNeirong("您修改了活动#" + mingcheng + "#");
									tiXing4.setShijian(date);
									tiXing4.setZhuangtai(0);
									tiXing4.setShujuid(huoDong.getHuodongid());
									tiXing4.setType(2);
									tiXing4.setJieshourenid(user.getYonghuid());
									tixingService.insert(tiXing4);
								}
								// if (qufen.equals("1")) {
								// out.print("<script>alert('修改成功！');</script>");
								// out.print("<script>location='wodericheng_fdy';</script>");
								// } else {
								// out.print("<script>alert('修改成功！');</script>");
								// out.print("<script>location='myhuodong_fdy';</script>");
								// }
								return "success";
							} else {
								// if (qufen.equals("1")) {
								// out.print("<script>alert('修改失败！');</script>");
								// out.print("<script>location='wodericheng_fdy';</script>");
								// } else {
								// out.print("<script>alert('修改失败！');</script>");
								// out.print("<script>location='myhuodong_fdy';</script>");
								// }
							}
						} else {// 修改活动，或者修改活动的同时删除备忘
							if (beiWL == null || "".equals(beiWL)) {// 只修改活动
								// 发送激光消息(修改活动)
								XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
								xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
								xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
								xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
								xiaoXiFaSong.setShuJuLeiXing(2);
								xiaoXiFaSong.setFaSongLeiXing(1);
								xiaoXiFaSong.setShiFouChengGong(0);
								xiaoXiFaSong.setXueXiaoId(xueXiaoID);
								// 发送提醒消息(修改活动)
								TiXing tiXing = new TiXing();
								Date date = new Date();
								tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
								tiXing.setShijian(date);
								tiXing.setZhuangtai(0);
								tiXing.setShujuid(huoDong.getHuodongid());
								tiXing.setType(2);
								for (int k = 0; k < banjiids.length; k++) {
									List<XueSheng> xueShengs = xueshengService
											.getAllByBanJiID(Integer.parseInt(banjiids[k]));
									for (XueSheng xueSheng : xueShengs) {
										tiXing.setJieshourenid(xueSheng.getXueshengid());
										tixingService.insert(tiXing);
										xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
										jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);

									}
								}
								// 给辅导员发送激光消息(修改活动)
								XiaoXiFaSong xiaoXiFaSong3 = new XiaoXiFaSong();
								xiaoXiFaSong3.setXiaoXiMingCheng("修改活动");
								xiaoXiFaSong3.setXiaoXiNeiRong("您修改了活动#" + mingcheng + "#");
								xiaoXiFaSong3.setShuJuId(huoDong.getHuodongid());
								xiaoXiFaSong3.setShuJuLeiXing(2);
								xiaoXiFaSong3.setFaSongLeiXing(1);
								xiaoXiFaSong3.setShiFouChengGong(0);
								xiaoXiFaSong3.setXueXiaoId(xueXiaoID);
								xiaoXiFaSong3.setFaSongMuBiao(user.getYonghuid().toString());
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong3);
								// 给辅导员发送提醒消息(修改活动)
								TiXing tiXing3 = new TiXing();
								tiXing3.setNeirong("您修改了活动#" + mingcheng + "#");
								tiXing3.setShijian(date);
								tiXing3.setZhuangtai(0);
								tiXing3.setShujuid(huoDong.getHuodongid());
								tiXing3.setType(2);
								tiXing3.setJieshourenid(user.getYonghuid());
								tixingService.insert(tiXing3);
							} else {// 删除备忘
								map.put("beiwlid", beiWL.getId().toString());
								beiwlService.delete_beiwanglubanji(map);
								beiwlService.delete_beiwangluren(map);
								beiwlService.delete_beiwlhuizhi(map);
								int k = beiwlService.deleteByPrimaryKey(beiWL.getId());
								// 发送激光消息(修改活动)
								XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
								xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
								xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
								xiaoXiFaSong.setShuJuId(huoDong.getHuodongid());
								xiaoXiFaSong.setShuJuLeiXing(2);
								xiaoXiFaSong.setFaSongLeiXing(1);
								xiaoXiFaSong.setShiFouChengGong(0);
								xiaoXiFaSong.setXueXiaoId(xueXiaoID);
								// 发送提醒消息(修改活动)
								TiXing tiXing = new TiXing();
								Date date = new Date();
								tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
								tiXing.setShijian(date);
								tiXing.setZhuangtai(0);
								tiXing.setShujuid(huoDong.getHuodongid());
								tiXing.setType(2);
								// 发送激光消息(删除备忘)
								XiaoXiFaSong xiaoXiFaSong2 = new XiaoXiFaSong();
								xiaoXiFaSong.setXiaoXiMingCheng("删除事件!");
								xiaoXiFaSong2.setShuJuId(beiWL.getId());
								xiaoXiFaSong2.setShuJuLeiXing(3);
								xiaoXiFaSong2.setFaSongLeiXing(1);
								xiaoXiFaSong2.setShiFouChengGong(0);
								xiaoXiFaSong2.setXueXiaoId(xueXiaoID);
								// 发送提醒消息(删除备忘)
								TiXing tiXing2 = new TiXing();
								tiXing2.setShijian(date);
								tiXing2.setZhuangtai(0);
								tiXing2.setShujuid(beiWL.getId());
								tiXing2.setType(3);
								for (int m = 0; m < banjiids.length; m++) {
									List<XueSheng> xueShengs = xueshengService
											.getAllByBanJiID(Integer.parseInt(banjiids[m]));
									for (XueSheng xueSheng : xueShengs) {
										tiXing.setNeirong(user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
										xiaoXiFaSong.setXiaoXiNeiRong(
												user.getYonghuxingming() + "修改了活动#" + mingcheng + "#");
										tiXing2.setNeirong(user.getYonghuxingming() + "删除了事件$" + mingcheng + "$");
										xiaoXiFaSong2.setXiaoXiNeiRong(
												user.getYonghuxingming() + "删除了事件$" + mingcheng + "$");
										tiXing.setJieshourenid(xueSheng.getXueshengid());
										tiXing2.setJieshourenid(xueSheng.getXueshengid());
										tixingService.insert(tiXing);
										xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
										xiaoXiFaSong2.setFaSongMuBiao(xueSheng.getXueshengid().toString());
										jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
										tixingService.insert(tiXing2);
										jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong2);

									}
								}
								// 给辅导员发送激光消息(修改活动)
								XiaoXiFaSong xiaoXiFaSong3 = new XiaoXiFaSong();
								xiaoXiFaSong3.setXiaoXiMingCheng("修改活动");
								xiaoXiFaSong3.setXiaoXiNeiRong("您修改了活动#" + mingcheng + "#");
								xiaoXiFaSong3.setShuJuId(huoDong.getHuodongid());
								xiaoXiFaSong3.setShuJuLeiXing(2);
								xiaoXiFaSong3.setFaSongLeiXing(1);
								xiaoXiFaSong3.setShiFouChengGong(0);
								xiaoXiFaSong3.setXueXiaoId(xueXiaoID);
								xiaoXiFaSong3.setFaSongMuBiao(user.getYonghuid().toString());
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong3);
								// 给辅导员发送提醒消息(修改活动)
								TiXing tiXing3 = new TiXing();
								tiXing3.setNeirong("您修改了活动#" + mingcheng + "#");
								tiXing3.setShijian(date);
								tiXing3.setZhuangtai(0);
								tiXing3.setShujuid(huoDong.getHuodongid());
								tiXing3.setType(2);
								tiXing3.setJieshourenid(user.getYonghuid());
								tixingService.insert(tiXing3);

								// 发送激光消息(删除备忘)
								XiaoXiFaSong xiaoXiFaSong4 = new XiaoXiFaSong();
								xiaoXiFaSong4.setXiaoXiMingCheng("删除事件!");
								xiaoXiFaSong3.setXiaoXiNeiRong("您删除了事件$" + mingcheng + "$");
								xiaoXiFaSong4.setShuJuId(beiWL.getId());
								xiaoXiFaSong4.setShuJuLeiXing(3);
								xiaoXiFaSong4.setFaSongLeiXing(1);
								xiaoXiFaSong4.setShiFouChengGong(0);
								xiaoXiFaSong4.setXueXiaoId(xueXiaoID);
								xiaoXiFaSong4.setFaSongMuBiao(user.getYonghuid().toString());
								jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong4);
								// 发送提醒消息(删除备忘)
								TiXing tiXing4 = new TiXing();
								tiXing4.setNeirong("您删除了事件$" + mingcheng + "$");
								tiXing4.setShijian(date);
								tiXing4.setZhuangtai(0);
								tiXing4.setShujuid(beiWL.getId());
								tiXing4.setType(3);
								tiXing4.setJieshourenid(user.getYonghuid());
								tixingService.insert(tiXing4);
							}
						}
						// if (qufen.equals("1")) {
						// out.print("<script>alert('修改成功！');</script>");
						// out.print("<script>location='wodericheng_fdy';</script>");
						// } else {
						// out.print("<script>alert('修改成功！');</script>");
						// out.print("<script>location='myhuodong_fdy';</script>");
						// }
						return "success";
					} else {
						// if (qufen.equals("1")) {
						// out.print("<script>alert('修改失败！');</script>");
						// out.print("<script>location='wodericheng_fdy';</script>");
						// } else {
						// out.print("<script>alert('修改失败！');</script>");
						// out.print("<script>location='myhuodong_fdy';</script>");
						// }
					}
				}
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
			return null;
		}
		return null;
	}

	@RequestMapping(value = "delhuodong_fdy") // 拒绝活动——将参与状态改为2，参与人还存在
	@ResponseBody
	public String delhuodong_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		// PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			String my = request.getParameter("my");
			if (!Util.isNumeric(id)) {
				// response.sendRedirect("logout");
				return null;
			}
			YongHu user = (YongHu) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				// response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "1");
			List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (canYuRens != null && canYuRens.size() > 0) {
				try {
					huodongService.delete_huodongcanyuren(map);
					huodongService.insert_huodongjujueren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			} else {
				try {
					huodongService.insert_huodongjujueren(map);
					sign = 1;
				} catch (Exception e) {
					e.printStackTrace();
					sign = 0;
				}
			}
			if (sign == 1) {
				return "success";
				// if (qufen != null && !"".equals(qufen) && qufen.equals("1"))
				// {
				// out.print("<script>alert('拒绝成功！');</script>");
				// out.print("<script>location='wodericheng_fdy';</script>");
				// } else if (my != null && !"".equals(my) && my.equals("1")) {
				// out.print("<script>alert('拒绝成功！');</script>");
				// out.print("<script>location='myhuodong_fdy';</script>");
				// } else {
				// out.print("<script>alert('拒绝成功！');</script>");
				// out.print("<script>location='newhuodong_fdy';</script>");
				// }
			} else {
				// if (qufen != null && !"".equals(qufen) && qufen.equals("1"))
				// {
				// out.print("<script>alert('拒绝失败！');</script>");
				// out.print("<script>location='wodericheng_fdy';</script>");
				// } else if (my != null && !"".equals(my) && my.equals("1")) {
				// out.print("<script>alert('拒绝失败！');</script>");
				// out.print("<script>location='myhuodong_fdy';</script>");
				// } else {
				// out.print("<script>alert('拒绝失败！');</script>");
				// out.print("<script>location='newhuodong_fdy';</script>");
				// }
				return "fail";
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "newhuodong_fdy") // 待参加活动
	public ModelAndView newhuodong_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			// String canyuren1 = "1," + user.getYonghuid().toString() + ",0;%";
			// String canyuren2 = "1," + user.getYonghuid().toString() + ",2;%";
			// String canyuren3 = "%;1," + user.getYonghuid().toString() +
			// ",0;%";
			// String canyuren4 = "%;1," + user.getYonghuid().toString() +
			// ",2;%";
			// List<HuoDong> huodongs =
			// huodongService.getAllBycanYuRen2(canyuren1, canyuren2, canyuren3,
			// canyuren4);
			Map<String, String> map = new HashMap<>();
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "1");
			List<HuoDong> huodongs = huodongService.getAllByRenIDAndRenLeiXing(map);
			List<HuoDong> huoDongs = new ArrayList<>();
			for (HuoDong huoDong : huodongs) {
				map.put("huodongid", huoDong.getHuodongid().toString());
				List<Map<String, Object>> juJueRen = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				String tianjiarenid = huoDong.getTianjiarenid().toString();
				if (juJueRen != null && juJueRen.size() > 0) {
					YongHu tianjiaren = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
					String faqiren = "";
					if (tianjiaren == null) {
						faqiren = xueshengService.getUserById(Integer.parseInt(tianjiarenid)).getXingming();
					} else {
						faqiren = tianjiaren.getYonghuxingming();
					}
					huoDong.setFaqiren(faqiren);
					huoDong.setZhuangtai(2);
					huoDongs.add(huoDong);
				} else {
					if (!tianjiarenid.equals(user.getYonghuid().toString())) {
						List<Map<String, Object>> canYuRen = huodongService
								.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
						if (canYuRen == null || canYuRen.size() <= 0) {
							YongHu tianjiaren = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
							String faqiren = "";
							if (tianjiaren == null) {
								faqiren = xueshengService.getUserById(Integer.parseInt(tianjiarenid)).getXingming();
							} else {
								faqiren = tianjiaren.getYonghuxingming();
							}
							huoDong.setFaqiren(faqiren);
							huoDong.setZhuangtai(0);
							huoDongs.add(huoDong);
						}
					}
				}

				/**
				 * String canyuren[] = huoDong.getCanyuren().split(";"); for
				 * (String string : canyuren) { String canyurenid[] =
				 * string.split(","); if
				 * (canyurenid[1].equals(user.getYonghuid().toString())) { if
				 * (canyurenid[2].equals("0")) { huoDong.setZhuangtai(0); } if
				 * (canyurenid[2].equals("2")) { huoDong.setZhuangtai(2); } } }
				 */
			}
			mv.addObject("fudaoyuanid", user.getYonghuid().toString());
			mv.addObject("huodong", huoDongs);
			mv.setViewName("fudaoyuan/newhuodong_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "confirmhuodong_fdy") // 确认参加活动——将参与状态改为1
	@ResponseBody
	public String confirmhuodong_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<>();
		if (Util.checkSession(request)) {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			YongHu user = (YongHu) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "1");
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
				// response.setContentType("text/html; charset=utf-8");
				// out.print("<script>alert('参与成功！');</script>");
				// out.print("<script>location='newhuodong_fdy';</script>");
				return "success";
			} else {
				// response.setContentType("text/html; charset=utf-8");
				// out.print("<script>alert('参与失败！');</script>");
				// out.print("<script>location='newhuodong_fdy';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "canyuhuodong_fdy") // 确认参加活动——将参与状态改为1
	@ResponseBody
	public String canyuhuodong_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		// response.setContentType("text/html; charset=utf-8");
		Map<String, String> map = new HashMap<>();
		if (Util.checkSession(request)) {
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			YongHu user = (YongHu) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "1");
			List<Map<String, Object>> canyuren = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
			if (canyuren != null && canyuren.size() > 0) {
				try {
					huodongService.delete_huodongjujueren(map);
					huodongService.insert_huodongcanyuren(map);
					sign = 1;
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				sign = huodongService.insert_huodongcanyuren(map);
			}
			if (sign != 0) {
				// if (qufen.equals("1")) {
				// out.print("<script>alert('参与成功！');</script>");
				// out.print("<script>location='wodericheng_fdy';</script>");
				// } else {
				// out.print("<script>alert('参与成功！');</script>");
				// out.print("<script>location='myhuodong_fdy';</script>");
				// }
				return "success";
			} else {
				// if (qufen.equals("1")) {
				// out.print("<script>alert('参与失败！');</script>");
				// out.print("<script>location='wodericheng_fdy';</script>");
				// } else {
				// out.print("<script>alert('参与失败！');</script>");
				// out.print("<script>location='myhuodong_fdy';</script>");
				// }
				return "fail";
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "cleanhuodong_fdy") // 彻底清除活动,将参与人剔除，活动还存在
	@ResponseBody
	public String cleanhuodong_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		// PrintWriter out = response.getWriter();
		// response.setContentType("text/html; charset=utf-8");
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			// System.out.println(id);
			YongHu user = (YongHu) session.getAttribute("user");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			map.put("huodongid", id);
			int sign = 0;
			List<String> cyr = new ArrayList<>();
			cyr.add(huoDong.getTianjiarenid().toString());
			try {
				if (huoDong.getLeixing() == 1) {
					List<String> banjis = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(id));
					for (String string : banjis) {
						List<XueSheng> xues = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng : xues) {
							cyr.add(xueSheng.getXueshengid().toString());
						}
					}
					huodongService.delete_huodongbanji(map);
				}
				if (huoDong.getLeixing() == 2) {
					List<Map<String, Object>> sues = huodongService
							.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(map);
					for (Map<String, Object> map2 : sues) {
						cyr.add(map2.get("yaoQingRenID").toString());
					}
					huodongService.delete_huodongren(map);
				}
				sign = 1;
			} catch (Exception e) {
				e.printStackTrace();
				sign = 0;
			}
			if (sign == 1) {
				// 发送提醒消息
				Date date = new Date();
				TiXing tiXing = new TiXing();
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				tiXing.setNeirong(user.getYonghuxingming() + "删除了活动#" + huoDong.getHuodongmingcheng() + "#");
				tiXing.setShijian(date);
				tiXing.setZhuangtai(0);
				tiXing.setType(2);
				tiXing.setShujuid(Integer.parseInt(id));
				xiaoXiFaSong.setXiaoXiMingCheng("活动");
				xiaoXiFaSong
						.setXiaoXiNeiRong(user.getYonghuxingming() + "删除了活动#" + huoDong.getHuodongmingcheng() + "#");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
				xiaoXiFaSong.setShuJuLeiXing(2);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong
						.setXueXiaoId(yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString());
				if (!cyr.isEmpty() && cyr.size() > 0) {
					for (String string : cyr) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						xiaoXiFaSong.setFaSongMuBiao(string);
						tixingService.insert(tiXing);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
				}
				return "success";
				// out.print("<script>alert('删除成功！');</script>");
				// out.print("<script>location='newhuodong_fdy';</script>");
			} else {
				// out.print("<script>alert('删除失败！');</script>");
				// out.print("<script>location='newhuodong_fdy';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "mybeiwanglu_fdy") // 我的备忘录
	public ModelAndView mybeiwanglu_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			List<BeiWL> beiWLs = new ArrayList<>();
			if (id == null || id.equals("")) {
				mv.addObject("qufen", null);
				Map<String, String> map = new HashMap<>();
				map.put("renid", user.getYonghuid().toString());
				map.put("renleixing", "1");
				beiWLs = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(map);

				// for (BeiWL beiwl : beiWLs) {
				// if (beiwl.getLeixing() == 1) {
				// if (beiwl.getHuizhi() == 1) {
				// String jieshouren[] = beiwl.getJieshouren().split(";");
				// for (String string : jieshouren) {
				// String str[] = string.split(",");
				// if (str[1].equals("2") || str[1].equals("1")) {
				// beiwl.setZhuangtai("1");
				// break;
				// }
				// }
				// }
				// }
				// if (beiwl.getLeixing() == 2) {
				// if (beiwl.getHuizhi() == 1) {
				// if(beiwl.getJieshouren()!=null&&!"".equals(beiwl.getJieshouren())){
				// String jieshouren[] = beiwl.getJieshouren().split(";");
				// for (String string : jieshouren) {
				// String str[] = string.split(":");
				// for (String string2 : str[1].split("!")) {
				// String s[] = string2.split(",");
				// if (s[1].equals("2") || s[1].equals("1")) {
				// beiwl.setZhuangtai("1");
				// mv.addObject("zhuangtai", 1);
				// break;
				// }
				// }
				//
				// }
				// }
				//
				// }
				// }
				// }

			} else {
				mv.addObject("qufen", qufen);
				BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
				beiWLs.add(beiWL);
			}
			mv.addObject("userid", user.getYonghuid());
			mv.addObject("beiwl", beiWLs);
			mv.setViewName("fudaoyuan/mybeiwanglu_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */

	@RequestMapping(value = "addbeiwang_fdy") // 新增备忘录
	public ModelAndView addbeiwang_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			mv.setViewName("fudaoyuan/addbeiwanglu_fdy");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "savebeiwang_fdy") // 保存新增备忘录———自己备忘//或下一步跳到给学生添加事件页面
	public Map<String, Object> savebeiwang_fdy(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		Map<String, Object> map2 = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String date = request.getParameter("riqi");
			String times = request.getParameter("shijian");
			String shijian = date + " " + times;
			map.put("leixing", "0");
			map.put("neirong", neirong);
			// map.put("didian", didian);
			map.put("userid", user.getYonghuid().toString());
			map.put("shijian", shijian);
			int i = beiwlService.insert(map);
			if (i != 0) {
				map2.put("status", "success");
			} else {
				map2.put("status", "abort!");
			}
			return map2;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}
	// @RequestMapping(value = "addshijianforbanjiandstudent") //
	// 保存新增备忘录———自己备忘//或下一步跳到给学生添加事件页面
	// public ModelAndView addshijianforbanjiandstudent(HttpServletRequest
	// request, HttpServletResponse response) throws IOException {
	// Map<String, String> map = new HashMap<String, String>();
	// HttpSession session = request.getSession();
	// ModelAndView mv = new ModelAndView();
	// if (session.getAttribute("user") != null && session.getAttribute("user")
	// != "") {
	// YongHu user = (YongHu) session.getAttribute("user");
	// String neirong = request.getParameter("neirong");
	// // String didian = request.getParameter("didian");
	// String date = request.getParameter("riqi");
	// String time = request.getParameter("shijian");
	// String shijian = date + " " + time;
	// String action = request.getParameter("action");
	// if (action.equals("下一步")) {
	// String xuesheng_banji = request.getParameter("ziji_xuesheng");
	//
	// // 班级
	// String banjiids =
	// fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
	// String banjiid[] = banjiids.split(",");
	// List<BanJi> banJis = new ArrayList<>();
	// List<Map<String, String>> maps = new ArrayList<>();
	// for (String string : banjiid) {
	// BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(string));
	// banJis.add(banJi);
	// List<XueSheng> xueShengs2 =
	// xueshengService.getAllByBanJiID(Integer.parseInt(string));
	// for (XueSheng xueSheng : xueShengs2) {
	// Map<String, String> map2 = new HashMap<>();
	// map2.put("banji", banJi.getBanjimingcheng());
	// map2.put("banjiid", string);
	// map2.put("xueshengid", xueSheng.getXueshengid().toString());
	// map2.put("xuehao", xueSheng.getXuehao());
	// map2.put("xingming", xueSheng.getXingming());
	// maps.add(map2);
	// }
	// }
	// if (xuesheng_banji.equals("1")) { // 学生
	// mv.addObject("xuesheng", maps);
	// mv.addObject("banji", banJis);
	// mv.addObject("neirong", neirong);
	// // mv.addObject("didian", didian);
	// mv.addObject("shijian", shijian);
	// mv.setViewName("fudaoyuan/addshijianforxuesheng");
	// return mv;
	// }
	//
	// if ("2".equals(xuesheng_banji)) { // 班级
	// mv.addObject("xuesheng", maps);
	// mv.addObject("banji", banJis);
	// mv.addObject("neirong", neirong);
	// // mv.addObject("didian", didian);
	// mv.addObject("shijian", shijian);
	// mv.setViewName("fudaoyuan/addshijianforbanji");
	// return mv;
	// }
	// }
	// } else {
	// response.sendRedirect("login");
	// }
	// return null;
	// }

	@RequestMapping(value = "addshijianforxuesheng") // 给学生添加事件
	public ModelAndView addshijianforxuesheng(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String date = request.getParameter("riqi");
			String time = request.getParameter("shijian");
			String shijian = date + " " + time;
			// 班级
			String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
			String banjiid[] = banjiids.split(",");
			List<BanJi> banJis = new ArrayList<>();
			List<Map<String, String>> maps = new ArrayList<>();
			for (String string : banjiid) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String riqi = sdf.format(new Date());
				BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(string));
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
				if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
					banJis.add(banJi);
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs2) {
						Map<String, String> map2 = new HashMap<>();
						map2.put("banji", banJi.getBanjimingcheng());
						map2.put("banjiid", string);
						map2.put("xueshengid", xueSheng.getXueshengid().toString());
						map2.put("xuehao", xueSheng.getXuehao());
						map2.put("xingming", xueSheng.getXingming());
						maps.add(map2);
					}
				}
			}
			mv.addObject("xuesheng", maps);
			mv.addObject("banji", banJis);
			mv.addObject("neirong", neirong);
			// mv.addObject("didian", didian);
			mv.addObject("shijian", shijian);
			mv.setViewName("fudaoyuan/addshijianforxuesheng");
			return mv;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "addshijianforbanji") // 给班级添加事件
	public ModelAndView addshijianforbanji(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") { // 班级
			YongHu user = (YongHu) session.getAttribute("user");
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String date = request.getParameter("riqi");
			String time = request.getParameter("shijian");
			String shijian = date + " " + time;
			// 班级
			String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
			String banjiid[] = banjiids.split(",");
			List<BanJi> banJis = new ArrayList<>();
			List<Map<String, String>> maps = new ArrayList<>();
			for (String string : banjiid) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String riqi = sdf.format(new Date());
				BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(string));
				Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid()).getRuxuenianfen();
				String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
				if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
					banJis.add(banJi);
					List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueShengs2) {
						Map<String, String> map2 = new HashMap<>();
						map2.put("banji", banJi.getBanjimingcheng());
						map2.put("banjiid", string);
						map2.put("xueshengid", xueSheng.getXueshengid().toString());
						map2.put("xuehao", xueSheng.getXuehao());
						map2.put("xingming", xueSheng.getXingming());
						maps.add(map2);
					}
				}
			}
			mv.addObject("xuesheng", maps);
			mv.addObject("banji", banJis);
			mv.addObject("neirong", neirong);
			// mv.addObject("didian", didian);
			mv.addObject("shijian", shijian);
			mv.setViewName("fudaoyuan/addshijianforbanji");
			return mv;
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "saveshijianforxuesheng") // 保存事件——为学生添加
	public Object saveshijianforxuesheng(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<String, Object> map2 = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String shijian = request.getParameter("shijian");
			String huizhi = request.getParameter("huizhi");
			String xueshengids = request.getParameter("xueshengids");
			String xueshengid[] = xueshengids.split(",");
			String jieshouren = "";
			for (String string : xueshengid) {
				jieshouren += string + ",0;";
			}
			BeiWL beiWL = new BeiWL();
			beiWL.setLeixing(2);
			beiWL.setNeirong(neirong);
			beiWL.setShijian(shijian);
			// beiWL.setDidian(didian);
			beiWL.setUserid(user.getYonghuid().toString());
			// beiWL.setJieshouren(jieshouren);
			beiWL.setHuizhi(Integer.parseInt(huizhi));
			int i = beiwlService.insertforxuesheng(beiWL);
			if (i != 0) {
				for (String string : xueshengid) {
					Map<String, String> map = new HashMap<>();
					map.put("beiwlid", beiWL.getId().toString());
					map.put("jieshourenid", string);
					map.put("leixing", "0");
					beiwlService.insert_beiwangluren(map);
				}
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("添加事件");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "给你添加了一个事件$" + neirong + "$");
				xiaoXiFaSong.setShuJuId(beiWL.getId());
				xiaoXiFaSong.setShuJuLeiXing(3);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// 发送提醒消息
				TiXing tiXing = new TiXing();
				Date date2 = new Date();
				tiXing.setNeirong(user.getYonghuxingming() + "给你添加了一个事件$" + neirong + "$");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(beiWL.getId());
				tiXing.setType(3);
				for (String string : xueshengid) {
					tiXing.setJieshourenid(Integer.parseInt(string));
					tixingService.insert(tiXing);
					xiaoXiFaSong.setFaSongMuBiao(string);
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				}
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "saveshijianforbanji") // 保存事件——为班级添加
	public Object saveshijianforbanji(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<String, Object> map2 = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String shijian = request.getParameter("shijian");
			String huizhi = request.getParameter("huizhi");
			String jieshouren = "";

			String banjiids = request.getParameter("xueshengids");
			String banjiid[] = banjiids.split(",");

			for (String string : banjiid) {
				jieshouren += string + ":";
				List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
				for (XueSheng xueSheng : xueshengs) {
					jieshouren += xueSheng.getXueshengid() + ",";
				}
			}
			BeiWL beiWL = new BeiWL();
			beiWL.setLeixing(1);
			beiWL.setNeirong(neirong);
			beiWL.setShijian(shijian);
			// beiWL.setDidian(didian);
			// beiWL.setBanjiids(banjiids);
			beiWL.setUserid(user.getYonghuid().toString());
			// beiWL.setJieshouren(jieshouren);
			beiWL.setHuizhi(Integer.parseInt(huizhi));
			int i = beiwlService.insertforbanji(beiWL);
			if (i != 0) {
				for (String string : banjiid) {
					Map<String, String> map = new HashMap<>();
					map.put("beiwlid", beiWL.getId().toString());
					map.put("banjiid", string);
					beiwlService.insert_beiwanglubanji(map);
				}
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("添加事件");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "给你添加了一个事件$" + neirong + "$");
				xiaoXiFaSong.setShuJuId(beiWL.getId());
				xiaoXiFaSong.setShuJuLeiXing(3);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				// 发送提醒消息
				TiXing tiXing = new TiXing();
				Date date2 = new Date();
				tiXing.setNeirong(user.getYonghuxingming() + "给你添加了一个事件$" + neirong + "$");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(beiWL.getId());
				tiXing.setType(3);
				for (String string : banjiids.split(",")) {
					List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					if (xueShengs.size() > 0) {
						for (XueSheng xueSheng : xueShengs) {
							tiXing.setJieshourenid(xueSheng.getXueshengid());
							tixingService.insert(tiXing);
							xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}
				}
				map2.put("status", "success");
			} else {
				map2.put("status", "fail");
			}
			return JSON.toJSON(map2);
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "xiugaibeiwanglu_fdy") // 修改备忘录
	public ModelAndView xiugaibeiwanglu_fdy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String fudaoyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if ((beiWL != null) && (beiWL.getUserid().equals(fudaoyuanid))) {
				Map<String, String> map = new HashMap<>();
				int leixing = beiWL.getLeixing();
				String datetime = beiWL.getShijian();
				String date = datetime.substring(0, datetime.indexOf(" "));
				String time = datetime.substring(datetime.indexOf(" ") + 1, datetime.length());
				map.put("beiwlid", id);
				String jsr = "";
				if (leixing == 0) {
					mv.addObject("beiwanglu", beiWL);
					mv.addObject("date", date);
					mv.addObject("time", time);
					mv.addObject("qufen", qufen);
					mv.setViewName("fudaoyuan/xiugaibeiwanglu_fdy");
				}
				if (leixing == 2) {
					int huizhi = 0;
					if (null == beiWL.getHuizhi()) {

					} else {
						huizhi = beiWL.getHuizhi();
					}
					// String jieshouren = beiWL.getJieshouren();
					// 接收人处理
					// String xuesheng[] = jieshouren.split(";");
					List<Map<String, Object>> xuesheng = beiwlService.getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(map);
					List<XueSheng> xueShengs = new ArrayList<>();
					for (Map<String, Object> string : xuesheng) {
						if (string.get("leiXing").toString().equals("0")) {
							jsr += string.get("jieShouRenID").toString() + ",";
							XueSheng xueSheng2 = xueshengService
									.getUserById(Integer.parseInt(string.get("jieShouRenID").toString()));
							xueShengs.add(xueSheng2);
						}
					}

					// 班级、学生
					String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
					String banjiid[] = banjiids.split(",");

					List<BanJi> banJis = new ArrayList<>();
					List<Map<String, String>> maps = new ArrayList<>();
					for (String string : banjiid) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String riqi = sdf.format(new Date());
						BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
								.getRuxuenianfen();
						String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
						if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
							banJis.add(banJi);
							List<XueSheng> xueShengs2 = xueshengService.getAllByBanJiID(Integer.parseInt(string));
							for (XueSheng xueSheng : xueShengs2) {
								Map<String, String> map2 = new HashMap<>();
								map2.put("banji", banJi.getBanjimingcheng());
								map2.put("banjiid", string);
								map2.put("xueshengid", xueSheng.getXueshengid().toString());
								map2.put("xuehao", xueSheng.getXuehao());
								map2.put("xingming", xueSheng.getXingming());
								maps.add(map2);
							}
						}
					}

					// String str[] = jieshouren.split(";");
					// for (String string : str) {
					// jsr += string.split(",")[0] + ",";
					// }
					beiWL.setJieshouren(jsr);
					mv.addObject("xuesheng", maps);
					mv.addObject("xuesheng2", xueShengs);
					mv.addObject("huizhi", huizhi);
					mv.addObject("beiwanglu", beiWL);
					mv.addObject("date", date);
					mv.addObject("time", time);
					mv.addObject("qufen", qufen);
					mv.setViewName("fudaoyuan/xiugaishijianforxuesheng");
				}

				if (leixing == 1) {
					List<BanJi> banjis = new ArrayList<>();
					List<BanJi> allbanjis = new ArrayList<>();

					int huizhi = 0;
					if (null == beiWL.getHuizhi()) {

					} else {
						huizhi = beiWL.getHuizhi();
					}

					// String jieshouren = beiWL.getBanjiids();
					// String str[] = jieshouren.split(",");
					List<String> str = beiwlService.getAllBanJiIDByBeiWLID(id);
					for (String string : str) {
						jsr += string + ",";
						BanJi banji = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						banjis.add(banji);
					}
					String banjiids = fudaoyuanService.getByfuDaoYuanID(user.getYonghuid()).getBanjiid();
					String banjiid[] = banjiids.split(",");
					for (String string : banjiid) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String riqi = sdf.format(new Date());
						BanJi banJi = banjiService.selectByPrimaryKey(Integer.parseInt(string));
						Integer ruXueNianFen = nianfenService.selectByPrimaryKey(banJi.getRuxuenianfenid())
								.getRuxuenianfen();
						String biYeNianFen = (ruXueNianFen + banJi.getLeixing()) + "-09-01";
						if (sdf.parse(biYeNianFen).getTime() > sdf.parse(riqi).getTime()) {
							allbanjis.add(banJi);
						}
					}
					beiWL.setBanjiids(jsr);
					mv.addObject("huizhi", huizhi);
					mv.addObject("beiwanglu", beiWL);
					mv.addObject("date", date);
					mv.addObject("time", time);
					mv.addObject("qufen", qufen);
					mv.addObject("banjis", banjis);
					mv.addObject("allbanjis", allbanjis);
					mv.setViewName("fudaoyuan/xiugaishijianforbanji");
				}
				return mv;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "saveupdatebeiwanglu_fdy") // 保存修改备忘录
	public Object saveupdatebeiwanglu_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		BeiWL beiWL = new BeiWL();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map2 = new HashMap<>();
			String fudaoyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiwanglu = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if (beiwanglu != null && beiwanglu.getUserid().equals(fudaoyuanid)) {
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
				beiWL.setUserid(user.getYonghuid().toString());
				int i = beiwlService.updateByPrimaryKey(beiWL);
				if (i != 0) {
					if (qufen.equals("1")) {
						map2.put("status", "success");
					} else {
						map2.put("status", "success");
					}
				} else {
					if (qufen.equals("1")) {
						map2.put("status", "fail");
					} else {
						map2.put("status", "fail");
					}
				}
				return JSON.toJSON(map2);
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "saveupdateshijianforxuesheng") // 保存修改给学生的事件
	public Object saveupdateshijianforxuesheng(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		BeiWL beiWL = new BeiWL();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, Object> map2 = new HashMap<>();

			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String fudaoyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiwanglu = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if (beiwanglu != null && beiwanglu.getUserid().equals(fudaoyuanid)) {
				String neirong = request.getParameter("neirong");
				String didian = request.getParameter("didian");
				String date = request.getParameter("riqi");
				String time = request.getParameter("shijian");
				String shijian = date + " " + time;
				String huizhi = request.getParameter("huizhi");
				String xueshengids = request.getParameter("xueshengids");
				// String jieshouren = "";
				String jsr = "";
				// String banjiids = "";
				String xueshengid[] = {};
				if (beiwanglu.getLeixing() == 2) {
					xueshengid = xueshengids.split(",");
					beiWL.setLeixing(2);
					for (String string : xueshengid) {
						jsr += string + ",";
						// jieshouren += string + ",0;";
					}
					// beiWL.setJieshouren(jieshouren);
				}
				if (beiwanglu.getLeixing() == 1) {
					beiWL.setLeixing(1);
					xueshengid = xueshengids.split(",");
					for (String string : xueshengid) {
						// banjiids += string + ",";
						List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng : xueshengs) {
							jsr += xueSheng.getXueshengid() + ",";
						}
					}
					// beiWL.setBanjiids(banjiids);
				}

				beiWL.setId(Integer.parseInt(id));
				beiWL.setHuodongid(beiwanglu.getHuodongid());
				beiWL.setNeirong(neirong);
				// beiWL.setDidian(didian);
				beiWL.setShijian(shijian);
				beiWL.setUserid(user.getYonghuid().toString());
				beiWL.setHuizhi(Integer.parseInt(huizhi));

				int i = beiwlService.updateshijianforxuesheng(beiWL);
				if (i != 0) {
					Map<String, String> map = new HashMap<>();
					map.put("beiwlid", id);
					if (beiWL.getLeixing() == 1) {
						beiwlService.delete_beiwanglubanji(map);
						if (xueshengids != null && !"".equals(xueshengids)) {
							String ids[] = xueshengids.split(",");
							for (String string : ids) {
								map.put("banjiid", string);
								beiwlService.insert_beiwanglubanji(map);
							}
						}
					}
					if (beiWL.getLeixing() == 2) {
						beiwlService.delete_beiwangluren(map);
						if (xueshengids != null && !"".equals(xueshengids)) {
							String ids[] = xueshengids.split(",");
							for (String string : ids) {
								map.put("jieshourenid", string);
								map.put("leixing", "0");
								beiwlService.insert_beiwangluren(map);
							}
						}
					}
					// 发送激光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("修改事件");
					xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "修改了事件$" + neirong + "$");
					xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
					xiaoXiFaSong.setShuJuLeiXing(3);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xueXiaoID);
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date2 = new Date();
					tiXing.setNeirong(user.getYonghuxingming() + "修改了事件$" + neirong + "$");
					tiXing.setShijian(date2);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(Integer.parseInt(id));
					tiXing.setType(3);
					for (String string : jsr.split(",")) {
						// if (beiwanglu.getLeixing() == 1) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						tixingService.insert(tiXing);
						xiaoXiFaSong.setFaSongMuBiao(string);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
					if (qufen.equals("1")) {
						map2.put("status", "success");
					} else {
						map2.put("status", "success");
					}
				} else {
					if (qufen.equals("1")) {
						map2.put("status", "fail");
					} else {
						map2.put("status", "fail");
					}
				}
				return JSON.toJSON(map2);
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "delbeiwl_fdy") // 删除备忘录
	public Object delbeiwl_fdy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			int yuanxiid = user.getYuanxiid();
			Map<String, Object> map2 = new HashMap<>();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String fudaoyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			String jsr = "";
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			Map<String, String> map = new HashMap<>();
			map.put("beiwlid", id);
			if (beiWL != null && beiWL.getUserid().equals(fudaoyuanid)) {
				if (beiWL.getLeixing() == 0) {
					jsr += beiWL.getUserid() + ",";
				} else if (beiWL.getLeixing() == 1) {
					List<String> banjiids = beiwlService.getAllBanJiIDByBeiWLID(id);
					for (String string : banjiids) {
						List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng : xueshengs) {
							jsr += xueSheng.getXueshengid() + ",";
						}
					}
				} else {
					List<Map<String, Object>> ids = beiwlService.getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(map);
					for (Map<String, Object> string : ids) {
						if (string != null && string.containsKey("jieShouRenID")) {
							jsr += string.get("jieShouRenID").toString() + ",";
						}
					}
				}
				int i = 0;
				try {
					beiwlService.delete_beiwlhuizhi(map);
					beiwlService.delete_beiwanglubanji(map);
					beiwlService.delete_beiwangluren(map);
					beiwlService.deleteByPrimaryKey(Integer.parseInt(id));
					i = 1;
				} catch (Exception e) {
					e.printStackTrace();
					i = 0;
				}
				if (i != 0) {
					// 发送激光消息
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("删除事件");
					xiaoXiFaSong.setXiaoXiNeiRong(user.getYonghuxingming() + "删除了事件$" + beiWL.getNeirong() + "$");
					xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
					xiaoXiFaSong.setShuJuLeiXing(3);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xueXiaoID);
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					Date date2 = new Date();
					tiXing.setNeirong(user.getYonghuxingming() + "删除了事件$" + beiWL.getNeirong() + "$");
					tiXing.setShijian(date2);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(Integer.parseInt(id));
					tiXing.setType(3);
					String jieshouren[] = jsr.split(",");
					for (String string : jieshouren) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						tixingService.insert(tiXing);
						xiaoXiFaSong.setFaSongMuBiao(string);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
					map2.put("status", "success");
				} else {
					map2.put("status", "fail");
				}
				return JSON.toJSON(map2);
			} else {
				response.sendRedirect("logout");
				return null;
			}
		} else {
			response.sendRedirect("login");
			return null;
		}
	}

	@RequestMapping(value = "chakanjieshouren") // 查看接收人
	public ModelAndView chakanjieshouren(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String fudaoyuanid = user.getYonghuid().toString();
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(id));
			if ((beiWL != null) && (beiWL.getUserid().equals(fudaoyuanid))) {

				int huizhi = 0;
				if (null == beiWL.getHuizhi()) {

				} else {
					huizhi = beiWL.getHuizhi();
				}

				List<BanJi> banjis = new ArrayList<>();
				List<String> banjiids = new ArrayList<>();
				List<Map<String, String>> maps = new ArrayList<>();
				Map<String, String> paramMap = new HashMap<>();
				paramMap.put("beiwlid", id);
				if (beiWL.getLeixing() == 2) {
					List<Map<String, Object>> jieshourenids = beiwlService
							.getBeiWLRenByBeiWLIDAndRenIDAndRenLeiXing(paramMap);
					List<Map<String, Object>> huizhiids = beiwlService
							.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(paramMap);
					if (jieshourenids != null && jieshourenids.size() > 0) {
						for (Map<String, Object> string : jieshourenids) {
							int i = 0;
							for (Map<String, Object> strings : huizhiids) {
								if (string.containsKey("jieShouRenID") && strings.containsKey("huiZhiRenID")) {
									if (string.get("jieShouRenID").toString()
											.equals(strings.get("huiZhiRenID").toString())
											&& string.get("leiXing").toString()
													.equals(strings.get("leiXing").toString())) {
										Map<String, String> map = new HashMap<>();
										XueSheng xueSheng2 = xueshengService
												.getUserById(Integer.parseInt(strings.get("huiZhiRenID").toString()));
										String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng2.getBanjiid())
												.getBanjimingcheng();
										map.put("banji", banjimingcheng);
										map.put("xueshengid", xueSheng2.getXueshengid().toString());
										map.put("xuehao", xueSheng2.getXuehao());
										map.put("xingming", xueSheng2.getXingming());
										if (huizhi == 1) {
											map.put("zhuangtai", strings.get("zhuangTai").toString());
										}
										maps.add(map);
										i = 1;
									}
								}
							}
							if (i == 0) {
								Map<String, String> map = new HashMap<>();
								XueSheng xueSheng2 = xueshengService
										.getUserById(Integer.parseInt(string.get("jieShouRenID").toString()));
								String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng2.getBanjiid())
										.getBanjimingcheng();
								map.put("banji", banjimingcheng);
								map.put("xueshengid", xueSheng2.getXueshengid().toString());
								map.put("xuehao", xueSheng2.getXuehao());
								map.put("xingming", xueSheng2.getXingming());
								if (huizhi == 1) {
									map.put("zhuangtai", "0");
								}
								maps.add(map);
							}
						}
						mv.addObject("shijian", beiWL);
						mv.addObject("xuesheng", maps);
						mv.setViewName("fudaoyuan/chakanjieshouren");
						return mv;
					}
				}

				if (beiWL.getLeixing() == 1) {
					banjiids = beiwlService.getAllBanJiIDByBeiWLID(id);
					if (banjiids != null && banjiids.size() > 0) {
						String banjiid = request.getParameter("banjiid");
						for (String s : banjiids) {
							banjis.add(banjiService.selectByPrimaryKey(Integer.parseInt(s)));
						}
						if (banjiid == null || banjiid == "") {
							banjiid = banjiids.get(0);
						}
						if (!banjiids.contains(banjiid)) {
							response.sendRedirect("logout");
							return null;
						}
						BanJi bj = banjiService.selectByPrimaryKey(Integer.parseInt(banjiid));
						List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
						List<Map<String, Object>> huizhiids = beiwlService
								.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(paramMap);
						if (huizhiids == null || huizhiids.size() <= 0) {
							for (XueSheng xuess : xueShengs) {
								Map<String, String> map = new HashMap<>();
								map.put("banji", bj.getBanjimingcheng());
								map.put("xueshengid", xuess.getXueshengid().toString());
								map.put("xuehao", xuess.getXuehao());
								map.put("xingming", xuess.getXingming());
								if (huizhi == 1) {
									map.put("zhuangtai", "0");
								}
								maps.add(map);
							}
						} else {
							for (XueSheng xuess : xueShengs) {
								int i = 0;
								for (Map<String, Object> string : huizhiids) {
									if (xuess.getXueshengid().toString().equals(string.get("huiZhiRenID").toString())) {
										Map<String, String> map = new HashMap<>();
										map.put("banji", bj.getBanjimingcheng());
										map.put("xueshengid", xuess.getXueshengid().toString());
										map.put("xuehao", xuess.getXuehao());
										map.put("xingming", xuess.getXingming());
										if (huizhi == 1) {
											map.put("zhuangtai", string.get("zhuangTai").toString());
										}
										maps.add(map);
										i = 1;
									}
								}
								if (i == 0) {
									Map<String, String> map = new HashMap<>();
									map.put("banji", bj.getBanjimingcheng());
									map.put("xueshengid", xuess.getXueshengid().toString());
									map.put("xuehao", xuess.getXuehao());
									map.put("xingming", xuess.getXingming());
									if (huizhi == 1) {
										map.put("zhuangtai", "0");
									}
									maps.add(map);
								}
							}
						}
						mv.addObject("bjid", bj.getBanjiid());
						mv.addObject("shijian", beiWL);
						mv.addObject("xuesheng", maps);
						mv.addObject("banjis", banjis);
						mv.setViewName("fudaoyuan/chakanjieshourenbanji");
						return mv;
					} else {
						response.sendRedirect("login");
					}
				}
			} else {
				response.sendRedirect("login");
			}

		} else {
			response.sendRedirect("logout");
		}
		return null;
	}

}
