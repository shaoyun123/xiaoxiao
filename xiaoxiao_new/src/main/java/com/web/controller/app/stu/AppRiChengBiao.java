package com.web.controller.app.stu;

import java.io.IOException;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sun.tools.internal.ws.processor.model.Request;
import com.web.model.BeiWL;
import com.web.model.ChaQinAnPai;
import com.web.model.FuDaoYuan;
import com.web.model.HuoDong;
import com.web.model.JCSJ;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.JieCiFangAn;
import com.web.model.KeCheng;
import com.web.model.BeiZhu;
import com.web.model.SheTuanBuMenJiBenXinXin;
import com.web.model.SheTuanBuMenXinXin;
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
import com.web.service.ChaQinService;
import com.web.service.FuDaoYuanService;
import com.web.service.HuoDongService;
import com.web.service.JCSJService;
import com.web.service.JiGuangService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.JieCiFangAnService;
import com.web.service.BeiZhuService;
import com.web.service.KeChengService;
import com.web.service.SheTuanService;
import com.web.service.TiXingService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.util.DateUtil;
import com.web.util.Tools;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class AppRiChengBiao {
	@Autowired
	private BeiWLService beiwlService;
	@Autowired
	private BanJiService banjiService;
	@Autowired
	private XueShengService xueshengService;
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private HuoDongService huodongService;
	@Autowired
	private JCSJService jcsjService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private SheTuanService shetuanSerivce;
	@Autowired
	private XueQiService xueqiService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private TiXingService tixingService;
	@Autowired
	private JieCiFangAnService jieCiFangAnService;
	@Autowired
	private ChaQinService chaQinService;
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private BeiZhuService beiZhuService;

	@RequestMapping(value = "app_getshijianlist")
	@ResponseBody
	public JSONObject app_getshijianlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xuexiaoxuehao = request.getParameter("xuexiaoxuehao");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			if (xs.getXuexiaoXuehao().equals(xuexiaoxuehao)) {
//				String xuexiaoid = xuexiaoxuehao.split("_")[0];
				String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
				List<String> xueNianList = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xuexiaoid));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String riqi = sdf.format(new Date());
				Map<String, String> map = new HashMap<>();
				map.put("xueXiaoID", xuexiaoid);
				map.put("riqi", riqi);
				Map<String, Object> xueQi = new HashMap<>();
				xueQi = xueqiService.getMapXueQiByxueXiaoIDandriQi(map);
				if (xueQi == null) {
					List<Map<String, Object>> xueQis = xueqiService.getNewerXueQi(map);
					if (xueQis != null && xueQis.size() > 0) {
						xueQi = xueQis.get(0);
					}
				}
				int weekNum = Util.WeeksBetweenDays(sdf.parse(xueQi.get("kaiShiRiQi").toString()),
						sdf.parse(xueQi.get("jieShuRiQi").toString()));
				String xueNian = xueQi.get("ruXueNianFen").toString();
				String xueqi = xueQi.get("xueQi").toString();

				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(sdf.parse(xueQi.get("kaiShiRiQi").toString()));
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
				json.put("zhou", zhou);
				json.put("xuenianlist", xueNianList);
				json.put("weeknum", weekNum);
				json.put("xuenian", xueNian);
				json.put("xueqi", xueqi);
			} else {
				return null;
			}
		}
		return json;
	}

	// 我的日程一进入获取当前年月日并返回
	@RequestMapping(value = "app_GetNianYueRi")
	@ResponseBody
	public List<String> app_GetNianYueRi(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = code + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			List<String> list = new ArrayList<>();
			Date date = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			list.add(Integer.toString(year));
			list.add(Integer.toString(month));
			list.add(Integer.toString(day));
			String riqi = df.format(date);
			list.add(riqi);
			return list;
		} else {
			return null;
		}
	}

	// 获得学年学期
	@RequestMapping(value = "app_GetNowXueNianXueQi")
	@ResponseBody
	public List<String> app_GetNowXueNianXueQi(HttpServletRequest request) throws ParseException {
		List<String> datetime = new ArrayList<String>();
		String code = request.getParameter("CODE");
		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			String xueXiaoXueHao[] = data[1].split("_");
//			String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banjiService.findXueXiaoIDByBanJiID(xueshengService.selectByPrimaryKey(Integer.parseInt(data[0])).getBanjiid());
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", xueXiaoID);
			// XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(map);
			Map<String, Object> xueQi = new HashMap<>();
			xueQi = xueqiService.getMapXueQiByxueXiaoIDandriQi(map);
			if (xueQi == null) {
				List<Map<String, Object>> xueQis = xueqiService.getNewerXueQi(map);
				if (xueQis != null && xueQis.size() > 0) {
					xueQi = xueQis.get(0);
				}
			}
			if (xueQi != null) {
				// 计算今天是当前学期的第几周
				Date kaishiriqi = format.parse(xueQi.get("kaiShiRiQi").toString());
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
				datetime.add(xueQi.get("ruXueNianFen").toString());
				datetime.add(xueQi.get("xueQi").toString());
				datetime.add(String.valueOf(zhou));
				datetime.add(format.format(kaishiriqi).toString());
			}
		} else {
			return null;
		}
		return datetime;
	}

	// 我的课程一进入根据当前年月日查询该学期课程
	@RequestMapping(value = "app_WoDeKeCheng")
	@ResponseBody
	public JSONObject app_WoDeKeCheng(HttpServletRequest request) throws ParseException {
		JSONObject json = new JSONObject();
		String xueshengid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<Object, Object> maps = new HashMap<>();
		if (str.equals(token)) {
			XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			String zhous = request.getParameter("zhou");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> mpp = new HashMap<>();
			Map<String, Object> xueQi2 = new HashMap<>();
			String xuexiaoxuehao = request.getParameter("xuexiaoXuehao");
			String xueXiaoXueHao[] = xuexiaoxuehao.split("_");
//			String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banjiService.findXueXiaoIDByBanJiID(xueSheng.getBanjiid());;
			if ((xuenian == null || "".equals(xuenian)) || (xueqi == null || "".equals(xueqi))) {
				mpp.put("riqi", sdf.format(date));
				mpp.put("xueXiaoID", xueXiaoID);
				// XueQi xueQi2 = xueqiService.getByxueXiaoIDandriQi(mpp);
				xueQi2 = xueqiService.getMapXueQiByxueXiaoIDandriQi(mpp);
				xuenian = xueQi2.get("ruXueNianFen").toString();
				xueqi = xueQi2.get("xueQi").toString();

			} else {
				mpp.put("xueqi", xueqi);
				mpp.put("nianfen", xuenian.split("~")[0]);
				mpp.put("xuexiaoid", xueXiaoID);
				xueQi2 = xueqiService.getMapXueQiByXueXiaoIDAndXueNianAndXueQi(mpp);
			}

			if (xueQi2 == null) {
				json.put("keChengs", "");
				json.put("jcsj", "");
				json.put("xuenian", "");
				json.put("xueqi", "");
				json.put("zhou", "");
				return json;
			}
			// String xuenian = xueQi2.getXuenian();
			// String xueqi = xueQi2.getXueqi().toString();
			Date kaishiriqi = sdf.parse(xueQi2.get("kaiShiRiQi").toString());
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			int zhou = 0;
			if (zhous == null || "".equals(zhous)) {
				cal1.setTime(kaishiriqi);
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
				zhou = timeDistance / 7 + 1;
			} else {
				zhou = Integer.parseInt(zhous);
			}

			Map<String, Integer> map2 = new HashMap<>();
			map2.put("zhuangtai", 1);
			map2.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map2);
			if (jieCiFangAn == null) {
				json.put("keChengs", "");
				json.put("jcsj", "");
				json.put("xuenian", xuenian);
				json.put("xueqi", xueqi);
				json.put("zhou", zhou);
				return json;
			}

			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			Map<String, String> map = new HashMap<>();
			List<Map<String, Object>> keChengs = kechengService.getByBanJiIDAndXueShengID(Integer.parseInt(banjiid),
					xueshengid);
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (Map<String, Object> keChengMap : keChengs) {
				KeCheng kecheng = new KeCheng();
				kecheng.setLeixing(Integer.parseInt(keChengMap.get("leiXing").toString()));
				String tianjiarenid = keChengMap.get("tianJiaRenID").toString();
				kecheng.setTianjiarenid(tianjiarenid);
				String[] strs = tianjiarenid.split(",");
				YongHu yongHu = new YongHu();
				if (strs.length != 1) {
					String banjiid1 = xueSheng.getBanjiid() + ",%";
					String banjiid2 = "%," + xueSheng.getBanjiid() + "%";
					FuDaoYuan fuDaoYuan = fuDaoYuanService.getBybanJiID(banjiid1, banjiid2);
					for (String string : strs) {
						if (string.equals(fuDaoYuan.getFudaoyuanid().toString())) {
							yongHu = yonghuService.selectYongHuByID(Integer.parseInt(string));
						}
					}
				} else {
					yongHu = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
				}
				if (yongHu != null) {
					if (yongHu.getJueseid().equals(2)) {
						kecheng.setTianjiarenleixing("辅导员");
					} else {
						kecheng.setTianjiarenleixing("教师");
					}
				} else {
					kecheng.setTianjiarenleixing("学生");
				}
				// 课程备注
				map.put("yinyongid", keChengMap.get("ID").toString());
				map.put("leixing", "1");
				map.put("beizhurenid", xueshengid);
				map.put("status", status);
				List<BeiZhu> beiZhuS = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(map);
				if (beiZhuS != null && beiZhuS.size() > 0) {
					for (BeiZhu beiZhu : beiZhuS) {
						kecheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
					}
				}
				if (xueQi2.get("ID").toString().equals(keChengMap.get("xueQiID").toString())) {
					// 停课情况
					List<String> tingkes = new ArrayList<>();
					// for (Map<String, Object> tingKe : keChengs) {
					// if
					// (keChengMap.get("ID").toString().equals(tingKe.get("ID").toString()))
					// {
					// if (tingKe.containsKey("shiJianLeiXing")
					// && tingKe.get("shiJianLeiXing").toString().equals("5")) {
					// if ((zhou +
					// "").equals(tingKe.get("kaiShiZhou").toString())) {
					// tingkes.add(tingKe.get("zhouCi").toString());
					// }
					// }
					// }
					// }

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("1")) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (String zhouCi : tingkes) {
								if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
									i = 1;
									break;
								}
							}
							if (i == 0) {
								kecheng.setDanshuangzhoushuoming("1");
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
							kecheng.setDanshuangzhoushuoming("1");
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
							&& keChengMap.get("shiJianLeiXing").toString().equals("2")) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (String zhouCi : tingkes) {
								if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
									i = 1;
									break;
								}
							}
							if (i == 0) {
//								if (zhou % 2 != 0) {
								    kecheng.setDanshuangzhoushuoming("2");
									kecheng.setDanshuangzhou(1);
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
//								}
							}
						} else {
//							if (zhou % 2 != 0) {
								kecheng.setDanshuangzhou(1);
								kecheng.setDanshuangzhoushuoming("2");
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
//							}
						}
					}

					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("3")) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (String zhouCi : tingkes) {
								if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
									i = 1;
									break;
								}
							}
							if (i == 0) {
//								if (zhou % 2 == 0) {
									kecheng.setDanshuangzhou(2);
									kecheng.setDanshuangzhoushuoming("3");
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
//								}
							}
						} else {
//							if (zhou % 2 == 0) {
								kecheng.setDanshuangzhou(2);
								kecheng.setDanshuangzhoushuoming("3");
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
//							}
						}
					}
					if (keChengMap.containsKey("shiJianLeiXing")
							&& keChengMap.get("shiJianLeiXing").toString().equals("6")) {
						kecheng.setKechenghao(6);
						kecheng.setDanshuangzhoushuoming("6");
						kecheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
						kecheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
						kecheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
						kecheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
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
			List<Map<String, String>> tingKeMaps = new ArrayList<>();
			for (Map<String, Object> map22 : keChengs) {
				if (xueQi2.get("ID").toString().equals(map22.get("xueQiID").toString())) {
					if (map22.containsKey("shiJianLeiXing") && map22.get("shiJianLeiXing").toString().equals("5")) {
						Map<String, String> tingKeMap = new HashMap<>();
						tingKeMap.put("ID", map22.get("ID").toString());
						tingKeMap.put("jiaoShiID", map22.get("jiaoShiID").toString());
						tingKeMap.put("zhouCi", map22.get("zhouCi").toString());
						tingKeMap.put("kaiShiJieCi", map22.get("kaiShiJieCi").toString());
						tingKeMap.put("jieShuJieCi", map22.get("jieShuJieCi").toString());
						tingKeMap.put("kaiShiZhou", map22.get("kaiShiZhou").toString());
						tingKeMaps.add(tingKeMap);
					}
				}
			}
			for (int i = 0; i < keChengs.size(); i++) {
				if (keChengs.get(i).containsKey("xueQiID")
						&& keChengs.get(i).get("xueQiID").toString().equals(xueQi2.get("ID").toString())) {
					if (keChengs.get(i).containsKey("shiJianLeiXing")
							&& keChengs.get(i).get("shiJianLeiXing").toString().equals("4")) {
						Map<String, String> firstMap = new HashMap<>();
						String firstZhou = "";
						Map<String, String> secondMap = new HashMap<>();
						String secondZhou = "";
						int ss = 0;
						if (tingKeMaps != null && tingKeMaps.size() > 0) {
							for (Map<String, String> map22 : tingKeMaps) {
								if (map22.get("ID").toString()
										.equals(keChengs.get(i).get("ID").toString())) {
									if (map22.get("zhouCi").toString()
											.equals(keChengs.get(i).get("zhouCi").toString())
											&& map22.get("kaiShiJieCi").toString()
													.equals(keChengs.get(i).get("kaiShiJieCi").toString())
											&& map22.get("jieShuJieCi").toString()
													.equals(keChengs.get(i).get("jieShuJieCi").toString())
											&& map22.get("kaiShiZhou").toString()
													.equals(keChengs.get(i).get("kaiShiZhou").toString())
											&& map22.get("jiaoShiID").toString()
													.equals(keChengs.get(i).get("jiaoShiID").toString())) {
										ss = 1;
									}
								}
							}
						}
						if(ss == 0){
							firstZhou = keChengs.get(i).get("kaiShiZhou").toString() + ",";
							firstMap.put("id", keChengs.get(i).get("ID").toString());
							firstMap.put("kechengmingcheng", keChengs.get(i).get("keChengMingCheng").toString());
							firstMap.put("renkejiaoshi", keChengs.get(i).get("renKeJiaoShi").toString());
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
						for (int j = 0; j < keChengs.size(); j++) {
							if (i != j
									&& keChengs.get(i).get("ID").toString().equals(keChengs.get(j).get("ID").toString())
									&& keChengs.get(j).containsKey("shiJianLeiXing") && keChengs.get(j).get("shiJianLeiXing").toString().equals("4")) {
								if (keChengs.get(i).get("zhouCi").toString()
										.equals(keChengs.get(j).get("zhouCi").toString())
										&& keChengs.get(i).get("kaiShiJieCi").toString()
												.equals(keChengs.get(j).get("kaiShiJieCi").toString())
										&& keChengs.get(i).get("jieShuJieCi").toString()
												.equals(keChengs.get(j).get("jieShuJieCi").toString())) {
									int l = 0;
									if (tingKeMaps != null && tingKeMaps.size() > 0) {
										for (Map<String, String> map22 : tingKeMaps) {
											if (map22.get("ID").toString()
													.equals(keChengs.get(j).get("ID").toString())) {
												if (map22.get("zhouCi").toString()
														.equals(keChengs.get(j).get("zhouCi").toString())
														&& map22.get("kaiShiJieCi").toString()
																.equals(keChengs.get(j).get("kaiShiJieCi").toString())
														&& map22.get("jieShuJieCi").toString()
																.equals(keChengs.get(j).get("jieShuJieCi").toString())
														&& map22.get("kaiShiZhou").toString()
																.equals(keChengs.get(j).get("kaiShiZhou").toString())
														&& map22.get("jiaoShiID").toString()
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
										for (Map<String, String> map22 : tingKeMaps) {
											if (map22.get("ID").toString()
													.equals(keChengs.get(j).get("ID").toString())) {
												if (map22.get("zhouCi").toString()
														.equals(keChengs.get(j).get("zhouCi").toString())
														&& map22.get("kaiShiJieCi").toString()
																.equals(keChengs.get(j).get("kaiShiJieCi").toString())
														&& map22.get("jieShuJieCi").toString()
																.equals(keChengs.get(j).get("jieShuJieCi").toString())
														&& map22.get("kaiShiZhou").toString()
																.equals(keChengs.get(j).get("kaiShiZhou").toString())
														&& map22.get("jiaoShiID").toString()
																.equals(keChengs.get(j).get("jiaoShiID").toString())) {
													l = 1;
												}
											}
										}
									}
									if(l == 0){
										secondZhou += keChengs.get(j).get("kaiShiZhou").toString() + ",";
										secondMap.put("id", keChengs.get(j).get("ID").toString());
										secondMap.put("kechengmingcheng",
												keChengs.get(j).get("keChengMingCheng").toString());
										secondMap.put("renkejiaoshi", keChengs.get(j).get("renKeJiaoShi").toString());
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
						if(!"".equals(firstZhou) && firstZhou != null){
							firstMap.put("zhoushu", firstZhou.substring(0, firstZhou.lastIndexOf(",")));
						}
						if (firstMap != null && !(firstMap.isEmpty())) {
							KeCheng kecheng = new KeCheng();
							kecheng.setLeixing(Integer.parseInt(keChengs.get(i).get("leiXing").toString()));
							String tianjiarenid = keChengs.get(i).get("tianJiaRenID").toString();
							kecheng.setTianjiarenid(tianjiarenid);
							String[] strs = tianjiarenid.split(",");
							YongHu yongHu = new YongHu();
							if (strs.length != 1) {
								String banjiid1 = xueSheng.getBanjiid() + ",%";
								String banjiid2 = "%," + xueSheng.getBanjiid() + "%";
								FuDaoYuan fuDaoYuan = fuDaoYuanService.getBybanJiID(banjiid1, banjiid2);
								for (String string : strs) {
									if (string.equals(fuDaoYuan.getFudaoyuanid().toString())) {
										yongHu = yonghuService.selectYongHuByID(Integer.parseInt(string));
									}
								}
							} else {
								yongHu = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
							}
							if (yongHu != null) {
								if (yongHu.getJueseid().equals(2)) {
									kecheng.setTianjiarenleixing("辅导员");
								} else {
									kecheng.setTianjiarenleixing("教师");
								}
							} else {
								kecheng.setTianjiarenleixing("学生");
							}
							// 课程备注
							map.put("yinyongid", keChengs.get(i).get("ID").toString());
							map.put("leixing", "1");
							map.put("beizhurenid", xueshengid);
							map.put("status", status);
							List<BeiZhu> beiZhuS = beiZhuService
									.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(map);
							if (beiZhuS != null && beiZhuS.size() > 0) {
								for (BeiZhu beiZhu : beiZhuS) {
									kecheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
								}
							}
							kecheng.setId(Integer.parseInt(firstMap.get("id").toString()));
							kecheng.setBanJiMingCheng(firstMap.get("zhoushu").toString());
							int m = 0;
							for (KeCheng kc : keChengs2) {
								boolean a = kc.getId()==kecheng.getId();
								boolean b = kc.getId().toString().equals(kecheng.getId().toString());
								if (kc.getId().toString().equals(kecheng.getId().toString())) {
									if (kc.getBanJiMingCheng() != null) {
										String zhou1[] = kc.getBanJiMingCheng().split(",");
										String zhou2[] = kecheng.getBanJiMingCheng().split(",");
										for (String string : zhou1) {
											for (String s : zhou2) {
												if (string.equals(s)) {
													if(kc.getZhouci().toString().equals(firstMap.get("zhouci").toString())){
														m = 1;
														break;
													}
												}
											}
											if (m == 1) {
												break;
											}
										}
										if (m == 1) {
											break;
										}
									}
								}
							}
							if (m == 0) {
								kecheng.setKechenghao(3);
								kecheng.setDanshuangzhoushuoming("4");
								kecheng.setKechengmingcheng(firstMap.get("kechengmingcheng").toString());
								kecheng.setRenkejiaoshi(firstMap.get("renkejiaoshi").toString());
								kecheng.setKaishijieci(Integer.parseInt(firstMap.get("kaishijieci").toString()));
								kecheng.setJieshujieci(Integer.parseInt(firstMap.get("jieshujieci").toString()));
								kecheng.setXiaoquming(firstMap.get("xiaoquming").toString());
								kecheng.setJiaoshiming(firstMap.get("jiaoshiming").toString());
								kecheng.setZhouci(Integer.parseInt(firstMap.get("zhouci").toString()));
								keChengs2.add(kecheng);
							}
						}
						if (secondZhou != null && !"".equals(secondZhou)) {
							secondMap.put("zhoushu", secondZhou.substring(0, secondZhou.lastIndexOf(",")));
							KeCheng kecheng = new KeCheng();
							kecheng.setLeixing(Integer.parseInt(keChengs.get(i).get("leiXing").toString()));
							String tianjiarenid = keChengs.get(i).get("tianJiaRenID").toString();
							kecheng.setTianjiarenid(tianjiarenid);
							String[] strs = tianjiarenid.split(",");
							YongHu yongHu = new YongHu();
							if (strs.length != 1) {
								String banjiid1 = xueSheng.getBanjiid() + ",%";
								String banjiid2 = "%," + xueSheng.getBanjiid() + "%";
								FuDaoYuan fuDaoYuan = fuDaoYuanService.getBybanJiID(banjiid1, banjiid2);
								for (String string : strs) {
									if (string.equals(fuDaoYuan.getFudaoyuanid().toString())) {
										yongHu = yonghuService.selectYongHuByID(Integer.parseInt(string));
									}
								}
							} else {
								yongHu = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
							}
							if (yongHu != null) {
								if (yongHu.getJueseid().equals(2)) {
									kecheng.setTianjiarenleixing("辅导员");
								} else {
									kecheng.setTianjiarenleixing("教师");
								}
							} else {
								kecheng.setTianjiarenleixing("学生");
							}
							// 课程备注
							map.put("yinyongid", keChengs.get(i).get("ID").toString());
							map.put("leixing", "1");
							map.put("beizhurenid", xueshengid);
							map.put("status", status);
							List<BeiZhu> beiZhuS = beiZhuService
									.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(map);
							if (beiZhuS != null && beiZhuS.size() > 0) {
								for (BeiZhu beiZhu : beiZhuS) {
									kecheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
								}
							}
							kecheng.setId(Integer.parseInt(secondMap.get("id").toString()));
							kecheng.setBanJiMingCheng(secondMap.get("zhoushu").toString());
							int m = 0;
							for (KeCheng kc : keChengs2) {
								if (kc.getId().toString().equals(kecheng.getId().toString())) {
									if (kc.getBanJiMingCheng() != null) {
										String zhou1[] = kc.getBanJiMingCheng().split(",");
										String zhou2[] = kecheng.getBanJiMingCheng().split(",");
										for (String string : zhou1) {
											for (String s : zhou2) {
												if (string.equals(s)) {
													if(kc.getZhouci().toString().equals(secondMap.get("zhouci").toString())){
														m = 1;
														break;
													}
												}
											}
											if (m == 1) {
												break;
											}
										}
										if (m == 1) {
											break;
										}
									}
								}
							}
							if (m == 0) {
								kecheng.setKechenghao(3);
								kecheng.setDanshuangzhoushuoming("4");
								kecheng.setKechengmingcheng(secondMap.get("kechengmingcheng").toString());
								kecheng.setRenkejiaoshi(secondMap.get("renkejiaoshi").toString());
								kecheng.setKaishijieci(Integer.parseInt(secondMap.get("kaishijieci").toString()));
								kecheng.setJieshujieci(Integer.parseInt(secondMap.get("jieshujieci").toString()));
								kecheng.setXiaoquming(secondMap.get("xiaoquming").toString());
								kecheng.setJiaoshiming(secondMap.get("jiaoshiming").toString());
								kecheng.setZhouci(Integer.parseInt(secondMap.get("zhouci").toString()));
								keChengs2.add(kecheng);
							}
						}
					}
				}
			}
			for (KeCheng keCheng : keChengs2) {
				int isadd = 0;
				if (xueSheng.getIsbanzhang() != null && keCheng.getTianjiarenid().equals(xueshengid)
						&& xueSheng.getIsbanzhang()) {
					isadd = 1;
				} else {
					if (keCheng.getTianjiarenid().equals(xueshengid) && keCheng.getLeixing() == 2) {
						isadd = 1;
					} else {
						List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(keCheng.getId().toString());
						if (xuanXiuIDs != null && xuanXiuIDs.size() > 0) {
							for (String xuanxiuid : xuanXiuIDs) {
								if (xuanxiuid.equals(xueshengid) && !(keCheng.getTianjiarenid().equals(xueshengid))
										&& keCheng.getLeixing() == 2) {
									isadd = 1;
								}
							}
						}
					}
				}
				keCheng.setIsadd(isadd);
			}
			List<KeCheng> keChengs3 = new ArrayList<>();
			for(int i=0; i<keChengs2.size(); i++){
				List<Map<String,String>> mapS = new ArrayList<>();
				Map<String,String> dm = new HashMap<>();
				
				dm.put("kaishijieci", keChengs2.get(i).getKaishijieci().toString());
				dm.put("jieshujieci", keChengs2.get(i).getJieshujieci().toString());
				dm.put("zhouci", keChengs2.get(i).getZhouci().toString());
				dm.put("xiaoquming", keChengs2.get(i).getXiaoquming());
				dm.put("jiaoshiming", keChengs2.get(i).getJiaoshiming());
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("1")){
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", keChengs2.get(i).getJieshuzhou().toString());
				}
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("2")){
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", keChengs2.get(i).getJieshuzhou().toString());
					dm.put("danshuangzhou", keChengs2.get(i).getDanshuangzhou().toString());
				}
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("3")){
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", keChengs2.get(i).getJieshuzhou().toString());
					dm.put("danshuangzhou", keChengs2.get(i).getDanshuangzhou().toString());
				}
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("4")){
					dm.put("kechenghao", keChengs2.get(i).getKechenghao().toString());
					dm.put("banJiMingCheng", keChengs2.get(i).getBanJiMingCheng());
				}
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("6")){
					dm.put("kechenghao", keChengs2.get(i).getKechenghao().toString());
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
				}
				mapS.add(dm);
				for(int j=0; j<keChengs2.size(); j++){
					if(i != j ){
						if(keChengs2.get(i).getId().toString().equals(keChengs2.get(j).getId().toString())){
							Map<String,String> dmj = new HashMap<>();
							dmj.put("kaishijieci", keChengs2.get(j).getKaishijieci().toString());
							dmj.put("jieshujieci", keChengs2.get(j).getJieshujieci().toString());
							dmj.put("zhouci", keChengs2.get(j).getZhouci().toString());
							dmj.put("xiaoquming", keChengs2.get(j).getXiaoquming());
							dmj.put("jiaoshiming", keChengs2.get(j).getJiaoshiming());
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("1")){
								dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", keChengs2.get(j).getJieshuzhou().toString());
							}
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("2")){
								dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", keChengs2.get(j).getJieshuzhou().toString());
								dmj.put("danshuangzhou", keChengs2.get(j).getDanshuangzhou().toString());
							}
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("3")){
								dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", keChengs2.get(j).getJieshuzhou().toString());
								dmj.put("danshuangzhou", keChengs2.get(j).getDanshuangzhou().toString());
							}
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("4")){
								dmj.put("kechenghao", keChengs2.get(j).getKechenghao().toString());
								dmj.put("banJiMingCheng", keChengs2.get(j).getBanJiMingCheng());
							}
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("6")){
								dmj.put("kechenghao", keChengs2.get(j).getKechenghao().toString());
								dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
							}
//							dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
//							dmj.put("jieshuzhou", keChengs2.get(j).getJieshuzhou().toString());
//							dmj.put("danshuangzhou", keChengs2.get(j).getDanshuangzhou().toString());
//							dmj.put("kechenghao", keChengs2.get(j).getKechenghao().toString());
//							dmj.put("banJiMingCheng", keChengs2.get(j).getBanJiMingCheng());
							mapS.add(dmj);
						}
					}
				}
				keChengs2.get(i).setMaps(mapS);
				if(!keChengs3.isEmpty() && keChengs3.size()>0){
					int temp = 0 ;
					for(KeCheng kc : keChengs3){
						if(kc.getId().toString().equals(keChengs2.get(i).getId().toString())){
							temp = 1;
							break;
						}
					}
					if(temp == 0){
						keChengs3.add(keChengs2.get(i));
					}
				}else{
					keChengs3.add(keChengs2.get(i));
				}
			}
			
			
			json.put("xuenian", xuenian);
			json.put("xueqi", xueqi);
			json.put("zhou", zhou);
			json.put("keChengs", keChengs3);
			json.put("jcsj", jcsjs);
		} else {
			return null;
		}
		return json;
	}

	// 更改后进入我的课程根据年月日查询课程
	@RequestMapping(value = "app_WoDeKeCheng2")
	@ResponseBody
	public JSONObject app_WoDeKeCheng2(HttpServletRequest request) throws ParseException {
		JSONObject json = new JSONObject();
		String xueshengid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<Object, Object> maps = new HashMap<>();
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			String zhous = request.getParameter("zhou");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> mpp = new HashMap<>();
			Map<String, Object> xueQi2 = new HashMap<>();
			String xuexiaoxuehao = request.getParameter("xuexiaoXuehao");
			String xueXiaoXueHao[] = xuexiaoxuehao.split("_");
//			String xueXiaoID = xueXiaoXueHao[0];
			String xueXiaoID = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			if ((xuenian == null || "".equals(xuenian)) || (xueqi == null || "".equals(xueqi))) {
				mpp.put("riqi", sdf.format(date));
				mpp.put("xueXiaoID", xueXiaoID);
				// XueQi xueQi2 = xueqiService.getByxueXiaoIDandriQi(mpp);
				xueQi2 = xueqiService.getMapXueQiByxueXiaoIDandriQi(mpp);
				xuenian = xueQi2.get("ruXueNianFen").toString();
				xueqi = xueQi2.get("xueQi").toString();

			} else {
				mpp.put("xueqi", xueqi);
				mpp.put("nianfen", xuenian.split("~")[0]);
				mpp.put("xuexiaoid", xueXiaoID);
				xueQi2 = xueqiService.getMapXueQiByXueXiaoIDAndXueNianAndXueQi(mpp);
			}

			if (xueQi2 == null) {
				json.put("keChengs", "");
				json.put("jcsj", "");
				json.put("xuenian", "");
				json.put("xueqi", "");
				json.put("zhou", "");
				return json;
			}
			// String xuenian = xueQi2.getXuenian();
			// String xueqi = xueQi2.getXueqi().toString();
			Date kaishiriqi = sdf.parse(xueQi2.get("kaiShiRiQi").toString());
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			int zhou = 0;
			if (zhous == null || "".equals(zhous)) {
				cal1.setTime(kaishiriqi);
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
				zhou = timeDistance / 7 + 1;
			} else {
				zhou = Integer.parseInt(zhous);
			}

			Map<String, Integer> map2 = new HashMap<>();
			map2.put("zhuangtai", 1);
			map2.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map2);
			if (jieCiFangAn == null) {
				json.put("keChengs", "");
				json.put("jcsj", "");
				json.put("xuenian", xuenian);
				json.put("xueqi", xueqi);
				json.put("zhou", zhou);
				return json;
			}

			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			XueSheng xueSheng = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			Map<String, String> map = new HashMap<>();
			// map.put("banjiid1", banjiid + ",%");
			// map.put("banjiid2", "%," + banjiid + ",%");
			// map.put("xueshengid1", xueshengid + ",%");
			// map.put("xueshengid2", "%," + xueshengid + ",%");
			// List<KeCheng> keChengs =
			// kechengService.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(map);

			List<Map<String, Object>> keChengs = kechengService.getByBanJiIDAndXueShengID(Integer.parseInt(banjiid),
					xueshengid);
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (Map<String, Object> keChengMap : keChengs) {
				KeCheng kecheng = new KeCheng();
				kecheng.setLeixing(Integer.parseInt(keChengMap.get("leiXing").toString()));
				String tianjiarenid = keChengMap.get("tianJiaRenID").toString();
				kecheng.setTianjiarenid(tianjiarenid);
				String[] strs = tianjiarenid.split(",");
				YongHu yongHu = new YongHu();
				if (strs.length != 1) {
					String banjiid1 = xueSheng.getBanjiid() + ",%";
					String banjiid2 = "%," + xueSheng.getBanjiid() + "%";
					FuDaoYuan fuDaoYuan = fuDaoYuanService.getBybanJiID(banjiid1, banjiid2);
					for (String string : strs) {
						if (string.equals(fuDaoYuan.getFudaoyuanid().toString())) {
							yongHu = yonghuService.selectYongHuByID(Integer.parseInt(string));
						}
					}
				} else {
					yongHu = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
				}
				if (yongHu != null) {
					if (yongHu.getJueseid().equals(2)) {
						kecheng.setTianjiarenleixing("辅导员");
					} else {
						kecheng.setTianjiarenleixing("教师");
					}
				} else {
					kecheng.setTianjiarenleixing("学生");
				}
				// 课程备注
				map.put("yinyongid", keChengMap.get("ID").toString());
				map.put("leixing", "1");
				map.put("beizhurenid", xueshengid);
				map.put("status", status);
				List<BeiZhu> beiZhuS = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(map);
				if (beiZhuS != null && beiZhuS.size() > 0) {
					for (BeiZhu beiZhu : beiZhuS) {
						kecheng.setKechengbeizhu(beiZhu.getBeizhuneirong());
					}
				}
				if (xueQi2.get("ID").toString().equals(keChengMap.get("xueQiID").toString())) {
					// 停课情况
					List<String> tingkes = new ArrayList<>();
					for (Map<String, Object> tingKe : keChengs) {
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
							&& zhou <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (String zhouCi : tingkes) {
								if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
									i = 1;
									break;
								}
							}
							if (i == 0) {
								kecheng.setDanshuangzhoushuoming("1");
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
							kecheng.setDanshuangzhoushuoming("1");
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
							&& zhou >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
							&& zhou <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())) {
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
									kecheng.setDanshuangzhoushuoming("2");
									kecheng.setDanshuangzhou(1);
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
							if (zhou % 2 != 0) {
								kecheng.setDanshuangzhoushuoming("2");
								kecheng.setDanshuangzhou(1);
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
							&& zhou >= Integer.parseInt(keChengMap.get("kaiShiZhou").toString())
							&& zhou <= Integer.parseInt(keChengMap.get("jieShuZhou").toString())) {
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
									kecheng.setDanshuangzhoushuoming("3");
									kecheng.setDanshuangzhou(2);
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
							if (zhou % 2 == 0) {
								kecheng.setDanshuangzhoushuoming("3");
								kecheng.setDanshuangzhou(2);
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
							&& zhou == Integer.parseInt(keChengMap.get("kaiShiZhou").toString())) {
						int i = 0;
						if (tingkes.size() > 0 && !(tingkes.isEmpty())) {
							for (String zhouCi : tingkes) {
								if (zhouCi.equals(keChengMap.get("zhouCi").toString())) {
									i = 1;
									break;
								}
							}
							if (i == 0) {
								kecheng.setDanshuangzhoushuoming("4");
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
							kecheng.setDanshuangzhoushuoming("4");
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
							&& zhou == Integer.parseInt(keChengMap.get("kaiShiZhou").toString())) {
						kecheng.setDanshuangzhoushuoming("6");
						kecheng.setKechenghao(6);
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
				 * if (!(keCheng.getDanshuangzhoushuoming().isEmpty())) { String
				 * danShuangZhouShuoMing[] =
				 * keCheng.getDanshuangzhoushuoming().split("zqxj"); switch
				 * (danShuangZhouShuoMing[0]) { case "1": keCheng.setLeixing(1);
				 * String zhouCi[] = danShuangZhouShuoMing[1].split(",");
				 * keCheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
				 * keCheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
				 * 
				 * List<Map<String, String>> jiakes = new ArrayList<>(); List
				 * <String> tiaokes = new ArrayList<>();
				 * 
				 * // 加课说明,调课说明 String jiake[] =
				 * danShuangZhouShuoMing[4].split("zqjx"); if (jiake.length > 0)
				 * {
				 * 
				 * for (String string : jiake) { String str1[] =
				 * string.split(",");
				 * 
				 * // 获取当前周的周一和周日 cal1.setTime(kaishiriqi);
				 * cal1.add(Calendar.DATE, (zhou - 1) * 7); String Monday =
				 * sdf.format(cal1.getTime()); cal1.add(Calendar.DATE, 6);
				 * String Sunday = sdf.format(cal1.getTime()); if
				 * ((sdf.parse(str1[0]).after(sdf.parse(Monday)) &&
				 * sdf.parse(str1[0]).before(sdf.parse(Sunday))) ||
				 * str1[0].equals(Monday) || str1[0].equals(Sunday)) { KeCheng
				 * newkecheng = new KeCheng(); Calendar cal =
				 * Calendar.getInstance(); cal.setTime(sdf.parse(str1[0])); int
				 * n = cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n ==
				 * 1) { zhouci = 7; } else { zhouci = n - 1; }
				 * 
				 * String beizhuid = keCheng.getBeizhuid(); String beiZhuids =
				 * ""; if (!("".equals(beizhuid)) && null != beizhuid) { String
				 * ids[] = beizhuid.split(";"); for (String string2 : ids) {
				 * String bz[] = string2.split(","); if
				 * (bz[0].equals(xueshengid)) { beiZhuids += bz[1] + ","; } } if
				 * (!("".equals(beiZhuids)) && null != beiZhuids) { String
				 * beiZhu[] = beiZhuids.split(","); for (String string2 :
				 * beiZhu) { BeiZhu keChengBeiZhu = beiZhuService
				 * .selectByPrimaryKey(Integer.parseInt(string2)); if
				 * (keChengBeiZhu.getLeixing() == 1 &&
				 * keChengBeiZhu.getYinyongid() == keCheng.getId() &&
				 * keChengBeiZhu.getBeizhurenid() == Integer
				 * .parseInt(xueshengid) &&
				 * keChengBeiZhu.getStatus().equals(status)) {
				 * newkecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong())
				 * ; } } } }
				 * 
				 * newkecheng.setId(keCheng.getId());
				 * newkecheng.setKechengid(keCheng.getKechengid());
				 * newkecheng.setTianjiarenid(keCheng.getTianjiarenid());
				 * newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng())
				 * ; newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * newkecheng.setKaishizhou(zhou); //
				 * newkecheng.setJieshuzhou(keCheng.getJieshuzhou());
				 * newkecheng.setDanshuangzhoushuoming(keCheng.
				 * getDanshuangzhoushuoming()); newkecheng.setZhouci(zhouci);
				 * JiaoShi jiaoshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str1[3]));
				 * String xiaoquming =
				 * xiaoquService.selectByPrimaryKey(jiaoshi.getXiaoquid())
				 * .getMingcheng(); newkecheng.setXiaoquming(xiaoquming); String
				 * jiaoXueLouMing = jiaoXueLouService
				 * .selectByPrimaryKey(jiaoshi.getJiaoxuelouid()).
				 * getJiaoXueLouMing();
				 * newkecheng.setJiaoXueLouMing(jiaoXueLouMing); String
				 * jiaoshiming = jiaoshi.getJiaoshiming();
				 * newkecheng.setJiaoshiming(jiaoshiming); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(str1[1]));
				 * JCSJ jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(str1[2]));
				 * newkecheng.setKaishijieci(jcsj1.getJieci());
				 * newkecheng.setJieshujieci(jcsj2.getJieci()); if
				 * (keCheng.getXuenian().equals(xuenian) &&
				 * keCheng.getXueqi().toString().equals(xueqi)) {
				 * keChengs2.add(newkecheng); } } } } // 停课说明 String tingke[] =
				 * danShuangZhouShuoMing[3].split("zqjx"); for (String string :
				 * tingke) { tiaokes.add(string); } if (zhou >=
				 * Integer.parseInt(zhouCi[0]) && zhou <=
				 * Integer.parseInt(zhouCi[1])) { // 课程对应的节次，教学楼 String xinxi[]
				 * = danShuangZhouShuoMing[2].split("zxqj"); if (xinxi.length >
				 * 0) { for (String string : xinxi) { String str2[] =
				 * string.split(","); int i = zhou - Integer.parseInt(zhouCi[0])
				 * + 1; // 获取本周距离学期开始的天数 int day = (Integer.parseInt(zhouCi[0])
				 * - 1 + (i - 1)) * 7 + Integer.parseInt(str2[0]) - 1; //
				 * 获取每次上课日期 String shangkeriqi = sdf.format(new
				 * Date(sdf.parse(xueQi2.getKaishiriqi()).getTime() + (long) day
				 * * 24 * 60 * 60 * 1000)); int k = 0; for (String s : tiaokes)
				 * { if (shangkeriqi.equals(s)) { k = 1; break; } } if (k == 0)
				 * { KeCheng newkecheng = new KeCheng(); String beizhuid =
				 * keCheng.getBeizhuid(); String beiZhuids = ""; if
				 * (!("".equals(beizhuid)) && null != beizhuid) { String ids[] =
				 * beizhuid.split(";"); for (String string2 : ids) { String bz[]
				 * = string2.split(","); if (bz[0].equals(xueshengid)) {
				 * beiZhuids += bz[1] + ","; } } if (!("".equals(beiZhuids)) &&
				 * null != beiZhuids) { String beiZhu[] = beiZhuids.split(",");
				 * for (String string2 : beiZhu) { BeiZhu keChengBeiZhu =
				 * beiZhuService .selectByPrimaryKey(Integer.parseInt(string2));
				 * if (keChengBeiZhu.getLeixing() == 1 &&
				 * keChengBeiZhu.getYinyongid() == keCheng.getId() &&
				 * keChengBeiZhu.getBeizhurenid() == Integer
				 * .parseInt(xueshengid) &&
				 * keChengBeiZhu.getStatus().equals(status)) {
				 * newkecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong())
				 * ; } } } }
				 * 
				 * newkecheng.setId(keCheng.getId());
				 * newkecheng.setKechengid(keCheng.getKechengid());
				 * newkecheng.setTianjiarenid(keCheng.getTianjiarenid());
				 * newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng())
				 * ; newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * newkecheng.setKaishizhou(keCheng.getKaishizhou());
				 * newkecheng.setJieshuzhou(keCheng.getJieshuzhou());
				 * newkecheng.setZhouci(Integer.parseInt(str2[0])); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[1]));
				 * JCSJ jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[2]));
				 * newkecheng.setKaishijieci(jcsj1.getJieci());
				 * newkecheng.setJieshujieci(jcsj2.getJieci()); JiaoShi jshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str2[3]));
				 * String jiaoshiming = jshi.getJiaoshiming(); String
				 * jiaoXueLouMing = jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * (); String xiaoquming =
				 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid())
				 * .getMingcheng(); newkecheng.setJiaoshiming(jiaoshiming);
				 * newkecheng.setJiaoXueLouMing(jiaoXueLouMing);
				 * newkecheng.setXiaoquming(xiaoquming); if
				 * (keCheng.getXuenian().equals(xuenian) &&
				 * keCheng.getXueqi().toString().equals(xueqi)) {
				 * keChengs2.add(newkecheng); } } } } } break;
				 * 
				 * case "2": keCheng.setLeixing(2); String zhouCi2[] =
				 * danShuangZhouShuoMing[1].split(",");
				 * keCheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
				 * keCheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
				 * List<Map<String, String>> maps2 = new ArrayList<>(); List
				 * <String> tiaokes2 = new ArrayList<>();
				 * 
				 * // 加课说明,调课说明 String jiake2[] =
				 * danShuangZhouShuoMing[4].split("zqjx"); if (jiake2.length >
				 * 0) { for (String string : jiake2) { String str1[] =
				 * string.split(","); // 获取当前周的周一和周日 cal1.setTime(kaishiriqi);
				 * cal1.add(Calendar.DATE, (zhou - 1) * 7); String Monday =
				 * sdf.format(cal1.getTime()); cal1.add(Calendar.DATE, 6);
				 * String Sunday = sdf.format(cal1.getTime()); if
				 * ((sdf.parse(str1[0]).after(sdf.parse(Monday)) &&
				 * sdf.parse(str1[0]).before(sdf.parse(Sunday))) ||
				 * str1[0].equals(Monday) || str1[0].equals(Sunday)) { KeCheng
				 * newkecheng = new KeCheng(); Calendar cal =
				 * Calendar.getInstance(); cal.setTime(sdf.parse(str1[0])); int
				 * n = cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n ==
				 * 1) { zhouci = 7; } else { zhouci = n - 1; } String beizhuid =
				 * keCheng.getBeizhuid(); String beiZhuids = ""; if
				 * (!("".equals(beizhuid)) && null != beizhuid) { String ids[] =
				 * beizhuid.split(";"); for (String string2 : ids) { String bz[]
				 * = string2.split(","); if (bz[0].equals(xueshengid)) {
				 * beiZhuids += bz[1] + ","; } } if (!("".equals(beiZhuids)) &&
				 * null != beiZhuids) { String beiZhu[] = beiZhuids.split(",");
				 * for (String string2 : beiZhu) { BeiZhu keChengBeiZhu =
				 * beiZhuService .selectByPrimaryKey(Integer.parseInt(string2));
				 * if (keChengBeiZhu.getLeixing() == 1 &&
				 * keChengBeiZhu.getYinyongid() == keCheng.getId() &&
				 * keChengBeiZhu.getBeizhurenid() == Integer
				 * .parseInt(xueshengid) &&
				 * keChengBeiZhu.getStatus().equals(status) ) {
				 * newkecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong())
				 * ; } } } } newkecheng.setId(keCheng.getId());
				 * newkecheng.setKechengid(keCheng.getKechengid());
				 * newkecheng.setTianjiarenid(keCheng.getTianjiarenid());
				 * newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng())
				 * ; newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * newkecheng.setKaishizhou(zhou); //
				 * newkecheng.setJieshuzhou(keCheng.getJieshuzhou());
				 * newkecheng.setDanshuangzhoushuoming(keCheng.
				 * getDanshuangzhoushuoming()); newkecheng.setZhouci(zhouci);
				 * JiaoShi jiaoshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str1[3]));
				 * String xiaoquming =
				 * xiaoquService.selectByPrimaryKey(jiaoshi.getXiaoquid())
				 * .getMingcheng(); newkecheng.setXiaoquming(xiaoquming); String
				 * jiaoXueLouMing = jiaoXueLouService
				 * .selectByPrimaryKey(jiaoshi.getJiaoxuelouid()).
				 * getJiaoXueLouMing();
				 * newkecheng.setJiaoXueLouMing(jiaoXueLouMing); String
				 * jiaoshiming = jiaoshi.getJiaoshiming();
				 * newkecheng.setJiaoshiming(jiaoshiming); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(str1[1]));
				 * JCSJ jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(str1[2]));
				 * newkecheng.setKaishijieci(jcsj1.getJieci());
				 * newkecheng.setJieshujieci(jcsj2.getJieci()); if
				 * (keCheng.getXuenian().equals(xuenian) &&
				 * keCheng.getXueqi().toString().equals(xueqi)) {
				 * keChengs2.add(newkecheng); } } } } // 停课说明 String tingke2[] =
				 * danShuangZhouShuoMing[3].split("zqjx"); for (String string :
				 * tingke2) { tiaokes2.add(string); }
				 * 
				 * // 课程对应的节次，教学楼 String xinxi2[] =
				 * danShuangZhouShuoMing[2].split("zqjx"); Set<Map<String,
				 * String>> danSet = new HashSet<>(); Set<Map<String, String>>
				 * shuangSet = new HashSet<>(); if (zhou >=
				 * Integer.parseInt(zhouCi2[0]) && zhou <=
				 * Integer.parseInt(zhouCi2[1])) {
				 * 
				 * if (zhou % 2 == 0) { // 双周 String total[] =
				 * xinxi2[1].split("zxqj"); if (total.length > 0) { for (String
				 * string2 : total) { String xinxi[] = string2.split(","); //
				 * 根据当前周，判断日期 int i = zhou - Integer.parseInt(zhouCi2[0]) + 1;
				 * // 获取本周距离学期开始的天数 int day = (Integer.parseInt(zhouCi2[0]) - 1
				 * + (i - 1)) * 7 + Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期
				 * String shangkeriqi = sdf .format(new
				 * Date(sdf.parse(xueQi2.getKaishiriqi()).getTime() + (long) day
				 * * 24 * 60 * 60 * 1000)); int k = 0; for (String string :
				 * tiaokes2) { if (shangkeriqi.equals(string)) { k = 1; break; }
				 * }
				 * 
				 * if (k == 0) {
				 * 
				 * KeCheng kecheng = new KeCheng(); // 判断单周是否有这门课 int p = 9;
				 * String totals[] = xinxi2[0].split("zxqj"); for (String
				 * string2s : totals) { String[] ss = string2s.split(","); if
				 * (ss[0].equals(xinxi[0]) && ss[1].equals(xinxi[1]) &&
				 * ss[2].equals(xinxi[2])) { p = 0; } } if (p == 0) {
				 * kecheng.setDanshuangzhou(0); } else {
				 * kecheng.setDanshuangzhou(2); } String beizhuid =
				 * keCheng.getBeizhuid(); String beiZhuids = ""; if
				 * (!("".equals(beizhuid)) && null != beizhuid) { String ids[] =
				 * beizhuid.split(";"); for (String string5 : ids) { String bz[]
				 * = string5.split(","); if (bz[0].equals(xueshengid)) {
				 * beiZhuids += bz[1] + ","; } } if (!("".equals(beiZhuids)) &&
				 * null != beiZhuids) { String beiZhu[] = beiZhuids.split(",");
				 * for (String string6 : beiZhu) { BeiZhu keChengBeiZhu =
				 * beiZhuService .selectByPrimaryKey(Integer.parseInt(string6));
				 * if (keChengBeiZhu.getLeixing() == 1 &&
				 * keChengBeiZhu.getYinyongid() == keCheng.getId() &&
				 * keChengBeiZhu.getBeizhurenid() == Integer
				 * .parseInt(xueshengid) &&
				 * keChengBeiZhu.getStatus().equals(status)) {
				 * kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong()); }
				 * } } } kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setTianjiarenid(keCheng.getTianjiarenid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
				 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
				 * 
				 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
				 * JCSJ jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * 
				 * JiaoShi jshi = jiaoshiService
				 * .selectByPrimaryKey(Integer.parseInt(xinxi[3]));
				 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * ());
				 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
				 * getXiaoquid()) .getMingcheng()); if
				 * (keCheng.getXuenian().equals(xuenian) &&
				 * keCheng.getXueqi().toString().equals(xueqi)) {
				 * keChengs2.add(kecheng); } } } } } else { // 单周 String total[]
				 * = xinxi2[0].split("zxqj"); if (total.length > 0) { for
				 * (String string2 : total) { String xinxi[] =
				 * string2.split(","); // 根据当前周，判断日期 int i = zhou -
				 * Integer.parseInt(zhouCi2[0]) + 1; // 获取本周距离学期开始的天数 int day =
				 * (Integer.parseInt(zhouCi2[0]) - 1 + (i - 1)) * 7 +
				 * Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 String
				 * shangkeriqi = sdf .format(new
				 * Date(sdf.parse(xueQi2.getKaishiriqi()).getTime() + (long) day
				 * * 24 * 60 * 60 * 1000)); int k = 0; for (String string :
				 * tiaokes2) { if (shangkeriqi.equals(string)) { k = 1; break; }
				 * }
				 * 
				 * if (k == 0) { KeCheng kecheng = new KeCheng();
				 * 
				 * // 判断单周是否有这门课 int p = 9; String totals[] =
				 * xinxi2[1].split("zxqj"); for (String string2s : totals) {
				 * String[] ss = string2s.split(","); if (ss[0].equals(xinxi[0])
				 * && ss[1].equals(xinxi[1]) && ss[2].equals(xinxi[2])) { p = 0;
				 * } } if (p == 0) { kecheng.setDanshuangzhou(0); } else {
				 * kecheng.setDanshuangzhou(1); } String beizhuid =
				 * keCheng.getBeizhuid(); String beiZhuids = ""; if
				 * (!("".equals(beizhuid)) && null != beizhuid) { String ids[] =
				 * beizhuid.split(";"); for (String string5 : ids) { String bz[]
				 * = string5.split(","); if (bz[0].equals(xueshengid)) {
				 * beiZhuids += bz[1] + ","; } } if (!("".equals(beiZhuids)) &&
				 * null != beiZhuids) { String beiZhu[] = beiZhuids.split(",");
				 * for (String string6 : beiZhu) { BeiZhu keChengBeiZhu =
				 * beiZhuService .selectByPrimaryKey(Integer.parseInt(string6));
				 * if (keChengBeiZhu.getLeixing() == 1 &&
				 * keChengBeiZhu.getYinyongid() == keCheng.getId() &&
				 * keChengBeiZhu.getBeizhurenid() == Integer
				 * .parseInt(xueshengid) &&
				 * keChengBeiZhu.getStatus().equals(status)) {
				 * kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong()); }
				 * } } } kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setTianjiarenid(keCheng.getTianjiarenid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
				 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
				 * 
				 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
				 * JCSJ jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * 
				 * JiaoShi jshi = jiaoshiService
				 * .selectByPrimaryKey(Integer.parseInt(xinxi[3]));
				 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * ());
				 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
				 * getXiaoquid()) .getMingcheng()); if
				 * (keCheng.getXuenian().equals(xuenian) &&
				 * keCheng.getXueqi().toString().equals(xueqi)) {
				 * keChengs2.add(kecheng); } } } } } } break;
				 * 
				 * case "3": keCheng.setLeixing(3); String kcheng[] =
				 * danShuangZhouShuoMing[1].split("zqjx"); if (kcheng.length >
				 * 0) { int zhous = 0; for (int ii = 1; ii <= 7; ii++) { int
				 * days = (zhou - 1) * 7 + ii - 1; // 获取每次上课日期 String s =
				 * sdf.format(new
				 * Date(sdf.parse(xueQi2.getKaishiriqi()).getTime() + (long)
				 * days * 24 * 60 * 60 * 1000)); for (String string : kcheng) {
				 * int k2 = 0; if (s.equals(string.split(",")[0])) { k2 = 1;
				 * zhous = ii; } if (k2 == 1) { String kc[] = string.split(",");
				 * KeCheng kecheng = new KeCheng(); String beizhuid =
				 * keCheng.getBeizhuid(); String beiZhuids = ""; if
				 * (!("".equals(beizhuid)) && null != beizhuid) { String ids[] =
				 * beizhuid.split(";"); for (String string5 : ids) { String bz[]
				 * = string5.split(","); if (bz[0].equals(xueshengid)) {
				 * beiZhuids += bz[1] + ","; } } if (!("".equals(beiZhuids)) &&
				 * null != beiZhuids) { String beiZhu[] = beiZhuids.split(",");
				 * for (String string6 : beiZhu) { BeiZhu keChengBeiZhu =
				 * beiZhuService .selectByPrimaryKey(Integer.parseInt(string6));
				 * if (keChengBeiZhu.getLeixing() == 1 &&
				 * keChengBeiZhu.getYinyongid() == keCheng.getId() &&
				 * keChengBeiZhu.getBeizhurenid() == Integer
				 * .parseInt(xueshengid) &&
				 * keChengBeiZhu.getStatus().equals(status) ) {
				 * kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong()); }
				 * } } } kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setTianjiarenid(keCheng.getTianjiarenid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(zhou); //
				 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
				 * kecheng.setZhouci(zhous); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
				 * jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * 
				 * JiaoShi jshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
				 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * ()); kecheng.setXiaoquming(
				 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
				 * getMingcheng()); if (keCheng.getXuenian().equals(xuenian) &&
				 * keCheng.getXueqi().toString().equals(xueqi)) {
				 * keChengs2.add(kecheng); } } } } } break; } }
				 */

			}
			for (KeCheng keCheng : keChengs2) {
				int isadd = 0;
				if (xueSheng.getIsbanzhang() != null && keCheng.getTianjiarenid().equals(xueshengid)
						&& xueSheng.getIsbanzhang()) {
					isadd = 1;
				} else {
					if (keCheng.getTianjiarenid().equals(xueshengid) && keCheng.getLeixing() == 2) {
						isadd = 1;
					} else {
						List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(keCheng.getId().toString());
						if (xuanXiuIDs != null && xuanXiuIDs.size() > 0) {
							for (String xuanxiuid : xuanXiuIDs) {
								if (xuanxiuid.equals(xueshengid) && !(keCheng.getTianjiarenid().equals(xueshengid))
										&& keCheng.getLeixing() == 2) {
									isadd = 1;
								}
							}
						}
					}
				}
				keCheng.setIsadd(isadd);
			}
			
			List<KeCheng> keChengs3 = new ArrayList<>();
			for(int i=0; i<keChengs2.size(); i++){
				List<Map<String,String>> mapS = new ArrayList<>();
				Map<String,String> dm = new HashMap<>();
				
				dm.put("kaishijieci", keChengs2.get(i).getKaishijieci().toString());
				dm.put("jieshujieci", keChengs2.get(i).getJieshujieci().toString());
				dm.put("zhouci", keChengs2.get(i).getZhouci().toString());
				dm.put("xiaoquming", keChengs2.get(i).getXiaoquming());
				dm.put("jiaoshiming", keChengs2.get(i).getJiaoshiming());
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("1")){
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", keChengs2.get(i).getJieshuzhou().toString());
				}
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("2")){
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", keChengs2.get(i).getJieshuzhou().toString());
					dm.put("danshuangzhou", keChengs2.get(i).getDanshuangzhou().toString());
				}
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("3")){
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", keChengs2.get(i).getJieshuzhou().toString());
					dm.put("danshuangzhou", keChengs2.get(i).getDanshuangzhou().toString());
				}
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("4")){
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
				}
				if(keChengs2.get(i).getDanshuangzhoushuoming().equals("6")){
					dm.put("kechenghao", keChengs2.get(i).getKechenghao().toString());
					dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
				}
				mapS.add(dm);
				for(int j=0; j<keChengs2.size(); j++){
					if(i != j ){
						if(keChengs2.get(i).getId().toString().equals(keChengs2.get(j).getId().toString())){
							Map<String,String> dmj = new HashMap<>();
							dmj.put("kaishijieci", keChengs2.get(j).getKaishijieci().toString());
							dmj.put("jieshujieci", keChengs2.get(j).getJieshujieci().toString());
							dmj.put("zhouci", keChengs2.get(j).getZhouci().toString());
							dmj.put("xiaoquming", keChengs2.get(j).getXiaoquming());
							dmj.put("jiaoshiming", keChengs2.get(j).getJiaoshiming());
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("1")){
								dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", keChengs2.get(j).getJieshuzhou().toString());
							}
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("2")){
								dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", keChengs2.get(j).getJieshuzhou().toString());
								dmj.put("danshuangzhou", keChengs2.get(j).getDanshuangzhou().toString());
							}
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("3")){
								dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", keChengs2.get(j).getJieshuzhou().toString());
								dmj.put("danshuangzhou", keChengs2.get(j).getDanshuangzhou().toString());
							}
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("4")){
								dm.put("kaishizhou", keChengs2.get(i).getKaishizhou().toString());
							}
							if(keChengs2.get(j).getDanshuangzhoushuoming().equals("6")){
								dmj.put("kechenghao", keChengs2.get(j).getKechenghao().toString());
								dmj.put("kaishizhou", keChengs2.get(j).getKaishizhou().toString());
							}
							mapS.add(dmj);
						}
					}
				}
				keChengs2.get(i).setMaps(mapS);
				if(!keChengs3.isEmpty() && keChengs3.size()>0){
					int temp = 0 ;
					for(KeCheng kc : keChengs3){
						if(kc.getId().toString().equals(keChengs2.get(i).getId().toString())){
							temp = 1;
							break;
						}
					}
					if(temp == 0){
						keChengs3.add(keChengs2.get(i));
					}
				}else{
					keChengs3.add(keChengs2.get(i));
				}
			}
			
			json.put("xuenian", xuenian);
			json.put("xueqi", xueqi);
			json.put("zhou", zhou);
			json.put("keChengs", keChengs3);
			json.put("jcsj", jcsjs);
		} else {
			return null;
		}
		return json;
	}

	// 根据条件查询该学期课程
	@RequestMapping(value = "app_ChaXunKeCheng")
	@ResponseBody
	public JSONObject app_ChaXunKeCheng(HttpServletRequest request) throws ParseException {
		String xueshengid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<Object, Object> maps = new HashMap<>();
		if (str.equals(token)) {
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			String zhou = request.getParameter("zhou");
			String xueshengid1 = xueshengid + ",1;%";
			String xueshengid2 = "%;" + xueshengid + ",1;%";
			List<KeCheng> keChengs = kechengService.getAllByxueShengID(xueshengid1, xueshengid2);
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (KeCheng keCheng : keChengs) {
				String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
						.getMingcheng();
				String jiaoshiming = jiaoshiService.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi()))
						.getJiaoshiming();
				keCheng.setXiaoquming(xiaoquming);
				keCheng.setJiaoshiming(jiaoshiming);
				String tianjiarenid = keCheng.getTianjiarenid();
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
				int i = 0;
				int j = 0;
				if (keCheng.getXuenian().equals(xuenian) && keCheng.getXueqi().toString().equals(xueqi)) {
					i = 1;
				}
				if (keCheng.getKaishizhou() <= Integer.parseInt(zhou)
						&& keCheng.getJieshuzhou() >= Integer.parseInt(zhou)) {
					j = 1;
				}
				if (i == 1 && j == 1) {
					keChengs2.add(keCheng);
				}
			}
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			// 获取当前周的周一和周日
			Map<String, String> map = new HashMap<>();
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			String xuexiao_xuehao = request.getParameter("xuexiaoXuehao");
//			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.lastIndexOf("_"));
			String xueXiaoID = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			map.put("xuexiaoid", xueXiaoID);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			if (xueQi == null) {
				maps.put("keChengs", "");
				maps.put("jcsj", "");
				return JSONObject.fromObject(maps);
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date kaishiriqi = simpleDateFormat.parse(xueQi.getKaishiriqi());
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(kaishiriqi);
			cal1.add(Calendar.DATE, (Integer.parseInt(zhou) - 1) * 7);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String Monday = sdf.format(cal1.getTime());
			cal1.add(Calendar.DATE, 6);
			String Sunday = sdf.format(cal1.getTime());
			// System.out.println("周一："+Monday);
			// System.out.println("周日："+Sunday);
			List<KeCheng> keChengs3 = new ArrayList<>();
			for (KeCheng keCheng : keChengs2) {
				String shangkeriqi[] = keCheng.getShangkeriqi().split(",");
				for (String string : shangkeriqi) {
					KeCheng newkecheng = new KeCheng();
					String riqi[] = string.split(";");
					if ((sdf.parse(riqi[0]).after(sdf.parse(Monday)) && sdf.parse(riqi[0]).before(sdf.parse(Sunday)))
							|| riqi[0].equals(Monday) || riqi[0].equals(Sunday)) {
						// System.out.println(string+"长度："+riqi.length);
						if (riqi.length == 1) {
							newkecheng = keCheng;
							keChengs3.add(newkecheng);
							continue;
						}
						Calendar cal = Calendar.getInstance();
						cal.setTime(sdf.parse(riqi[0]));
						int n = cal.get(Calendar.DAY_OF_WEEK);
						int zhouci = 0;
						if (n == 1) {
							zhouci = 7;
						} else {
							zhouci = n - 1;
						}
						newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
						newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
						// newkecheng.setKaishizhou(keCheng.getKaishizhou());
						// newkecheng.setJieshuzhou(keCheng.getJieshuzhou());
						newkecheng.setDanshuangzhou(keCheng.getDanshuangzhou());
						newkecheng.setDanshuangzhoushuoming(keCheng.getDanshuangzhoushuoming());
						newkecheng.setZhouci(zhouci);
						String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(riqi[1])).getMingcheng();
						newkecheng.setXiaoquming(xiaoquming);
						String jiaoshiming = jiaoshiService.selectByPrimaryKey(Integer.parseInt(riqi[2]))
								.getJiaoshiming();
						newkecheng.setJiaoshiming(jiaoshiming);
						newkecheng.setKaishijieci(Integer.parseInt(riqi[3]));
						newkecheng.setJieshujieci(Integer.parseInt(riqi[4]));
						keChengs3.add(newkecheng);
					}
				}
			}
			Map<String, Integer> map2 = new HashMap<>();
			map2.put("zhuangtai", 1);
			map2.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map2);
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			maps.put("keChengs", keChengs3);
			maps.put("zhou", zhou);
			maps.put("jcsj", jcsjs);
		} else {
			return null;
		}
		return JSONObject.fromObject(maps);
	}

	// 通过学年 学期 查询周数
	@RequestMapping(value = "app_getweeknum")
	@ResponseBody
	public JSONObject app_getweeknum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		String xueshengid = request.getParameter("xueshengid");
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
//		String xuexiaoid = xs.getXuexiaoXuehao().split("_")[0];
		String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
		Map<String, String> map = new HashMap<>();
		map.put("xueqi", xueqi);
		map.put("nianfen", xuenian.split("~")[0]);
		map.put("xuexiaoid", xuexiaoid);
		XueQi xq = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int weekNum = Util.WeeksBetweenDays(sdf.parse(xq.getKaishiriqi()), sdf.parse(xq.getJieshuriqi()));
		json.put("weeknum", weekNum);
		return json;

	}

	// 根据条件查询该学期课程
	@RequestMapping(value = "app_ChaXunKeCheng2")
	@ResponseBody
	public JSONObject app_ChaXunKeCheng2(HttpServletRequest request) throws ParseException {
		String xueshengid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<Object, Object> maps = new HashMap<>();
		if (str.equals(token)) {
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			int zhou = Integer.parseInt(request.getParameter("zhou"));
			XueSheng xuesheng = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
//			String xuexiaoid = xuesheng.getXuexiaoXuehao().split("_")[0];
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xuesheng.getBanjiid());
			Map<String, String> mapp = new HashMap<>();
			mapp.put("xuenian", xuenian);
			mapp.put("xueqi", xueqi);
			mapp.put("xuexiaoid", xuexiaoid);
			XueQi xueQi2 = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(mapp);
			mapp.put("banjiid1", banjiid + ",%");
			mapp.put("banjiid2", "%," + banjiid + ",%");
			mapp.put("xueshengid1", xueshengid + ",%");
			mapp.put("xueshengid2", "%," + xueshengid + ",%");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date kaishiriqi = sdf.parse(xueQi2.getKaishiriqi());
			Calendar cal1 = Calendar.getInstance();
			List<KeCheng> keChengs = kechengService.getAllBybanJiIDsAndxuanXiuIDsAndmianXiuIDs(mapp);
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (KeCheng keCheng : keChengs) {
				String tianjiarenid = keCheng.getTianjiarenid();
				String[] strs = tianjiarenid.split(",");
				YongHu yongHu = new YongHu();
				if (strs.length != 1) {
					String banjiid1 = xuesheng.getBanjiid() + ",%";
					String banjiid2 = "%," + xuesheng.getBanjiid() + "%";
					FuDaoYuan fuDaoYuan = fuDaoYuanService.getBybanJiID(banjiid1, banjiid2);
					for (String string : strs) {
						if (string.equals(fuDaoYuan.getFudaoyuanid().toString())) {
							yongHu = yonghuService.selectYongHuByID(Integer.parseInt(string));
						}
					}
				} else {
					yongHu = yonghuService.selectYongHuByID(Integer.parseInt(tianjiarenid));
				}
				if (yongHu != null) {
					if (yongHu.getJueseid().equals(2)) {
						keCheng.setTianjiarenleixing("辅导员");
					} else {
						keCheng.setTianjiarenleixing("教师");
					}
				} else {
					keCheng.setTianjiarenleixing("学生");
				}

				if (!(keCheng.getDanshuangzhoushuoming().isEmpty())) {
					String danShuangZhouShuoMing[] = keCheng.getDanshuangzhoushuoming().split("zqxj");
					switch (danShuangZhouShuoMing[0]) {
					case "1":
						keCheng.setLeixing(1);
						String zhouCi[] = danShuangZhouShuoMing[1].split(",");
						keCheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
						keCheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));

						List<Map<String, String>> jiakes = new ArrayList<>();
						List<String> tiaokes = new ArrayList<>();

						// 加课说明,调课说明
						String jiake[] = danShuangZhouShuoMing[4].split("zqjx");
						for (String string : jiake) {
							String str1[] = string.split(",");

							// 获取当前周的周一和周日
							cal1.setTime(kaishiriqi);
							cal1.add(Calendar.DATE, (zhou - 1) * 7);
							String Monday = sdf.format(cal1.getTime());
							cal1.add(Calendar.DATE, 6);
							String Sunday = sdf.format(cal1.getTime());
							if ((sdf.parse(str1[0]).after(sdf.parse(Monday))
									&& sdf.parse(str1[0]).before(sdf.parse(Sunday))) || str1[0].equals(Monday)
									|| str1[0].equals(Sunday)) {
								KeCheng newkecheng = new KeCheng();
								Calendar cal = Calendar.getInstance();
								cal.setTime(sdf.parse(str1[0]));
								int n = cal.get(Calendar.DAY_OF_WEEK);
								int zhouci = 0;
								if (n == 1) {
									zhouci = 7;
								} else {
									zhouci = n - 1;
								}
								String beizhuid = keCheng.getBeizhuid();
								String beiZhuids = "";
								if (!("".equals(beizhuid)) && null != beizhuid) {
									String ids[] = beizhuid.split(";");
									for (String string5 : ids) {
										String bz[] = string5.split(",");
										if (bz[0].equals(xueshengid)) {
											beiZhuids += bz[1] + ",";
										}
									}
									if (!("".equals(beiZhuids)) && null != beiZhuids) {
										String beiZhu[] = beiZhuids.split(",");
										for (String string6 : beiZhu) {
											BeiZhu keChengBeiZhu = beiZhuService
													.selectByPrimaryKey(Integer.parseInt(string6));
											if (keChengBeiZhu.getLeixing() == 1
													&& keChengBeiZhu.getYinyongid() == keCheng.getId()
													&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
													&& keChengBeiZhu.getStatus().equals(status)) {
												newkecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
											}
										}
									}
								}
								newkecheng.setLeixing(0);// 调课或加课
								newkecheng.setId(keCheng.getId());
								newkecheng.setKechengid(keCheng.getKechengid());
								newkecheng.setTianjiarenid(keCheng.getTianjiarenid());
								newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
								newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
								newkecheng.setKaishizhou(zhou);
								// newkecheng.setJieshuzhou(keCheng.getJieshuzhou());
								newkecheng.setDanshuangzhoushuoming(keCheng.getDanshuangzhoushuoming());
								newkecheng.setZhouci(zhouci);
								JiaoShi jiaoshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(str1[3]));
								String xiaoquming = xiaoquService.selectByPrimaryKey(jiaoshi.getXiaoquid())
										.getMingcheng();
								newkecheng.setXiaoquming(xiaoquming);
								String jiaoXueLouMing = jiaoXueLouService.selectByPrimaryKey(jiaoshi.getJiaoxuelouid())
										.getJiaoXueLouMing();
								newkecheng.setJiaoXueLouMing(jiaoXueLouMing);
								String jiaoshiming = jiaoshi.getJiaoshiming();
								newkecheng.setJiaoshiming(jiaoshiming);
								JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(str1[1]));
								JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(str1[2]));
								newkecheng.setKaishijieci(jcsj1.getJieci());
								newkecheng.setJieshujieci(jcsj2.getJieci());
								if (keCheng.getXuenian().equals(xuenian)
										&& keCheng.getXueqi().toString().equals(xueqi)) {
									keChengs2.add(newkecheng);
								}
							}
						}

						// 停课说明
						String tingke[] = danShuangZhouShuoMing[3].split("zqjx");
						for (String string : tingke) {
							tiaokes.add(string);
						}

						if (zhou >= Integer.parseInt(zhouCi[0]) && zhou <= Integer.parseInt(zhouCi[1])) {
							// 课程对应的节次，教学楼
							String xinxi[] = danShuangZhouShuoMing[2].split("zxqj");
							for (String string : xinxi) {
								String str2[] = string.split(",");
								int i = zhou - Integer.parseInt(zhouCi[0]) + 1;
								// 获取本周距离学期开始的天数
								int day = (Integer.parseInt(zhouCi[0]) - 1 + (i - 1)) * 7 + Integer.parseInt(str2[0])
										- 1;
								// 获取每次上课日期
								String shangkeriqi = sdf.format(new Date(sdf.parse(xueQi2.getKaishiriqi()).getTime()
										+ (long) day * 24 * 60 * 60 * 1000));
								int k = 0;
								for (String s : tiaokes) {
									if (shangkeriqi.equals(s)) {
										k = 1;
										break;
									}
								}
								if (k == 0) {
									KeCheng newkecheng = new KeCheng();
									String beizhuid = keCheng.getBeizhuid();
									String beiZhuids = "";
									if (!("".equals(beizhuid)) && null != beizhuid) {
										String ids[] = beizhuid.split(";");
										for (String string5 : ids) {
											String bz[] = string5.split(",");
											if (bz[0].equals(xueshengid)) {
												beiZhuids += bz[1] + ",";
											}
										}
										if (!("".equals(beiZhuids)) && null != beiZhuids) {
											String beiZhu[] = beiZhuids.split(",");
											for (String string6 : beiZhu) {
												BeiZhu keChengBeiZhu = beiZhuService
														.selectByPrimaryKey(Integer.parseInt(string6));
												if (keChengBeiZhu.getLeixing() == 1
														&& keChengBeiZhu.getYinyongid() == keCheng.getId()
														&& keChengBeiZhu.getBeizhurenid() == Integer
																.parseInt(xueshengid)
														&& keChengBeiZhu.getStatus().equals(status)) {
													newkecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
												}
											}
										}
									}
									newkecheng.setLeixing(keCheng.getLeixing());
									newkecheng.setId(keCheng.getId());
									newkecheng.setKechengid(keCheng.getKechengid());
									newkecheng.setTianjiarenid(keCheng.getTianjiarenid());
									newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
									newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
									newkecheng.setKaishizhou(keCheng.getKaishizhou());
									newkecheng.setJieshuzhou(keCheng.getJieshuzhou());
									newkecheng.setZhouci(Integer.parseInt(str2[0]));
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(str2[1]));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(str2[2]));
									newkecheng.setKaishijieci(jcsj1.getJieci());
									newkecheng.setJieshujieci(jcsj2.getJieci());
									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(str2[3]));
									String jiaoshiming = jshi.getJiaoshiming();
									String jiaoXueLouMing = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
											.getJiaoXueLouMing();
									String xiaoquming = xiaoquService.selectByPrimaryKey(jshi.getXiaoquid())
											.getMingcheng();
									newkecheng.setJiaoshiming(jiaoshiming);
									newkecheng.setJiaoXueLouMing(jiaoXueLouMing);
									newkecheng.setXiaoquming(xiaoquming);
									if (keCheng.getXuenian().equals(xuenian)
											&& keCheng.getXueqi().toString().equals(xueqi)) {
										keChengs2.add(newkecheng);
									}
								}
							}
						}

						break;

					case "2":
						keCheng.setLeixing(2);
						String zhouCi2[] = danShuangZhouShuoMing[1].split(",");
						keCheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
						keCheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));

						List<Map<String, String>> maps2 = new ArrayList<>();
						List<String> tiaokes2 = new ArrayList<>();

						// 加课说明,调课说明
						String jiake2[] = danShuangZhouShuoMing[4].split("zqjx");
						for (String string : jiake2) {
							String str1[] = string.split(",");
							// 获取当前周的周一和周日
							cal1.setTime(kaishiriqi);
							cal1.add(Calendar.DATE, (zhou - 1) * 7);
							String Monday = sdf.format(cal1.getTime());
							cal1.add(Calendar.DATE, 6);
							String Sunday = sdf.format(cal1.getTime());
							if ((sdf.parse(str1[0]).after(sdf.parse(Monday))
									&& sdf.parse(str1[0]).before(sdf.parse(Sunday))) || str1[0].equals(Monday)
									|| str1[0].equals(Sunday)) {
								KeCheng newkecheng = new KeCheng();
								Calendar cal = Calendar.getInstance();
								cal.setTime(sdf.parse(str1[0]));
								int n = cal.get(Calendar.DAY_OF_WEEK);
								int zhouci = 0;
								if (n == 1) {
									zhouci = 7;
								} else {
									zhouci = n - 1;
								}
								String beizhuid = keCheng.getBeizhuid();
								String beiZhuids = "";
								if (!("".equals(beizhuid)) && null != beizhuid) {
									String ids[] = beizhuid.split(";");
									for (String string5 : ids) {
										String bz[] = string5.split(",");
										if (bz[0].equals(xueshengid)) {
											beiZhuids += bz[1] + ",";
										}
									}
									if (!("".equals(beiZhuids)) && null != beiZhuids) {
										String beiZhu[] = beiZhuids.split(",");
										for (String string6 : beiZhu) {
											BeiZhu keChengBeiZhu = beiZhuService
													.selectByPrimaryKey(Integer.parseInt(string6));
											if (keChengBeiZhu.getLeixing() == 1
													&& keChengBeiZhu.getYinyongid() == keCheng.getId()
													&& keChengBeiZhu.getBeizhurenid() == Integer.parseInt(xueshengid)
													&& keChengBeiZhu.getStatus().equals(status)) {
												newkecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
											}
										}
									}
								}
								newkecheng.setLeixing(0);// 调课或加课
								newkecheng.setId(keCheng.getId());
								newkecheng.setKechengid(keCheng.getKechengid());
								newkecheng.setTianjiarenid(keCheng.getTianjiarenid());
								newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
								newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
								newkecheng.setKaishizhou(zhou);
								// newkecheng.setJieshuzhou(keCheng.getJieshuzhou());
								newkecheng.setDanshuangzhoushuoming(keCheng.getDanshuangzhoushuoming());
								newkecheng.setZhouci(zhouci);
								JiaoShi jiaoshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(str1[3]));
								String xiaoquming = xiaoquService.selectByPrimaryKey(jiaoshi.getXiaoquid())
										.getMingcheng();
								newkecheng.setXiaoquming(xiaoquming);
								String jiaoXueLouMing = jiaoXueLouService.selectByPrimaryKey(jiaoshi.getJiaoxuelouid())
										.getJiaoXueLouMing();
								newkecheng.setJiaoXueLouMing(jiaoXueLouMing);
								String jiaoshiming = jiaoshi.getJiaoshiming();
								newkecheng.setJiaoshiming(jiaoshiming);
								JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(str1[1]));
								JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(str1[2]));
								newkecheng.setKaishijieci(jcsj1.getJieci());
								newkecheng.setJieshujieci(jcsj2.getJieci());
								if (keCheng.getXuenian().equals(xuenian)
										&& keCheng.getXueqi().toString().equals(xueqi)) {
									keChengs2.add(newkecheng);
								}
							}
						}
						// 停课说明
						String tingke2[] = danShuangZhouShuoMing[3].split("zqjx");
						for (String string : tingke2) {
							tiaokes2.add(string);
						}

						// 课程对应的节次，教学楼
						String xinxi2[] = danShuangZhouShuoMing[2].split("zqjx");
						Set<Map<String, String>> danSet = new HashSet<>();
						Set<Map<String, String>> shuangSet = new HashSet<>();
						if (zhou >= Integer.parseInt(zhouCi2[0]) && zhou <= Integer.parseInt(zhouCi2[1])) {

							if (zhou % 2 == 0) { // 双周
								String total[] = xinxi2[1].split("zxqj");
								for (String string2 : total) {
									String xinxi[] = string2.split(",");
									// 根据当前周，判断日期
									int i = zhou - Integer.parseInt(zhouCi2[0]) + 1;
									// 获取本周距离学期开始的天数
									int day = (Integer.parseInt(zhouCi2[0]) - 1 + (i - 1)) * 7
											+ Integer.parseInt(xinxi[0]) - 1;
									// 获取每次上课日期
									String shangkeriqi = sdf.format(new Date(sdf.parse(xueQi2.getKaishiriqi()).getTime()
											+ (long) day * 24 * 60 * 60 * 1000));
									int k = 0;
									for (String string : tiaokes2) {
										if (shangkeriqi.equals(string)) {
											k = 1;
											break;
										}
									}

									if (k == 0) {
										KeCheng kecheng = new KeCheng();

										// 判断单周是否有这门课
										int p = 9;
										String totals[] = xinxi2[0].split("zxqj");
										for (String string2s : totals) {
											String[] ss = string2s.split(",");
											if (ss[0].equals(xinxi[0]) && ss[1].equals(xinxi[1])
													&& ss[2].equals(xinxi[2])) {
												p = 0;
											}
										}
										if (p == 0) {
											kecheng.setDanshuangzhou(0);
										} else {
											kecheng.setDanshuangzhou(2);
										}
										String beizhuid = keCheng.getBeizhuid();
										String beiZhuids = "";
										if (!("".equals(beizhuid)) && null != beizhuid) {
											String ids[] = beizhuid.split(";");
											for (String string5 : ids) {
												String bz[] = string5.split(",");
												if (bz[0].equals(xueshengid)) {
													beiZhuids += bz[1] + ",";
												}
											}
											if (!("".equals(beiZhuids)) && null != beiZhuids) {
												String beiZhu[] = beiZhuids.split(",");
												for (String string6 : beiZhu) {
													BeiZhu keChengBeiZhu = beiZhuService
															.selectByPrimaryKey(Integer.parseInt(string6));
													if (keChengBeiZhu.getLeixing() == 1
															&& keChengBeiZhu.getYinyongid() == keCheng.getId()
															&& keChengBeiZhu.getBeizhurenid() == Integer
																	.parseInt(xueshengid)
															&& keChengBeiZhu.getStatus().equals(status)) {
														kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
													}
												}
											}
										}
										kecheng.setLeixing(keCheng.getLeixing());
										kecheng.setId(keCheng.getId());
										kecheng.setKechengid(keCheng.getKechengid());
										kecheng.setTianjiarenid(keCheng.getTianjiarenid());
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
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jiaoXueLouService
												.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
										if (keCheng.getXuenian().equals(xuenian)
												&& keCheng.getXueqi().toString().equals(xueqi)) {
											keChengs2.add(kecheng);
										}
									}
								}
							} else { // 单周
								String total[] = xinxi2[0].split("zxqj");
								for (String string2 : total) {
									String xinxi[] = string2.split(",");
									// 根据当前周，判断日期
									int i = zhou - Integer.parseInt(zhouCi2[0]) + 1;
									// 获取本周距离学期开始的天数
									int day = (Integer.parseInt(zhouCi2[0]) - 1 + (i - 1)) * 7
											+ Integer.parseInt(xinxi[0]) - 1;
									// 获取每次上课日期
									String shangkeriqi = sdf.format(new Date(sdf.parse(xueQi2.getKaishiriqi()).getTime()
											+ (long) day * 24 * 60 * 60 * 1000));
									int k = 0;
									for (String string : tiaokes2) {
										if (shangkeriqi.equals(string)) {
											k = 1;
											break;
										}
									}

									if (k == 0) {
										KeCheng kecheng = new KeCheng();

										// 判断双周是否有这门课
										int p = 9;
										String totals[] = xinxi2[1].split("zxqj");
										for (String string2s : totals) {
											String[] ss = string2s.split(",");
											if (ss[0].equals(xinxi[0]) && ss[1].equals(xinxi[1])
													&& ss[2].equals(xinxi[2])) {
												p = 0;
											}
										}
										if (p == 0) {
											kecheng.setDanshuangzhou(0);
										} else {
											kecheng.setDanshuangzhou(1);
										}
										String beizhuid = keCheng.getBeizhuid();
										String beiZhuids = "";
										if (!("".equals(beizhuid)) && null != beizhuid) {
											String ids[] = beizhuid.split(";");
											for (String string5 : ids) {
												String bz[] = string5.split(",");
												if (bz[0].equals(xueshengid)) {
													beiZhuids += bz[1] + ",";
												}
											}
											if (!("".equals(beiZhuids)) && null != beiZhuids) {
												String beiZhu[] = beiZhuids.split(",");
												for (String string6 : beiZhu) {
													BeiZhu keChengBeiZhu = beiZhuService
															.selectByPrimaryKey(Integer.parseInt(string6));
													if (keChengBeiZhu.getLeixing() == 1
															&& keChengBeiZhu.getYinyongid() == keCheng.getId()
															&& keChengBeiZhu.getBeizhurenid() == Integer
																	.parseInt(xueshengid)
															&& keChengBeiZhu.getStatus().equals(status)) {
														kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
													}
												}
											}
										}
										kecheng.setLeixing(keCheng.getLeixing());
										kecheng.setId(keCheng.getId());
										kecheng.setKechengid(keCheng.getKechengid());
										kecheng.setTianjiarenid(keCheng.getTianjiarenid());
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
										kecheng.setJiaoshiming(jshi.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jiaoXueLouService
												.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
										kecheng.setXiaoquming(
												xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
										if (keCheng.getXuenian().equals(xuenian)
												&& keCheng.getXueqi().toString().equals(xueqi)) {
											keChengs2.add(kecheng);
										}
									}
								}
							}

						}
						break;

					case "3":
						keCheng.setLeixing(3);
						String kcheng[] = danShuangZhouShuoMing[1].split("zqjx");
						int zhous = 0;
						for (int ii = 1; ii <= 7; ii++) {
							int days = (zhou - 1) * 7 + ii - 1;
							// 获取每次上课日期
							String s = sdf.format(new Date(
									sdf.parse(xueQi2.getKaishiriqi()).getTime() + (long) days * 24 * 60 * 60 * 1000));
							for (String string : kcheng) {
								int k2 = 0;
								if (s.equals(string.split(",")[0])) {
									k2 = 1;
									zhous = ii;
								}
								if (k2 == 1) {
									String kc[] = string.split(",");
									KeCheng kecheng = new KeCheng();
									String beizhuid = keCheng.getBeizhuid();
									String beiZhuids = "";
									if (!("".equals(beizhuid)) && null != beizhuid) {
										String ids[] = beizhuid.split(";");
										for (String string5 : ids) {
											String bz[] = string5.split(",");
											if (bz[0].equals(xueshengid)) {
												beiZhuids += bz[1] + ",";
											}
										}
										if (!("".equals(beiZhuids)) && null != beiZhuids) {
											String beiZhu[] = beiZhuids.split(",");
											for (String string6 : beiZhu) {
												BeiZhu keChengBeiZhu = beiZhuService
														.selectByPrimaryKey(Integer.parseInt(string6));
												if (keChengBeiZhu.getLeixing() == 1
														&& keChengBeiZhu.getYinyongid() == keCheng.getId()
														&& keChengBeiZhu.getBeizhurenid() == Integer
																.parseInt(xueshengid)
														&& keChengBeiZhu.getStatus().equals(status)) {
													kecheng.setKechengbeizhu(keChengBeiZhu.getBeizhuneirong());
												}
											}
										}
									}
									kecheng.setShangkeriqi(kc[0]);
									kecheng.setLeixing(keCheng.getLeixing());
									kecheng.setId(keCheng.getId());
									kecheng.setKechengid(keCheng.getKechengid());
									kecheng.setTianjiarenid(keCheng.getTianjiarenid());
									kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
									kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
									kecheng.setKaishizhou(zhou);
									// kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
									kecheng.setZhouci(zhous);
									JCSJ jcsj1 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1]));
									JCSJ jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
									kecheng.setKaishijieci(jcsj1.getJieci());
									kecheng.setJieshujieci(jcsj2.getJieci());

									JiaoShi jshi = jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
									kecheng.setJiaoshiming(jshi.getJiaoshiming());
									kecheng.setJiaoXueLouMing(jiaoXueLouService
											.selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing());
									kecheng.setXiaoquming(
											xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng());
									if (keCheng.getXuenian().equals(xuenian)
											&& keCheng.getXueqi().toString().equals(xueqi)) {
										keChengs2.add(kecheng);
									}
								}
							}
						}
						break;
					}
				}
			}
			for (KeCheng keCheng : keChengs2) {
				// JCSJ jcsj1 =
				// jcsjService.selectByPrimaryKey(keCheng.getKaishijieci());
				// JCSJ jcsj2 =
				// jcsjService.selectByPrimaryKey(keCheng.getJieshujieci());
				// keCheng.setKaishijieci(jcsj1.getJieci());
				// keCheng.setJieshujieci(jcsj2.getJieci());
				int isadd = 0;
				if (keCheng.getXuanxiuids() != null && keCheng.getXuanxiuids() != "") {
					String xuanxiuids[] = keCheng.getXuanxiuids().split(",");
					for (String xuanxiuid : xuanxiuids) {
						if (xuanxiuid.equals(xueshengid)) {
							isadd = 1;
						}
					}
				}
				keCheng.setIsadd(isadd);
			}
			Map<String, Integer> map2 = new HashMap<>();
			map2.put("zhuangtai", 1);
			map2.put("xuexiaoid", Integer.parseInt(xuexiaoid));
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map2);
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			maps.put("keChengs", keChengs2);
			maps.put("zhou", zhou);
			maps.put("jcsj", jcsjs);
		} else {
			return null;
		}
		return JSONObject.fromObject(maps);
	}

	// 删除非必修课程
	@RequestMapping(value = "app_DelKeCheng")
	@ResponseBody
	public String app_DelKeCheng(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(data[1]));
			if (kecheng == null) {
				return "dengchu";
			}
			String canyuren = kecheng.getCanyuren();
			String newcanyuren = "";
			int i = 0;
			if (canyuren != null && !"".equals(canyuren)) {
				String canyurenid[] = canyuren.split(";");
				for (String string : canyurenid) {
					if (string.equals(data[0] + ",1")) {
						i = 1;
						newcanyuren += data[0] + ",0;";
						continue;
					}
					newcanyuren += string + ";";
				}
			}

			if (i == 0) {
				return "dengchu";
			}
			KeCheng keCheng2 = new KeCheng();
			keCheng2.setCanyuren(newcanyuren);
			keCheng2.setId(Integer.parseInt(data[1]));
			int j = kechengService.updatecanYuRenByPrimaryKey(keCheng2);
			if (j != 0) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	// 从课表中删除这门非全班上的课
	@RequestMapping(value = "app_DelKeCheng2")
	@ResponseBody
	public String app_DelKeCheng2(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(data[1]));
			if (kecheng == null) {
				return "dengchu";
			}
			int j = 0;
			try {
				Map<String, String> map = new HashMap<>();
				map.put("kechengid", data[1]);
				if (kecheng.getTianjiarenid().toString().equals(data[0])) {
					kechengService.delete_allshangkebanjiByID(data[1]);
					kechengService.delete_allshangkeshijianByID(data[1]);
					kechengService.delete_allshangkexuanxiurenByID(data[1]);
					kechengService.delete_shangkemianxiuren(map);
					kechengService.deleteByPrimaryKey(Integer.parseInt(data[1]));
				} else {
					if (kecheng.getLeixing() == 1) {
						map.put("xueshengid", data[0]);
						kechengService.insert_shangkemianxiuren(map);
					} else {
						map.put("xueshengid", data[0]);
						kechengService.delete_shangkemianxiuren(map);
						kechengService.delete_shangkexuanxiuren(map);
					}
				}
				j = 1;
			} catch (Exception e) {
				e.printStackTrace();
			}

			/**
			 * String xuanxiuids[] = kecheng.getXuanxiuids().split(","); String
			 * newxuanxiuids = ""; for (String xuanxiuid : xuanxiuids) { if
			 * (!xuanxiuid.equals(data[0])) { newxuanxiuids += xuanxiuid + ",";
			 * } } KeCheng keCheng2 = new KeCheng();
			 * keCheng2.setId(Integer.parseInt(data[1]));
			 * keCheng2.setXuanxiuids(newxuanxiuids); int j =
			 * kechengService.updateBJandXXandMXByPrimaryKey(keCheng2);
			 */
			if (j != 0) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	// 免修课程
	@RequestMapping(value = "app_MianXiuKeCheng")
	@ResponseBody
	public String app_MianXiuKeCheng(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			XueSheng user = xueshengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(user.getBanjiid());
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(data[1]));
			if (kecheng == null) {
				return "dengchu";
			}
			int j = 0;
			List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(data[1]);
			int stem = 0;
			for (String string : xuanXiuIDs) {
				if (string.equals(data[0])) {
					stem = 1;
				}
			}
			Map<String, String> map = new HashMap<>();
			map.put("kechengid", data[1]);
			map.put("xueshengid", data[0]);
			if (stem == 0) {
				try {
					kechengService.insert_shangkemianxiuren(map);
					j = 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					kechengService.delete_shangkexuanxiuren(map);
					kechengService.insert_shangkemianxiuren(map);
					j = 1;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			/**
			 * String banjiids[] = kecheng.getBanjiids().split(","); int count =
			 * 0; for (String banji : banjiids) { if (banji.equals(banjiid)) {
			 * count = 1; } } String newmianxiuids = ""; if
			 * (kecheng.getMianxiuids() != null && kecheng.getMianxiuids() !=
			 * "") { newmianxiuids = kecheng.getMianxiuids() + data[0] + ","; }
			 * else { newmianxiuids = data[0] + ","; } KeCheng keCheng2 = new
			 * KeCheng(); keCheng2.setId(Integer.parseInt(data[1]));
			 * keCheng2.setMianxiuids(newmianxiuids); int j =
			 * kechengService.updateBJandXXandMXByPrimaryKey(keCheng2);
			 */
			if (j != 0) {
				// 发送极光消息
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				xiaoXiFaSong.setXiaoXiMingCheng(user.getXingming() + "免修了这门课《" + kecheng.getKechengmingcheng() + "》");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "免修了这门课《" + kecheng.getKechengmingcheng() + "》");
				xiaoXiFaSong.setShuJuLeiXing(1);
				xiaoXiFaSong.setShuJuId(kecheng.getId());
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
//				xiaoXiFaSong.setXueXiaoId(user.getXuexiaoXuehao().split("_")[0]);
				xiaoXiFaSong.setXueXiaoId(xuexiaoid);
				// 发送提醒消息
				TiXing tiXing = new TiXing();
				Date date2 = new Date();
				tiXing.setNeirong(user.getXingming() + "免修了这门课《" + kecheng.getKechengmingcheng() + "》");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setShujuid(kecheng.getId());
				tiXing.setType(1);
				tiXing.setJieshourenid(Integer.parseInt(data[0]));
				tixingService.insert(tiXing);
				xiaoXiFaSong.setFaSongMuBiao(data[0]);
				jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	// 查看已免修的课程
	@RequestMapping(value = "app_TianJiaKeCheng")
	@ResponseBody
	public List<KeCheng> app_TianJiaKeCheng(HttpServletRequest request) throws Exception {
		String xueshengid = request.getParameter("studentid");
		String banjiid = request.getParameter("banjiid");
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		List<KeCheng> keChengs = new ArrayList<>();
		List<KeCheng> kechengs2 = new ArrayList<>();
		List<KeCheng> keChengs3 = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (str.equals(token)) {
			Map<String, String> map = new HashMap<>();
			XueSheng xues = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
//			String xuexiaoid = xues.getXuexiaoXuehao().split("_")[0];
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xues.getBanjiid());
			map.put("xuenian", xuenian);
			map.put("xueqi", xueqi);
			map.put("xuexiaoid", xuexiaoid);
			map.put("nianfen", xuenian.split("~")[0]);
			XueQi xq = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			String kaishi = xq.getKaishiriqi();
			Date kaishiriqi = sdf.parse(kaishi);
			map.put("banjiid", banjiid);
			map.put("xueshengid", xueshengid);
			List<Map<String, Object>> keChengMap = kechengService.getAllKeChengByBanJiIDAndXueShengID(map);

			for (Map<String, Object> map2 : keChengMap) {
				if (map2.containsKey("xueQiID") && map2.get("xueQiID").toString().equals(xq.getXueqiid().toString())) {
					if (map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("1")) {
						KeCheng kecheng = new KeCheng();
						kecheng.setDanshuangzhoushuoming("1");
						kecheng.setId(Integer.parseInt(map2.get("ID").toString()));
						kecheng.setKechengmingcheng(map2.get("keChengMingCheng").toString());
						kecheng.setRenkejiaoshi(map2.get("renKeJiaoShi").toString());
						kecheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
						kecheng.setJieshuzhou(Integer.parseInt(map2.get("jieShuZhou").toString()));
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(map2.get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(map2.get("jieShuJieCi").toString()));
						kecheng.setKaishijieci(jcsj1.getJieci());
						kecheng.setJieshujieci(jcsj2.getJieci());
						kecheng.setZhouci(Integer.parseInt(map2.get("zhouCi").toString()));
						JiaoShi js = jiaoshiService
								.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
						JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
						kecheng.setJiaoshiming(js.getJiaoshiming());
						kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
						kechengs2.add(kecheng);
					}
					if (map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("2")) {
						KeCheng kecheng = new KeCheng();
						kecheng.setDanshuangzhou(1);
						kecheng.setDanshuangzhoushuoming("2");
						kecheng.setId(Integer.parseInt(map2.get("ID").toString()));
						kecheng.setKechengmingcheng(map2.get("keChengMingCheng").toString());
						kecheng.setRenkejiaoshi(map2.get("renKeJiaoShi").toString());
						kecheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
						kecheng.setJieshuzhou(Integer.parseInt(map2.get("jieShuZhou").toString()));
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(map2.get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(map2.get("jieShuJieCi").toString()));
						kecheng.setKaishijieci(jcsj1.getJieci());
						kecheng.setJieshujieci(jcsj2.getJieci());
						kecheng.setZhouci(Integer.parseInt(map2.get("zhouCi").toString()));
						JiaoShi js = jiaoshiService
								.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
						JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
						kecheng.setJiaoshiming(js.getJiaoshiming());
						kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
						kechengs2.add(kecheng);
					}
					if (map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("3")) {
						KeCheng kecheng = new KeCheng();
						kecheng.setDanshuangzhou(2);
						kecheng.setDanshuangzhoushuoming("3");
						kecheng.setId(Integer.parseInt(map2.get("ID").toString()));
						kecheng.setKechengmingcheng(map2.get("keChengMingCheng").toString());
						kecheng.setRenkejiaoshi(map2.get("renKeJiaoShi").toString());
						kecheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
						kecheng.setJieshuzhou(Integer.parseInt(map2.get("jieShuZhou").toString()));
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(map2.get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(map2.get("jieShuJieCi").toString()));
						kecheng.setKaishijieci(jcsj1.getJieci());
						kecheng.setJieshujieci(jcsj2.getJieci());
						kecheng.setZhouci(Integer.parseInt(map2.get("zhouCi").toString()));
						JiaoShi js = jiaoshiService
								.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
						JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
						kecheng.setJiaoshiming(js.getJiaoshiming());
						kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
						kechengs2.add(kecheng);
					}
					if (map2.containsKey("shiJianLeiXing") && map2.get("shiJianLeiXing").toString().equals("6")) {
						KeCheng kecheng = new KeCheng();
						kecheng.setDanshuangzhoushuoming("6");
						kecheng.setId(Integer.parseInt(map2.get("ID").toString()));
						kecheng.setKechengmingcheng(map2.get("keChengMingCheng").toString());
						kecheng.setRenkejiaoshi(map2.get("renKeJiaoShi").toString());
						kecheng.setKaishizhou(Integer.parseInt(map2.get("kaiShiZhou").toString()));
						JCSJ jcsj1 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(map2.get("kaiShiJieCi").toString()));
						JCSJ jcsj2 = jcsjService
								.selectByPrimaryKey(Integer.parseInt(map2.get("jieShuJieCi").toString()));
						kecheng.setKaishijieci(jcsj1.getJieci());
						kecheng.setJieshujieci(jcsj2.getJieci());
						kecheng.setZhouci(Integer.parseInt(map2.get("zhouCi").toString()));
						JiaoShi js = jiaoshiService
								.selectByPrimaryKey(Integer.parseInt(map2.get("jiaoShiID").toString()));
						JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
						kecheng.setJiaoshiming(js.getJiaoshiming());
						kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
						kechengs2.add(kecheng);
					}
				}
			}

			List<Map<String, String>> tingKeMaps = new ArrayList<>();
			for (Map<String, Object> map22 : keChengMap) {
				if (xq.getXueqiid().toString().equals(map22.get("xueQiID").toString())) {
					if (map22.containsKey("shiJianLeiXing") && map22.get("shiJianLeiXing").toString().equals("5")) {
						Map<String, String> tingKeMap = new HashMap<>();
						tingKeMap.put("ID", map22.get("ID").toString());
						tingKeMap.put("jiaoShiID", map22.get("jiaoShiID").toString());
						tingKeMap.put("zhouCi", map22.get("zhouCi").toString());
						tingKeMap.put("kaiShiJieCi", map22.get("kaiShiJieCi").toString());
						tingKeMap.put("jieShuJieCi", map22.get("jieShuJieCi").toString());
						tingKeMap.put("kaiShiZhou", map22.get("kaiShiZhou").toString());
						tingKeMaps.add(tingKeMap);
					}
				}
			}
			
			for (int i = 0; i < keChengMap.size(); i++) {
				if (keChengMap.get(i).containsKey("xueQiID")
						&& keChengMap.get(i).get("xueQiID").toString().equals(xq.getXueqiid().toString())) {
					if (keChengMap.get(i).containsKey("shiJianLeiXing")
							&& keChengMap.get(i).get("shiJianLeiXing").toString().equals("4")) {
						Map<String, String> firstMap = new HashMap<>();
						Map<String, String> secondMap = new HashMap<>();
						String secondZhou = "";
						String firstZhou = "";
						int ss = 0;
						if (tingKeMaps != null && tingKeMaps.size() > 0) {
							for (Map<String, String> map22 : tingKeMaps) {
								if (map22.get("ID").toString()
										.equals(keChengMap.get(i).get("ID").toString())) {
									if (map22.get("zhouCi").toString()
											.equals(keChengMap.get(i).get("zhouCi").toString())
											&& map22.get("kaiShiJieCi").toString()
													.equals(keChengMap.get(i).get("kaiShiJieCi").toString())
											&& map22.get("jieShuJieCi").toString()
													.equals(keChengMap.get(i).get("jieShuJieCi").toString())
											&& map22.get("kaiShiZhou").toString()
													.equals(keChengMap.get(i).get("kaiShiZhou").toString())
											&& map22.get("jiaoShiID").toString()
													.equals(keChengMap.get(i).get("jiaoShiID").toString())) {
										ss = 1;
									}
								}
							}
						}
						if(ss == 0){
							firstZhou = keChengMap.get(i).get("kaiShiZhou").toString() + ",";
							firstMap.put("id", keChengMap.get(i).get("ID").toString());
							firstMap.put("kechengmingcheng", keChengMap.get(i).get("keChengMingCheng").toString());
							firstMap.put("renkejiaoshi", keChengMap.get(i).get("renKeJiaoShi").toString());
							firstMap.put("zhouci", keChengMap.get(i).get("zhouCi").toString());
							JCSJ jcsj1 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get(i).get("kaiShiJieCi").toString()));
							JCSJ jcsj2 = jcsjService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get(i).get("jieShuJieCi").toString()));
							firstMap.put("kaishijieci", jcsj1.getJieci().toString());
							firstMap.put("jieshujieci", jcsj2.getJieci().toString());
							JiaoShi jshi = jiaoshiService
									.selectByPrimaryKey(Integer.parseInt(keChengMap.get(i).get("jiaoShiID").toString()));
							String jiaoshiming = jshi.getJiaoshiming();
							JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
							String jiaoXueLouMing = jxl.getJiaoXueLouMing();
							String xiaoquming = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng();
							firstMap.put("jiaoshiming", jiaoshiming);
							firstMap.put("xiaoquming", xiaoquming);
							firstMap.put("jiaoXueLouMing", jiaoXueLouMing);
						}
						for (int j = 0; j < keChengMap.size(); j++) {
							if (i != j
									&& keChengMap.get(i).get("ID").toString()
											.equals(keChengMap.get(j).get("ID").toString())
									&& keChengMap.get(j).get("shiJianLeiXing").toString().equals("4")) {
								if (keChengMap.get(i).get("zhouCi").toString()
										.equals(keChengMap.get(j).get("zhouCi").toString())
										&& keChengMap.get(i).get("kaiShiJieCi").toString()
												.equals(keChengMap.get(j).get("kaiShiJieCi").toString())
										&& keChengMap.get(i).get("jieShuJieCi").toString()
												.equals(keChengMap.get(j).get("jieShuJieCi").toString())) {
									int l = 0;
									if (tingKeMaps != null && tingKeMaps.size() > 0) {
										for (Map<String, String> map22 : tingKeMaps) {
											if (map22.get("ID").toString()
													.equals(keChengMap.get(j).get("ID").toString())) {
												if (map22.get("zhouCi").toString()
														.equals(keChengMap.get(j).get("zhouCi").toString())
														&& map22.get("kaiShiJieCi").toString()
																.equals(keChengMap.get(j).get("kaiShiJieCi").toString())
														&& map22.get("jieShuJieCi").toString()
																.equals(keChengMap.get(j).get("jieShuJieCi").toString())
														&& map22.get("kaiShiZhou").toString()
																.equals(keChengMap.get(j).get("kaiShiZhou").toString())
														&& map22.get("jiaoShiID").toString()
																.equals(keChengMap.get(j).get("jiaoShiID").toString())) {
													l = 1;
												}
											}
										}
									}
									if (l == 0) {
										firstZhou += keChengMap.get(j).get("kaiShiZhou").toString() + ",";
									}
								} else {
									int l = 0;
									if (tingKeMaps != null && tingKeMaps.size() > 0) {
										for (Map<String, String> map22 : tingKeMaps) {
											if (map22.get("ID").toString()
													.equals(keChengMap.get(j).get("ID").toString())) {
												if (map22.get("zhouCi").toString()
														.equals(keChengMap.get(j).get("zhouCi").toString())
														&& map22.get("kaiShiJieCi").toString()
																.equals(keChengMap.get(j).get("kaiShiJieCi").toString())
														&& map22.get("jieShuJieCi").toString()
																.equals(keChengMap.get(j).get("jieShuJieCi").toString())
														&& map22.get("kaiShiZhou").toString()
																.equals(keChengMap.get(j).get("kaiShiZhou").toString())
														&& map22.get("jiaoShiID").toString()
																.equals(keChengMap.get(j).get("jiaoShiID").toString())) {
													l = 1;
												}
											}
										}
									}
									if(l == 0){
										secondZhou += keChengMap.get(j).get("kaiShiZhou").toString() + ",";
										secondMap.put("id", keChengMap.get(j).get("ID").toString());
										secondMap.put("kechengmingcheng",
												keChengMap.get(j).get("keChengMingCheng").toString());
										secondMap.put("renkejiaoshi", keChengMap.get(j).get("renKeJiaoShi").toString());
										secondMap.put("zhouci", keChengMap.get(j).get("zhouCi").toString());
										JCSJ jcsj12 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get(j).get("kaiShiJieCi").toString()));
										JCSJ jcsj22 = jcsjService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get(j).get("jieShuJieCi").toString()));
										secondMap.put("kaishijieci", jcsj12.getJieci().toString());
										secondMap.put("jieshujieci", jcsj22.getJieci().toString());
										JiaoShi jshi2 = jiaoshiService.selectByPrimaryKey(
												Integer.parseInt(keChengMap.get(j).get("jiaoShiID").toString()));
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
						if (firstZhou != null && !"".equals(firstZhou)) {
							firstMap.put("zhoushu", firstZhou.substring(0, firstZhou.lastIndexOf(",")));
						}
						if (firstMap != null && !(firstMap.isEmpty())) {
							KeCheng kecheng = new KeCheng();
							kecheng.setId(Integer.parseInt(firstMap.get("id").toString()));
							kecheng.setBanJiMingCheng(firstMap.get("zhoushu").toString());
							int m = 0;
							for (KeCheng kc : kechengs2) {
								if (kc.getId().toString().equals(kecheng.getId().toString())) {
									if (kc.getBanJiMingCheng() != null) {
										String zhou1[] = kc.getBanJiMingCheng().split(",");
										String zhou2[] = kecheng.getBanJiMingCheng().split(",");
										for (String string : zhou1) {
											for (String s : zhou2) {
												if (string.equals(s)) {
													if(kc.getZhouci().toString().equals(firstMap.get("zhouci").toString())){
														m = 1;
														break;
													}
												}
											}
											if (m == 1) {
												break;
											}
										}
										if (m == 1) {
											break;
										}
									}
								}
							}
							if (m == 0) {
								kecheng.setLeixing(3);
								kecheng.setDanshuangzhoushuoming("4");
								kecheng.setKechengmingcheng(firstMap.get("kechengmingcheng").toString());
								kecheng.setRenkejiaoshi(firstMap.get("renkejiaoshi").toString());
								kecheng.setKaishijieci(Integer.parseInt(firstMap.get("kaishijieci").toString()));
								kecheng.setJieshujieci(Integer.parseInt(firstMap.get("jieshujieci").toString()));
								kecheng.setXiaoquming(firstMap.get("xiaoquming").toString());
								kecheng.setJiaoshiming(firstMap.get("jiaoshiming").toString());
								kecheng.setZhouci(Integer.parseInt(firstMap.get("zhouci").toString()));
								kechengs2.add(kecheng);
							}
						}
						if (secondZhou != null && !"".equals(secondZhou)) {
							secondMap.put("zhoushu", secondZhou.substring(0, secondZhou.lastIndexOf(",")));
							KeCheng kecheng = new KeCheng();
							kecheng.setId(Integer.parseInt(secondMap.get("id").toString()));
							kecheng.setBanJiMingCheng(secondMap.get("zhoushu").toString());
							int m = 0;
							for (KeCheng kc : kechengs2) {
								if (kc.getId().toString().equals(kecheng.getId().toString())) {
									if (kc.getBanJiMingCheng() != null) {
										String zhou1[] = kc.getBanJiMingCheng().split(",");
										String zhou2[] = kecheng.getBanJiMingCheng().split(",");
										for (String string : zhou1) {
											for (String s : zhou2) {
												if (string.equals(s)) {
													if(kc.getZhouci().toString().equals(secondMap.get("zhouci").toString())){
														m = 1;
														break;
													}
												}
											}
											if (m == 1) {
												break;
											}
										}
										if (m == 1) {
											break;
										}
									}
								}
							}
							if (m == 0) {
								kecheng.setLeixing(3);
								kecheng.setDanshuangzhoushuoming("4");
								kecheng.setKechengmingcheng(secondMap.get("kechengmingcheng").toString());
								kecheng.setRenkejiaoshi(secondMap.get("renkejiaoshi").toString());
								kecheng.setKaishijieci(Integer.parseInt(secondMap.get("kaishijieci").toString()));
								kecheng.setJieshujieci(Integer.parseInt(secondMap.get("jieshujieci").toString()));
								kecheng.setXiaoquming(secondMap.get("xiaoquming").toString());
								kecheng.setJiaoshiming(secondMap.get("jiaoshiming").toString());
								kecheng.setZhouci(Integer.parseInt(secondMap.get("zhouci").toString()));
								kechengs2.add(kecheng);
							}
						}
					}
				}
			}
			/**
			 * for (KeCheng keCheng : keChengs) { String danShuangZhouShuoMing[]
			 * = keCheng.getDanshuangzhoushuoming().split("zqxj"); switch
			 * (danShuangZhouShuoMing[0]) { case "1": // 连续 String zhouCi[] =
			 * danShuangZhouShuoMing[1].split(","); // 判断 是否调停 String tiaoKe[] =
			 * danShuangZhouShuoMing[4].split("zqjx"); // 获取周几，节次，教室 String
			 * total[] = danShuangZhouShuoMing[2].split("zxqj"); for (String
			 * string2 : total) { String xinxi[] = string2.split(","); KeCheng
			 * kecheng = new KeCheng(); kecheng.setId(keCheng.getId());
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setXuanxiuids(keCheng.getXuanxiuids());
			 * kecheng.setMianxiuids(keCheng.getMianxiuids());
			 * kecheng.setKeDaiBiao(keCheng.getKeDaiBiao());
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
			 * kecheng.setJieshujieci(jcsj2.getJieci()); JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).
			 * getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()).getMingcheng()); kechengs2.add(kecheng); } if
			 * (tiaoKe.length > 0) { for (String string : tiaoKe) { String kc[]
			 * = string.split(","); Calendar cal = Calendar.getInstance();
			 * cal.setTime(sdf.parse(kc[0])); int n =
			 * cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n == 1) {
			 * zhouci = 7; } else { zhouci = n - 1; }
			 * 
			 * Calendar cal1 = Calendar.getInstance(); cal1.setTime(kaishiriqi);
			 * Calendar cal2 = Calendar.getInstance();
			 * cal2.setTime(sdf.parse(kc[0])); int day1 =
			 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
			 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
			 * cal1.get(Calendar.YEAR); int year2 = cal2.get(Calendar.YEAR); int
			 * timeDistance = 0; if (year1 != year2) { // 不同年 for (int i =
			 * year1; i < year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
			 * 400 == 0) {// 闰年 timeDistance += 366; } else { // 不是闰年
			 * timeDistance += 365; } timeDistance += day2 - day1; } } else {//
			 * 同年 timeDistance = day2 - day1; } int zhou = timeDistance / 7 + 1;
			 * 
			 * KeCheng kecheng = new KeCheng(); kecheng.setId(keCheng.getId());
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setXuanxiuids(keCheng.getXuanxiuids());
			 * kecheng.setMianxiuids(keCheng.getMianxiuids());
			 * kecheng.setKeDaiBiao(keCheng.getKeDaiBiao());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(zhou); //
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * 
			 * kecheng.setZhouci(zhouci); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).
			 * getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()).getMingcheng()); kechengs2.add(kecheng); } }
			 * break;
			 * 
			 * case "2": // 单双周 String zhouCi2[] =
			 * danShuangZhouShuoMing[1].split(",");
			 * keCheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * keCheng.setJieshuzhou(Integer.parseInt(zhouCi2[1])); String
			 * tiaoKe2[] = danShuangZhouShuoMing[4].split("zqjx");
			 * Set<Map<String, String>> danSet = new HashSet<>();
			 * Set<Map<String, String>> shuangSet = new HashSet<>();
			 * List<Map<String, String>> maps2 = new ArrayList<>(); // 判断单双周
			 * String xinxi2[] = danShuangZhouShuoMing[2].split("zqjx"); for
			 * (String string : xinxi2[0].split("zxqj")) { // 单周 String str2[] =
			 * string.split(","); Map<String, String> kechengmap = new
			 * HashMap<>(); kechengmap.put("zhouci", str2[0]); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[2]));
			 * kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
			 * kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str2[3]));
			 * String jiaoshiming = jshi.getJiaoshiming(); String jiaoXueLouMing
			 * = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
			 * .getJiaoXueLouMing(); String xiaoquming =
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * (); kechengmap.put("jiaoshiming", jiaoshiming);
			 * kechengmap.put("xiaoquming", xiaoquming);
			 * kechengmap.put("jiaoXueLouMing", jiaoXueLouMing);
			 * danSet.add(kechengmap); } for (String string :
			 * xinxi2[1].split("zxqj")) { // 双周 String str2[] =
			 * string.split(","); Map<String, String> kechengmap = new
			 * HashMap<>(); kechengmap.put("zhouci", str2[0]); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[2]));
			 * kechengmap.put("kaishijieci", jcsj1.getJieci().toString());
			 * kechengmap.put("jieshujieci", jcsj2.getJieci().toString());
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(str2[3]));
			 * String jiaoshiming = jshi.getJiaoshiming(); String jiaoXueLouMing
			 * = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
			 * .getJiaoXueLouMing(); String xiaoquming =
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * (); kechengmap.put("jiaoshiming", jiaoshiming);
			 * kechengmap.put("xiaoquming", xiaoquming);
			 * kechengmap.put("jiaoXueLouMing", jiaoXueLouMing);
			 * shuangSet.add(kechengmap); } // 判断单双set，里重复内容 if (danSet.size() >
			 * shuangSet.size()) { for (Map<String, String> mapp : danSet) { if
			 * (shuangSet.contains(mapp)) { maps2.add(mapp); mapp.put("ds",
			 * "0"); } else { // 单周 mapp.put("ds", "1"); maps2.add(mapp); } } }
			 * if (danSet.size() < shuangSet.size()) { for (Map<String, String>
			 * mapp : shuangSet) { if (danSet.contains(mapp)) { maps2.add(mapp);
			 * mapp.put("ds", "0"); } else { // 双周 mapp.put("ds", "2");
			 * maps2.add(mapp); } } }
			 * 
			 * for (Map<String, String> map3 : maps2) { KeCheng newkecheng = new
			 * KeCheng(); newkecheng.setId(keCheng.getId());
			 * newkecheng.setKechengid(keCheng.getKechengid());
			 * newkecheng.setXuanxiuids(keCheng.getXuanxiuids());
			 * newkecheng.setMianxiuids(keCheng.getMianxiuids());
			 * newkecheng.setKeDaiBiao(keCheng.getKeDaiBiao());
			 * newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * newkecheng.setKaishizhou(keCheng.getKaishizhou());
			 * newkecheng.setJieshuzhou(keCheng.getJieshuzhou());
			 * newkecheng.setDanshuangzhoushuoming(keCheng.
			 * getDanshuangzhoushuoming());
			 * newkecheng.setZhouci(Integer.parseInt(map3.get("zhouci")));
			 * newkecheng.setXiaoquming(map3.get("xiaoquming"));
			 * newkecheng.setJiaoXueLouMing(map3.get("jiaoXueLouMing"));
			 * newkecheng.setJiaoshiming(map3.get("jiaoshiming"));
			 * newkecheng.setKaishijieci(Integer.parseInt(map3.get("kaishijieci"
			 * )));
			 * newkecheng.setJieshujieci(Integer.parseInt(map3.get("jieshujieci"
			 * )));
			 * newkecheng.setDanshuangzhou(Integer.parseInt(map3.get("ds")));
			 * 
			 * kechengs2.add(newkecheng); } if (tiaoKe2.length > 0) {
			 * 
			 * for (String string : tiaoKe2) { String kc[] = string.split(",");
			 * Calendar cal = Calendar.getInstance();
			 * cal.setTime(sdf.parse(kc[0])); int n =
			 * cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n == 1) {
			 * zhouci = 7; } else { zhouci = n - 1; }
			 * 
			 * Calendar cal1 = Calendar.getInstance(); cal1.setTime(kaishiriqi);
			 * Calendar cal2 = Calendar.getInstance();
			 * cal2.setTime(sdf.parse(kc[0])); int day1 =
			 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
			 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
			 * cal1.get(Calendar.YEAR); int year2 = cal2.get(Calendar.YEAR); int
			 * timeDistance = 0; if (year1 != year2) { // 不同年 for (int i =
			 * year1; i < year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
			 * 400 == 0) {// 闰年 timeDistance += 366; } else { // 不是闰年
			 * timeDistance += 365; } timeDistance += day2 - day1; } } else {//
			 * 同年 timeDistance = day2 - day1; } int zhou = timeDistance / 7 + 1;
			 * 
			 * KeCheng kecheng = new KeCheng();
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setXuanxiuids(keCheng.getXuanxiuids());
			 * kecheng.setMianxiuids(keCheng.getMianxiuids());
			 * kecheng.setKeDaiBiao(keCheng.getKeDaiBiao());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setKaishizhou(zhou); //
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(zhouci); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).
			 * getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()).getMingcheng()); kechengs2.add(kecheng); } }
			 * break; case "3": // 单次，不连续,只需比对时间
			 * 
			 * String allXinIn[] = danShuangZhouShuoMing[1].split("zqjx");
			 * String one[] = allXinIn[0].split(","); if (allXinIn.length == 1)
			 * { Calendar cal1 = Calendar.getInstance();
			 * cal1.setTime(kaishiriqi); Calendar cal2 = Calendar.getInstance();
			 * cal2.setTime(sdf.parse(one[0])); int day1 =
			 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
			 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
			 * cal1.get(Calendar.YEAR); int year2 = cal2.get(Calendar.YEAR); int
			 * timeDistance = 0; if (year1 != year2) { // 不同年 for (int i =
			 * year1; i < year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
			 * 400 == 0) {// 闰年 timeDistance += 366; } else { // 不是闰年
			 * timeDistance += 365; } timeDistance += day2 - day1; } } else {//
			 * 同年 timeDistance = day2 - day1; } int zhou = timeDistance / 7 + 1;
			 * Calendar cal3 = Calendar.getInstance();
			 * cal3.setTime(sdf.parse(one[0])); int n3 =
			 * cal3.get(Calendar.DAY_OF_WEEK); int zhouci1 = 0; if (n3 == 1) {
			 * zhouci1 = 7; } else { zhouci1 = n3 - 1; } keCheng.setLeixing(3);
			 * keCheng.setKaishizhou(zhou); keCheng.setBanJiMingCheng(zhou +
			 * ""); keCheng.setZhouci(zhouci1); //
			 * keCheng.setJieshuzhou(Util.WeeksBetweenDays(kaiShiRiQi, //
			 * zhouDate)); JiaoShi jiaoShi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(one[3]));
			 * String xiaoquming =
			 * xiaoquService.selectByPrimaryKey(jiaoShi.getXiaoquid()).
			 * getMingcheng(); JiaoXueLou jiaoXueLou =
			 * jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
			 * int kaiShiJieCi =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(one[1])).getJieci
			 * (); int jieShuJieCi =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(one[2])).getJieci
			 * (); keCheng.setKaishijieci(kaiShiJieCi);
			 * keCheng.setJieshujieci(jieShuJieCi);
			 * keCheng.setXiaoquming(xiaoquming);
			 * keCheng.setJiaoshiming(jiaoShi.getJiaoshiming());
			 * keCheng.setJiaoXueLouMing(jiaoXueLou.getJiaoXueLouMing());
			 * kechengs2.add(keCheng); break; } else if (allXinIn.length > 1) {
			 * String firstZhous = ""; String secondZhous = ""; Map<String,
			 * String> firstMap = new HashMap<>(); Map<String, String> secondMap
			 * = new HashMap<>(); List<Map<String, String>> mapps = new
			 * ArrayList<>();
			 * 
			 * Calendar cal1 = Calendar.getInstance(); cal1.setTime(kaishiriqi);
			 * Calendar cal2 = Calendar.getInstance();
			 * cal2.setTime(sdf.parse(one[0])); int day1 =
			 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
			 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
			 * cal1.get(Calendar.YEAR); int year2 = cal2.get(Calendar.YEAR); int
			 * timeDistance = 0; if (year1 != year2) { // 不同年 for (int i =
			 * year1; i < year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
			 * 400 == 0) {// 闰年 timeDistance += 366; } else { // 不是闰年
			 * timeDistance += 365; } timeDistance += day2 - day1; } } else {//
			 * 同年 timeDistance = day2 - day1; } int zhou = timeDistance / 7 + 1;
			 * firstZhous += zhou; Calendar cal = Calendar.getInstance();
			 * cal.setTime(sdf.parse(one[0])); int n =
			 * cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n == 1) {
			 * zhouci = 7; } else { zhouci = n - 1; } firstMap.put("zhouci",
			 * zhouci + ""); JCSJ jcsj11 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(one[1])); JCSJ
			 * jcsj22 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(one[2]));
			 * firstMap.put("kaishijieci", jcsj11.getJieci().toString());
			 * firstMap.put("jieshujieci", jcsj22.getJieci().toString());
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(one[3]));
			 * String jiaoshiming = jshi.getJiaoshiming(); String jiaoXueLouMing
			 * = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid())
			 * .getJiaoXueLouMing(); String xiaoquming =
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * (); firstMap.put("jiaoshiming", jiaoshiming);
			 * firstMap.put("xiaoquming", xiaoquming);
			 * firstMap.put("jiaoXueLouMing", jiaoXueLouMing); for (int i = 1; i
			 * < allXinIn.length; i++) { String all[] = allXinIn[i].split(",");
			 * if (one[1].equals(all[1]) && one[2].equals(all[2]) &&
			 * one[3].equals(all[3])) { Calendar cal12 = Calendar.getInstance();
			 * cal12.setTime(kaishiriqi); Calendar cal22 =
			 * Calendar.getInstance(); cal22.setTime(sdf.parse(all[0])); int
			 * day12 = cal12.get(Calendar.DAY_OF_YEAR); int day22 =
			 * cal22.get(Calendar.DAY_OF_YEAR); int year12 =
			 * cal12.get(Calendar.YEAR); int year22 = cal22.get(Calendar.YEAR);
			 * int timeDistance2 = 0; if (year12 != year22) { // 不同年 for (int i2
			 * = year12; i2 < year22; i2++) { if (i2 % 4 == 0 && i2 % 100 != 0
			 * || i2 % 400 == 0) {// 闰年 timeDistance2 += 366; } else { // 不是闰年
			 * timeDistance2 += 365; } timeDistance2 += day22 - day12; } } else
			 * {// 同年 timeDistance2 = day22 - day12; } int zhou2 = timeDistance2
			 * / 7 + 1; firstZhous += "," + zhou2;
			 * 
			 * } else { Calendar cal11 = Calendar.getInstance();
			 * cal11.setTime(kaishiriqi); Calendar cal22 =
			 * Calendar.getInstance(); cal22.setTime(sdf.parse(all[0])); int
			 * day12 = cal11.get(Calendar.DAY_OF_YEAR); int day22 =
			 * cal22.get(Calendar.DAY_OF_YEAR); int year12 =
			 * cal11.get(Calendar.YEAR); int year22 = cal22.get(Calendar.YEAR);
			 * int timeDistance2 = 0; if (year12 != year22) { // 不同年 for (int i2
			 * = year12; i2 < year22; i2++) { if (i2 % 4 == 0 && i2 % 100 != 0
			 * || i2 % 400 == 0) {// 闰年 timeDistance2 += 366; } else { // 不是闰年
			 * timeDistance2 += 365; } timeDistance2 += day22 - day12; } } else
			 * {// 同年 timeDistance2 = day22 - day12; } int zhou2 = timeDistance2
			 * / 7 + 1; secondZhous += zhou2 + ","; Calendar cal3 =
			 * Calendar.getInstance(); cal3.setTime(sdf.parse(all[0])); int n3 =
			 * cal3.get(Calendar.DAY_OF_WEEK); int zhouci1 = 0; if (n3 == 1) {
			 * zhouci1 = 7; } else { zhouci1 = n3 - 1; } secondMap.put("zhouci",
			 * zhouci1 + ""); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(all[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(all[2]));
			 * secondMap.put("kaishijieci", jcsj1.getJieci().toString());
			 * secondMap.put("jieshujieci", jcsj2.getJieci().toString());
			 * JiaoShi jshi2 =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(all[3]));
			 * String jiaoshiming2 = jshi2.getJiaoshiming(); String
			 * jiaoXueLouMing2 =
			 * jiaoXueLouService.selectByPrimaryKey(jshi2.getJiaoxuelouid())
			 * .getJiaoXueLouMing(); String xiaoquming2 =
			 * xiaoquService.selectByPrimaryKey(jshi2.getXiaoquid())
			 * .getMingcheng(); secondMap.put("jiaoshiming", jiaoshiming2);
			 * secondMap.put("xiaoquming", xiaoquming2);
			 * secondMap.put("jiaoXueLouMing", jiaoXueLouMing2); } }
			 * firstMap.put("zhoushu", firstZhous);
			 * 
			 * if (null != secondZhous && !("".equals(secondZhous))) {
			 * secondMap.put("zhoushu", secondZhous.substring(0,
			 * secondZhous.lastIndexOf(","))); mapps.add(secondMap); }
			 * 
			 * mapps.add(firstMap);
			 * 
			 * for (Map<String, String> m : mapps) {
			 * 
			 * KeCheng kecheng = new KeCheng(); kecheng.setLeixing(3);
			 * kecheng.setKechengid(keCheng.getKechengid());
			 * kecheng.setXuanxiuids(keCheng.getXuanxiuids());
			 * kecheng.setMianxiuids(keCheng.getMianxiuids());
			 * kecheng.setKeDaiBiao(keCheng.getKeDaiBiao());
			 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
			 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
			 * kecheng.setBanJiMingCheng(m.get("zhoushu")); // 显示 // 周数 //
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCCi2[0])); //
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(Integer.parseInt(m.get("zhouci"))); JCSJ jcsj1
			 * = jcsjService.selectByPrimaryKey(Integer.parseInt(m.get(
			 * "kaishijieci"))); JCSJ jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(m.get(
			 * "jieshujieci"))); kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * 
			 * kecheng.setJiaoshiming(m.get("jiaoshiming"));
			 * kecheng.setJiaoXueLouMing(m.get("jiaoXueLouMing"));
			 * kecheng.setXiaoquming(m.get("xiaoquming"));
			 * kechengs2.add(kecheng); } } break; } }
			 */

			for (KeCheng keCheng : kechengs2) {
				int isadd = 0;
				List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(keCheng.getId().toString());
				if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
					for (String mianxiuid : mianXiuIDs) {
						if (mianxiuid.equals(xueshengid)) {
							isadd = 1;
						}
					}
				}
				keCheng.setIsadd(isadd);
			}
			
			
			for(int i=0; i<kechengs2.size(); i++){
				List<Map<String,String>> mapS = new ArrayList<>();
				Map<String,String> dm = new HashMap<>();
				
				dm.put("kaishijieci", kechengs2.get(i).getKaishijieci().toString());
				dm.put("jieshujieci", kechengs2.get(i).getJieshujieci().toString());
				dm.put("zhouci", kechengs2.get(i).getZhouci().toString());
				dm.put("xiaoquming", kechengs2.get(i).getXiaoquming());
				dm.put("jiaoshiming", kechengs2.get(i).getJiaoshiming());
				if(kechengs2.get(i).getDanshuangzhoushuoming().equals("1")){
					dm.put("kaishizhou", kechengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", kechengs2.get(i).getJieshuzhou().toString());
				}
				if(kechengs2.get(i).getDanshuangzhoushuoming().equals("2")){
					dm.put("kaishizhou", kechengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", kechengs2.get(i).getJieshuzhou().toString());
					dm.put("danshuangzhou", kechengs2.get(i).getDanshuangzhou().toString());
				}
				if(kechengs2.get(i).getDanshuangzhoushuoming().equals("3")){
					dm.put("kaishizhou", kechengs2.get(i).getKaishizhou().toString());
					dm.put("jieshuzhou", kechengs2.get(i).getJieshuzhou().toString());
					dm.put("danshuangzhou", kechengs2.get(i).getDanshuangzhou().toString());
				}
				if(kechengs2.get(i).getDanshuangzhoushuoming().equals("4")){
					dm.put("banJiMingCheng", kechengs2.get(i).getBanJiMingCheng());
					dm.put("leixing", String.valueOf(kechengs2.get(i).getLeixing()));
				}
				if(kechengs2.get(i).getDanshuangzhoushuoming().equals("6")){
					dm.put("kaishizhou", kechengs2.get(i).getKaishizhou().toString());
				}
				mapS.add(dm);
				for(int j=0; j<kechengs2.size(); j++){
					if(i != j ){
						if(kechengs2.get(i).getId().toString().equals(kechengs2.get(j).getId().toString())){
							Map<String,String> dmj = new HashMap<>();
							dmj.put("kaishijieci", kechengs2.get(j).getKaishijieci().toString());
							dmj.put("jieshujieci", kechengs2.get(j).getJieshujieci().toString());
							dmj.put("zhouci", kechengs2.get(j).getZhouci().toString());
							dmj.put("xiaoquming", kechengs2.get(j).getXiaoquming());
							dmj.put("jiaoshiming", kechengs2.get(j).getJiaoshiming());
							if(kechengs2.get(j).getDanshuangzhoushuoming().equals("1")){
								dmj.put("kaishizhou", kechengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", kechengs2.get(j).getJieshuzhou().toString());
							}
							if(kechengs2.get(j).getDanshuangzhoushuoming().equals("2")){
								dmj.put("kaishizhou", kechengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", kechengs2.get(j).getJieshuzhou().toString());
								dmj.put("danshuangzhou", kechengs2.get(j).getDanshuangzhou().toString());
							}
							if(kechengs2.get(j).getDanshuangzhoushuoming().equals("3")){
								dmj.put("kaishizhou", kechengs2.get(j).getKaishizhou().toString());
								dmj.put("jieshuzhou", kechengs2.get(j).getJieshuzhou().toString());
								dmj.put("danshuangzhou", kechengs2.get(j).getDanshuangzhou().toString());
							}
							if(kechengs2.get(j).getDanshuangzhoushuoming().equals("4")){
								dm.put("banJiMingCheng", kechengs2.get(i).getBanJiMingCheng());
								dm.put("leixing", String.valueOf(kechengs2.get(i).getLeixing()));
							}
							if(kechengs2.get(j).getDanshuangzhoushuoming().equals("6")){
								dmj.put("kaishizhou", kechengs2.get(j).getKaishizhou().toString());
							}
							mapS.add(dmj);
						}
					}
				}
				kechengs2.get(i).setMaps(mapS);
				if(!keChengs3.isEmpty() && keChengs3.size()>0){
					int temp = 0 ;
					for(KeCheng kc : keChengs3){
						if(kc.getId().toString().equals(kechengs2.get(i).getId().toString())){
							temp = 1;
							break;
						}
					}
					if(temp == 0){
						keChengs3.add(kechengs2.get(i));
					}
				}else{
					keChengs3.add(kechengs2.get(i));
				}
			}
			
		} else {
			return null;
		}
		return keChengs3;
	}

	// 将已免修的课程，取消免修，重新添加到课表中
	@RequestMapping(value = "app_SaveTianJiaKeCheng")
	@ResponseBody
	public String app_SaveTianJiaKeCheng(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String banjiid = request.getParameter("banjiid");
		String xueshengid = data[0];
		String id = data[1];
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (keCheng == null) {
				return "dengchu";
			}
			int k = 0;
			List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
			int tem = 0;
			for (String string : mianXiuIDs) {
				if (string.equals(xueshengid)) {
					tem = 1;
				}
			}
			if (tem == 1) {
				int p = 0;
				List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(id);
				for (String string : xuanXiuIDs) {
					if (string.equals(xueshengid)) {
						p = 1;
					}
				}
				if (p == 0) {
					try {
						Map<String, String> map = new HashMap<>();
						map.put("kechengid", id);
						map.put("xueshengid", xueshengid);
						kechengService.delete_shangkemianxiuren(map);
						kechengService.insert_shangkexuanxiuren(map);
						k = 1;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			/**
			 * String mianxiuids[] = keCheng.getMianxiuids().split(","); String
			 * newmianxiuids = ""; for (String mianxiu : mianxiuids) { if
			 * (!mianxiu.equals(xueshengid)) { newmianxiuids += mianxiu + ","; }
			 * } KeCheng keCheng2 = new KeCheng();
			 * keCheng2.setId(Integer.parseInt(id));
			 * keCheng2.setMianxiuids(newmianxiuids); int k =
			 * kechengService.updateBJandXXandMXByPrimaryKey(keCheng2);
			 */
			if (k != 0) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	// 调停课程页面显示数据
	@RequestMapping(value = "app_TiaoTingKeCheng")
	@ResponseBody
	public Object app_TiaoTingKeCheng(HttpServletRequest request) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String ids[] = request.getParameter("id").split("/");
		String qufen = request.getParameter("qufen");
		String id = ids[0];
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			Map<String, Object> map = new HashMap<>();
			map.put("xiaoqus", "");
			map.put("riqi", "");
			map.put("kecheng", "");
			map.put("zongjieci", "");
			if (!Util.isNumeric(id)) {
				return JSON.toJSON(map);
			}
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (kecheng == null) {
				return JSON.toJSON(map);
			}
			String tianjiarenid = kecheng.getTianjiarenid();
			if (!xueshengid.equals(tianjiarenid)) {
				return JSON.toJSON(map);
			}
//			String xuexiaoid = xuexiaoXuehao.split("_")[0];
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			String xuenian = ids[3];
			String xueqi = ids[4];
			Map<String, String> m = new HashMap<String, String>();
			m.put("xuenian", xuenian);
			m.put("xueqi", xueqi);
			m.put("xuexiaoid", xuexiaoid);
			m.put("nianfen", xuenian.split("~")[0]);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(m);

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<String> riqi = new ArrayList<>();
			Map<String, Integer> map2 = new HashMap<>();
			map2.put("xuexiaoid", Integer.parseInt(xuexiaoid));
			map2.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map2);
			int weeknum = Util.WeeksBetweenDays(simpleDateFormat.parse(xueQi.getKaishiriqi()),
					simpleDateFormat.parse(xueQi.getJieshuriqi()));

			List<Map<String, Object>> shangKeShiJianMap = kechengService.getShangKeShiJianByID(ids[0]);
			List<Map<String, String>> tingKeShiJianMap = new ArrayList<>();
			if (shangKeShiJianMap.size() > 0 && shangKeShiJianMap != null) {
				// 从 所有上课时间 中，得所有停课时间 --- shiJianLeiXing=5
				for (Map<String, Object> map3 : shangKeShiJianMap) {
					if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("5")) {
						Map<String, String> tkmap = new HashMap<>();
						tkmap.put("kaishizhou", map3.get("kaiShiZhou").toString());
						tkmap.put("zhouci", map3.get("zhouCi").toString());
						tingKeShiJianMap.add(tkmap);
					}
				}

				// var a = id0 + "/" + zhou1 + "/" + zhouci2 + "/" + xuenian3 +
				// "/" + xueqi4 + "/" + danshuang5 + "/" + kaishijieci6 + "/" +
				// jieshujieci7 + "/" + kaishizhou8 + "/" + jieshuzhou9;
				if (ids[9] != null && Integer.parseInt(ids[9]) != 0) {
					if (ids[5].equals("1")) { // 2
						for (Map<String, Object> map3 : shangKeShiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("2")) {
								if (ids[8].equals(map3.get("kaiShiZhou").toString())
										&& ids[9].equals(map3.get("jieShuZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
											ids[6]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
											ids[7]);
									if (ids[2].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										kecheng.setDanshuangzhou(1);
										kecheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
												+ map3.get("kaiShiJieCi").toString() + ","
												+ map3.get("jieShuJieCi").toString());
										kecheng.setZhouci(Integer.parseInt(ids[2]));
										kecheng.setKaishijieci(Integer.parseInt(ids[6]));
										kecheng.setJieshujieci(Integer.parseInt(ids[7]));
										kecheng.setKaishizhou(Integer.parseInt(ids[8]));
										kecheng.setJieshuzhou(Integer.parseInt(ids[9]));
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
										XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
										kecheng.setJiaoshiming(js.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										kecheng.setXiaoquming(xq.getMingcheng());
										kecheng.setXiaoqu(xq.getXiaoquid().toString());
										kecheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());

										// 根据开始周 结束周，判断日期
										String[] shangkeriqi = new String[Integer.parseInt(ids[9])
												- Integer.parseInt(ids[8]) + 1];
										// 判断开始周是 单周还是双周
										int o = Integer.parseInt(ids[8]) % 2;
										if (o == 0) {// 偶数
											for (int a = 0; a < shangkeriqi.length; a++) {
												if (a % 2 != 0) {
													// 获取每次上课距离学期开始的天数
													int day = (Integer.parseInt(ids[8]) - 1 + a) * 7
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
													int day = (Integer.parseInt(ids[8]) - 1 + a) * 7
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
					} else if (ids[5].equals("2")) { // 3
						for (Map<String, Object> map3 : shangKeShiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("3")) {
								if (ids[8].equals(map3.get("kaiShiZhou").toString())
										&& ids[9].equals(map3.get("jieShuZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
											ids[6]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
											ids[7]);
									if (ids[2].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										kecheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
												+ map3.get("kaiShiJieCi").toString() + ","
												+ map3.get("jieShuJieCi").toString());
										kecheng.setDanshuangzhou(2);
										kecheng.setZhouci(Integer.parseInt(ids[2]));
										kecheng.setKaishijieci(Integer.parseInt(ids[6]));
										kecheng.setJieshujieci(Integer.parseInt(ids[7]));
										kecheng.setKaishizhou(Integer.parseInt(ids[8]));
										kecheng.setJieshuzhou(Integer.parseInt(ids[9]));
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
										XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
										kecheng.setJiaoshiming(js.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										kecheng.setXiaoquming(xq.getMingcheng());
										kecheng.setXiaoqu(xq.getXiaoquid().toString());
										kecheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());

										// 根据开始周 结束周，判断日期
										String[] shangkeriqi = new String[Integer.parseInt(ids[9])
												- Integer.parseInt(ids[8]) + 1];
										// 判断开始周是 单周还是双周
										int o = Integer.parseInt(ids[8]) % 2;
										if (o == 0) {// 偶数
											for (int a = 0; a < shangkeriqi.length; a++) {
												if (a % 2 == 0) {
													// 获取每次上课距离学期开始的天数
													int day = (Integer.parseInt(ids[8]) - 1 + a) * 7
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
													int day = (Integer.parseInt(ids[8]) - 1 + a) * 7
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
					} else { // 1
						for (Map<String, Object> map3 : shangKeShiJianMap) {
							if (map3.containsKey("shiJianLeiXing")
									&& map3.get("shiJianLeiXing").toString().equals("1")) {
								if (ids[8].equals(map3.get("kaiShiZhou").toString())
										&& ids[9].equals(map3.get("jieShuZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
											ids[6]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
											ids[7]);
									if (ids[2].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										kecheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
												+ map3.get("kaiShiJieCi").toString() + ","
												+ map3.get("jieShuJieCi").toString());
										kecheng.setDanshuangzhou(0);
										kecheng.setZhouci(Integer.parseInt(ids[2]));
										kecheng.setKaishijieci(Integer.parseInt(ids[6]));
										kecheng.setJieshujieci(Integer.parseInt(ids[7]));
										kecheng.setKaishizhou(Integer.parseInt(ids[8]));
										kecheng.setJieshuzhou(Integer.parseInt(ids[9]));
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
										XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
										kecheng.setJiaoshiming(js.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										kecheng.setXiaoquming(xq.getMingcheng());
										kecheng.setXiaoqu(xq.getXiaoquid().toString());
										kecheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());

										// 根据开始周 结束周，判断日期
										String[] shangkeriqi = new String[Integer.parseInt(ids[9])
												- Integer.parseInt(ids[8]) + 1];
										for (int a = 0; a < shangkeriqi.length; a++) {
											// 获取每次上课距离学期开始的天数
											int day = (Integer.parseInt(ids[8]) - 1 + a) * 7 + Integer.parseInt(ids[2])
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
				} else {// 4或6
					for (Map<String, Object> map3 : shangKeShiJianMap) {
						if (map3.containsKey("shiJianLeiXing") && (map3.get("shiJianLeiXing").toString().equals("4")
								|| map3.get("shiJianLeiXing").toString().equals("6"))) {
							if (map3.get("shiJianLeiXing").toString().equals("6")) {
								if (ids[1].equals(map3.get("kaiShiZhou").toString())) {
									int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
											ids[6]);
									int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
											ids[7]);
									if (ids[2].equals(map3.get("zhouCi").toString())
											&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
											&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
										kecheng.setLeixing(3);
										kecheng.setKaishizhou(Integer.parseInt(map3.get("kaiShiZhou").toString()));
										kecheng.setZhouci(Integer.parseInt(ids[2]));
										kecheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
												+ map3.get("kaiShiJieCi").toString() + ","
												+ map3.get("jieShuJieCi").toString());
										kecheng.setKaishijieci(Integer.parseInt(ids[6]));
										kecheng.setJieshujieci(Integer.parseInt(ids[7]));
										JiaoShi js = jiaoshiService
												.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
										XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
										kecheng.setJiaoshiming(js.getJiaoshiming());
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
										kecheng.setXiaoquming(xq.getMingcheng());
										kecheng.setXiaoqu(xq.getXiaoquid().toString());
										kecheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());

										// 获取每次上课距离学期开始的天数
										int day = (Integer.parseInt(ids[1]) - 1) * 7 + Integer.parseInt(ids[2]) - 1;
										// 获取每次上课日期
										String date = simpleDateFormat
												.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
														+ (long) day * 24 * 60 * 60 * 1000));
										kecheng.setBanJiMingCheng(date);
										riqi.add(date);
									}
								}
							} else {
								if("2".equals(qufen)){
									if (ids[1].equals(map3.get("kaiShiZhou").toString())) {
										int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
												ids[6]);
										int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
												ids[7]);
										if (ids[2].equals(map3.get("zhouCi").toString())
												&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
												&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
											kecheng.setLeixing(3);
											kecheng.setKaishizhou(Integer.parseInt(map3.get("kaiShiZhou").toString()));
											kecheng.setZhouci(Integer.parseInt(ids[2]));
											kecheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
													+ map3.get("kaiShiJieCi").toString() + ","
													+ map3.get("jieShuJieCi").toString());
											kecheng.setKaishijieci(Integer.parseInt(ids[6]));
											kecheng.setJieshujieci(Integer.parseInt(ids[7]));
											JiaoShi js = jiaoshiService
													.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
											JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
											XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
											kecheng.setJiaoshiming(js.getJiaoshiming());
											kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
											kecheng.setXiaoquming(xq.getMingcheng());
											kecheng.setXiaoqu(xq.getXiaoquid().toString());
											kecheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());

											// 获取每次上课距离学期开始的天数
											int day = (Integer.parseInt(ids[1]) - 1) * 7 + Integer.parseInt(ids[2]) - 1;
											// 获取每次上课日期
											String date = simpleDateFormat
													.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
															+ (long) day * 24 * 60 * 60 * 1000));
											kecheng.setBanJiMingCheng(date);
											riqi.add(date);
										}
									}
								}else{
									String zhous[] = ids[8].split(",");
									for (String string : zhous) {
										if (string.equals(map3.get("kaiShiZhou").toString())) {
											int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													ids[6]);
											int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(),
													ids[7]);
											if (ids[2].equals(map3.get("zhouCi").toString())
													&& (kaishijieciid + "").equals(map3.get("kaiShiJieCi").toString())
													&& (jieshujieciid + "").equals(map3.get("jieShuJieCi").toString())) {
												kecheng.setLeixing(3);
												kecheng.setKaishizhou(Integer.parseInt(map3.get("kaiShiZhou").toString()));
												kecheng.setZhouci(Integer.parseInt(ids[2]));
												kecheng.setShangkeriqi(map3.get("jiaoShiID").toString() + ","
														+ map3.get("kaiShiJieCi").toString() + ","
														+ map3.get("jieShuJieCi").toString());
												kecheng.setKaishijieci(Integer.parseInt(ids[6]));
												kecheng.setJieshujieci(Integer.parseInt(ids[7]));
												JiaoShi js = jiaoshiService
														.selectByPrimaryKey(Integer.parseInt(map3.get("jiaoShiID").toString()));
												JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(js.getJiaoxuelouid());
												XiaoQu xq = xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId());
												kecheng.setJiaoshiming(js.getJiaoshiming());
												kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
												kecheng.setXiaoquming(xq.getMingcheng());
												kecheng.setXiaoqu(xq.getXiaoquid().toString());
												kecheng.setShangkejiaoshi(map3.get("jiaoShiID").toString());

												// 获取每次上课距离学期开始的天数
												int day = (Integer.parseInt(string) - 1) * 7 + Integer.parseInt(ids[2]) - 1;
												// 获取每次上课日期
												String date = simpleDateFormat
														.format(new Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime()
																+ (long) day * 24 * 60 * 60 * 1000));
												kecheng.setBanJiMingCheng(date);
												riqi.add(date);
											}
										}
									}
								}
							}

						}
					}
				}
			}
			// 去掉停课日期
			if (tingKeShiJianMap != null && tingKeShiJianMap.size() > 0) {
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
			}

			/**
			 * if (!(kecheng.getDanshuangzhoushuoming().isEmpty())) { String
			 * danShuangZhouShuoMing[] =
			 * kecheng.getDanshuangzhoushuoming().split("zqxj"); switch
			 * (danShuangZhouShuoMing[0]) { case "1": // 连续
			 * kecheng.setLeixing(1); String zhouCi[] =
			 * danShuangZhouShuoMing[1].split(","); // 获得1-16周所有上课对应的日期 // 判断
			 * 是否调停 String tiaoTing[] = danShuangZhouShuoMing[3].split("zqjx");
			 * String tiaoKe[] = danShuangZhouShuoMing[4].split("zqjx"); //
			 * 获取周几，节次，教室 String total[] =
			 * danShuangZhouShuoMing[2].split("zxqj"); for (String string2 :
			 * total) { String xinxi[] = string2.split(","); if
			 * (xinxi[0].equals(ids[2]) && xinxi[1].equals(ids[6]) &&
			 * xinxi[2].equals(ids[7])) { // 根据当前周，判断日期 String[] shangkeriqi =
			 * new String[Integer.parseInt(zhouCi[1]) -
			 * Integer.parseInt(zhouCi[0]) + 1];
			 * 
			 * for (int a = 0; a < shangkeriqi.length; a++) { // 获取每次上课距离学期开始的天数
			 * int day = (Integer.parseInt(zhouCi[0]) - 1 + a) * 7 +
			 * Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 shangkeriqi[a] =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); riqi.add(shangkeriqi[a]); }
			 * String j = new String(); for (String string : tiaoTing) { for
			 * (int k = 0; k < riqi.size(); k++) { if
			 * (riqi.get(k).equals(string)) { j += "," + k; } } } for (String s
			 * : j.trim().split(",")) { if (!(s.equals("")) && s != null) {
			 * riqi.remove(Integer.parseInt(s)); } }
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * 
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * kecheng.setShangkejiaoshi(xinxi[3]);
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
			 * kecheng.setXiaoqu(jshi.getXiaoquid() + "");
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).
			 * getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()).getMingcheng()); } } if (tiaoKe.length > 0) {
			 * String p = ""; for (String string : tiaoKe) { // int day =
			 * Util.daysBetween(string.split(",")[0], // xueQi.getKaishiriqi());
			 * Calendar cal = Calendar.getInstance();
			 * cal.setTime(simpleDateFormat.parse(string.split(",")[0])); int n
			 * = cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n == 1) {
			 * zhouci = 7; } else { zhouci = n - 1; } if (ids[2].equals(zhouci +
			 * "") && ids[6].equals(string.split(",")[1]) &&
			 * ids[7].equals(string.split(",")[2])) { String s[] =
			 * string.split(","); riqi.add(s[0]); p = string.split(",")[0]; } }
			 * for (String string : tiaoKe) { String kc[] = string.split(",");
			 * if (p.equals(kc[0])) { Calendar cal1 = Calendar.getInstance();
			 * cal1.setTime(simpleDateFormat.parse(xueQi.getKaishiriqi()));
			 * Calendar cal2 = Calendar.getInstance();
			 * cal2.setTime(simpleDateFormat.parse(p)); int day1 =
			 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
			 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
			 * cal1.get(Calendar.YEAR); int year2 = cal2.get(Calendar.YEAR); int
			 * timeDistance = 0; if (year1 != year2) { // 不同年 for (int i =
			 * year1; i < year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
			 * 400 == 0) {// 闰年 timeDistance += 366; } else { // 不是闰年
			 * timeDistance += 365; } timeDistance += day2 - day1; } } else {//
			 * 同年 timeDistance = day2 - day1; } int zhou = timeDistance / 7 + 1;
			 * 
			 * Calendar cal = Calendar.getInstance();
			 * cal.setTime(simpleDateFormat.parse(p)); int n =
			 * cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n == 1) {
			 * zhouci = 7; } else { zhouci = n - 1; }
			 * kecheng.setKaishizhou(zhou); //
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * kecheng.setZhouci(zhouci); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * kecheng.setShangkejiaoshi(kc[3]); JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
			 * kecheng.setXiaoqu(jshi.getXiaoquid() + "");
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService.selectByPrimaryKey(
			 * jshi.getJiaoxuelouid()) .getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); break; } } } break; case "2": // 单双周 kecheng.setLeixing(2);
			 * String zhouCi2[] = danShuangZhouShuoMing[1].split(","); // 判断单双周
			 * String danShuang[] = danShuangZhouShuoMing[2].split("zqjx");
			 * String tiaoTing2[] = danShuangZhouShuoMing[3].split("zqjx");
			 * String tiaoKe2[] = danShuangZhouShuoMing[4].split("zqjx"); String
			 * ds = ids[5]; kecheng.setKeDaiBiao(ds); if (ds.equals("0")) { //
			 * 全连续 String total2[] = danShuang[0].split("zxqj"); for (String
			 * string2 : total2) { String xinxi[] = string2.split(","); if
			 * (xinxi[0].equals(ids[2]) && xinxi[1].equals(ids[6]) &&
			 * xinxi[2].equals(ids[7])) { // 根据当前周，判断日期 String[] shangkeriqi =
			 * new String[Integer.parseInt(zhouCi2[1]) -
			 * Integer.parseInt(zhouCi2[0]) + 1]; for (int a = 0; a <
			 * shangkeriqi.length; a++) { // 获取每次上课距离学期开始的天数 int day =
			 * (Integer.parseInt(zhouCi2[0]) - 1 + a) * 7 +
			 * Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 shangkeriqi[a] =
			 * simpleDateFormat .format(new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); riqi.add(shangkeriqi[a]); }
			 * String j = new String(); for (String string : tiaoTing2) { for
			 * (int k = 0; k < riqi.size(); k++) { if
			 * (riqi.get(k).equals(string)) { j += "," + k; } } } for (String s
			 * : j.trim().split(",")) { if (!(s.equals("")) && s != null) {
			 * riqi.remove(Integer.parseInt(s)); } }
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * kecheng.setShangkejiaoshi(xinxi[3]); JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
			 * kecheng.setXiaoqu(jshi.getXiaoquid() + "");
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService.selectByPrimaryKey(
			 * jshi.getJiaoxuelouid()) .getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); } } } if (ds.equals("1")) { // 单周 String total2[] =
			 * danShuang[0].split("zxqj"); for (String string2 : total2) {
			 * String xinxi[] = string2.split(","); if (xinxi[0].equals(ids[2])
			 * && xinxi[1].equals(ids[6]) && xinxi[2].equals(ids[7])) { //
			 * 根据当前周，判断日期 String[] shangkeriqi = new
			 * String[Integer.parseInt(zhouCi2[1]) -
			 * Integer.parseInt(zhouCi2[0]) + 1]; // 判断zhouCi[0]是 单周还是双周 int o =
			 * Integer.parseInt(zhouCi2[0]) % 2; if (o == 0) {// 偶数 for (int a =
			 * 0; a < shangkeriqi.length; a++) { if (a % 2 != 0) { //
			 * 获取每次上课距离学期开始的天数 int day = (Integer.parseInt(zhouCi2[0]) - 1 + a)
			 * * 7 + Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 shangkeriqi[a]
			 * = simpleDateFormat.format( new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); riqi.add(shangkeriqi[a]); } }
			 * } else {// 奇数 for (int a = 0; a < shangkeriqi.length; a++) { if
			 * ((a + 1) % 2 != 0) { // 获取每次上课距离学期开始的天数 int day =
			 * (Integer.parseInt(zhouCi2[0]) - 1 + a) * 7 +
			 * Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 shangkeriqi[a] =
			 * simpleDateFormat.format( new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); riqi.add(shangkeriqi[a]); } }
			 * } String j = new String(); for (String string : tiaoTing2) { for
			 * (int k = 0; k < riqi.size(); k++) { if
			 * (riqi.get(k).equals(string)) { j += "," + k; } } } for (String s
			 * : j.trim().split(",")) { if (!("".equals(s)) && s != null) {
			 * riqi.remove(Integer.parseInt(s)); } }
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * kecheng.setShangkejiaoshi(xinxi[3]);
			 * 
			 * JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
			 * kecheng.setXiaoqu(jshi.getXiaoquid() + "");
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService.selectByPrimaryKey(
			 * jshi.getJiaoxuelouid()) .getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); } } } if (ds.equals("2")) { // 双周 String total2[] =
			 * danShuang[1].split("zxqj"); for (String string2 : total2) {
			 * String xinxi[] = string2.split(","); if (xinxi[0].equals(ids[2])
			 * && xinxi[1].equals(ids[6]) && xinxi[2].equals(ids[7])) { //
			 * 根据当前周，判断日期 String[] shangkeriqi = new
			 * String[Integer.parseInt(zhouCi2[1]) -
			 * Integer.parseInt(zhouCi2[0]) + 1]; int o =
			 * Integer.parseInt(zhouCi2[0]) % 2; if (o == 0) {// 偶数 for (int a =
			 * 0; a < shangkeriqi.length; a++) { if (a % 2 == 0) { //
			 * 获取每次上课距离学期开始的天数 int day = (Integer.parseInt(zhouCi2[0]) - 1 + a)
			 * * 7 + Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 shangkeriqi[a]
			 * = simpleDateFormat.format( new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); riqi.add(shangkeriqi[a]); } }
			 * } else {// 奇数 for (int a = 0; a < shangkeriqi.length; a++) { if
			 * ((a + 1) % 2 == 0) { // 获取每次上课距离学期开始的天数 int day =
			 * (Integer.parseInt(zhouCi2[0]) - 1 + a) * 7 +
			 * Integer.parseInt(xinxi[0]) - 1; // 获取每次上课日期 shangkeriqi[a] =
			 * simpleDateFormat.format( new
			 * Date(simpleDateFormat.parse(xueQi.getKaishiriqi()).getTime() +
			 * (long) day * 24 * 60 * 60 * 1000)); riqi.add(shangkeriqi[a]); } }
			 * } String j = new String(); for (String string : tiaoTing2) { for
			 * (int k = 0; k < riqi.size(); k++) { if
			 * (riqi.get(k).equals(string)) { j += "," + k; } } } for (String s
			 * : j.trim().split(",")) { if (!(s.equals("")) && s != null) {
			 * riqi.remove(Integer.parseInt(s)); } }
			 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
			 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1])); JCSJ
			 * jcsj2 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * kecheng.setShangkejiaoshi(xinxi[3]); JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]));
			 * kecheng.setXiaoqu(jshi.getXiaoquid() + "");
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService.selectByPrimaryKey(
			 * jshi.getJiaoxuelouid()) .getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ());
			 * 
			 * } } } if (tiaoKe2.length > 0) { String p = ""; for (String string
			 * : tiaoKe2) { int day = Util.daysBetween(string.split(",")[0],
			 * xueQi.getKaishiriqi()); Calendar cal = Calendar.getInstance();
			 * cal.setTime(simpleDateFormat.parse(string.split(",")[0])); int n
			 * = cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n == 1) {
			 * zhouci = 7; } else { zhouci = n - 1; } if (ids[2].equals(zhouci +
			 * "") && ids[6].equals(string.split(",")[1]) &&
			 * ids[7].equals(string.split(",")[2])) { String s[] =
			 * string.split(","); riqi.add(s[0]); p = string.split(",")[0]; } }
			 * for (String string : tiaoKe2) { String kc[] = string.split(",");
			 * if (p.equals(kc[0])) { Calendar cal1 = Calendar.getInstance();
			 * cal1.setTime(simpleDateFormat.parse(xueQi.getKaishiriqi()));
			 * Calendar cal2 = Calendar.getInstance();
			 * cal2.setTime(simpleDateFormat.parse(p)); int day1 =
			 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
			 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
			 * cal1.get(Calendar.YEAR); int year2 = cal2.get(Calendar.YEAR); int
			 * timeDistance = 0; if (year1 != year2) { // 不同年 for (int i =
			 * year1; i < year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
			 * 400 == 0) {// 闰年 timeDistance += 366; } else { // 不是闰年
			 * timeDistance += 365; } timeDistance += day2 - day1; } } else {//
			 * 同年 timeDistance = day2 - day1; } int zhou = timeDistance / 7 + 1;
			 * 
			 * Calendar cal = Calendar.getInstance();
			 * cal.setTime(simpleDateFormat.parse(p)); int n =
			 * cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n == 1) {
			 * zhouci = 7; } else { zhouci = n - 1; }
			 * kecheng.setKaishizhou(zhou); //
			 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
			 * kecheng.setZhouci(zhouci); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * kecheng.setShangkejiaoshi(kc[3]); JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
			 * kecheng.setXiaoqu(jshi.getXiaoquid() + "");
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(jiaoXueLouService.selectByPrimaryKey(
			 * jshi.getJiaoxuelouid()) .getJiaoXueLouMing());
			 * kecheng.setXiaoquming(
			 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).getMingcheng
			 * ()); break; } } }
			 * 
			 * break; case "3": // 单次，周数，周次 kecheng.setLeixing(3); String zhou =
			 * ids[1]; // kecheng.setKaifangyuanxi(ids[1]); String kcheng[] =
			 * danShuangZhouShuoMing[1].split("zqjx"); // String zhoushu[] =
			 * ids[1].split(","); String zhouci1 = ids[2]; String p = ""; for
			 * (String string : kcheng) { String str1[] = string.split(",");
			 * Calendar cal1 = Calendar.getInstance();
			 * cal1.setTime(simpleDateFormat.parse(xueQi.getKaishiriqi()));
			 * Calendar cal2 = Calendar.getInstance();
			 * cal2.setTime(simpleDateFormat.parse(str1[0])); int day1 =
			 * cal1.get(Calendar.DAY_OF_YEAR); int day2 =
			 * cal2.get(Calendar.DAY_OF_YEAR); int year1 =
			 * cal1.get(Calendar.YEAR); int year2 = cal2.get(Calendar.YEAR); int
			 * timeDistance = 0; if (year1 != year2) { // 不同年 for (int i =
			 * year1; i < year2; i++) { if (i % 4 == 0 && i % 100 != 0 || i %
			 * 400 == 0) {// 闰年 timeDistance += 366; } else { // 不是闰年
			 * timeDistance += 365; } timeDistance += day2 - day1; } } else {//
			 * 同年 timeDistance = day2 - day1; } int zhous = timeDistance / 7 +
			 * 1; Calendar cal = Calendar.getInstance();
			 * cal.setTime(simpleDateFormat.parse(str1[0])); int n =
			 * cal.get(Calendar.DAY_OF_WEEK); int zhouci = 0; if (n == 1) {
			 * zhouci = 7; } else { zhouci = n - 1; } if (zhouci ==
			 * Integer.parseInt(zhouci1) && ids[6].equals(str1[1]) &&
			 * ids[7].equals(str1[2]) && zhous == Integer.parseInt(zhou)) {
			 * riqi.add(str1[0]); p = str1[0]; } } for (String string : kcheng)
			 * { String kc[] = string.split(","); if (p.equals(kc[0])) {
			 * kecheng.setKaishizhou(Integer.parseInt(zhou));
			 * kecheng.setZhouci(Integer.parseInt(zhouci1));
			 * kecheng.setShangkeriqi(kc[0]); JCSJ jcsj1 =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
			 * jcsj2 = jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
			 * kecheng.setKaishijieci(jcsj1.getJieci());
			 * kecheng.setJieshujieci(jcsj2.getJieci());
			 * kecheng.setShangkejiaoshi(kc[3]); JiaoShi jshi =
			 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
			 * kecheng.setXiaoqu(jshi.getXiaoquid() + "");
			 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
			 * kecheng.setJiaoXueLouMing(
			 * jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid()).
			 * getJiaoXueLouMing());
			 * kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jshi.
			 * getXiaoquid()).getMingcheng()); break; } } } }
			 */

			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(Integer.parseInt(xuexiaoid));
			List<JCSJ> jcsjs = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			map.put("xiaoqus", xiaoQus);
			map.put("riqi", riqi);
			map.put("kecheng", kecheng);
			map.put("zongjieci", jcsjs);
			map.put("weeknum", weeknum);
			map.put("qufen", qufen);
			return JSON.toJSON(map);
		} else {
			return null;
		}
	}

	// 在调停课程中根据校区显示教室
	@RequestMapping(value = "app_XianShiJiaoShiByTiaoTing")
	@ResponseBody
	public List<JiaoShi> app_XianShiJiaoShiByTiaoTing(HttpServletRequest request) {
		String xiaoquid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xueshengid = request.getParameter("xueshengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			List<JiaoShi> jiaoShis = new ArrayList<>();
			if (xiaoquid.equals("")) {
				return jiaoShis;
			}
			jiaoShis = jiaoshiService.getAllByxiaoQuID(Integer.parseInt(xiaoquid));
			return jiaoShis;
		} else {
			return null;
		}
	}

	// 保存调停课程
	@RequestMapping(value = "app_SaveTiaoTingKeCheng")
	@ResponseBody
	public String app_SaveTiaoTingKeCheng(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String tiaoting = request.getParameter("tiaoting");
		String xueshengid = request.getParameter("xueshengid");
		String xingming = request.getParameter("xingming");
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		// jiaoshiid , kaishijieciid , jieshujieciid
		String jiuxinxi = request.getParameter("jiuxinxi");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (kecheng == null) {
				return "dengchu";
			}
			String tianjiarenid = kecheng.getTianjiarenid();
			if (!xueshengid.equals(tianjiarenid)) {
				return "dengchu";
			}
			List<String> cyr = new ArrayList<>();
			List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
//			String xuexiaoid = xuexiaoXuehao.split("_")[0];
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());

			if (kecheng.getLeixing() == 1) {
				List<String> banJiIDs = kechengService.getByAllBanJiIDByID(id);
				if (banJiIDs != null && banJiIDs.size() > 0) {
					for (String string : banJiIDs) {
						List<XueSheng> xss = xueshengService.getAllByBanJiID(Integer.parseInt(string));
						for (XueSheng xueSheng : xss) {
							int i = 0;
							if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
								for (String s : mianXiuIDs) {
									if (s.equals(xueSheng.getXueshengid().toString())) {
										i = 1;
									}
								}
								if (i == 0) {
									cyr.add(xueSheng.getXueshengid().toString());
								}
							}
						}
					}
				}
			}
			if (kecheng.getLeixing() == 2) {
				List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(id);
				for (String string : xuanXiuIDs) {
					int i = 0;
					if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
						for (String s : mianXiuIDs) {
							if (s.equals(string)) {
								i = 1;
							}
						}
						if (i == 0) {
							cyr.add(string);
						}
					}
				}
				cyr.add(xueshengid);
			}
			Map<String, String> map = new HashMap<>();
			map.put("kechengid", id);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String shangkeshijian[] = jiuxinxi.split(",");
			if (tiaoting.equals("0")) {
				// String newriqi = "";
				// String danShuangZhouShuoMing = "";
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

				/**
				 * String danshuangzhoushuoming[] =
				 * kecheng.getDanshuangzhoushuoming().split("zqxj"); switch
				 * (danshuangzhoushuoming[0]) { case "3": // 次 String
				 * tingtiaoriqi[] = danshuangzhoushuoming[1].split("zqjx"); for
				 * (String string : tingtiaoriqi) { if
				 * (tingkeriqi.equals(string.split(",")[0])) { continue; }
				 * newriqi += string + "zqjx"; } danshuangzhoushuoming[1] =
				 * newriqi; danShuangZhouShuoMing += danshuangzhoushuoming[0] +
				 * "zqxj" + danshuangzhoushuoming[1]; break;
				 * 
				 * default: // 单双、连续 都是讲停课日期直接保存 String tingtiaoriqi2[] =
				 * danshuangzhoushuoming[3].split("zqjx");
				 * 
				 * for (String string : tingtiaoriqi2) { newriqi += string +
				 * "zqjx"; } newriqi += tingkeriqi + "zqjx";
				 * danshuangzhoushuoming[3] = newriqi; danShuangZhouShuoMing +=
				 * danshuangzhoushuoming[0] + "zqxj" + danshuangzhoushuoming[1]
				 * + "zqxj" + danshuangzhoushuoming[2] + "zqxj" +
				 * danshuangzhoushuoming[3] + "zqxj" + danshuangzhoushuoming[4];
				 * break; }
				 * 
				 * KeCheng keCheng2 = new KeCheng();
				 * keCheng2.setId(Integer.parseInt(id));
				 * keCheng2.setDanshuangzhoushuoming(danShuangZhouShuoMing); int
				 * i = kechengService.updatedanShuangZhouShuoMingByPrimaryKey(
				 * keCheng2);
				 */
				if (i != 0) {
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					Date date = new Date();
					tiXing.setNeirong(xingming + "将" + tingkeriqi + "的《" + kecheng.getKechengmingcheng() + "》停课一次");
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(kecheng.getId());
					tiXing.setType(1);
					xiaoXiFaSong.setXiaoXiMingCheng("调停课程");
					xiaoXiFaSong.setXiaoXiNeiRong(
							xingming + "将" + tingkeriqi + "的《" + kecheng.getKechengmingcheng() + "》停课一次");
					xiaoXiFaSong.setShuJuId(kecheng.getId());
					xiaoXiFaSong.setShuJuLeiXing(1);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xuexiaoid);
					if (cyr != null && cyr.size() > 0) {
						for (String string : cyr) {
							tiXing.setJieshourenid(Integer.parseInt(string));
							xiaoXiFaSong.setFaSongMuBiao(string);
							tixingService.insert(tiXing);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}

					return "successtingke";
				} else {
					return "failtingke";
				}
			}
			if (tiaoting.equals("1")) {
				String tiaokeriqi = request.getParameter("tiaotingriqi");
				String newriqi = request.getParameter("newriqi");
				String xiaoqu = request.getParameter("xiaoqu");
				String shangkejiaoshi = request.getParameter("shangkejiaoshi");
				String kaishijieci = request.getParameter("kaishijieci");
				String jieshujieci = request.getParameter("jieshujieci");

				map.put("xueXiaoID", xuexiaoid);
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

				/**
				 * String newshangkeriqi = ""; String danShuangZhouShuoMing =
				 * ""; String danshuangzhoushuoming[] =
				 * kecheng.getDanshuangzhoushuoming().split("zqxj"); switch
				 * (danshuangzhoushuoming[0]) { case "3": // 次 String
				 * tingtiaoriqi[] = danshuangzhoushuoming[1].split("zqjx"); for
				 * (String string : tingtiaoriqi) { if
				 * (tiaokeriqi.equals(string.split(",")[0])) { continue; }
				 * newshangkeriqi += string + "zqjx"; } newshangkeriqi +=
				 * newriqi + "," + kaishijieci + "," + jieshujieci + "," +
				 * shangkejiaoshi + "zqjx"; danshuangzhoushuoming[1] =
				 * newshangkeriqi; danShuangZhouShuoMing +=
				 * danshuangzhoushuoming[0] + "zqxj" + danshuangzhoushuoming[1];
				 * break;
				 * 
				 * default: // 单双、连续 都是讲停课日期直接保存 String tingtiaoriqi2[] =
				 * danshuangzhoushuoming[3].split("zqjx"); for (String string :
				 * tingtiaoriqi2) { newshangkeriqi += string + "zqjx"; }
				 * newshangkeriqi += tiaokeriqi + "zqjx";
				 * danshuangzhoushuoming[3] = newshangkeriqi;
				 * 
				 * String newTiaoKe = ""; String tiaoKeRiQi[] =
				 * danshuangzhoushuoming[4].split("zqjx"); for (String string :
				 * tiaoKeRiQi) { newTiaoKe += string + "zqjx"; } newTiaoKe +=
				 * newriqi + "," + kaishijieci + "," + jieshujieci + "," +
				 * shangkejiaoshi + "zqjx"; danshuangzhoushuoming[4] =
				 * newTiaoKe; danShuangZhouShuoMing += danshuangzhoushuoming[0]
				 * + "zqxj" + danshuangzhoushuoming[1] + "zqxj" +
				 * danshuangzhoushuoming[2] + "zqxj" + danshuangzhoushuoming[3]
				 * + "zqxj" + danshuangzhoushuoming[4]; break; }
				 * 
				 * KeCheng keCheng2 = new KeCheng();
				 * keCheng2.setId(Integer.parseInt(id));
				 * keCheng2.setDanshuangzhoushuoming(danShuangZhouShuoMing); int
				 * i = kechengService.updatedanShuangZhouShuoMingByPrimaryKey(
				 * keCheng2);
				 */
				int m = 0;
				if (i != 0) {
					Map<String, String> newmap = new HashMap<>();
					newmap.put("xueXiaoID", xuexiaoid);
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
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					Date date = new Date();
					tiXing.setNeirong(
							xingming + "将" + tiaokeriqi + "的《" + kecheng.getKechengmingcheng() + "》调到了" + newriqi);
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(kecheng.getId());
					tiXing.setType(1);
					xiaoXiFaSong.setXiaoXiMingCheng("调停课程");
					xiaoXiFaSong.setXiaoXiNeiRong(
							xingming + "将" + tiaokeriqi + "的《" + kecheng.getKechengmingcheng() + "》调到了" + newriqi);
					xiaoXiFaSong.setShuJuId(kecheng.getId());
					xiaoXiFaSong.setShuJuLeiXing(1);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xuexiaoid);
					if (cyr != null && cyr.size() > 0) {
						for (String string : cyr) {
							tiXing.setJieshourenid(Integer.parseInt(string));
							xiaoXiFaSong.setFaSongMuBiao(string);
							tixingService.insert(tiXing);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}

					return "successtiaoke";
				} else {
					return "failtiaoke";
				}
			}
			if (tiaoting.equals("2")) {
				String newriqi = request.getParameter("newriqi");
				String xiaoqu = request.getParameter("xiaoqu");
				String shangkejiaoshi = request.getParameter("shangkejiaoshi");
				String kaishijieci = request.getParameter("kaishijieci");
				String jieshujieci = request.getParameter("jieshujieci");

				Map<String, String> newmap = new HashMap<>();
				newmap.put("xueXiaoID", xuexiaoid);
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

				int i = kechengService.insert_shangkeshijian(newmap);
				/**
				 * String newshangkeriqi = ""; String danShuangZhouShuoMing =
				 * ""; String danshuangzhoushuoming[] =
				 * kecheng.getDanshuangzhoushuoming().split("zqxj"); switch
				 * (danshuangzhoushuoming[0]) { case "3": // 次 String
				 * tingtiaoriqi[] = danshuangzhoushuoming[1].split("zqjx"); for
				 * (String string : tingtiaoriqi) { newshangkeriqi += string +
				 * "zqjx"; } newshangkeriqi += newriqi + "," + kaishijieci + ","
				 * + jieshujieci + "," + shangkejiaoshi + "zqjx";
				 * danshuangzhoushuoming[1] = newshangkeriqi;
				 * danShuangZhouShuoMing += danshuangzhoushuoming[0] + "zqxj" +
				 * danshuangzhoushuoming[1]; break;
				 * 
				 * default: // 单双、连续 都是讲停课日期直接保存 String newTiaoKe = ""; String
				 * tiaoKeRiQi[] = danshuangzhoushuoming[4].split("zqjx"); for
				 * (String string : tiaoKeRiQi) { newTiaoKe += string + "zqjx";
				 * } newTiaoKe += newriqi + "," + kaishijieci + "," +
				 * jieshujieci + "," + shangkejiaoshi + "zqjx";
				 * danshuangzhoushuoming[4] = newTiaoKe; danShuangZhouShuoMing
				 * += danshuangzhoushuoming[0] + "zqxj" +
				 * danshuangzhoushuoming[1] + "zqxj" + danshuangzhoushuoming[2]
				 * + "zqxj" + danshuangzhoushuoming[3] + "zqxj" +
				 * danshuangzhoushuoming[4]; break; }
				 * 
				 * KeCheng keCheng2 = new KeCheng();
				 * keCheng2.setId(Integer.parseInt(id));
				 * keCheng2.setDanshuangzhoushuoming(danShuangZhouShuoMing); int
				 * i = kechengService.updatedanShuangZhouShuoMingByPrimaryKey(
				 * keCheng2);
				 */
				if (i != 0) {
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					Date date = new Date();
					tiXing.setNeirong(xingming + "在" + newriqi + "添加了一次《" + kecheng.getKechengmingcheng() + "》");
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					tiXing.setShujuid(kecheng.getId());
					tiXing.setType(1);
					xiaoXiFaSong.setXiaoXiMingCheng("调停课程");
					xiaoXiFaSong.setXiaoXiNeiRong(
							xingming + "在" + newriqi + "添加了一次《" + kecheng.getKechengmingcheng() + "》");
					xiaoXiFaSong.setShuJuId(kecheng.getId());
					xiaoXiFaSong.setShuJuLeiXing(1);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xuexiaoid);
					if (cyr != null && cyr.size() > 0) {
						for (String string : cyr) {
							tiXing.setJieshourenid(Integer.parseInt(string));
							xiaoXiFaSong.setFaSongMuBiao(string);
							tixingService.insert(tiXing);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}

					return "successjiake";
				} else {
					return "failjiake";
				}
			}
		} else {
			return null;
		}
		return "dengchu";
	}

	// 保存修改课程
	@RequestMapping(value = "app_SaveXiuGaiKeCheng")
	@ResponseBody
	public String app_SaveXiuGaiKeCheng(HttpServletRequest request) throws ParseException {
		String id = request.getParameter("id");
		String xueshengid = request.getParameter("xueshengid");
		String xingming = request.getParameter("xingming");
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String yuanxinxi = request.getParameter("yuanxinxi");
		String yuanriqi = request.getParameter("yuanriqi");
		String yuanjiaoshi = request.getParameter("yuanjiaoshi");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		// KeCheng keCheng = new KeCheng();
		if (str.equals(token)) {
			if (!Util.isNumeric(id)) {
				return "dengchu";
			}
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			if (kecheng == null) {
				return "dengchu";
			}
			if (!kecheng.getTianjiarenid().equals(xueshengid)) {
				return "dengchu";
			}
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			String kechengmingcheng = request.getParameter("kechengmingcheng");
			String renkejiaoshi = request.getParameter("renkejiaoshi");
			String xiaoqu = request.getParameter("xiaoqu");
			String shangkejiaoshi = request.getParameter("shangkejiaoshi");
			String kaishizhou = request.getParameter("kaishizhou");
			String jieshuzhou = request.getParameter("jieshuzhou");
			String zhouci = request.getParameter("zhouci");
			String ds = request.getParameter("danshuangzhou");
			String shijian = request.getParameter("shijian");
			String shijians = request.getParameter("shijians");
			String qufen = request.getParameter("qufen");
			String danshuangzhoushuoming = "";
			String kaishijieci = request.getParameter("kaishijieci");
			String jieshujieci = request.getParameter("jieshujieci");
			// 取得当前学年、学期
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			String xuexiao_xuehao = xuexiaoXuehao;
//			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_"));
			String xueXiaoID = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			Map<String, String> map = new HashMap<>();
			map.put("riqi", riqi.format(date));
			map.put("xueXiaoID", xueXiaoID);
			XueQi xueQi2 = xueqiService.selectByID(kecheng.getXueqiid());
			int xueqi = xueQi2.getXueqi();
			List<String> cyr = new ArrayList<>();
			List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
			if (kecheng.getLeixing() == 1) {
				List<String> banJis = kechengService.getByAllBanJiIDByID(id);
				for (String string : banJis) {
					List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string));
					for (XueSheng xueSheng : xueshengs) {
						int i = 0;
						for (String s : mianXiuIDs) {
							if (s.equals(xueSheng.getXueshengid().toString())) {
								i = 1;
								break;
							}
						}
						if (i == 0) {
							cyr.add(xueSheng.getXueshengid().toString());
						}
					}
				}
			}
			if (kecheng.getLeixing() == 2) {
				List<String> xu = kechengService.getAllXuanXiuIDByID(id);
				for (String string : xu) {
					int i = 0;
					for (String s : mianXiuIDs) {
						if (s.equals(string)) {
							i = 1;
							break;
						}
					}
					if (i == 0) {
						cyr.add(string);
					}
				}
				cyr.add(xueshengid);
			}
			int j = 0;
			// var a = id0 + "/" + zhou1 + "/" + zhouci2 + "/" + xuenian3 +
			// "/" + xueqi4 + "/" + danshuang5 + "/" + kaishijieci6 + "/" +
			// jieshujieci7 + "/" + kaishizhou8 + "/" + jieshuzhou9;
			List<Map<String, Object>> shiJianMap = kechengService.getShangKeShiJianByID(id);
			String ids[] = yuanxinxi.split("/");
			map.put("kechengid", id);
			if (ids[9] == null || Integer.parseInt(ids[9]) == 0) {
				for (Map<String, Object> shijianmap : shiJianMap) {
					if (shijianmap.containsKey("shiJianLeiXing")
							&& shijianmap.get("shiJianLeiXing").toString().equals("4")) {
						if(qufen.equals("2")){
							if (ids[1].equals(shijianmap.get("kaiShiZhou").toString())
									&& ids[2].equals(shijianmap.get("zhouCi").toString())
									&& yuanjiaoshi.equals(shijianmap.get("jiaoShiID").toString())) {
								String kaishi = jcsjService
										.selectByPrimaryKey(Integer.parseInt(shijianmap.get("kaiShiJieCi").toString()))
										.getJieci().toString();
								String jieshu = jcsjService
										.selectByPrimaryKey(Integer.parseInt(shijianmap.get("jieShuJieCi").toString()))
										.getJieci().toString();
								if (ids[6].equals(kaishi) && ids[7].equals(jieshu)) {
									int zhou = 0;
									int zhoucis = 0;
									if(shijian != null && !"".equals(shijian)){
										Calendar cal1 = Calendar.getInstance();
										cal1.setTime(riqi.parse(xueQi2.getKaishiriqi()));
										Calendar cal2 = Calendar.getInstance();
										cal2.setTime(riqi.parse(shijian));
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
										zhou = timeDistance / 7 + 1;
										Calendar c = Calendar.getInstance();
										c.setTime(riqi.parse(shijian));
										zhoucis = 0;
										if (c.get(Calendar.DAY_OF_WEEK) == 1) {
											zhoucis = 7;
										} else {
											zhoucis = c.get(Calendar.DAY_OF_WEEK) - 1;
										}
									}else{
										zhou = Integer.parseInt(ids[1]);
										zhoucis = Integer.parseInt(ids[2]);
									}
									map.put("yuanjiaoshiid", yuanjiaoshi);
									map.put("yuanzhouci", ids[2]);
									map.put("yuankaishizhou", ids[1]);
									map.put("yuankaishijieci", shijianmap.get("kaiShiJieCi").toString());
									map.put("yuanjieshujieci", shijianmap.get("jieShuJieCi").toString());
									map.put("yuanleixing", "4");
									map.put("jiaoshiid", shangkejiaoshi);
									map.put("kaishijieci", kaishijieci);
									map.put("jieshujieci", jieshujieci);
									map.put("leixing", "4");
									map.put("kaishizhou", zhou + "");
									map.put("zhouci", zhoucis + "");
	
									j = kechengService.update_shangkeshijian(map);
									break;
								}
							}
						}else{
							String zs[] = ids[8].split(",");
							for (String string : zs) {
								if (string.equals(shijianmap.get("kaiShiZhou").toString())
										&& ids[2].equals(shijianmap.get("zhouCi").toString())
										&& yuanjiaoshi.equals(shijianmap.get("jiaoShiID").toString())) {
									String kaishi = jcsjService
											.selectByPrimaryKey(Integer.parseInt(shijianmap.get("kaiShiJieCi").toString()))
											.getJieci().toString();
									String jieshu = jcsjService
											.selectByPrimaryKey(Integer.parseInt(shijianmap.get("jieShuJieCi").toString()))
											.getJieci().toString();
									if (ids[6].equals(kaishi) && ids[7].equals(jieshu)) {
										String sj[] = shijians.split(";");
										int zhou=0;
										int zhoucis=0;
										for (String string2 : sj) {
											String s[] = string2.split(",");
											Calendar cal1 = Calendar.getInstance();
											cal1.setTime(riqi.parse(xueQi2.getKaishiriqi()));
											Calendar cal2 = Calendar.getInstance();
											cal2.setTime(riqi.parse(s[0]));
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
											int zhous = timeDistance / 7 + 1;
											Calendar c = Calendar.getInstance();
											c.setTime(riqi.parse(s[0]));
											int zhouciss = 0;
											if (c.get(Calendar.DAY_OF_WEEK) == 1) {
												zhouciss = 7;
											} else {
												zhouciss = c.get(Calendar.DAY_OF_WEEK) - 1;
											}
											if(String.valueOf(zhous).equals(string) && String.valueOf(zhouciss).equals(ids[2])){
												if(s.length!=1){
//													if(s[1]!=null && !"".equals(s[1])){
													Calendar cal11 = Calendar.getInstance();
													cal11.setTime(riqi.parse(xueQi2.getKaishiriqi()));
													Calendar cal12 = Calendar.getInstance();
													cal12.setTime(riqi.parse(s[1]));
													int day11 = cal11.get(Calendar.DAY_OF_YEAR);
													int day12 = cal12.get(Calendar.DAY_OF_YEAR);
													int year11 = cal11.get(Calendar.YEAR);
													int year12 = cal12.get(Calendar.YEAR);
													int timeDistance1 = 0;
													if (year11 != year12) { // 不同年
														for (int i = year11; i < year12; i++) {
															if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
																timeDistance1 += 366;
															} else { // 不是闰年
																timeDistance1 += 365;
															}
															timeDistance1 += day12 - day11;
														}
													} else {// 同年
														timeDistance1 = day12 - day11;
													}
													zhou = timeDistance1 / 7 + 1;
													Calendar c2 = Calendar.getInstance();
													c2.setTime(riqi.parse(s[1]));
													if (c2.get(Calendar.DAY_OF_WEEK) == 1) {
														zhoucis = 7;
													} else {
														zhoucis = c2.get(Calendar.DAY_OF_WEEK) - 1;
													}
												}else{
													zhou = zhous;
													zhoucis = zhouciss;
												}
												map.put("yuanjiaoshiid", yuanjiaoshi);
												map.put("yuanzhouci", ids[2]);
												map.put("yuankaishizhou", string);
												map.put("yuankaishijieci", shijianmap.get("kaiShiJieCi").toString());
												map.put("yuanjieshujieci", shijianmap.get("jieShuJieCi").toString());
												map.put("yuanleixing", "4");
												map.put("jiaoshiid", shangkejiaoshi);
												map.put("kaishijieci", kaishijieci);
												map.put("jieshujieci", jieshujieci);
												map.put("leixing", "4");
												map.put("kaishizhou", zhou + "");
												map.put("zhouci", zhoucis + "");
												
												j = kechengService.update_shangkeshijian(map);
												break;
											}
										}
//										Calendar cal1 = Calendar.getInstance();
//										cal1.setTime(riqi.parse(xueQi2.getKaishiriqi()));
//										Calendar cal2 = Calendar.getInstance();
//										cal2.setTime(riqi.parse(shijian));
//										int day1 = cal1.get(Calendar.DAY_OF_YEAR);
//										int day2 = cal2.get(Calendar.DAY_OF_YEAR);
//										int year1 = cal1.get(Calendar.YEAR);
//										int year2 = cal2.get(Calendar.YEAR);
//										int timeDistance = 0;
//										if (year1 != year2) { // 不同年
//											for (int i = year1; i < year2; i++) {
//												if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {// 闰年
//													timeDistance += 366;
//												} else { // 不是闰年
//													timeDistance += 365;
//												}
//												timeDistance += day2 - day1;
//											}
//										} else {// 同年
//											timeDistance = day2 - day1;
//										}
//										int zhou = timeDistance / 7 + 1;
//										Calendar c = Calendar.getInstance();
//										c.setTime(riqi.parse(shijian));
//										int zhoucis = 0;
//										if (c.get(Calendar.DAY_OF_WEEK) == 1) {
//											zhoucis = 7;
//										} else {
//											zhoucis = c.get(Calendar.DAY_OF_WEEK) - 1;
//										}
										
									}
								}
							}
						}
						
						
					}
					if (shijianmap.containsKey("shiJianLeiXing")
							&& shijianmap.get("shiJianLeiXing").toString().equals("6")) {
						if (ids[1].equals(shijianmap.get("kaiShiZhou").toString())
								&& ids[2].equals(shijianmap.get("zhouCi").toString())
								&& yuanjiaoshi.equals(shijianmap.get("jiaoShiID").toString())) {
							String kaishi = jcsjService
									.selectByPrimaryKey(Integer.parseInt(shijianmap.get("kaiShiJieCi").toString()))
									.getJieci().toString();
							String jieshu = jcsjService
									.selectByPrimaryKey(Integer.parseInt(shijianmap.get("jieShuJieCi").toString()))
									.getJieci().toString();
							if (ids[6].equals(kaishi) && ids[7].equals(jieshu)) {
								Calendar cal1 = Calendar.getInstance();
								cal1.setTime(riqi.parse(xueQi2.getKaishiriqi()));
								Calendar cal2 = Calendar.getInstance();
								cal2.setTime(riqi.parse(shijian));
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
								c.setTime(riqi.parse(shijian));
								int zhoucis = 0;
								if (c.get(Calendar.DAY_OF_WEEK) == 1) {
									zhoucis = 7;
								} else {
									zhoucis = c.get(Calendar.DAY_OF_WEEK) - 1;
								}
								map.put("yuanjiaoshiid", yuanjiaoshi);
								map.put("yuanzhouci", ids[2]);
								map.put("yuankaishizhou", ids[1]);
								map.put("yuankaishijieci", shijianmap.get("kaiShiJieCi").toString());
								map.put("yuanjieshujieci", shijianmap.get("jieShuJieCi").toString());
								map.put("yuanleixing", "6");
								map.put("jiaoshiid", shangkejiaoshi);
								map.put("kaishijieci", kaishijieci);
								map.put("jieshujieci", jieshujieci);
								map.put("leixing", "6");
								map.put("kaishizhou", zhou + "");
								map.put("zhouci", zhoucis + "");

								j = kechengService.update_shangkeshijian(map);
								break;
							}
						}
					}
				}
			} else {
				for (Map<String, Object> map3 : shiJianMap) {
					if (ids[5].equals("1")) { // 2
						if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("2")) {
							if (yuanjiaoshi.equals(map3.get("jiaoShiID").toString())
									&& map3.get("kaiShiZhou").toString().equals(ids[8])
									&& map3.get("jieShuZhou").toString().equals(ids[9])) {
								String kaishi = jcsjService
										.selectByPrimaryKey(Integer.parseInt(map3.get("kaiShiJieCi").toString()))
										.getJieci().toString();
								String jieshu = jcsjService
										.selectByPrimaryKey(Integer.parseInt(map3.get("jieShuJieCi").toString()))
										.getJieci().toString();
								if (map3.get("zhouCi").toString().equals(ids[2]) && ids[6].equals(kaishi)
										&& ids[7].equals(jieshu)) {
									map.put("yuanjiaoshiid", yuanjiaoshi);
									map.put("yuankaishijieci", map3.get("kaiShiJieCi").toString());
									map.put("yuanjieshujieci", map3.get("jieShuJieCi").toString());
									map.put("yuanleixing", "2");
									map.put("yuankaishizhou", ids[8]);
									map.put("yuanjieshuzhou", ids[9]);
									map.put("yuanzhouci", ids[2]);
									map.put("jiaoshiid", shangkejiaoshi);
									map.put("kaishijieci", kaishijieci);
									map.put("jieshujieci", jieshujieci);
									if (ds.equals("0")) {
										map.put("leixing", "1");
									}
									if (ds.equals("1")) {
										map.put("leixing", "2");
									}
									if (ds.equals("2")) {
										map.put("leixing", "3");
									}
									map.put("kaishizhou", kaishizhou);
									map.put("jieshuzhou", jieshuzhou);
									map.put("zhouci", zhouci);
									j = kechengService.update_shangkeshijian(map);
									break;
								}
							}
						}
					} else if (ids[5].equals("2")) { // 3
						if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("3")) {
							if (yuanjiaoshi.equals(map3.get("jiaoShiID").toString())
									&& map3.get("kaiShiZhou").toString().equals(ids[8])
									&& map3.get("jieShuZhou").toString().equals(ids[9])) {
								String kaishi = jcsjService
										.selectByPrimaryKey(Integer.parseInt(map3.get("kaiShiJieCi").toString()))
										.getJieci().toString();
								String jieshu = jcsjService
										.selectByPrimaryKey(Integer.parseInt(map3.get("jieShuJieCi").toString()))
										.getJieci().toString();
								if (map3.get("zhouCi").toString().equals(ids[2]) && ids[6].equals(kaishi)
										&& ids[7].equals(jieshu)) {
									map.put("yuanjiaoshiid", yuanjiaoshi);
									map.put("yuankaishijieci", map3.get("kaiShiJieCi").toString());
									map.put("yuanjieshujieci", map3.get("jieShuJieCi").toString());
									map.put("yuanleixing", "3");
									map.put("yuankaishizhou", ids[8]);
									map.put("yuanjieshuzhou", ids[9]);
									map.put("yuanzhouci", ids[2]);
									map.put("jiaoshiid", shangkejiaoshi);
									map.put("kaishijieci", kaishijieci);
									map.put("jieshujieci", jieshujieci);
									if (ds.equals("0")) {
										map.put("leixing", "1");
									}
									if (ds.equals("1")) {
										map.put("leixing", "2");
									}
									if (ds.equals("2")) {
										map.put("leixing", "3");
									}
									map.put("kaishizhou", kaishizhou);
									map.put("jieshuzhou", jieshuzhou);
									map.put("zhouci", zhouci);
									j = kechengService.update_shangkeshijian(map);
									break;
								}
							}
						}
					} else { // 1
						if (map3.containsKey("shiJianLeiXing") && map3.get("shiJianLeiXing").toString().equals("1")) {
							if (yuanjiaoshi.equals(map3.get("jiaoShiID").toString())
									&& map3.get("kaiShiZhou").toString().equals(ids[8])
									&& map3.get("jieShuZhou").toString().equals(ids[9])) {
								String kaishi = jcsjService
										.selectByPrimaryKey(Integer.parseInt(map3.get("kaiShiJieCi").toString()))
										.getJieci().toString();
								String jieshu = jcsjService
										.selectByPrimaryKey(Integer.parseInt(map3.get("jieShuJieCi").toString()))
										.getJieci().toString();
								if (map3.get("zhouCi").toString().equals(ids[2]) && ids[6].equals(kaishi)
										&& ids[7].equals(jieshu)) {
									map.put("yuanjiaoshiid", yuanjiaoshi);
									map.put("yuankaishijieci", map3.get("kaiShiJieCi").toString());
									map.put("yuanjieshujieci", map3.get("jieShuJieCi").toString());
									map.put("yuanleixing", "1");
									map.put("yuankaishizhou", ids[8]);
									map.put("yuanjieshuzhou", ids[9]);
									map.put("yuanzhouci", ids[2]);
									map.put("jiaoshiid", shangkejiaoshi);
									map.put("kaishijieci", kaishijieci);
									map.put("jieshujieci", jieshujieci);
									if (ds.equals("0")) {
										map.put("leixing", "1");
									}
									if (ds.equals("1")) {
										map.put("leixing", "2");
									}
									if (ds.equals("2")) {
										map.put("leixing", "3");
									}
									map.put("kaishizhou", kaishizhou);
									map.put("jieshuzhou", jieshuzhou);
									map.put("zhouci", zhouci);
									j = kechengService.update_shangkeshijian(map);
									break;
								}
							}
						}
					}
				}
			}

			/**
			 * String xuenian = xueQi2.getXuenian(); String
			 * danShuangZhouShuoMing[] =
			 * kecheng.getDanshuangzhoushuoming().split("zqxj"); switch
			 * (danShuangZhouShuoMing[0]) { case "1": danShuangZhouShuoMing[1] =
			 * kaishizhou + "," + jieshuzhou; String shuoming1[] =
			 * yuanxinxi.split("/"); danshuangzhoushuoming +=
			 * danShuangZhouShuoMing[0] + "zqxj" + danShuangZhouShuoMing[1] +
			 * "zqxj"; String xinxi[] = danShuangZhouShuoMing[2].split("zxqj");
			 * for (String string : xinxi) { String str1[] = string.split(",");
			 * String kaishi =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str1[1])).
			 * getJieci().toString(); String jieshu =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str1[2])).
			 * getJieci().toString(); if (str1[0].equals(shuoming1[2]) &&
			 * kaishi.equals(shuoming1[6]) && jieshu.equals(shuoming1[7]) &&
			 * str1[3].equals(yuanjiaoshi)) { danshuangzhoushuoming += zhouci +
			 * "," + kaishijieci + "," + jieshujieci + "," + shangkejiaoshi +
			 * "zxqj"; } else { danshuangzhoushuoming += string + "zxqj"; } }
			 * danshuangzhoushuoming += "zqxj" + danShuangZhouShuoMing[3] +
			 * "zqxj" + danShuangZhouShuoMing[4];
			 * 
			 * break;
			 * 
			 * case "2": danShuangZhouShuoMing[1] = kaishizhou + "," +
			 * jieshuzhou; String shuoming[] = yuanxinxi.split("/"); // String
			 * ds = request.getParameter("ds"); danshuangzhoushuoming +=
			 * danShuangZhouShuoMing[0] + "zqxj" + danShuangZhouShuoMing[1] +
			 * "zqxj"; String xinxi2[] = danShuangZhouShuoMing[2].split("zqjx");
			 * // if ("0".equals(shuoming[4])) { // 每周 String dan = ""; String
			 * shuang = ""; // 单周 for (String string : xinxi2[0].split("zxqj"))
			 * { if (string != null && !("".equals(string))) { String str2[] =
			 * string.split(","); String kaishi =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[1])).
			 * getJieci().toString(); String jieshu =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[2])).
			 * getJieci().toString(); if (str2[0].equals(shuoming[2]) &&
			 * kaishi.equals(shuoming[6]) && jieshu.equals(shuoming[7]) &&
			 * str2[3].equals(yuanjiaoshi)) { continue; } else { dan += string +
			 * "zxqj"; } } }
			 * 
			 * // 双周 for (String string : xinxi2[1].split("zxqj")) { if (string
			 * != null && !("".equals(string))) { String str2[] =
			 * string.split(","); String kaishi =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[1])).
			 * getJieci().toString(); String jieshu =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str2[2])).
			 * getJieci().toString(); if (str2[0].equals(shuoming[2]) &&
			 * kaishi.equals(shuoming[6]) && jieshu.equals(shuoming[7]) &&
			 * str2[3].equals(yuanjiaoshi)) { continue; } else { shuang +=
			 * string + "zxqj"; } } } if ("0".equals(danshuangzhou)) { dan +=
			 * zhouci + "," + kaishijieci + "," + jieshujieci + "," +
			 * shangkejiaoshi + "zxqj"; shuang += zhouci + "," + kaishijieci +
			 * "," + jieshujieci + "," + shangkejiaoshi + "zxqj"; } if
			 * ("1".equals(danshuangzhou)) { dan += zhouci + "," + kaishijieci +
			 * "," + jieshujieci + "," + shangkejiaoshi + "zxqj"; } if
			 * ("2".equals(danshuangzhou)) { shuang += zhouci + "," +
			 * kaishijieci + "," + jieshujieci + "," + shangkejiaoshi + "zxqj";
			 * } if ("".equals(dan)) { dan += "zxqj"; } if ("".equals(shuang)) {
			 * shuang += "zxqj"; }
			 * 
			 * danshuangzhoushuoming += dan + "zqjx" + shuang + "zqxj" +
			 * danShuangZhouShuoMing[3] + "zqxj" + danShuangZhouShuoMing[4];
			 * 
			 * break;
			 * 
			 * case "3": danshuangzhoushuoming += danShuangZhouShuoMing[0] +
			 * "zqxj"; String xinxi3[] = danShuangZhouShuoMing[1].split("zqjx");
			 * String shuomings[] = yuanxinxi.split("/"); for (String string :
			 * xinxi3) { String str3[] = string.split(","); String kaishi =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str3[1])).
			 * getJieci().toString(); String jieshu =
			 * jcsjService.selectByPrimaryKey(Integer.parseInt(str3[2])).
			 * getJieci().toString(); int i = 0; if (yuanriqi.equals(str3[0]) &&
			 * kaishi.equals(shuomings[6]) && jieshu.equals(shuomings[7]) &&
			 * str3[3].equals(yuanjiaoshi)) { i = 1; continue; } if (i == 0) {
			 * danshuangzhoushuoming += string + "zqjx"; } }
			 * danshuangzhoushuoming += shijian + "," + kaishijieci + "," +
			 * jieshujieci + "," + shangkejiaoshi + "zqjx";
			 * 
			 * break; } if (kecheng.getBanjiids() != null &&
			 * !("".equals(kecheng.getBanjiids()))) { for (String banjiid :
			 * kecheng.getBanjiids().split(",")) { for (XueSheng xs :
			 * xueshengService.getAllByBanJiID(Integer.parseInt(banjiid))) {
			 * 
			 * if (null != kecheng.getMianxiuids() &&
			 * !("".equals(kecheng.getMianxiuids()))) { String canyuren[] =
			 * kecheng.getMianxiuids().split(","); int i = 0; for (String string
			 * : canyuren) { if (cyr.indexOf(xs.getXuehao()) < 0 &&
			 * !(string.equals(xs.getXuehao()))) { i = 1; } } if (i == 1) {
			 * cyr.add(xs.getXuehao()); } } else { if
			 * (cyr.indexOf(xs.getXuehao()) < 0) { cyr.add(xs.getXuehao()); } }
			 * } } } if (null != kecheng.getXuanxiuids() &&
			 * !("".equals(kecheng.getXuanxiuids()))) { String canyuren[] =
			 * kecheng.getXuanxiuids().split(","); for (String string :
			 * canyuren) { if (null != kecheng.getMianxiuids() &&
			 * !("".equals(kecheng.getMianxiuids()))) { String mianxiu[] =
			 * kecheng.getMianxiuids().split(","); int i = 0; for (String
			 * stringss : mianxiu) { if (cyr.indexOf(string) < 0 &&
			 * !(string.equals(stringss))) { i = 1; } } if (i == 1) {
			 * cyr.add(string); } } else { if (cyr.indexOf(string) < 0) {
			 * cyr.add(string); } } } }
			 * 
			 * keCheng.setId(Integer.parseInt(id));
			 * keCheng.setKechengmingcheng(kechengmingcheng);
			 * keCheng.setRenkejiaoshi(renkejiaoshi);
			 * keCheng.setTianjiarenid(xueshengid);
			 * keCheng.setDanshuangzhoushuoming(danshuangzhoushuoming);
			 * keCheng.setXuenian(xuenian); keCheng.setXueqi(xueqi); int j =
			 * kechengService.updateByDanShuangZhou(keCheng);
			 */
			if (j != 0) {
				// 发送消息提醒
				TiXing tiXing = new TiXing();
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				Date date2 = new Date();
				tiXing.setNeirong(xingming + "修改了课程《" + kecheng.getKechengmingcheng() + "》");
				tiXing.setShijian(date2);
				tiXing.setZhuangtai(0);
				tiXing.setType(1);
				tiXing.setShujuid(kecheng.getId());
				xiaoXiFaSong.setXiaoXiMingCheng("修改课程");
				xiaoXiFaSong.setXiaoXiNeiRong(xingming + "修改了课程《" + kecheng.getKechengmingcheng() + "》");
				xiaoXiFaSong.setShuJuId(kecheng.getId());
				xiaoXiFaSong.setShuJuLeiXing(1);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(xueXiaoID);
				if (cyr != null && cyr.size() > 0) {
					for (String string : cyr) {
						tiXing.setJieshourenid(Integer.parseInt(string));
						xiaoXiFaSong.setFaSongMuBiao(string);
						tixingService.insert(tiXing);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
				}
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	// 返回校区数据
	@RequestMapping(value = "app_BackXiaoQuInfo")
	@ResponseBody
	public Object app_BackXiaoQuInfo(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		Map<String, Object> mp = new HashMap<>();
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		mp.put("xiaoqus", "");
		if (str.equals(token)) {
			String xuexiao_xuehao = xuexiaoXuehao;
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
//			String xuexiaoid = xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_"));
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			// 校区列表
			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(Integer.parseInt(xuexiaoid));
			mp.put("xiaoqus", xiaoQus);
			return JSON.toJSON(mp);
		} else {
			return null;
		}
	}

	// 显示下拉课程列表
	@RequestMapping(value = "app_BackJieCiInfo")
	@ResponseBody
	public Object app_BackJieCiInfo(HttpServletRequest request) throws Exception {
		String xueshengid = request.getParameter("xueshengid");
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		Map<String, Object> mp = new HashMap<>();
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xuenian = request.getParameter("xuenain");
		String xueqi = request.getParameter("xueqi");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		mp.put("jcsj", "");
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
			String xuexiao_xuehao = xuexiaoXuehao;
//			String xuexiaoid = xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_"));
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			Map<String, Integer> map = new HashMap<>();
			map.put("zhuangtai", 1);
			map.put("xuexiaoid", Integer.parseInt(xuexiaoid));
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(map);
			List<JCSJ> jcsj = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());

			XueQi xueQi = new XueQi();
			Map<String, String> m = new HashMap<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (xuenian == null || xuenian.equals("") || xueqi == null || "".equals(xueqi)) {
				m.put("xueXiaoID", xuexiaoid);
				m.put("riqi", sdf.format(new Date()));
				xueQi = xueqiService.getByxueXiaoIDandriQi(m);
			} else {
				m.put("xuexiaoid", xuexiaoid);
				m.put("xueqi", xueqi);
				m.put("nianfen", xuenian.split("~")[0]);
				xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(m);
			}
			if (xueQi == null) {
				List<XueQi> xueQis = xueqiService.getNewerXueQiByXueQi(m);
				if (xueQis != null && xueQis.size() > 0) {
					xueQi = xueQis.get(0);
				}
			}
			int weeknum = Util.WeeksBetweenDays(sdf.parse(xueQi.getKaishiriqi()), sdf.parse(xueQi.getJieshuriqi()));

			List<XiaoQu> xiaoQus = xiaoquService.getAllByxueXiaoID(Integer.parseInt(xuexiaoid));
			mp.put("xiaoqus", xiaoQus);
			mp.put("zhounum", weeknum);
			mp.put("zongjieci", jcsj);
			return JSON.toJSON(mp);
		} else {
			return null;
		}
	}

	// 通过校区、教室和周次来查询课程
	@RequestMapping(value = "app_ChaXunKeChengByTiaoJian")
	@ResponseBody
	public List<KeCheng> app_ChaXunKeChengByTiaoJian(HttpServletRequest request) {
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xiaoqu = request.getParameter("xiaoqu");
		String jiaoshi = request.getParameter("jiaoshi");
		String zhouci = request.getParameter("zhouci");
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		Map<String, String> map = new HashMap<>();
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
//			String xuexiaoid = xs.getXuexiaoXuehao().split("_")[0];
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			map.put("xuexiaoid", xuexiaoid);
			map.put("xueqi", xueqi);
			map.put("nianfen", xuenian.split("~")[0]);
			XueQi xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);

			// map.put("xiaoqu", xiaoqu);
			map.put("shangkejiaoshi", jiaoshi);
			map.put("zhouci", zhouci);
			// map.put("xuenian", xuenian);
			// map.put("xueqi", xueqi);
			map.put("xueqiid", xueQi.getXueqiid().toString());
			List<Map<String, Object>> keChengMaps = kechengService.getByJiaoShiIDAndZhouCiAndXueQiID(map);
			// List<KeCheng> keChengs =
			// kechengService.getAllByxiaoQuandshangKeJiaoShiandzhouCi(map);
			List<KeCheng> keChengs = new ArrayList<>();
			if (keChengMaps != null && keChengMaps.size() > 0) {
				for (Map<String, Object> keChengMap : keChengMaps) {
					KeCheng keCheng = new KeCheng();
					if (xs.getIsbanzhang()!=null && xs.getIsbanzhang()) {
						keCheng.setLeixing(Integer.parseInt(keChengMap.get("leiXing").toString()));
						keCheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
						keCheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
						keCheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
						JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(jiaoshi));
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
						if (keChengMap.containsKey("shiJianLeiXing")
								&& keChengMap.get("shiJianLeiXing").toString().equals("1")) {
							keCheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
							keCheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
							keCheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
							keChengs.add(keCheng);
						}
						if (keChengMap.containsKey("shiJianLeiXing")
								&& keChengMap.get("shiJianLeiXing").toString().equals("2")) {
							keCheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
							keCheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
							keCheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
							keCheng.setDanshuangzhou(1);
							keChengs.add(keCheng);
						}
						if (keChengMap.containsKey("shiJianLeiXing")
								&& keChengMap.get("shiJianLeiXing").toString().equals("3")) {
							keCheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
							keCheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
							keCheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
							keCheng.setDanshuangzhou(2);
							keChengs.add(keCheng);
						}
						if (keChengMap.containsKey("shiJianLeiXing")
								&& keChengMap.get("shiJianLeiXing").toString().equals("4")) {
							keCheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
							keCheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
							keCheng.setDanshuangzhou(3);
							keChengs.add(keCheng);
						}
						
					} else {
						if (keChengMap.get("leiXing").toString().equals("2")) {
							keCheng.setLeixing(Integer.parseInt(keChengMap.get("leiXing").toString()));
							keCheng.setId(Integer.parseInt(keChengMap.get("ID").toString()));
							keCheng.setKechengmingcheng(keChengMap.get("keChengMingCheng").toString());
							keCheng.setRenkejiaoshi(keChengMap.get("renKeJiaoShi").toString());
							JiaoShi js = jiaoshiService.selectByPrimaryKey(Integer.parseInt(jiaoshi));
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
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("1")) {
								keCheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
								keCheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
								keCheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
								keChengs.add(keCheng);
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("2")) {
								keCheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
								keCheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
								keCheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
								keCheng.setDanshuangzhou(1);
								keChengs.add(keCheng);
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("3")) {
								keCheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
								keCheng.setJieshuzhou(Integer.parseInt(keChengMap.get("jieShuZhou").toString()));
								keCheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
								keCheng.setDanshuangzhou(2);
								keChengs.add(keCheng);
							}
							if (keChengMap.containsKey("shiJianLeiXing")
									&& keChengMap.get("shiJianLeiXing").toString().equals("4")) {
								keCheng.setKaishizhou(Integer.parseInt(keChengMap.get("kaiShiZhou").toString()));
								keCheng.setZhouci(Integer.parseInt(keChengMap.get("zhouCi").toString()));
								keCheng.setDanshuangzhou(3);
								keChengs.add(keCheng);
							}
							
						}
					}
				}
			}
			return keChengs;
		} else {
			return null;
		}
	}

	// 保存自主添加课程
	@RequestMapping(value = "app_SaveZiZhuJiaKe_ZiYou")
	@ResponseBody
	public String app_SaveZiZhuTianJiaKeCheng(HttpServletRequest request) throws ParseException {
		String studentid = request.getParameter("xueshengid");
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		String banjiid = request.getParameter("banjiid");
		String xingming = request.getParameter("xingming");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = studentid + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			String kechengmingcheng = request.getParameter("kechengmingcheng");
			String renkejiaoshi = request.getParameter("renkejiaoshi");
			String leixing = request.getParameter("leixing");
			KeCheng keCheng = new KeCheng();
			// 参与人
			String ziji_banji = request.getParameter("ziji_banji");
			List<XueSheng> xueShengs = null;
			if (Tools.notEmpty(banjiid)) {
				xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(banjiid));
			}
			if (ziji_banji.equals("0")) {
				keCheng.setLeixing(2);
			} else {
				keCheng.setLeixing(1);
			}
			// 取得当前学年、学期
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(studentid));
			String xuexiao_xuehao = xuexiaoXuehao;
//			String xueXiaoID = xuexiao_xuehao.substring(0, xuexiao_xuehao.indexOf("_"));
			String xueXiaoID = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			// 通过学校id查询学校当前使用的节次方案
			Map<String, Integer> mapp = new HashMap<>();
			mapp.put("xuexiaoid", Integer.parseInt(xueXiaoID));
			mapp.put("zhuangtai", 1);
			JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(mapp);
			List<JCSJ> jcsj = jcsjService.getAllByjieCiFangAnID(jieCiFangAn.getId());
			Map<String, String> map = new HashMap<>();
			String xuenian = request.getParameter("xuenian");
			String xueqi = request.getParameter("xueqi");
			XueQi xueQi2 = new XueQi();
			if (xuenian == null || "".equals(xuenian) || xueqi == null || "".equals(xueqi)) {
				map.put("riqi", sdf.format(date));
				map.put("xueXiaoID", xueXiaoID);
				xueQi2 = xueqiService.getByxueXiaoIDandriQi(map);
			} else {
				map.put("xuexiaoid", xueXiaoID);
				map.put("xueqi", xueqi);
				map.put("nianfen", xuenian.split("~")[0]);
				xueQi2 = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
			}

			if (xueQi2 == null) {
				return "yichaochu";
			}
			if (leixing.equals("1")) {
				String shangkeci[] = request.getParameter("shangkeci").split(";");
				for (String string : shangkeci) {
					String strs[] = string.split(",");
					if (strs[2].compareTo(xueQi2.getKaishiriqi()) < 0
							|| strs[2].compareTo(xueQi2.getJieshuriqi()) > 0) {
						return "yichaochu";
					}
				}
			}

			keCheng.setKechengmingcheng(kechengmingcheng);
			keCheng.setRenkejiaoshi(renkejiaoshi);
			keCheng.setTianjiarenid(studentid);
			keCheng.setXueqiid(xueQi2.getXueqiid());
			int j = kechengService.insert_danshaungzhou(keCheng);
			if (j != 0) {
				map.put("kechengid", keCheng.getId().toString());
				String shangkejiaoshi1 = request.getParameter("shangkejiaoshi");
				String kaishizhou = request.getParameter("kaishizhou");
				String jieshuzhou = request.getParameter("jieshuzhou");
				String zhouci1 = request.getParameter("zhouci1");
				String danshuangzhou1 = request.getParameter("danshuangzhou1");
				String kaishijieci1 = request.getParameter("kaishijieci1");
				String jieshujieci1 = request.getParameter("jieshujieci1");

				if (leixing.equals("0")) {
					// da += i + "," + shangkejiaoshi + "," + kaishizhou +
					// ","+jieshuzhou+"," + kaishijieci + "," + jieshujieci +
					// "," + zhouci+ "," + danshuangzhou+";";
					String shangkezheng[] = request.getParameter("shangkezheng").split(";");
					for (String string : shangkezheng) {
						String strs[] = string.split(",");
						if (strs[0].equals("1")) {
							map.put("kaishizhou", kaishizhou);
							map.put("jieshuzhou", jieshuzhou);
							map.put("jiaoshiid", shangkejiaoshi1);
							map.put("zhouci", zhouci1);
							map.put("kaishijieci", kaishijieci1);
							map.put("jieshujieci", jieshujieci1);
							if (danshuangzhou1.equals("0")) {
								map.put("leixing", "1");
							}
							if (danshuangzhou1.equals("1")) {
								map.put("leixing", "2");
							}
							if (danshuangzhou1.equals("2")) {
								map.put("leixing", "3");
							}
							kechengService.insert_shangkeshijian(map);
						} else {
							map.put("kaishizhou", strs[2]);
							map.put("jieshuzhou", strs[3]);
							map.put("jiaoshiid", strs[1]);
							map.put("zhouci", strs[6]);
							map.put("kaishijieci", strs[4]);
							map.put("jieshujieci", strs[5]);
							if (strs[7].equals("0")) {
								map.put("leixing", "1");
							}
							if (strs[7].equals("1")) {
								map.put("leixing", "2");
							}
							if (strs[7].equals("2")) {
								map.put("leixing", "3");
							}
							kechengService.insert_shangkeshijian(map);
						}
					}
				}
				if (leixing.equals("1")) {
					// da += i + "," + shangkejiaoshi + "," + kaishishijian +
					// "," + kaishijieci + "," + jieshujieci + ";";
					String shangkeci[] = request.getParameter("shangkeci").split(";");
					for (String string : shangkeci) {
						String strs[] = string.split(",");
						if (strs[0].equals("1")) {
							Calendar cal1 = Calendar.getInstance();
							cal1.setTime(sdf.parse(xueQi2.getKaishiriqi()));
							Calendar cal2 = Calendar.getInstance();
							cal2.setTime(sdf.parse(strs[2]));
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
							c.setTime(sdf.parse(strs[2]));
							int zhouci = 0;
							if (c.get(Calendar.DAY_OF_WEEK) == 1) {
								zhouci = 7;
							} else {
								zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
							}
							map.put("kaishizhou", zhou + "");
							map.put("jiaoshiid", shangkejiaoshi1);
							map.put("zhouci", zhouci + "");
							map.put("kaishijieci", strs[3]);
							map.put("jieshujieci", strs[4]);
							map.put("leixing", "4");
							kechengService.insert_shangkeshijian(map);
						} else {
							Calendar cal1 = Calendar.getInstance();
							cal1.setTime(sdf.parse(xueQi2.getKaishiriqi()));
							Calendar cal2 = Calendar.getInstance();
							cal2.setTime(sdf.parse(strs[2]));
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
							c.setTime(sdf.parse(strs[2]));
							int zhouci = 0;
							if (c.get(Calendar.DAY_OF_WEEK) == 1) {
								zhouci = 7;
							} else {
								zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
							}
							map.put("kaishizhou", zhou + "");
							map.put("jiaoshiid", strs[1]);
							map.put("zhouci", zhouci + "");
							map.put("kaishijieci", strs[3]);
							map.put("jieshujieci", strs[4]);
							map.put("leixing", "4");
							kechengService.insert_shangkeshijian(map);
						}
					}
				}

				// 发送提醒消息
				if (ziji_banji.equals("1")) {
					TiXing tiXing = new TiXing();
					Date date2 = new Date();
					tiXing.setNeirong(xingming + "给你加了一门课《" + kechengmingcheng + "》");
					tiXing.setShijian(date2);
					tiXing.setZhuangtai(0);
					tiXing.setType(1);
					tiXing.setShujuid(keCheng.getId());
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					xiaoXiFaSong.setXiaoXiMingCheng("新加课程");
					xiaoXiFaSong.setXiaoXiNeiRong(xingming + "给你加了一门课《" + kechengmingcheng + "》");
					xiaoXiFaSong.setShuJuId(keCheng.getId());
					xiaoXiFaSong.setShuJuLeiXing(1);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(xueXiaoID);
					if (Tools.notEmpty(xueShengs)) {
						for (XueSheng xueSheng : xueShengs) {
							tiXing.setJieshourenid(xueSheng.getXueshengid());
							xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
							tixingService.insert(tiXing);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
					}
				}
				return "success";
			} else {
				return "fail";
			}
			// }
		} else {
			return null;
		}
	}

	// 通过条件查到课程选择添加
	@RequestMapping(value = "app_SaveZiZhuJiaKe_xuanze")
	@ResponseBody
	public String app_SaveZiZhuJiaKe_xuanze(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String id = request.getParameter("id");
		String ziji_banji = request.getParameter("ziji_banji");
		String status = request.getParameter("status");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		System.out.println(ziji_banji);
		String retInfo = "";
		if (str.equals(token)) {
			KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			int sum1 = 0;
			int sum2 = 0;
			String newxuanxiuids = "";
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(data[0]));
			if (ziji_banji.equals("0")) { // 只给自己添加
				if (keCheng.getLeixing() == 2) {
					int k = 0;
					List<String> xuanXiuIDs = kechengService.getAllXuanXiuIDByID(id);
					List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
					int t = 0;
					if (xuanXiuIDs != null && xuanXiuIDs.size() > 0) {
						for (String string : xuanXiuIDs) {
							if (string.equals(data[0])) {
								t = 1;
								break;
							}
						}
						if (t == 1) {
							retInfo = "yitianjia";
						} else {
							Map<String, String> map = new HashMap<>();
							map.put("kechengid", id);
							map.put("xueshengid", data[0]);
							if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
								for (String string : mianXiuIDs) {
									if (string.equals(data[0])) {
										kechengService.delete_shangkemianxiuren(map);
									}
								}
							}
							if (!keCheng.getTianjiarenid().equals(data[0])) {
								k = kechengService.insert_shangkexuanxiuren(map);
								if (k != 0) {
									retInfo = "success";
								} else {
									retInfo = "fail";
								}
							} else {
								retInfo = "yitianjia";
							}
						}
					} else {
						Map<String, String> map = new HashMap<>();
						map.put("kechengid", id);
						map.put("xueshengid", data[0]);
						if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
							for (String string : mianXiuIDs) {
								if (string.equals(data[0])) {
									kechengService.delete_shangkemianxiuren(map);
								}
							}
						}
						if (!keCheng.getTianjiarenid().equals(data[0])) {
							k = kechengService.insert_shangkexuanxiuren(map);
							if (k != 0) {
								retInfo = "success";
							} else {
								retInfo = "fail";
							}
						} else {
							retInfo = "yitianjia";
						}
					}
					/**
					 * if (keCheng.getBanjiids() != null &&
					 * keCheng.getBanjiids() != "") { String banjiids[] =
					 * keCheng.getBanjiids().split(","); for (String banji :
					 * banjiids) { if (banji.equals(data[2])) { t = 1; break; }
					 * } } if (keCheng.getXuanxiuids() != null &&
					 * keCheng.getXuanxiuids() != "") { String xuanxiuids[] =
					 * keCheng.getXuanxiuids().split(","); for (String xuanxiuid
					 * : xuanxiuids) { if (xuanxiuid.equals(data[0])) { t = 1;
					 * break; } } }
					 * 
					 * if (t == 1) { retInfo = "yitianjia"; } else { if
					 * (keCheng.getXuanxiuids() != null &&
					 * keCheng.getXuanxiuids() != "") { newxuanxiuids =
					 * keCheng.getXuanxiuids() + data[0] + ","; } else {
					 * newxuanxiuids = data[0] + ","; } KeCheng keCheng2 = new
					 * KeCheng(); keCheng2.setId(Integer.parseInt(id));
					 * keCheng2.setXuanxiuids(newxuanxiuids); int k =
					 * kechengService.updateBJandXXandMXByPrimaryKey(keCheng2);
					 */

				} else {
					retInfo = "fail";
				}
			}
			if (ziji_banji.equals("1")) { // 给本班添加
				if (keCheng.getLeixing() == 1) {
					String banjiid = data[2];
					// String banjiids = "";
					int samebanji = 0;
					int k = 0;
					List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(data[2]));
					Map<String, String> map = new HashMap<>();
					map.put("kechengid", id);
					map.put("banjiid", banjiid);
					List<String> banJis = kechengService.getByAllBanJiIDByID(id);
					String kechengid;
					List<String> mianXiuIDs = kechengService.getAllMianXiuIDByID(id);
					if (mianXiuIDs != null && mianXiuIDs.size() > 0) {
						for (String string : mianXiuIDs) {
							if (string.equals(data[0])) {
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
							retInfo = "yitianjia";
						} else {
							k = kechengService.insert_shangkebanji(map);
						}
					} else {
						k = kechengService.insert_shangkebanji(map);
					}
					/**
					 * if (keCheng.getBanjiids() != null &&
					 * keCheng.getBanjiids() != "") { banjiids =
					 * keCheng.getBanjiids(); String banji[] =
					 * banjiids.split(","); for (String string : banji) { if
					 * (string.equals(banjiid)) { samebanji = 1; break; } } }
					 * else { samebanji = -1; } List<XueSheng> xueShengs =
					 * xueshengService.getAllByBanJiID(Integer.parseInt(data[2])
					 * ); if (samebanji == 1) { return "yitianjia"; } else if
					 * (samebanji == 0) { banjiids += keCheng.getBanjiids() +
					 * banjiid + ","; } else { banjiids = banjiid + ","; } if
					 * (keCheng.getXuanxiuids() != null &&
					 * keCheng.getXuanxiuids() != "") { String xuanxiuids[] =
					 * keCheng.getXuanxiuids().split(","); for (String xuanxiu :
					 * xuanxiuids) { int same = 0; for (XueSheng xueSheng :
					 * xueShengs) { sum1++; if
					 * (xueSheng.getXueshengid().toString().equals(xuanxiu)) {
					 * same = 1; sum2++; break; } } if (same == 0) {
					 * newxuanxiuids += xuanxiu + ","; } } } KeCheng keCheng2 =
					 * new KeCheng(); keCheng2.setId(Integer.parseInt(id));
					 * keCheng2.setBanjiids(banjiids);
					 * keCheng2.setXuanxiuids(newxuanxiuids); int k =
					 * kechengService.updateBJandXXandMXByPrimaryKey(keCheng2);
					 */

					if (k != 0) {
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						Date date2 = new Date();
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						tiXing.setNeirong(data[3] + "给你加了一门课《" + keCheng.getKechengmingcheng() + "》");
						tiXing.setShijian(date2);
						tiXing.setZhuangtai(0);
						tiXing.setShujuid(keCheng.getId());
						tiXing.setType(1);
						xiaoXiFaSong.setXiaoXiMingCheng("新加课程");
						xiaoXiFaSong.setXiaoXiNeiRong(data[3] + "给你加了一门课《" + keCheng.getKechengmingcheng() + "》");
						xiaoXiFaSong.setShuJuId(keCheng.getId());
						xiaoXiFaSong.setShuJuLeiXing(1);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid()));
						for (XueSheng xueSheng : xueShengs) {
							tiXing.setJieshourenid(xueSheng.getXueshengid());
							xiaoXiFaSong.setFaSongMuBiao(xueSheng.getXueshengid().toString());
							tixingService.insert(tiXing);
							jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
						}
						// int n = sum1 - sum2;
						// retInfo = sum2 + "," + n;
						retInfo = "success";
					} else {
						retInfo = "fail";
					}
				} else {
					retInfo = "fail";
				}
			}
			return retInfo;
		} else {
			return null;
		}
	}

	// 首页 弹需要回执的备忘录 类型为1或2
	@RequestMapping("app_alertbeiwanglu")
	@ResponseBody
	public JSONObject alertBeiWangLu(HttpServletRequest request) {
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xueshengid = request.getParameter("xueshengid");
		List<BeiWL> beiWLs = new ArrayList<>();
		String s = xueshengid + "," + status;
		String str = Util.SHA1Encode(s);
		if (str.equals(token)) {
			Map<String, String> paramMap = new HashMap<>();
			// String date = new SimpleDateFormat("yyyy-MM-dd").format(new
			// Date());
			String start = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
			String end = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() + 1000 * 60 * 60 * 24)
					+ " 00:00";
			String banji = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid)).getBanjiid().toString();
			// String jieshouren1 = xueshengid + ",_;%";
			// String jieshouren2 = "%;" + xueshengid + ",_;%";
			// String banjiid1 = banji + ",%";
			// String banjiid2 = "%," + banji + ",%";
			// paramMap.put("start", start);
			// paramMap.put("end", end);
			// paramMap.put("jieshouren1", jieshouren1);
			// paramMap.put("jieshouren2", jieshouren2);
			// paramMap.put("banjiid1", banjiid1);
			// paramMap.put("banjiid2", banjiid2);
			paramMap.put("renid", xueshengid);
			paramMap.put("renleixing", "0");
			paramMap.put("banjiid", banji);
			paramMap.put("kaishi", start);
			paramMap.put("jieshu", end);

			List<BeiWL> list = new ArrayList<>();
			// List<BeiWL> list =
			// beiwlService.getAllByJieShouRenAndRiQi(paramMap);
			List<BeiWL> beiWLList = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(paramMap);
			for (BeiWL beiWL : beiWLList) {
				if (String.valueOf(beiWL.getHuizhi()) != null && !"".equals(String.valueOf(beiWL.getHuizhi())) && "1".equals(String.valueOf(beiWL.getHuizhi()))) {
					paramMap.put("beiwlid", beiWL.getId().toString());
					List<Map<String, Object>> huiZhis = beiwlService
							.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(paramMap);
					if (huiZhis == null || huiZhis.size() <= 0) {
						list.add(beiWL);
					}
				}

				/**
				 * int leixing = beiWL.getLeixing(); if (leixing == 1) { String
				 * jie[] = beiWL.getJieshouren().split(";"); for (String string
				 * : jie) { String strs[] = string.split(","); if
				 * (strs[0].equals(xueshengid) && strs[1].equals("0")) {
				 * beiWLs.add(beiWL); } } } if (leixing == 2) { String jie[] =
				 * beiWL.getBanjiids().split(","); if (beiWL.getJieshouren() !=
				 * null && !("".equals(beiWL.getJieshouren()))) { for (String
				 * string : jie) { if (banji.equals(string)) { String jies[] =
				 * beiWL.getJieshouren().split(";"); for (String strings : jies)
				 * { String strs[] = strings.split(","); if
				 * (strs[0].equals(xueshengid) && strs[1].equals("0")) {
				 * beiWLs.add(beiWL); } } } } } else { beiWLs.add(beiWL); } }
				 */
			}
			JSONObject json = new JSONObject();
			json.put("beiWLs", list);
			return json;
		} else {

			return null;
		}
	}

	// 在日程首页根据日期查询某天，返回课程，日程，事件和查寝数据
	@RequestMapping(value = "app_ChaXunKeChengByDate")
	@ResponseBody
	public Object app_ChaXunKeChengByDate(HttpServletRequest request) throws ParseException {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String banjiid = request.getParameter("banjiid");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<String, String> mapp = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		Map<Object, Object> map2 = new HashMap<>();
		if (str.equals(token)) {
			List<KeCheng> keChengs2 = new ArrayList<>();
			List<HuoDong> huodongs = new ArrayList<>();
			List<BeiWL> beiWLs2 = new ArrayList<>();
			ChaQinAnPai chaQinAnPai = new ChaQinAnPai();
			if (data.length == 1) {
				map2.put("chaqin", chaQinAnPai);
				map2.put("keChengs", keChengs2);
				map2.put("huodongs", huodongs);
				map2.put("beiwang", beiWLs2);

				return JSON.toJSON(map2);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String sign = data[0];
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(sign));
			// map.put("canyuren1", sign + ",1;%");
			// map.put("canyuren2", "%;" + sign + ",1;%");
			// map.put("xueshengid1", sign + ",%");
			// map.put("xueshengid2", "%," + sign + ",%");
			// map.put("shangkeriqi1", data[1] + "%");
			// map.put("shangkeriqi2", "%" + data[1] + "%");
			// map.put("banjiid1", banjiid + ",%");
			// map.put("banjiid2", "%," + banjiid + ",%");
			// String canyuren1 = "0," + data[0] + ",1;%";
			// String canyuren2 = "0," + data[0] + ",0;%";
			// String canyuren3 = "%;0," + data[0] + ",0;%";
			// String canyuren4 = "%;0," + data[0] + ",1;%";
			// mapp.put("canyuren1", canyuren1);
			// mapp.put("canyuren2", canyuren2);
			// mapp.put("canyuren3", canyuren3);
			// mapp.put("canyuren4", canyuren4);
			// mapp.put("riqi", data[1]);
			// mapp.put("banjiid1", banjiid + ",%");
			// mapp.put("banjiid2", "%," + banjiid + ",%");
			mapp.put("renid", data[0]);
			mapp.put("banjiid", banjiid);
			mapp.put("renleixing", "0");
			mapp.put("riqi", data[1]);
			// huodongs = huodongService.getAllBycanYuRenAndRiQiInAppMain(mapp);
			huodongs = huodongService.getAllByRiQiAndRenIDAndRenLeiXing(mapp);
			for (HuoDong huoDong : huodongs) {
				mapp.put("huodongid", huoDong.getHuodongid().toString());
				List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(mapp);
				// List<Map<String, Object>> juJueRens =
				// huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(mapp);
				if (huoDong.getTianjiarenid().toString().equals(data[0])) {
					huoDong.setZhuangtai(1);
				} else {
					if (canYuRens == null || canYuRens.size() <= 0) {
						huoDong.setZhuangtai(0);
					} else {
						huoDong.setZhuangtai(1);
					}
				}
				/**
				 * int i = 0; String canyuren[] =
				 * huoDong.getCanyuren().split(";"); for (String string :
				 * canyuren) { String canyurenid[] = string.split(","); if
				 * (canyurenid[1].equals(sign) && canyurenid[0].equals("0")) {
				 * if (canyurenid[2].equals("0")) { huoDong.setZhuangtai(0); i =
				 * 1; break; } if (canyurenid[2].equals("1")) {
				 * huoDong.setZhuangtai(1); i = 1; break; } if
				 * (canyurenid[2].equals("2")) { huoDong.setZhuangtai(2); i = 1;
				 * break; } } } if (i == 0) { huoDong.setZhuangtai(0); }
				 */
			}
			Map<String, String> m = new HashMap<>();
//			String xuexiaoid = xs.getXuexiaoXuehao().split("_")[0];
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			m.put("xueXiaoID", xuexiaoid);
			m.put("riqi", data[1]);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(m);
			if (xueQi == null) {
				map2.put("keChengs", keChengs2);
			} else {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(sdf.parse(xueQi.getKaishiriqi()));
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(sdf.parse(data[1]));
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
				c.setTime(sdf.parse(data[1]));
				int zhouci = 0;
				if (c.get(Calendar.DAY_OF_WEEK) == 1) {
					zhouci = 7;
				} else {
					zhouci = c.get(Calendar.DAY_OF_WEEK) - 1;
				}

				// map.put("ciriqi", "zqxj" + data[1] + ",");
				// map.put("danshuangriqi", "zqjx" + data[1] + ",");
				// map.put("danshaungzhouci", "zqxj" + zhouci + ",");
				// map.put("lianxuriqi", "zqjx" + zhouci + ",");
				// map.put("lianxuzhouci", "zxqj" + zhouci + ",");
				// map.put("tiaokeriqi", data[1] + "zqjx");

				List<Map<String, Object>> keChengMaps = kechengService
						.getByBanJiIDAndXueShengID(Integer.parseInt(banjiid), data[0]);
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
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
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
										JiaoXueLou jxl = jiaoXueLouService.selectByPrimaryKey(jshi.getJiaoxuelouid());
										kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
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
							kecheng.setJiaoXueLouMing(jxl.getJiaoXueLouMing());
							kecheng.setXiaoquming(xiaoquService.selectByPrimaryKey(jxl.getXiaoQuId()).getMingcheng());
							kecheng.setKaishishijian(jcsj1.getKaishishijian());
							kecheng.setJieshushijian(jcsj2.getJieshushijian());
							keChengs2.add(kecheng);
						}
					}
				}
				map2.put("keChengs", keChengs2);
				/**
				 * List<KeCheng> keChengs =
				 * kechengService.getAllByxueShengIDandRiQiandBanJi(map); for
				 * (KeCheng keCheng : keChengs) { String danShuangZhouShuoMing[]
				 * = keCheng.getDanshuangzhoushuoming().split("zqxj"); switch
				 * (danShuangZhouShuoMing[0]) { case "1": // 连续 String zhouCi[]
				 * = danShuangZhouShuoMing[1].split(","); // 判断周次是否在 if
				 * (Integer.parseInt(zhouCi[0]) <= zhou &&
				 * Integer.parseInt(zhouCi[1]) >= zhou &&
				 * keCheng.getXuenian().equals(xueQi.getXuenian()) &&
				 * keCheng.getXueqi().toString().equals(xueQi.getXueqi().
				 * toString())) {
				 * 
				 * // 获取周几，节次，教室 String total[] =
				 * danShuangZhouShuoMing[2].split("zxqj"); for (String string2 :
				 * total) { String xinxi[] = string2.split(","); if
				 * (xinxi[0].equals(zhouci + "")) { KeCheng kecheng = new
				 * KeCheng(); kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(Integer.parseInt(zhouCi[0]));
				 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
				 * 
				 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
				 * JCSJ jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * kecheng.setKaishishijian(jcsj1.getKaishishijian());
				 * kecheng.setJieshushijian(jcsj2.getJieshushijian());
				 * 
				 * JiaoShi jshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]))
				 * ; kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * ()); kecheng.setXiaoquming(
				 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
				 * getMingcheng()); keChengs2.add(kecheng); } } } // 调课 String
				 * tiaoKe[] = danShuangZhouShuoMing[4].split("zqjx"); if
				 * (tiaoKe.length > 0) { for (String string2 : tiaoKe) { if
				 * (data[1].equals(string2.split(",")[0])) { String kc[] =
				 * string2.split(","); KeCheng kecheng = new KeCheng();
				 * kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(zhou); //
				 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi[1]));
				 * kecheng.setZhouci(zhouci); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
				 * jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * kecheng.setKaishishijian(jcsj1.getKaishishijian());
				 * kecheng.setJieshushijian(jcsj2.getJieshushijian());
				 * 
				 * JiaoShi jshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
				 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * ()); kecheng.setXiaoquming(
				 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
				 * getMingcheng()); keChengs2.add(kecheng); } } } break;
				 * 
				 * case "2": // 单双周 String zhouCi2[] =
				 * danShuangZhouShuoMing[1].split(","); // 判断周次是否在 if
				 * (Integer.parseInt(zhouCi2[0]) <= zhou &&
				 * Integer.parseInt(zhouCi2[1]) >= zhou &&
				 * keCheng.getXuenian().equals(xueQi.getXuenian()) &&
				 * keCheng.getXueqi().toString().equals(xueQi.getXueqi().
				 * toString())) { String danShuang[] =
				 * danShuangZhouShuoMing[2].split("zqjx"); if (zhou % 2 == 0) {
				 * // 双周 // 获取周几，节次，教室 String total[] =
				 * danShuang[1].split("zxqj"); for (String string2 : total) {
				 * String xinxi[] = string2.split(","); if
				 * (xinxi[0].equals(zhouci + "")) { KeCheng kecheng = new
				 * KeCheng(); kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
				 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
				 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
				 * JCSJ jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * kecheng.setKaishishijian(jcsj1.getKaishishijian());
				 * kecheng.setJieshushijian(jcsj2.getJieshushijian());
				 * 
				 * JiaoShi jshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]))
				 * ; kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * ()); kecheng.setXiaoquming(
				 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
				 * getMingcheng()); keChengs2.add(kecheng); } }
				 * 
				 * } else { // 单周 // 获取周几，节次，教室 String total[] =
				 * danShuang[0].split("zxqj"); for (String string2 : total) {
				 * String xinxi[] = string2.split(","); if
				 * (xinxi[0].equals(zhouci + "")) { KeCheng kecheng = new
				 * KeCheng(); kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(Integer.parseInt(zhouCi2[0]));
				 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
				 * kecheng.setZhouci(Integer.parseInt(xinxi[0])); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[1]));
				 * JCSJ jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(xinxi[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * kecheng.setKaishishijian(jcsj1.getKaishishijian());
				 * kecheng.setJieshushijian(jcsj2.getJieshushijian());
				 * 
				 * JiaoShi jshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(xinxi[3]))
				 * ; kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * ()); kecheng.setXiaoquming(
				 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
				 * getMingcheng()); keChengs2.add(kecheng); } } } } String
				 * tiaoKe1[] = danShuangZhouShuoMing[4].split("zqjx"); if
				 * (tiaoKe1.length > 0) { for (String string3 : tiaoKe1) { if
				 * (data[1].equals(string3.split(",")[0])) { String kc[] =
				 * string3.split(","); KeCheng kecheng = new KeCheng();
				 * kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(zhou); //
				 * kecheng.setJieshuzhou(Integer.parseInt(zhouCi2[1]));
				 * kecheng.setZhouci(zhouci); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
				 * jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * kecheng.setKaishishijian(jcsj1.getKaishishijian());
				 * kecheng.setJieshushijian(jcsj2.getJieshushijian());
				 * 
				 * JiaoShi jshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
				 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService
				 * .selectByPrimaryKey(jshi.getJiaoxuelouid()).getJiaoXueLouMing
				 * ()); kecheng.setXiaoquming(
				 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
				 * getMingcheng()); keChengs2.add(kecheng); } } } break; case
				 * "3": // 单次，不连续,只需比对时间 String kcheng[] =
				 * danShuangZhouShuoMing[1].split("zqjx"); for (String string4 :
				 * kcheng) { if (data[1].equals(string4.split(",")[0])) { String
				 * kc[] = string4.split(","); KeCheng kecheng = new KeCheng();
				 * kecheng.setLeixing(3); kecheng.setId(keCheng.getId());
				 * kecheng.setKechengid(keCheng.getKechengid());
				 * kecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
				 * kecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
				 * kecheng.setKaishizhou(zhou); // kecheng.setJieshuzhou(zhou);
				 * kecheng.setZhouci(zhouci); JCSJ jcsj1 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[1])); JCSJ
				 * jcsj2 =
				 * jcsjService.selectByPrimaryKey(Integer.parseInt(kc[2]));
				 * kecheng.setKaishijieci(jcsj1.getJieci());
				 * kecheng.setJieshujieci(jcsj2.getJieci());
				 * kecheng.setKaishishijian(jcsj1.getKaishishijian());
				 * kecheng.setJieshushijian(jcsj2.getJieshushijian());
				 * 
				 * JiaoShi jshi =
				 * jiaoshiService.selectByPrimaryKey(Integer.parseInt(kc[3]));
				 * kecheng.setJiaoshiming(jshi.getJiaoshiming());
				 * kecheng.setJiaoXueLouMing(jiaoXueLouService.
				 * selectByPrimaryKey(jshi.getJiaoxuelouid())
				 * .getJiaoXueLouMing()); kecheng.setXiaoquming(
				 * xiaoquService.selectByPrimaryKey(jshi.getXiaoquid()).
				 * getMingcheng()); keChengs2.add(kecheng); } } break; } }
				 */
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			long time = format.parse(data[1]).getTime();
			time = time + 24 * 60 * 60 * 1000;
			Date date = new Date(time);
			String nextriqi = format.format(date);
			mapp.put("kaishi", data[1]);
			mapp.put("jieshu", nextriqi);
			// List<BeiWL> beiWLs =
			// beiwlService.getAllByuserIDAndLeiXingOrJieShouRen(map3);
			List<BeiWL> beiWLS = new ArrayList<>();
			List<BeiWL> beiWLs = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(mapp);
			for (BeiWL beiWL : beiWLs) {
				if (null == beiWL.getHuodongid() || beiWL.getHuodongid().toString().isEmpty()) {
					beiWLS.add(beiWL);
					continue;
				} else {
					int huodongid = beiWL.getHuodongid();
					HuoDong huodong = huodongService.selectByPrimaryKey(huodongid);
					if (null == huodong) {
						beiWLS.add(beiWL);
						continue;
					} else {
						Map<String, String> mm = new HashMap<>();
						mm.put("huodongid", huodong.getHuodongid().toString());
						mapp.put("renid", data[0]);
						mapp.put("renleixing", "0");
						List<Map<String, Object>> jujueren = huodongService
								.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(mm);
						if (jujueren.isEmpty() || jujueren.size() <= 0 || null == jujueren) {
							beiWLS.add(beiWL);
							continue;
						}
					}
				}
			}
			// YongHu yongHu =
			// yonghuService.selectYongHuByID(Integer.parseInt(beiWL.getUserid()));
			// if (yongHu != null) {
			// beiWL.setUsername(yongHu.getYonghuxingming());
			// } else {
			// XueSheng xueSheng =
			// xueshengService.getUserById(Integer.parseInt(beiWL.getUserid()));
			// beiWL.setUsername(xueSheng.getXingming());
			// }
			// if (beiWL.getHuizhi() != null && beiWL.getHuizhi() == 1) {
			// if (beiWL.getLeixing() == 1) {
			// String jieshourens[] = beiWL.getJieshouren().split(";");
			// for (String jieshourenn : jieshourens) {
			// String jieshou[] = jieshourenn.split(",");
			// if (jieshou[0].equals(code)) {
			// beiWL.setZhuangtai(jieshou[1]);
			// }
			// }
			// }
			// if (beiWL.getLeixing() == 2) {
			// if (null == beiWL.getBanjiids() ||
			// "".equals(beiWL.getBanjiids())) {
			// String jieshourens[] = beiWL.getJieshouren().split(";");
			// for (String jieshouren : jieshourens) {
			// String jieshou[] = jieshouren.split(":");
			// String sid[] = jieshou[1].split("!");
			// for (String string2 : sid) {
			// String[] s = string2.split(",");
			// if (s[0].equals(code)) {
			// beiWL.setZhuangtai(s[1]);
			// }
			// }
			// }
			// } else {
			// if (null != beiWL.getJieshouren() &&
			// !("".equals(beiWL.getJieshouren()))) {
			// String jieshourens[] = beiWL.getJieshouren().split(";");
			// int i = 0;
			// for (String jieshouren : jieshourens) {
			// String[] s = jieshouren.split(",");
			// if (s[0].equals(code)) {
			// beiWL.setZhuangtai(s[1]);
			// i = 1;
			// break;
			// }
			// }
			// if (i == 0) {
			// beiWL.setZhuangtai("0");
			// }
			// } else {
			// beiWL.setZhuangtai("0");
			// }
			// }
			// }
			// }
			// }
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getBybanJiID(banjiid + ",%", "%," + banjiid + ",%");
			if (fuDaoYuan != null) {
				paramMap.put("fudaoyuanId", fuDaoYuan.getFudaoyuanid());
				paramMap.put("susheId", data[2]);
				paramMap.put("date", sdf.parse(data[1]));
				paramMap.put("searchStatus", "1");
				List<ChaQinAnPai> chaQinAnPaiList = chaQinService.selectByYongHuIdAndRiQi(paramMap);
				if (chaQinAnPaiList != null && chaQinAnPaiList.size() > 0) {
					// chaQinAnPai = chaQinAnPaiList.get(0);
					map2.put("chaqin", chaQinAnPaiList);
				} else {
					map2.put("chaqin", null);
				}
			}
			// map2.put("chaqin", chaQinAnPai);
			map2.put("huodongs", huodongs);
			map2.put("beiwang", beiWLS);
			return JSON.toJSON(map2);
		} else {
			return null;
		}
	}

	// 我的日程一进入根据当前年月日查询今日日程
	@RequestMapping(value = "app_WoDeRiCheng") // 未使用，getAllByxueShengIDandshangKeRiQi方法内部有改动
	@ResponseBody
	public JSONObject app_WoDeRiCheng(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String data[] = code.split(",zytech,");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<String, String> map = new HashMap<>();
		Map<String, String> mapp = new HashMap<>();
		Map<Object, Object> map2 = new HashMap<>();
		if (str.equals(token)) {
			String canyuren = "%0," + data[0] + ",1%";
			String sign = data[0];
			map.put("canyuren1", sign + ",1;%");
			map.put("canyuren2", "%;" + sign + ",1;%");
			map.put("shangkeriqi1", data[1] + "%");
			map.put("shangkeriqi2", "%" + data[1] + "%");
			mapp.put("canyuren", canyuren);
			mapp.put("riqi", data[1]);
			List<HuoDong> huodongs = huodongService.getAllBycanYuRenAndRiQi(mapp);
			List<KeCheng> keChengs = kechengService.getAllByxueShengIDandshangKeRiQi(map);
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (KeCheng keCheng : keChengs) {
				String shangkeriqi[] = keCheng.getShangkeriqi().split(",");
				for (String string : shangkeriqi) {
					String riqi1[] = string.split(";");
					KeCheng newkecheng = new KeCheng();
					if (riqi1[0].equals(data[1]) && riqi1.length != 1) {
						newkecheng.setXiaoqu(riqi1[1]);
						newkecheng.setShangkejiaoshi(riqi1[2]);
						newkecheng.setKaishijieci(Integer.parseInt(riqi1[3]));
						newkecheng.setJieshujieci(Integer.parseInt(riqi1[4]));
						int kaishijieci = newkecheng.getKaishijieci();
						int jieshujieci = newkecheng.getJieshujieci();
						String kaishishijian = jcsjService.getkaiShiShiJianByid(kaishijieci);
						String jieshushijian = jcsjService.getjieShuShiJianByid(jieshujieci);
						newkecheng.setKaishishijian(kaishishijian);
						newkecheng.setJieshushijian(jieshushijian);
						String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
								.getMingcheng();
						String jiaoshiming = jiaoshiService
								.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi())).getJiaoshiming();
						newkecheng.setXiaoquming(xiaoquming);
						newkecheng.setJiaoshiming(jiaoshiming);
						newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
						newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
						newkecheng.setCanyuren(keCheng.getCanyuren());
						keChengs2.add(newkecheng);
					}
				}
				int kaishijieci = keCheng.getKaishijieci();
				int jieshujieci = keCheng.getJieshujieci();
				String kaishishijian = jcsjService.getkaiShiShiJianByid(kaishijieci);
				String jieshushijian = jcsjService.getjieShuShiJianByid(jieshujieci);
				keCheng.setKaishishijian(kaishishijian);
				keCheng.setJieshushijian(jieshushijian);
				String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
						.getMingcheng();
				String jiaoshiming = jiaoshiService.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi()))
						.getJiaoshiming();
				keCheng.setXiaoquming(xiaoquming);
				keCheng.setJiaoshiming(jiaoshiming);
				keChengs2.add(keCheng);
			}
			map2.put("keChengs", keChengs2);
			map2.put("huodongs", huodongs);
			System.out.println(map2);
			return JSONObject.fromObject(map2);
		} else {
			return null;
		}
	}

	// 通过年月日查询那一天的日程
	@RequestMapping(value = "app_ChaXunBynianyueri")
	@ResponseBody
	public JSONObject app_ChaXunBynianyueri(HttpServletRequest request) {
		String nian = request.getParameter("nian");
		String yue = request.getParameter("yue");
		String ri = request.getParameter("ri");
		String xueshengid = request.getParameter("studentid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		Map<String, String> map = new HashMap<>();
		Map<String, String> mapp = new HashMap<>();
		Map<Object, Object> map2 = new HashMap<>();
		String code = nian;
		if (Integer.parseInt(yue) < 10) {
			code += "-0" + yue;
		} else {
			code += "-" + yue;
		}
		if (Integer.parseInt(ri) < 10) {
			code += "-0" + ri;
		} else {
			code += "-" + ri;
		}
		System.out.println(code);
		if (str.equals(token)) {
			String canyuren = "%0," + xueshengid + ",1;";
			String sign = xueshengid;
			map.put("canyuren1", sign + ",1;%");
			map.put("canyuren2", "%;" + sign + ",1;%");
			map.put("shangkeriqi1", code + "%");
			map.put("shangkeriqi2", "%" + code + "%");
			mapp.put("canyuren", canyuren);
			mapp.put("riqi", code);
			List<HuoDong> huodongs = huodongService.getAllBycanYuRenAndRiQi(mapp);
			List<KeCheng> keChengs = kechengService.getAllByxueShengIDandshangKeRiQi(map);
			List<KeCheng> keChengs2 = new ArrayList<>();
			for (KeCheng keCheng : keChengs) {
				String shangkeriqi[] = keCheng.getShangkeriqi().split(",");
				for (String string : shangkeriqi) {
					String riqi1[] = string.split(";");
					KeCheng newkecheng = new KeCheng();
					if (riqi1[0].equals(code) && riqi1.length != 1) {
						newkecheng.setXiaoqu(riqi1[1]);
						newkecheng.setShangkejiaoshi(riqi1[2]);
						newkecheng.setKaishijieci(Integer.parseInt(riqi1[3]));
						newkecheng.setJieshujieci(Integer.parseInt(riqi1[4]));
						int kaishijieci = newkecheng.getKaishijieci();
						int jieshujieci = newkecheng.getJieshujieci();
						String kaishishijian = jcsjService.getkaiShiShiJianByid(kaishijieci);
						String jieshushijian = jcsjService.getjieShuShiJianByid(jieshujieci);
						newkecheng.setKaishishijian(kaishishijian);
						newkecheng.setJieshushijian(jieshushijian);
						String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
								.getMingcheng();
						String jiaoshiming = jiaoshiService
								.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi())).getJiaoshiming();
						newkecheng.setXiaoquming(xiaoquming);
						newkecheng.setJiaoshiming(jiaoshiming);
						newkecheng.setKechengmingcheng(keCheng.getKechengmingcheng());
						newkecheng.setRenkejiaoshi(keCheng.getRenkejiaoshi());
						newkecheng.setCanyuren(keCheng.getCanyuren());
						keChengs2.add(newkecheng);
					}
				}
				int kaishijieci = keCheng.getKaishijieci();
				int jieshujieci = keCheng.getJieshujieci();
				String kaishishijian = jcsjService.getkaiShiShiJianByid(kaishijieci);
				String jieshushijian = jcsjService.getjieShuShiJianByid(jieshujieci);
				keCheng.setKaishishijian(kaishishijian);
				keCheng.setJieshushijian(jieshushijian);
				String xiaoquming = xiaoquService.selectByPrimaryKey(Integer.parseInt(keCheng.getXiaoqu()))
						.getMingcheng();
				String jiaoshiming = jiaoshiService.selectByPrimaryKey(Integer.parseInt(keCheng.getShangkejiaoshi()))
						.getJiaoshiming();
				keCheng.setXiaoquming(xiaoquming);
				keCheng.setJiaoshiming(jiaoshiming);
				keChengs2.add(keCheng);
			}
			map2.put("keChengs", keChengs2);
			map2.put("huodongs", huodongs);
			System.out.println(map2);
			return JSONObject.fromObject(map2);
		} else {
			return null;
		}
	}

	// 通过登录学生的学号，姓名和学生id返回所有同班同学的信息
	@RequestMapping(value = "app_XianShiTongBan")
	@ResponseBody
	public List<XueSheng> app_XianShiTongBan(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = code + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(code));
			List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(xueSheng.getBanjiid());
			return xueShengs;
		} else {
			return null;
		}
	}

	// 通过登录学生信息，获取社团好友
	@RequestMapping(value = "app_XianShiSheTuan")
	@ResponseBody
	public List<Map<String, String>> app_XianShiSheTuan(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = code + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			String xueshengid1 = "%," + code + ",%";
			String xueshengid2 = code + ",%";
			XueSheng user = xueshengService.getUserById(Integer.parseInt(code));
			String xueXiaoXueHao[] = user.getXuexiaoXuehao().split("_");
			String xuexiaoid = xueXiaoXueHao[0];
			Date date = new Date();
			SimpleDateFormat dFormat = new SimpleDateFormat("yyyy");
			List<SheTuanXinXi> sheTuanXinXis = shetuanSerivce.selectSheTuanXinXiByXueShengIDAndNianDuAndXueXiaoID(
					xueshengid1, xueshengid2, dFormat.format(date), Integer.parseInt(xuexiaoid));
			List<Map<String, String>> maps = new ArrayList<>();
			if (code != null && code != "") {
				for (SheTuanXinXi sheTuanXinXi : sheTuanXinXis) {
					String shetuanmingcheng = shetuanSerivce
							.selectSheTuanJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid()).getMingcheng();
					String sheyuans = sheTuanXinXi.getSheyuanids();
					String sheyuanids[] = sheyuans.split(",");
					List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = shetuanSerivce
							.selectSheTuanBuMenJiBenXinXiBySheTuanID(sheTuanXinXi.getShetuanid());
					for (String string : sheyuanids) {
						Map<String, String> map = new HashMap<>();
						XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(string));
						String sheyuanid1 = "%," + string + ",%";
						String sheyuanid2 = string + ",%";
						String bumenmingcheng = "";
						if (sheTuanBuMenJiBenXinXins != null) {
							for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
								SheTuanBuMenXinXin XinXin = shetuanSerivce
										.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(
												sheTuanBuMenJiBenXinXin.getBumenid(), dFormat.format(date), sheyuanid1,
												sheyuanid2);
								if (XinXin == null) {
									continue;
								}
								bumenmingcheng += sheTuanBuMenJiBenXinXin.getBumenmingcheng() + "/";
							}
						}
						if (bumenmingcheng.equals("")) {
							bumenmingcheng = "——";
						} else {
							bumenmingcheng = bumenmingcheng.substring(0, bumenmingcheng.indexOf("/"));
						}
						map.put("shetuanmingcheng", shetuanmingcheng);
						map.put("bumenmingcheng", bumenmingcheng);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
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
					List<SheTuanBuMenJiBenXinXin> sheTuanBuMenJiBenXinXins = shetuanSerivce
							.selectSheTuanBuMenJiBenXinXiByXueShengZuZhiID(xueShengZuZhiXinXi.getXueshengzuzhiid());
					for (String string : sheyuanids) {
						Map<String, String> map = new HashMap<>();
						XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(string));
						String sheyuanid1 = "%," + string + ",%";
						String sheyuanid2 = string + ",%";
						String bumenmingcheng = "";
						if (sheTuanBuMenJiBenXinXins != null) {
							for (SheTuanBuMenJiBenXinXin sheTuanBuMenJiBenXinXin : sheTuanBuMenJiBenXinXins) {
								SheTuanBuMenXinXin xinXin = shetuanSerivce
										.selectSheTuanBuMenByBuMenIDAndNianDuAndXueShengID(
												sheTuanBuMenJiBenXinXin.getBumenid(), dFormat.format(date), sheyuanid1,
												sheyuanid2);
								if (xinXin == null) {
									continue;
								}
								bumenmingcheng += sheTuanBuMenJiBenXinXin.getBumenmingcheng() + "/";
							}
						}
						if (bumenmingcheng.equals("")) {
							bumenmingcheng = "——";
						} else {
							bumenmingcheng = bumenmingcheng.substring(0, bumenmingcheng.lastIndexOf("/"));
						}
						map.put("shetuanmingcheng", zuzhimingcheng);
						map.put("bumenmingcheng", bumenmingcheng);
						map.put("xueshengid", xueSheng.getXueshengid().toString());
						map.put("xuehao", xueSheng.getXuehao());
						map.put("xingming", xueSheng.getXingming());
						maps.add(map);
					}
				}
			} else {
				return maps;
			}
			return maps;
		} else {
			return null;
		}
	}

	// 通过学生id返回该学生自身的学号，姓名和班级
	@RequestMapping(value = "app_CanYuRenByid_huodong")
	@ResponseBody
	public JSONObject app_CanYuRenByid_huodong(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String id = request.getParameter("huodongid");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huoDong != null) {
				Map<Object, Object> mpp = new HashMap<>();
				List<Map<String, String>> maps = new ArrayList<>();
				List<Map<String, String>> maps2 = new ArrayList<>();
				Map<String, String> ma = new HashMap<>();
				ma.put("huodongid", id);
				List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(ma);
				List<Map<String, Object>> juJueRens = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(ma);
				if (huoDong.getLeixing().toString() == null || "".equals(huoDong.getLeixing().toString())) {
					return null;
				} else {
					if (huoDong.getLeixing() == 1) {
						List<String> banJiIDs = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(id));
						for (String s : banJiIDs) {
							List<XueSheng> xueShengs = xueshengService.getAllByBanJiID(Integer.parseInt(s));
							for (XueSheng xueSheng : xueShengs) {
								Map<String, String> map = new HashMap<>();
								int i = 0;
								if (canYuRens != null && canYuRens.size() > 0) {
									for (Map<String, Object> cyr : canYuRens) {
										if (xueSheng.getXueshengid().toString().equals(cyr.get("canYuRenID").toString())
												&& cyr.get("leiXing").toString().equals("0")) {
											map.put("xueshengid", xueSheng.getXueshengid().toString());
											map.put("xuehao", xueSheng.getXuehao());
											map.put("xingming", xueSheng.getXingming());
											map.put("canyuzhuangtai", "1");
											maps.add(map);
											i = 1;
											break;
										}
									}
								}
								if (juJueRens != null && juJueRens.size() > 0) {
									for (Map<String, Object> jjr : juJueRens) {
										if (xueSheng.getXueshengid().toString().equals(jjr.get("juJueRenID").toString())
												&& jjr.get("leiXing").toString().equals("0")) {
											map.put("xueshengid", xueSheng.getXueshengid().toString());
											map.put("xuehao", xueSheng.getXuehao());
											map.put("xingming", xueSheng.getXingming());
											map.put("canyuzhuangtai", "2");
											if (jjr.containsKey("liYou") && !(jjr.get("liYou").toString().equals(""))
													&& null != jjr.get("liYou").toString()) {
												map.put("liyou", jjr.get("liYou").toString());
											} else {
												map.put("liyou", "");
											}
											maps.add(map);
											i = 1;
											break;
										}
									}
								}
								if (i == 0) {
									map.put("xueshengid", xueSheng.getXueshengid().toString());
									map.put("xuehao", xueSheng.getXuehao());
									map.put("xingming", xueSheng.getXingming());
									map.put("canyuzhuangtai", "0");
									maps.add(map);
								}
							}
						}
					}
					if (huoDong.getLeixing() == 2) {
						List<Map<String, Object>> rens = huodongService
								.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(ma);
						for (Map<String, Object> ren : rens) {
							Map<String, String> map = new HashMap<>();
							if (ren.get("leiXing").toString().equals("0")) {
								XueSheng xueSheng = xueshengService
										.selectByPrimaryKey(Integer.parseInt(ren.get("yaoQingRenID").toString()));
								int i = 0;
								if (canYuRens != null && canYuRens.size() > 0) {
									for (Map<String, Object> cyr : canYuRens) {
										if (xueSheng.getXueshengid().toString().equals(cyr.get("canYuRenID").toString())
												&& cyr.get("leiXing").toString().equals("0")) {
											map.put("xueshengid", xueSheng.getXueshengid().toString());
											map.put("xuehao", xueSheng.getXuehao());
											map.put("xingming", xueSheng.getXingming());
											map.put("canyuzhuangtai", "1");
											maps.add(map);
											i = 1;
											break;
										}
									}
								}
								if (juJueRens != null && juJueRens.size() > 0) {
									for (Map<String, Object> jjr : juJueRens) {
										if (xueSheng.getXueshengid().toString().equals(jjr.get("juJueRenID").toString())
												&& jjr.get("leiXing").toString().equals("0")) {
											map.put("xueshengid", xueSheng.getXueshengid().toString());
											map.put("xuehao", xueSheng.getXuehao());
											map.put("xingming", xueSheng.getXingming());
											map.put("canyuzhuangtai", "2");
											if (jjr.containsKey("liYou") && !(jjr.get("liYou").toString().equals(""))
													&& null != jjr.get("liYou").toString()) {
												map.put("liyou", jjr.get("liYou").toString());
											} else {
												map.put("liyou", "");
											}
											maps.add(map);
											i = 1;
											break;
										}
									}
								}
								if (i == 0) {
									map.put("xueshengid", xueSheng.getXueshengid().toString());
									map.put("xuehao", xueSheng.getXuehao());
									map.put("xingming", xueSheng.getXingming());
									map.put("canyuzhuangtai", "0");
									maps.add(map);
								}
							} else {
								YongHu fudaoyuan = yonghuService
										.selectYongHuByID(Integer.parseInt(ren.get("yaoQingRenID").toString()));
								int i = 0;
								if (canYuRens != null && canYuRens.size() > 0) {
									for (Map<String, Object> cyr : canYuRens) {
										if (fudaoyuan.getYonghuid().toString().equals(cyr.get("canYuRenID").toString())
												&& cyr.get("leiXing").toString().equals("1")) {
											map.put("fudaoyuanid", fudaoyuan.getYonghuid().toString());
											map.put("xingming", fudaoyuan.getYonghuxingming());
											map.put("canyuzhuangtai", "1");
											maps2.add(map);
											i = 1;
											break;
										}
									}
								}
								if (juJueRens != null && juJueRens.size() > 0) {
									for (Map<String, Object> jjr : juJueRens) {
										if (fudaoyuan.getYonghuid().toString().equals(jjr.get("juJueRenID").toString())
												&& jjr.get("leiXing").toString().equals("1")) {
											map.put("fudaoyuanid", fudaoyuan.getYonghuid().toString());
											map.put("xingming", fudaoyuan.getYonghuxingming());
											map.put("canyuzhuangtai", "2");
											if (jjr.containsKey("liYou") && !(jjr.get("liYou").toString().equals(""))
													&& null != jjr.get("liYou").toString()) {
												map.put("liyou", jjr.get("liYou").toString());
											} else {
												map.put("liyou", "");
											}
											maps2.add(map);
											i = 1;
											break;
										}
									}
								}
								if (i == 0) {
									map.put("fudaoyuanid", fudaoyuan.getYonghuid().toString());
									map.put("xingming", fudaoyuan.getYonghuxingming());
									map.put("canyuzhuangtai", "0");
									maps2.add(map);
								}
							}
						}
					}
					if (huoDong.getTianjiarenid().toString().equals(xueshengid)
							&& huoDong.getTianjiarenleixing().toString().equals("0")) {
						List<String> banJiIDs = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(id));
						XueSheng xueSheng = xueshengService.selectByPrimaryKey(huoDong.getTianjiarenid());
						int n = 0;
						if (banJiIDs != null && banJiIDs.size() > 0) {
							for (String s : banJiIDs) {
								if (s.equals(xueSheng.getBanjiid().toString())) {
									n = 1;
								}
							}
						}
						if (n == 0) {
							Map<String, String> map = new HashMap<>();
							int i = 0;
							if (canYuRens != null && canYuRens.size() > 0) {
								for (Map<String, Object> cyr : canYuRens) {
									if (xueSheng.getXueshengid().toString().equals(cyr.get("canYuRenID").toString())
											&& cyr.get("leiXing").toString().equals("0")) {
										map.put("xueshengid", xueSheng.getXueshengid().toString());
										map.put("xuehao", xueSheng.getXuehao());
										map.put("xingming", xueSheng.getXingming());
										map.put("canyuzhuangtai", "1");
										maps.add(map);
										i = 1;
										break;
									}
								}
							}
							if (juJueRens != null && juJueRens.size() > 0) {
								for (Map<String, Object> jjr : juJueRens) {
									if (xueSheng.getXueshengid().toString().equals(jjr.get("juJueRenID").toString())
											&& jjr.get("leiXing").toString().equals("0")) {
										map.put("xueshengid", xueSheng.getXueshengid().toString());
										map.put("xuehao", xueSheng.getXuehao());
										map.put("xingming", xueSheng.getXingming());
										map.put("canyuzhuangtai", "2");
										if (jjr.containsKey("liYou") && !(jjr.get("liYou").toString().equals(""))
												&& null != jjr.get("liYou").toString()) {
											map.put("liyou", jjr.get("liYou").toString());
										} else {
											map.put("liyou", "");
										}
										maps.add(map);
										i = 1;
										break;
									}
								}
							}
							if (i == 0) {
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "1");
								maps.add(map);
							}
						}
					} else {
						Map<String, String> map = new HashMap<>();
						YongHu fudaoyuan = yonghuService.selectYongHuByID(huoDong.getTianjiarenid());
						if (fudaoyuan != null) {
							int i = 0;
							if (canYuRens != null && canYuRens.size() > 0) {
								for (Map<String, Object> cyr : canYuRens) {
									if (fudaoyuan.getYonghuid().toString().equals(cyr.get("canYuRenID").toString())
											&& cyr.get("leiXing").toString().equals("1")) {
										map.put("fudaoyuanid", fudaoyuan.getYonghuid().toString());
										map.put("xingming", fudaoyuan.getYonghuxingming());
										map.put("canyuzhuangtai", "1");
										maps2.add(map);
										i = 1;
										break;
									}
								}
							}
							if (juJueRens != null && juJueRens.size() > 0) {
								for (Map<String, Object> jjr : juJueRens) {
									if (fudaoyuan.getYonghuid().toString().equals(jjr.get("juJueRenID").toString())
											&& jjr.get("leiXing").toString().equals("1")) {
										map.put("fudaoyuanid", fudaoyuan.getYonghuid().toString());
										map.put("xingming", fudaoyuan.getYonghuxingming());
										map.put("canyuzhuangtai", "2");
										if (jjr.containsKey("liYou") && !(jjr.get("liYou").toString().equals(""))
												&& null != jjr.get("liYou").toString()) {
											map.put("liyou", jjr.get("liYou").toString());
										} else {
											map.put("liyou", "");
										}
										maps2.add(map);
										i = 1;
										break;
									}
								}
							}
							if (i == 0) {
								map.put("fudaoyuanid", fudaoyuan.getYonghuid().toString());
								map.put("xingming", fudaoyuan.getYonghuxingming());
								map.put("canyuzhuangtai", "1");
								maps2.add(map);
							}
						} else {
							XueSheng xueSheng = xueshengService.selectByPrimaryKey(huoDong.getTianjiarenid());
							int i = 0;
							if (canYuRens != null && canYuRens.size() > 0) {
								for (Map<String, Object> cyr : canYuRens) {
									if (xueSheng.getXueshengid().toString().equals(cyr.get("canYuRenID").toString())
											&& cyr.get("leiXing").toString().equals("0")) {
										map.put("xueshengid", xueSheng.getXueshengid().toString());
										map.put("xuehao", xueSheng.getXuehao());
										map.put("xingming", xueSheng.getXingming());
										map.put("canyuzhuangtai", "1");
										maps.add(map);
										i = 1;
										break;
									}
								}
							}
							if (juJueRens != null && juJueRens.size() > 0) {
								for (Map<String, Object> jjr : juJueRens) {
									if (xueSheng.getXueshengid().toString().equals(jjr.get("juJueRenID").toString())
											&& jjr.get("leiXing").toString().equals("0")) {
										map.put("xueshengid", xueSheng.getXueshengid().toString());
										map.put("xuehao", xueSheng.getXuehao());
										map.put("xingming", xueSheng.getXingming());
										map.put("canyuzhuangtai", "2");
										if (jjr.containsKey("liYou") && !(jjr.get("liYou").toString().equals(""))
												&& null != jjr.get("liYou").toString()) {
											map.put("liyou", jjr.get("liYou").toString());
										} else {
											map.put("liyou", "");
										}
										maps.add(map);
										i = 1;
										break;
									}
								}
							}
							if (i == 0) {
								map.put("xueshengid", xueSheng.getXueshengid().toString());
								map.put("xuehao", xueSheng.getXuehao());
								map.put("xingming", xueSheng.getXingming());
								map.put("canyuzhuangtai", "1");
								maps.add(map);
							}
						}
					}

					/**
					 * String canyurens[] = code.split(";"); String sign = "0,"
					 * + xueshengid + ",1"; String sign1 = "0," + xueshengid +
					 * ",0"; String sign2 = "0," + xueshengid + ",2";
					 * mpp.put("student", maps); mpp.put("daoyuan", maps2); int
					 * i = 0; for (String string : canyurens) { String string2 =
					 * string.substring(0, string.indexOf(",",
					 * string.indexOf(",") + 1) + 2);
					 * System.out.println(string2); if (string2.equals(sign) ||
					 * string2.equals(sign1) || string2.equals(sign2)) { i = 1;
					 * } } // if (i == 0) { // return
					 * JSONObject.fromObject(mpp); // } for (String string :
					 * canyurens) { String canyr[] = string.split(",");
					 * Map<String, String> map = new HashMap<>(); if
					 * (canyr[0].equals("0")) {// 参与人为学生 XueSheng xueSheng =
					 * xueshengService.getUserById(Integer.parseInt(canyr[1]));
					 * map.put("xueshengid", canyr[1]); map.put("xuehao",
					 * xueSheng.getXuehao()); map.put("xingming",
					 * xueSheng.getXingming()); map.put("canyuzhuangtai",
					 * canyr[2]); if (canyr[2].equals("2")) { if (canyr.length
					 * >= 4) { String liyou = ""; for (int j = 3; j <
					 * canyr.length; j++) { liyou += canyr[j]; }
					 * map.put("liyou", liyou); } else { map.put("liyou", ""); }
					 * 
					 * } maps.add(map); } if (canyr[0].equals("1")) { YongHu
					 * fudaoyuan =
					 * yonghuService.selectYongHuByID(Integer.parseInt(canyr[1])
					 * ); map.put("fudaoyuanid", canyr[1]); map.put("xingming",
					 * fudaoyuan.getYonghuxingming()); map.put("canyuzhuangtai",
					 * canyr[2]); maps2.add(map); } }
					 */
					mpp.put("student", maps);
					mpp.put("daoyuan", maps2);
					return JSONObject.fromObject(mpp);
				}
			}
		}
		return null;
	}

	// 查看课程参与人
	@RequestMapping(value = "app_CanYuRenByid_kecheng")
	@ResponseBody
	public List<Map<String, String>> app_CanYuRenByid_kecheng(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String xueshengid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			List<Map<String, String>> maps = new ArrayList<>();
			String canyurenid[] = code.split(";");
			int i = 0;
			for (String string : canyurenid) {
				if (string.equals(xueshengid + ",1")) {
					i = 1;
					break;
				}
			}
			if (i == 0) {
				return maps;
			}
			for (String string : canyurenid) {
				Map<String, String> map = new HashMap<>();
				String cyr[] = string.split(",");
				XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(cyr[0]));
				String banjimingcheng = banjiService.selectByPrimaryKey(xueSheng.getBanjiid()).getBanjimingcheng();
				map.put("xueshengid", xueSheng.getXueshengid().toString());
				map.put("banji", banjimingcheng);
				map.put("xuehao", xueSheng.getXuehao());
				map.put("xingming", xueSheng.getXingming());
				map.put("zhuangtai", cyr[1]);
				maps.add(map);
			}
			return maps;
		} else {
			return null;
		}
	}

	// 保存新增活动
	@RequestMapping(value = "app_SaveHuoDong")
	@ResponseBody
	public String app_SaveHuoDong(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String xingming = request.getParameter("xingming");
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		String mingcheng = request.getParameter("huodongmingcheng");
		String didian = request.getParameter("huodongdidian");
		String shuoming = request.getParameter("huodongbeizhu");
		String tianjiaren = request.getParameter("zijixueshengid");
		String canyuren = request.getParameter("canyuren") + "0," + request.getParameter("ziji") + ",1;";
		String riqi = request.getParameter("huodongriqi");
		String kaishishijian = request.getParameter("kaishishijian");
		String jieshushijian = request.getParameter("jieshushijian");
		String canyu[] = canyuren.split(";");
		String newStr = "";
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = tianjiaren + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(tianjiaren));
			Date date1 = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String datetime = format.format(date1);
			String time = riqi + " " + jieshushijian;
			int res = datetime.compareTo(time);
			if (res > 0) {
				return "timeout";
			}
			for (int i = 0; i < canyu.length; i++) {
				if (newStr.indexOf(canyu[i]) == -1) {
					newStr += canyu[i] + ";";
				}
			}
			map.put("huodongmingcheng", mingcheng);
			map.put("didian", didian);
			map.put("shuoming", shuoming);
			map.put("tianjiarenid", tianjiaren);
			map.put("tianjiarenleixing", "0");
			// map.put("canyuren", newStr);
			map.put("riqi", riqi);
			map.put("kaishishijian", kaishishijian);
			map.put("jieshushijian", jieshushijian);
			map.put("leixing", "2");
			int i = huodongService.insert(map);
			if (i != 0) {
				String ren[] = newStr.split(";");
				for (String string : ren) {
					String s[] = string.split(",");
					if (!tianjiaren.equals(s[1])) {
						Map<String, String> m = new HashMap<>();
						m.put("huodongid", map.get("huodongid").toString());
						m.put("renid", s[1]);
						m.put("renleixing", "0");
						huodongService.insert_huodongren(m);
					}
				}

				// 发送提醒消息
				TiXing tiXing = new TiXing();
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				Date date = new Date();
				tiXing.setNeirong(xingming + "邀请你参加活动#" + mingcheng + "#");
				tiXing.setShijian(date);
				tiXing.setZhuangtai(0);
				tiXing.setType(2);
				tiXing.setShujuid(Integer.parseInt(map.get("huodongid").toString()));
				xiaoXiFaSong.setXiaoXiMingCheng("新加活动");
				xiaoXiFaSong.setXiaoXiNeiRong(xingming + "邀请你参加活动#" + mingcheng + "#");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(map.get("huodongid").toString()));
				xiaoXiFaSong.setShuJuLeiXing(2);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid()));
				String canyurens[] = canyuren.split(";");
				for (String string : canyurens) {
					String cyr[] = string.split(",");
					tiXing.setJieshourenid(Integer.parseInt(cyr[1]));
					xiaoXiFaSong.setFaSongMuBiao(cyr[1]);
					tixingService.insert(tiXing);
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				}
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	// 修改活动先通过huodongid返回这条活动信息
	@RequestMapping(value = "app_XiuGaiHuoDong")
	@ResponseBody
	public HuoDong app_XiuGaiHuoDong(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xueshengid = request.getParameter("xueshengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(code));
			if (huoDong == null) {
				return new HuoDong();
			}
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("huodongid", code);
			String cyr = "";
			if (huoDong.getLeixing() == 1) {
				List<String> banJiIDs = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(code));
				for (String string2 : banJiIDs) {
					List<XueSheng> xueshengs = xueshengService.getAllByBanJiID(Integer.parseInt(string2));
					for (XueSheng xueSheng : xueshengs) {
						cyr += xueSheng.getXueshengid() + ",";
					}
				}
			}
			if (huoDong.getLeixing() == 2) {
				List<Map<String, Object>> rens = huodongService
						.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(paramMap);
				for (Map<String, Object> map : rens) {
					cyr += map.get("yaoQingRenID").toString() + ",";
				}
			}
			if (huoDong.getTianjiarenid().toString().equals(xueshengid)
					&& huoDong.getTianjiarenleixing().toString().equals("0")) {
				List<String> banJiIDs = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(code));
				XueSheng xueSheng = xueshengService.selectByPrimaryKey(huoDong.getTianjiarenid());
				int n = 0;
				if (banJiIDs != null && banJiIDs.size() > 0) {
					for (String s : banJiIDs) {
						if (s.equals(xueSheng.getBanjiid().toString())) {
							n = 1;
						}
					}
				}
				if (n == 0) {
					cyr += huoDong.getTianjiarenid() + ",";
				}
			}
			huoDong.setCanyuren(cyr);
			return huoDong;
		} else {
			return null;
		}
	}

	// 保存修改后的活动
	@RequestMapping(value = "app_SaveUpdateHuoDong")
	@ResponseBody
	public String app_SaveUpdateHuoDong(HttpServletRequest request) throws ParseException {
		String xingming = request.getParameter("xingming");
		String xuexiaoXuehao = request.getParameter("xuexiaoXuehao");
		String huodongid = request.getParameter("huodongid");
		String mingcheng = request.getParameter("huodongmingcheng");
		String didian = request.getParameter("huodongdidian");
		String shuoming = request.getParameter("huodongbeizhu");
		String tianjiaren = request.getParameter("zijixueshengid");
		String canyuren = request.getParameter("canyuren");
		String riqi = request.getParameter("huodongriqi");
		String kaishishijian = request.getParameter("kaishishijian");
		String jieshushijian = request.getParameter("jieshushijian");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = tianjiaren + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("huodongid", huodongid);
			Date date1 = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String datetime = format.format(date1);
			String time = riqi + " " + jieshushijian;
			int res = datetime.compareTo(time);
			if (res > 0) {
				return "timeout";
			}
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(tianjiaren));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			HuoDong huoDong = new HuoDong();
			huoDong.setHuodongid(Integer.parseInt(huodongid));
			huoDong.setHuodongmingcheng(mingcheng);
			huoDong.setDidian(didian);
			huoDong.setShuoming(shuoming);
			huoDong.setTianjiarenid(Integer.parseInt(tianjiaren));
			huoDong.setTianjiarenleixing(0);
			huoDong.setLeixing(2);
			// huoDong.setCanyuren(newStr);
			try {
				huoDong.setRiqi(df.parse(riqi));
			} catch (Exception e) {
				// TODO: handle exception
			}
			huoDong.setKaishishijian(kaishishijian);
			huoDong.setJieshushijian(jieshushijian);
			int i = huodongService.updateByPrimaryKey(huoDong);
			if (i != 0) {
				List<String> banjis = huodongService.getAllBanJiIDByHuoDongID(Integer.parseInt(huodongid));
				if (banjis != null && banjis.size() > 0) {
					huodongService.delete_huodongbanji(paramMap);
				}
				String canyu[] = canyuren.split(",");
				List<String> newStr = new ArrayList<>();
				for (int m = 0; m < canyu.length; m++) {
					if (!newStr.contains(canyu[m])) {
						newStr.add(canyu[m]);
					}
				}
				List<Map<String, Object>> canYuRens = huodongService
						.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
				List<Map<String, Object>> juJueRens = huodongService
						.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
				List<Map<String, Object>> yaoQingRens = huodongService
						.getAllYaoQingRenByHuoDongIDAndRenIDAndRenLeiXing(paramMap);
				for (Map<String, Object> map : canYuRens) {
					int k = 0;
					for (String s : newStr) {
						if (map.get("canYuRenID").toString().equals(s)) {
							k = 1;
							break;
						}
					}
					if (k == 0) {
						paramMap.put("renid", map.get("canYuRenID").toString());
						paramMap.put("renleixing", "0");
						huodongService.delete_huodongcanyuren(paramMap);
					}
				}
				for (Map<String, Object> map : juJueRens) {
					int k = 0;
					for (String s : newStr) {
						if (map.get("juJueRenID").toString().equals(s)) {
							k = 1;
							break;
						}
					}
					if (k == 0) {
						paramMap.put("renid", map.get("juJueRenID").toString());
						paramMap.put("renleixing", "0");
						huodongService.delete_huodongjujueren(paramMap);
					}
				}
				for (Map<String, Object> map : yaoQingRens) {
					int k = 0;
					for (String s : newStr) {
						if (map.get("yaoQingRenID").toString().equals(s)) {
							k = 1;
							break;
						}
					}
					if (k == 0) {
						paramMap.put("renid", map.get("yaoQingRenID").toString());
						paramMap.put("renleixing", "0");
						huodongService.delete_huodongren(paramMap);
					}
				}
				for (String s : newStr) {
					if (s != null && !"".equals(s)) {
						int k = 0;
						for (Map<String, Object> map : yaoQingRens) {
							if (map.get("yaoQingRenID").toString().equals(s)) {
								k = 1;
								break;
							}
						}
						if (k == 0) {
							if (!s.equals(tianjiaren)) {
								paramMap.put("renid", s);
								paramMap.put("renleixing", "0");
								huodongService.insert_huodongren(paramMap);
							}
						}
					}
				}

				// 发送提醒消息
				TiXing tiXing = new TiXing();
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				Date date = new Date();
				tiXing.setNeirong(xingming + "修改了活动#" + mingcheng + "#");
				tiXing.setShijian(date);
				tiXing.setZhuangtai(0);
				tiXing.setType(2);
				tiXing.setShujuid(Integer.parseInt(huodongid));
				xiaoXiFaSong.setXiaoXiMingCheng("修改活动");
				xiaoXiFaSong.setXiaoXiNeiRong(xingming + "修改了活动#" + mingcheng + "#");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(huodongid));
				xiaoXiFaSong.setShuJuLeiXing(2);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid()));
				// String canyurens1[] = canyuren.split(";");
				for (String string : canyu) {
					// String cyr[] = string.split(",");
					tiXing.setJieshourenid(Integer.parseInt(string));
					xiaoXiFaSong.setFaSongMuBiao(string);
					tixingService.insert(tiXing);
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				}
				return "success";
			} else {
				return "fail";
			}
		} else {
			return null;
		}
	}

	// 待确认日程显示
	@RequestMapping(value = "app_DaiQueRenRiCheng")
	@ResponseBody
	public List<HuoDong> app_DaiQueRenRiCheng(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String data[] = code.split(",zytech,");
		String strings = data[0] + "," + status;
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			String canyuren1 = "0," + data[0] + ",0";
			String canyuren2 = "0," + data[0] + ",2";
			String canyuren3 = "%;0," + data[0] + ",0;%";
			String canyuren4 = "%;0," + data[0] + ",2;%";
			List<HuoDong> huodongs = huodongService.getAllBycanYuRen2(canyuren1, canyuren2, canyuren3, canyuren4);
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

				String canyuren[] = huoDong.getCanyuren().split(";");
				for (String string : canyuren) {
					String canyurenid[] = string.split(",");
					if (canyurenid[1].equals(data[0]) && canyurenid[0].equals("0")) {
						if (canyurenid[2].equals("0")) {
							huoDong.setZhuangtai(0);
						}
						if (canyurenid[2].equals("1")) {
							huoDong.setZhuangtai(1);
						}
						if (canyurenid[2].equals("2")) {
							huoDong.setZhuangtai(2);
						}
					}
				}
			}
			return huodongs;
		} else {
			return null;
		}
	}

	// 参加待确认活动
	@RequestMapping(value = "app_ChanJiaHuoDong")
	@ResponseBody
	public String app_ChanJiaHuoDong(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String data[] = code.split(",zytech,");
		String string = data[1] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Map<String, String> map = new HashMap<>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(data[0]));
			if (huoDong == null) {
				return "huodongnull";
			}
			String datetime = format.format(date);
			String time = huoDong.getRiqi() + " " + huoDong.getJieshushijian();
			int res = datetime.compareTo(time);
			if (res <= 0) {
				int i = 0;
				map.put("huodongid", data[0]);
				map.put("renid", data[1]);
				map.put("renleixing", "0");
				List<Map<String, Object>> juJueRens = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				if (canYuRens != null && canYuRens.size() > 0) {
					try {
						huodongService.delete_huodongcanyuren(map);
						if (juJueRens != null && juJueRens.size() > 0) {
							huodongService.delete_huodongjujueren(map);
						}
						huodongService.insert_huodongcanyuren(map);
						i = 1;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						if (juJueRens != null && juJueRens.size() > 0) {
							huodongService.delete_huodongjujueren(map);
						}
						huodongService.insert_huodongcanyuren(map);
						i = 1;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				/**
				 * String canyuren =
				 * huodongService.getcanYuRenByhuoDongID(Integer.parseInt(data[0
				 * ])); String newcanyuren = ""; String canyurens[] =
				 * canyuren.split(";"); int k = 0; for (int i = 0; i <
				 * canyurens.length; i++) { String xuesheng[] =
				 * canyurens[i].split(","); if (xuesheng[1].equals(data[1])) {
				 * xuesheng[2] = "1"; canyurens[i] = xuesheng[0] + "," +
				 * xuesheng[1] + "," + xuesheng[2]; k = 1; } canyurens[i] =
				 * canyurens[i]; newcanyuren += canyurens[i] + ";"; } if (k ==
				 * 0) { newcanyuren += "0," + data[1] + ",1;"; }
				 * map.put("huodongid", data[0]); map.put("canyuren",
				 * newcanyuren);
				 * huodongService.updateByPrimaryKeySelective(map);
				 */
				if (i != 0) {
					return "success";
				}
			} else {
				return "timeup";
			}
		}
		return null;
	}

	// 拒绝参加活动
	@RequestMapping(value = "app_JuJueHuoDong")
	@ResponseBody
	public String app_JuJueHuoDong(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String data[] = code.split(",zytech,");
		String liyou = request.getParameter("liyou");
		String string = data[1] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng user = xueshengService.selectByPrimaryKey(Integer.parseInt(data[1]));
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(data[0]));
			if (huoDong == null) {
				return null;
			}
			String datetime = format.format(date);
			String time = huoDong.getRiqi() + " " + huoDong.getJieshushijian();
			int res = datetime.compareTo(time);
			if (res <= 0) {
				if (liyou == "") {
					liyou = "无";
				}
				int i = 0;
				map.put("huodongid", data[0]);
				map.put("renid", data[1]);
				map.put("renleixing", "0");
				List<Map<String, Object>> juJueRens = huodongService.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				List<Map<String, Object>> canYuRens = huodongService.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(map);
				if (canYuRens != null && canYuRens.size() > 0) {
					try {
						for (Map<String, Object> map2 : canYuRens) {
							huodongService.delete_huodongcanyuren(map);
						}
						if (juJueRens != null && juJueRens.size() > 0) {
							huodongService.delete_huodongjujueren(map);
						}
						map.put("liyou", liyou);
						huodongService.insert_huodongjujueren(map);
						i = 1;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						if (juJueRens != null && juJueRens.size() > 0) {
							huodongService.delete_huodongjujueren(map);
						}
						map.put("liyou", liyou);
						huodongService.insert_huodongjujueren(map);
						i = 1;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				/**
				 * String canyuren =
				 * huodongService.getcanYuRenByhuoDongID(Integer.parseInt(data[0
				 * ])); String newcanyuren = ""; String canyurens[] =
				 * canyuren.split(";"); int sign = 0; for (int i = 0; i <
				 * canyurens.length; i++) { String xuesheng[] =
				 * canyurens[i].split(","); if (xuesheng[1].equals(data[1])) {
				 * xuesheng[2] = "2"; sign = 1; canyurens[i] = xuesheng[0] + ","
				 * + xuesheng[1] + "," + xuesheng[2] + "," + liyou; }
				 * canyurens[i] = canyurens[i]; newcanyuren += canyurens[i] +
				 * ";"; } if (sign == 0) { newcanyuren += "0," + data[1] + ",2,"
				 * + liyou + ";"; }
				 * 
				 * map.put("huodongid", data[0]); map.put("canyuren",
				 * newcanyuren); int i =
				 * huodongService.updateByPrimaryKeySelective(map);
				 */
				if (i != 0) {
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					tiXing.setNeirong(user.getXingming() + "拒绝了活动#" + huoDong.getHuodongmingcheng() + "#");
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					tiXing.setType(2);
					tiXing.setShujuid(Integer.parseInt(data[0]));
					xiaoXiFaSong.setXiaoXiMingCheng("活动");
					xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "拒绝了活动#" + huoDong.getHuodongmingcheng() + "#");
					xiaoXiFaSong.setShuJuId(Integer.parseInt(data[0]));
					xiaoXiFaSong.setShuJuLeiXing(2);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(banjiService.findXueXiaoIDByBanJiID(user.getBanjiid()));
					tiXing.setJieshourenid(huoDong.getTianjiarenid());
					xiaoXiFaSong.setFaSongMuBiao(huoDong.getTianjiarenid().toString());
					tixingService.insert(tiXing);
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);

					return "success";
				} else {
					return null;
				}
			} else {
				return "timeup";
			}
		} else {
			return null;
		}
	}

	// 彻底从活动中清除自己
	@RequestMapping(value = "app_CleanHuoDong")
	@ResponseBody
	public String app_CleanHuoDong(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String data[] = code.split(",zytech,");
		String string = data[1] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			HuoDong huoDong = huodongService.selectByPrimaryKey(Integer.parseInt(data[0]));
			if (huoDong == null) {
				return "fail1";
			}
			String canyuren = huodongService.getcanYuRenByhuoDongID(Integer.parseInt(data[0]));
			if (canyuren == null) {
				return "fail2";
			}
			String newcanyuren = "";
			String canyurens[] = canyuren.split(";");
			for (int i = 0, k = 0; i < canyurens.length; i++) {
				String xuesheng[] = canyurens[i].split(",");
				if (!xuesheng[1].equals(data[1])) {
					newcanyuren += canyurens[k] + ";";
					k++;
				}
			}
			map.put("huodongid", data[0]);
			map.put("canyuren", newcanyuren);
			huodongService.updateByPrimaryKeySelective(map);
			return "success";
		} else {
			return null;
		}
	}

	// 我的备忘录
	@RequestMapping(value = "app_s_mybeiwanglu")
	@ResponseBody
	public List<BeiWL> app_s_mybeiwanglu(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = code + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			if (code != null && code != "") {
				String banjiid = xueshengService.selectByPrimaryKey(Integer.parseInt(code)).getBanjiid().toString();
				// Map<String, Object> map3 = new HashMap<>();
				// String jieshouren1 = "%;" + code + ",_;%";
				// String jieshouren2 = code + ",_;%";
				// map3.put("userid", code);
				// map3.put("leixing", 0);
				// map3.put("jieshouren1", jieshouren1);
				// map3.put("jieshouren2", jieshouren2);
				// map3.put("banjiid1", banjiid + ",%");
				// map3.put("banjiid2", "%," + banjiid + ",%");
				// List<BeiWL> beiWLs =
				// beiwlService.getAllByuserIDAndLeiXingOrJieShouRen(map3);
				Map<String, String> map = new HashMap<>();
				map.put("renid", code);
				map.put("renleixing", "0");
				map.put("banjiid", banjiid);
				List<BeiWL> beiWLs = beiwlService.getBeiWLByRenIDAndRenLeiXingAndRiQi(map);
				for (BeiWL beiWL : beiWLs) {
					YongHu yongHu = yonghuService.selectYongHuByID(Integer.parseInt(beiWL.getUserid()));
					if (yongHu != null) {
						beiWL.setUsername(yongHu.getYonghuxingming());
					} else {
						XueSheng xueSheng = xueshengService.getUserById(Integer.parseInt(beiWL.getUserid()));
						beiWL.setUsername(xueSheng.getXingming());
					}
					if (beiWL.getHuizhi() != null && beiWL.getHuizhi() == 1) {
						beiWL.setZhuangtai("0");
						map.put("beiwlid", beiWL.getId().toString());
						List<Map<String, Object>> huiZhis = beiwlService
								.getBeiWLHuiZhiByBeiWLIDAndRenIDAndRenLeiXing(map);
						if (huiZhis != null && huiZhis.size() > 0) {
							for (Map<String, Object> map2 : huiZhis) {
								if (map2.get("huiZhiRenID").toString().equals(code)) {
									beiWL.setZhuangtai(map2.get("zhuangTai").toString());
								}
							}
						}

						/**
						 * if (beiWL.getLeixing() == 1) { String jieshourens[] =
						 * beiWL.getJieshouren().split(";"); for (String
						 * jieshourenn : jieshourens) { String jieshou[] =
						 * jieshourenn.split(","); if (jieshou[0].equals(code))
						 * { beiWL.setZhuangtai(jieshou[1]); } } } if
						 * (beiWL.getLeixing() == 2) { if (null ==
						 * beiWL.getBanjiids() ||
						 * "".equals(beiWL.getBanjiids())) { String
						 * jieshourens[] = beiWL.getJieshouren().split(";"); for
						 * (String jieshouren : jieshourens) { String jieshou[]
						 * = jieshouren.split(":"); String sid[] =
						 * jieshou[1].split("!"); for (String string2 : sid) {
						 * String[] s = string2.split(","); if
						 * (s[0].equals(code)) { beiWL.setZhuangtai(s[1]); } } }
						 * } else { if (null != beiWL.getJieshouren() &&
						 * !("".equals(beiWL.getJieshouren()))) { String
						 * jieshourens[] = beiWL.getJieshouren().split(";"); int
						 * i = 0; for (String jieshouren : jieshourens) {
						 * String[] s = jieshouren.split(","); if
						 * (s[0].equals(code)) { beiWL.setZhuangtai(s[1]); i =
						 * 1; break; } } if (i == 0) { beiWL.setZhuangtai("0");
						 * } } else { beiWL.setZhuangtai("0"); } } }
						 */
					}
				}
				return beiWLs;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// 删除这条备忘录
	@RequestMapping(value = "app_s_deletebeiwanglu")
	@ResponseBody
	public String app_s_deletebeiwanglu(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String retinfo = "";
		String data[] = code.split(",zytech,");
		String string = data[0] + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			if (data[1] != null && data[1] != "") {
				XueSheng user = xueshengService.selectByPrimaryKey(Integer.parseInt(data[0]));
				BeiWL beiWLs = beiwlService.selectByPrimaryKey(Integer.parseInt(data[1]));
				if (beiWLs.getUserid().equals(data[0])) {
					int i = 0;
					try {
						Map<String, String> map = new HashMap<>();
						map.put("beiwlid", data[1]);
						beiwlService.delete_beiwanglubanji(map);
						beiwlService.delete_beiwangluren(map);
						beiwlService.delete_beiwlhuizhi(map);
						beiwlService.deleteByPrimaryKey(Integer.parseInt(data[1]));
						i = 1;
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (i > 0) {
						Date date = new Date();
						// 发送提醒消息
						TiXing tiXing = new TiXing();
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						tiXing.setNeirong(user.getXingming() + "删除了事件$" + beiWLs.getNeirong() + "$");
						tiXing.setShijian(date);
						tiXing.setZhuangtai(0);
						tiXing.setType(3);
						tiXing.setShujuid(Integer.parseInt(data[1]));
						xiaoXiFaSong.setXiaoXiMingCheng("删除事件");
						xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "删除了事件$" + beiWLs.getNeirong() + "$");
						xiaoXiFaSong.setShuJuId(Integer.parseInt(data[1]));
						xiaoXiFaSong.setShuJuLeiXing(3);
						xiaoXiFaSong.setFaSongLeiXing(1);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(banjiService.findXueXiaoIDByBanJiID(user.getBanjiid()));
						tiXing.setJieshourenid(Integer.parseInt(beiWLs.getUserid()));
						xiaoXiFaSong.setFaSongMuBiao(beiWLs.getUserid());
						tixingService.insert(tiXing);
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);

						retinfo = "success";
					} else {
						retinfo = "fail";
					}
				} else {
					retinfo = "fail";
				}
			} else {
				retinfo = "fail";
			}
			return retinfo;
		} else {
			return null;
		}
	}

	// 修改备忘录
	@RequestMapping(value = "app_s_xianshibeiwanglu")
	@ResponseBody
	public JSONObject app_s_xianshibeiwanglu(HttpServletRequest request) {
		String code = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xueshengid = request.getParameter("xueshengid");
		String string = xueshengid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Map<String, String> map = new HashMap<String, String>();
			BeiWL beiWL = beiwlService.selectByPrimaryKey(Integer.parseInt(code));
			map.put("id", beiWL.getId().toString());
			map.put("neirong", beiWL.getNeirong());
			// map.put("didian", beiWL.getDidian());
			map.put("userid", beiWL.getUserid());
			String datetime = beiWL.getShijian();
			String date = datetime.substring(0, datetime.indexOf(" "));
			String time = datetime.substring(datetime.indexOf(" ") + 1, datetime.length());
			map.put("date", date);
			map.put("time", time);
			// System.out.println(map);
			return JSONObject.fromObject(map);
		} else {
			return null;
		}
	}

	// 保存修改后的备忘录
	@RequestMapping(value = "app_s_saveXiuGaibeiwanglu")
	@ResponseBody
	public String app_s_saveXiuGaibeiwanglu(String beiwangid, String studentid, String beiwangneirong,
			String beiwangdidian, String xueshengid, String beiwangriqi, String beiwangshijian,
			HttpServletRequest request) throws IOException {
		String retInfo = "";
		BeiWL beiWL = new BeiWL();
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			if (studentid.equals(xueshengid)) {
				XueSheng user = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid));
				String datetime = beiwangriqi + " " + beiwangshijian;
				datetime = beiwangriqi + " " + beiwangshijian;
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				String nowtime = format.format(date);
				int res = nowtime.compareTo(datetime);
				if (res > 0) {
					return "timeout";
				}
				beiWL.setId(Integer.parseInt(beiwangid));
				beiWL.setNeirong(beiwangneirong);
				// beiWL.setDidian(beiwangdidian);
				beiWL.setShijian(datetime);
				beiWL.setUserid(xueshengid);
				beiWL.setLeixing(0);
				int i = beiwlService.updateByPrimaryKey(beiWL);
				if (i > 0) {
					// 发送提醒消息
					TiXing tiXing = new TiXing();
					XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
					tiXing.setNeirong(user.getXingming() + "修改了事件$" + beiWL.getNeirong() + "$");
					tiXing.setShijian(date);
					tiXing.setZhuangtai(0);
					tiXing.setType(3);
					tiXing.setShujuid(Integer.parseInt(beiwangid));
					xiaoXiFaSong.setXiaoXiMingCheng("修改事件");
					xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "修改了事件$" + beiWL.getNeirong() + "$");
					xiaoXiFaSong.setShuJuId(Integer.parseInt(beiwangid));
					xiaoXiFaSong.setShuJuLeiXing(3);
					xiaoXiFaSong.setFaSongLeiXing(1);
					xiaoXiFaSong.setShiFouChengGong(0);
					xiaoXiFaSong.setXueXiaoId(banjiService.findXueXiaoIDByBanJiID(user.getBanjiid()));
					tiXing.setJieshourenid(Integer.parseInt(beiWL.getUserid()));
					xiaoXiFaSong.setFaSongMuBiao(beiWL.getUserid());
					tixingService.insert(tiXing);
					jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					retInfo = "success";
				} else {
					retInfo = "fail";
				}
			} else {
				retInfo = "fail";
			}
			return retInfo;
		} else {
			return null;
		}
	}

	// 保存新增的备忘录
	@RequestMapping(value = "app_s_saveXinZengbeiwanglu")
	@ResponseBody
	public String app_s_saveXinZengbeiwanglu(String beiwangid, String studentid, String beiwangneirong,
			String beiwangdidian, String xueshengid, String beiwangriqi, String beiwangshijian,
			HttpServletRequest request) throws IOException {
		String retInfo = "";
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng user = xueshengService.selectByPrimaryKey(Integer.parseInt(studentid));
			Map<String, String> map = new HashMap<String, String>();
			String datetime = "";
			datetime = beiwangriqi + " " + beiwangshijian;
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String nowtime = format.format(date);
			int res = nowtime.compareTo(datetime);
			if (res > 0) {
				return "timeout";
			}
			map.put("neirong", beiwangneirong);
			map.put("didian", beiwangdidian);
			map.put("shijian", datetime);
			map.put("userid", studentid);
			map.put("leixing", "0");
			int i = beiwlService.insert(map);
			if (i > 0) {
				// 发送提醒消息
				TiXing tiXing = new TiXing();
				XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
				tiXing.setNeirong(user.getXingming() + "新增了事件$" + beiwangneirong + "$");
				tiXing.setShijian(date);
				tiXing.setZhuangtai(0);
				tiXing.setType(3);
				tiXing.setShujuid(Integer.parseInt(map.get("id")));
				xiaoXiFaSong.setXiaoXiMingCheng("新增事件");
				xiaoXiFaSong.setXiaoXiNeiRong(user.getXingming() + "新增了事件$" + beiwangneirong + "$");
				xiaoXiFaSong.setShuJuId(Integer.parseInt(map.get("id")));
				xiaoXiFaSong.setShuJuLeiXing(3);
				xiaoXiFaSong.setFaSongLeiXing(1);
				xiaoXiFaSong.setShiFouChengGong(0);
				xiaoXiFaSong.setXueXiaoId(banjiService.findXueXiaoIDByBanJiID(user.getBanjiid()));
				tiXing.setJieshourenid(Integer.parseInt(studentid));
				xiaoXiFaSong.setFaSongMuBiao(studentid);
				tixingService.insert(tiXing);
				jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
				retInfo = "success";
			} else {
				retInfo = "fail";
			}
			return retInfo;
		} else {
			return null;
		}
	}

	// 查看历史活动记录
	@RequestMapping(value = "app_HistoryHuoDongList")
	@ResponseBody
	public Object app_HistoryHuoDongList(HttpServletRequest request) {
		String xueshengid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String strings = xueshengid + "," + status;
		Map<String, Object> map = new HashMap<>();
		String str = Util.SHA1Encode(strings);
		if (str.equals(token)) {
			String banjiid = xueshengService.selectByPrimaryKey(Integer.parseInt(xueshengid)).getBanjiid().toString();
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("renid", xueshengid);
			paramMap.put("renleixing", "0");
			paramMap.put("banjiid", banjiid);
			// int count = huodongService.getCountHistoryHuoDong(paramMap);
			int count = huodongService.getCountByRenIDAndRenLeiXing(paramMap);
			int pageSize = 10;
			int pages = (count / pageSize) + 1;
			if (request.getParameter("page") == null || request.getParameter("page") == "") {
				paramMap.put("start", "0");
				paramMap.put("stop", "10");
				// List<HuoDong> huoDongs =
				// huodongService.getHistoryHuoDongBycanYuRen(map);
				List<HuoDong> huoDongs = huodongService.getAllByRenIDAndRenLeiXing(paramMap);
				for (HuoDong huoDong : huoDongs) {
					paramMap.put("huodongid", huoDong.getHuodongid().toString());
					List<Map<String, Object>> canYunRens = huodongService
							.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
					List<Map<String, Object>> juJueRens = huodongService
							.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
					if (huoDong.getTianjiarenid().toString().equals(xueshengid)) {
						if (juJueRens != null && juJueRens.size() > 0) {
							huoDong.setZhuangtai(2);
						} else {
							huoDong.setZhuangtai(1);
						}
					} else {
						if (juJueRens != null && juJueRens.size() > 0) {
							huoDong.setZhuangtai(2);
						} else if (canYunRens != null && canYunRens.size() > 0) {
							huoDong.setZhuangtai(1);
						} else {
							huoDong.setZhuangtai(0);
						}
					}
					paramMap.put("yinyongid", huoDong.getHuodongid().toString());
					paramMap.put("beizhurenid", xueshengid);
					paramMap.put("leixing", "3");
					paramMap.put("status", status);
					List<BeiZhu> beiZhuS = beiZhuService
							.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(paramMap);
					if (beiZhuS != null && beiZhuS.size() > 0) {
						for (BeiZhu beiZhu : beiZhuS) {
							huoDong.setBeizhu(beiZhu.getBeizhuneirong());
						}
					} else {
						huoDong.setBeizhu("");
					}

					/**
					 * String canyuren[] = huoDong.getCanyuren().split(";"); int
					 * i = 0; for (String string : canyuren) { String
					 * canyurenid[] = string.split(","); if
					 * (canyurenid[1].equals(xueshengid) &&
					 * canyurenid[0].equals("0")) { if
					 * (canyurenid[2].equals("0")) { huoDong.setZhuangtai(0); i
					 * = 1; break; } if (canyurenid[2].equals("1")) {
					 * huoDong.setZhuangtai(1); i = 1; break; } if
					 * (canyurenid[2].equals("2")) { huoDong.setZhuangtai(2); i
					 * = 1; break; } } } if (i == 0) { huoDong.setZhuangtai(0);
					 * } String beizhuids = huoDong.getBeizhuids(); int ii = 0;
					 * if (!"".equals(beizhuids) && null != beizhuids) { String
					 * beizhuid[] = beizhuids.split(";"); for (String string :
					 * beizhuid) { String strs[] = string.split(","); if
					 * (strs[0].equals(xueshengid)) { BeiZhu beiZhu =
					 * beiZhuService.selectByPrimaryKey(Integer.parseInt(strs[1]
					 * )); if (beiZhu.getLeixing() == 3 && beiZhu.getYinyongid()
					 * == huoDong.getHuodongid() && beiZhu.getBeizhurenid() ==
					 * Integer.parseInt(xueshengid) &&
					 * beiZhu.getStatus().equals(status)) { ii =
					 * Integer.parseInt(strs[1]); } } } } if (ii != 0) { BeiZhu
					 * beiZhu = beiZhuService.selectByPrimaryKey(ii);
					 * huoDong.setBeizhuids(beiZhu.getBeizhuneirong()); // 备注内容
					 * } else { huoDong.setBeizhuids(""); }
					 */
				}
				map.put("huodongs", huoDongs);
				map.put("pages", pages);
				map.put("page", 1);
				return JSON.toJSON(map);
			} else {
				if (Util.isNumeric(request.getParameter("page"))) {
					int page = Integer.parseInt(request.getParameter("page"));
					if (page > 0 && page <= pages) {
						paramMap.put("start", ((page - 1) * 10) + "");
						paramMap.put("stop", "10");
						// List<HuoDong> huoDongs =
						// huodongService.getHistoryHuoDongBycanYuRen(map);
						List<HuoDong> huoDongs = huodongService.getAllByRenIDAndRenLeiXing(paramMap);
						for (HuoDong huoDong : huoDongs) {
							paramMap.put("huodongid", huoDong.getHuodongid().toString());
							List<Map<String, Object>> canYunRens = huodongService
									.getCanYuRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
							List<Map<String, Object>> juJueRens = huodongService
									.getJuJueRenByhuoDongIDAndRenIDAndRenLeiXing(paramMap);
							if (huoDong.getTianjiarenid().toString().equals(xueshengid)) {
								if (juJueRens != null && juJueRens.size() > 0) {
									huoDong.setZhuangtai(2);
								} else {
									huoDong.setZhuangtai(1);
								}
							} else {
								if (juJueRens != null && juJueRens.size() > 0) {
									huoDong.setZhuangtai(2);
								} else if (canYunRens != null && canYunRens.size() > 0) {
									huoDong.setZhuangtai(1);
								} else {
									huoDong.setZhuangtai(0);
								}
							}
							paramMap.put("yinyongid", huoDong.getHuodongid().toString());
							paramMap.put("beizhurenid", xueshengid);
							paramMap.put("leixing", "3");
							paramMap.put("status", status);
							List<BeiZhu> beiZhuS = beiZhuService
									.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(paramMap);
							if (beiZhuS != null && beiZhuS.size() > 0) {
								for (BeiZhu beiZhu : beiZhuS) {
									huoDong.setBeizhu(beiZhu.getBeizhuneirong());
								}
							} else {
								huoDong.setBeizhu("");
							}
							/**
							 * String canyuren[] =
							 * huoDong.getCanyuren().split(";"); int i = 0; for
							 * (String string : canyuren) { String canyurenid[]
							 * = string.split(","); if
							 * (canyurenid[1].equals(xueshengid) &&
							 * canyurenid[0].equals("0")) { if
							 * (canyurenid[2].equals("0")) {
							 * huoDong.setZhuangtai(0); i = 1; break; } if
							 * (canyurenid[2].equals("1")) {
							 * huoDong.setZhuangtai(1); i = 1; break; } if
							 * (canyurenid[2].equals("2")) {
							 * huoDong.setZhuangtai(2); i = 1; break; } } } if
							 * (i == 0) { huoDong.setZhuangtai(0); } String
							 * beizhuids = huoDong.getBeizhuids(); int ii = 0;
							 * if (!"".equals(beizhuids) && null != beizhuids) {
							 * String beizhuid[] = beizhuids.split(";"); for
							 * (String string : beizhuid) { String strs[] =
							 * string.split(","); if
							 * (strs[0].equals(xueshengid)) { BeiZhu beiZhu =
							 * beiZhuService.selectByPrimaryKey(Integer.parseInt
							 * (strs[1])); if (beiZhu.getLeixing() == 3 &&
							 * beiZhu.getYinyongid() == huoDong.getHuodongid()
							 * && beiZhu.getBeizhurenid() ==
							 * Integer.parseInt(xueshengid) &&
							 * beiZhu.getStatus().equals(status)) { ii =
							 * Integer.parseInt(strs[1]); } } } } if (ii != 0) {
							 * BeiZhu beiZhu =
							 * beiZhuService.selectByPrimaryKey(ii);
							 * huoDong.setBeizhuids(beiZhu.getBeizhuneirong());
							 * // 备注内容 } else { huoDong.setBeizhuids(""); }
							 */
						}
						map.put("huodongs", huoDongs);
						map.put("pages", pages);
						map.put("page", page);
						return JSON.toJSON(map);
					} else {
						map.put("huodongs", "");
						map.put("pages", "");
						map.put("page", "");
						return JSON.toJSON(map);
					}

				} else {
					map.put("huodongs", "");
					map.put("pages", "");
					map.put("page", "");
					return JSON.toJSON(map);
				}
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 根据 课程id 判断kecheng表中beizhuid 是否为空
	 * 
	 * 空 直接添加 不为空 根据beizhuid 判断类型 1 一门课程---kechengbeizhu 2
	 * 单节课程---cikechengbeizhu
	 * 
	 * 类型符合就修改，不符合就新增
	 * 
	 * @return
	 * @throws Exception
	 */

	// 新增或修改 单节课程 备注
	@RequestMapping(value = "app_savejiekechengbeizhu")
	@ResponseBody
	public String app_savejiekechengbeizhu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String retInfo = "";
		String studentid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(studentid));
			String id = request.getParameter("code");
			String zhouci = request.getParameter("zhouci");
			String kaishijieci = request.getParameter("kaishijieci");
			String jieshujieci = request.getParameter("jieshujieci");
			String riqi = request.getParameter("riqi");
			String jiekechengbeizhu = request.getParameter("jiekechengbeizhu");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> m = new HashMap<>();
//			String xuexiaoid = xs.getXuexiaoXuehao().split("_")[0];
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			m.put("xueXiaoID", xuexiaoid);
			m.put("riqi", riqi);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(m);
			if (xueQi == null) {
				retInfo = "false";
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
				Map<String, Integer> mapp = new HashMap<>();
				mapp.put("xuexiaoid", Integer.parseInt(xuexiaoid));
				mapp.put("zhuangtai", 1);
				JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(mapp);
				int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), kaishijieci);
				int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), jieshujieci);

				String tiaojian = zhou + "," + zhouci + "," + kaishijieciid + "," + jieshujieciid;
				KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
				m.put("yinyongid", id);
				m.put("beizhurenid", studentid);
				m.put("status", status);
				m.put("leixing", "2");
				m.put("tiaojian", tiaojian);
				List<BeiZhu> beiZhuS = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(m);
				if (beiZhuS == null || beiZhuS.size() <= 0) {
					m.put("beizhuneirong", jiekechengbeizhu);
					try {
						beiZhuService.insert(m);
						retInfo = "success";
					} catch (Exception e) {
						e.printStackTrace();
						retInfo = "false";
					}
				} else {
					try {
						for (BeiZhu beiZhu : beiZhuS) {
							beiZhu.setBeizhuneirong(jiekechengbeizhu);
							beiZhuService.updateByPrimaryKey(beiZhu);
						}
						retInfo = "success";
					} catch (Exception e) {
						e.printStackTrace();
						retInfo = "false";
					}
				}

				/**
				 * String beizhuid = kecheng.getBeizhuid(); if
				 * ("".equals(beizhuid) || null == beizhuid) { Map<String,
				 * String> map = new HashMap<>(); map.put("yinyongid", id);
				 * map.put("leixing", "2"); map.put("tiaojian", zhou + "," +
				 * zhouci + "," + kaishijieciid + "," + jieshujieciid);
				 * map.put("beizhuneirong", jiekechengbeizhu);
				 * map.put("beizhurenid", studentid); map.put("status",status);
				 * int i = beiZhuService.insert(map); if (i > 0) {
				 * kecheng.setBeizhuid(studentid + "," + map.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "success"; } } else { String beizhuxinxi[] =
				 * beizhuid.split(";"); String ids = ""; String beizhus = "";
				 * for (String string2 : beizhuxinxi) { String xinxi[] =
				 * string2.split(","); if (xinxi[0].equals(studentid)) { ids +=
				 * xinxi[1] + ","; } else { beizhus += string2 + ";"; } } if
				 * ("".equals(ids) || null == ids) { Map<String, String> map =
				 * new HashMap<>(); map.put("yinyongid", id); map.put("leixing",
				 * "2"); map.put("tiaojian", zhou + "," + zhouci + "," +
				 * kaishijieciid + "," + jieshujieciid);
				 * map.put("beizhuneirong", jiekechengbeizhu);
				 * map.put("beizhurenid", studentid); map.put("status",status);
				 * int i = beiZhuService.insert(map); beizhus += studentid + ","
				 * + map.get("id") + ";"; if (i > 0) {
				 * kecheng.setBeizhuid(beizhus);
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "success"; } } else { String strs[] = ids.split(","); String
				 * jiexiangqing = zhou + "," + zhouci + "," + kaishijieciid +
				 * "," + jieshujieciid; int i = 0; for (String string2 : strs) {
				 * BeiZhu keChengBeiZhu = beiZhuService
				 * .selectByPrimaryKey(Integer.parseInt(string2)); if
				 * (keChengBeiZhu.getTiaojian().equals(jiexiangqing) &&
				 * keChengBeiZhu.getLeixing() == 2 &&
				 * keChengBeiZhu.getBeizhurenid() == Integer.parseInt(studentid)
				 * && keChengBeiZhu.getYinyongid() == Integer.parseInt(id) &&
				 * keChengBeiZhu.getStatus().equals(status)) { i =
				 * Integer.parseInt(string2); } else { beizhus += studentid +
				 * "," + string2 + ";"; } } if (i == 0) { Map<String, String>
				 * map = new HashMap<>(); map.put("yinyongid", id);
				 * map.put("leixing", "2"); map.put("tiaojian", zhou + "," +
				 * zhouci + "," + kaishijieciid + "," + jieshujieciid);
				 * map.put("beizhuneirong", jiekechengbeizhu);
				 * map.put("beizhurenid", studentid); map.put("status",status);
				 * int j = beiZhuService.insert(map); beizhus += studentid + ","
				 * + map.get("id") + ";"; if (j > 0) {
				 * kecheng.setBeizhuid(beizhus);
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "success"; } } else { BeiZhu keChengBeiZhu =
				 * beiZhuService.selectByPrimaryKey(i);
				 * keChengBeiZhu.setBeizhuneirong(jiekechengbeizhu); int j =
				 * beiZhuService.updateByPrimaryKey(keChengBeiZhu); if (j > 0) {
				 * retInfo = "success"; } } } }
				 */
			}
		}
		return retInfo;
	}

	// 新增或修改 一门课程 备注
	@RequestMapping(value = "app_savekechengbeizhu")
	@ResponseBody
	public String app_savekechengbeizhu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String retInfo = "";
		String studentid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(studentid));
			String id = request.getParameter("code");
			String kechengbeizhu = request.getParameter("kechengbeizhu");
			KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
			Map<String, String> map = new HashMap<>();
			map.put("yinyongid", id);
			map.put("beizhurenid", studentid);
			map.put("status", status);
			map.put("leixing", "1");
			List<BeiZhu> beiZhuS = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(map);
			// String beizhuid = kecheng.getBeizhuid();
			if (beiZhuS.size() <= 0 || null == beiZhuS) {
				map.put("beizhuneirong", kechengbeizhu);
				int i = beiZhuService.insert(map);
				if (i > 0) {
					retInfo = "success";
				}
			} else {
				try {
					for (BeiZhu bz : beiZhuS) {
						bz.setBeizhuneirong(kechengbeizhu);
						beiZhuService.updateByPrimaryKey(bz);
					}
					retInfo = "success";
				} catch (Exception e) {
					e.printStackTrace();
				}
				// String beizhuxinxi[] = beizhuid.split(";");
				// String ids = "";
				// String beizhus = "";
				// for (String string2 : beizhuxinxi) {
				// String xinxi[] = string2.split(",");
				// if (xinxi[0].equals(studentid)) {
				// ids += xinxi[1] + ",";
				// } else {
				// beizhus += string2 + ";";
				// }
				// }
				// if ("".equals(ids) || null == ids) {
				// Map<String, String> map = new HashMap<>();
				// map.put("yinyongid", id);
				// map.put("leixing", "1");
				// map.put("beizhuneirong", kechengbeizhu);
				// map.put("beizhurenid", studentid);
				// map.put("status",status);
				// int i = beiZhuService.insert(map);
				// beizhus += studentid + "," + map.get("id") + ";";
				// if (i > 0) {
				// kecheng.setBeizhuid(beizhus);
				// kechengService.updateKeChengBeiZhuID(kecheng);
				// retInfo = "success";
				// }
				// } else {
				// String strs[] = ids.split(",");
				// int i = 0;
				// for (String string2 : strs) {
				// BeiZhu keChengBeiZhu = beiZhuService
				// .selectByPrimaryKey(Integer.parseInt(string2));
				// if (keChengBeiZhu.getLeixing() == 1 &&
				// keChengBeiZhu.getYinyongid() == Integer.parseInt(id)
				// && keChengBeiZhu.getBeizhurenid() ==
				// Integer.parseInt(studentid) &&
				// keChengBeiZhu.getStatus().equals(status)) {
				// i = Integer.parseInt(string2);
				// } else {
				// beizhus += studentid + "," + string2 + ";";
				// }
				// }
				// if (i == 0) {
				// Map<String, String> map = new HashMap<>();
				// map.put("yinyongid", id);
				// map.put("leixing", "1");
				// map.put("beizhuneirong", kechengbeizhu);
				// map.put("beizhurenid", studentid);
				// map.put("status",status);
				// int j = beiZhuService.insert(map);
				// beizhus += studentid + "," + map.get("id") + ";";
				// if (j > 0) {
				// kecheng.setBeizhuid(beizhus);
				// kechengService.updateKeChengBeiZhuID(kecheng);
				// retInfo = "success";
				// }
				// } else {
				// BeiZhu keChengBeiZhu = beiZhuService.selectByPrimaryKey(i);
				// keChengBeiZhu.setBeizhuneirong(kechengbeizhu);
				// int j = beiZhuService.updateByPrimaryKey(keChengBeiZhu);
				// if (j > 0) {
				// retInfo = "success";
				// }
				// }
				// }
			}
		}
		return retInfo;

	}

	// 新增或修改 单节课程/一门课程 备注
	@RequestMapping(value = "app_savedoublekechengbeizhu")
	@ResponseBody
	public String app_savedoublekechengbeizhu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String retInfo = "";
		String studentid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(studentid));
			String id = request.getParameter("code");
			String zhouci = request.getParameter("zhouci");
			String kaishijieci = request.getParameter("kaishijieci");
			String jieshujieci = request.getParameter("jieshujieci");
			String riqi = request.getParameter("riqi");
			String jiekechengbeizhu = request.getParameter("jiekechengbeizhu");
			String kechengbeizhu = request.getParameter("kechengbeizhu");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> m = new HashMap<>();
//			String xuexiaoid = xs.getXuexiaoXuehao().split("_")[0];
			String xuexiaoid = banjiService.findXueXiaoIDByBanJiID(xs.getBanjiid());
			m.put("xueXiaoID", xuexiaoid);
			m.put("riqi", riqi);
			XueQi xueQi = xueqiService.getByxueXiaoIDandriQi(m);
			if (xueQi == null) {
				retInfo = "false";
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

				Map<String, Integer> mapp = new HashMap<>();
				mapp.put("xuexiaoid", Integer.parseInt(xuexiaoid));
				mapp.put("zhuangtai", 1);
				JieCiFangAn jieCiFangAn = jieCiFangAnService.selectByxueXiaoIDAndZhuangTai(mapp);
				int kaishijieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), kaishijieci);
				int jieshujieciid = jcsjService.findJCSJIDbyFangAnIdandJcsj(jieCiFangAn.getId(), jieshujieci);

				KeCheng kecheng = kechengService.selectByPrimaryKey(Integer.parseInt(id));
				m.put("yinyongid", id);
				m.put("beizhurenid", studentid);
				m.put("status", status);
				List<BeiZhu> beiZhuS = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(m);
				if (beiZhuS == null || beiZhuS.size() <= 0) {
					Map<String, String> map = new HashMap<>();
					map.put("yinyongid", id);
					map.put("leixing", "2");
					map.put("tiaojian", zhou + "," + zhouci + "," + kaishijieciid + "," + jieshujieciid);
					map.put("beizhuneirong", jiekechengbeizhu);
					map.put("beizhurenid", studentid);
					map.put("status", status);
					int i = beiZhuService.insert(map);
					Map<String, String> map2 = new HashMap<>();
					map2.put("yinyongid", id);
					map2.put("leixing", "1");
					map2.put("beizhuneirong", kechengbeizhu);
					map2.put("beizhurenid", studentid);
					map2.put("status", status);
					int j = beiZhuService.insert(map2);
					if (i > 0 && j > 0) {
						retInfo = "success";
					} else if (i > 0 && j == 0) {
						retInfo = "successjiekecheng";
					} else if (i == 0 && j > 0) {
						retInfo = "successkecheng";
					}
				} else {
					int i = 0, j = 0;
					for (BeiZhu beiZhu : beiZhuS) {
						if (beiZhu.getLeixing() == 1) {
							try {
								beiZhu.setBeizhuneirong(kechengbeizhu);
								j = beiZhuService.updateByPrimaryKey(beiZhu);
								// retInfo = "success";
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (beiZhu.getLeixing() == 2) {
							String tiaojian = zhou + "," + zhouci + "," + kaishijieciid + "," + jieshujieciid;
							if (beiZhu.getTiaojian().equals(tiaojian)) {
								try {
									beiZhu.setBeizhuneirong(jiekechengbeizhu);
									i = beiZhuService.updateByPrimaryKey(beiZhu);
									// retInfo = "success";
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					if (i > 0 && j > 0) {
						retInfo = "success";
					} else if (i > 0 && j == 0) {
						retInfo = "successjiekecheng";
					} else if (i == 0 && j > 0) {
						retInfo = "successkecheng";
					}
				}
				/**
				 * String beizhuid = kecheng.getBeizhuid(); if
				 * ("".equals(beizhuid) || null == beizhuid) { Map<String,
				 * String> map = new HashMap<>(); map.put("yinyongid", id);
				 * map.put("leixing", "2"); map.put("tiaojian", zhou + "," +
				 * zhouci + "," + kaishijieciid + "," + jieshujieciid);
				 * map.put("beizhuneirong", jiekechengbeizhu);
				 * map.put("beizhurenid", studentid); map.put("status",status);
				 * int i = beiZhuService.insert(map); Map<String, String> map2 =
				 * new HashMap<>(); map2.put("yinyongid", id);
				 * map2.put("leixing", "1"); map2.put("beizhuneirong",
				 * kechengbeizhu); map2.put("beizhurenid", studentid);
				 * map2.put("status",status); int j =
				 * beiZhuService.insert(map2); if (i > 0 && j > 0) {
				 * kecheng.setBeizhuid( studentid + "," + map.get("id") + ";" +
				 * studentid + "," + map2.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "success"; } else if (i > 0 && j == 0) {
				 * kecheng.setBeizhuid(studentid + "," + map.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "successjiekecheng"; } else if (i == 0 && j > 0) {
				 * kecheng.setBeizhuid(studentid + "," + map2.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "successkecheng"; } } else { String beizhuxinxi[] =
				 * beizhuid.split(";"); String ids = ""; String beizhus = "";
				 * for (String string2 : beizhuxinxi) { String xinxi[] =
				 * string2.split(","); if (xinxi[0].equals(studentid)) { ids +=
				 * xinxi[1] + ","; } else { beizhus += string2 + ";"; } } if
				 * ("".equals(ids) || null == ids) { Map<String, String> map =
				 * new HashMap<>(); map.put("yinyongid", id); map.put("leixing",
				 * "2"); map.put("tiaojian", zhou + "," + zhouci + "," +
				 * kaishijieciid + "," + jieshujieciid);
				 * map.put("beizhuneirong", jiekechengbeizhu);
				 * map.put("beizhurenid", studentid); map.put("status",status);
				 * int i = beiZhuService.insert(map); Map<String, String> map2 =
				 * new HashMap<>(); map2.put("yinyongid", id);
				 * map2.put("leixing", "1"); map2.put("beizhuneirong",
				 * kechengbeizhu); map2.put("beizhurenid", studentid);
				 * map2.put("status",status); int j =
				 * beiZhuService.insert(map2); if (i > 0 && j > 0) {
				 * kecheng.setBeizhuid(beizhus + studentid + "," + map.get("id")
				 * + ";" + studentid + "," + map2.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "success"; } else if (i > 0 && j == 0) {
				 * kecheng.setBeizhuid(beizhus + studentid + "," + map.get("id")
				 * + ";"); kechengService.updateKeChengBeiZhuID(kecheng);
				 * retInfo = "successjiekecheng"; } else if (i == 0 && j > 0) {
				 * kecheng.setBeizhuid(beizhus + studentid + "," +
				 * map2.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "successkecheng"; } } else { String strs[] = ids.split(",");
				 * String jiexiangqing = zhou + "," + zhouci + "," +
				 * kaishijieciid + "," + jieshujieciid; int i = 0; int k = 0;
				 * for (String string2 : strs) { BeiZhu keChengBeiZhu =
				 * beiZhuService .selectByPrimaryKey(Integer.parseInt(string2));
				 * if (keChengBeiZhu.getLeixing() == 2) { if
				 * (keChengBeiZhu.getBeizhurenid() ==
				 * Integer.parseInt(studentid) && keChengBeiZhu.getYinyongid()
				 * == Integer.parseInt(id) &&
				 * keChengBeiZhu.getTiaojian().equals(jiexiangqing)) { i =
				 * Integer.parseInt(string2); } else { beizhus += studentid +
				 * "," + string2 + ";"; } } else if (keChengBeiZhu.getLeixing()
				 * == 1) { if (keChengBeiZhu.getYinyongid() ==
				 * Integer.parseInt(id) && keChengBeiZhu.getBeizhurenid() ==
				 * Integer.parseInt(studentid) &&
				 * keChengBeiZhu.getStatus().equals(status)) { k =
				 * Integer.parseInt(string2); } else { beizhus += studentid +
				 * "," + string2 + ";"; } } } if (i == 0 && k == 0) {
				 * Map<String, String> map = new HashMap<>();
				 * map.put("yinyongid", id); map.put("leixing", "2");
				 * map.put("tiaojian", zhou + "," + zhouci + "," + kaishijieciid
				 * + "," + jieshujieciid); map.put("beizhuneirong",
				 * jiekechengbeizhu); map.put("beizhurenid", studentid);
				 * map.put("status",status); int n = beiZhuService.insert(map);
				 * Map<String, String> map2 = new HashMap<>();
				 * map2.put("yinyongid", id); map2.put("leixing", "1");
				 * map2.put("beizhuneirong", kechengbeizhu);
				 * map2.put("beizhurenid", studentid);
				 * map2.put("status",status); int j =
				 * beiZhuService.insert(map2); if (n > 0 && j > 0) {
				 * kecheng.setBeizhuid(beizhus + studentid + "," + map.get("id")
				 * + ";" + studentid + "," + map2.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "success"; } else if (n > 0 && j == 0) {
				 * kecheng.setBeizhuid(beizhus + studentid + "," + map.get("id")
				 * + ";"); kechengService.updateKeChengBeiZhuID(kecheng);
				 * retInfo = "successjiekecheng"; } else if (n == 0 && j > 0) {
				 * kecheng.setBeizhuid(beizhus + studentid + "," +
				 * map2.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "successkecheng"; } } if (i != 0 && k == 0) { BeiZhu
				 * keChengBeiZhu = beiZhuService.selectByPrimaryKey(i);
				 * keChengBeiZhu.setBeizhuneirong(jiekechengbeizhu); int j =
				 * beiZhuService.updateByPrimaryKey(keChengBeiZhu); Map<String,
				 * String> map2 = new HashMap<>(); map2.put("yinyongid", id);
				 * map2.put("leixing", "1"); map2.put("beizhuneirong",
				 * kechengbeizhu); map2.put("beizhurenid", studentid);
				 * map2.put("status",status); int n =
				 * beiZhuService.insert(map2); if (j > 0 && n > 0) {
				 * kecheng.setBeizhuid(beizhus+";"+studentid+","+keChengBeiZhu.
				 * getId()+";" + studentid + "," + map2.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "success"; } else if (j > 0 && n == 0) { retInfo =
				 * "successjiekecheng"; } else if (j == 0 && n > 0) {
				 * kecheng.setBeizhuid(beizhus + studentid + "," +
				 * map2.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "successkecheng"; } } if (i == 0 && k != 0) { BeiZhu
				 * keChengBeiZhu = beiZhuService.selectByPrimaryKey(k);
				 * keChengBeiZhu.setBeizhuneirong(kechengbeizhu); int j =
				 * beiZhuService.updateByPrimaryKey(keChengBeiZhu); Map<String,
				 * String> map = new HashMap<>(); map.put("yinyongid", id);
				 * map.put("leixing", "2"); map.put("tiaojian", zhou + "," +
				 * zhouci + "," + kaishijieciid + "," + jieshujieciid);
				 * map.put("beizhuneirong", jiekechengbeizhu);
				 * map.put("beizhurenid", studentid); map.put("status",status);
				 * int n = beiZhuService.insert(map); if (j > 0 && n > 0) {
				 * kecheng.setBeizhuid(beizhus+";"+studentid+","+keChengBeiZhu.
				 * getId()+";" + studentid + "," + map.get("id") + ";");
				 * kechengService.updateKeChengBeiZhuID(kecheng); retInfo =
				 * "success"; } else if (j > 0 && n == 0) { retInfo =
				 * "successkecheng"; } else if (j == 0 && n > 0) {
				 * kecheng.setBeizhuid(beizhus + studentid + "," + map.get("id")
				 * + ";"); kechengService.updateKeChengBeiZhuID(kecheng);
				 * retInfo = "successjiekecheng"; } } if (i != 0 && k != 0) {
				 * BeiZhu keChengBeiZhu = beiZhuService.selectByPrimaryKey(k);
				 * keChengBeiZhu.setBeizhuneirong(kechengbeizhu); int j =
				 * beiZhuService.updateByPrimaryKey(keChengBeiZhu);
				 * 
				 * BeiZhu keChengBeiZhu2 = beiZhuService.selectByPrimaryKey(i);
				 * keChengBeiZhu2.setBeizhuneirong(jiekechengbeizhu); int n =
				 * beiZhuService.updateByPrimaryKey(keChengBeiZhu2);
				 * 
				 * if (j > 0 && n > 0) { retInfo = "success"; } if (j > 0 && n
				 * == 0) { retInfo = "successkecheng"; } if (j == 0 && n > 0) {
				 * retInfo = "successjiekecheng"; } } } }
				 */
			}
		}
		return retInfo;
	}

	@RequestMapping(value = "app_savehuodongbeizhu")
	@ResponseBody
	public String app_savehuodongbeizhu(HttpServletRequest request, HttpServletResponse response) {
		String retInfo = "";
		String studentid = request.getParameter("xueshengid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = studentid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			XueSheng xs = xueshengService.selectByPrimaryKey(Integer.parseInt(studentid));
			String id = request.getParameter("id");
			String neirong = request.getParameter("neirong");
			HuoDong huodong = huodongService.selectByPrimaryKey(Integer.parseInt(id));
			if (huodong != null) {
				Map<String, String> map = new HashMap<>();
				map.put("yinyongid", id);
				map.put("leixing", "3");
				map.put("beizhurenid", studentid);
				map.put("status", status);
				List<BeiZhu> beiZhus = beiZhuService.getBeiZhuByYinYongIDAndLeiXingAndBeiZhuRenIDAndStatus(map);
				if (beiZhus == null || beiZhus.size() <= 0) {
					map.put("beizhuneirong", neirong);
					try {
						beiZhuService.insert(map);
						retInfo = "success";
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						for (BeiZhu beiZhu : beiZhus) {
							beiZhu.setBeizhuneirong(neirong);
							beiZhuService.updateByPrimaryKey(beiZhu);
						}
						retInfo = "success";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			/**
			 * String beizhuid = huodong.getBeizhuids(); if ("".equals(beizhuid)
			 * || null == beizhuid) { Map<String, String> map = new HashMap<>();
			 * map.put("yinyongid", id); map.put("leixing", "3");
			 * map.put("beizhuneirong", neirong); map.put("beizhurenid",
			 * studentid); map.put("status",status); int i =
			 * beiZhuService.insert(map); if (i > 0) {
			 * huodong.setBeizhuids(studentid + "," + map.get("id") + ";");
			 * huodongService.updateBeiZhuByID(huodong); retInfo = "success"; }
			 * } else { String beizhuxinxi[] = beizhuid.split(";"); String ids =
			 * ""; String beizhus = ""; for (String string2 : beizhuxinxi) {
			 * String xinxi[] = string2.split(","); if
			 * (xinxi[0].equals(studentid)) { ids += xinxi[1] + ","; } else {
			 * beizhus += string2 + ";"; } } if ("".equals(ids) || null == ids)
			 * { Map<String, String> map = new HashMap<>(); map.put("yinyongid",
			 * id); map.put("leixing", "3"); map.put("beizhuneirong", neirong);
			 * map.put("beizhurenid", studentid); map.put("status",status); int
			 * i = beiZhuService.insert(map); beizhus += studentid + "," +
			 * map.get("id") + ";"; if (i > 0) { huodong.setBeizhuids(beizhus);
			 * huodongService.updateBeiZhuByID(huodong); retInfo = "success"; }
			 * } else { String strs[] = ids.split(","); int i = 0; for (String
			 * string2 : strs) { BeiZhu beiZhu = beiZhuService
			 * .selectByPrimaryKey(Integer.parseInt(string2)); if
			 * (beiZhu.getLeixing() == 3 && beiZhu.getYinyongid() ==
			 * Integer.parseInt(id) && beiZhu.getBeizhurenid() ==
			 * Integer.parseInt(studentid) && beiZhu.getStatus().equals(status))
			 * { i = Integer.parseInt(string2); } else { beizhus += studentid +
			 * "," + string2 + ";"; } } if (i == 0) { Map<String, String> map =
			 * new HashMap<>(); map.put("yinyongid", id); map.put("leixing",
			 * "3"); map.put("beizhuneirong", neirong); map.put("beizhurenid",
			 * studentid); map.put("status",status); int j =
			 * beiZhuService.insert(map); beizhus += studentid + "," +
			 * map.get("id") + ";"; if (j > 0) { huodong.setBeizhuids(beizhus);
			 * huodongService.updateBeiZhuByID(huodong); retInfo = "success"; }
			 * } else { BeiZhu beiZhu = beiZhuService.selectByPrimaryKey(i);
			 * beiZhu.setBeizhuneirong(neirong); int j =
			 * beiZhuService.updateByPrimaryKey(beiZhu); if (j > 0) { retInfo =
			 * "success"; } } } }
			 */
		}
		return retInfo;
	}
}
