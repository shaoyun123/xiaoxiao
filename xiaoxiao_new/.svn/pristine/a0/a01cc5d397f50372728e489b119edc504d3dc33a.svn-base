package com.web.controller.app.jiaoshi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.tools.ant.filters.LineContains.Contains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.tools.javac.resources.compiler;
import com.web.model.App_FenZu;
import com.web.model.App_KaoTiKu;
import com.web.model.App_ShiJianTi;
import com.web.model.JiaoShi;
import com.web.model.JiaoXueLou;
import com.web.model.KeCheng;
import com.web.model.XiaoQu;
import com.web.model.XueQi;
import com.web.model.XueSheng;
import com.web.model.YongHu;
import com.web.service.App_FenZuService;
import com.web.service.App_KaoPingChouQianService;
import com.web.service.App_KaoPingJieGuoService;
import com.web.service.App_KaoTiKuService;
import com.web.service.App_KeChengKaoPingService;
import com.web.service.App_ShiJianTiService;
import com.web.service.App_XueXiZuService;
import com.web.service.BanJiService;
import com.web.service.JiaoShiService;
import com.web.service.JiaoXueLouService;
import com.web.service.KeChengService;
import com.web.service.NianFenService;
import com.web.service.XiaoQuService;
import com.web.service.XueQiService;
import com.web.service.XueShengService;
import com.web.service.YongHuService;
import com.web.service.YuanXiService;
import com.web.util.Util;

import edu.emory.mathcs.backport.java.util.Arrays;
import net.sf.json.JSONObject;

@Controller
public class ShiXiController {
	@Autowired
	private KeChengService kechengService;
	@Autowired
	private JiaoShiService jiaoshiService;
	@Autowired
	private JiaoXueLouService jiaoXueLouService;
	@Autowired
	private XiaoQuService xiaoquService;
	@Autowired
	private YongHuService yongHuService;
	@Autowired
	private App_KaoTiKuService app_KaoTiKuService;
	@Autowired
	private App_ShiJianTiService app_ShiJianTiService;
	@Autowired
	private XueShengService xueShengService;
	@Autowired
	private App_FenZuService app_FenZuService;
	@Autowired
	private YuanXiService yuanxiService;
	@Autowired
	private XueQiService xueqiService;

