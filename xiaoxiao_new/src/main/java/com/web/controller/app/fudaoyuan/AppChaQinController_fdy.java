package com.web.controller.app.fudaoyuan;

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

import com.alibaba.fastjson.JSON;
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
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import net.sf.json.JSONObject;

@Controller
public class AppChaQinController_fdy {
	@Autowired
	private FuDaoYuanService fuDaoYuanService;
	@Autowired
	private ChaQinService chaQinService;
	@Autowired
	private YongHuService yonghuService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private YuanXiService yuanXiService;
	@Autowired
	private BanJiService banJiService;
	@Autowired
	private JiGuangService jiGuangService;
	@Autowired
	private XueQiService xueQiService;
	@Autowired
	private QingjiaService qingJiaService;
	@Autowired
	private SuSheLouService suSheLouService;
	@Autowired
	private XueShengSuSheService suSheService;
	@Autowired
	private NianFenService nianFenService;

	@ResponseBody
	@RequestMapping(value = "app_addchaqin")
	public JSONObject app_addChaQin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String yonghuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			JSONObject json = new JSONObject();
			YongHu user = yonghuService.selectYongHuByID(Integer.parseInt(yonghuid));
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
			Map<String, String> map = new HashMap<>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
			XueQi xueQi = new XueQi();
			xueQi = xueQiService.getByxueXiaoIDandriQi(map);
			json.put("now", format.format(date));
			if (null != xueQi) {
				json.put("end", format.parse(xueQi.getJieshuriqi()));
			}
			return json;
		}
		return null;
	}

	@RequestMapping(value = "app_savechaqinanpai")
	@ResponseBody
	public JSONObject app_chaqinanpai(HttpServletRequest request) throws IOException, Exception {
		JSONObject json = new JSONObject();
		String retInfo= "";
		String yonghuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(Integer.parseInt(yonghuid));
			YongHu user = yonghuService.selectYongHuByID(Integer.parseInt(yonghuid));
			String leiXing = request.getParameter("pl");
			YuanXi yuanXi = yuanXiService.selectByPrimaryKey(user.getYuanxiid());
			Map<String, String> map = new HashMap<>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			map.put("riqi", format.format(date));
			map.put("xueXiaoID", yuanXi.getXuexiaoid().toString());
			XueQi xueQi = new XueQi();
			xueQi = xueQiService.getByxueXiaoIDandriQi(map);
			if (xueQi == null) {
				retInfo = "wuxueqi";
				json.put("retInfo", retInfo);
				return json;
			}
			Date jieShuRiQi = format.parse(xueQi.getJieshuriqi());
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(jieShuRiQi);
			calendar2.add(Calendar.DAY_OF_MONTH, 1);
			SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
			if (leiXing.equals("0")) { // 单次
				ChaQinAnPai chaQinAnPai = new ChaQinAnPai();
				chaQinAnPai.setJiaoshiid(Integer.parseInt(yonghuid));
				chaQinAnPai.setRiqi(format.parse(request.getParameter("oncetime")));
				chaQinAnPai.setKaishishijian(format2.parse(request.getParameter("kaishishijian")));
				chaQinAnPai.setJieshushijian(format2.parse(request.getParameter("jiezhishijian")));
				chaQinAnPai.setPaizhaoyaoqiu(request.getParameter("yaoqiu"));
				chaQinAnPai.setZhuangtai(1);
				int i = chaQinService.insertChaQinAnPai(chaQinAnPai);
				if (i > 0) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String nowTime = sdf.format(new Date());
					String[] banJiIds = fuDaoYuan.getBanjiid().split(",");
					List<String> banJis = new ArrayList<>();
					for (String string2 : banJiIds) {
						BanJi bj = banJiService.selectByPrimaryKey(Integer.parseInt(string2));
						Integer ruXueNianFen = nianFenService.selectByPrimaryKey(bj.getRuxuenianfenid())
								.getRuxuenianfen();
						String biYeNianFen = (ruXueNianFen + bj.getLeixing()) + "-09-01";
						if (sdf.parse(biYeNianFen).getTime() > sdf.parse(nowTime).getTime()) {
							banJis.add(string2);
						}
					}
					for (int j = 0; j < banJis.size(); j++) {
						XiaoXiFaSong xiaoXiFaSong = new XiaoXiFaSong();
						xiaoXiFaSong.setXiaoXiMingCheng("辅导员发布了新的查寝安排！");
						xiaoXiFaSong.setXiaoXiNeiRong("辅导员发布了新的查寝安排，请按时上传照片！");
						xiaoXiFaSong.setShuJuId(chaQinAnPai.getAnpaiid());
						xiaoXiFaSong.setShuJuLeiXing(5);
						xiaoXiFaSong.setFaSongMuBiao(banJis.get(j));
						xiaoXiFaSong.setFaSongLeiXing(2);
						xiaoXiFaSong.setShiFouChengGong(0);
						xiaoXiFaSong.setXueXiaoId(yuanXi.getXuexiaoid().toString());
						jiGuangService.insertXiaoXiFaSong(xiaoXiFaSong);
					}
					retInfo = "success";
					json.put("retInfo", retInfo);
					return json;
				}else {
					return json;
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
					anPai.setJiaoshiid(Integer.parseInt(yonghuid));
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
				Boolean bool = chaQinService.insertAppChaQinAnPaiList(anPais, user);
				if (bool) {
					retInfo = "success";
				} else {
					retInfo = "fail";
				}
				json.put("retInfo", retInfo);
				return json;
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
					chaQinAnPai.setJiaoshiid(Integer.parseInt(yonghuid));
					chaQinAnPai.setKaishishijian(format2.parse(request.getParameter("kaishishijian")));
					chaQinAnPai.setJieshushijian(format2.parse(request.getParameter("jiezhishijian")));
					chaQinAnPai.setPaizhaoyaoqiu(request.getParameter("yaoqiu"));
					chaQinAnPai.setZhuangtai(1);
					chaQinAnPai.setRiqi(calendar.getTime());
					list.add(chaQinAnPai);
					calendar.add(Calendar.DAY_OF_MONTH, 7);
				}
				Boolean bool = chaQinService.insertAppChaQinAnPaiList(list, user);
				if (bool) {
					retInfo = "success";
				} else {
					retInfo = "fail";
				}
				json.put("retInfo", retInfo);
				return json;
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
					chaQinAnPai.setJiaoshiid(Integer.parseInt(yonghuid));
					chaQinAnPai.setKaishishijian(format2.parse(request.getParameter("kaishishijian")));
					chaQinAnPai.setJieshushijian(format2.parse(request.getParameter("jiezhishijian")));
					chaQinAnPai.setPaizhaoyaoqiu(request.getParameter("yaoqiu"));
					chaQinAnPai.setZhuangtai(1);
					chaQinAnPai.setRiqi(calendar.getTime());
					list.add(chaQinAnPai);
					System.out.println(calendar.getTime().toString());
					calendar.add(Calendar.MONTH, 1);
				}
				Boolean bool = chaQinService.insertAppChaQinAnPaiList(list, user);
				if (bool) {
					retInfo = "success";
				} else {
					retInfo = "fail";
				}
				json.put("retInfo", retInfo);
				return json;
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "app_chaqin")
	public List<ChaQinAnPai> app_chaqin(HttpServletRequest request) throws IOException {
		String yonghuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			Integer pageSize = (request.getParameter("pageSize") == null || "".equals(request.getParameter("pageSize")))
					? 0 : Integer.valueOf(request.getParameter("pageSize"));
			Integer pageNum = (request.getParameter("pageNum") == null || "".equals(request.getParameter("pageNum")))
					? 0 : Integer.valueOf(request.getParameter("pageNum"));
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("fudaoyuanid", yonghuid);
			paramMap.put("yonghuid", yonghuid);
			paramMap.put("start", String.valueOf(pageNum * pageSize));
			paramMap.put("stop", String.valueOf(pageSize));

			List<ChaQinAnPai> anPais = chaQinService.selectByFuDaoYuanId(paramMap);
			return anPais;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "app_chaqinanpai")
	public JSONObject chaqinanpai(HttpServletRequest request) throws Exception {
		String yonghuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			JSONObject json = new JSONObject();
			String id = request.getParameter("id");
			if (id == null || id.equals("")) {
				return null;
			}
			YongHu user = yonghuService.selectYongHuByID(Integer.parseInt(yonghuid));
			ChaQinAnPai anPai = chaQinService.selectChaQinAnPaiByPrimaryKey(Integer.parseInt(id));
			if (anPai == null || anPai.getJiaoshiid().equals(user.getYonghuid().toString())) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = sdf.format(new Date());
			FuDaoYuan fuDaoYuan = fuDaoYuanService.getByfuDaoYuanID(user.getYonghuid());
			String[] banjis = fuDaoYuan.getBanjiid().split(",");
			List<String> banJis = new ArrayList<>();
			for (String string2 : banjis) {
				BanJi bj = banJiService.selectByPrimaryKey(Integer.parseInt(string2));
				Integer ruXueNianFen = nianFenService.selectByPrimaryKey(bj.getRuxuenianfenid()).getRuxuenianfen();
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
				chaQinZhanShi.setSushemingcheng(suSheLouService.selectByPrimaryKey(suShe.getSuSheLouId()).getMingCheng()
						+ suShe.getMenPaiHao());
				chaQinJieGuos = new ArrayList<>();
				map.put("susheid", suSheIDs.get(i));
				chaQinJieGuos = chaQinService.selectChaQinJieGuoByanPaiIDAndsuSheID(map);
				if (chaQinJieGuos.isEmpty()) {
					chaQinZhanShi.setZhuangtai(0);
				} else {
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
							Qingjia qingjia = qingJiaService.getByZhuangTaiAndShijianAndXueShengid(paramMap);
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
			json.put("chaqin", chaQinZhanShis);
			return json;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "app_chaqinjilu")
	public JSONObject app_chaqinjilu(HttpServletRequest request) throws IOException {
		String yonghuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			JSONObject json = new JSONObject();
			YongHu user = yonghuService.selectYongHuByID(Integer.parseInt(yonghuid));
			String id = request.getParameter("id");
			ChaQinJieGuo chaQinJieGuo = chaQinService.selectByByPrimaryKey(Integer.parseInt(id));
			if (chaQinJieGuo == null) {
				return null;
			}
			ChaQinAnPai chaQinAnPai = chaQinService.selectChaQinAnPaiByPrimaryKey(chaQinJieGuo.getAnpaiid());
			if (!chaQinAnPai.getJiaoshiid().toString().equals(user.getYonghuid().toString())) {
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
			json.put("chaqinjieguo", chaQinJieGuo);
			json.put("xueshengs", list);
			json.put("shifoushenhe", b);
			return json;
		}
		return null;
	}

	@RequestMapping(value = "app_subchaqinjl")
	@ResponseBody
	public String app_subChaQinJiLu(HttpServletRequest request) throws Exception {
		String yonghuid = request.getParameter("CODE");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (str.equals(token)) {
			YongHu user = yonghuService.selectYongHuByID(Integer.parseInt(yonghuid));
			String id = request.getParameter("id");
			ChaQinJieGuo chaQinJieGuo = chaQinService.selectByByPrimaryKey(Integer.parseInt(id));
			if (chaQinJieGuo == null) {
				return "fail";
			}
			ChaQinAnPai chaQinAnPai = chaQinService.selectChaQinAnPaiByPrimaryKey(chaQinJieGuo.getAnpaiid());
			if (!chaQinAnPai.getJiaoshiid().toString().equals(user.getYonghuid().toString())) {
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
		return null;
	}

}
