package com.web.controller.web.jiaoshi;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import com.web.model.NianFen;
import com.web.model.TiXing;
import com.web.model.XiaoQu;
import com.web.model.XiaoXiFaSong;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.YiJianXiang;
import com.web.model.YongHu;
import com.web.service.BanJiService;
import com.web.service.BeiWLService;
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
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.DateUtil;
import com.web.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@LoginStatusAnnotation(status = "jiaoshi")
public class RiChengController_jiaoshi {
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private BeiWLService beiwlService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private KeChengJiBenService kechengjibenService;
	@Autowired
	private HuoDongService huodongService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private TiXingService tixingService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private JieCiFangAnService jieCiFangAnService;
	@Autowired
	private NianFenService nianfenService;

	@RequestMapping(value = "wodekecheng_jiaoshi") // 我的课程——教师
	public ModelAndView wodekecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> map = new HashMap<>();
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			List<String> xueNians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			map.put("riqi", riqi.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi = new XueQi();
			xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			if (xueQi == null) {
				List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
				if (xueqis != null && xueqis.size() > 0) {
					xueQi = xueqis.get(0);
				}
			}
			int nf = nianfenService.selectByPrimaryKey(xueQi.getNianfenid()).getRuxuenianfen();
			String xuenian = nf + "~" + (nf + 1);
			xueQi.setXuenian(xuenian);
			// 获取上课周总数
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = simpleDateFormat.parse(xueQi.getKaishiriqi());
			Date date2 = simpleDateFormat.parse(xueQi.getJieshuriqi());
			int zhounum = Util.WeeksBetweenDays(date1, date2);
			String xueqi = xueQi.getXueqi().toString();
			// //计算今天是当前学期的第几周
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

			String renkelaoshiid = user.getYonghuid().toString();
			map.put("xueqiid", xueQi.getXueqiid().toString());
			map.put("renkelaoshiid", renkelaoshiid);
			// List<KeCheng> keChengs =
			// kechengService.getAllByrenKeLaoShiID(renkelaoshiid+",%","%,"+renkelaoshiid);
			List<Map<String, Object>> keChengs = kechengService.getKeChengByRenKeLaoShiID(map);
			List<Map<String, String>> tingKeMaps = new ArrayList<>();
			for (Map<String, Object> map2 : keChengs) {
				if (xueQi.getXueqiid().toString().equals(map2.get("xueQiID").toString())) {
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
				if (xueQi.getXueqiid().toString().equals(keChengs.get(i).get("xueQiID").toString())) {
					KeCheng kecheng = new KeCheng();
					kecheng.setTianjiarenid(keChengs.get(i).get("tianJiaRenID").toString());
					kecheng.setId(Integer.parseInt(keChengs.get(i).get("ID").toString()));
					kecheng.setKechengmingcheng(keChengs.get(i).get("keChengMingCheng").toString());
					kecheng.setRenkejiaoshi(keChengs.get(i).get("renKeJiaoShi").toString());
					List<Map<String, String>> maps = new ArrayList<>();

					if (keChengs.get(i).containsKey("shiJianLeiXing") && (keChengs.get(i).get("shiJianLeiXing").toString().equals("1")
							|| keChengs.get(i).get("shiJianLeiXing").toString().equals("2")
							|| keChengs.get(i).get("shiJianLeiXing").toString().equals("3"))) {
						kecheng.setKaishizhou(Integer.parseInt(keChengs.get(i).get("kaiShiZhou").toString()));
						kecheng.setJieshuzhou(Integer.parseInt(keChengs.get(i).get("jieShuZhou").toString()));
						for (int j = 0; j < keChengs.size(); j++) { // 第二级
																	// 判断id相等时，
							if (i != j
									&& keChengs.get(i).get("ID").toString().equals(keChengs.get(j).get("ID").toString())
									&& (keChengs.get(j).get("shiJianLeiXing").toString().equals("1")
											|| keChengs.get(j).get("shiJianLeiXing").toString().equals("2") || keChengs
													.get(j).get("shiJianLeiXing").toString().equals("3"))) {

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
						if (keChengs.get(i).containsKey("shiJianLeiXing") && keChengs.get(i).get("shiJianLeiXing").toString().equals("1")) {
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
						if (keChengs.get(i).containsKey("shiJianLeiXing") && keChengs.get(i).get("shiJianLeiXing").toString().equals("2")) {
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
						if (keChengs.get(i).containsKey("shiJianLeiXing") && keChengs.get(i).get("shiJianLeiXing").toString().equals("3")) {
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
					if (keChengs.get(i).containsKey("shiJianLeiXing") && keChengs.get(i).get("shiJianLeiXing").toString().equals("4")) { // 按次上课
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

					if (keChengs.get(i).containsKey("shiJianLeiXing") && keChengs.get(i).get("shiJianLeiXing").toString().equals("6")) {
						kecheng.setLeixing(3);
						kecheng.setKaishizhou(Integer.parseInt(keChengs.get(i).get("kaiShiZhou").toString()));
						Map<String, String> kechengmap = new HashMap<>();
						kechengmap.put("zhouci", keChengs.get(i).get("zhouCi").toString());
						kechengmap.put("zhoushu", keChengs.get(i).get("kaiShiZhou").toString());
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jieShuJieCi").toString()));
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
						kecheng.setMaps(maps);
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
								keChengs2.add(keChengs2.get(i));
							}
							mv.addObject("kecheng", keChengs3);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								keChengs2.add(keChengs2.get(i));
							}
							mv.addObject("kecheng", keChengs3);
						}
					} else if (page == pages) {
						List<KeCheng> keChengs3 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							keChengs2.add(keChengs2.get(i));
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
			mv.addObject("yonghu", user);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xuenians", xueNians);
			mv.addObject("xueqi", xueqi);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("jiaoshi/wodekecheng_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chaxunkecheng_jiaoshi") // 查询课程
	public ModelAndView chaxunkecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			List<String> xueNians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xueXiaoID));
			Map<String, String> map = new HashMap<>();
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			map.put("xuexiaoid", xueXiaoID);
			map.put("nianfen", xuenian.split("~")[0]);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			if (xueQi == null) {
				mv.addObject("yonghu", user);
				mv.addObject("xuenian", xuenian);
				mv.addObject("xuenians", xueNians);
				mv.addObject("xueqi", xueqi);
				mv.setViewName("jiaoshi/wodekecheng_jiaoshi");
				return mv;
			}
			// System.out.println(xuenian);
			// System.out.println(xueqi);
			String jiaoshi = user.getYonghuid().toString();
			map.put("xueqiid", xueQi.getXueqiid().toString());
			map.put("renkelaoshiid", jiaoshi);
			List<Map<String, Object>> keChengs = kechengService.getKeChengByRenKeLaoShiID(map);
			List<Map<String, String>> tingKeMaps = new ArrayList<>();
			for (Map<String, Object> map2 : keChengs) {
				if (xueQi.getXueqiid().toString().equals(map2.get("xueQiID").toString())) {
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
				if (xueQi.getXueqiid().toString().equals(keChengs.get(i).get("xueQiID").toString())) {
					KeCheng kecheng = new KeCheng();
					kecheng.setTianjiarenid(keChengs.get(i).get("tianJiaRenID").toString());
					kecheng.setId(Integer.parseInt(keChengs.get(i).get("ID").toString()));
					kecheng.setKechengmingcheng(keChengs.get(i).get("keChengMingCheng").toString());
					kecheng.setRenkejiaoshi(keChengs.get(i).get("renKeJiaoShi").toString());
					List<Map<String, String>> maps = new ArrayList<>();

					if (keChengs.get(i).containsKey("shiJianLeiXing") && (keChengs.get(i).get("shiJianLeiXing").toString().equals("1")
							|| keChengs.get(i).get("shiJianLeiXing").toString().equals("2")
							|| keChengs.get(i).get("shiJianLeiXing").toString().equals("3"))) {
						kecheng.setKaishizhou(Integer.parseInt(keChengs.get(i).get("kaiShiZhou").toString()));
						kecheng.setJieshuzhou(Integer.parseInt(keChengs.get(i).get("jieShuZhou").toString()));
						for (int j = 0; j < keChengs.size(); j++) { // 第二级
																	// 判断id相等时，
							if (i != j
									&& keChengs.get(i).get("ID").toString().equals(keChengs.get(j).get("ID").toString())
									&& (keChengs.get(j).get("shiJianLeiXing").toString().equals("1")
											|| keChengs.get(j).get("shiJianLeiXing").toString().equals("2") || keChengs
													.get(j).get("shiJianLeiXing").toString().equals("3"))) {

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
					if (keChengs.get(i).containsKey("shiJianLeiXing") && keChengs.get(i).get("shiJianLeiXing").toString().equals("4")) { // 按次上课
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
					if (keChengs.get(i).containsKey("shiJianLeiXing") && keChengs.get(i).get("shiJianLeiXing").toString().equals("6")) {
						kecheng.setLeixing(3);
						kecheng.setKaishizhou(Integer.parseInt(keChengs.get(i).get("kaiShiZhou").toString()));
						Map<String, String> kechengmap = new HashMap<>();
						kechengmap.put("zhouci", keChengs.get(i).get("zhouCi").toString());
						kechengmap.put("zhoushu", keChengs.get(i).get("kaiShiZhou").toString());
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(keChengs.get(i).get("jieShuJieCi").toString()));
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
						kecheng.setMaps(maps);
						keChengs2.add(kecheng);
					}
				}
			}

			/**
			 * List<KeCheng> keChengs =
			 * kechengService.getAllByrenKeLaoShiID(jiaoshi+",%","%,"+jiaoshi);
			 * List<KeCheng> keChengs2 = new ArrayList<>(); for (KeCheng keCheng
			 * : keChengs) { if
			 * (!(keCheng.getDanshuangzhoushuoming().isEmpty())) { String
			 * danShuangZhouShuoMing[] =
			 * keCheng.getDanshuangzhoushuoming().split("zqxj"); switch
			 * (danShuangZhouShuoMing[0]) { case "1": keCheng.setLeixing(1);
			 * String zhouCi[] = danShuangZhouShuoMing[1].split(",");
			 * keCheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
			 * keCheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * 
			 * List<Map<String, String>> maps = new ArrayList<>();
			 * List<Map<String, String>> jiakes = new ArrayList<>(); List
			 * <String> tiaokes = new ArrayList<>();
			 * 
			 * // 加课说明,调课说明 String jiake[] =
			 * danShuangZhouShuoMing[4].split("zqjx"); for (String string :
			 * jiake) { String str[] = string.split(","); Map<String, String>
			 * jiamap = new HashMap<>(); jiamap.put("shangkeriqi", str[0]); JCSJ
			 * jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(str[1]));
			 * JCSJ jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str[2]));
			 * jiamap.put("kaishijieci", jcsj1.getJieci().toString());
			 * jiamap.put("jieshujieci", jcsj2.getJieci().toString()); JiaoShi
			 * jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str[3]));
			 * String jiaoshiming = jshi.getJiaoshiming();
			 * if(jiaoshiming.equals("待定")){ jiamap.put("xiaoquming", "");
			 * jiamap.put("jiaoxuelouming", ""); } else
			 * if(jiaoshiming.equals("现场参观")){ jiamap.put("xiaoquming", "");
			 * jiamap.put("jiaoxuelouming", ""); } else{ String jiaoXueLouMing =
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
			 * .getJiaoXueLouMing(); String xiaoquming =
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * (); jiamap.put("xiaoquming", xiaoquming);
			 * jiamap.put("jiaoxuelouming", jiaoXueLouMing); }
			 * jiamap.put("jiaoshiming", jiaoshiming); jiakes.add(jiamap); }
			 * 
			 * // 停课说明 String tingke[] = danShuangZhouShuoMing[3].split("zqjx");
			 * for (String string : tingke) { tiaokes.add(string); }
			 * 
			 * // 课程对应的节次，教学楼 String xinxi[] =
			 * danShuangZhouShuoMing[2].split("zxqj"); for (String string :
			 * xinxi) { String str[] = string.split(","); Map<String, String>
			 * kechengmap = new HashMap<>(); kechengmap.put("zhouci", str[0]);
			 * JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(str[2]));
			 * kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
			 * kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str[3]));
			 * String jiaoshiming = jshi.getJiaoshiming();
			 * if(jiaoshiming.equals("待定")){ kechengmap.put("xiaoquming", "");
			 * kechengmap.put("jiaoXueLouMing", ""); } else
			 * if(jiaoshiming.equals("现场参观")){ kechengmap.put("xiaoquming", "");
			 * kechengmap.put("jiaoXueLouMing", ""); } else { String
			 * jiaoXueLouMing =
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
			 * .getJiaoXueLouMing(); String xiaoquming =
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * (); kechengmap.put("xiaoquming", xiaoquming);
			 * kechengmap.put("jiaoXueLouMing", jiaoXueLouMing); }
			 * kechengmap.put("jiaoshiming", jiaoshiming); maps.add(kechengmap);
			 * } keCheng.setJiakes(jiakes); keCheng.setMaps(maps);
			 * keCheng.setTiaokes(tiaokes); if
			 * (keCheng.getXuenian().equals(xuenian) &&
			 * keCheng.getXueqi().toString().equals(xueqi)) {
			 * keChengs2.add(keCheng); } break;
			 * 
			 * case "2": keCheng.setLeixing(2); String zhouCi2[] =
			 * danShuangZhouShuoMing[1].split(",");
			 * keCheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * keCheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * 
			 * List<Map<String, String>> maps2 = new ArrayList<>();
			 * List<Map<String, String>> jiakes2 = new ArrayList<>(); List
			 * <String> tiaokes2 = new ArrayList<>();
			 * 
			 * // 加课说明,调课说明 String jiake2[] =
			 * danShuangZhouShuoMing[4].split("zqjx"); for (String string :
			 * jiake2) { String str[] = string.split(","); Map<String, String>
			 * jiamap = new HashMap<>(); jiamap.put("shangkeriqi", str[0]); JCSJ
			 * jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(str[1]));
			 * JCSJ jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str[2]));
			 * jiamap.put("kaishijieci", jcsj1.getJieci().toString());
			 * jiamap.put("jieshujieci", jcsj2.getJieci().toString()); JiaoShi
			 * jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str[3]));
			 * String jiaoshiming = jshi.getJiaoshiming();
			 * if(jiaoshiming.equals("待定")){ jiamap.put("xiaoquming", "");
			 * jiamap.put("jiaoXueLouMing", ""); } else
			 * if(jiaoshiming.equals("现场参观")){ jiamap.put("xiaoquming", "");
			 * jiamap.put("jiaoXueLouMing", ""); } else { String jiaoXueLouMing
			 * = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
			 * .getJiaoXueLouMing(); String xiaoquming =
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * (); jiamap.put("xiaoquming", xiaoquming);
			 * jiamap.put("jiaoXueLouMing", jiaoXueLouMing); }
			 * jiamap.put("jiaoshiming", jiaoshiming); jiakes2.add(jiamap); }
			 * 
			 * // 停课说明 String tingke2[] =
			 * danShuangZhouShuoMing[3].split("zqjx"); for (String string :
			 * tingke2) { tiaokes2.add(string); }
			 * 
			 * // 课程对应的节次，教学楼 String xinxi2[] =
			 * danShuangZhouShuoMing[2].split("zqjx"); Set<Map<String, String>>
			 * danSet = new HashSet<>(); Set<Map<String, String>> shuangSet =
			 * new HashSet<>(); for (String string : xinxi2[0].split("zxqj")) {
			 * // 单周 String str[] = string.split(","); Map<String, String>
			 * kechengmap = new HashMap<>(); kechengmap.put("zhouci", str[0]);
			 * JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(str[2]));
			 * kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
			 * kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str[3]));
			 * if(jshi.getJiaoshiming().equals("待定")){
			 * kechengmap.put("xiaoquming", "");
			 * kechengmap.put("jiaoXueLouMing", ""); } else
			 * if(jshi.getJiaoshiming().equals("现场参观")){
			 * kechengmap.put("xiaoquming", "");
			 * kechengmap.put("jiaoXueLouMing", ""); } else{ String
			 * jiaoXueLouMing=
			 * (jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())).
			 * getJiaoXueLouMing(); String xiaoquming=
			 * (xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
			 * getMingcheng()); kechengmap.put("xiaoquming", xiaoquming);
			 * kechengmap.put("jiaoXueLouMing", jiaoXueLouMing); }
			 * kechengmap.put("jiaoshiming", jshi.getJiaoshiming());
			 * danSet.add(kechengmap); } for (String string :
			 * xinxi2[1].split("zxqj")) { // 双周 String str[] =
			 * string.split(","); Map<String, String> kechengmap = new
			 * HashMap<>(); kechengmap.put("zhouci", str[0]); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(str[2]));
			 * kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
			 * kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str[3]));
			 * if(jshi.getJiaoshiming().equals("待定")){
			 * kechengmap.put("xiaoquming", "");
			 * kechengmap.put("jiaoXueLouMing", ""); } else
			 * if(jshi.getJiaoshiming().equals("现场参观")){
			 * kechengmap.put("xiaoquming", "");
			 * kechengmap.put("jiaoXueLouMing", ""); } else{ String
			 * jiaoXueLouMing=
			 * (jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())).
			 * getJiaoXueLouMing(); String xiaoquming=
			 * (xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
			 * getMingcheng()); kechengmap.put("xiaoquming", xiaoquming);
			 * kechengmap.put("jiaoXueLouMing", jiaoXueLouMing); }
			 * kechengmap.put("jiaoshiming", jshi.getJiaoshiming());
			 * shuangSet.add(kechengmap); } // 判断单双set，里重复内容 if (danSet.size() >
			 * shuangSet.size()) { for (Map<String, String> mapp : danSet) { if
			 * (shuangSet.contains(mapp)) { maps2.add(mapp); mapp.put("ds",
			 * "0"); } else { // 单周 mapp.put("ds", "1"); maps2.add(mapp); } } }
			 * if (danSet.size() < shuangSet.size()) { for (Map<String, String>
			 * mapp : shuangSet) { if (danSet.contains(mapp)) { maps2.add(mapp);
			 * mapp.put("ds", "0"); } else { // 双周 mapp.put("ds", "2");
			 * maps2.add(mapp); } } } if (danSet.size() == shuangSet.size()) {
			 * for (Map<String, String> mapp : shuangSet) { int i = 0; for
			 * (Map<String, String> maap : danSet) { if (mapp.equals(maap)) { i
			 * = 1; continue; } } if (i == 0) { mapp.put("ds", "2");
			 * maps2.add(mapp); } if (i == 1) { mapp.put("ds", "0");
			 * maps2.add(mapp); } } for (Map<String, String> maap : danSet) {
			 * int i = 0; for (Map<String, String> m : maps2) { if
			 * ((m.get("zhouci").equals(maap.get("zhouci"))) &&
			 * (m.get("kaishijieci").equals(maap.get("kaishijieci"))) &&
			 * (m.get("jieshujieci").equals(maap.get("jieshujieci"))) &&
			 * (m.get("jiaoshiming").equals(maap.get("jiaoshiming")))) { i = 1;
			 * break; } } if (i == 0) { maap.put("ds", "1"); maps2.add(maap); }
			 * }
			 * 
			 * }
			 * 
			 * keCheng.setJiakes(jiakes2); keCheng.setMaps(maps2);
			 * keCheng.setTiaokes(tiaokes2); if
			 * (keCheng.getXuenian().equals(xuenian) &&
			 * keCheng.getXueqi().toString().equals(xueqi)) {
			 * keChengs2.add(keCheng); } break;
			 * 
			 * case "3": keCheng.setLeixing(3); String allXinIn[] =
			 * danShuangZhouShuoMing[1].split("zqjx"); Date kaiShiRiQi =
			 * DateUtil.fomatDate(xueQi.getKaishiriqi(), "yyyy-MM-dd"); String
			 * one[] = allXinIn[0].split(","); String firstZhous = ""; String
			 * secondZhous = ""; Map<String, String> firstMap = new HashMap<>();
			 * Map<String, String> secondMap = new HashMap<>(); List<Map<String,
			 * String>> mapps = new ArrayList<>(); Date zhouDate =
			 * DateUtil.fomatDate(one[0], "yyyy-MM-dd"); firstZhous +=
			 * Util.WeeksBetweenDays(kaiShiRiQi, zhouDate); int zhouci = (int)
			 * Math.abs((kaiShiRiQi.getTime() - zhouDate.getTime()) % 7);
			 * firstMap.put("zhouci", (zhouci + 1) + ""); JCSJ jcsj11 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(one[1])); JCSJ
			 * jcsj22 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(one[2]));
			 * firstMap.put("kaishijieci", jcsj11.getJieci().toString());
			 * firstMap.put("jieshujieci", jcsj22.getJieci().toString());
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(one[3]));
			 * if(jshi.getJiaoshiming().equals("待定")){
			 * firstMap.put("xiaoquming", ""); firstMap.put("jiaoXueLouMing",
			 * ""); } else if(jshi.getJiaoshiming().equals("现场参观")){
			 * firstMap.put("xiaoquming", ""); firstMap.put("jiaoXueLouMing",
			 * ""); } else{ String jiaoXueLouMing=
			 * (jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())).
			 * getJiaoXueLouMing(); String xiaoquming=
			 * (xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
			 * getMingcheng()); firstMap.put("xiaoquming", xiaoquming);
			 * firstMap.put("jiaoXueLouMing", jiaoXueLouMing); }
			 * firstMap.put("jiaoshiming", jshi.getJiaoshiming()); for (int i =
			 * 1; i < allXinIn.length; i++) { String all[] =
			 * allXinIn[i].split(","); if (one[1].equals(all[1]) &&
			 * one[2].equals(all[2]) && one[3].equals(all[3])) {
			 * 
			 * Date zhouDates = DateUtil.fomatDate(all[0], "yyyy-MM-dd");
			 * firstZhous += "," + Util.WeeksBetweenDays(kaiShiRiQi, zhouDates);
			 * 
			 * } else { Date zhouDates = DateUtil.fomatDate(all[0],
			 * "yyyy-MM-dd"); secondZhous += Util.WeeksBetweenDays(kaiShiRiQi,
			 * zhouDates) + ","; int zhoucis = (int)
			 * Math.abs((kaiShiRiQi.getTime() - zhouDates.getTime()) % 7);
			 * secondMap.put("zhouci", (zhoucis + 1) + ""); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(all[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(all[2]));
			 * secondMap.put("kaishijieci", jcsj1.getJieci().toString());
			 * secondMap.put("jieshujieci", jcsj2.getJieci().toString());
			 * JiaoShi jshi2 =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(all[3]));
			 * if(jshi2.getJiaoshiming().equals("待定")){
			 * secondMap.put("xiaoquming", ""); secondMap.put("jiaoXueLouMing",
			 * ""); } else if(jshi2.getJiaoshiming().equals("现场参观")){
			 * secondMap.put("xiaoquming", ""); secondMap.put("jiaoXueLouMing",
			 * ""); } else{ String jiaoXueLouMing2=
			 * (jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())).
			 * getJiaoXueLouMing(); String xiaoquming2=
			 * (xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
			 * getMingcheng()); secondMap.put("xiaoquming", xiaoquming2);
			 * secondMap.put("jiaoXueLouMing", jiaoXueLouMing2); }
			 * secondMap.put("jiaoshiming", jshi2.getJiaoshiming()); } }
			 * firstMap.put("zhoushu", firstZhous); if
			 * (secondZhous.lastIndexOf(",") > 0) { secondMap.put("zhoushu",
			 * secondZhous.substring(0, secondZhous.lastIndexOf(",")));
			 * mapps.add(secondMap); } mapps.add(firstMap);
			 * keCheng.setMaps(mapps); if (keCheng.getXuenian().equals(xuenian)
			 * && keCheng.getXueqi().toString().equals(xueqi)) {
			 * keChengs2.add(keCheng); } break; } } }
			 */
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
								keChengs2.add(keChengs2.get(i));
							}
							mv.addObject("kecheng", keChengs3);
						} else {
							for (int i = (page - 1) * 10; i < (page * 10); i++) {
								keChengs2.add(keChengs2.get(i));
							}
							mv.addObject("kecheng", keChengs3);
						}
					} else if (page == pages) {
						List<KeCheng> keChengs3 = new ArrayList<>();
						for (int i = (page - 1) * 10; i < count; i++) {
							keChengs2.add(keChengs2.get(i));
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
			mv.addObject("yonghu", user);
			mv.addObject("xuenian", xuenian);
			mv.addObject("xuenians", xueNians);
			mv.addObject("xueqi", xueqi);
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("jiaoshi/wodekecheng_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addkecheng_js") // 新增课程
	public ModelAndView addkecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			// 校区列表
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			// 可选择课程列表
			String jiaoshi1 = "%," + user.getYonghuid().toString() + ",%";
			String jiaoshi2 = user.getYonghuid().toString() + ",%";
			// List<KeChengJiBen> keChengJiBens =
			// kechengjibenService.getAllByrenKeJiaoShi(jiaoshi1, jiaoshi2);
			List<KeChengJiBen> keChengJiBens = new ArrayList<>();
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

			Map<String, Integer> map2 = new HashMap<>();
			map2.put("zhuangtai", 1);
			map2.put("xuexiaoid", xuexiaoid);
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map2);
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());

			mv.addObject("kecheng", keChengJiBens);
			mv.addObject("xiaoqu", xiaoQus);
			mv.addObject("jcsj", jcsjs);
			mv.setViewName("jiaoshi/addkecheng_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping("chaxundanmenkecheng_jiaoshi")
	@ResponseBody
	public JSONObject chaxundanmenkecheng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isJiaoShi(request)) {
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

	@RequestMapping(value = "savekecheng_jiaoshi") // 保存新增课程
	public ModelAndView savekecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		KeCheng keCheng = new KeCheng();
		Map<String, String> map = new HashMap<>();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String kechengid = request.getParameter("kecheng");
			KeChengJiBen keChengJiBen = kechengjibenService.selectByPrimaryKey(Integer.parseInt(kechengid));
			String kechengmingcheng = keChengJiBen.getKechengmingcheng();
			String kaifangyuanxi = keChengJiBen.getYuanxiids();
			String renkejiaoshiids = keChengJiBen.getRenkejiaoshiids();
			String renkejiaoshiid[] = renkejiaoshiids.split(",");
			String renkejiaoshi = "";
			for (String string : renkejiaoshiid) {
				String teacher = yonghuService.selectYongHuByID(Integer.parseInt(string)).getYonghuxingming();
				renkejiaoshi += teacher + ",";
			}
			renkejiaoshi = renkejiaoshi.substring(0, renkejiaoshi.lastIndexOf(","));
			String xiaoqu = request.getParameter("xiaoqu");
			String shangkejiaoshi = request.getParameter("shangkejiaoshi");// 教室
			String kaishizhou = request.getParameter("kaishizhou");
			String jieshuzhou = request.getParameter("jieshuzhou");
			String zhouci = request.getParameter("zhouci");
			String danshuangzhou = request.getParameter("danshuangzhou");
			String danshuangzhoushuoming = request.getParameter("danshuangzhoushuoming");
			String kaishijieci = request.getParameter("kaishijieci");
			String jieshujieci = request.getParameter("jieshujieci");
			// 取得当前学年、学期
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			map.put("riqi", riqi.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi2 = xueqiService.getByxueXiaoIDandriQi(map);
			String xuenian = xueQi2.getXuenian();
			int xueqi = xueQi2.getXueqi();

			int day;
			String[] shangkeriqi = new String[Integer.parseInt(jieshuzhou) - Integer.parseInt(kaishizhou) + 1];
			String shangkeriqis = "";
			for (int i = 0; i < shangkeriqi.length; i++) {
				// 获取每次上课距离学期开始的天数
				day = (Integer.parseInt(kaishizhou) - 1 + i) * 7 + Integer.parseInt(zhouci) - 1;
				// 获取每次上课日期
				shangkeriqi[i] = riqi.format(
						new Date(riqi.parse(xueQi2.getKaishiriqi()).getTime() + (long) day * 24 * 60 * 60 * 1000));
				shangkeriqis += shangkeriqi[i] + ",";
			}
			keCheng.setKechengid(Integer.parseInt(kechengid));
			keCheng.setKechengmingcheng(kechengmingcheng);
			keCheng.setXiaoqu(xiaoqu);
			keCheng.setShangkejiaoshi(shangkejiaoshi);
			keCheng.setRenkejiaoshi(renkejiaoshi);
			keCheng.setRenkelaoshiid(renkejiaoshiids);
			keCheng.setKaifangyuanxi(kaifangyuanxi);
			keCheng.setTianjiarenid(user.getYonghuid().toString());
			keCheng.setKaishizhou(Integer.parseInt(kaishizhou));
			keCheng.setJieshuzhou(Integer.parseInt(jieshuzhou));
			keCheng.setZhouci(Integer.parseInt(zhouci));
			keCheng.setDanshuangzhou(Integer.parseInt(danshuangzhou));
			keCheng.setDanshuangzhoushuoming(danshuangzhoushuoming);
			keCheng.setCanyuren("");
			keCheng.setKaishijieci(Integer.parseInt(kaishijieci));
			keCheng.setJieshujieci(Integer.parseInt(jieshujieci));
			keCheng.setXuenian(xuenian);
			keCheng.setXueqi(xueqi);
			keCheng.setShangkeriqi(shangkeriqis);
			int i = kechengService.insertSelective(keCheng);
			if (i != 0) {
				out.print("<script>alert('加课成功！');</script>");
				out.print("<script>location='wodekecheng_jiaoshi';</script>");
			} else {
				out.print("<script>alert('加课失败！');</script>");
				out.print("<script>location='wodekecheng_jiaoshi';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "tiaotingkecheng_jiaoshi") // 调停课程
	public ModelAndView tiaotingkecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
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
			int jiecifanganid = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map2).getId();
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<String> riqi = new ArrayList<>();
			// 通过id，得所有上课时间
			List<Map<String, Object>> shangKeShiJianMap = kechengService.getShangKeShiJianByID(ids[0]);
			List<Map<String, String>> tingKeShiJianMap = new ArrayList<>();
			if (shangKeShiJianMap.size() > 0 && shangKeShiJianMap != null) {
				// 从 所有上课时间 中，得所有停课时间 --- shiJianLeiXing=5
				for (Map<String, Object> map3 : shangKeShiJianMap) {
					if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("5")) {
						Map<String, String> map = new HashMap<>();
						map.put("ID", map3.get("ID").toString());
						map.put("kaiShiZhou", map3.get("kaiShiZhou").toString());
						map.put("zhouCi", map3.get("zhouCi").toString());
						map.put("kaiShiJieCi", map3.get("kaiShiJieCi").toString());
						map.put("jieShuJieCi", map3.get("jieShuJieCi").toString());
						map.put("jiaoShiID", map3.get("jiaoShiID").toString());
						tingKeShiJianMap.add(map);
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
				if (tingKeShiJianMap != null && tingKeShiJianMap.size() > 0) {
					for (Map<String, String> tiaokemap : tingKeShiJianMap) {
						// 获取每次上课距离学期开始的天数
						int day = (Integer.parseInt(tiaokemap.get("kaiShiZhou").toString()) - 1) * 7
								+ Integer.parseInt(tiaokemap.get("zhouCi").toString()) - 1;
						// 获取每次上课日期
						String date = simpleDateFormat
								.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
										+ (long) day * 24 * 60 * 60 * 1000));
						if (riqi.contains(date)) {
							riqi.remove(date);
						}
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
			mv.setViewName("jiaoshi/tiaotingkecheng_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savetiaotingkecheng_jiaoshi") // 保存调停课程
	@ResponseBody
	public String savetiaotingkecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			String id = request.getParameter("id");
			int xuexiaoid = yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid();
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
//			if (!user.getYonghuid().toString().equals(tianjiarenid)) {
//				response.sendRedirect("logout");
//				return null;
//			}
			int yuanxiid = user.getYuanxiid();
			List<String> canyubanji = kechengService.getByAllBanJiIDByID(id);
			List<BanJi> banjiid = banjiService.getAllByYuanXiID(yuanxiid);
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
						String cyr[] = canyurens.split(",");
						for (String string : cyr) {
							tiXing.setJieshourenid(Integer.parseInt(string));
							tixingService.insert(tiXing);
							xiaoXiFaSong.setFaSongMuBiao(string);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}
//					out.print("<script>alert('停课成功！');</script>");
//					out.print("<script>location='wodekecheng_jiaoshi';</script>");
					return "success";
				} else {
//					out.print("<script>alert('停课失败！');</script>");
//					out.print("<script>location='wodekecheng_jiaoshi';</script>");
				}
			}
			if (tiaoting.equals("1")) {
				String tiaokeriqi = request.getParameter("tiaotingriqi");
				String newriqi = request.getParameter("newriqi");
				String xiaoqu = request.getParameter("xiaoqu");
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
						String cyr[] = canyurens.split(",");
						for (String string : cyr) {
							tiXing.setJieshourenid(Integer.parseInt(string));
							tixingService.insert(tiXing);
							xiaoXiFaSong.setFaSongMuBiao(string);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}
//					out.print("<script>alert('调课成功！');</script>");
//					out.print("<script>location='wodekecheng_jiaoshi';</script>");
					return "success";
				} else {
//					out.print("<script>alert('调课失败！');</script>");
//					out.print("<script>location='wodekecheng_jiaoshi';</script>");
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
						String cyr[] = canyurens.split(",");
						for (String string : cyr) {
							tiXing.setJieshourenid(Integer.parseInt(string));
							tixingService.insert(tiXing);
							xiaoXiFaSong.setFaSongMuBiao(string);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}
//					out.print("<script>alert('加课成功！');</script>");
//					out.print("<script>location='wodekecheng_jiaoshi';</script>");
					return "success";
				} else {
//					out.print("<script>alert('加课失败！');</script>");
//					out.print("<script>location='wodekecheng_jiaoshi';</script>");
				}
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "setKeDaiBiao_jiaoshi")
	@ResponseBody
	public String setKeDaiBiao_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

	@RequestMapping(value = "cancelKeDaiBiao_jiaoshi")
	@ResponseBody
	public String cancelKeDaiBiao_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

	@RequestMapping(value = "xiugaikecheng_jiaoshi") // 修改课程
	public ModelAndView xiugaikecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
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
			String tianjiarenid = kecheng.getTianjiarenid();
//			if (!user.getYonghuid().toString().equals(tianjiarenid)) {
//				response.sendRedirect("logout");
//				return null;
//			}
			int yuanxiid = user.getYuanxiid();
			int xuexiaoid = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid();
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
			int jiecifanganid = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map2).getId();
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jiecifanganid);
			// 校区列表
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(xuexiaoid);
			List<Map<String, String>> maps = new ArrayList<>();
			List<Map<String, Object>> shiJianMap = kechengService.getShangKeShiJianByID(id);
			if (shiJianMap != null && shiJianMap.size() > 0) {
				String ewaixinxi[] = request.getParameter("xinxi").split("/");
				if (ewaixinxi[0] != null && !("".equals(ewaixinxi[0])) && ewaixinxi[0].equals("3")) { // 次
					// xinxi=${KeCheng.leixing}/${maps.zhouci}/${maps.zhoushu}/${maps.kaishijieci}/${maps.jieshujieci}
					for (Map<String, Object> map3 : shiJianMap) {
						if (map3.containsKey("shiJianLeiXing") && (map3.get("shiJianLeiXing").toString().equals("4") || map3.get("shiJianLeiXing").toString().equals("6"))) {
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
			mv.addObject("kecheng", kecheng);
			mv.setViewName("jiaoshi/xiugaikecheng_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatekecheng_jiaoshi") // 保存修改课程
	@ResponseBody
	public String saveupdatekecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
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
			String tianjiarenid = kecheng.getTianjiarenid();
//			if (!user.getYonghuid().toString().equals(tianjiarenid)) {
//				response.sendRedirect("logout");
//				return null;
//			}
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
			String leixing = request.getParameter("leixing");
			// 取得当前学年、学期
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			Map<String, Integer> mapp = new HashMap<>();
			mapp.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			mapp.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(mapp);
			XueQi xueQi2 = xueqiService.selectByID(kecheng.getXueqiid());
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
						if (map3.containsKey("shiJianLeiXing") && (map3.get("shiJianLeiXing").toString().equals("4") || map3.get("shiJianLeiXing").toString().equals("6"))) {
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
												paramMap.put("yuanleixing", map3.get("shiJianLeiXing").toString());
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
											paramMap.put("leixing", map3.get("shiJianLeiXing").toString());
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
					if (string != null && !"".equals(string)) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						tixingService.insert(tiXing);
						xiaoXiFaSong.setFaSongMuBiao(string);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
				}
//				out.print("<script>alert('修改课程成功！');</script>");
//				out.print("<script>location='wodekecheng_jiaoshi';</script>");
				return "success";
			} else {
//				out.print("<script>alert('修改课程失败！');</script>");
//				out.print("<script>location='wodekecheng_jiaoshi';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delkecheng_jiaoshi") // 删除课程
	@ResponseBody
	public String delkecheng_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
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
			String tianjiarenid = kecheng.getTianjiarenid();
			if (!user.getYonghuid().toString().equals(tianjiarenid)) {
				response.sendRedirect("logout");
				return null;
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
			mapss.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			mapss.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(mapss);
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
			if (j != 0) {
				// 发送激光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng("删除课程");
				xiaoXiFaSong
						.setXiaoXiNeiRong(user.getYonghuxingming() + "删除了课程《" + kecheng.getKechengmingcheng() + "》");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(id));
				xiaoXiFaSong.setShuJuLeiXing(1);
				xiaoXiFaSong.setFaSongLeiXing(2);
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
//				out.print("<script>alert('删除成功！');</script>");
//				out.print("<script>location='wodekecheng_jiaoshi';</script>");
				return "success";
			} else {
//				out.print("<script>alert('删除失败！');</script>");
//				out.print("<script>location='wodekecheng_jiaoshi';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "chakanshangkexuesheng") // 查看上课学生
	public ModelAndView chakanshangkexuesheng(HttpServletRequest request, HttpServletResponse response)
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
				for (int i = 0; i < keDaiBiao.length; i++) {
					xueShengIdList.add(keDaiBiao[i].split(",")[0]);
				}
			}
			List<Map<String, String>> maps = new ArrayList<>();
			List<String> banJiIDs = kechengService.getByAllBanJiIDByID(id);
			List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
			List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(id);

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
			mv.addObject("student", maps);
			mv.setViewName("jiaoshi/chakanshangkexuesheng");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "wodebeiwanglu_jiaoshi") // 我的备忘录
	public ModelAndView wodebeiwanglu_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String qufen = request.getParameter("qufen");
			String id = request.getParameter("id");
			List<BeiWL> beiWLs = new ArrayList<>();
			if (id != null && !(id.equals(""))) {
				beiWLs.add(beiwlService.selectByPrimaryKey(Integer.parseInt(id)));
			} else {
				Map<String,String> map = new HashMap<>();
				map.put("renid", user.getYonghuid().toString());
				map.put("renleixing", "2");
				beiWLs = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(map);
			}
			mv.addObject("qufen", qufen);
			mv.addObject("beiwl", beiWLs);
			mv.setViewName("jiaoshi/wodebeiwanglu_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addbeiwang_jiaoshi") // 新增备忘录
	public ModelAndView addbeiwang_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			mv.setViewName("jiaoshi/addbeiwanglu_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savebeiwang_jiaoshi") // 保存新增备忘录
	@ResponseBody
	public String savebeiwang_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String neirong = request.getParameter("neirong");
			// String didian = request.getParameter("didian");
			String date = request.getParameter("riqi");
			String time = request.getParameter("shijian");
			String shijian = date + " " + time;
			/*
			 * System.out.println(neirong); System.out.println(didian);
			 * System.out.println(shijian);
			 */
			map.put("neirong", neirong);
			// map.put("didian", didian);
			map.put("userid", user.getYonghuid().toString());
			map.put("shijian", shijian);
			map.put("leixing", "0");
			int i = beiwlService.insert(map);
			if (i != 0) {
//				out.print("<script>alert('新增成功！');</script>");
//				out.print("<script>location='wodebeiwanglu_jiaoshi';</script>");
				return "success";
			} else {
//				out.print("<script>alert('新增失败！');</script>");
//				out.print("<script>location='wodebeiwanglu_jiaoshi';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiugaibeiwanglu_jiaoshi") // 修改备忘录
	public ModelAndView xiugaibeiwanglu_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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
				String datetime = beiWL.getShijian();
				String date = datetime.substring(0, datetime.indexOf(" "));
				String time = datetime.substring(datetime.indexOf(" ") + 1, datetime.length());
				// System.out.println(datetime);
				// System.out.println(date);
				// System.out.println(time);
				mv.addObject("beiwanglu", beiWL);
				mv.addObject("date", date);
				mv.addObject("time", time);
				mv.addObject("qufen", qufen);
				mv.setViewName("jiaoshi/xiugaibeiwanglu_jiaoshi");
				return mv;
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatebeiwanglu_jiaoshi") // 保存修改备忘录
	@ResponseBody
	public String saveupdatebeiwanglu_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		BeiWL beiWL = new BeiWL();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			String fudaoyuanid = user.getYonghuid().toString();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
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
				beiWL.setNeirong(neirong);
				// beiWL.setDidian(didian);
				beiWL.setShijian(shijian);
				beiWL.setLeixing(0);
				beiWL.setUserid(user.getYonghuid().toString());
				int i = beiwlService.updateByPrimaryKey(beiWL);
				if (i != 0) {
//					if (qufen.equals("1")) {
//						out.print("<script>alert('修改成功！');</script>");
//						out.print("<script>location='wodericheng_jiaoshi';</script>");
//					} else {
//						out.print("<script>alert('修改成功！');</script>");
//						out.print("<script>location='wodebeiwanglu_jiaoshi';</script>");
//					}
					return "success";
				} else {
//					if (qufen.equals("1")) {
//						out.print("<script>alert('修改失败！');</script>");
//						out.print("<script>location='wodericheng_jiaoshi';</script>");
//					} else {
//						out.print("<script>alert('修改失败！');</script>");
//						out.print("<script>location='wodebeiwanglu_jiaoshi';</script>");
//					}
				}

			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delbeiwl_jiaoshi") // 删除备忘录
	@ResponseBody
	public String delbeiwl_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
//		PrintWriter out = response.getWriter();
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
			if (beiWL != null && beiWL.getUserid().equals(fudaoyuanid)) {
				int i = beiwlService.deleteByPrimaryKey(Integer.parseInt(id));
				if (i != 0) {
					response.setContentType("text/html; charset=utf-8");
//					if (qufen.equals("1")) {
//						out.print("<script>location='wodericheng_jiaoshi';</script>");
//					} else {
//						out.print("<script>location='wodebeiwanglu_jiaoshi';</script>");
//					}
					return "success";
				} else {
					response.setContentType("text/html; charset=utf-8");
//					if (qufen.equals("1")) {
//						out.print("<script>location='wodericheng_jiaoshi';</script>");
//					} else {
//						out.print("<script>location='wodebeiwanglu_jiaoshi';</script>");
//					}
				}
			} else {
				response.sendRedirect("logout");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "show_JiaoxueLou") // 根据校区，显示所有教学楼
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

	@RequestMapping(value = "show_JiaoShi") // 根据教学楼，显示所有教室
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

	@RequestMapping(value = "show_jiaoShiByXiaoQuid")
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

	@RequestMapping("wodericheng_jiaoshi")
	public ModelAndView wodericheng_laoshi(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (Util.checkSession(request)) {
			mv.addObject("t", new Date().getTime());
			mv.setViewName("jiaoshi/wodericheng_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping("chaxunrichengbydate_jiaoshi")
	@ResponseBody
	public JSONObject chaxunrichengbydate(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		JSONObject json = new JSONObject();
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isJiaoShi(request)) {
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
			List<HuoDong> huodongs = new ArrayList<>();
			List<KeCheng> kechengs = new ArrayList<>();
			List<BeiWL> beiwanglus = new ArrayList<>();
			List<TiXing> tixings = new ArrayList<>();
			List<YiJianXiang> yijianxiangs = new ArrayList<>();
			if (user != null) {
				String xuexiaoid = yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString();
				paramMap.put("riqi", riqi);
				paramMap.put("xueXiaoID", xuexiaoid);
				XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(paramMap);
				if (xueQi == null) {
					returnMap.put("kechengs", kechengs);
				} else {
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

					paramMap.put("xueqiid", xueQi.getXueqiid().toString());
					paramMap.put("renkelaoshiid", user.getYonghuid().toString());
					paramMap.put("zhou", zhou + "");
					paramMap.put("zhouci", String.valueOf(zhouci));
					List<Map<String, Object>> keChengs = kechengService.getKeChengByRenKeLaoShiID(paramMap);
					for (Map<String, Object> maps : keChengs) {
						if (maps.containsKey("shiJianLeiXing") && maps.get("shiJianLeiXing").toString().equals("1")) {
							if (zhou >= Integer.parseInt(maps.get("kaiShiZhou").toString())
									&& zhou <= Integer.parseInt(maps.get("jieShuZhou").toString())
									&& zhouci == Integer.parseInt(maps.get("zhouCi").toString())) {
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
						if (maps.containsKey("shiJianLeiXing") && maps.get("shiJianLeiXing").toString().equals("2")) {
							if (zhou >= Integer.parseInt(maps.get("kaiShiZhou").toString())
									&& zhou <= Integer.parseInt(maps.get("jieShuZhou").toString())
									&& zhouci == Integer.parseInt(maps.get("zhouCi").toString())) {
								if (zhou % 2 != 0) {
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
						if (maps.containsKey("shiJianLeiXing") && maps.get("shiJianLeiXing").toString().equals("3")) {
							if (zhou >= Integer.parseInt(maps.get("kaiShiZhou").toString())
									&& zhou <= Integer.parseInt(maps.get("jieShuZhou").toString())
									&& zhouci == Integer.parseInt(maps.get("zhouCi").toString())) {
								if (zhou % 2 == 0) {
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
						if (maps.containsKey("shiJianLeiXing") && maps.get("shiJianLeiXing").toString().equals("4")) {
							if (zhou == Integer.parseInt(maps.get("kaiShiZhou").toString())
									&& zhouci == Integer.parseInt(maps.get("zhouCi").toString())) {
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
						if (maps.containsKey("shiJianLeiXing") && maps.get("shiJianLeiXing").toString().equals("6")) {
							if (zhou == Integer.parseInt(maps.get("kaiShiZhou").toString())
									&& zhouci == Integer.parseInt(maps.get("zhouCi").toString())) {
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
					returnMap.put("kechengs", kechengs);
				}
				paramMap.put("renid", user.getYonghuid().toString());
				paramMap.put("renleixing", "2");
				huodongs = huodongService.getAllByRiQiAndRenIDAndRenLeiXing(paramMap);
				for (HuoDong huoDong : huodongs) {
					paramMap.put("huodongid", huoDong.getHuodongid().toString());
					List<Map<String, Object>> canyuren = huodongService
							.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
					List<Map<String, Object>> jujueren = huodongService
							.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
					int i = 0;
					if (jujueren.size() > 0 && jujueren != null) {
						for (Map<String, Object> map : jujueren) {
							if (map.get("juJueRenID").toString().equals(user.getYonghuid().toString())
									&& map.get("leiXing").toString().equals("2")) {
								huoDong.setZhuangtai(2);
								i = 1;
								break;
							}
						}
					}
					if (canyuren.size() > 0 && canyuren != null) {
						for (Map<String, Object> map : canyuren) {
							if (map.get("canYuRenID").toString().equals(user.getYonghuid().toString())
									&& map.get("leiXing").toString().equals("2")) {
								huoDong.setZhuangtai(1);
								i = 1;
								break;
							}
						}
					}
					if (huoDong.getTianjiarenid() == user.getYonghuid() && huoDong.getTianjiarenleixing() == 2) {
						huoDong.setZhuangtai(1);
						i = 1;
					}
					if (i == 0) {
						huoDong.setZhuangtai(0);
					}
				}

				paramMap.put("kaishi", riqi + " 00:00");
				paramMap.put("jieshu", nextriqi + " 00:00");
				beiwanglus = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(paramMap);
				
				paramMap.put("kaishishijian", riqi + " 00:00:00");
				paramMap.put("jieshushijian", nextriqi + " 00:00:00");
				tixings = tixingService.getTiXingByjieShouRenIDAndRiQi(paramMap);
				// yijianxiangs =
				// yiJianXiangService.getYiJianXiangByjieShouRenIDAndRiQi(paramMap);

				returnMap.put("huodongs", huodongs);
				returnMap.put("beiwanglus", beiwanglus);
				returnMap.put("tixings", tixings);
				returnMap.put("yijianxiangs", yijianxiangs);

			} else {
				returnMap.put("huodongs", huodongs);
				returnMap.put("kechengs", kechengs);
				returnMap.put("beiwanglus", beiwanglus);
				returnMap.put("tixings", tixings);
				returnMap.put("yijianxiangs", yijianxiangs);
			}
			json.put("shuju", returnMap);
		} else

		{
			response.sendRedirect("login");
			return null;
		}

		return json;
	}

	@RequestMapping("richeng_huodong_jiaoshi")
	public ModelAndView riChengHuoDong(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!Util.checkSession(request)) {
			response.sendRedirect("login");
			return null;
		}
		if (!Util.isJiaoShi(request)) {
			response.sendRedirect("logout");
			return null;
		}
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			if (user != null) {
				String id = request.getParameter("id");
				String zhuangtai = request.getParameter("zhuangtai");
				String qufen = request.getParameter("qufen");
				HuoDong huodong = null;
				List<HuoDong> huodongs = new ArrayList<>();
				if (id == null || id == "") {
					huodong = new HuoDong();
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

						huodongs.add(huodong);
						mv.addObject("huodongs", huodongs);
					} else {
						mv.addObject("huodongs", huodongs);
					}
					mv.addObject("qufen", qufen);
					mv.addObject("renkelaoshiid", user.getYonghuid());
					mv.addObject("shijian", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				}
			} else {
				response.sendRedirect("login");
				return null;
			}
		}
		mv.setViewName("jiaoshi/myhuodong_jiaoshi");
		return mv;
	}

	@RequestMapping(value = "chakancanyuren_jiaoshi") // 查看参与人
	public ModelAndView chakancanyuren_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
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
			String canyuren = huoDong.getCanyuren();
			String canyurens[] = canyuren.split(";");
			String sign = "1," + user.getYonghuid().toString() + ",0";
			String sign1 = "1," + user.getYonghuid().toString() + ",1";
			String sign2 = "1," + user.getYonghuid().toString() + ",2";
			int i = 0;
			for (String string : canyurens) {
				if (string.equals(sign) || string.equals(sign1) || string.equals(sign2)) {
					i = 1;
				}
			}
			if (i == 0) {
				response.sendRedirect("logout");
				return null;
			}
			List<Map<String, String>> maps = new ArrayList<>();
			List<Map<String, String>> maps2 = new ArrayList<>();
			for (String string : canyurens) {
				String canyr[] = string.split(",");
				Map<String, String> map = new HashMap<>();
				if (canyr[0].equals("0")) {
					XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(canyr[1]));
					String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
					map.put("banjimingcheng", banjimingcheng);
					map.put("xueshengid", canyr[1]);
					map.put("xuehao", xueSheng.getXuehao());
					map.put("xingming", xueSheng.getXingming());
					map.put("canyuzhuangtai", canyr[2]);
					if (canyr[2].equals("2")) {
						map.put("liyou", canyr[3]);
					}
					maps.add(map);
				}
				if (canyr[0].equals("1")) {
					YongHu jiaoshi = yonghuService.selectYongHuByID(Integer.parseInt(canyr[1]));
					map.put("jiaoshiid", canyr[1]);
					map.put("xingming", jiaoshi.getYonghuming());
					map.put("canyuzhuangtai", canyr[2]);
					maps2.add(map);
				}
			}
			mv.addObject("huodong", huoDong);
			mv.addObject("student", maps);
			mv.addObject("fudaoyuan", maps2);
			mv.setViewName("jiaoshi/chakancanyuren_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "myhuodong_jiaoshi") // 我的活动
	public ModelAndView myhuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			List<HuoDong> huoDongs = new ArrayList<>();
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "2");
			huoDongs = huodongService.getAllByRenIDAndRenLeiXing(map);
			for (HuoDong huoDong : huoDongs) {
				map.put("huodongid", huoDong.getHuodongid().toString());

				List<Map<String, Object>> canyuren = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				List<Map<String, Object>> jujueren = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);

				if (huoDong.getTianjiarenid() == user.getYonghuid()) {
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
			mv.addObject("renkelaoshiid", user.getYonghuid().toString());
			mv.addObject("pages", pages);
			mv.addObject("page", page);
			mv.setViewName("jiaoshi/myhuodong_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "addhuodong_jiaoshi") // 新增活动
	public ModelAndView addhuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			mv.setViewName("jiaoshi/addhuodong_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "savehuodong_jiaoshi") // 保存新增活动
	@ResponseBody
	public String savehuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String shuoming = request.getParameter("shuoming");
			String riqi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			map.put("huodongmingcheng", mingcheng);
			map.put("didian", didian);
			map.put("shuoming", shuoming);
			map.put("tianjiarenid", user.getYonghuid().toString());
			// map.put("canyuren", canyuren);
			map.put("riqi", riqi);
			map.put("kaishishijian", kaishishijian);
			map.put("jieshushijian", jieshushijian);
			map.put("tianjiarenleixing", "2");
			map.put("leixing", "2");
			int i = huodongService.insert(map);
			if (i != 0) {
//				out.print("<script>alert('新增成功！');</script>");
//				out.print("<script>location='wodericheng_jiaoshi';</script>");
				return "success";
			} else {
//				out.print("<script>alert('新增失败！');</script>");
//				out.print("<script>location='wodericheng_jiaoshi';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "xiugaihuodong_jiaoshi") // 修改活动
	public ModelAndView xiugaihuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
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
			mv.addObject("huodong", huoDong);
			mv.addObject("qufen", qufen);
			mv.setViewName("jiaoshi/xiugaihuodong_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "saveupdatehuodong_jiaoshi") // 保存修改活动
	@ResponseBody
	public String saveupdatehuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			YongHu user = (YongHu) session.getAttribute("user");
			// 获取学校id
			int yuanxiid = user.getYuanxiid();
			String xueXiaoID = yuanxiService.selectByPrimaryKey(yuanxiid).getXuexiaoid().toString();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
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
//			PrintWriter out = response.getWriter();
			String mingcheng = request.getParameter("mingcheng");
			String didian = request.getParameter("didian");
			String shuoming = request.getParameter("shuoming");
			String riqi = request.getParameter("riqi");
			String kaishishijian = request.getParameter("kaishishijian");
			String jieshushijian = request.getParameter("jieshushijian");
			HuoDong huoDong2 = new HuoDong();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			huoDong2.setHuodongid(Integer.parseInt(huodongid));
			huoDong2.setHuodongmingcheng(mingcheng);
			huoDong2.setDidian(didian);
			huoDong2.setRiqi(df.parse(riqi));
			huoDong2.setKaishishijian(kaishishijian);
			huoDong2.setJieshushijian(jieshushijian);
			huoDong2.setTianjiarenid(user.getYonghuid());
			huoDong2.setTianjiarenleixing(2);
			huoDong2.setShuoming(shuoming);
			huoDong2.setLeixing(2);
			int j = huodongService.updateByPrimaryKey(huoDong2);
			if (j != 0) {
//				if(qufen.equals("1")){
//					out.print("<script>alert('修改成功！');</script>");
//					out.print("<script>location='wodericheng_jiaoshi';</script>");
//				}else{
//					out.print("<script>alert('修改成功！');</script>");
//					out.print("<script>location='myhuodong_jiaoshi';</script>");
//				}
				return "success";
			} else {
//				if(qufen.equals("1")){
//					out.print("<script>alert('修改失败！');</script>");
//					out.print("<script>location='wodericheng_jiaoshi';</script>");
//				}else{
//					out.print("<script>alert('修改失败！');</script>");
//					out.print("<script>location='myhuodong_jiaoshi';</script>");
//				}
			}

		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "delhuodong_jiaoshi") // 拒绝活动——将参与状态改为2，参与人还存在
	@ResponseBody
	public String delhuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			String qufen = request.getParameter("qufen");
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
			int sign = 0;
			map.put("huodongid", id);
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "2");
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
//				if(qufen.equals("1")){
//					out.print("<script>alert('拒绝成功！');</script>");
//					out.print("<script>location='wodericheng_jiaoshi';</script>");
//				}else{
//					out.print("<script>alert('拒绝成功！');</script>");
//					out.print("<script>location='myhuodong_jiaoshi';</script>");
//				}
				return "success";
			} else {
//				if(qufen.equals("1")){
//					out.print("<script>alert('拒绝失败！');</script>");
//					out.print("<script>location='wodericheng_jiaoshi';</script>");
//				}else{
//					out.print("<script>alert('拒绝失败！');</script>");
//					out.print("<script>location='myhuodong_jiaoshi';</script>");
//				}
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "newhuodong_jiaoshi") // 待确认活动
	public ModelAndView newhuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		if (Util.checkSession(request)) {
			YongHu user = (YongHu) session.getAttribute("user");
			Map<String, String> map = new HashMap<>();
			map.put("renid", user.getYonghuid().toString());
			map.put("renleixing", "2");
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
				} 
				else {
					faqiren = tianjiaren.getYonghuxingming();
					if (tianjiarenid.equals(user.getYonghuid().toString())) {
						if (juJueRen != null && juJueRen.size() > 0) {
							huoDong.setFaqiren(faqiren);
							huoDong.setZhuangtai(2);
							huoDongs.add(huoDong);
						} 
					} else {
						if (juJueRen == null || juJueRen.size() <= 0) {
							if (canYuRen == null || juJueRen.size() <= 0) {
								huoDong.setFaqiren(tianjiaren.getYonghuxingming());
								huoDong.setZhuangtai(0);
								huoDongs.add(huoDong);
							} 
						} else {
							huoDong.setFaqiren(faqiren);
							huoDong.setZhuangtai(2);
							huoDongs.add(huoDong);
						}
					}
				}
			}
			mv.addObject("huodong", huoDongs);
			mv.setViewName("jiaoshi/newhuodong_jiaoshi");
			return mv;
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "confirmhuodong_jiaoshi") // 确认参加活动——将参与状态改为1
	@ResponseBody
	public String confirmhuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		PrintWriter out = response.getWriter();
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
			map.put("renleixing", "2");
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
//				response.setContentType("text/html; charset=utf-8");
//				out.print("<script>alert('参与成功！');</script>");
//				out.print("<script>location='newhuodong_jiaoshi';</script>");
				return "success";
			} else {
//				response.setContentType("text/html; charset=utf-8");
//				out.print("<script>alert('参与失败！');</script>");
//				out.print("<script>location='newhuodong_jiaoshi';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}

	@RequestMapping(value = "canyuhuodong_jiaoshi") // 确认参加活动——将参与状态改为1
	@ResponseBody
	public String canyuhuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
//		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
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
			map.put("renleixing", "2");
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
//				response.setContentType("text/html; charset=utf-8");
//				if(qufen.equals("1")){
//					out.print("<script>alert('参与成功！');</script>");
//					out.print("<script>location='wodericheng_jiaoshi';</script>");
//				}else{
//					out.print("<script>alert('参与成功！');</script>");
//					out.print("<script>location='myhuodong_jiaoshi';</script>");
//				}
				return "success";
			} else {
//				response.setContentType("text/html; charset=utf-8");
//				if(qufen.equals("1")){
//					out.print("<script>alert('参与失败！');</script>");
//					out.print("<script>location='wodericheng_jiaoshi';</script>");
//				}else{
//					out.print("<script>alert('参与失败！');</script>");
//					out.print("<script>location='myhuodong_jiaoshi';</script>");
//				}
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
	@RequestMapping(value = "cleanhuodong_jiaoshi") // 彻底清除活动,将参与人剔除，活动还存在
	@ResponseBody
	public String cleanhuodong_jiaoshi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
		Map<String, String> map = new HashMap<>();
		if (session.getAttribute("user") != null && session.getAttribute("user") != "") {
			String id = request.getParameter("id");
			if (!Util.isNumeric(id)) {
				response.sendRedirect("logout");
				return null;
			}
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong == null) {
				response.sendRedirect("logout");
				return null;
			}
			map.put("huodongid", id);
			int sign = 0;
			try {
				if (huoDong.getLeixing() == 1) {
					huodongService.delete_huodongbanji(map);
				}
				if (huoDong.getLeixing() == 2) {
					huodongService.delete_huodongren(map);
				}
				sign = 1;
			} catch (Exception e) {
				e.printStackTrace();
				sign = 0;
			}
			if (sign == 1) {
//				out.print("<script>alert('删除成功！');</script>");
//				out.print("<script>location='newhuodong_jiaoshi';</script>");
				return "success";
			} else {
//				out.print("<script>alert('删除失败！');</script>");
//				out.print("<script>location='newhuodong_jiaoshi';</script>");
			}
		} else {
			response.sendRedirect("login");
		}
		return null;
	}
}