	@ResponseBody
	@RequestMapping(value = "app_getshixilist")
	public JSONObject app_getshixilist(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String xuenian = request.getParameter("xuenian");
		String xueqi = request.getParameter("xueqi");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		YongHu user = yongHuService.selectYongHuByID(Integer.parseInt(yonghuid));
		List<Map<String, Object>> keChengs2 = new ArrayList<>();
		XueQi xueQi = new XueQi();
		Map<String, String> map = new HashMap<>();
		String xuexiaoid = yuanxiService.selectByPrimaryKey(user.getYuanxiid()).getXuexiaoid().toString();
		String nianfen = "";
		if(xuenian!=null &&! "".equals(xuenian)) {
			nianfen = xuenian.split("~")[0];
		}
		map.put("nianfen", nianfen);
		map.put("xuenian", xuenian);
		map.put("xueqi", xueqi);
		map.put("xuexiaoid", xuexiaoid);
		xueQi = xueqiService.getByXueXiaoIDAndXueNianAndXueQi(map);
		if(xueQi==null) {
			Date date = new Date();
			SimpleDateFormat riqi = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, String> maps = new HashMap<>();
			maps.put("riqi", riqi.format(date));
			maps.put("xueXiaoID", xuexiaoid);
			xueQi = xueqiService.getByxueXiaoIDandriQi(maps);
			if (xueQi == null) {
				List<XueQi> xueqis = xueqiService.getNewerXueQiByXueQi(map);
				if (xueqis != null && xueqis.size() > 0) {
					xueQi = xueqis.get(0);
				}
			}
		}
		String renkelaoshiid = user.getYonghuid().toString();
		map.put("xueqiid", xueQi.getXueqiid().toString());
		map.put("renkelaoshiid", renkelaoshiid);
		// 获得该老师的所有课程
		List<Map<String, Object>> keChengs = kechengService.getKeChengByRenKeLaoShiID(map);
		// 课程为空,获取参加的其他实习
		if (keChengs == null || keChengs.isEmpty()) {
			String idStr = "";
			getqitashixi(user, keChengs2, idStr,xueQi.getXueqiid().toString());
			quchong(keChengs2);
			List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xuexiaoid));
			jsonObject.put("xuenians", xuenians);
			jsonObject.put("kechengs", keChengs2);
			return jsonObject;
		}
		// 如果课程不为空
		for (int i = 0; i < keChengs.size(); i++) {
			// 获取某一门课程的实习课
			List<Map<String, Object>> shixi = kechengService
					.selectShiXiByKeChengID((Integer) keChengs.get(i).get("ID"));
			String idString = "";
			if (shixi != null && shixi.size() != 0) {
				for (int j = 0; j < shixi.size(); j++) {
					keChengs.get(i).put("shixiid", (Integer) shixi.get(j).get("ID"));
					JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey((Integer) keChengs.get(i).get("jiaoShiID"));
					JiaoXueLou jiaoxuelou = jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
					XiaoQu xiaoQu = xiaoquService.selectByPrimaryKey(jiaoxuelou.getXiaoQuId());
					keChengs.get(i).put("jiaoxuelouming", jiaoxuelou.getJiaoXueLouMing());
					keChengs.get(i).put("jiaoshiming", jiaoShi.getJiaoshiming());
					keChengs.get(i).put("xiaoquming", xiaoQu.getMingcheng());
					keChengs.get(i).put("shixixinxi", shixi.get(j));
					String renkelaoshidString[] = shixi.get(j).get("renKeLaoShiID").toString().split(";");
					getlaoshixinxi(keChengs, renkelaoshidString);
					// 获取所有某一门课程的所有实习id
					idString += shixi.get(j).get("ID").toString() + ",";
					// shixi不为空，获得其他实践课
					getqitashixi(user, keChengs2, idString,xueQi.getXueqiid().toString());
					try {
						if(shixi.get(j).get("sheZhi")==null) {
							keChengs2.add(keChengs.get(i));
							continue;
						}
						fenzuxinxi(shixi.get(j).get("sheZhi").toString(), keChengs.get(i));
					} catch (Exception e) {
						keChengs2.add(keChengs.get(i));
						e.printStackTrace();
						continue;
					}
					keChengs2.add(keChengs.get(i));
				}
			} else {
				// shixi为空，获取除了本次实习外的其它所有实习
				getqitashixi(user, keChengs2, idString,xueQi.getXueqiid().toString());
			}
		}
		quchong(keChengs2);
		List<String> xuenians = xueqiService.getXuenianByXuexiaoID(Integer.parseInt(xuexiaoid));
		jsonObject.put("xuenians", xuenians);
		jsonObject.put("kechengs", keChengs2);
		return jsonObject;
	}

	private void getqitashixi(YongHu user, List<Map<String, Object>> keChengs2, String idStr,String xueqiid) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		if (idStr == null || idStr.equals("")) {
			map2.put("id", idStr);
		} else {
			map2.put("id", Arrays.asList(idStr.split(",")));
		}
		// 获取除了本次实习外的其它所有实习
		List<Map<String, Object>> allshixi = (List<Map<String, Object>>) kechengService.getallshixi(map2);
		if (allshixi == null || allshixi.isEmpty()) {
			return;
		}
		for (int m = 0; m < allshixi.size(); m++) {
			if (allshixi.get(m).get("renKeLaoShiID") == null || "".equals(allshixi.get(m).get("renKeLaoShiID"))) {
				return;
			}
			String renkelaoshidString[] = allshixi.get(m).get("renKeLaoShiID").toString().split(";");
			String renkelaoshiids = "";
			for (String string2 : renkelaoshidString) {
				renkelaoshiids += "," + string2.split(",")[0];
			}
			// 获取老师参加的其他实习,该实习是其他老师创建的
			if ((renkelaoshiids + ",").contains("," + user.getYonghuid().toString() + ",")) {
				String kechengidString = allshixi.get(m).get("keChengID").toString();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("kechengid", Integer.parseInt(kechengidString));
//				KeCheng keCheng = kechengService.selectByPrimaryKey(Integer.parseInt(kechengidString));
//				paramMap.put("renkelaoshiid", Integer.parseInt(keCheng.getRenkelaoshiid()));
//				List<Map<String, Object>> kechengMap = kechengService.getKeChengAndShiJianByKeChengID(paramMap);
//				 获取课程和对应的时间
				List<Map<String, Object>> kechengMap = kechengService.getKeChengAndShiJianByKeChengID(paramMap);
				for (int j = 0; j < kechengMap.size(); j++) {
					if(!kechengMap.get(j).get("xueQiID").toString().equals(xueqiid)) {
						continue;
					}
//					List<Map<String, Object>> laoshiMap = new ArrayList<Map<String, Object>>();
					kechengMap.get(j).put("shixiid", (Integer) allshixi.get(m).get("ID"));
					JiaoShi jiaoShi = jiaoshiService.selectByPrimaryKey((Integer) kechengMap.get(j).get("jiaoShiID"));
					JiaoXueLou jiaoxuelou = jiaoXueLouService.selectByPrimaryKey(jiaoShi.getJiaoxuelouid());
					XiaoQu xiaoQu = xiaoquService.selectByPrimaryKey(jiaoxuelou.getXiaoQuId());
					kechengMap.get(j).put("jiaoxuelouming", jiaoxuelou.getJiaoXueLouMing());
					kechengMap.get(j).put("jiaoshiming", jiaoShi.getJiaoshiming());
					kechengMap.get(j).put("xiaoquming", xiaoQu.getMingcheng());
					kechengMap.get(j).put("shixixinxi", allshixi.get(m));
					getlaoshixinxi(kechengMap, renkelaoshidString);
					try {
						if (allshixi.get(m).get("sheZhi") == null) {
							keChengs2.add(kechengMap.get(j));
							continue;
						}
						fenzuxinxi(allshixi.get(m).get("sheZhi").toString(), kechengMap.get(j));
					} catch (Exception e) {
						keChengs2.add(kechengMap.get(j));
						e.printStackTrace();
						continue;
					}
					keChengs2.add(kechengMap.get(j));
				}
			}
		}
	}

	private void quchong(List<Map<String, Object>> list) {
//		for (int i = 0; i < list.size() - 1; i++) {
//			for (int j = list.size() - 1; j > i; j--) {
//				if (list.get(j).equals(list.get(i))) {
//					list.remove(j);
//				}
//			}
//		}
		HashSet<Map<String, Object>> h = new HashSet<Map<String, Object>>(list);
		list.clear();
		list.addAll(h);
	}

	private void getlaoshixinxi(List<Map<String, Object>> kechengMap, String[] renkelaoshidString) {
		for (int j = 0; j < kechengMap.size(); j++) {
			List<Map<String, Object>> laoshiMap = new ArrayList<Map<String, Object>>();
			for (String string2 : renkelaoshidString) {
				// 查询实习负责人
				if ((string2.split(",")[0]).equals(kechengMap.get(j).get("renKeLaoShiID").toString())) {
					YongHu fuzeren = yongHuService
							.selectYongHuByID(Integer.parseInt(kechengMap.get(j).get("renKeLaoShiID").toString()));
					if (fuzeren == null) {
						return;
					}
					Map<String, Object> fuzenrenMap = new HashMap<String, Object>();
					fuzenrenMap.put("fuzerenxingming", fuzeren.getYonghuxingming());
					fuzenrenMap.put("fuzerenid", fuzeren.getYonghuid());
					kechengMap.get(j).put("fuzeren", fuzenrenMap);
					continue;
				}
				// 查询其他参与老师姓名和id
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("laoshiid", string2.split(",")[0]);
				YongHu yongHu = yongHuService.selectYongHuByID(Integer.parseInt(string2.split(",")[0]));
				if (yongHu == null) {
					map.put("laoshixingming", "");
				} else {
					map.put("laoshixingming", yongHu.getYonghuxingming());
				}
				laoshiMap.add(map);
			}
			kechengMap.get(j).put("laoshixinxi", laoshiMap);
		}
	}
	private void fenzuxinxi(String shezhi,Map<String, Object> kecheng) {
		String[] fenzushezhi = shezhi.split(";");
		for (String string2 : fenzushezhi) {
			String[] strings = string2.split(",");
			switch (strings[0]) {
			case "1":
				kecheng.put("fenxiaozu", strings[1]);
				if (strings.length == 3) {
					kecheng.put("xiaozurongliang", strings[2]);
				}
				break;
			case "2":
				kecheng.put("fendazu", strings[1]);
				if (strings.length == 3) {
					kecheng.put("dazuzhangid", strings[2]);
					XueSheng xsSheng = xueShengService.selectByPrimaryKey(Integer.parseInt(strings[2]));
					if (xsSheng != null) {
						kecheng.put("dazuzhang", xsSheng.getXingming());
					}
				}
				break;
			case "4":
				kecheng.put("isyouti", strings[1]);
				if (strings.length == 3) {
					kecheng.put("fentileixing", strings[2]);
				}
				break;
			case "3":
				kecheng.put("shifoufenzu", strings[1]);
				if (strings.length == 3) {
					kecheng.put("fenzuleixing", strings[2]);
				}
				break;
			default:
				break;
			}
		}
	}
	

	@ResponseBody
	@RequestMapping(value = "app_shixishezhi")
	public JSONObject app_shixishezhi(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String leixing = request.getParameter("leixing");
		String shixiid = request.getParameter("shixiid");
		String renkelaoshiid = request.getParameter("renkelaoshiid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		Map<String, Object> shixiMap = kechengService.getshixi(Integer.parseInt(shixiid));
		String shezhi = shixiMap.get("sheZhi").toString();
		String newshezhi = "";
		if (shezhi == null || "".equals(shezhi)) {
			newshezhi += "3," + leixing + ";";
		} else {
			for (String string2 : shezhi.split(";")) {
				if ((string2.split(",")[0]).equals("3")) {
					string2 = "3," + leixing;
				}
				newshezhi += string2 + ";";
			}
		}
		shixiMap.put("shezhi", newshezhi);
		shixiMap.put("renkelaoshiid", renkelaoshiid);
		int i = kechengService.updateshixi(shixiMap);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_getkaoti")
	public JSONObject app_getkaoti(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		List<App_KaoTiKu> kaotis = app_KaoTiKuService.getShiJianTiByLaoShiId(Integer.parseInt(yonghuid));
		for (App_KaoTiKu app_KaoTiKu : kaotis) {
			YongHu yongHu = yongHuService.selectYongHuByID(app_KaoTiKu.getShangchuanrenid());
			if (yongHu == null) {
				return jsonObject;
			}
			app_KaoTiKu.setLaoshixingming(yongHu.getYonghuxingming());
		}
		jsonObject.put("kaoti", kaotis);
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_addkaoti")
	public JSONObject app_addkaoti(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaotimingcheng = request.getParameter("kaotimingcheng");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		List<App_KaoTiKu> kaotis = app_KaoTiKuService.getShiJianTiByLaoShiId(Integer.parseInt(yonghuid));
		for (int i = 0; i < kaotis.size(); i++) {
			if (kaotis.get(i).getMingcheng().equals(kaotimingcheng)) {
				jsonObject.put("status", "samename");
				return jsonObject;
			}
		}
		App_KaoTiKu app_KaoTiKu = new App_KaoTiKu();
		app_KaoTiKu.setMingcheng(kaotimingcheng);
		app_KaoTiKu.setShangchuanrenid(Integer.parseInt(yonghuid));
		int i = app_KaoTiKuService.insert(app_KaoTiKu);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_laoshixuanti")
	public JSONObject app_laoshixuanti(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String timu = request.getParameter("timu");
		String shixiid = request.getParameter("shixiid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		if (timu == null || "".equals(timu)) {
			return jsonObject;
		}
//		有时间限制吗?
		List<Map<String, Object>> shiJianTis = app_ShiJianTiService.getKaoTiByLaoShiId(Integer.parseInt(yonghuid),Integer.parseInt(shixiid));
		for (String string2 : timu.trim().split(";")) {
			String timuid = string2.split(",")[0];
			String rongliang = string2.split(",")[1];
			for (int j = 0; j < shiJianTis.size(); j++) {
				if (shiJianTis.get(j).get("shiXiID").toString().equals(shixiid)
						&& shiJianTis.get(j).get("kaotikuid").toString().equals(timuid)) {
					jsonObject.put("status", "yixuan");
					return jsonObject;
				}
			}
			App_KaoTiKu app_KaoTiKu = app_KaoTiKuService.selectByPrimaryKey(Integer.parseInt(timuid));
			if (app_KaoTiKu == null) {
				jsonObject.put("status", "nokaoti");
				return jsonObject;
			}
			App_ShiJianTi app_ShiJianTi = new App_ShiJianTi();
			app_ShiJianTi.setTimuid(Integer.parseInt(timuid));
			app_ShiJianTi.setShixiid(Integer.parseInt(shixiid));
			app_ShiJianTi.setRongliang(Integer.parseInt(rongliang));
			app_ShiJianTi.setMingcheng(app_KaoTiKu.getMingcheng());
			app_ShiJianTi.setShifoushenhe(0);
			int i = app_ShiJianTiService.insert(app_ShiJianTi);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
				return jsonObject;
			}
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_allkaoti")
	public JSONObject app_allkaoti(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String shixiid = request.getParameter("shixiid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		List<Map<String, Object>> allshijiantiList = app_ShiJianTiService
				.getShiJianTiByShiXiId(Integer.parseInt(shixiid));
		jsonObject.put("allkaoti", allshijiantiList);
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_shenhekaoti")
	public JSONObject app_shenhekaoti(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaotiid = request.getParameter("kaotiid");
		String isTongGuo = request.getParameter("isTongGuo");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		for (String string2 : kaotiid.split(",")) {
			App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(string2));
			if (app_ShiJianTi == null) {
				jsonObject.put("status", "nokaoti");
				return jsonObject;
			}
			// 发布
			if (isTongGuo.equals("3")) {
				app_ShiJianTi.setShifoushenhe(3);
			}
			// 审核通过
			if (isTongGuo.equals("1")) {
				app_ShiJianTi.setShifoushenhe(1);
			}
			int i = app_ShiJianTiService.update(app_ShiJianTi);
			if (i != 0) {
				jsonObject.put("status", "success");
			} else {
				jsonObject.put("status", "fail");
			}
		}
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping(value = "app_getxuesheng")
	public JSONObject app_getxuesheng(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaotiid = request.getParameter("kaotiid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		List<Map<String, Object>> xueshengList = new ArrayList<Map<String, Object>>();
		if (app_ShiJianTi == null) {
			jsonObject.put("status", "nokaoti");
			return jsonObject;
		}
		if (app_ShiJianTi.getXueshengid() != null) {
			String xueshengids = app_ShiJianTi.getXueshengid();
			xueshengList = getxueshenginfo(xueshengids);
		}
		jsonObject.put("xueshengxin", xueshengList);
		return jsonObject;
	}

	// 查询学生信息
	private List<Map<String, Object>> getxueshenginfo(String xueshengids) {
		List<Map<String, Object>> xueshengMaps = xueShengService
				.selectByXueShengIDs2(Arrays.asList(xueshengids.split(",")));
		return xueshengMaps == null ? new ArrayList<Map<String, Object>>() : xueshengMaps;
	}

	// 考题发布
	@ResponseBody
	@RequestMapping(value = "app_kaotifabu")
	public JSONObject app_kaotifabu(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaotiid = request.getParameter("kaotiid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		app_ShiJianTi.setShifoushenhe(3);
		int i = app_ShiJianTiService.update(app_ShiJianTi);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 实习负责人修改考题容量
	@ResponseBody
	@RequestMapping(value = "app_updatekaoti")
	public JSONObject app_updatekaoti(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaotiid = request.getParameter("kaotiid");
		String rongliang = request.getParameter("rongliang");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi == null) {
			jsonObject.put("status", "nokaoti");
			return jsonObject;
		}
		app_ShiJianTi.setRongliang(Integer.parseInt(rongliang));
		int i = app_ShiJianTiService.update(app_ShiJianTi);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 老师修改自己提交的考题名称
	@ResponseBody
	@RequestMapping(value = "app_updatekaotiming")
	public JSONObject app_updatekaotiming(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaotiid = request.getParameter("kaotiid");
		String mingcheng = request.getParameter("mingcheng");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		if (app_ShiJianTi == null) {
			jsonObject.put("status", "nokaoti");
			return jsonObject;
		}
		app_ShiJianTi.setMingcheng(mingcheng);
		int i = app_ShiJianTiService.update(app_ShiJianTi);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}

	// 老师选学生
	@ResponseBody
	@RequestMapping(value = "app_laoshixuanxuesheng")
	public JSONObject app_laoshixuanxuesheng(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String kaotiid = request.getParameter("kaotiid");
		String xueshengids = request.getParameter("xueshengid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
		app_ShiJianTi.setXueshengid("," + xueshengids + ",");
		app_ShiJianTi.setShifoushenhe(4);
		int i = app_ShiJianTiService.update(app_ShiJianTi);
		if (i != 0) {
			jsonObject.put("status", "success");
		} else {
			jsonObject.put("status", "fail");
		}
		return jsonObject;
	}
	
	// 分组设置
		@ResponseBody
		@RequestMapping(value = "app_fenzushezhi")
		public JSONObject app_fenzushezhi(HttpServletRequest request, HttpServletResponse response) {
			String yonghuid = request.getParameter("yonghuid");
			String token = request.getParameter("token");
			String status = request.getParameter("status");
			String kaotiid = request.getParameter("kaotiid");
			String fendazu = request.getParameter("fendazu");
			String fendazuleixing = request.getParameter("fendazuleixing");
			JSONObject jsonObject = new JSONObject();
			String string = yonghuid + "," + status;
			String str = Util.SHA1Encode(string);
			if (!str.equals(token)) {
				return jsonObject;
			}
			App_ShiJianTi app_ShiJianTi = app_ShiJianTiService.selectByPrimaryKey(Integer.parseInt(kaotiid));
			String shezhiString = "";
			if(fendazu.equals("0") ) {
				shezhiString = "2,"+fendazu+",0;";
			}else {
				shezhiString = "2,"+fendazu+","+fendazuleixing+";";
			}
			app_ShiJianTi.setShezhi(shezhiString);
			int i = app_ShiJianTiService.update(app_ShiJianTi);
//			if(fendazu.equals("1")) {
//				App_FenZu app_FenZu = new App_FenZu();
//				app_FenZu.setRongLiang(app_ShiJianTi.getRongliang());
//				app_FenZu.setZuMingCheng(app_ShiJianTi.getMingcheng());
//				app_FenZu.setZuZhangId(zuZhangId);
//				app_FenZu.setShiJianKeChengId(app_ShiJianTi.getShixiid());
//				app_FenZuService.insertdazu(app_FenZu);
//			}
		
			return jsonObject;
		}
		
	
	// 分组信息
	@ResponseBody
	@RequestMapping(value = "app_fenzuxinxi")
	public JSONObject app_fenzuxinxi(HttpServletRequest request, HttpServletResponse response) {
		String yonghuid = request.getParameter("yonghuid");
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String shixiid = request.getParameter("shixiid");
		JSONObject jsonObject = new JSONObject();
		String string = yonghuid + "," + status;
		String str = Util.SHA1Encode(string);
		if (!str.equals(token)) {
			return jsonObject;
		}
		List<Map<String, Object>> list = app_ShiJianTiService.getShiJianTiByShiXiId(Integer.parseInt(shixiid));
		List<Map<String, Object>> xueshengList = new ArrayList<Map<String,Object>>();
//		List<Map<String, Object>> xueshengList2 = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> app_ShiJianTi : list) {
			if (app_ShiJianTi.get("xueShengID") == null || "".equals(app_ShiJianTi.get("xueShengID"))) {
				jsonObject.put("status", "noxuesheng");
				return jsonObject;
			}
			if(app_ShiJianTi.get("sheZhi")!=null) {
				String shezhiString = app_ShiJianTi.get("sheZhi").toString();
				for(String string2 : shezhiString.split(";")) {
					String fendazuString [] = string2.split(",");
					String leixing = fendazuString[0];
					switch (leixing) {
					case "2":
						app_ShiJianTi.put("fendazu",fendazuString[1] );
						app_ShiJianTi.put("fenzuleixing",fendazuString[2]);
							String xueshengid = app_ShiJianTi.get("xueShengID").toString();
							xueshengList = getxueshenginfo(xueshengid);
						break;
					default:
						break;
					}
				}
//				xueshengList2 = getxueshenginfo(xueshengids);
			}
			
			app_ShiJianTi.put("xueshengxinxi", xueshengList);
		}
//		jsonObject.put("allxuesheng", xueshengList2);
		jsonObject.put("kaotis", list);
		return jsonObject;
	}
	
	// 老师分大组
		@ResponseBody
		@RequestMapping(value = "app_laoshifenzu")
		public JSONObject app_laoshifenzu(HttpServletRequest request, HttpServletResponse response) {
			String yonghuid = request.getParameter("yonghuid");
			String token = request.getParameter("token");
			String status = request.getParameter("status");
			String xueshengid = request.getParameter("xueshengid");
			String mingcheng = request.getParameter("mingcheng");
			String shixiid = request.getParameter("shixiid");
			String zuzhangid = request.getParameter("zuzhangid");
			JSONObject jsonObject = new JSONObject();
			String string = yonghuid + "," + status;
			String str = Util.SHA1Encode(string);
			if (!str.equals(token)) {
				return jsonObject;
			}
			App_FenZu app_FenZu = new App_FenZu();
//			app_FenZu.setRongLiang(rong);
			app_FenZu.setZuMingCheng(mingcheng);
			app_FenZu.setZuZhangId(Integer.parseInt(zuzhangid));
			app_FenZu.setShiJianKeChengId(Integer.parseInt(shixiid));
			int i = app_FenZuService.insertdazu(app_FenZu);
			List<Map<String, Object>> xueshengList = getxueshenginfo(xueshengid);
			for(Map<String, Object> map : xueshengList) {
				
			}
			return jsonObject;
		}
	
}
